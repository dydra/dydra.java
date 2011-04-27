package com.dydra;

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
  public String name;

  /**
   * Constructs...
   *
   * @param name a valid account name
   */
  public Account(final String name) {
    super(name);
    this.name = name;
  }

  /**
   * Returns a particular repository belonging to this account.
   *
   * @param  name a valid repository name
   * @return the repository
   */
  public Repository getRepository(final String name) {
    return new Repository(this.name + "/" + name);
  }

  /**
   * Returns the repositories belonging to this account.
   *
   * @return a repository iterator
   */
  public Iterator<Repository> getRepositories() {
    return null; // TODO
  }
}
