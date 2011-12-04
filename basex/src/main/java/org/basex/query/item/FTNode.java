package org.basex.query.item;

import org.basex.data.Data;
import org.basex.data.FTMatches;
import org.basex.util.ft.Scoring;

/**
 * Disk-based full-text Node item.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class FTNode extends DBNode {
  /** Length of the full-text token. */
  private final int tl;
  /** Total number of indexed results. */
  private final int is;
  /** Full-text matches. */
  public FTMatches all;

  /**
   * Constructor, called by the sequential variant.
   * @param a matches
   * @param s scoring
   */
  public FTNode(final FTMatches a, final double s) {
    this(a, null, 0, 0, 0, s);
  }

  /**
   * Constructor, called by the index variant.
   * @param a full-text matches
   * @param d data reference
   * @param p pre value
   * @param tol token length
   * @param tis total size indexed results
   * @param s score value out of the index
   */
  public FTNode(final FTMatches a, final Data d, final int p, final int tol,
      final int tis, final double s) {
    super(d, p, null, NodeType.TXT);
    all = a;
    tl = tol;
    is = tis;
    if(s != -1) score = s;
  }

  @Override
  public double score() {
    if(score == null) {
      if(all == null) return 0;
      score = Scoring.textNode(all.size, is, tl, data.textLen(pre, true));
    }
    return score;
  }

  @Override
  public String toString() {
    return super.toString() + (all != null ? " (" + all.size + ")" : "");
  }
}
