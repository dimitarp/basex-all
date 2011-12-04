package org.basex.data;

import java.io.IOException;

import org.basex.io.serial.Serializer;

/**
 * This is an interface for query results.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public interface Result {
  /**
   * Number of values, stored in the result instance.
   * @return number of values
   */
  long size();

  /**
   * Compares results for equality.
   * @param r result to be compared
   * @return true if results are equal
   */
  boolean sameAs(Result r);

  /**
   * Serializes the complete result.
   * @param ser serializer
   * @throws IOException I/O exception
   */
  void serialize(Serializer ser) throws IOException;

  /**
   * Serializes the specified result.
   * @param ser serializer
   * @param n results offset to serialize
   * @throws IOException I/O exception
   */
  void serialize(Serializer ser, int n) throws IOException;
}
