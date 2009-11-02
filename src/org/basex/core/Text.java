package org.basex.core;

import static org.basex.core.Commands.*;
import static org.basex.core.Lang.*;
import static org.basex.util.Token.*;

/**
 * This class contains internationalized text strings, which are used
 * throughout the project. If this class is called first, the Strings
 * are initialized by the {@link org.basex.core.Lang} class.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-09, ISC License
 * @author Christian Gruen
 * @author Andreas Weiler
 */
public interface Text {

  // FREQUENTLY USED CHARACTERS ===============================================

  /** New line. */
  String NL = org.basex.core.Prop.NL;
  /** Colon. */
  String COL = ":";
  /** Colon/space. */
  String COLS = ": ";
  /** Dot. */
  String DOT = ".";
  /** Dots. */
  String DOTS = "...";
  /** List. */
  String LI = "- ";
  /** On flag. */
  String TRUE = "TRUE";
  /** Off flag. */
  String FALSE = "FALSE";
  /** All flag. */
  String ALL = "ALL";

  /** Project name. */
  String NAME = "BaseX";
  /** Project namespace. */
  String NAMESPACE = NAME.toLowerCase();
  /** URL. */
  String URL = "http://www." + NAMESPACE + ".org";
  /** Mail. */
  String MAIL = "info@" + NAMESPACE + ".org";
  /** Code version. */
  String VERSION = "5.74";
  /** Company info. */
  String COMPANY = "DBIS, University of Konstanz";
  /** Version information. */
  String VERSINFO = lang("version", VERSION);

  /** Title and version. */
  String TITLE = NAME + " " + VERSION;
  /** Title, version and company. */
  String CONSOLEINFO = TITLE + "; " + COMPANY;

  // CONSOLE INFO =============================================================

  /** Console text. */
  String CONSOLE = TITLE + " [%]" + NL + "%";
  /** Console text. */
  String CONSOLE2 = lang("help_intro", "help") + NL;

  /** Goodbye information. */
  String[] CLIENTBYE = {
      lang("bye1"), lang("bye2"), lang("bye3"), lang("bye4")
  };

  /** Local (standalone) mode. */
  String LOCALMODE = lang("cs_local");
  /** Client mode. */
  String CLIENTMODE = lang("cs_client");
  /** Local (standalone) mode. */
  String SERVERMODE = "Server";

  /** Start information. */
  String CLIENTINFO = CONSOLEINFO + NL +
    "Usage: " + NAME + CLIENTMODE + " [-np] [-cdovVxz] [query]" + NL +
    "  [query]    specify query file" + NL +
    "  -c<cmd>    send database commands" + NL +
    "  -d         debug mode" + NL +
    "  -n<name>   specify server name" + NL +
    "  -o<file>   specify output file" + NL +
    "  -p<port>   specify server port" + NL +
    "  -v/V       show (all) process info" + NL +
    "  -x         print result as xml" + NL +
    "  -z         skip query output";

  /** Start information. */
  String LOCALINFO = CONSOLEINFO + NL +
    "Usage: " + NAME + " [-cdiovVxz] [query]" + NL +
    "  [query]    specify query file" + NL +
    "  -c<cmd>    send database commands" + NL +
    "  -d         debug mode" + NL +
    "  -i<file>   specify XML input" + NL +
    "  -o<file>   specify output file" + NL +
    "  -v/V       show (all) process info" + NL +
    "  -x         print result as xml" + NL +
    "  -z         skip query output";

  // SERVER ===================================================================

  /** Server start. */
  String SERVERSTART = lang("srv_start") + NL;
  /** Server stop. */
  String SERVERSTOPPED = SERVERMODE + ' ' + lang("srv_stop");
  /** Server started. */
  String SERVERBIND = lang("srv_bind");
  /** Error shown in the client console when timeout is reached. */
  String SERVERTIME = lang("srv_timeout");
  /** Connection error. */
  String SERVERERR = lang("srv_connect");
  /** Login error. */
  String SERVERLOGIN = lang("srv_login");
  /** User name. */
  String SERVERUSER = lang("srv_user");
  /** Password. */
  String SERVERPW = lang("srv_pw");
  
  /** Start information. */
  String SERVERINFO = CONSOLEINFO + NL +
    "Usage: java " + NAME + SERVERMODE + " [-dpv] [stop]" + NL +
    " stop     stop server" + NL +
    " -d       debug mode" + NL +
    " -p<port> specify server port" + NL +
    " -v       verbose mode";

  // COMMANDS =================================================================

  /** Command help. */
  String FRAGMENT = "fragment";
  /** Command help. */
  String SOURCE = "source";
  /** Command help. */
  String TARGET = "target";
  /** Command help. */
  String QUERY = "query";
  /** Command help. */
  String VAL = "value";
  /** Command help. */
  String FILE = "file";
  /** Command help. */
  String PATH = "path";
  /** Command help. */
  String TEXT = "text";
  /** Command help. */
  String NAM = "name";
  /** Command help. */
  String PW = "password";
  /** Command help. */
  String POS = "pos";
  /** Command help. */
  String INTO = "INTO";
  /** Command help. */
  String AT = "AT";
  /** Command help. */
  String FROM = "FROM";
  /** Command help. */
  String TO = "TO";
  /** Command help. */
  String ON = "ON";
  /** Command help. */
  String OFF = "OFF";
  /** Command help. */
  String USER = "USER";

  /** Missing help. */
  String NOHELP = lang("ch_nohelp");

