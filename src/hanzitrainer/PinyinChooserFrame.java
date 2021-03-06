/*
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

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.util.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;
import javax.swing.text.*;

/**
 *
 * @author Administrator
 */
public class PinyinChooserFrame extends JPanel
        implements javax.swing.event.DocumentListener
{

    private ArrayList<javax.swing.JComponent> entry_list;
    private int number_of_boxes;
    private FlowLayout panel_layout;
    private JScrollPane scroller_container;
    private int entry_length;
    private HanziDB database;
    private CedictParser cedict_database = null;

    private class PinyinChooserComboRenderer extends JLabel implements ListCellRenderer
    {

        private int change_index;
        private DefaultListCellRenderer default_renderer;

        public PinyinChooserComboRenderer(int index)
        {
            change_index = index;
            default_renderer = new DefaultListCellRenderer();
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            Component comp = default_renderer.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
            setText(value.toString());
            if (index >= change_index)
            {
                System.out.println("index " + index + ", change " + change_index + ", text " +value.toString());
                setFont(comp.getFont().deriveFont(Font.ITALIC));
            }
            else
            {
                setFont(comp.getFont().deriveFont(Font.PLAIN));
            }

            return this;
        }
    }

    public PinyinChooserFrame(JScrollPane scroller, HanziDB db)
    {
        super();
        scroller_container = scroller;
        entry_list = new ArrayList<javax.swing.JComponent>();
        number_of_boxes = 0;
        panel_layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(panel_layout);
        entry_length = 0;
        database = db;
    }

    public PinyinChooserFrame(JScrollPane scroller, HanziDB db, CedictParser cedict)
    {
        this(scroller, db);
        cedict_database = cedict;
    }

    private void debuglog(String log)
    {
        System.out.println("PinyinChooserFrame : " + log);
    }

    private Boolean is_chinese_char(String input)
    {
        int entry;
        if (input.codePointCount(0, input.length()) != 1)
        {
            return false;
        }
        else
        {
            entry = input.codePointAt(0);
            if ((entry >= 0x4E00) && (entry <= 0x9fff))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    private void add_item(int index, String entry)
    {

        if (is_chinese_char(entry))
        {
            ArrayList<String> possibilities = database.get_pinyin_for_character(entry);
            if (cedict_database.check_for_empty_db())
            {
                add_combo_box(index, possibilities);
            }
            else
            {
                ArrayList<String> cedict_possibilities = cedict_database.get_pinyin_for_character(entry);
                for (int i=0; i<cedict_possibilities.size(); i++)
                {
                    String s = cedict_possibilities.get(i);
                    int siz = possibilities.size();
                    if (possibilities.contains(s))
                    {
                        cedict_possibilities.remove(i);
                    }
                }
                add_combo_box(index, possibilities, cedict_possibilities);
            }
        }
        else
        {
            add_label(index, entry);
        }
    }

    private void add_combo_box(int index, ArrayList<String> content)
    {
        int i;
        JComboBox temp_box = new JComboBox();
        for (i = 0; i < content.size(); i++)
        {
            temp_box.addItem(content.get(i));
        }
        temp_box.setEditable(true);
        entry_list.add(index, temp_box);
        number_of_boxes++;
        update_layout();
    }

    private void add_combo_box(int index, ArrayList<String> content, ArrayList<String> secondary)
    {
        int i;
        JComboBox temp_box = new JComboBox();
        for (i = 0; i < content.size(); i++)
        {
            temp_box.addItem(content.get(i));
        }
        for (i = 0; i < secondary.size(); i++)
        {
            temp_box.addItem(secondary.get(i));
        }
        temp_box.setEditable(true);
        temp_box.setRenderer(new PinyinChooserComboRenderer(content.size()));
        entry_list.add(index, temp_box);
        number_of_boxes++;
        update_layout();
    }

    private void add_label(int index, String content)
    {
        JLabel temp_label = new JLabel();
        temp_label.setText(content);
        entry_list.add(index, temp_label);
        number_of_boxes++;
        update_layout();
    }

    private void remove_combo_box(int index)
    {
        JComponent item = (JComponent) entry_list.get(index);
        entry_list.remove(index);
        number_of_boxes--;
        update_layout();
        panel_layout.removeLayoutComponent(item);
    }

    private void set_combo_box(int index, String pinyin)
    {
        JComboBox item = (JComboBox) entry_list.get(index);
        item.setSelectedItem(pinyin);
    }

    public ArrayList<String> get_pinyins()
    {
        ArrayList<String> res = new ArrayList<String>();
        int i;

        for (i = 0; i < number_of_boxes; i++)
        {
            if (entry_list.get(i) instanceof JLabel)
            {
                continue;
            }
            else
            {
                JComboBox box = (JComboBox) (entry_list.get(i));
                String selected = (String) (box.getSelectedItem());
                res.add(selected);
            }
        }
        return res;
    }

    private void update_layout()
    {
        int i;

        removeAll();

        for (i = 0; i < number_of_boxes; i++)
        {
            add((Component) entry_list.get(i));
        }

        scroller_container.setViewportView(this);
    }

    private void check_existing_word(String current)
    {
        int id = database.get_word_id(current);
        int i;

        if (id != -1)
        {
            String pinyin_str = database.get_word_details(id).get(1);
            ArrayList<Pinyin> pinyins = PinyinParser.parse_string(pinyin_str);
            debuglog("found word id " + id + " " + pinyin_str);
            for (i = 0; i < pinyins.size(); i++)
            {
                set_combo_box(i, pinyins.get(i).get_lame_version());
            }
        }
        return;
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e)
    {
        Document doc = e.getDocument();
        try
        {
            check_existing_word(doc.getText(0, doc.getLength()));
        }
        catch (BadLocationException ex)
        {
            debuglog("bad location (changedUpdate) !");
            Logger.getLogger(PinyinChooserFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUpdate(javax.swing.event.DocumentEvent e)
    {
        Document doc = e.getDocument();
        String inserted;
        int i;

        e.getChange(e.getDocument().getDefaultRootElement());
        try
        {
            inserted = doc.getText(e.getOffset(), e.getLength());
            if ((Character.isWhitespace(inserted.codePointAt(e.getLength() - 1))))
            {
                debuglog("not yet");
            }
            else
            {
                if (e.getOffset() > entry_length)
                {
                    debuglog("inserted " + (e.getOffset() - entry_length) + " spaces");
                    for (i = 0; i < e.getOffset() - entry_length; i++)
                    {
                        add_label(e.getOffset(), new String(" "));
                    }
                }
                for (i = e.getLength() - 1; i >= 0; i--)
                {
                    add_item(e.getOffset(), doc.getText(e.getOffset() + i, 1));
                }
                debuglog("inserted " + e.getLength() + " long, at " + e.getOffset() + " added [" + doc.getText(e.getOffset(), e.getLength()) + "]");
                entry_length = doc.getLength();
            }

            check_existing_word(doc.getText(0, doc.getLength()));

        }
        catch (BadLocationException ex)
        {
            debuglog("bad location !");
            Logger.getLogger(PinyinChooserFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeUpdate(javax.swing.event.DocumentEvent e)
    {
        Document doc = e.getDocument();
        int i;

        if (doc.getLength() == entry_length)
        {
            debuglog("no change");
            return;
        }
        else
        {
            //debuglog(doc.getLength() + " != " + entry_length);
            entry_length = doc.getLength();
            try
            {
                debuglog("removed " + e.getLength() + " long, at " + e.getOffset() + " now [" + doc.getText(0, doc.getLength()) + "]");
                for (i = 0; i < e.getLength(); i++)
                {
                    remove_combo_box(e.getOffset());
                }
                check_existing_word(doc.getText(0, doc.getLength()));
            }
            catch (BadLocationException ex)
            {
                debuglog("bad location !");
                Logger.getLogger(PinyinChooserFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
