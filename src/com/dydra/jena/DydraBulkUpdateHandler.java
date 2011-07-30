/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.BulkUpdateHandler;
import com.hp.hpl.jena.graph.impl.SimpleBulkUpdateHandler;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/BulkUpdateHandler.html
 */
public class DydraBulkUpdateHandler extends SimpleBulkUpdateHandler implements BulkUpdateHandler {
  protected final DydraGraph graph;

  /**
   * @throws NullPointerException if <code>graph</code> is null
   */
  public DydraBulkUpdateHandler(@NotNull final DydraGraph graph) {
    super(graph);

    if (graph == null)
      throw new NullPointerException("graph cannot be null");

    this.graph = graph;
  }

  // TODO
}
