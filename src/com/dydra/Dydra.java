/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;

/**
 * Constructs public and authenticated Dydra.com URLs.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api
 */
public final class Dydra {
  public static final String HOST     = "dydra.com";
  public static final String BASE_URL = "http://" + HOST + "/";
  public static final String AUTH_URL = "http://%s:%s@" + HOST + "/";

  /**
   * Returns the base URL for Dydra.com API access.
   *
   * @return a URL string
   */
  @NotNull
  public static String getBaseURL() {
    try {
      final String baseURL = System.getenv("DYDRA_URL");
      if (baseURL != null && !baseURL.isEmpty()) {
        return baseURL;
      }
    }
    catch (SecurityException e) {}
    return BASE_URL;
  }

  /**
   * Returns an unauthenticated URL.
   *
   * @param  path a root-relative path, without the initial slash
   * @return an unauthenticated URL string
   */
  @NotNull
  public static String getPublicURL(@NotNull final String path) {
    return getBaseURL() + path;
  }

  /**
   * Returns an authenticated URL using an existing session.
   *
   * @param  session the session to obtain credentials from
   * @param  path  a root-relative path, without the initial slash
   * @return an authenticated URL string
   */
  @NotNull
  public static String getAuthenticatedURL(@NotNull final Session session,
                                           @NotNull final String path) {
    return (session.token != null) ?
      getAuthenticatedURL(session.token, path) :
      getAuthenticatedURL(session.username, session.password, path);
  }

  /**
   * Returns an authenticated URL using a given API key.
   *
   * @param  token a valid API token
   * @param  path  a root-relative path, without the initial slash
   * @return an authenticated URL string
   */
  @NotNull
  public static String getAuthenticatedURL(@NotNull final String token,
                                           @NotNull final String path) {
    return getBaseURL() + path + "?auth_token=" + token;
  }

  /**
   * Returns an authenticated URL using the given HTTP credentials.
   *
   * @param  username a valid Dydra.com account name
   * @param  password the account password
   * @param  path     a root-relative path, without the initial slash
   * @return an authenticated URL string
   */
  @NotNull
  public static String getAuthenticatedURL(@NotNull final String username,
                                           @NotNull final String password,
                                           @NotNull final String path) {
    return String.format(AUTH_URL, username, password) + path;
  }

  /**
   * @private
   */
  private Dydra() {
    throw new AssertionError("cannot instantiate static class");
  }
}
