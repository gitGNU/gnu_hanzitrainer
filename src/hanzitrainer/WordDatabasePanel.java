/*
 * WordDatabasePanel.java
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

/**
 *
 * @author  Administrator
 */
public class WordDatabasePanel extends javax.swing.JPanel
{

    /** Creates new form WordDatabasePanel */
    public WordDatabasePanel(HanziDB database, HanziApplicationUpdater updater)
    {
        main_database = database;
        parent_app = updater;
        initComponents();
        WordDatabaseUpdateDB();
    }

    public void WordDatabaseUpdateDB()
    {
        int res;
        TableFiller.fireTableDataChanged();

        res = main_database.get_number_words();
        numWordLabel.setText("Number of words : " + res);

        res = main_database.get_average_word_score();
        avgWordScoreLabel.setText("Average score : " + res);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avgWordScoreLabel = new javax.swing.JLabel();
        numWordLabel = new javax.swing.JLabel();
        DBScroll = new javax.swing.JScrollPane();
        DBTable = new javax.swing.JTable();
        TableFiller = new DBTableFiller(main_database);

        setName("Form"); // NOI18N

        avgWordScoreLabel.setName("avgWordScoreLabel"); // NOI18N

        numWordLabel.setName("numWordLabel"); // NOI18N

        DBScroll.setName("DBScroll"); // NOI18N

        DBTable.setModel(TableFiller);
        DBTable.setName("DBTable"); // NOI18N
        DBTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DBTable.getTableHeader().setReorderingAllowed(false);
        DBTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DBTableMouseClicked(evt);
            }
        });
        DBScroll.setViewportView(DBTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(DBScroll)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(numWordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(avgWordScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numWordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avgWordScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DBScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void DBTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DBTableMouseClicked
    if (evt.getClickCount() == 2)
    {
        int row = DBTable.getSelectedRow();
        String chinese_word;
        chinese_word = (String) TableFiller.getValueAt(row, 0);

        parent_app.edit_word(chinese_word);
    }
}//GEN-LAST:event_DBTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane DBScroll;
    private javax.swing.JTable DBTable;
    private javax.swing.JLabel avgWordScoreLabel;
    private javax.swing.JLabel numWordLabel;
    // End of variables declaration//GEN-END:variables
    private DBTableFiller TableFiller;
    private HanziDB main_database;
    private HanziApplicationUpdater parent_app;
}
