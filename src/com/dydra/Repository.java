package com.dydra;

/**
 * Represents a Dydra.com RDF repository.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public class Repository extends Resource {
  /**
   * The repository name.
   */
  public String name;

  /**
   * Constructs...
   *
   * @param name a valid repository name
   */
  public Repository(final String name) {
    super(name);
    this.name = name;
  }

  /**
   * Returns ...
   *
   * @return the repository owner
   */
  public Account getAccount() {
    return null; // TODO
  }

  /**
   * Returns the number of RDF statements in this repository.
   *
   * @return a positive integer
   */
  public long getCount() {
    return 0; // TODO: call the dydra.repository.count RPC method
  }

  /**
   * Deletes all data in this repository.
   *
   * @return a pending job
   */
  public Job clear() {
    return null; // TODO: call the dydra.repository.clear RPC method
  }

  /**
   * Imports data from the given URL into this repository.
   *
   * @param  url a valid URL string
   * @return a pending job
   */
  public Job importFromURL(final String url) {
    return null; // TODO: call the dydra.repository.import RPC method
  }
}