  /** Database separator. */
  String[] HELPDB = { lang("ch_helpdatabase0") };
  /** Command help. */
  String[] HELPCREATE = {
    "[" + CmdCreate.DB + "|" + CmdCreate.FS + "|" +
    CmdCreate.INDEX + "|" + CmdCreate.USER + "] [...]",
    lang("ch_create1"),
    lang("ch_create2") + NL +
    LI + CmdCreate.DB + " [" + PATH + "] [" + NAM + "]?:" + NL +
      "  " + lang("ch_create3", NAM, PATH) + NL +
    LI + CmdCreate.INDEX + " [" + CmdIndex.TEXT + "|" + CmdIndex.ATTRIBUTE +
      "|" + CmdIndex.FULLTEXT + "|" + CmdIndex.SUMMARY + "]: " + NL +
      "  " + lang("ch_create4") + NL +
    LI + CmdCreate.FS + " [" + PATH + "] [" + NAM +
      "] ([mountpoint] [backingstore]): " + NL +
      "  " + lang("ch_create5", NAM, PATH) + NL +
      "  " + lang("ch_create6", "mountpoint", "backingstore") + NL +
    LI + CmdCreate.USER + " [" + NAM + "] [" + PW + "]?: " + NL +
      "  " + lang("ch_create7")
  };
  /** Command help. */
  String[] HELPOPEN = {
    "[" + NAM + "]", lang("ch_open1"), lang("ch_open2", NAM)
  };
  /** Command help. */
  String[] HELPINFO = {
    "[" + CmdInfo.DB + "|" + CmdInfo.INDEX + "|" + CmdInfo.TABLE + "]?",
    lang("ch_info1"),
    lang("ch_info21") + NL +
    LI + lang("ch_info22") + NL +
    LI + CmdInfo.DB + ": " + lang("ch_info23") + NL +
    LI + CmdInfo.INDEX + ": " + lang("ch_info24") + NL +
    LI + CmdInfo.TABLE + " [start end] | [" + QUERY + "]: " + lang("ch_info25")
  };
  /** Command help. */
  String[] HELPCLOSE = {
    "", lang("ch_close1"), lang("ch_close2")
  };
  /** Command help. */
  String[] HELPLIST = {
    "", lang("ch_list1"), lang("ch_list2")
  };
  /** Command help. */
  String[] HELPDROP = {
    "[" + CmdDrop.DB + "|" + CmdDrop.INDEX + "|" + CmdDrop.USER + "] [...]",
    lang("ch_drop1"),
    lang("ch_drop2") + NL +
    LI + CmdDrop.DB + " [" + NAM + "]:" + NL +
      "  " + lang("ch_drop21") + NL +
    LI + CmdDrop.INDEX + " [" + CmdIndex.SUMMARY + "|" + CmdIndex.TEXT + "|" +
      CmdIndex.ATTRIBUTE + "|" + CmdIndex.FULLTEXT + "]:" + NL +
      "  " + lang("ch_drop22") + NL +
    LI + CmdDrop.USER + " [" + NAM + "]:" + NL + "  " + lang("ch_drop3", NAM)
  };
  /** Command help. */
  String[] HELPEXPORT = {
    "[" + PATH + "]", lang("ch_export1"), lang("ch_export2", PATH)
  };
  /** Command help. */
  String[] HELPOPTIMIZE = {
    "", lang("ch_optimize1"), lang("ch_optimize2")
  };

  /** Command help. */
  String[] HELPQ = { lang("ch_helpquery0") };
  /** Command help. */
  String[] HELPXQUERY = {
    "[" + QUERY + "]", lang("ch_xquery1"), lang("ch_xquery2")
  };
  /** Command help. */
  String[] HELPFIND = {
    "[" + QUERY + "]", lang("ch_find1"), lang("ch_find2")
  };
  /** Command help. */
  String[] HELPRUN = {
    "[" + PATH + "]", lang("ch_run1"), lang("ch_run2", PATH)
  };
  /** Command help. */
  String[] HELPCS = {
    "[" + QUERY + "]", lang("ch_cs1"), lang("ch_cs2")
  };

  /** Command help. */
  String[] HELPA = { lang("ch_helpadmin0") };
  /** Command help. */
  String[] HELPKILL = {
    "", lang("ch_kill1"), lang("ch_kill2")
  };
  /** Command help. */
  String[] HELPSHOW = {
    "[" + CmdShow.DATABASES + "|" + CmdShow.SESSIONS + "|" +
    CmdShow.USERS + "]",
    lang("ch_show1"),
    lang("ch_show21") + NL +
    LI + CmdShow.DATABASES + ": " + lang("ch_show22") + NL +
    LI + CmdShow.SESSIONS + ": " + lang("ch_show23") + NL +
    LI + CmdShow.USERS + ": " + lang("ch_show24")
  };
  /** Command help. */
  String[] HELPGRANT = {
    "[" + CmdPerm.READ + "|" + CmdPerm.WRITE + "|" + CmdPerm.CREATE + "|" +
    CmdPerm.ADMIN + "|" + CmdPerm.ALL + "] (" + ON + " [db]) " + TO + " [user]",
    lang("ch_grant1"),
    lang("ch_grant2")
  };
  /** Command help. */
  String[] HELPREVOKE = {
    "[" + CmdPerm.READ + "|" + CmdPerm.WRITE + "|" + CmdPerm.CREATE + "|" +
    CmdPerm.ADMIN + "|" + CmdPerm.ALL + "] (" + ON + " [db]) " +
    FROM + " [user]",
    lang("ch_revoke1"),
    lang("ch_revoke2")
  };
  /** Command help. */
  String[] HELPALTER = {
    USER + " [" + NAME + "] [" + PW + "]?", lang("ch_alter1"), lang("ch_alter2")
  };

  /** Command help. */
  String[] HELPG = { lang("ch_helpgeneral0") };
  /** Command help. */
  String[] HELPSET = {
    "[option] [value]?",
    lang("ch_set1", "info"),
    lang("ch_set2", "option", "value") + NL +
    LI + CmdSet.INFO + " [all]?" + COLS + lang("ch_set21") + NL +
    LI + CmdSet.DEBUG     + COLS + lang("ch_set22") + NL +
    LI + CmdSet.SERIALIZE + COLS + lang("ch_set23") + NL +
    LI + CmdSet.XMLOUTPUT + COLS + lang("ch_set24") + NL +
    LI + CmdSet.MAINMEM   + COLS + lang("ch_set25") + NL +
    LI + CmdSet.CHOP      + COLS + lang("ch_set26") + NL +
    LI + CmdSet.ENTITY    + COLS + lang("ch_set27") + NL +
    LI + CmdSet.TEXTINDEX + COLS + lang("ch_set28") + NL +
    LI + CmdSet.ATTRINDEX + COLS + lang("ch_set29") + NL +
    LI + CmdSet.FTINDEX   + COLS + lang("ch_set31")
  };
  /** Command help. */
  String[] HELPPASSWORD = {
    "[" + PW + "]?", lang("ch_password1"), lang("ch_password2")
  };
  /** Command help. */
  String[] HELPHELP = {
    "[command]?", lang("ch_help1", NAME), lang("ch_help2", "command")
  };
  /** Command help. */
  String[] HELPEXIT = {
    "", lang("ch_exit1", NAME), lang("ch_exit2", NAME)
  };

