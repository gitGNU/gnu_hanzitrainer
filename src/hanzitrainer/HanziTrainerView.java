/*
 * HanziTrainerView.java
 */

package hanzitrainer;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;


/**
 * The application's main frame.
 */
public class HanziTrainerView extends FrameView{
    
    HanziDB main_database;

    public HanziTrainerView(SingleFrameApplication app) {
        super(app);
        
        main_database = new HanziDB();

        initComponents();
 
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        VocabularyBuilderPanel = new javax.swing.JPanel();
        ChineseLabel = new javax.swing.JLabel();
        PinyinLabel = new javax.swing.JLabel();
        EnglishLabel = new javax.swing.JLabel();
        ChineseTextField = new javax.swing.JTextField();
        EnglishTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        PinyinScroll = new javax.swing.JScrollPane();
        DatabasePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        numCharLabel = new javax.swing.JLabel();
        numWordLabel = new javax.swing.JLabel();
        TestPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        OpenDBMenuItem = new javax.swing.JMenuItem();
        SaveDBMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        jComboBox5 = new javax.swing.JComboBox();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        VocabularyBuilderPanel.setName("VocabularyBuilderPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(hanzitrainer.HanziTrainerApp.class).getContext().getResourceMap(HanziTrainerView.class);
        ChineseLabel.setText(resourceMap.getString("ChineseLabel.text")); // NOI18N
        ChineseLabel.setName("ChineseLabel"); // NOI18N

        PinyinLabel.setText(resourceMap.getString("PinyinLabel.text")); // NOI18N
        PinyinLabel.setName("PinyinLabel"); // NOI18N

        EnglishLabel.setText(resourceMap.getString("EnglishLabel.text")); // NOI18N
        EnglishLabel.setName("EnglishLabel"); // NOI18N

        ChineseTextField.setText(resourceMap.getString("ChineseTextField.text")); // NOI18N
        ChineseTextField.setName("ChineseTextField"); // NOI18N

        this.ChineseTextField.getDocument().addDocumentListener(new PinyinChooserFrame(PinyinScroll,main_database));

        EnglishTextField.setText(resourceMap.getString("EnglishTextField.text")); // NOI18N
        EnglishTextField.setName("EnglishTextField"); // NOI18N

        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setName("SaveButton"); // NOI18N

        ResetButton.setText(resourceMap.getString("ResetButton.text")); // NOI18N
        ResetButton.setName("ResetButton"); // NOI18N

        PinyinScroll.setName("PinyinScroll"); // NOI18N

        javax.swing.GroupLayout VocabularyBuilderPanelLayout = new javax.swing.GroupLayout(VocabularyBuilderPanel);
        VocabularyBuilderPanel.setLayout(VocabularyBuilderPanelLayout);
        VocabularyBuilderPanelLayout.setHorizontalGroup(
            VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ChineseLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChineseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(PinyinLabel))
                            .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(EnglishLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnglishTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                            .addComponent(PinyinScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addContainerGap(349, Short.MAX_VALUE)
                        .addComponent(ResetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton)))
                .addContainerGap())
        );
        VocabularyBuilderPanelLayout.setVerticalGroup(
            VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChineseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChineseLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PinyinLabel)
                    .addComponent(PinyinScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnglishLabel)
                    .addComponent(EnglishTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(ResetButton))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        ChineseLabel.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel1.AccessibleContext.accessibleName")); // NOI18N

        jTabbedPane1.addTab(resourceMap.getString("VocabularyBuilderPanel.TabConstraints.tabTitle"), VocabularyBuilderPanel); // NOI18N

        DatabasePanel.setName("DatabasePanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setModel(new DBTableFiller(main_database));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane2.setViewportView(jTable1);

        numCharLabel.setText(resourceMap.getString("numCharLabel.text")); // NOI18N
        numCharLabel.setName("numCharLabel"); // NOI18N

        numWordLabel.setText(resourceMap.getString("numWordLabel.text")); // NOI18N
        numWordLabel.setName("numWordLabel"); // NOI18N

        javax.swing.GroupLayout DatabasePanelLayout = new javax.swing.GroupLayout(DatabasePanel);
        DatabasePanel.setLayout(DatabasePanelLayout);
        DatabasePanelLayout.setHorizontalGroup(
            DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
            .addGroup(DatabasePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(numCharLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numWordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        DatabasePanelLayout.setVerticalGroup(
            DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numCharLabel)
                    .addComponent(numWordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(resourceMap.getString("DatabasePanel.TabConstraints.tabTitle"), DatabasePanel); // NOI18N

        TestPanel.setName("TestPanel"); // NOI18N

        javax.swing.GroupLayout TestPanelLayout = new javax.swing.GroupLayout(TestPanel);
        TestPanel.setLayout(TestPanelLayout);
        TestPanelLayout.setHorizontalGroup(
            TestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        TestPanelLayout.setVerticalGroup(
            TestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("TestPanel.TabConstraints.tabTitle"), TestPanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        OpenDBMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenDBMenuItem.setMnemonic('O');
        OpenDBMenuItem.setText(resourceMap.getString("OpenDBMenuItem.text")); // NOI18N
        OpenDBMenuItem.setName("OpenDBMenuItem"); // NOI18N
        fileMenu.add(OpenDBMenuItem);
        OpenDBMenuItem.getAccessibleContext().setAccessibleDescription(resourceMap.getString("OpenDBItem1.AccessibleContext.accessibleDescription")); // NOI18N

        SaveDBMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveDBMenuItem.setText(resourceMap.getString("SaveDBMenuItem.text")); // NOI18N
        SaveDBMenuItem.setName("SaveDBMenuItem"); // NOI18N
        fileMenu.add(SaveDBMenuItem);
        SaveDBMenuItem.getAccessibleContext().setAccessibleDescription(resourceMap.getString("SaveDBMenuItem.AccessibleContext.accessibleDescription")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(hanzitrainer.HanziTrainerApp.class).getContext().getActionMap(HanziTrainerView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        menuBar.add(helpMenu);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setName("jComboBox5"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChineseLabel;
    private javax.swing.JTextField ChineseTextField;
    private javax.swing.JPanel DatabasePanel;
    private javax.swing.JLabel EnglishLabel;
    private javax.swing.JTextField EnglishTextField;
    private javax.swing.JMenuItem OpenDBMenuItem;
    private javax.swing.JLabel PinyinLabel;
    private javax.swing.JScrollPane PinyinScroll;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JMenuItem SaveDBMenuItem;
    private javax.swing.JPanel TestPanel;
    private javax.swing.JPanel VocabularyBuilderPanel;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel numCharLabel;
    private javax.swing.JLabel numWordLabel;
    // End of variables declaration//GEN-END:variables


}
