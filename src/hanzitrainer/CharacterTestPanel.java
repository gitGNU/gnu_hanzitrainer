/*
 * CharacterTest.java
 *
 * Created on June 20, 2008, 4:19 PM
 * 
 * HanziTrainer to help you learn Mandarin
 * Copyright (c) 2008, Matthieu Jeanson ( matthieu.jeanson[at]gmail.com )
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * The name its contributors may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MATTHIEU JEANSON ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL MATTHIEU JEANSON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hanzitrainer;

import java.util.ArrayList;
import java.util.Locale;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.Font;

/**
 *
 * @author  Administrator
 */
public class CharacterTestPanel extends javax.swing.JPanel 
    implements TableModel, hanzitrainer.internals.HanziTab
{

    /** Creates new form CharacterTest */
    public CharacterTestPanel(HanziDBscore database, HanziApplicationUpdater updater)
    {
        main_database = database;
        parent_app = updater;
        initComponents();
        character_history = new ArrayList<String>();
        chinese_word_list = new ArrayList<String>();
        chinese_word_list_state = new ArrayList<Integer>();
        chinese_word_list_translation = new ArrayList<String>();
        current_chinese_words = new ArrayList<String>();
        current_chinese_words_translation = new ArrayList<String>();
        guess_pinyins = new ArrayList<String>();
        guess_chinese_words = new ArrayList<String>();

        set_new_character_to_guess();
    }

    public void FontPreferenceChanged(java.awt.Font character_font, java.awt.Font chinese_font)
    {
        CharacterLabel.setFont(character_font);
        GuessChineseTextField.setFont(chinese_font);
        PreviousCharDBTable.setFont(chinese_font);
        PreviousCharacterLabel.setFont(chinese_font);
    }
    public void DatabaseChanged()
    {
        set_new_character_to_guess();
    }
    public void CedictDatabaseChanged() {}

    private void check_old_data()
    {
        String[] guessed_chinese;
        ArrayList<String> good_chinese = new ArrayList<String>();
        ArrayList<String> bad_chinese = new ArrayList<String>();
        ArrayList<String> other_chinese = new ArrayList<String>();
        ArrayList<String> good_chinese_translation = new ArrayList<String>();
        ArrayList<String> bad_chinese_translation = new ArrayList<String>();
        ArrayList<String> other_chinese_translation = new ArrayList<String>();
        TableModelEvent t_event = new TableModelEvent(this);
        int i;

        // If there was nothing, then do not do anything...
        if (current_character.equals(""))
            return;

        // Move all current stuff to previous
        previous_pinyins = current_pinyins;
        previous_character = current_character;
        previous_chinese_words = current_chinese_words;
        previous_chinese_words_translation = current_chinese_words_translation;

        check_old_pinyins();

        // Check for any suggested chinese words
        if (!GuessChineseTextField.getText().equals(""))
        {
            // Store the different words into a table
            guessed_chinese = GuessChineseTextField.getText().split("[,，]");
            guess_chinese_words.clear();
            for (String item : guessed_chinese)
            {
                guess_chinese_words.add(item.trim());
            }
            // Sort between good and bad chinese words
            for (i=0; i<guess_chinese_words.size(); i++)
            {
                String item = guess_chinese_words.get(i);
                
                if (!previous_chinese_words.contains(item))
                {
                    bad_chinese.add(item);
                    bad_chinese_translation.add("");
                }
                else
                {
                    good_chinese.add(item);
                    good_chinese_translation.add(
                            previous_chinese_words_translation.get(previous_chinese_words.indexOf(item))
                            );
                }
            }
        }
        // Find any unguessed chinese, but keep the single character words as good
        for (i=0; i<previous_chinese_words.size(); i++)
        {
            String item = previous_chinese_words.get(i);
            if ((!good_chinese.contains(item)) && (!bad_chinese.contains(item)))
            {
                if (item.codePointCount(0, item.length())==1)
                {
                    good_chinese.add(item);
                    good_chinese_translation.add(
                            previous_chinese_words_translation.get(previous_chinese_words.indexOf(item))
                            );
                }
                else
                {
                    other_chinese.add(item);
                    other_chinese_translation.add(
                            previous_chinese_words_translation.get(previous_chinese_words.indexOf(item))
                            );
                }
            }
        }
        
        // for any well guessed Chinese word, increase its score
        for (String item : good_chinese)
        {
            main_database.change_word_score(main_database.get_word_id(item),
                    true, 1);
        }
        
        // Store the chinese words in a table with the state for the colors
        chinese_word_list.clear();
        chinese_word_list_state.clear();
        chinese_word_list_translation.clear();
        for (i=0; i<bad_chinese.size(); i++)
        {
            chinese_word_list.add(bad_chinese.get(i));
            chinese_word_list_state.add(0);
            chinese_word_list_translation.add(bad_chinese_translation.get(i));
        }
        for (i=0; i<good_chinese.size(); i++)
        {
            chinese_word_list.add(good_chinese.get(i));
            chinese_word_list_state.add(1);
            chinese_word_list_translation.add(good_chinese_translation.get(i));
        }
        for (i=0; i<other_chinese.size(); i++)
        {
            chinese_word_list.add(other_chinese.get(i));
            chinese_word_list_state.add(2);
            chinese_word_list_translation.add(other_chinese_translation.get(i));
        }
        PreviousCharDBTable.tableChanged(t_event);

        PreviousCharacterLabel.setText(previous_character);
    }
    
    private void check_old_pinyins()
    {
        String[] guessed_pinyins;
        ArrayList<String> good_pinyins = new ArrayList<String>();
        ArrayList<String> bad_pinyins = new ArrayList<String>();
        ArrayList<String> other_pinyins = new ArrayList<String>();
        String pinyins_result;
        int num_char = main_database.get_number_characters();

        // If there was nothing, then do not do anything...
        if (current_character.equals(""))
            return;

        
        // Store the guessed pinyins in a table
        guessed_pinyins = GuessPinyinTextField.getText().split("[,，]");
        guess_pinyins.clear();
        for (String item : guessed_pinyins)
        {
            Pinyin py = new Pinyin(item.trim());
            guess_pinyins.add(py.get_lame_version());
        }
        
        // Sort out the pinyins between good, bad and others (not guessed)
        for (String item : guess_pinyins)
        {
            if (!previous_pinyins.contains(item))
            {
                bad_pinyins.add(item);
            }
            else
            {
                good_pinyins.add(item);
            }
        }
        for (String item : previous_pinyins)
        {
            if ((!good_pinyins.contains(item)) && (!bad_pinyins.contains(item)))
            {
                other_pinyins.add(item);
            }
        }
        
        // Update the score for the character depending on the pinyins
        // for each good pinyin, increase big
        for (String item : good_pinyins)
        {
            main_database.change_character_score(
                    current_character, 
                    true, 1);
        }
        // for each bad pinyin, if it is just a bad tone, do not change
        // if it is just bad, decrease big
        for (String item : bad_pinyins)
        {
            boolean found_similar_radical=false;
            for (String item_other : other_pinyins)
            {
                if (Pinyin.pinyins_are_same_radical(item, item_other))
                    found_similar_radical=true;
            }
            if (!found_similar_radical)
                main_database.change_character_score(
                        current_character, 
                        false, 1);
            found_similar_radical=false;
        }
        // for each other pinyin, if it does not have any similar bad pinyin
        // decrease a little
        for (String item : other_pinyins)
        {
            boolean found_similar_radical=false;
            for (String item_bad : bad_pinyins)
            {
                if (Pinyin.pinyins_are_same_radical(item, item_bad))
                    found_similar_radical=true;
            }
            if (!found_similar_radical)
                main_database.change_character_score(
                        current_character, 
                        false, 2);
            found_similar_radical=false;
        }
        
        if (good_pinyins.size() != 0)
        {
            character_history.add(current_character);
            if (character_history.size() > (num_char - 1) / 2) 
            {
                character_history.remove(0);
            }
        }
        
        // Create some HTML text with the pinyin to put some colors...
        pinyins_result = "<HTML><FONT COLOR=\"RED\">";
        for (String item : bad_pinyins)
        {
            pinyins_result += item + " ";
        }
        pinyins_result += "</FONT><FONT COLOR=\"GREEN\">";
        for (String item : good_pinyins)
        {
            pinyins_result += item + " ";
        }
        pinyins_result += "</FONT><FONT COLOR=\"BLACK\">";
        for (String item : other_pinyins)
        {
            pinyins_result += item + " ";
        }
        pinyins_result += "</HTML>";

        PreviousPinyinsLabel.setText(pinyins_result);        
    }

    private void set_new_character_to_guess()
    {
        int num_char = main_database.get_number_characters();
        int index;
        String hanzi;
        ArrayList<Integer> words;

        if (num_char == 0)
        {
            current_character = "";
            current_pinyins = new ArrayList<String>();
            CharacterLabel.setText(current_character);
            GuessPinyinTextField.setText("");
            GuessChineseTextField.setText("");
            return;
        }
        
        // pick a character randomly that cannot be found in the history
        do
        {
            index = (int) (SettingsDialog.random_low() * num_char);
            System.out.println("random number got " + index + " over " + num_char);
            hanzi = main_database.get_character_low_score(index);
            System.out.println("getting index " + index);
        }
        while (character_history.contains(hanzi));
        current_chinese_words.clear();
        current_chinese_words_translation.clear();
        words = main_database.get_words_with_character(hanzi);
        for (Integer word_id : words)
        {
            current_chinese_words.add(main_database.get_word_details(word_id).get(0));
            current_chinese_words_translation.add(main_database.get_word_details(word_id).get(2));
        }
        current_pinyins = main_database.get_pinyin_for_character(hanzi);
        current_character = hanzi;
        CharacterLabel.setText(hanzi);
        GuessPinyinTextField.setText("");
        GuessChineseTextField.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CharacterLabel = new javax.swing.JLabel();
        DoneGuessCharacterButton = new javax.swing.JButton();
        PinyinsLabel = new javax.swing.JLabel();
        AsInLabel = new javax.swing.JLabel();
        GuessPinyinTextField = new javax.swing.JTextField();
        GuessChineseTextField = new javax.swing.JTextField();
        PreviousCharacterLabel = new javax.swing.JLabel();
        PreviouslyLabel = new javax.swing.JLabel();
        GoodOrBadLabel = new javax.swing.JLabel();
        PreviousCharDBScroll = new javax.swing.JScrollPane();
        PreviousCharDBTable = new javax.swing.JTable();
        //char_table_filler = new DBTableFiller(main_database);
        PreviousPinyinsLabel = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        CharacterLabel.setFont(Font.decode("MingLiU 80 56 12-Plain-24")); // NOI18N

        CharacterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CharacterLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CharacterLabel.setName("CharacterLabel"); // NOI18N

        DoneGuessCharacterButton.setText("Done"); // NOI18N
        DoneGuessCharacterButton.setName("DoneGuessCharacterButton"); // NOI18N
        DoneGuessCharacterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneGuessCharacterButton_action(evt);
            }
        });

        PinyinsLabel.setText("Pinyin(s)"); // NOI18N
        PinyinsLabel.setName("PinyinsLabel"); // NOI18N

        AsInLabel.setText("As in :"); // NOI18N
        AsInLabel.setName("AsInLabel"); // NOI18N

        GuessPinyinTextField.setText(""); // NOI18N
        GuessPinyinTextField.setName("GuessPinyinTextField"); // NOI18N
        GuessPinyinTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessPinyinTextFieldActionPerformed(evt);
            }
        });

        GuessChineseTextField.setText(""); // NOI18N
        GuessChineseTextField.setName("GuessChineseTextField"); // NOI18N
        GuessChineseTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessChineseTextFieldActionPerformed(evt);
            }
        });
        GuessChineseTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                GuessChineseTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                GuessChineseTextFieldFocusLost(evt);
            }
        });

        PreviousCharacterLabel.setFont(Font.decode("MingLiU 80 56 12-Plain-24")); // NOI18N
        PreviousCharacterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PreviousCharacterLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PreviousCharacterLabel.setName("PreviousCharacterLabel"); // NOI18N
        PreviousCharacterLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PreviousCharacterLabelMouseClicked(evt);
            }
        });

        PreviouslyLabel.setText("Previously :"); // NOI18N
        PreviouslyLabel.setName("PreviouslyLabel"); // NOI18N

        GoodOrBadLabel.setText(""); // NOI18N
        GoodOrBadLabel.setName("GoodOrBadLabel"); // NOI18N

        PreviousCharDBScroll.setName("PreviousCharDBScroll"); // NOI18N
        PreviousCharDBScroll.setPreferredSize(new java.awt.Dimension(447, 402));

        PreviousCharDBTable.setModel(this);
        PreviousCharDBTable.setName("PreviousCharDBTable"); // NOI18N
        PreviousCharDBTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PreviousCharDBTableMouseClicked(evt);
            }
        });
        PreviousCharDBScroll.setViewportView(PreviousCharDBTable);

        PreviousPinyinsLabel.setText(""); // NOI18N
        PreviousPinyinsLabel.setName("PreviousPinyinsLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PreviouslyLabel)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GoodOrBadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(PreviousCharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(PreviousPinyinsLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(DoneGuessCharacterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PreviousCharDBScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PinyinsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AsInLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GuessPinyinTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addComponent(GuessChineseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PinyinsLabel)
                            .addComponent(GuessPinyinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AsInLabel)
                            .addComponent(GuessChineseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DoneGuessCharacterButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(PreviousCharacterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(PreviouslyLabel)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(PreviousCharDBScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(PreviousPinyinsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(GoodOrBadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

private void DoneGuessCharacterButton_action(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneGuessCharacterButton_action

    check_old_data();
    set_new_character_to_guess();
    GuessPinyinTextField.requestFocus();
}//GEN-LAST:event_DoneGuessCharacterButton_action

private void PreviousCharDBTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreviousCharDBTableMouseClicked
    if (evt.getClickCount() == 2)
    {
        int row = PreviousCharDBTable.getSelectedRow();
        String chinese_word;
        chinese_word = (String) chinese_word_list.get(row);

        parent_app.edit_word(chinese_word);
    }
}//GEN-LAST:event_PreviousCharDBTableMouseClicked

private void GuessChineseTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GuessChineseTextFieldFocusLost
    GuessChineseTextField.getInputContext().selectInputMethod(Locale.getDefault());
}//GEN-LAST:event_GuessChineseTextFieldFocusLost

private void GuessChineseTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GuessChineseTextFieldFocusGained
    GuessChineseTextField.getInputContext().selectInputMethod(Locale.CHINA);
}//GEN-LAST:event_GuessChineseTextFieldFocusGained

private void GuessPinyinTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessPinyinTextFieldActionPerformed
    DoneGuessCharacterButton_action(evt);
}//GEN-LAST:event_GuessPinyinTextFieldActionPerformed

private void GuessChineseTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessChineseTextFieldActionPerformed
    DoneGuessCharacterButton_action(evt);
}//GEN-LAST:event_GuessChineseTextFieldActionPerformed

private void PreviousCharacterLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreviousCharacterLabelMouseClicked
    parent_app.review_character(previous_character);
}//GEN-LAST:event_PreviousCharacterLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AsInLabel;
    private javax.swing.JLabel CharacterLabel;
    private javax.swing.JButton DoneGuessCharacterButton;
    private javax.swing.JLabel GoodOrBadLabel;
    private javax.swing.JTextField GuessChineseTextField;
    private javax.swing.JTextField GuessPinyinTextField;
    private javax.swing.JLabel PinyinsLabel;
    private javax.swing.JScrollPane PreviousCharDBScroll;
    private javax.swing.JTable PreviousCharDBTable;
    private javax.swing.JLabel PreviousCharacterLabel;
    private javax.swing.JLabel PreviousPinyinsLabel;
    private javax.swing.JLabel PreviouslyLabel;
    // End of variables declaration//GEN-END:variables
    //private DBTableFiller char_table_filler;
    private ArrayList<String> character_history;
    private HanziDBscore main_database;
    private HanziApplicationUpdater parent_app;
    
    private String current_character;
    private ArrayList<String> current_pinyins;
    private ArrayList<String> current_chinese_words;
    private ArrayList<String> current_chinese_words_translation;
    
    private String previous_character;
    private ArrayList<String> previous_pinyins;
    private ArrayList<String> previous_chinese_words;
    private ArrayList<String> previous_chinese_words_translation;
    
    private ArrayList<String> guess_pinyins;
    private ArrayList<String> guess_chinese_words;
    private ArrayList<String> chinese_word_list;
    private ArrayList<Integer> chinese_word_list_state; // 0=good, 1=bad, 2=other
    private ArrayList<String> chinese_word_list_translation;

    @Override
    public int getRowCount()
    {
        return chinese_word_list.size();
    }

    @Override
    public int getColumnCount()
    {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0 :
                return "Chinese Word";
            case 1:
                return "Translation";
        }
        return "column " + columnIndex + " ??";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        int state;
        state = chinese_word_list_state.get(rowIndex);
        String res = "";
        switch (state)
        {
        case 0:
            res += "<HTML><FONT COLOR=\"RED\">";
            break;
        case 1:
            res += "<HTML><FONT COLOR=\"GREEN\">";
            break;
        case 2:
        default :
            break;
        }
        switch (columnIndex)
        {
            case 0:
                res += chinese_word_list.get(rowIndex);
                break;
            case 1:
                res += chinese_word_list_translation.get(rowIndex);
                break;
        }
        
        switch (state)
        {
        case 0:
        case 1:
            res += "</FONT></HTML>";
            break;
        case 2:
        default :
            break;
        }
        return res;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        return;
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {
    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {
    }
}
