package org.basex.test.performance;

import java.util.Random;
import org.basex.BaseXServer;
import org.basex.core.Text;
import org.basex.server.ClientSession;
import org.junit.Test;

/**
 * This class performs a client/server memory stress tests with a specified
 * number of threads and queries.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public final class ServerMemTest {
  /** Query to be run ("%" may be used as placeholder for dynamic content). */
  private static final String QUERY =
    "(for $i in 1 to 50000 order by $i return $i)[1]";

  /** Server reference. */
  static BaseXServer server;
  /** Random number generator. */
  static final Random RND = new Random();

  /**
   * Runs the test.
   * @throws Exception exception
   */
  @Test
  public void clients20parallel20() throws Exception {
    run(20, 20);
  }

  /**
   * Runs the test.
   * @throws Exception exception
   */
  @Test
  public void clients20parallel200() throws Exception {
    run(20, 200);
  }

  /**
   * Runs the test.
   * @throws Exception exception
   */
  @Test
  public void clients200parallel20() throws Exception {
    run(200, 20);
  }

  /**
   * Runs the test.
   * @throws Exception exception
   */
  @Test
  public void clients200parallel200() throws Exception {
    run(200, 200);
  }

  /**
   * Runs the stress test.
   * @param clients number of clients
   * @param parallel number of parallel runs
   * @throws Exception exception
   */
  private void run(final int clients, final int parallel) throws Exception {
    // Run server instance
    server = new BaseXServer("-p9999", "-e9998", "-z",
        "-cset parallel " + parallel);

    // Run clients
    final Client[] cl = new Client[clients];
    for(int i = 0; i < clients; ++i) cl[i] = new Client();
    for(final Client c : cl) c.start();
    for(final Client c : cl) c.join();

    // Stop server
    server.stop();
  }

  /** Single client. */
  static final class Client extends Thread {
    /** Client session. */
    private final ClientSession session;

    /**
     * Default constructor.
     * @throws Exception exception
     */
    public Client() throws Exception {
      session = new ClientSession(Text.LOCALHOST, 9999, Text.ADMIN, Text.ADMIN);
    }

    @Override
    public void run() {
      try {
        // Perform query
        session.execute("xquery " + QUERY);
        session.close();
      } catch(final Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
