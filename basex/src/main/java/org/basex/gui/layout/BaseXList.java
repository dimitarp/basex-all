package org.basex.gui.layout;

import static org.basex.gui.layout.BaseXKeys.*;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.MouseInputAdapter;

import org.basex.core.Prop;
import org.basex.gui.dialog.Dialog;
import org.basex.io.IOFile;
import org.basex.util.Token;
import org.basex.util.list.IntList;
import org.basex.util.list.StringList;

/**
 * Combination of text field and a list, communicating with each other.
 * List entries are automatically completed if they match the first characters
 * of the typed in text. Moreover, the cursor keys can be used to scroll
 * through the list, and list entries can be chosen with mouse clicks.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class BaseXList extends BaseXBack {
  /** Scroll pane. */
  private final JScrollPane scroll;
  /** Single choice. */
  final boolean single;
  /** Text field. */
  final BaseXTextField text;
  /** List. */
  final JList list;
  /** List values. */
  String[] values;
  /** Numeric list. */
  boolean num = true;

  /**
   * Default constructor.
   * @param choice the input values for the list
   * @param d dialog reference
   */
  public BaseXList(final String[] choice, final Dialog d) {
    this(choice, d, true);
  }

  /**
   * Default constructor.
   * @param choice the input values for the list
   * @param d dialog reference
   * @param s only allow single choices
   */
  public BaseXList(final String[] choice, final Dialog d, final boolean s) {
    // cache list values
    values = choice.clone();
    single = s;

    // checks if list is purely numeric
    for(final String v : values) num = num && v.matches("[0-9]+");

    layout(new TableLayout(2, 1));
    text = new BaseXTextField(d);
    text.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(final FocusEvent e) {
        text.selectAll();
      }
    });
    text.addKeyListener(new KeyAdapter() {
      boolean typed;

      @Override
      public void keyPressed(final KeyEvent e) {
        final int page = getHeight() / getFont().getSize();
        final int[] inds = list.getSelectedIndices();
        final int op1 = inds.length == 0 ? -1 : inds[0];
        final int op2 = inds.length == 0 ? -1 : inds[inds.length - 1];
        int np1 = op1, np2 = op2;

        if(NEXTLINE.is(e)) {
          np2 = Math.min(op2 + 1, values.length - 1);
        } else if(PREVLINE.is(e)) {
          np1 = Math.max(op1 - 1, 0);
        } else if(NEXTPAGE.is(e)) {
          np2 = Math.min(op2 + page, values.length - 1);
        } else if(PREVPAGE.is(e)) {
          np1 = Math.max(op1 - page, 0);
        } else if(TEXTSTART.is(e)) {
          np1 = 0;
        } else if(TEXTEND.is(e)) {
          np2 = values.length - 1;
        } else {
          return;
        }

        final IntList il = new IntList();
        for(int n = np1; n <= np2; n++) il.add(n);
        // choose new list value
        final int nv = op2 != np2 ? np2 : np1;
        final String val = values[nv];
        list.setSelectedValue(val, true);
        if(e.isShiftDown() && !single) {
          list.setSelectedIndices(il.toArray());
          text.setText("");
        } else {
          list.setSelectedIndex(nv);
          text.setText(val);
          text.selectAll();
        }
      }

      @Override
      public void keyTyped(final KeyEvent e) {
        final char ch = e.getKeyChar();
        if(num) {
          typed = ch >= '0' && ch <= '9';
          if(!typed) e.consume();
        } else {
          typed = ch >= ' ' && ch != 127;
        }
      }

      @Override
      public void keyReleased(final KeyEvent e) {
        if(typed) {
          typed = false;

          final String txt = text.getText().trim().toLowerCase(Locale.ENGLISH);
          final boolean glob = txt.matches("^.*[*?,].*$");
          final String regex = glob ? IOFile.regex(txt, false) : null;

          final IntList il = new IntList();
          for(int i = 0; i < values.length; ++i) {
            final String db = Prop.WIN ? values[i].toLowerCase(Locale.ENGLISH) :
              values[i];
            if(glob) {
              if(db.matches(regex)) il.add(i);
            } else if(db.startsWith(txt)) {
              final int c = text.getCaretPosition();
              list.setSelectedValue(values[i], true);
              text.setText(values[i]);
              text.select(c, values[i].length());
              break;
            }
          }
          if(glob) {
            list.setSelectedValue(values[il.get(0)], true);
            list.setSelectedIndices(il.toArray());
          }
        }
        d.action(null);
      }
    });
    add(text);

    final MouseInputAdapter mouse = new MouseInputAdapter() {
      @Override
      public void mouseEntered(final MouseEvent e) {
        BaseXLayout.focus(text, null);
      }
      @Override
      public void mousePressed(final MouseEvent e) {
        final Object[] i = list.getSelectedValues();
        if(i.length == 0) return;

        text.setText(i.length == 1 ? i[0].toString() : "");
        text.requestFocusInWindow();
        text.selectAll();
        d.action(null);
      }
      @Override
      public void mouseDragged(final MouseEvent e) {
        mousePressed(e);
      }
      @Override
      public void mouseClicked(final MouseEvent e) {
        if(e.getClickCount() == 2) {
          d.close();
          return;
        }
      }
    };

    list = new JList(choice);
    list.setFocusable(false);
    if(s) list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addMouseListener(mouse);
    list.addMouseMotionListener(mouse);
    text.setFont(list.getFont());
    BaseXLayout.addInteraction(list, d);

    scroll = new JScrollPane(list,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    add(scroll);
    setIndex(0);
  }

  /**
   * Chooses the specified value in the text field and list.
   * @param value the value to be set
   */
  public void setValue(final String value) {
    list.setSelectedValue(value, true);
    text.setText(value);
  }

  /**
   * Sets the specified font.
   * @param font font name
   * @param style style
   */
  public void setFont(final String font, final int style) {
    final Font f = text.getFont();
    text.setFont(new Font(font, style, f.getSize()));
  }

  /**
   * Returns all list choices.
   * @return list index entry
   */
  public String[] getList() {
    return values;
  }

  /**
   * Returns the first (or only) chosen value of the text field.
   * @return text field value
   */
  public String getValue() {
    return text.getText();
  }

  /**
   * Returns all selected values.
   * @return text field value
   */
  public StringList getValues() {
    final StringList sl = new StringList();
    for(final Object o : list.getSelectedValues()) sl.add(o.toString());
    return sl;
  }

  /**
   * Returns the numeric representation of the user input. If the
   * text field is invalid, the list entry is returned.
   * @return numeric value
   */
  public int getNum() {
    final int i = Token.toInt(text.getText());
    if(i != Integer.MIN_VALUE) return i;
    final Object value = list.getSelectedValue();
    return value != null ? Token.toInt(value.toString()) : 0;
  }

  /**
   * Returns the current list index.
   * @return list index entry
   */
  public int getIndex() {
    return list.getSelectedIndex();
  }

  /**
   * Sets the specified list index.
   * @param i list entry
   */
  public void setIndex(final int i) {
    if(i < values.length) setValue(values[i]);
    else text.setText("");
  }

  @Override
  public void setSize(final int w, final int h) {
    BaseXLayout.setWidth(text, w);
    BaseXLayout.setSize(scroll, w, h);
  }

  /**
   * Resets the data shown in the list.
   * @param data list data
   */
  public void setData(final String[] data) {
    values = data.clone();
    list.setListData(data);
    setIndex(0);
  }

  @Override
  public boolean requestFocusInWindow() {
    return text.requestFocusInWindow();
  }
}
