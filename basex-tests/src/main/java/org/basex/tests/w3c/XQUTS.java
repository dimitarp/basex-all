package org.basex.tests.w3c;

import org.basex.core.Prop;
import org.basex.data.Nodes;
import org.basex.util.Util;

/**
 * XQuery Update Test Suite wrapper.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class XQUTS extends W3CTS {
  /**
   * Main method of the test class.
   * @param args command-line arguments
   * @throws Exception exception
   */
  public static void main(final String[] args) throws Exception {
    new XQUTS().run(args);
  }

  /**
   * Constructor.
   */
  public XQUTS() {
    super(Util.name(XQUTS.class));
    context.prop.set(Prop.FORCECREATE, true);
  }

  @Override
  protected Nodes states(final Nodes root) throws Exception {
    return nodes("*:state", root);
  }

  @Override
  protected boolean updating() {
    return true;
  }
}
