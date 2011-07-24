/* This is free and unencumbered software released into the public domain. */

package com.dydra;

/**
 * Represents a relation stored in a Dydra.com repository.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://en.wikipedia.org/wiki/Binary_relation
 */
public class Relation extends Statement {
  /**
   * Constructs a relation from the given statement identifier.
   *
   * @param  id a statement identifier
   */
  public Relation(final Identifier id) {
    super(id);
  }

  /**
   * Returns a string representation of this relation.
   *
   * @return a string
   */
  @Override
  public String toString() {
    return super.toString(); // TODO
  }
}
