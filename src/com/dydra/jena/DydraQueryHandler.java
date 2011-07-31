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
  protected final DydraGraph graph;

  /**
   * @throws NullPointerException if <code>graph</code> is null
   */
  public DydraQueryHandler(@NotNull final DydraGraph graph) {
    super(graph);

    if (graph == null)
      throw new NullPointerException("graph cannot be null");

    this.graph = graph;
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

    if (node.isLiteral()) {
      return this.graph.contains(Node.ANY, Node.ANY, node);
    }
    if (node.isBlank()) {
      return this.graph.contains(node, Node.ANY, Node.ANY) ||
             this.graph.contains(Node.ANY, Node.ANY, node);
    }
    if (node.isURI()) {
      return this.graph.contains(node, Node.ANY, Node.ANY) ||
             this.graph.contains(Node.ANY, node, Node.ANY) ||
             this.graph.contains(Node.ANY, Node.ANY, node);
    }
    return false;
  }
}
