/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.repository.manager.RepositoryManager;

/**
 * A manager for repositories hosted on Dydra.
 *
 * This repository manager allows access to Dydra repositories similar to
 * how local repositories are accessed using the {@link
 * org.openrdf.repository.manager.LocalRepositoryManager} class.
 */
public class DydraRepositoryManager extends RemoteRepositoryManager {
  public DydraRepositoryManager(final String serverURL) {
    super(serverURL);
  }
}