  // STARTER WINDOW ===========================================================

  /** Waiting info. */
  String WAIT1 = lang("launch") + " " + TITLE;
  /** Waiting info. */
  String WAIT2 = lang("wait") + DOTS;

  // PROCESS INFOS ============================================================

  /** Process time. */
  String PROCTIME = lang("proc_time") + ": %";
  /** No document warning. */
  String PROCSYNTAX = lang("proc_syntax") + ": %";
  /** Command execution error. */
  String PROCERR = lang("proc_err") + COL + NL + "%";
  /** No database error. */
  String PROCNODB = lang("proc_nodb");
  /** Main memory error. */
  String PROCMM = lang("proc_mm");
  /** Out of memory error. */
  String PROCOUTMEM = lang("proc_outmem");
  /** Progress exception. */
  String PROGERR = "Interrupted";

  /** Unknown command error. */
  String CMDNO = lang("cmd_no");
  /** Unknown command error. */
  String CMDUNKNOWN = lang("cmd_unknown");
  /** Unknown command error. */
  String CMDWHICH = CMDUNKNOWN + "; " + lang("help_short", "help") + DOT;
  /** Unknown command error. */
  String CMDSIMILAR = CMDUNKNOWN + "; " + lang("cmd_similar");
  /** Database closed. */
  String CMDHELP = lang("help_long", "help");

  // CREATE COMMAND ===========================================================

  /** Create database information. */
  String PROGCREATE = lang("pc_create");
  /** Create database information. */
  String PROGINDEX = lang("pc_index");
  /** Database update. */
  String DBUPDATE = lang("pc_update");
  /** Index update. */
  String INDUPDATE = lang("pc_indupdate");
  /** Builder error. */
  String CANCELCREATE = lang("pc_cancel");
  /** Builder error. */
  String LIMITRANGE = lang("pc_range");
  /** Builder error. */
  String LIMITTAGS = lang("pc_tags");
  /** Builder error. */
  String LIMITATTS = lang("pc_atts");
  /** Create database information. */
  String NODESPARSED = " " + lang("pc_parse");
  /** Scanner position. */
  String SCANPOS = lang("pc_pos");

  /** Create database index. */
  String INDEXTXT = lang("pc_indextxt") + DOTS;
  /** Create database index. */
  String INDEXATT = lang("pc_indexatt") + DOTS;
  /** Create database index. */
  String INDEXFTX = lang("pc_indexftx") + DOTS;

  /** Database created. */
  String DBCREATED = lang("pc_created");
  /** Parse error. */
  String CREATEERR = lang("pc_err");

  /** File not found. */
  String FILEWHICH = lang("pc_filenf");
  /** Path not found. */
  String PATHWHICH = lang("pc_pathnf");
  /** Missing database name. */
  String DBWHICH = lang("pc_dbnf");

  // DATABASE COMMANDS ========================================================

  /** Database not found. */
  String DBNOTFOUND = lang("db_no");
  /** Database closed. */
  String DBCLOSED = lang("db_closed");
  /** Database not closed. */
  String DBCLOSEERR = lang("db_closeerr");
  /** Database dropped. */
  String DBDROPPED = lang("db_dropped");
  /** Database not dropped. */
  String DBNOTDROPPED = lang("db_notdropped");
  /** Database is in use by a other client. */
  String DBINUSE = lang("db_inuse");
  /** Database not opened. */
  String DBOPENERR = lang("db_notopened");
  /** Database opened. */
  String DBOPENED = lang("db_opened");
  /** Database exported. */
  String DBEXPORTED = lang("db_exported");

  /** Database optimized. */
  String DBOPTIMIZED = lang("db_optimized");
  /** Index created. */
  String DBINDEXED = lang("in_created");
  /** Index dropped. */
  String DBDROP = lang("in_dropped");
  /** Index not dropped. */
  String DBDROPERR = lang("in_notdropped");

  // DATABASE/INDEX INFORMATION ===============================================

  /** Index info. */
  String TRIE = LI + "Compressed Trie";
  /** Index info. */
  String NAMINDEX = LI + "Hash Index";
  /** Index info. */
  String FUZZY = LI + "Fuzzy Index";
  /** Index info. */
  String TXTINDEX = LI + "Tree Index";
  /** Index info. */
  String SIZEDISK = LI + "Size on Disk: ";
  /** Index info. */
  String IDXENTRIES = LI + "Entries: ";

  // XQUERY COMMAND ===========================================================

  /** Query info: query. */
  String QUERYQU = lang("qu_query") + COL + " ";
  /** Query info: optimizing. */
  String QUERYCOMP = lang("qu_comp") + COL;
  /** Query info: evaluating. */
  String QUERYEVAL = lang("qu_eval") + COL;
  /** Query info: querying. */
  String QUERYTIME = lang("qu_time") + COL;
  /** Query info: result . */
  String QUERYRESULT = lang("qu_result") + COLS;
  /** Query info: plan. */
  String QUERYPLAN = lang("qu_plan") + COLS;
  /** Query info: compiler. */
  String QUERYSEP = LI;

