package org.basex.query.iter;

import org.basex.query.QueryException;
import org.basex.query.item.FTNode;

/**
 * Node iterator interface.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Sebastian Gath
 */
public abstract class FTIter extends Iter {
  @Override
  public abstract FTNode next() throws QueryException;
}
