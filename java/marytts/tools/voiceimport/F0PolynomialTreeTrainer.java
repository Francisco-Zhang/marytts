/**
 * Copyright 2006 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * This file is part of MARY TTS.
 *
 * MARY TTS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package marytts.tools.voiceimport;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.sound.sampled.AudioFormat;
import javax.swing.JFrame;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import marytts.cart.CART;
import marytts.cart.DirectedGraph;
import marytts.cart.io.DirectedGraphWriter;
import marytts.cart.io.MaryCARTWriter;
import marytts.features.FeatureDefinition;
import marytts.features.FeatureVector;
import marytts.signalproc.analysis.F0TrackerAutocorrelationHeuristic;
import marytts.signalproc.analysis.PitchFileHeader;
import marytts.signalproc.display.FunctionGraph;
import marytts.tools.voiceimport.traintrees.AgglomerativeClusterer;
import marytts.tools.voiceimport.traintrees.F0ContourPolynomialDistanceMeasure;
import marytts.tools.voiceimport.traintrees.Wagon;
import marytts.unitselection.concat.DatagramDoubleDataSource;
import marytts.unitselection.data.Datagram;
import marytts.unitselection.data.FeatureFileReader;
import marytts.unitselection.data.TimelineReader;
import marytts.unitselection.data.Unit;
import marytts.unitselection.data.UnitFileReader;
import marytts.util.data.BufferedDoubleDataSource;
import marytts.util.data.audio.AudioPlayer;
import marytts.util.data.audio.DDSAudioInputStream;
import marytts.util.math.MathUtils;
import marytts.util.math.Polynomial;
import marytts.util.signal.SignalProcUtils;


public class F0PolynomialTreeTrainer extends VoiceImportComponent
{
    protected File maryDir;
    protected FeatureFileReader features;
    protected FeatureDefinition featureDefinition;
    protected FeatureFileReader contours;
    protected DatabaseLayout db = null;
    
    public final String FEATUREFILE = "F0PolynomialTreeTrainer.featureFile";
    public final String F0FEATUREFILE = "F0PolynomialTreeTrainer.f0FeatureFile";  
    public final String WAGONDIR = "F0PolynomialTreeTrainer.wagonDir";
    public final String WAGONEXECUTABLE = "F0PolynomialTreeTrainer.wagonExecutable";
    public final String F0TREE = "F0PolynomialTreeTrainer.treeFile";
    public final String BALANCE = "F0PolynomialTreeTrainer.wagonBalance";
    public final String STOP = "F0PolynomialTreeTrainer.wagonStop";

    public String getName() {
        return "F0PolynomialTreeTrainer";
    }
   
    
   public SortedMap<String, String> getDefaultProps(DatabaseLayout db){
       this.db = db;
       if (props == null) {
           props = new TreeMap<String, String>();
           String fileDir = db.getProp(db.FILEDIR);
           String maryExt = db.getProp(db.MARYEXT);
           props.put(FEATUREFILE,fileDir+"halfphoneFeatures"+maryExt);
           props.put(F0FEATUREFILE,fileDir+"syllableF0Polynoms"+maryExt);
           props.put(WAGONDIR, "f0contours");
           props.put(WAGONEXECUTABLE, System.getenv("ESTDIR")+"/main/wagon");
           props.put(F0TREE, fileDir+"f0contourtree.mry");
           props.put(BALANCE, "0");
           props.put(STOP, "50");
       }
       return props;
   }
    
   protected void setupHelp()
   {
       if (props2Help ==null) {
           props2Help = new TreeMap<String, String>();
           props2Help.put(FEATUREFILE,"file containing all halfphone units and their target cost features");
           props2Help.put(F0FEATUREFILE,"file containing syllable-based polynom coefficients on vowels");
           props2Help.put(WAGONDIR, "directory in which to store the wagon files");
           props2Help.put(WAGONEXECUTABLE, "full path of the wagon executable from the Edinburgh speech tools");
           props2Help.put(F0TREE, "the path for saving the resulting f0 contour tree");
           props2Help.put(BALANCE, "the wagon balance value");
           props2Help.put(STOP, "the wagon stop criterion (min number of items in leaf)");
       }
   }
  
   
    public boolean compute() throws IOException
    {
        logger.info("F0 polynomial tree trainer started.");

        features = new FeatureFileReader(getProp(FEATUREFILE));
        featureDefinition = features.getFeatureDefinition();
        contours = new FeatureFileReader(getProp(F0FEATUREFILE));
        if (features.getNumberOfUnits() != contours.getNumberOfUnits()) {
            throw new IllegalArgumentException("Number of units differs between feature file and contour file -- they are out of sync");
        }
        
        
        // 1. Extract the feature vectors for which there are contours
        List<FeatureVector> relevantFVList = new ArrayList<FeatureVector>();
        for (int i=0, numUnits=contours.getNumberOfUnits(); i<numUnits; i++) {
            FeatureVector fv = contours.getFeatureVector(i);
            float[] floats = fv.getContinuousFeatures();
            boolean isZero = true;
            for (int j=0; j<floats.length; j++) {
                if (floats[j] != 0) {
                    isZero = false;
                    break;
                }
            }
            if (!isZero) {
                relevantFVList.add(features.getFeatureVector(i));
                // TODO: remove cutoff here:
                if (relevantFVList.size() >= 2000) break;
            }
        }
        FeatureVector[] relevantFV = relevantFVList.toArray(new FeatureVector[0]);
        logger.debug("Read "+relevantFV.length+" f0 contours and corresponding feature vectors");

        // 2. Grow a tree
        //CART cart = trainWagon(relevantFV);
        DirectedGraph graph = trainAgglomerativeCluster(relevantFV);
        
        // 3. Save resulting tree in MARY format.
        if (graph != null) {
            DirectedGraphWriter writer = new DirectedGraphWriter();
            writer.saveGraph(graph, getProp(F0TREE));
            writer.toTextOut(graph, new PrintWriter(System.out));
            return true;
        }
        return false;
    }


    private CART trainWagon(FeatureVector[] relevantFV) throws IOException
    {
        // 2. Call wagon with these feature vectors 
        // and a distance measure based on contour distances
        Wagon.setWagonExecutable(new File(getProp(WAGONEXECUTABLE)));
        File wagonDir = new File(getProp(WAGONDIR));
        if (!wagonDir.exists()) {
            wagonDir.mkdirs();
        }
        Wagon w = new Wagon("f0contours", featureDefinition, relevantFV,
                new F0ContourPolynomialDistanceMeasure(contours),
                wagonDir,
                Integer.parseInt(getProp(BALANCE)),
                Integer.parseInt(getProp(STOP)));
        w.run();
        if (w.success()) return w.getCART();
        return null;
    }


    private DirectedGraph trainAgglomerativeCluster(FeatureVector[] relevantFV)
    throws IOException
    {
        AgglomerativeClusterer clusterer = new AgglomerativeClusterer(relevantFV,
                featureDefinition,
                new F0ContourPolynomialDistanceMeasure(contours));
        return clusterer.cluster();
    }

    
    
    /**
     * Provide the progress of computation, in percent, or -1 if
     * that feature is not implemented.
     * @return -1 if not implemented, or an integer between 0 and 100.
     */
    public int getProgress()
    {
        return -1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        F0PolynomialTreeTrainer acfeatsWriter = 
            new F0PolynomialTreeTrainer();
        DatabaseLayout db = new DatabaseLayout(acfeatsWriter);
        acfeatsWriter.compute();
    }


}