  /** Query info: query. */
  String QUERYSTRING = lang("qu_tabquery") + COLS;
  /** Query info: compiling. */
  String QUERYPARSE = lang("qu_tabpars") + COLS;
  /** Query info: compiling. */
  String QUERYCOMPILE = lang("qu_tabcomp") + COLS;
  /** Query info: evaluating. */
  String QUERYEVALUATE = lang("qu_tabeval") + COLS;
  /** Query info: finishing. */
  String QUERYFINISH = lang("qu_tabfinish") + COLS;
  /** Query info: time for printing. */
  String QUERYPRINT = lang("qu_tabprint") + COLS;
  /** Query info: total time. */
  String QUERYTOTAL = lang("qu_tabtotal") + COLS;
  /** Query hits. */
  String QUERYHITS = lang("qu_tabhits") + COLS;
  /** Query info: printed data. */
  String QUERYPRINTED = lang("qu_tabprinted") + COLS;
  /** Query info: memory. */
  String QUERYMEM = lang("qu_tabmem") + ": %";
  /** Query hits. */
  String HITS = lang("qu_hits");
  /** Insert query info. */
  String QUERYNODESERR = lang("qu_nodeserr");

  /** Position info. */
  String STOPPED = lang("qu_stopped");
  /** Position info. */
  String LINEINFO = lang("qu_line");
  /** Position info. */
  String COLINFO = lang("qu_col");
  /** Position info. */
  String FILEINFO = lang("qu_file");

  /** Query hits. */
  String VALHIT = "Item";
  /** Query hits. */
  String VALHITS = "Items";

  // USER COMMANDS ============================================================

  /** User name. */
  String[] USERHEAD = { "Username",  "Read", "Write", "Create", "Admin" };
  /** Admin user. */
  String ADMIN = "admin";
  
  // ADMIN COMMANDS ==========================================================

  /** Show databases. */
  String SRVDATABASES = lang("ad_databases");
  /** Show sessions. */
  String SRVSESSIONS = lang("ad_sessions");
  /** Show sessions. */
  String PERMNO = lang("ad_permno");
  /** Invalid permissions. */
  String PERMINV = lang("ad_perminv");
  /** Permission granted. */
  String PERMDEL = lang("ad_permdel");
  /** Permission revoked. */
  String PERMADD = lang("ad_permadd");
  /** User not found. */
  String USERNO = lang("ad_userno");
  /** User dropped. */
  String USERDROP = lang("ad_userdrop");
  /** User added. */
  String USERCREATE = lang("ad_usercreate");
  /** User changed. */
  String USERALTER = lang("ad_useralter");
  /** User added. */
  String USERKNOWN = lang("ad_userknown");
  /** User not found. */
  String PASSNO = lang("ad_passno");

  // GENERAL COMMANDS =========================================================

  /** Insert query info. */
  String SETERR = lang("gc_seterr");

  // INFO STRINGS =============================================================

  /** Process information. */
  String INFOWAIT = lang("wait") + DOTS;
  /** Index information. */
  String INFOINDEX = lang("info_index");
  /** Index information. */
  String INFOBUILD = lang("info_build") + DOTS;
  /** Optimize information. */
  String INFOOPT = lang("info_opt") + DOTS;
  /** Optimize information. */
  String INFOOPTIM = lang("info_optim");
  /** Statistics information. */
  String INFOSTATS = lang("info_stats") + DOTS;

  /** Info on source document. */
  String INFODBNAME = lang("info_dbname");
  /** Info on database size. */
  String INFODBSIZE = lang("info_dbsize");
  /** Info on source document. */
  String INFODOC = lang("info_doc");
  /** Info on database time stamp. */
  String INFOTIME = lang("info_time");
  /** Info on number of documents. */
  String INFONDOCS = lang("info_ndocs");
  /** Info on document size. */
  String INFODOCSIZE = lang("info_docsize");
  /** Document encoding. */
  String INFOENCODING = lang("info_encoding");
  /** Info on database table size. */
  String INFONODES = lang("info_nodes");
  /** Maximum tree height. */
  String INFOHEIGHT = lang("info_height");

  /** Info on used main memory. */
  String INFOMEM = lang("info_mem");

  /** Info on database path. */
  String INFODBPATH = lang("info_dbpath");
  /** No document opened. */
  String INFONODB = lang("info_nodb") + DOT;
  /** Info on database. */
  String INFODBERR = lang("info_dberror");

  /** Info on query verbosity. */
  String INFOINFO = lang("info_info");
  /** Info on query verbosity. */
  String INFOALL = lang("info_all");
  /** Info on debug mode. */
  String INFODEBUG = lang("info_debug");
  /** Info on whitespace chopping. */
  String INFOCHOP = lang("info_chop");
  /** Info on entity parsing. */
  String INFOENTITY = lang("info_entities");
  /** Info on result serialization. */
  String INFOSERIALIZE = lang("info_serialize");
  /** Info on tags. */
  String INFOTAGS = lang("info_tags");
  /** Info on attributes. */
  String INFOATTS = lang("info_atts");
  /** Info on namespaces. */
  String INFONS = lang("info_ns");
  /** Info on path summary. */
  String INFOPATHINDEX = lang("info_pathindex");
  /** Info on text indexing. */
  String INFOTEXTINDEX = lang("info_txtindex");
  /** Info on attribute indexing. */
  String INFOATTRINDEX = lang("info_atvindex");
  /** Info on full-text indexing. */
  String INFOFTINDEX = lang("info_ftindex");
  /** Info on fuzzy indexing. */
  String INFOFZINDEX = lang("info_fzindex");
  /** Info on index. */
  String INFOOUTOFDATED = lang("info_outofdated");

  /** Info on database. */
  String INFODB = lang("info_db");
  /** Info on document creation. */
  String INFOCREATE = lang("info_create");
  /** Database info. */
  String INFOGENERAL = lang("info_general");
  /** Database info. */
  String RESULTCHOP = " (" + lang("info_resultchop") + ")";
  /** Option flag. */
  String INFOON = lang("info_on");
  /** Option flag. */
  String INFOOFF = lang("info_off");
  /** Error info. */
  String INFOERROR = lang("info_error") + COLS;
  /** Error info. */
  String INFOENTRIES = "(" + lang("info_entries") + ")";

  // MENU ENTRIES =============================================================

  /** Menu entry. */
  String MENUFILE = lang("m_file");
  /** Menu entry. */
  String MENUEDIT = lang("m_edit");
  /** Menu entry. */
  String MENUVIEW = lang("m_view");
  /** Menu entry. */
  String MENUOPTIONS = lang("m_options");
  /** Menu entry. */
  String MENUDEEPFS = "DeepFS";
  /** Menu entry. */
  String MENUHELP = lang("m_help");

