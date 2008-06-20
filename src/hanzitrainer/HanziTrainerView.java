/*
 * HanziTrainerView.java
 */
package hanzitrainer;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.util.ArrayList;
import java.io.File;
import java.util.Locale;
import java.util.prefs.*;

/**
 * The application's main frame.
 */
public class HanziTrainerView extends FrameView implements HanziApplicationUpdater
{

    HanziDB main_database;
    private Preferences my_preferences;
    CharacterReviewPanel char_review;
    WordDatabasePanel word_database;
    CharacterTestPanel char_test;

    public HanziTrainerView(SingleFrameApplication app)
    {
        super(app);
        String database_file;

        my_preferences = Preferences.userRoot();

        main_database = new HanziDB();
        database_file = my_preferences.get("database_filename", "");
        if (!database_file.equals(""))
        {
            main_database.HanziDB_open(database_file);
        }
        initComponents();
        word_database = new WordDatabasePanel(main_database, this);
        Tabs.addTab("Word Database", word_database);
        char_review = new CharacterReviewPanel(main_database, this);
        Tabs.addTab("Character Review", char_review);
        char_test = new CharacterTestPanel(main_database, this);
        Tabs.addTab("Character Test", char_test);
    }

    @SuppressWarnings(
                      {
                          "static-access"
                      })
    public void HanziTrainerViewKill()
    {
        String database_file = main_database.HanziDB_get_filename();
        if (main_database.get_database_changed())
        {
            Object[] options =
            {
                "Save",
                "Do no save"
            };
            if (ChoicePane.showOptionDialog(this.getFrame(),
                    "You did not save the database, do you want to save now ?", "Save?",
                    ChoicePane.YES_NO_OPTION, ChoicePane.WARNING_MESSAGE,
                    null, options, options[1]) == ChoicePane.YES_OPTION)
            {
                if (database_file.equals(""))
                {
                    save_database_as(new java.awt.event.ActionEvent(this, java.awt.event.ActionEvent.ACTION_PERFORMED, "Save on quit"));
                }
                else
                {
                    main_database.HanziDB_save();
                }
            }
        }

        my_preferences.put("database_filename", database_file);
        main_database.shutdown();
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
        Tabs = new javax.swing.JTabbedPane();
        VocabularyBuilderPanel = new javax.swing.JPanel();
        ChineseLabel = new javax.swing.JLabel();
        PinyinLabel = new javax.swing.JLabel();
        EnglishLabel = new javax.swing.JLabel();
        ChineseTextField = new javax.swing.JTextField();
        EnglishTextField = new javax.swing.JTextField();
        SaveButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        PinyinScroll = new javax.swing.JScrollPane();
        EnglishTranslations = new javax.swing.JComboBox();
        AddNewWordButton = new javax.swing.JRadioButton();
        EditWordButton = new javax.swing.JRadioButton();
        DeleteWordButton = new javax.swing.JRadioButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        OpenDBMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        SaveDBMenuItem = new javax.swing.JMenuItem();
        SaveDBAsMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        vocabularyEditorButtonGroup = new javax.swing.ButtonGroup();
        ChoicePane = new javax.swing.JOptionPane();

        mainPanel.setName("mainPanel"); // NOI18N

        Tabs.setName("Tabs"); // NOI18N

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
        PinyinChooser = new PinyinChooserFrame(PinyinScroll,main_database);
        this.ChineseTextField.getDocument().addDocumentListener(PinyinChooser);
        ChineseTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ChineseTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ChineseTextFieldFocusLost(evt);
            }
        });

        EnglishTextField.setText(resourceMap.getString("EnglishTextField.text")); // NOI18N
        EnglishTextField.setName("EnglishTextField"); // NOI18N
        EnglishTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setName("SaveButton"); // NOI18N
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ResetButton.setText(resourceMap.getString("ResetButton.text")); // NOI18N
        ResetButton.setDefaultCapable(false);
        ResetButton.setName("ResetButton"); // NOI18N
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        PinyinScroll.setName("PinyinScroll"); // NOI18N

        EnglishTranslations.setEnabled(false);
        EnglishTranslations.setName("EnglishTranslations"); // NOI18N

        vocabularyEditorButtonGroup.add(AddNewWordButton);
        AddNewWordButton.setText(resourceMap.getString("AddNewWordButton.text")); // NOI18N
        AddNewWordButton.setEnabled(false);
        AddNewWordButton.setName("AddNewWordButton"); // NOI18N

        vocabularyEditorButtonGroup.add(EditWordButton);
        EditWordButton.setText(resourceMap.getString("EditWordButton.text")); // NOI18N
        EditWordButton.setEnabled(false);
        EditWordButton.setName("EditWordButton"); // NOI18N

        vocabularyEditorButtonGroup.add(DeleteWordButton);
        DeleteWordButton.setText(resourceMap.getString("DeleteWordButton.text")); // NOI18N
        DeleteWordButton.setEnabled(false);
        DeleteWordButton.setName("DeleteWordButton"); // NOI18N

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
                        .addComponent(ChineseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                    .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(PinyinLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PinyinScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                    .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(EnglishLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EnglishTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EnglishTranslations, javax.swing.GroupLayout.Alignment.TRAILING, 0, 286, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditWordButton)
                            .addComponent(AddNewWordButton)
                            .addComponent(DeleteWordButton)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VocabularyBuilderPanelLayout.createSequentialGroup()
                        .addContainerGap(417, Short.MAX_VALUE)
                        .addComponent(ResetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        VocabularyBuilderPanelLayout.setVerticalGroup(
            VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VocabularyBuilderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChineseLabel)
                    .addComponent(ChineseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PinyinScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PinyinLabel))
                .addGap(13, 13, 13)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnglishLabel)
                    .addComponent(EnglishTranslations, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNewWordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EnglishTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditWordButton))
                .addGap(3, 3, 3)
                .addComponent(DeleteWordButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VocabularyBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResetButton)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        ChineseLabel.getAccessibleContext().setAccessibleName(resourceMap.getString("jLabel1.AccessibleContext.accessibleName")); // NOI18N

        Tabs.addTab(resourceMap.getString("VocabularyBuilderPanel.TabConstraints.tabTitle"), VocabularyBuilderPanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tabs.getAccessibleContext().setAccessibleName(resourceMap.getString("jTabbedPane1.AccessibleContext.accessibleName")); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N
        fileMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                FileMenuSelected(evt);
            }
        });

        OpenDBMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenDBMenuItem.setMnemonic('O');
        OpenDBMenuItem.setText(resourceMap.getString("OpenDBMenuItem.text")); // NOI18N
        OpenDBMenuItem.setName("OpenDBMenuItem"); // NOI18N
        OpenDBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_database(evt);
            }
        });
        fileMenu.add(OpenDBMenuItem);
        OpenDBMenuItem.getAccessibleContext().setAccessibleDescription(resourceMap.getString("OpenDBItem1.AccessibleContext.accessibleDescription")); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_database(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        SaveDBMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveDBMenuItem.setText(resourceMap.getString("SaveDBMenuItem.text")); // NOI18N
        SaveDBMenuItem.setName("SaveDBMenuItem"); // NOI18N
        SaveDBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_database(evt);
            }
        });
        fileMenu.add(SaveDBMenuItem);
        SaveDBMenuItem.getAccessibleContext().setAccessibleDescription(resourceMap.getString("SaveDBMenuItem.AccessibleContext.accessibleDescription")); // NOI18N

        SaveDBAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.CTRL_MASK));
        SaveDBAsMenuItem.setText(resourceMap.getString("SaveDBAsMenuItem.text")); // NOI18N
        SaveDBAsMenuItem.setName("SaveDBAsMenuItem"); // NOI18N
        SaveDBAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_database_as(evt);
            }
        });
        fileMenu.add(SaveDBAsMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(hanzitrainer.HanziTrainerApp.class).getContext().getActionMap(HanziTrainerView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N
        menuBar.add(helpMenu);

        ChoicePane.setName("ChoicePane"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void init_file_chooser()
    {
        if (DBFileChooser == null)
        {
            DBFileChooser = new javax.swing.JFileChooser();
            javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter(
                    "Hanzi Trainer DB files", "ktdb");
            DBFileChooser.setFileFilter(filter);
        }
    }

    private int open_dialog()
    {
        init_file_chooser();
        return DBFileChooser.showOpenDialog(mainPanel);
    }

    private int save_dialog()
    {
        init_file_chooser();
        return DBFileChooser.showSaveDialog(mainPanel);
    }

    @SuppressWarnings("static-access")
private void open_database(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_database
        int returnVal = open_dialog();

        if (returnVal == DBFileChooser.APPROVE_OPTION)
        {
            File file = DBFileChooser.getSelectedFile();
            System.out.println("Opening: " + file.getPath());
            main_database.HanziDB_open(file.getPath());
            char_review.CharacterReviewUpdateDB();
            word_database.WordDatabaseUpdateDB();
        }
        else
        {
            System.out.println("Open command cancelled by user.");
        }
}//GEN-LAST:event_open_database

    @SuppressWarnings("static-access")
private void save_database_as(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_database_as
        int returnVal = save_dialog();
        if (returnVal == DBFileChooser.APPROVE_OPTION)
        {
            File file = DBFileChooser.getSelectedFile();
            System.out.println("Saving as: " + file.getPath());
            main_database.HanziDB_set_filename(file.getPath());
            main_database.HanziDB_save();
        }
        else
        {
            System.out.println("Open command cancelled by user.");
        }
}//GEN-LAST:event_save_database_as

private void save_database(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_database
    main_database.HanziDB_save();
}//GEN-LAST:event_save_database

private void close_database(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_database
    main_database.HanziDB_close();
    char_review.CharacterReviewUpdateDB();
    word_database.WordDatabaseUpdateDB();
}//GEN-LAST:event_close_database

private void FileMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_FileMenuSelected
    System.out.println("whether to enable save db ? file name is [" + main_database.HanziDB_get_filename() + "]");
    if (main_database.HanziDB_get_filename().equals(""))
    {
        SaveDBMenuItem.setEnabled(false);
    }
    else
    {
        SaveDBMenuItem.setEnabled(true);
    }
}//GEN-LAST:event_FileMenuSelected

private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
    ChineseTextField.setText("");
    EnglishTextField.setText("");
}//GEN-LAST:event_ResetButtonActionPerformed

private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
    String english = EnglishTextField.getText();
    ArrayList<String> pinyin = PinyinChooser.get_pinyins();
    String hanzi_string = ChineseTextField.getText();
    ArrayList<String> hanzi = new ArrayList<String>();
    int i;

    for (i = 0; i < pinyin.size(); i++)
    {
        if (!Pinyin.verify_pinyin(pinyin.get(i)))
        {
            return;
        }
    }
    for (i = 0; i < hanzi_string.codePointCount(0, hanzi_string.length()); i++)
    {
        int from;
        int to;
        from = hanzi_string.offsetByCodePoints(0, i);
        if (i == hanzi_string.codePointCount(0, hanzi_string.length()) - 1)
        {
            to = hanzi_string.length();
        }
        else
        {
            to = hanzi_string.offsetByCodePoints(0, i + 1);
        }
        hanzi.add(hanzi_string.substring(from, to));
    }
    if ((!AddNewWordButton.isEnabled()) || (AddNewWordButton.isSelected()))
    {
        if (english.length() == 0)
        {
            return;
        }
        main_database.add_translation(english, pinyin, hanzi);
    }
    else
    {
        if (DeleteWordButton.isSelected())
        {
            if (EnglishTranslations.getSelectedIndex() == 0)
            {
                return;
            }
            main_database.delete_translation((String) EnglishTranslations.getSelectedItem(), hanzi);
        }
        else
        {
            if (EditWordButton.isSelected())
            {
                if (EnglishTranslations.getSelectedIndex() == 0)
                {
                    return;
                }
                if (english.length() == 0)
                {
                    return;
                }
                main_database.delete_translation((String) EnglishTranslations.getSelectedItem(), hanzi);
                main_database.add_translation(english, pinyin, hanzi);
            }
        }
    }

    char_review.CharacterReviewUpdateDB();
    word_database.WordDatabaseUpdateDB();

    EnglishTranslations.removeAllItems();
    ChineseTextField.setText("");
    EnglishTextField.setText("");
}//GEN-LAST:event_SaveButtonActionPerformed

