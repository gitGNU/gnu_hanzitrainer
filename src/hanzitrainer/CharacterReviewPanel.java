/*
 * CharacterReviewPanel.java
 *
 * Created on June 18, 2008, 10:04 PM
 * 
 * HanziTrainer to help you learn Mandarin
 * Copyright (C) 2008  Matthieu Jeanson
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package hanzitrainer;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author  Administrator
 */
public class CharacterReviewPanel extends javax.swing.JPanel
{
    /** Creates new form CharacterReviewPanel */
    public CharacterReviewPanel(HanziDB database, HanziApplicationUpdater updater)
    {
        main_database = database;
        parent_app = updater;
        initComponents();
        CharTableFiller.set_character("");
        character_history = new ArrayList<String>();
    }

    public void CharacterReviewUpdateDB()
    {
        CharTableFiller.fireTableDataChanged();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CharacterLabel = new javax.swing.JLabel();
        NextCharacterButton = new javax.swing.JButton();
        CharsearchentryTextField = new javax.swing.JTextField();
        CharSearchButton = new javax.swing.JButton();
        CharPreviousButton = new javax.swing.JButton();
        CharDBScroll = new javax.swing.JScrollPane();
        CharDBTable = new javax.swing.JTable();
        CharTableFiller = new DBTableFiller(main_database);
        PinyinsTextfield = new javax.swing.JTextField();
        PinyinsLabel = new javax.swing.JLabel();
        PinyinsLabel1 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(hanzitrainer.HanziTrainerApp.class).getContext().getResourceMap(CharacterReviewPanel.class);
        CharacterLabel.setFont(resourceMap.getFont("CharacterLabel.font")); // NOI18N
        CharacterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CharacterLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CharacterLabel.setName("CharacterLabel"); // NOI18N

        NextCharacterButton.setText(resourceMap.getString("NextCharacterButton.text")); // NOI18N
        NextCharacterButton.setName("NextCharacterButton"); // NOI18N
        NextCharacterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextCharacterButtonrandom_character_action(evt);
            }
        });

        CharsearchentryTextField.setName("CharsearchentryTextField"); // NOI18N
        CharsearchentryTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CharsearchentryTextFieldCharSearchButtonAction(evt);
            }
        });
        CharsearchentryTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CharsearchentryTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CharsearchentryTextFieldFocusLost(evt);
            }
        });

        CharSearchButton.setText(resourceMap.getString("CharSearchButton.text")); // NOI18N
        CharSearchButton.setName("CharSearchButton"); // NOI18N
        CharSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CharSearchButtonAction(evt);
            }
        });

        CharPreviousButton.setText(resourceMap.getString("CharPreviousButton.text")); // NOI18N
        CharPreviousButton.setName("CharPreviousButton"); // NOI18N
        CharPreviousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CharPreviousButtonActionPerformed(evt);
            }
        });

        CharDBScroll.setName("CharDBScroll"); // NOI18N

        CharDBTable.setModel(CharTableFiller);
        CharDBTable.setName("CharDBTable"); // NOI18N
        CharDBTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CharDBTableMouseClicked(evt);
            }
        });
        CharDBScroll.setViewportView(CharDBTable);

        PinyinsTextfield.setEditable(false);
        PinyinsTextfield.setName("PinyinsTextfield"); // NOI18N

        PinyinsLabel.setText(resourceMap.getString("PinyinsLabel.text")); // NOI18N
        PinyinsLabel.setName("PinyinsLabel"); // NOI18N

        PinyinsLabel1.setText(resourceMap.getString("PinyinsLabel1.text")); // NOI18N
        PinyinsLabel1.setName("PinyinsLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PinyinsLabel)
                            .addComponent(PinyinsLabel1)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(CharsearchentryTextField)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CharSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(NextCharacterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CharPreviousButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CharDBScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(PinyinsTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CharPreviousButton)
                            .addComponent(NextCharacterButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CharSearchButton)
                            .addComponent(CharsearchentryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PinyinsLabel)
                            .addComponent(PinyinsTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CharDBScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(PinyinsLabel1))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void set_character_review(String hanzi)
    {
        ArrayList<String> pinyins = main_database.get_pinyin_from_character(hanzi);
        String pinyin_list = "";
        int i, j;

        if (pinyins.size() != 0)
        {
            for (i = pinyins.size() - 1; i >= 0; i--)
            {
                String pinyin_to_check = pinyins.get(i);
                int tone = pinyin_to_check.charAt(pinyin_to_check.length() - 1);
                boolean found = false;
                if ((tone < '1') || (tone > '4'))
                {
                    for (j = 0; j < pinyins.size(); j++)
                    {
                        if (i == j)
                        {
                            continue;
                        }
                        if (pinyins.get(j).startsWith(pinyin_to_check))
                        {
                            found = true;
                            break;
                        }
                    }
                    if (found)
                    {
                        pinyins.remove(i);
                    }
                }

            }

            pinyin_list = PinyinParser.convert_to_printed_version(pinyins.get(0));
            for (i = 1; i < pinyins.size(); i++)
            {
                pinyin_list += ", " + PinyinParser.convert_to_printed_version(pinyins.get(i));
            }
        }
        PinyinsTextfield.setText(pinyin_list);
        CharacterLabel.setText(hanzi);
        CharTableFiller.set_character(hanzi);
        CharTableFiller.fireTableDataChanged();
    }

private void NextCharacterButtonrandom_character_action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextCharacterButtonrandom_character_action
    int num_char = main_database.get_number_characters();
    int index;
    String hanzi;

    if (num_char == 0)
    {
        return;
    }
    do
    {
        index = (int) (Math.random() * num_char);
        hanzi = main_database.get_character_details(index);
    }
    while (character_history.contains(hanzi));
    character_history.add(hanzi);
    if (character_history.size() > (num_char - 1) / 2)
    {
        character_history.remove(0);
    }
    set_character_review(hanzi);
}//GEN-LAST:event_NextCharacterButtonrandom_character_action

private void CharsearchentryTextFieldCharSearchButtonAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CharsearchentryTextFieldCharSearchButtonAction
    String char_to_search = CharsearchentryTextField.getText();
    int num_char = main_database.get_number_characters();

    if (char_to_search.codePointCount(0, char_to_search.length()) != 1)
    {
        return;
    }
    character_history.add(char_to_search);
    if (character_history.size() > (num_char - 1) / 2)
    {
        character_history.remove(0);
    }
    set_character_review(char_to_search);
}//GEN-LAST:event_CharsearchentryTextFieldCharSearchButtonAction

