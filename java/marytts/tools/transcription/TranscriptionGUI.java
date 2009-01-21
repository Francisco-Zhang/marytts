/*
 * TranscriptionGUI.java
 *
 * Created on September 29, 2008, 12:58 PM
 */

package marytts.tools.transcription;

import java.awt.Dimension;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import marytts.tools.dbselection.DBHandler;

/**
 * MARY TRANSCRIPTION TOOL
 * 
 * Transcription tool for Letter-to-sound(LTS) rules
 * and maintain list of functional words (useful for newlanguage POS tagging)
 * 
 * @author  sathish
 */
public class TranscriptionGUI extends javax.swing.JFrame {
    
    TranscriptionTable simplePanel;
    String fileNametoSave = null; 
    String phoneSetFile = null;
    boolean loadTranscription = false;  
    String treeAbsolutePath = null;
    String dirName = null;
    String baseName = null;
    String suffix = null;
    String locale = null;
    
    /** Creates new form TranscriptionGUI */
    public TranscriptionGUI() {
        initComponents();
        setupMenuAccelerators();
        try {
            simplePanel = new TranscriptionTable();
            simplePanel.resize(tablePanel.size());
            tablePanel.add(simplePanel, java.awt.BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablePanel.updateUI();
        loadFromFile.setEnabled(false);
        saveToFile.setEnabled(false);
        saveAsToFile.setEnabled(false);
        loadFromMySql.setEnabled(false);
        trainPredictButton.setEnabled(false);
        this.setVisible(true);
        helpMenuItemActionPerformed(null);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                startUpHelpDialog.toFront();
                closeHelp.grabFocus();
            }
        });
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        mySqlDetailsDialog = new javax.swing.JDialog();
        mySQLDetailsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hostNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dbNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        userNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tableNameTextField = new javax.swing.JTextField();
        submitPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        passwordTextField = new javax.swing.JPasswordField();
        startUpHelpDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closeHelp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        htmlEditor = new javax.swing.JEditorPane();
        transcriptionPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        trainPredictButton = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFromFile = new javax.swing.JMenuItem();
        saveToFile = new javax.swing.JMenuItem();
        saveAsToFile = new javax.swing.JMenuItem();
        loadFromMySql = new javax.swing.JMenuItem();
        loadPhoneSet = new javax.swing.JMenuItem();
        quitTool = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();

        mySqlDetailsDialog.setTitle("MySQL Details");
        mySqlDetailsDialog.getAccessibleContext().setAccessibleParent(loadFromMySql);
        mySQLDetailsPanel.setMinimumSize(new java.awt.Dimension(400, 250));
        jLabel1.setText("Host Name");

        hostNameTextField.setText("localhost");

        jLabel2.setText("Database Name");

        dbNameTextField.setText("MarySQLDatabase");

        jLabel3.setText("User Name");

        jLabel4.setText("Password");

