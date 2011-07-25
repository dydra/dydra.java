/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;

/**
 * Represents a graph stored in a Dydra.com repository.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://en.wikipedia.org/wiki/Graph_(data_structure)
 */
public class Graph implements Identifiable {
  /**
   * The graph identifier.
   */
  public final Identifier id;

  /**
   * The graph context.
   */
  public final Identifiable context;

  /**
   * Constructs a graph from the given identifier.
   */
  public Graph() {
    this.id      = Identifier.EMPTY;
    this.context = null;
  }

  /**
   * Constructs a graph with the given context.
   *
   * @param  id a graph identifier
   * @throws NullPointerException if <code>context</code> is null
   */
  public Graph(@Nullable final Identifiable context) {
    if (context == null)
      throw new NullPointerException("context cannot be null");

    this.id      = context.toIdentifier();
    this.context = context;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param  other the object to compare this graph against
   * @return <code>true</code> if the given object is equivalent to this
   *         graph, <code>false</code> otherwise
   */
  @Override
  public boolean equals(@Nullable final Object other) {
    return (other instanceof Graph) && equals((Graph)other);
  }

  /**
   * Indicates whether another graph is equal to this one.
   *
   * @param  other the graph to compare this graph against
   * @return <code>true</code> if the given graph is equivalent to this
   *         graph, <code>false</code> otherwise
   */
  public boolean equals(@Nullable final Graph other) {
    return this.toIdentifier().equals(other.toIdentifier());
  }

  /**
   * Returns the hash code for this graph.
   *
   * @return a hash code value
   */
  @Override
  public int hashCode() {
    return this.toIdentifier().hashCode();
  }

  /**
   * Returns a string representation of this graph.
   *
   * @return a hexadecimal string of length <code>Identifier.LENGTH</code>
   */
  @Override @NotNull
  public String toString() {
    return this.toIdentifier().toString(); // TODO
  }

  /**
   * Returns the context identifier for this graph.
   *
   * @return a Dydra.com identifier
   */
  @NotNull
  public Identifier toIdentifier() {
    return this.id;
  }
}
