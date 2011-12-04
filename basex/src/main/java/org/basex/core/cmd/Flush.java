package org.basex.core.cmd;

import static org.basex.core.Text.*;

import org.basex.core.Command;
import org.basex.core.Prop;
import org.basex.core.User;
import org.basex.data.Data;

/**
 * Evaluates the 'flush' command and flushes the database buffers.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class Flush extends Command {
  /**
   * Default constructor.
   */
  public Flush() {
    super(User.WRITE | DATAREF);
  }

  @Override
  protected boolean run() {
    final Data data = context.data();
    final boolean af = prop.is(Prop.AUTOFLUSH);
    prop.set(Prop.AUTOFLUSH, true);
    data.flush();
    prop.set(Prop.AUTOFLUSH, af);
    return info(DBFLUSHED, data.meta.name, perf);
  }
}