  /** Menu label. */
  String MENUDB = lang("m_db") + COL;
  /** Menu label. */
  String MENUMAIN = lang("m_main") + COL;
  /** Menu label. */
  String MENUVIEWS = lang("m_views") + COL;
  /** Menu label. */
  String MENUINTER = lang("m_inter") + COL;
  /** Menu label. */
  String MENULAYOUT = lang("m_layout") + COL;

  // GUI COMMANDS =============================================================

  /** Command info. */
  String GUIABOUT = lang("c_about", NAME) + DOTS;
  /** Command info. */
  String GUIABOUTTT = lang("c_abouttt");
  /** Command info. */
  String GUICLOSE = lang("c_close");
  /** Command info. */
  String GUICLOSETT = lang("c_closett");
  /** Command info. */
  String GUICOLOR = lang("c_color") + DOTS;
  /** Command info. */
  String GUICOLORTT = lang("c_colortt");
  /** Command info. */
  String GUICUT = lang("c_cut");
  /** Command info. */
  String GUICOPY = lang("c_copy");
  /** Command info. */
  String GUIALL = lang("c_all");
  /** Command info. */
  String GUICPPATH = lang("c_cppath");
  /** Command info. */
  String GUICOPYTT = lang("c_copytt");
  /** Command info. */
  String GUICPPATHTT = lang("c_cppathtt");
  /** Command info. */
  String GUICREATE = lang("c_create") + DOTS;
  /** Command info. */
  String GUICREATETT = lang("c_creatett");
  /** Command info. */
  String GUIDELETE = lang("c_delete") + DOTS;
  /** Command info. */
  String GUIDEL = lang("c_delete");
  /** Command info. */
  String GUIDELETETT = lang("c_deletett");
  /** Command info. */
  String GUIDROP = lang("c_drop") + DOTS;
  /** Command info. */
  String GUIDROPTT = lang("c_droptt");
  /** Command info. */
  String GUIEDIT = lang("c_edit") + DOTS;
  /** Command info. */
  String GUIEDITTT = lang("c_edittt");
  /** Command info. */
  String GUIEXIT = lang("c_exit");
  /** Command info. */
  String GUIEXITTT = lang("c_exittt");
  /** Command info. */
  String GUIEXPORT = lang("c_export") + DOTS;
  /** Command info. */
  String GUIEXPORTTT = lang("c_exporttt");
  /** Command info. */
  String GUIFILTER = lang("c_filter");
  /** Command info. */
  String GUIFILTERTT = lang("c_filtertt");
  /** Command info. */
  String GUIFONTS = lang("c_fonts");
  /** Command info. */
  String GUIFONTSTT = lang("c_fontstt");
  /** Command info. */
  String GUIFULL = lang("c_full");
  /** Command info. */
  String GUIFULLTT = lang("c_fulltt");
  /** Command info. */
  String GUIGOBACK = lang("c_goback");
  /** Command info. */
  String GUIGOBACKTT = lang("c_gobacktt");
  /** Command info. */
  String GUIGOFORWARD = lang("c_goforward");
  /** Command info. */
  String GUIGOFORWARDTT = lang("c_goforwardtt");
  /** Command info. */
  String GUIGOUP = lang("c_goup");
  /** Command info. */
  String GUIGOUPTT = lang("c_gouptt");
  /** Command info. */
  String GUICREATEFS = lang("c_createfs") + DOTS;
  /** Command info. */
  String GUICREATEFSTT = lang("c_createfstt");
  /** Command info. */
  String GUIDQE = lang("c_dqe") + DOTS;
  /** Command info. */
  String GUIDQETT = lang("c_dqett") + DOT;
  /** Command info. */
  String GUIMOUNTFS = lang("c_mountfs") + DOTS;
  /** Command info. */
  String GUIMOUNTFSTT = lang("c_mountfstt") + DOT;
  /** Command info. */
  String GUIINFO = lang("c_props") + DOTS;
  /** Command info. */
  String GUIINFOTT = lang("c_propstt");
  /** Command info. */
  String GUIINSERT = lang("c_insert") + DOTS;
  /** Command info. */
  String GUIINSERTTT = lang("c_inserttt");
  /** Command info. */
  String GUIMAPLAYOUT = lang("c_maplayout");
  /** Command info. */
  String GUIMAPLAYOUTTT = lang("c_maplayouttt");
  /** Command info. */
  String GUIOPEN = lang("c_open") + DOTS;
  /** Command info. */
  String GUIOPENTT = lang("c_opentt");
  /** Command info. */
  String GUIPASTE = lang("c_paste");
  /** Command info. */
  String GUIPASTETT = lang("c_pastett");
  /** Command info. */
  String GUIPREFS = lang("c_prefs") + DOTS;
  /** Command info. */
  String GUIPREFSTT = lang("c_prefstt");
  /** Command info. */
  String GUIREDO = lang("c_redo");
  /** Command info. */
  String GUIROOT = lang("c_root");
  /** Command info. */
  String GUIROOTTT = lang("c_roottt");
  /** Command info. */
  String GUIRTEXEC = lang("c_rtexec");
  /** Command info. */
  String GUIRTEXECTT = lang("c_rtexectt");
  /** Command info. */
  String GUIRTFILTER = lang("c_rtfilter");
  /** Command info. */
  String GUIRTFILTERTT = lang("c_rtfiltertt");
  /** Command info. */
  String GUISHOWBUTTONS = lang("c_showbuttons");
  /** Command info. */
  String GUISHOWBUTTONSTT = lang("c_showbuttonstt");
  /** Command info. */
  String GUISHOWEXPLORE = lang("c_showexplore");
  /** Command info. */
  String GUISHOWEXPLORETT = lang("c_showexplorett");
  /** Command info. */
  String GUISHOWFOLDER = lang("c_showfolder");
  /** Command info. */
  String GUISHOWFOLDERTT = lang("c_showfoldertt");
  /** Command info. */
  String GUISHOWHELP = lang("c_showhelp");
  /** Command info. */
  String GUISHOWHELPTT = lang("c_showhelptt");
  /** Command info. */
  String GUISHOWINFO = lang("c_showinfo");
  /** Command info. */
  String GUISHOWINFOTT = lang("c_showinfott");
  /** Command info. */
  String GUISHOWINPUT = lang("c_showinput");
  /** Command info. */
  String GUISHOWINPUTTT = lang("c_showinputtt");
  /** Command info. */
  String GUISHOWMAP = lang("c_showmap");
  /** Command info. */
  String GUISHOWMAPTT = lang("c_showmaptt");
  /** Command info. */
  String GUISHOWMENU = lang("c_showmenu");
  /** Command info. */
  String GUISHOWMENUTT = lang("c_showmenutt");
  /** Command info. */
  String GUISHOWSTATUS = lang("c_showstatus");
  /** Command info. */
  String GUISHOWSTATUSTT = lang("c_showstatustt");
  /** Command info. */
  String GUISHOWTABLE = lang("c_showtable");
  /** Command info. */
  String GUISHOWPLOT = lang("c_showplot");
  /** Command info. */
  String GUISHOWTABLETT = lang("c_showtablett");
  /** Command info. */
  String GUISHOWPLOTTT = lang("c_showplottt");
  /** Command info. */
  String GUISHOWTEXT = lang("c_showtext");
  /** Command info. */
  String GUISHOWTEXTTT = lang("c_showtexttt");
  /** Command info. */
  String GUISHOWXQUERY = lang("c_showxquery");
  /** Command info. */
  String GUISHOWXQUERYTT = lang("c_showxquerytt");
  /** Command info. */
  String GUIUNDO = lang("c_undo");
  /** Command info. */
  String GUIXQOPEN = lang("c_xqopen") + DOTS;
  /** Command info. */
  String GUIXQOPENTT = lang("c_xqopentt");
  /** Command info. */
  String GUIXQSAVE = lang("c_xqsave") + DOTS;
  /** Command info. */
  String GUIXQSAVETT = lang("c_xqsavett");
  

