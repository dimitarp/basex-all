package org.basex.query.util;

import java.util.Arrays;
import org.basex.query.item.Value;
import org.basex.util.Util;
import org.basex.util.list.ElementList;

/**
 * This is a simple container for values.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class ValueList extends ElementList {
  /** List entries. */
  Value[] list = new Value[CAP];

  /**
   * Default constructor.
   */
  public ValueList() {
    this(CAP);
  }

  /**
   * Constructor, specifying an initial array capacity.
   * @param c array capacity
   */
  public ValueList(final int c) {
    list = new Value[c];
  }

  /**
   * Adds an element to the list.
   * @param e element be added
   */
  public void add(final Value e) {
    if(size == list.length) {
      final Value[] tmp = new Value[newSize()];
      System.arraycopy(list, 0, tmp, 0, size);
      list = tmp;
    }
    list[size++] = e;
  }

  /**
   * Returns the element at the specified index.
   * @param i index
   * @return element
   */
  public Value get(final int i) {
    return list[i];
  }

  @Override
  public String toString() {
    return Util.name(this) + Arrays.toString(Arrays.copyOf(list, size));
  }
}
