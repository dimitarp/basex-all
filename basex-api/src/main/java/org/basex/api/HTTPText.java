package org.basex.api;

/**
 * This class assembles texts which are used in the HTTP classes.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Christian Gruen
 */
public interface HTTPText {
  /** HTTP String. */
  String HTTP = "HTTP";
  /** Servlet string. */
  String SERVLET = "Servlet";

  /** Configuration: database user. */
  String DBUSER = "org.basex.user";
  /** Configuration: database user password. */
  String DBPASS = "org.basex.password";
  /** Configuration: operation mode:
      "local", "client", or default ({@code null}). */
  String DBMODE = "org.basex.mode";

  /** Mode: local. */
  String LOCAL = "local";
  /** Mode: client. */
  String CLIENT = "client";

  /** Authorization string. */
  String AUTHORIZATION = "Authorization";
  /** WWW-Authentication string. */
  String WWW_AUTHENTICATE = "WWW-Authenticate";
  /** Location string. */
  String LOCATION = "Location";
  /** Basic string. */
  String BASIC = "Basic";

  /** Error: no password. */
  String NOPASSWD = "No username/password specified.";
  /** Error: unsupported authorization method. */
  String WHICHAUTH = "Unsupported authorization method: %.";
  /** Error: only allow local or client mode. */
  String INVMODE = "You cannot use both local and client mode.";
}
