package org.basex.examples.query;

import org.basex.core.Context;
import org.basex.data.Result;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;

/**
 * This example demonstrates how items can be bound to variables with
 * the XQuery processor.
 *
 * @author BaseX Team 2005-11, BSD License
 */
public final class BindVariables {
  /**
   * Runs the example code.
   * @param args (ignored) command-line arguments
   * @throws QueryException if an error occurs while evaluating the query
   */
  public static void main(final String[] args)
      throws QueryException {

    /** Database context. */
    Context context = new Context();

    System.out.println("=== BindVariable ===");

    // ------------------------------------------------------------------------
    // Specify query to be executed
    String query =
      "declare variable $var1 as xs:string external; " +
      "declare variable $var2 external; " +
      "($var1, $var2)";

    // ------------------------------------------------------------------------
    // Create a query processor
    QueryProcessor proc = new QueryProcessor(query, context);

    // ------------------------------------------------------------------------
    // Define the items to be bound
    String string = "Hello World!\n";
    String number = "123";

    // ------------------------------------------------------------------------
    // Bind the variables
    proc.bind("var1", string);
    proc.bind("var2", number, "xs:integer");

    // ------------------------------------------------------------------------
    // Execute the query
    Result result = proc.execute();

    System.out.println("\n* Execute query:");

    // ------------------------------------------------------------------------
    // Print result as string
    System.out.println(result);

    // ------------------------------------------------------------------------
    // Close the query processor
    proc.close();

    // ------------------------------------------------------------------------
    // Close the database context
    context.close();
  }
}
