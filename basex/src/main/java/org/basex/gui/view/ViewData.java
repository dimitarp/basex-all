package org.basex.gui.view;

import static org.basex.data.DataText.*;
import org.basex.data.Data;
import org.basex.gui.GUIProp;
import org.basex.util.Token;
import org.basex.util.TokenBuilder;
import org.basex.util.list.IntList;

/**
 * This class assembles some database access methods which are used
 * in the same way by different visualizations. If more specific database
 * access is needed, it is advisable to directly work on the {@link Data}
 * class.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class ViewData {
  /** Preventing class instantiation. */
  private ViewData() { }

  /**
   * Checks if the specified node is a leaf node
   * (text node or file element or file tag).
   * @param prop gui properties
   * @param d data reference
   * @param pre pre value
   * @return result of comparison
   */
  public static boolean leaf(final GUIProp prop, final Data d,
      final int pre) {
    final int kind = d.kind(pre);
    if(kind == Data.ATTR) return true;

    final boolean atts = prop.is(GUIProp.MAPATTS);
    final int last = pre + (atts ? 1 : d.attSize(pre, kind));
    return last == d.meta.size || d.parent(pre, kind) >=
      d.parent(last, d.kind(last));
  }

  /**
   * Returns path for the specified pre value.
   * @param data data reference
   * @param pre pre value
   * @return current path
   */
  public static byte[] path(final Data data, final int pre) {
    if(data == null) return Token.EMPTY;

    int p = pre;
    int k = data.kind(p);
    final IntList il = new IntList();
    while(k != Data.DOC) {
      il.add(p);
      p = data.parent(p, k);
      k = data.kind(p);
    }

    final TokenBuilder tb = new TokenBuilder();
    tb.add("doc(\"");
    tb.add(content(data, p, true));
    tb.add("\")");
    for(int i = il.size() - 1; i >= 0; i--) {
      tb.add('/');
      tb.add(content(data, il.get(i), true));
    }
    return tb.finish();
  }

  /**
   * Returns the contents of the specified node.
   * @param data data reference
   * @param p pre value
   * @param s if specified, a short representation is returned
   * (no full-text nodes, only attribute names)
   * @return name
   */
  public static byte[] content(final Data data, final int p, final boolean s) {
    final int k = data.kind(p);
    switch(k) {
      case Data.ELEM: return data.name(p, k);
      case Data.DOC:  return data.text(p, true);
      case Data.TEXT: return s ? TEXT : data.text(p, true);
      case Data.COMM: return s ? COMM : data.text(p, true);
      case Data.PI:   return s ? PI : data.text(p, true);
    }

    final TokenBuilder tb = new TokenBuilder();
    tb.add(ATT);
    tb.add(data.name(p, k));
    if(!s) {
      tb.add(ATT1);
      tb.add(data.text(p, false));
      tb.add(ATT2);
    }
    return tb.finish();
  }

  /**
   * Returns the tag name of the specified node.
   * Note that the pre value must reference an element node.
   * @param prop gui properties
   * @param data data reference
   * @param pre pre value
   * @return name
   */
  public static byte[] tag(final GUIProp prop, final Data data,
      final int pre) {

    if(prop.is(GUIProp.SHOWNAME) && data.nameID != 0) {
      final byte[] att = data.attValue(data.nameID, pre);
      if(att != null) return att;
    }
    return content(data, pre, true);
  }

  /**
   * Returns the parent for the specified node.
   * @param data data reference
   * @param pre child node
   * @return parent node
   */
  public static int parent(final Data data, final int pre) {
    return data.parent(pre, data.kind(pre));
  }

  /**
   * Returns the size for the specified node.
   * @param data data reference
   * @param pre child node
   * @return parent node
   */
  public static int size(final Data data, final int pre) {
    return data.size(pre, data.kind(pre));
  }
}
