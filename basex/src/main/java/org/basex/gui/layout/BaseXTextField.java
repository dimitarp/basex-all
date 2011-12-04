package org.basex.gui.layout;

import static org.basex.gui.layout.BaseXKeys.*;

import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import org.basex.gui.layout.BaseXLayout.DropHandler;

/**
 * Project specific text field implementation.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public class BaseXTextField extends JTextField {
  /** Default width of text fields. */
  public static final int DWIDTH = 280;
  /** Last input. */
  String last = "";
  /** Text area to search in. */
  BaseXEditor area;
  /** Button help. */
  byte[] help;

  /**
   * Constructor.
   * @param win parent window
   */
  public BaseXTextField(final Window win) {
    this(null, win);
  }

  /**
   * Constructor.
   * @param txt input text
   * @param win parent window
   */
  public BaseXTextField(final String txt, final Window win) {
    BaseXLayout.setWidth(this, DWIDTH);
    BaseXLayout.addInteraction(this, win);

    if(txt != null) {
      setText(txt);
      selectAll();
    }

    addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(final FocusEvent e) {
        if(area != null) selectAll();
      }
    });

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(final MouseEvent e) {
        BaseXLayout.focus(e.getComponent(), help);
      }
    });

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(final KeyEvent e) {
        if(UNDO.is(e) || REDO.is(e)) {
          final String t = getText();
          setText(last);
          last = t;
        }
        if(area == null) return;
        final String text = getText();
        final boolean enter = ENTER.is(e);
        if(ESCAPE.is(e) || enter && text.isEmpty()) {
          area.requestFocusInWindow();
        } else if(enter || FINDNEXT.is(e) || FINDPREV.is(e) ||
            FINDNEXT2.is(e) || FINDPREV2.is(e)) {
          area.find(text, FINDPREV.is(e) || FINDPREV2.is(e) || e.isShiftDown());
        }
      }
      @Override
      public void keyReleased(final KeyEvent e) {
        if(area == null) return;
        final String text = getText();
        final char ch = e.getKeyChar();
        if(!control(e) && Character.isDefined(ch) && !ENTER.is(e))
          area.find(text, false);
        repaint();
      }
    });

    setDragEnabled(true);
    BaseXLayout.addDrop(this, new DropHandler() {
      @Override
      public void drop(final Object object) {
        replaceSelection(object.toString());
      }
    });
  }

  /**
   * Activates search functionality to the text field.
   * @param a text area to search
   */
  public final void setSearch(final BaseXEditor a) {
    area = a;
    BaseXLayout.setWidth(this, 120);
  }

  @Override
  public void setText(final String txt) {
    last = txt;
    super.setText(txt);
  }

  /**
   * Sets the text field help text.
   * @param hlp help text
   */
  public final void help(final byte[] hlp) {
    help = hlp;
  }
}
