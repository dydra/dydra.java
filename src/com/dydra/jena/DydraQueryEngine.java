package com.dydra.jena;

import com.dydra.Dydra;
import com.dydra.Repository;
import com.dydra.annotation.*;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

/**
 * Implements a Dydra-specific convenience and performance wrapper over
 * ARQ's remote query execution facilities.
 *
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/ARQ/javadoc/com/hp/hpl/jena/query/QueryExecution.html
 */
public class DydraQueryEngine extends QueryEngineHTTP implements QueryExecution {
  /**
   * Constructs a query engine for executing a query on a given repository.
   *
   * @param  repository
   *   the Dydra repository
   * @param  query
   *   the SPARQL query
   */
  public DydraQueryEngine(@NotNull final Repository repository,
                          @NotNull final Query query) {
    this(repository.name, query.toString());
  }

  /**
   * Constructs a query engine for executing a query on a given repository.
   *
   * @param  repository
   *   the Dydra repository
   * @param  query
   *   the SPARQL query string, e.g. "SELECT * WHERE {?s ?p ?o}"
   */
  public DydraQueryEngine(@NotNull final Repository repository,
                          @NotNull final String queryString) {
    this(repository.name, queryString);
  }

  /**
   * Constructs a query engine for executing a query on a given repository.
   *
   * @param  repositoryName
   *   the name of the Dydra repository, e.g. "jhacker/foaf"
   * @param  query
   *   the SPARQL query
   */
  public DydraQueryEngine(@NotNull final String repositoryName,
                          @NotNull final Query query) {
    this(repositoryName, query.toString());
  }

  /**
   * Constructs a query engine for executing a query on a given repository.
   *
   * @param  repositoryName
   *   the name of the Dydra repository, e.g. "jhacker/foaf"
   * @param  queryString
   *   the SPARQL query string, e.g. "SELECT * WHERE {?s ?p ?o}"
   */
  public DydraQueryEngine(@NotNull final String repositoryName,
                          @NotNull final String queryString) {
    super(Dydra.BASE_URL + repositoryName + "/sparql", queryString);
  }
}
