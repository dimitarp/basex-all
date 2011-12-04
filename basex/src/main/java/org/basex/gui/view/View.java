package org.basex.gui.view;

import static org.basex.gui.layout.BaseXKeys.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import org.basex.gui.GUICommands;
import org.basex.gui.GUIConstants;
import org.basex.gui.GUIConstants.Fill;
import org.basex.gui.GUIProp;
import org.basex.gui.layout.BaseXPanel;

/**
 * View observer pattern. All inheriting classes are attached to the
 * views array
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public abstract class View extends BaseXPanel {
  /**
   * Registers the specified view.
   * @param name name of view
   * @param hlp help text
   * @param man view manager
   */
  protected View(final String name, final byte[] hlp, final ViewNotifier man) {
    super(hlp, man.gui);
    mode(man.gui.gprop.is(GUIProp.GRADIENT) ? Fill.GRADIENT : Fill.PLAIN);
    setFocusable(true);
    setBackground(Color.white);
    setName(name);
    addMouseListener(this);
    addMouseMotionListener(this);
    addMouseWheelListener(this);
    addKeyListener(this);
    addComponentListener(this);
    man.add(this);
  }

  /**
   * Called when the data reference has changed.
   */
  public abstract void refreshInit();

  /**
   * Called when a new focus has been chosen.
   */
  public abstract void refreshFocus();

  /**
   * Called when a context set has been
   * marked.
   */
  public abstract void refreshMark();

  /**
   * Called when a new context set has been chosen.
   * @param more show more details
   * @param quick perform a quick context switch
   */
  public abstract void refreshContext(boolean more, boolean quick);

  /**
   * Called when GUI design has changed.
   */
  public abstract void refreshLayout();

  /**
   * Called when updates have been done in the data structure.
   */
  public abstract void refreshUpdate();

  /**
   * Tests if this view is currently visible.
   * @return result of check
   */
  public abstract boolean visible();

  /**
   * Sets the visibility of the view.
   * @param v visibility flag
   */
  public abstract void visible(final boolean v);

  /**
   * Tests if this view needs a database instance.
   * @return result of check
   */
  protected abstract boolean db();

  @Override
  public void mouseEntered(final MouseEvent e) {
    if(!gui.updating && gui.gprop.is(GUIProp.MOUSEFOCUS))
      requestFocusInWindow();
  }

  @Override
  public void mouseExited(final MouseEvent e) {
    if(!gui.updating) gui.cursor(GUIConstants.CURSORARROW);
  }

  @Override
  public void mousePressed(final MouseEvent e) {
    if(!gui.updating) requestFocusInWindow();
  }

  @Override
  public void keyPressed(final KeyEvent e) {
    if(gui.updating) return;
    if(ESCAPE.is(e)) {
      gui.fullscreen(false);
    } else if(SPACE.is(e)) {
      gui.notify.mark(sc(e) ? 2 : e.isShiftDown() ? 1 : 0, null);
    } else if(ENTER.is(e)) {
      GUICommands.FILTER.execute(gui);
    } else if(GOBACK2.is(e)) {
      GUICommands.GOBACK.execute(gui);
    }
  }

  @Override
  public final String toString() {
    return getName();
  }
}
