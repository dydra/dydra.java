/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.query.QueryHandler;
import com.hp.hpl.jena.graph.query.SimpleQueryHandler;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/query/QueryHandler.html
 */
public class DydraQueryHandler extends SimpleQueryHandler implements QueryHandler {
  public DydraQueryHandler(@NotNull final Graph graph) {
    super(graph);
  }

  /**
   * Checks whether the associated graph contains a given node.
   *
   * @param  node the node to attempt to find in the associated graph
   * @return <code>true</code> if the associated graph contains the given node
   * @throws NullPointerException if <code>node</code> is null
   */
  @Override
  public boolean containsNode(@NotNull final Node node) {
    if (node == null)
      throw new NullPointerException("node cannot be null");

    return super.containsNode(node); // TODO: perform an ASK query
  }
}
