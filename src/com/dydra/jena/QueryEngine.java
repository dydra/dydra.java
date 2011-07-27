package com.dydra.jena;

import com.dydra.Dydra;
import com.dydra.annotation.*;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

/**
 * Implements a Dydra-specific convenience and performance wrapper over
 * ARQ's remote query execution facilities.
 *
 * @see http://docs.dydra.com/sdk/java/jena
 */
public class QueryEngine extends QueryEngineHTTP implements QueryExecution {
  /**
   * Constructs a query engine for executing queries on a given repository.
   *
   * @param  repositoryName
   *   the name of the repository, e.g. "jhacker/foaf"
   * @param  query
   *   the SPARQL query
   */
  public QueryEngine(@NotNull final String repositoryName,
                     @NotNull final Query query) {
    this(repositoryName, query.toString());
  }

  /**
   * Constructs a query engine for executing queries on a given repository.
   *
   * @param  repositoryName
   *   the name of the repository, e.g. "jhacker/foaf"
   * @param  queryString
   *   the SPARQL query string, e.g. "SELECT * WHERE {?s ?p ?o}"
   */
  public QueryEngine(@NotNull final String repositoryName,
                     @NotNull final String queryString) {
    super(Dydra.BASE_URL + repositoryName + "/sparql", queryString);
  }
}
