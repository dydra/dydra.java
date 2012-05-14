/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.annotation.*;
import com.dydra.Dydra;

import java.util.Properties;

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
  public static final String SERVER_BASE_URL_PROPERTY = "com.dydra.sesame.url";
  public static final String SERVER_BASE_URL = "http://api.dydra.com/sesame2";

  private final String accountName;

  public DydraRepositoryManager(@NotNull final String accountName) {
    this(accountName, System.getProperty(SERVER_BASE_URL_PROPERTY, SERVER_BASE_URL));
  }

  public DydraRepositoryManager(@NotNull final String accountName,
                                @NotNull final Properties properties) {
    this(accountName, properties.getProperty(SERVER_BASE_URL_PROPERTY, SERVER_BASE_URL));
  }

  public DydraRepositoryManager(@NotNull final String accountName,
                                @NotNull final String serverBaseURL) {
    super((serverBaseURL.endsWith("/") ? serverBaseURL : serverBaseURL + "/") +
      accountName + "/");
    this.accountName = accountName;
  }

  public void setPassword(@Nullable final String password) {
    this.setUsernameAndPassword((password != null) ? this.accountName : null, password);
  }
}
