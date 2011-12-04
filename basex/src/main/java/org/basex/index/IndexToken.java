package org.basex.index;

/**
 * This class defines access to index tokens.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public interface IndexToken {
  /** Index types. */
  public enum IndexType {
    /** Attribute names. */ ATTNAME,
    /** Tag index.       */ TAG,
    /** Text index.      */ TEXT,
    /** Attribute index. */ ATTRIBUTE,
    /** Full-text index. */ FULLTEXT,
    /** Path index. */      PATH
  }

  /**
   * Returns the index type.
   * @return type
   */
  IndexType type();

  /**
   * Returns the current token.
   * @return token
   */
  byte[] get();
}
