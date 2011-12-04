package org.basex.gui.layout;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import org.basex.gui.GUIConstants.Msg;

/**
 * Project specific Label implementation.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public class BaseXLabel extends JLabel {
  /**
   * Constructor.
   */
  public BaseXLabel() {
    this("", false, false);
  }

  /**
   * Constructor, specifying a label text.
   * @param txt label text
   */
  public BaseXLabel(final String txt) {
    this(txt, false, false);
  }

  /**
   * Constructor, specifying a label text, vertical distance to the next
   * component, and a property for printing the label in bold.
   * @param txt label text
   * @param dist vertical distance to next component
   * @param bold bold flag
   */
  public BaseXLabel(final String txt, final boolean dist, final boolean bold) {
    super(txt);
    if(dist) border(0, 0, 8, 0);
    setFont(getFont().deriveFont(bold ? 1 : 0));
  }

  /**
   * Sets the label borders.
   * @param t top distance
   * @param l left distance
   * @param b bottom distance
   * @param r right distance
   * @return self reference
   */
  public BaseXLabel border(final int t, final int l, final int b, final int r) {
    setBorder(new EmptyBorder(t, l, b, r));
    return this;
  }

  /**
   * Sets the text color.
   * @param c color
   * @return self reference
   */
  public BaseXLabel color(final Color c) {
    setForeground(c);
    return this;
  }

  /**
   * Shows an text, preceded by a state icon.
   * If the text is {@code null}, no text and icon is shown.
   * @param text warning text
   * @param icon flag for displaying a warning or error icon
   */
  public void setText(final String text, final Msg icon) {
    setIcon(text == null ? null : icon.small);
    setText(text == null ? " " : text);
  }

  @Override
  public void setEnabled(final boolean flag) {
    if(flag != isEnabled()) super.setEnabled(flag);
  }
}