  // BUTTONS ==================================================================

  /** Search mode. */
  String BUTTONSEARCH = lang("b_search");
  /** Command mode. */
  String BUTTONCMD = lang("b_cmd");
  /** XQuery mode. */
  String BUTTONXQUERY = lang("b_xquery");
  /** Button text for confirming actions. */
  String BUTTONOK = lang("b_ok");
  /** Button text for confirming actions. */
  String BUTTONOPT = lang("b_opt");
  /** Button text for opening files. */
  String BUTTONRENAME = lang("b_rename");
  /** Button text for opening files. */
  String BUTTONOPEN = lang("b_open");
  /** Button text for mounting database. */
  String BUTTONMOUNT = lang("b_mount");
  /** Button text for canceling actions. */
  String BUTTONCANCEL = lang("b_cancel");
  /** Button text for deleting files. */
  String BUTTONDROP = lang("b_drop") + DOTS;
  /** Button text for browsing files/directories. */
  String BUTTONBROWSE = lang("b_browse") + DOTS;
  /** Button text for changing things. */
  String BUTTONCHANGE = lang("b_change");
  /** Button text for creating things. */
  String BUTTONCREATE = lang("b_create");
  /** Button text for alter password. */
  String BUTTONALTER = lang("b_alter");
  /** Button for starting the server. */
  String BUTTONSTASERV = lang("b_staserv");
  /** Button for starting the server. */
  String BUTTONSTOSERV = lang("b_stoserv");
  /** Button for connecting. */
  String BUTTONCONNECT = lang("b_connect");
  /** Button for disconnecting. */
  String BUTTONDISCONNECT = lang("b_disconnect");
  /** Button for refreshing. */
  String BUTTONREFRESH = lang("b_refresh");

  // STATUS BAR ===============================================================

  /** Default message. */
  String STATUSOK = lang("s_ok") + ". ";

  // VISUALIZATIONS ===========================================================

  /** Help string. */
  String NOTABLE = lang("no_table") + DOT;
  /** Help string. */
  String NOSPACE = lang("no_space");
  /** Binary file. */
  byte[] MAPBINARY = token(lang("map_binary"));
  /** Query info title. */
  String INFOTIT = lang("info_title");
  /** No query info. */
  String INFONO = lang("info_no");
  /** Query title. */
  String EXPLORETIT = lang("explore_title");
  /** Help title. */
  String HELPTIT = lang("help_title");
  /** Text title. */
  String TEXTTIT = lang("text_title");
  /** Query title. */
  String XQUERYTIT = "XQuery";

  /** Plot visualization. */
  String PLOTLOG = "log";

  // DIALOG WINDOWS ===========================================================

  /** Open dialog - No database. */
  String DIALOGINFO = lang("d_info");
  /** Dialog title for choosing a directory. */
  String DIALOGFC = lang("d_fctitle");

  /** Dialog title for choosing a file. */
  String CREATETITLE = lang("dc_title");
  /** Database creation filter. */
  String CREATEFILT = lang("dc_filter");
  /** Dialog title for creating a database. */
  String CREATENAME = lang("dc_name") + COLS;
  /** XML file description. */
  String CREATEXMLDESC = lang("dc_xmldesc") + " (*.xml)";
  /** ZIP file description. */
  String CREATEZIPDESC = lang("dc_zipdesc") + " (*.zip)";
  /** GZ file description. */
  String CREATEGZDESC = lang("dc_gzdesc") + " (*.gz)";
  /** XQ file description. */
  String CREATEXQDESC = lang("dc_xqdesc") + " (*.xq)";
  /** Dialog title for database options. */
  String CREATEADVTITLE = lang("dc_advtitle");
  /** Whitespaces information. */
  String CREATECHOP = lang("dc_chop");
  /** Entities information. */
  String CREATEENTITIES = lang("dc_entities");
  /** Entities information. */
  String CREATEDTD = lang("dc_dtd");
  /** SAX parsing information. */
  String CREATEINTPARSE = lang("dc_intparse");

