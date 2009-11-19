package org.basex.test.inex;

import static org.basex.core.Text.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import org.basex.core.Context;
import org.basex.core.LocalSession;
import org.basex.core.Main;
import org.basex.core.proc.List;
import org.basex.core.proc.XQuery;
import org.basex.io.PrintOutput;
import org.basex.util.Args;
import org.basex.util.Performance;
import org.basex.util.TokenBuilder;

/**
 * Simple INEX Database test.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-09, ISC License
 * @author Christian Gruen
 */
public final class InexSingle {
  /** Query file. */
  static final String QUERIES = "inex.queries";
  /** Database prefix. */
  static final String DBPREFIX = "inex";
  /** Maximum number of results. */
  static final int MAX = 25;
  /** Database context. */
  private final Context ctx = new Context();
  /** Session. */
  private LocalSession session = new LocalSession(ctx);
  /** Maximum number of queries. */
  private int quindex;

  /**
   * Main test method.
   * @param args command-line arguments
   * @throws Exception exception
   */
  public static void main(final String[] args) throws Exception {
    new InexSingle(args);
//    if (args.length != 1) System.out.println("submission file needed.");
//    else convert(args[0]);
  }

  /**
   * Converts ids of a submission file. 
   * @param subfile
   * @throws Exception
   */
  public static void convert(final String subfile) throws Exception {    
    String l;
    HashMap<String, String> hs = new HashMap<String, String>();
    
    final BufferedReader br = new BufferedReader(new FileReader(QUERIES));
    while((l = br.readLine()) != null) {
      final int i1 = l.indexOf(';');
      final int i2 = l.indexOf(';', i1 + 1);
      hs.put(l.substring(0, i1), l.substring(i1 + 1, i2));      
    }
    br.close();
    
    final BufferedReader in = new BufferedReader(new FileReader(subfile));
    final String subfileN = subfile.substring(0, subfile.indexOf('.')) + "1" + ".xml";
    System.out.println("........ Creating: " + subfileN);
    final BufferedWriter out = new BufferedWriter(new FileWriter(subfileN));
    while((l = in.readLine()) != null) {
      if (l.contains("<topic topic-id=")) {
        final int i1 = l.indexOf("\"");
        final int i2 = l.indexOf("\"", i1 + 1);
        final String key = l.substring(i1 + 1, i2);
        l = l.substring(0, i1 + 1) + hs.get(key) + l.substring(i2);
      }  
      out.write(l + NL); 
    }
    out.close();
    br.close();    
    System.out.println("done!");
  }
  
  /**
   * Default constructor.
   * @param args command-line arguments
   * @throws Exception exception
   */
  private InexSingle(final String[] args) throws Exception {
    final Performance p = new Performance();
    Main.outln(Main.name(InexSingle.class));

    // use tf/idf scoring model
    if(!parseArguments(args)) return;

    // cache queries
    final BufferedReader br = new BufferedReader(new FileReader(QUERIES));
    String l;
    int c = 0;
    String query = "";
    while((l = br.readLine()) != null) {
      if(++c == quindex) {
        query = l.substring(l.lastIndexOf(';') + 1).replaceAll("\"", "'");
      }
    }
    br.close();

    // compose query
    final TokenBuilder qu = new TokenBuilder();
    qu.add("let $hits := (for $i score $s in(\n");
    int d = 0;
    for(final String s : List.list(ctx)) {
      if(s.startsWith(DBPREFIX)) {
        if(d++ != 0) qu.add(",\n");
        qu.add("doc('" + s + "')" + query);
      }
    }
    qu.add(")\n" +
      "order by $s descending\n" +
      "return <result score='{ $s }' " +
      "file='{ replace(base-uri($i), '.*/', '') }'>{ $i }</result>)" +
      "\n" + 
      //      "[position() <= " + MAX + "]\n" +
      "return <results query=\"" + query + "\">{ $hits }</results>\n");

    String file = Integer.toString(quindex) + ".xml";
    final PrintOutput out = new PrintOutput(file);
    final boolean ok = session.execute(new XQuery(qu.toString()), out);
    out.close();
    if(ok) {
      while(file.length() < 7) file = "0" + file;
      // output result
      Main.outln("- " + query);
      Main.outln("Result saved to % in %", file, p);
    } else {
      Main.outln(session.info());
    }
  }

  /**
   * Parses the command-line arguments.
   * @param args the command-line arguments
   * @return true if all arguments have been correctly parsed
   */
  private boolean parseArguments(final String[] args) {
    final Args arg = new Args(args);
    final boolean ok = arg.more();

    if(ok) {
      quindex = arg.num();
    } else {
      Main.outln("Usage: " + Main.name(this) + " query" + NL +
        "  query  perform specified query (1-#queries)");
    }
    return ok;
  }
}