        jLabel5.setText("Table Name");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromSqlDatabase(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelMySqlDetails(evt);
            }
        });

        org.jdesktop.layout.GroupLayout submitPanelLayout = new org.jdesktop.layout.GroupLayout(submitPanel);
        submitPanel.setLayout(submitPanelLayout);
        submitPanelLayout.setHorizontalGroup(
            submitPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(submitPanelLayout.createSequentialGroup()
                .add(123, 123, 123)
                .add(okButton)
                .add(14, 14, 14)
                .add(cancelButton)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        submitPanelLayout.setVerticalGroup(
            submitPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(submitPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(submitPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(okButton))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout mySQLDetailsPanelLayout = new org.jdesktop.layout.GroupLayout(mySQLDetailsPanel);
        mySQLDetailsPanel.setLayout(mySQLDetailsPanelLayout);
        mySQLDetailsPanelLayout.setHorizontalGroup(
            mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, mySQLDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, submitPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(mySQLDetailsPanelLayout.createSequentialGroup()
                        .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel1)
                            .add(jLabel5)
                            .add(jLabel4)
                            .add(jLabel3))
                        .add(26, 26, 26)
                        .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, userNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dbNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .add(hostNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, tableNameTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, passwordTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mySQLDetailsPanelLayout.setVerticalGroup(
            mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mySQLDetailsPanelLayout.createSequentialGroup()
                .add(16, 16, 16)
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(hostNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dbNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(userNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(passwordTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mySQLDetailsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(tableNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(submitPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout mySqlDetailsDialogLayout = new org.jdesktop.layout.GroupLayout(mySqlDetailsDialog.getContentPane());
        mySqlDetailsDialog.getContentPane().setLayout(mySqlDetailsDialogLayout);
        mySqlDetailsDialogLayout.setHorizontalGroup(
            mySqlDetailsDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mySqlDetailsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .add(mySQLDetailsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mySqlDetailsDialogLayout.setVerticalGroup(
            mySqlDetailsDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mySqlDetailsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .add(mySQLDetailsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        startUpHelpDialog.setTitle("Help");
        closeHelp.setText("Close");
        closeHelp.setSelected(true);
        closeHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeHelpDialog(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .add(closeHelp)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(closeHelp)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(htmlEditor);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout startUpHelpDialogLayout = new org.jdesktop.layout.GroupLayout(startUpHelpDialog.getContentPane());
        startUpHelpDialog.getContentPane().setLayout(startUpHelpDialogLayout);
        startUpHelpDialogLayout.setHorizontalGroup(
            startUpHelpDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(startUpHelpDialogLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        startUpHelpDialogLayout.setVerticalGroup(
            startUpHelpDialogLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(startUpHelpDialogLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mary Transcription Tool");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizedJFrame(evt);
            }
        });
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                ancestorFrameResized(evt);
            }
        });

        org.jdesktop.layout.GroupLayout tablePanelLayout = new org.jdesktop.layout.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 477, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 505, Short.MAX_VALUE)
        );

        trainPredictButton.setText("Train and Predict");
        trainPredictButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainPredictActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .add(trainPredictButton)
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(trainPredictButton)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout transcriptionPanelLayout = new org.jdesktop.layout.GroupLayout(transcriptionPanel);
        transcriptionPanel.setLayout(transcriptionPanelLayout);
        transcriptionPanelLayout.setHorizontalGroup(
            transcriptionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(transcriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(transcriptionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(transcriptionPanelLayout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(buttonPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(tablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        transcriptionPanelLayout.setVerticalGroup(
            transcriptionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, transcriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(tablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fileMenu.setText("File");
        loadFromFile.setText("Open");
        loadFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromFileActionPerformed(evt);
            }
        });

        fileMenu.add(loadFromFile);

        saveToFile.setText("Save");
        saveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToFileActionPerformed(evt);
            }
        });

        fileMenu.add(saveToFile);

        saveAsToFile.setText("Save As");
        saveAsToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsToFileActionPerformed(evt);
            }
        });

        fileMenu.add(saveAsToFile);

        loadFromMySql.setText("Load from MySQL Database");
        loadFromMySql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromMySqlActionPerformed(evt);
            }
        });

        fileMenu.add(loadFromMySql);

        loadPhoneSet.setText("Specify Phone Set");
        loadPhoneSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPhoneSetActionPerformed(evt);
            }
        });

        fileMenu.add(loadPhoneSet);

        quitTool.setText("Quit");
        quitTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeTranscriptionTool(evt);
            }
        });

        fileMenu.add(quitTool);

        jMenuBar2.add(fileMenu);

        helpMenu.setText("Help");
        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });

        helpMenu.add(helpMenuItem);

        jMenuBar2.add(helpMenu);

        setJMenuBar(jMenuBar2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(transcriptionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(transcriptionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeTranscriptionTool(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeTranscriptionTool
        System.exit(0);
    }//GEN-LAST:event_closeTranscriptionTool

    private void saveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToFileActionPerformed
        if(!checkNecessaryEvents("save")) return;
        if(fileNametoSave == null){
            saveAsToFileActionPerformed(evt);
        }
        else{
            simplePanel.saveTranscription(fileNametoSave);
        }
        checkNecessaryEvents("save");
        
    }//GEN-LAST:event_saveToFileActionPerformed

    
    private void setupMenuAccelerators()
    {
        int controlKey = ActionEvent.CTRL_MASK;
        if (System.getProperty("os.name").contains("Mac"))
            controlKey = ActionEvent.META_MASK; // Apple key
        saveToFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, controlKey));
        loadFromFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, controlKey));        
    }
    
    private void closeHelpDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeHelpDialog
        startUpHelpDialog.show(false);
        if (phoneSetFile == null) {
            loadPhoneSetActionPerformed(null);
        }
    }//GEN-LAST:event_closeHelpDialog

    private void cancelMySqlDetails(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelMySqlDetails
        mySqlDetailsDialog.show(false);
    }//GEN-LAST:event_cancelMySqlDetails

    private void loadFromSqlDatabase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromSqlDatabase
        
        if(!checkNecessaryEvents("load")) return;
        String hostName = hostNameTextField.getText(); 
        String databaseName = dbNameTextField.getText(); 
        String tableName = tableNameTextField.getText();
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        locale = simplePanel.getLocaleString();
        DBHandler wikiToDB = new DBHandler(locale);
        wikiToDB.createDBConnection(hostName,databaseName,userName,password);
        wikiToDB.setWordListTable(tableName);
        int noWords = wikiToDB.getNumberOfWords(0);
        //HashMap<String, Integer> hpMap = wikiToDB.getMostFrequentWords(noWords, 0);
        ArrayList<String> arrList = wikiToDB.getMostFrequentWordsArray(noWords, 0);
        wikiToDB.closeDBConnection();
        try {
            simplePanel.loadTranscription(arrList);
            simplePanel.repaint();
            simplePanel.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileNametoSave = null; 
        
        treeAbsolutePath = null;
        dirName = null;
        baseName = null;
        suffix = null;
        
        mySqlDetailsDialog.show(false);
        loadTranscription = true;
        saveToFile.setEnabled(true);
        saveAsToFile.setEnabled(true);
        checkNecessaryEvents("load");
        
    }//GEN-LAST:event_loadFromSqlDatabase

    private void ancestorFrameResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_ancestorFrameResized
        try {
            simplePanel.resize(tablePanel.size());
            tablePanel.add(simplePanel, java.awt.BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablePanel.updateUI();
        this.setVisible(true);        
    }//GEN-LAST:event_ancestorFrameResized

    private void resizedJFrame(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_resizedJFrame
        try {
            simplePanel.resize(tablePanel.size());
            tablePanel.add(simplePanel, java.awt.BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tablePanel.updateUI();
        this.setVisible(true);
        
    }//GEN-LAST:event_resizedJFrame

    private void loadPhoneSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPhoneSetActionPerformed
        if(!checkNecessaryEvents("phoneset")) return;
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Load phoneset file");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(TranscriptionGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            phoneSetFile  = file.getAbsolutePath();
            
            if(phoneSetFile != null) {
                simplePanel.loadPhoneSet(phoneSetFile);
                loadPhoneSet.setEnabled(false);
                loadFromFile.setEnabled(true);
                loadFromMySql.setEnabled(true);   
                locale = simplePanel.getLocaleString();
            }
        }        
    }//GEN-LAST:event_loadPhoneSetActionPerformed

    private void loadFromMySqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromMySqlActionPerformed
        if(!checkNecessaryEvents("load")) return;
        Dimension d = new Dimension(500, 250);
        mySqlDetailsDialog.setSize(d);
        mySqlDetailsDialog.show(true);
        checkNecessaryEvents("load");
    }//GEN-LAST:event_loadFromMySqlActionPerformed

    private void saveAsToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsToFileActionPerformed
        if(!checkNecessaryEvents("save")) return;
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showSaveDialog(TranscriptionGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            fileNametoSave = file.getAbsolutePath();
            File saveFile = new File(fileNametoSave);
            dirName = saveFile.getParentFile().getAbsolutePath();
            String filename = saveFile.getName();
            if(filename.lastIndexOf(".")==-1){
                baseName = filename;
                suffix   = "";
            }
            else{
                baseName = filename.substring(0,filename.lastIndexOf("."));
                suffix   = filename.substring(filename.lastIndexOf("."),filename.length());
            }
            simplePanel.saveTranscription(file.getAbsolutePath());
        }
        checkNecessaryEvents("save");
    }//GEN-LAST:event_saveAsToFileActionPerformed

    private void loadFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromFileActionPerformed
        if(!checkNecessaryEvents("load")) return;
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open transcription file");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(TranscriptionGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            simplePanel.loadTranscription(file.getAbsolutePath());
            fileNametoSave = file.getAbsolutePath();
            File saveFile = new File(fileNametoSave);
            dirName = saveFile.getParentFile().getAbsolutePath();
            String filename = saveFile.getName();
            if(filename.lastIndexOf(".")==-1){
                baseName = filename;
                suffix   = "";
            }
            else{
                baseName = filename.substring(0,filename.lastIndexOf("."));
                suffix   = filename.substring(filename.lastIndexOf("."),filename.length());
            }
            loadTranscription = true;
            saveToFile.setEnabled(true);
            saveAsToFile.setEnabled(true);
        }
        checkNecessaryEvents("load");
    }//GEN-LAST:event_loadFromFileActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        try {
            htmlEditor.setContentType("text/html; charset=UTF-8");        
            htmlEditor.read(new InputStreamReader(TranscriptionGUI.class.getResourceAsStream("instructions.html"), "UTF-8"), null);
            htmlEditor.setPreferredSize(new Dimension(500, 400));
            htmlEditor.setEditable(true);
            htmlEditor.updateUI();
            startUpHelpDialog.setSize(new Dimension(700, 500));
            startUpHelpDialog.repaint();
            closeHelp.grabFocus(); 
            startUpHelpDialog.show(true);
            startUpHelpDialog.repaint();
        } catch (IOException e) {
            e.printStackTrace();
                System.out.println("Could not read file : "
                    +e.getMessage());            
        }
        
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void trainPredictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainPredictActionPerformed
        if(phoneSetFile != null && loadTranscription && treeAbsolutePath == null){
            saveAsToFileActionPerformed(evt);
        }
        if(treeAbsolutePath != null){
            simplePanel.trainPredict(treeAbsolutePath);
        }
    }//GEN-LAST:event_trainPredictActionPerformed
    
    private boolean checkNecessaryEvents(String eventName){
        
        if(eventName.equals("load")){
            // First load phoneset
            if(phoneSetFile == null){
                JOptionPane.showMessageDialog(this,
                    "Please specify phoneset for your language.",
                        "Phoneset specification",
                            JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        if(eventName.equals("save")){
         
            // First load phoneset
            if(phoneSetFile == null){
                
                if(phoneSetFile == null){
                    JOptionPane.showMessageDialog(this,
                        "Please specify phoneset for your language.",
                            "Phoneset specification",
                                JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
            // Second load data
            if(!loadTranscription){
                if(phoneSetFile == null){
                    JOptionPane.showMessageDialog(this,
                        "Please load data to transcribe.",
                            "Load data",
                                JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        
        // set treeAbsolutePath
        if(fileNametoSave != null){
            treeAbsolutePath = dirName+File.separator+baseName+".lts";
        }
        
        // When to enable train-predict button
        if(phoneSetFile != null && loadTranscription){
            trainPredictButton.setEnabled(true);
        }
        else{
            trainPredictButton.setEnabled(false);
        }
        
        return true;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TranscriptionGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeHelp;
    private javax.swing.JTextField dbNameTextField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JTextField hostNameTextField;
    private javax.swing.JEditorPane htmlEditor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem loadFromFile;
    private javax.swing.JMenuItem loadFromMySql;
    private javax.swing.JMenuItem loadPhoneSet;
    private javax.swing.JPanel mySQLDetailsPanel;
    private javax.swing.JDialog mySqlDetailsDialog;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JMenuItem quitTool;
    private javax.swing.JMenuItem saveAsToFile;
    private javax.swing.JMenuItem saveToFile;
    private javax.swing.JDialog startUpHelpDialog;
    private javax.swing.JPanel submitPanel;
    private javax.swing.JTextField tableNameTextField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JButton trainPredictButton;
    private javax.swing.JPanel transcriptionPanel;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
    
}