  /** Full-text index information. */
  String CREATEFZ = lang("dc_fzindex");
  /** Full-text index information. */
  String CREATESTEM = lang("dc_ftstem");
  /** Full-text index information. */
  String CREATECS = lang("dc_ftcs");
  /** Full-text index information. */
  String CREATEDC = lang("dc_ftdc");

  /** Whitespaces information. */
  String CHOPPINGINFO = lang("dc_chopinfo");
  /** Whitespaces information. */
  String INTPARSEINFO = lang("dc_intparseinfo");

  /** Path summary information. */
  String PATHINDEXINFO = lang("dc_pathinfo");
  /** Text index information. */
  String TXTINDEXINFO = lang("dc_txtinfo");
  /** Attribute value index information. */
  String ATTINDEXINFO = lang("dc_attinfo");
  /** Full-text index information. */
  String FTINDEXINFO = lang("dc_ftxinfo");
  /** Full-text index information. */
  String FZINDEXINFO = lang("dc_fzinfo");
  /** Full-text index information. */
  String FTSTEMINFO = lang("dc_ftsteminfo");
  /** Full-text index information. */
  String FTCSINFO = lang("dc_ftcsinfo");
  /** Full-text index information. */
  String FTDCINFO = lang("dc_ftdcinfo");

  /** General info. */
  String GENERALINFO = lang("dc_general");
  /** General info. */
  String PARSEINFO = lang("dc_parse");
  /** Indexing info. */
  String NAMESINFO = lang("dc_names");
  /** Indexing info. */
  String INDEXINFO = lang("dc_index");
  /** General info. */
  String METAINFO = lang("dc_meta");
  /** Indexing info. */
  String FTINFO = lang("dc_ft");

  /** Dialog title for opening a database. */
  String OPENTITLE = lang("do_title");
  /** Dialog asking if a new database should be be created. */
  String NODBQUESTION = INFONODB + NL + lang("do_nodbquestion") + NL + " ";
  /** Dialog asking if a new database should be be created. */
  String NODEEPFSQUESTION = lang("info_nodeepfs") + DOT + NL +
    lang("do_nodbquestion") + NL + " ";
  
  /** File dialog title. */
  String XQOPENTITLE = lang("dq_open");
  /** File dialog title. */
  String XQSAVETITLE = lang("dq_save");
  /** File dialog error. */
  String NOTOPENED = lang("dq_notopened");
  /** File dialog error. */
  String NOTSAVED = lang("dq_notsaved");
  /** File dialog replace information. */
  String FILEREPLACE = lang("dq_replace");

  /** Dialog title for exporting XML. */
  String EXPORTTITLE = lang("d_export");
  
  /** Server title. */
  String SRVTITLE = lang("ds_servdia");
  /** Server. */
  String SERVERN = lang("ds_server");
  /** Users. */
  String USERS = lang("ds_users");
  /** Host. */
  String HOST = lang("ds_host");
  /** PORT. */
  String PORT = lang("ds_port");
  /** Create User. */
  String CREATEU = lang("ds_createu");
  /** Drop User. */
  String DROPU = lang("ds_dropu");
  /** Permissions. */
  String PERMS = lang("ds_perms") + COLS;
  /** Question for dropping user. */
  String DRQUESTION = lang("ds_drquestion");
  /** Alter password. */
  String ALTERPW = lang("ds_alterpw");
  /** New password. */
  String NEWPW = lang("ds_newpw") + COLS;
  /** Invalid. */
  String INVALID = lang("ds_invalid");
  /** Login. */
  String LOGIN = lang("ds_login");
  /** Local. */
  String LOCAL = lang("ds_local");
  /** Databases. */
  String DATABASES = lang("ds_databases");
  /** Sessions. */
  String SESSIONS = lang("ds_sessions");
  
  /** Progress text for filesystem import. */
  String CREATEFSPROG = "Traversing filesystem...";
  /** Dialog title for import options. */
  String CREATEFSTITLE = lang("dfs_newtitle");
  /** Dialog title for creating a filesystem database. */
  String CREATEFSNAME = lang("dfs_name") + COLS;
  /** Import options. */
  String IMPORTALL = lang("dfs_all");
  /** Import options. */
  String IMPORTALLINFO = lang("dfs_allinfo") + DOT;
  /** Write from db to fs option. */
  String WTHROUGH = lang("dfs_wrt");
  /** Dialog question to activate write through. */
  String WTHROUGHOK = lang("dfs_wrtok");
  /** Import options. */
  String IMPORTFSTEXT = lang("dfs_text") + COL;
  /** Import options. */
  String IMPORTFSTEXT1 = lang("dfs_text1") + COL;
  /** Import options. */
  String IMPORTFSTEXT2 = lang("dfs_text2") + COL;
  /** Import options. */
  String IMPORTCONT = lang("dfs_cont");
  /** Import options. */
  String IMPORTMETA = lang("dfs_meta") + " (MP3, JPG, TIF, PNG, GIF, ...)";
  /** Import options. */
  String[] IMPORTFSMAX = {
      "Max. 1KB", "Max. 10KB", "Max. 100KB", "Max. 1MB", "Max. 10MB"
  };
  /** Import options. */
  int[] IMPORTFSMAXSIZE = { 1024, 10240, 102400, 1048576, 10485760 };

  /** Dialog title for opening a database as desktop query engine. */
  String OPENDQETITLE = lang("dqe_title") + DOTS;
  /** Dialog title for mounting a DeepFS database. */
  String OPENMOUNTTITLE = lang("dmnt_title") + DOTS;
  /** No valid path to mount point. */
  String NOVALIDMOUNT = lang("dmnt_nomountpath") + DOT;
  
  /** Dialog title for renaming a database. */
  String RENAMETITLE = lang("dr_title");
  /** Info for renaming a database. */
  String RENAMEINVALID = lang("dr_invalid");
  /** Info for renaming a database. */
  String RENAMEEXISTS = lang("dr_exists");
  /** Info for overwriting  a database. */
  String RENAMEOVER = lang("dr_over");
  /** Info for overwriting a database and deleting backing store. */
  String RENAMEOVERBACKING = lang("dr_overbacking");

