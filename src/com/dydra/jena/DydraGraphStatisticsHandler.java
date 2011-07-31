/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.GraphStatisticsHandler;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Node_Variable;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/GraphStatisticsHandler.html
 */
public class DydraGraphStatisticsHandler implements GraphStatisticsHandler {
  protected final DydraGraph graph;

  /**
   * @throws NullPointerException if <code>graph</code> is null
   */
  public DydraGraphStatisticsHandler(@NotNull final DydraGraph graph) {
    if (graph == null)
      throw new NullPointerException("graph cannot be null");

    this.graph = graph;
  }

  /**
   * Returns the number of statements that would match the given triple
   * pattern.
   *
   * @param  subject   the subject term, or <code>null</code>
   * @param  predicate the predicate term, or <code>null</code>
   * @param  object    the object term, or <code>null</code>
   * @return a positive integer, or -1 if no estimate is available
   */
  @Override
  public long getStatistic(@Nullable final Node subject,
                           @Nullable final Node predicate,
                           @Nullable final Node object) {
    final String query = (this.graph.getURI() != null) ?
      String.format("SELECT (COUNT(*) AS ?count) FROM <%s> WHERE {%%s %%s %%s}",
        this.graph.getURI()) :
      "SELECT (COUNT(*) AS ?count) WHERE {%s %s %s}";

    return this.graph.execCount(query,
      (subject   != null && subject   != Node.ANY) ? subject   : new Node_Variable("s"),
      (predicate != null && predicate != Node.ANY) ? predicate : new Node_Variable("p"),
      (object    != null && object    != Node.ANY) ? object    : new Node_Variable("o"));
  }
}
