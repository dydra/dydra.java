/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;
import java.util.Iterator;

/**
 * Represents a Dydra.com user account.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public class Account extends Resource {
  /**
   * The account name.
   */
  public final String name;

  /**
   * Constructs...
   *
   * @param name    a valid account name
   * @param session
   */
  public Account(@NotNull final String name, @NotNull final Session session) {
    super(name, session);
    this.name = name;
  }

  /**
   * Returns a particular repository belonging to this account.
   *
   * @param  name a valid repository name
   * @return the repository
   */
  @NotNull
  public Repository getRepository(final String name) {
    return new Repository(this.name + "/" + name, this.session);
  }

  /**
   * Returns the repositories belonging to this account.
   *
   * @return a repository iterator
   */
  @NotNull
  public Iterator<Repository> getRepositories() {
    throw new UnsupportedOperationException("not implemented");
  }
}
