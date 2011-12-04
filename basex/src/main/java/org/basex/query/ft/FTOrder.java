package org.basex.query.ft;

import static org.basex.util.Token.*;
import java.io.IOException;
import org.basex.data.FTMatch;
import org.basex.data.FTStringMatch;
import org.basex.io.serial.Serializer;
import org.basex.query.QueryContext;
import org.basex.query.QueryText;
import org.basex.util.InputInfo;
import org.basex.util.ft.FTLexer;

/**
 * FTOrder expression.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class FTOrder extends FTFilter {
  /**
   * Constructor.
   * @param ii input info
   * @param e expression
   */
  public FTOrder(final InputInfo ii, final FTExpr e) {
    super(ii, e);
  }

  @Override
  protected boolean filter(final QueryContext ctx, final FTMatch mtc,
      final FTLexer lex) {

    int p = 0, s = 0;
    boolean f = true;
    for(final FTStringMatch sm : mtc) {
      if(sm.ex) continue;
      if(f) {
        if(p == sm.q) continue;
        p = sm.q;
      }
      f = s <= sm.s;
      if(f) s = sm.s;
    }
    return f;
  }

  @Override
  public void plan(final Serializer ser) throws IOException {
    ser.openElement(this, token(QueryText.ORDERED), TRUE);
    super.plan(ser);
  }

  @Override
  public String toString() {
    return super.toString() + QueryText.ORDERED;
  }
}
