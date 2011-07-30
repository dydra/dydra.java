/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.TripleMatch;
import com.hp.hpl.jena.graph.impl.GraphBase;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.NullIterator;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/Graph.html
 */
public class DydraGraph extends GraphBase implements Graph {
  /**
   * Finds triples in this graph that match the given triple pattern.
   *
   * @param  pattern the triple pattern to match for
   * @return an iterator yielding matching triples
   * @throws NullPointerException if <code>pattern</code> is null
   */
  @Override @NotNull
  protected ExtendedIterator<Triple> graphBaseFind(@NotNull final TripleMatch pattern) {
    if (pattern == null)
      throw new NullPointerException("pattern cannot be null");

    return new NullIterator<Triple>(); // TODO
  }
}
