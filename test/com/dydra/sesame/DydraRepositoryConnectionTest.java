/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.test.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import static org.junit.Assert.*;
import java.io.IOException; 
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.openrdf.model.Namespace;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.NamespaceImpl;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.BindingSet;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.QueryResult;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.http.HTTPQueryEvaluationException;
import org.openrdf.rio.ParserConfig;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.helpers.RDFHandlerBase;

public class DydraRepositoryConnectionTest {
  public static final int TRIPLE_COUNT = 34; // @see etc/doap.nt
  public static final int TYPE_COUNT = 5;
  public static final String GRAPH_CONTEXT = "http://sdk.dydra.com/java/0.0.0/";
  public static final int TRIPLE_LIMIT = 20;

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryConnectionTest.class.getName());
  }

  protected Properties properties;
  protected String accountName;
  protected String password;
  protected String repositoryName;
  protected String serverURL;
  protected String repositoryURL;
  protected Repository repository;
  protected RepositoryConnection connection;
  protected ValueFactory valueFactory;
  protected URI context;

  protected ValueFactory getValueFactory() {
    if (valueFactory == null)
      valueFactory = connection.getValueFactory();
    return valueFactory;
  }

  protected URI getContext() {
    if (context == null)
      context = getValueFactory().createURI(GRAPH_CONTEXT);
    return context;
  }

  @Before
  public void setUp() throws RepositoryException {
    this.properties     = System.getProperties();
    this.accountName    = properties.getProperty("com.dydra.sesame.account", "jhacker");
    this.password       = properties.getProperty("com.dydra.sesame.password", null);
    this.repositoryName = properties.getProperty("com.dydra.sesame.repository", "test");
    this.serverURL      = properties.getProperty("com.dydra.sesame.url",
      "http://api.dydra.com/sesame2") + "/" + this.accountName + "/";
    this.repositoryURL  = this.serverURL + "repositories/" + this.repositoryName;
    this.repository     = new DydraRepository(this.serverURL, this.repositoryName);
    this.repository.initialize();
    this.connection     = this.repository.getConnection();
  }

  @After
  public void tearDown() {
    this.connection = null;
    this.repository = null;
  }

  @Test
  public void testRepository() {
    final Repository repository = connection.getRepository();
    assertNotNull(repository);
  }

  @Test
  public void testParserConfig() {
    // TODO: connection.setParserConfig()
    // TODO: connection.getParserConfig()
    final ParserConfig config = connection.getParserConfig();
    assertNotNull(config);
  }

  @Test
  public void testValueFactory() {
    final ValueFactory factory = connection.getValueFactory();
    assertNotNull(factory);
  }

  @Test
  public void testIsOpen() throws RepositoryException {
    assertTrue(connection.isOpen());
  }

  @Test
  public void testClose() throws RepositoryException {
    connection.close();
    assertFalse(connection.isOpen());
  }

  @Test
  public void testTupleQuery()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final TupleQuery query = connection.prepareTupleQuery(
      QueryLanguage.SPARQL, "SELECT * WHERE {?s ?p ?o} LIMIT " + TRIPLE_LIMIT, null);
    assertNotNull(query);

    final TupleQueryResult result = query.evaluate();
    assertNotNull(result);

    final List<String> variables = result.getBindingNames();
    assertNotNull(variables);
    assertEquals(3, variables.size());
    assertTrue(variables.contains("s"));
    assertTrue(variables.contains("p"));
    assertTrue(variables.contains("o"));

    assertTrue(result.hasNext());
    assertEquals(TRIPLE_LIMIT, countTupleQueryResult(result));
  }

  protected int countTupleQueryResult(final TupleQueryResult result)
      throws QueryEvaluationException {
    int count = 0;
    while (result.hasNext()) {
      result.next();
      count++;
    }
    result.close();
    return count;
  }

  @Test
  public void testGraphQuery()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final GraphQuery query = connection.prepareGraphQuery(
      QueryLanguage.SPARQL, "CONSTRUCT * WHERE {?s ?p ?o} LIMIT " + TRIPLE_LIMIT, null);
    assertNotNull(query);

    final GraphQueryResult result = query.evaluate();
    assertNotNull(result);

    assertTrue(result.hasNext());
    assertEquals(TRIPLE_LIMIT, countGraphQueryResult(result));
  }

  protected int countGraphQueryResult(final GraphQueryResult result)
      throws QueryEvaluationException {
    int count = 0;
    while (result.hasNext()) {
      result.next();
      count++;
    }
    result.close();
    return count;
  }

  @Test
  public void testBooleanQuery()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final BooleanQuery query = connection.prepareBooleanQuery(
      QueryLanguage.SPARQL, "ASK WHERE {?s ?p ?o}", null);
    assertNotNull(query);

    final boolean result = query.evaluate();
    assertTrue(result);
  }

  @Test
  public void testQuery()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    // TODO: final Query query = connection.prepareQuery(QueryLanguage.SPARQL, ...);
  }

  @Test
  public void testQueryWithBindings()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final TupleQuery query = connection.prepareTupleQuery(
      QueryLanguage.SPARQL, "SELECT * WHERE {?s ?p ?o}", null);
    assertNotNull(query);

    query.setBinding("p", RDF.TYPE);

    final TupleQueryResult result = query.evaluate();
    assertNotNull(result);

    final List<String> variables = result.getBindingNames();
    assertNotNull(variables);
    assertEquals(2, variables.size()); // FIXME: 3
    assertTrue(variables.contains("s"));
    //assertTrue(variables.contains("p")); // FIXME!
    assertTrue(variables.contains("o"));

    assertTrue(result.hasNext());
    int resultCount = 0;
    while (result.hasNext()) {
      resultCount++;
      final BindingSet bindings = result.next();
      assertNotNull(bindings);
      assertTrue(bindings.hasBinding("s"));
      //assertTrue(bindings.hasBinding("p")); // FIXME!
      assertTrue(bindings.hasBinding("o"));
      assertEquals(2, bindings.size()); // FIXME: 3
    }
    assertEquals(TYPE_COUNT, resultCount);
    result.close();
  }

  @Test
  public void testQueryWithIgnoredBindings()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final TupleQuery query = connection.prepareTupleQuery(
      QueryLanguage.SPARQL, "SELECT * WHERE {?s ?p ?o} LIMIT " + TRIPLE_LIMIT, null);
    assertNotNull(query);

    // binding the nonexistent variables ?x and ?y will be ignored
    query.setBinding("x", RDF.TYPE);
    query.setBinding("y", RDF.TYPE);

    final TupleQueryResult result = query.evaluate();
    assertNotNull(result);

    final List<String> variables = result.getBindingNames();
    assertNotNull(variables);
    assertEquals(3, variables.size());
    assertTrue(variables.contains("s"));
    assertTrue(variables.contains("p"));
    assertTrue(variables.contains("o"));
    assertFalse(variables.contains("x"));
    assertFalse(variables.contains("y"));

    assertTrue(result.hasNext());
    assertEquals(TRIPLE_LIMIT, countTupleQueryResult(result));
  }

  @Test(expected=HTTPQueryEvaluationException.class)
  public void testSeRQLQuery()
      throws RepositoryException, MalformedQueryException, QueryEvaluationException {
    final TupleQuery query = connection.prepareTupleQuery(
      QueryLanguage.SERQL, "SELECT * FROM {S} rdf:type {O}", null);
    assertNotNull(query);

    query.evaluate(); // throws HTTPQueryEvaluationException (400 Bad Request)
  }

  @Test @Category(Mutative.class)
  public void testUpdate() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareUpdate(QueryLanguage.SPARQL, ...)
  }

  @Test @Category(Mutative.class)
  public void testUpdateWithBindings() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareUpdate(QueryLanguage.SPARQL, ...)
  }

  @Test
  public void testGetContextIDs() throws RepositoryException {
    final RepositoryResult<Resource> contexts = connection.getContextIDs();
    assertNotNull(contexts);

    final List<Resource> contextList = contexts.asList();
    assertNotNull(contextList);

    assertFalse(contextList.isEmpty());
    assertTrue(contextList.size() >= 1);
  }

  @Test
  public void testGetStatements() throws RepositoryException {
    final RepositoryResult<Statement> statements = connection.getStatements(
      null, null, null, false, getContext());
    assertNotNull(statements);

    final List<Statement> statementList = statements.asList();
    assertNotNull(statementList);

    assertFalse(statementList.isEmpty());
    assertEquals(TRIPLE_COUNT, statementList.size());
  }

  @Test
  public void testHasStatement() throws RepositoryException {
    final Resource subject = getValueFactory().createURI("http://sdk.dydra.com/java/");
    final URI predicate    = getValueFactory().createURI("http://usefulinc.com/ns/doap#implements");
    final Value object     = getValueFactory().createLiteral("SPARQL 1.1", "en");

    assertTrue(connection.hasStatement(
      subject, predicate, object, false, getContext()));
  }

  @Test
  public void testExport() throws RepositoryException, RDFHandlerException {
    final AtomicInteger counter = new AtomicInteger(0);
    final RDFHandler handler = new RDFHandlerBase() {
      @Override
      public void handleStatement(final Statement stmt) throws RDFHandlerException {
        counter.incrementAndGet();
        assertNotNull(stmt);
      }
    };
    connection.export(handler, getContext());
    assertEquals(TRIPLE_COUNT, counter.get());
  }

  @Test
  public void testExportStatements() throws RepositoryException, RDFHandlerException {
    final AtomicInteger counter = new AtomicInteger(0);
    final RDFHandler handler = new RDFHandlerBase() {
      @Override
      public void handleStatement(final Statement stmt) throws RDFHandlerException {
        counter.incrementAndGet();
        assertNotNull(stmt);
      }
    };
    connection.exportStatements(null, RDF.TYPE, null, false, handler, getContext());
    assertEquals(TYPE_COUNT, counter.get());
  }

  @Test
  public void testSize() throws RepositoryException {
    final long size = connection.size();
    assertTrue(size >= TRIPLE_COUNT);
  }

  @Test
  public void testSizeOfDefaultGraph() throws RepositoryException {
    final long size = connection.size((Resource)null);
    assertTrue(size > 0);
  }

  @Test
  public void testSizeOfNamedGraph() throws RepositoryException {
    final long size = connection.size(getContext());
    assertEquals(TRIPLE_COUNT, size);
  }

  @Test
  public void testSizeOfUnknownGraph() throws RepositoryException {
    final URI randomURI = getValueFactory().createURI(
      "urn:uuid:" + UUID.randomUUID().toString());
    final long size = connection.size(randomURI);
    assertEquals(0, size);
  }

  @Test
  public void testIsEmpty() throws RepositoryException {
    assertFalse(connection.isEmpty());
  }

  @Test @Category(Mutative.class)
  public void testAutoCommit() throws RepositoryException {
    connection.setAutoCommit(true);
    assertTrue(connection.isAutoCommit());

    connection.setAutoCommit(false);
    assertFalse(connection.isAutoCommit());
  }

  @Test @Category(Mutative.class)
  public void testCommit() throws RepositoryException {
    // TODO: connection.commit()
  }

  @Test @Category(Mutative.class)
  public void testRollback() throws RepositoryException {
    // TODO: connection.rollback()
  }

  @Test @Category(Mutative.class)
  public void testAdd() throws IOException, RDFParseException, RepositoryException {
    // TODO: connection.add()
  }

  @Test @Category(Mutative.class)
  public void testRemove() throws RepositoryException {
    // TODO: connection.remove()
  }

  @Test @Category(Mutative.class)
  public void testClear() throws RepositoryException {
    // TODO: connection.clear()
  }

  @Test
  public void testGetNamespaces() throws RepositoryException {
    final RepositoryResult<Namespace> namespaces = connection.getNamespaces();
    assertNotNull(namespaces);

    final List<Namespace> namespaceList = namespaces.asList();
    assertNotNull(namespaceList);

    assertFalse(namespaceList.isEmpty());
    //assertTrue(namespaceList.contains(new NamespaceImpl("rdf", RDF.NAMESPACE)));
  }

  @Test
  public void testGetNamespace() throws RepositoryException {
    final String namespace = connection.getNamespace("rdf");
    assertEquals(RDF.NAMESPACE, namespace);
  }

  @Test @Category(Mutative.class)
  public void testSetNamespace() throws RepositoryException {
    // TODO: connection.setNamespace("rdf", RDF.NAMESPACE);
  }

  @Test @Category(Mutative.class)
  public void testRemoveNamespace() throws RepositoryException {
    // TODO: connection.removeNamespace("rdf");
  }

  @Test @Category(Mutative.class)
  public void testClearNamespaces() throws RepositoryException {
    // TODO: connection.clearNamespaces();
  }
}
