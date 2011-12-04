package org.basex.tests.w3c;

import org.basex.core.Prop;
import org.basex.util.Util;

/**
 * XQuery Test Suite wrapper.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class XQTS extends W3CTS {
  /**
   * Main method of the test class.
   * @param args command-line arguments
   * @throws Exception exception
   */
  public static void main(final String[] args) throws Exception {
    new XQTS().run(args);
  }

  /**
   * Constructor.
   */
  public XQTS() {
    super(Util.name(XQTS.class));
    context.prop.set(Prop.XQUERY3, false);
  }
}
