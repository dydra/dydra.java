package com.dydra;

/**
 * Implements a Dydra.com XML-RPC API client.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class Client {
  public static final String HOST     = "api.dydra.com";
  public static final String BASE_URL = "http://" + HOST + "/";
  public static final String AUTH_URL = "http://%s:%s@" + HOST + "/";

  /**
   * Returns an unauthenticated URL.
   *
   * @param  path a root-relative path, without the initial slash
   * @return an unauthenticated URL string
   */
  public static String getPublicURL(final String path) {
    return BASE_URL + path;
  }

  /**
   * Returns an authenticated URL using a given API key.
   *
   * @param  token a valid API token
   * @param  path a root-relative path, without the initial slash
   * @return an authenticated URL string
   */
  public static String getAuthURL(final String token, final String path) {
    return BASE_URL + path + "?auth_token=" + token;
  }

  /**
   * Returns an authenticated URL using the given HTTP credentials.
   *
   * @param  username a valid Dydra.com account name
   * @param  password the account password
   * @param  path a root-relative path, without the initial slash
   * @return an authenticated URL string
   */
  public static String getAuthURL(final String username, final String password, final String path) {
    return String.format(AUTH_URL, username, password) + path;
  }

  /**
   * The RPC endpoint URL.
   */
  public String url;

  /**
   * The API key, if any.
   */
  public String token;

  /**
   * The account name.
   */
  public String username;

  /**
   * The account password.
   */
  public String password;

  /**
   * Constructs a client instance using the credentials from the
   * $DYDRA_TOKEN environment variable or the ~/.dydra/token configuration
   * file.
   */
  public Client() {
    // TODO: read $DYDRA_TOKEN or ~/.dydra/token
  }

  /**
   * Constructs a client instance using the given API token.
   *
   * @param  token a valid API token
   */
  public Client(String token) {
    this.token = token;
    this.url = getAuthURL(token, "rpc");
  }

  /**
   * Constructs a client instance using the given HTTP credentials.
   *
   * @param  username a valid Dydra.com account name
   * @param  password the account password
   */
  public Client(String username, String password) {
    this.username = username;
    this.password = password;
    this.url = getAuthURL(username, password, "rpc");
  }
}
