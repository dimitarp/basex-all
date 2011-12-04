package org.basex.util.ft;

import java.util.Locale;

/**
 * Full-text units.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public enum FTUnit {
  /** Word unit. */
  WORD,
  /** Sentence unit. */
  SENTENCE,
  /** Paragraph unit. */
  PARAGRAPH;

  /**
   * Returns a string representation.
   * @return string representation
   */
  @Override
  public String toString() {
    return name().toLowerCase(Locale.ENGLISH);
  }
}