private void ChineseTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ChineseTextFieldFocusLost
    String hanzi_string = ChineseTextField.getText();
    ArrayList<String> translations = new ArrayList<String>();
    int i;

    ChineseTextField.getInputContext().selectInputMethod(Locale.getDefault());

    translations = main_database.get_chinese_word_translation(hanzi_string);
    if (translations.size() == 0)
    {
        return;
    }
    EnglishTranslations.addItem("[new]");
    for (i = 0; i < translations.size(); i++)
    {
        EnglishTranslations.addItem(translations.get(i));
    }
    EnglishTranslations.setEnabled(true);
    AddNewWordButton.setEnabled(true);
    EditWordButton.setEnabled(true);
    DeleteWordButton.setEnabled(true);
    AddNewWordButton.setSelected(true);
}//GEN-LAST:event_ChineseTextFieldFocusLost

private void ChineseTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ChineseTextFieldFocusGained
    ChineseTextField.getInputContext().selectInputMethod(Locale.CHINA);

    EnglishTranslations.removeAllItems();
    EnglishTranslations.setEnabled(false);
    AddNewWordButton.setEnabled(false);
    EditWordButton.setEnabled(false);
    DeleteWordButton.setEnabled(false);
    AddNewWordButton.setSelected(false);
    EditWordButton.setSelected(false);
    DeleteWordButton.setSelected(false);
}//GEN-LAST:event_ChineseTextFieldFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AddNewWordButton;
    private javax.swing.JLabel ChineseLabel;
    private javax.swing.JTextField ChineseTextField;
    private javax.swing.JOptionPane ChoicePane;
    private javax.swing.JRadioButton DeleteWordButton;
    private javax.swing.JRadioButton EditWordButton;
    private javax.swing.JLabel EnglishLabel;
    private javax.swing.JTextField EnglishTextField;
    private javax.swing.JComboBox EnglishTranslations;
    private javax.swing.JMenuItem OpenDBMenuItem;
    private javax.swing.JLabel PinyinLabel;
    private javax.swing.JScrollPane PinyinScroll;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JMenuItem SaveDBAsMenuItem;
    private javax.swing.JMenuItem SaveDBMenuItem;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JPanel VocabularyBuilderPanel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.ButtonGroup vocabularyEditorButtonGroup;
    // End of variables declaration//GEN-END:variables
    private PinyinChooserFrame PinyinChooser;
    private javax.swing.JFileChooser DBFileChooser;

    public void edit_word(String to_edit)
    {
        ChineseTextField.setText(to_edit);
        Tabs.setSelectedIndex(0);
    }
}
