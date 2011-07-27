package com.dydra.jena;

import com.dydra.Repository;
import com.dydra.annotation.*;
import com.hp.hpl.jena.query.QueryExecution;
import java.util.List;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/ARQ/javadoc/com/hp/hpl/jena/query/QueryExecutionFactory.html
 */
public class QueryExecutionFactory {
  protected QueryExecutionFactory() {}

  /**
   * @param  query
   * @param  repository
   */
  @NotNull
  public static QueryExecution prepare(@NotNull final String query,
                                       @NotNull final Repository repository) {
    return new QueryEngine(repository, query);
  }

  /**
   * @param  query
   * @param  repository
   * @param  defaultGraphURI
   */
  @NotNull
  public static QueryExecution prepare(@NotNull final String query,
                                       @NotNull final Repository repository,
                                       @Nullable final String defaultGraphURI) {
    QueryEngine engine = new QueryEngine(repository, query);
    if (defaultGraphURI != null) {
      engine.addDefaultGraph(defaultGraphURI);
    }
    return engine;
  }

  /**
   * @param  query
   * @param  repository
   * @param  defaultGraphURIs
   * @param  namedGraphURIs
   */
  @NotNull
  public static QueryExecution prepare(@NotNull final String query,
                                       @NotNull final Repository repository,
                                       @Nullable final List<String> defaultGraphURIs,
                                       @Nullable final List<String> namedGraphURIs) {
    QueryEngine engine = new QueryEngine(repository, query);
    if (defaultGraphURIs != null) {
      engine.setDefaultGraphURIs(defaultGraphURIs);
    }
    if (namedGraphURIs != null) {
      engine.setNamedGraphURIs(namedGraphURIs);
    }
    return engine;
  }
}
