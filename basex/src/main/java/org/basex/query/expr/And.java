package org.basex.query.expr;

import static org.basex.query.QueryText.*;

import org.basex.query.QueryContext;
import org.basex.query.QueryException;
import org.basex.query.item.Bln;
import org.basex.query.item.Item;
import org.basex.query.util.IndexContext;
import org.basex.util.Array;
import org.basex.util.InputInfo;
import org.basex.util.ft.Scoring;

/**
 * And expression.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class And extends Logical {
  /**
   * Constructor.
   * @param ii input info
   * @param e expression list
   */
  public And(final InputInfo ii, final Expr[] e) {
    super(ii, e);
  }

  @Override
  public Expr comp(final QueryContext ctx) throws QueryException {
    // remove atomic values
    final Expr c = super.comp(ctx);
    if(c != this) return c;

    // merge predicates if possible
    Expr[] ex = {};
    Pos ps = null;
    CmpR cr = null;
    for(final Expr e : expr) {
      Expr tmp = null;
      if(e instanceof Pos) {
        // merge numeric predicates
        tmp = ps == null ? e : ps.intersect((Pos) e, input);
        if(!(tmp instanceof Pos)) return tmp;
        ps = (Pos) tmp;
      } else if(e instanceof CmpR) {
        // merge comparisons
        tmp = cr == null ? e : cr.intersect((CmpR) e);
        if(tmp instanceof CmpR) cr = (CmpR) tmp;
        else if(tmp != null) return tmp;
      }
      // no optimization found; add original expression
      if(tmp == null) ex = Array.add(ex, e);
    }
    expr = ex;
    if(ps != null) expr = Array.add(expr, ps);
    if(cr != null) expr = Array.add(expr, cr);
    if(ex.length != expr.length) ctx.compInfo(OPTWRITE, this);
    compFlatten(ctx);

    // return single expression if it yields a boolean
    return expr.length == 1 ? compBln(expr[0]) : this;
  }

  @Override
  public Bln item(final QueryContext ctx, final InputInfo ii)
      throws QueryException {
    double s = 0;
    for(final Expr e : expr) {
      final Item it = e.ebv(ctx, input);
      if(!it.bool(input)) return Bln.FALSE;
      s = Scoring.and(s, it.score());
    }
    // no scoring - return default boolean
    return s == 0 ? Bln.TRUE : Bln.get(s);
  }

  @Override
  public boolean indexAccessible(final IndexContext ic) throws QueryException {
    int is = 0;
    final double[] ics = new double[expr.length];
    boolean ia = true;
    for(int e = 0; e < expr.length; ++e) {
      if(expr[e].indexAccessible(ic) && !ic.seq) {
        // skip queries with no results
        if(ic.costs() == 0) return true;
        // summarize costs
        ics[e] = ic.costs();
        if(is == 0 || ic.costs() < is) is = ic.costs();
      } else {
        ia = false;
      }
    }

    if(ia) {
      // evaluate arguments with high selectivity first
      final int[] ord = Array.createOrder(ics, true);
      final Expr[] ex = new Expr[ics.length];
      for(int e = 0; e < expr.length; ++e) ex[e] = expr[ord[e]];
      expr = ex;
    }

    ic.costs(is);
    return ia;
  }

  @Override
  public Expr indexEquivalent(final IndexContext ic) throws QueryException {
    super.indexEquivalent(ic);
    return new InterSect(input, expr);
  }

  @Override
  public String toString() {
    return toString(" " + AND + " ");
  }
}
