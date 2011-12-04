package org.basex.core.cmd;

import org.basex.core.User;
import org.basex.data.Nodes;

/**
 * Evaluates the 'cs' command and sets a new initial context set.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class Cs extends AQuery {
  /**
   * Default constructor.
   * @param query query
   */
  public Cs(final String query) {
    super(DATAREF | User.READ, query);
  }

  @Override
  protected boolean run() {
    queryNodes();
    if(result == null) return false;
    if(result.size() != 0) {
      context.current(((Nodes) result).checkRoot());
    }
    return true;
  }
}