private void CharsearchentryTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CharsearchentryTextFieldFocusGained
    CharsearchentryTextField.getInputContext().selectInputMethod(Locale.CHINA);
}//GEN-LAST:event_CharsearchentryTextFieldFocusGained

private void CharsearchentryTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CharsearchentryTextFieldFocusLost
    CharsearchentryTextField.getInputContext().selectInputMethod(Locale.getDefault());
}//GEN-LAST:event_CharsearchentryTextFieldFocusLost

private void CharSearchButtonAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CharSearchButtonAction
    String char_to_search = CharsearchentryTextField.getText();
    int num_char = main_database.get_number_characters();

    if (char_to_search.codePointCount(0, char_to_search.length()) != 1)
    {
        return;
    }
    character_history.add(char_to_search);
    if (character_history.size() > (num_char - 1) / 2)
    {
        character_history.remove(0);
    }
    set_character_review(char_to_search);
}//GEN-LAST:event_CharSearchButtonAction

private void CharPreviousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CharPreviousButtonActionPerformed
    String hanzi;

    if (character_history.size() <= 1)
    {
        return;
    }
    hanzi = character_history.get(character_history.size() - 2);
    character_history.remove(character_history.size() - 1);

    set_character_review(hanzi);
}//GEN-LAST:event_CharPreviousButtonActionPerformed

private void CharDBTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CharDBTableMouseClicked
    if (evt.getClickCount() == 2)
    {
        int row = CharDBTable.getSelectedRow();
        String chinese_word;
        chinese_word = (String) CharTableFiller.getValueAt(row, 0);
        
        parent_app.edit_word(chinese_word);
    }
}//GEN-LAST:event_CharDBTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane CharDBScroll;
    private javax.swing.JTable CharDBTable;
    private javax.swing.JButton CharPreviousButton;
    private javax.swing.JButton CharSearchButton;
    private javax.swing.JLabel CharacterLabel;
    private javax.swing.JTextField CharsearchentryTextField;
    private javax.swing.JButton NextCharacterButton;
    private javax.swing.JLabel PinyinsLabel;
    private javax.swing.JLabel PinyinsLabel1;
    private javax.swing.JTextField PinyinsTextfield;
    // End of variables declaration//GEN-END:variables
    private DBTableFiller CharTableFiller;
    private ArrayList<String> character_history;
    private HanziDB main_database;
    private HanziApplicationUpdater parent_app;

}