  /** Dialog title for dropping a database. */
  String DROPTITLE = lang("dd_title");
  /** Dialog title for dropping a database. */
  String DROPCONF = lang("dd_question") + NL + " ";

  /** Dialog title for import options. */
  String PREFSTITLE = lang("dp_title");
  /** Database path. */
  String DATABASEPATH = lang("dp_dbpath");
  /** Interactions. */
  String PREFINTER = lang("dp_inter");
  /** Look and feel. */
  String PREFLF = lang("dp_lf") + " (" + lang("dp_restart") + ")";
  /** Focus. */
  String PREFFOCUS = lang("dp_focus");
  /** Simple file dialog. */
  String SIMPLEFILE = lang("dp_simplefd");
  /** Name display flag. */
  String PREFNAME = lang("dp_names");
  /** Language preference. */
  String PREFLANG = lang("dp_lang") + " (" + lang("dp_restart") + ")";

  /** Dialog title for deleting nodes. */
  String DELETECONF = lang("dx_question");
  /** Dialog title for closing XQuery file. */
  String XQUERYCONF = lang("dq_question");

  /** Dialog title for inserting new data. */
  String INSERTTITLE = lang("dn_title");
  /** Insert name. */
  String INSERTNAME = lang("dn_name") + COL;
  /** Insert value. */
  String INSERTVALUE = lang("dn_value") + COL;

  /** Dialog title for updating document data. */
  String EDITTITLE = lang("de_title");
  /** Dialog title for updating text. */
  String EDITTEXT = lang("de_text");
  /** Dialog title for updating text. */
  String[] EDITKIND = { lang("de_kind1"), lang("de_kind2"), lang("de_kind3"),
      lang("de_kind4"), lang("de_kind5"), lang("de_kind6")
  };

  /** Dialog title for choosing a font. */
  String FONTTITLE = lang("df_title");
  /** Predefined font types. */
  String[] FONTTYPES = { lang("df_type1"), lang("df_type2"), lang("df_type3") };

  /** Dialog title for treemap color schema. */
  String SCHEMATITLE = lang("dy_title");
  /** Color schema information. */
  String SCHEMARED = lang("dy_red");
  /** Color schema information. */
  String SCHEMAGREEN = lang("dy_green");
  /** Color schema information. */
  String SCHEMABLUE = lang("dy_blue");

  /** Dialog title for treemap design. */
  String MAPLAYOUTTITLE = lang("dm_title");
  /** Show attributes.  */
  String MAPATT = lang("dm_atts");
  /** Predefined number of layouts. */
  String[] MAPOFFSET = {
    lang("dm_choice1"), lang("dm_choice2"), lang("dm_choice3"),
    lang("dm_choice4"), lang("dm_choice5")
  };
  /** Predefined number of layouts. */
  String[] MAPALG = {
    "Split Layout", "Strip Layout", "Squarified Layout",
    "Slice&Dice Layout", "Binary Layout"
  };

  /** Map layout-algorithm. */
  String MAPOFF = lang("dm_offset") + COL;

  /** Size depending on... */
  String MAPSIZE = lang("dm_size");
  /** Size depending on... */
  String MAPBOTH = lang("dm_size_both");
  /** Size depending on... */
  String MAPCHILDREN = lang("dm_size_children");
  /** Size depending on... */
  String MAPFSSIZE = lang("dm_size_fssize");
  /** Size depending on... */
  String MAPTEXTSIZE = lang("dm_size_textsize");

  /** Memory information. */
  String MEMTOTAL = lang("dz_total") + COLS;
  /** Memory information. */
  String MEMRESERVED = lang("dz_reserved") + COLS;
  /** Memory information. */
  String MEMUSED = lang("dz_used") + COLS;
  /** Memory help. */
  String MEMHELP = lang("dz_help");

  /** About text. */
  String ABOUTTITLE = lang("da_title", NAME);
  /** Copyright info. */
  String COPYRIGHT = "©2005-09 " + COMPANY;
  /** License info. */
  String LICENSE = lang("da_license");
  /** Developer info. */
  String DEVELOPER = lang("da_dev") + ": Christian Grün";
  /** Contributors info. */
  String CONTRIBUTE1 = lang("da_cont1") + ": Sebastian Gath, Lukas Kircher,";
  /** Developer names. */
  String CONTRIBUTE2 = "Andreas Weiler, Alexander Holupirek " +
    lang("da_cont2");
  /** Translation. */
  String TRANSLATION = lang("da_translation") + COLS;

  // HELP TEXTS ===============================================================

  /** Help string. */
  byte[] HELPGO = token(lang("h_go"));
  /** Help string. */
  byte[] HELPSTOP = token(lang("h_stop"));
  /** Help string. */
  byte[] HELPHIST = token(lang("h_hist"));
  /** Help string. */
  byte[] HELPRECENT = token(lang("h_recent"));
  /** Help Dialog. */
  byte[] HELPCMD = token(lang("h_cmd"));
  /** Help Dialog. */
  byte[] HELPSEARCHXML = token(lang("h_searchxml"));
  /** Help Dialog. */
  byte[] HELPSEARCHFS = token(lang("h_searchfs"));
  /** Help Dialog. */
  byte[] HELPXPATH = token(lang("h_xpath"));
  /** Help string. */
  byte[] HELPMAP = token(lang("h_map"));
  /** Help string. */
  byte[] HELPPLOT = token(lang("h_plot"));
  /** Help string. */
  byte[] HELPFOLDER = token(lang("h_folder"));
  /** Help string. */
  byte[] HELPTABLE = token(lang("h_table"));
  /** Help string. */
  byte[] HELPTEXT = token(lang("h_text"));
  /** Help string. */
  byte[] HELPINFOO = token(lang("h_info"));
  /** Help string. */
  byte[] HELPEXPLORE = token(lang("h_explore"));
  /** Help string. */
  byte[] HELPXQUERYY = token(lang("h_xquery"));
  /** Help string. */
  byte[] HELPMOVER = token(lang("h_mover"));

  /** Dummy string to check if all language strings have been assigned. */
  String DUMMY = lang(null);
}
