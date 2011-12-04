package org.basex.query.util.pkg;

import static org.basex.util.Token.*;

import java.io.File;

import org.basex.util.TokenBuilder;
import org.basex.util.list.ObjList;

/**
 * Package.
 *
 * @author BaseX Team 2005-11, BSD License
 * @author Rositsa Shadura
 */
public final class Package {
  /** List of dependencies. */
  public final ObjList<Dependency> dep = new ObjList<Dependency>();
  /** Package components. */
  public final ObjList<Component> comps = new ObjList<Component>();
  /** Package short name. */
  public byte[] abbrev;
  /** Package uri. */
  byte[] name;
  /** Package version. */
  byte[] version;
  /** Version of packaging specification the package conforms to. */
  byte[] spec;

  /**
   * Returns unique package name consisting of package uri and package version.
   * @return result
   */
  byte[] uniqueName() {
    return new TokenBuilder().add(name).add('-').add(version).finish();
  }

  /**
   * Extracts the package name from a unique package name.
   * @param pkgName unique package name: name-version
   * @return package name
   */
  public static byte[] name(final byte[] pkgName) {
    final int idx = lastIndexOf(pkgName, '-');
    return idx == -1 ? pkgName : subtoken(pkgName, 0, idx);
  }

  /**
   * Extracts the package version from a unique package name.
   * @param pkgName unique package name: name-version
   * @return package version
   */
  public static byte[] version(final byte[] pkgName) {
    final int idx = lastIndexOf(pkgName, '-');
    return subtoken(pkgName, idx + 1, pkgName.length);
  }

  /**
   * Package dependency.
   * @author BaseX Team 2005-11, BSD License
   * @author Rositsa Shadura
   */
  public static final class Dependency {
    /** Name of package a package depends on. */
    public byte[] pkg;
    /** Name of processor a package depends on. */
    byte[] processor;
    /** Set of acceptable version. */
    byte[] versions;
    /** SemVer template. */
    byte[] semver;
    /** Minimum acceptable version. */
    byte[] semverMin;
    /** Maximum acceptable version. */
    byte[] semverMax;

    /**
     * Returns unique package name for secondary package using the given
     * version.
     * @param version version
     * @return unique name
     */
    public byte[] name(final byte[] version) {
      return new TokenBuilder().add(pkg).add('-').add(version).finish();
    }
  }

  /**
   * Package component.
   * @author BaseX Team 2005-11, BSD License
   * @author Rositsa Shadura
   */
  public static final class Component {
    /** Namespace URI. */
    public byte[] uri;
    /** Component file. */
    public byte[] file;

    /**
     * Extracts component's file name from component's path.
     * @return component's name
     */
    String name() {
      final String path = string(file);
      final int i = path.lastIndexOf(File.separator);
      return i == -1 ? path : path.substring(i + 1, path.length());
    }
  }
}
