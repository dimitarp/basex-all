package org.basex.gui;

import static org.basex.core.Text.*;
import java.awt.BorderLayout;
import org.basex.gui.layout.BaseXLabel;
import org.basex.gui.layout.BaseXLayout;
import org.basex.gui.layout.BaseXMem;
import org.basex.gui.layout.BaseXPanel;

/**
 * This is the status bar of the main window. It displays progress information
 * and includes a memory status.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class GUIStatus extends BaseXPanel {
  /** Memory usage. */
  private final BaseXMem mem;
  /** Status text. */
  private final BaseXLabel label;

  /**
   * Constructor.
   * @param main reference to the main window
   */
  GUIStatus(final AGUI main) {
    super(main);
    BaseXLayout.setHeight(this, getFont().getSize() + 6);
    addMouseListener(this);
    addMouseMotionListener(this);

    layout(new BorderLayout(4, 0));
    label = new BaseXLabel(OK).border(0, 4, 0, 0);
    add(label, BorderLayout.CENTER);
    mem = new BaseXMem(main, true);
    add(mem, BorderLayout.EAST);
  }

  /**
   * Sets the status text.
   * @param txt the text to be set
   */
  public void setText(final String txt) {
    label.setText(txt);
    repaint();
  }
}
