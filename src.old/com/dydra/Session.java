/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;
import com.dydra.rpc.*;

/**
 * Represents a Dydra.com API session.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class Session {
  /**
   * The RPC endpoint URL.
   */
  public String url;

  /**
   * The API token, if any.
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
   * Constructs an unauthenticated session.
   */
  public Session() {
    this.token = null;
    this.url   = Dydra.getPublicURL("rpc");
  }

  /**
   * Constructs an authenticated session using the given API token.
   *
   * @param  token a valid API token
   */
  public Session(@NotNull String token) {
    this.token = token;
    this.url   = Dydra.getAuthenticatedURL(token, "rpc");
  }

  /**
   * Constructs an authenticated session using the given HTTP credentials.
   *
   * @param  username a valid Dydra.com account name
   * @param  password the account password
   */
  public Session(@NotNull String username, @NotNull String password) {
    this.username = username;
    this.password = password;
    this.url      = Dydra.getAuthenticatedURL(username, password, "rpc");
  }

  /**
   * Returns an RPC client instance for invoking RPC methods.
   *
   * @return an RPC client
   */
  @NotNull
  public RPCClient getClient() {
    return new RPCClient(this);
  }

  /**
   * Returns information about the Dydra.com account associated with this
   * session.
   *
   * @param  username the account name
   * @return an account
   */
  @NotNull
  public Account getAccount() {
    return getAccount(this.username);
  }

  /**
   * Returns information about a Dydra.com account of the given name.
   *
   * @param  username the account name
   * @return an account
   * @throws NullPointerException if <code>name</code> is null
   */
  @NotNull
  public Account getAccount(@NotNull final String name) {
    if (name == null)
      throw new NullPointerException("name cannot be null");

    return new Account(name, this);
  }
}
