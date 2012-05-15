/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.IOException; 
import java.util.*;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.ParserConfig;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;

public class DydraRepositoryConnectionTest {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryConnectionTest.class.getName());
  }

  private Properties properties;
  private String accountName;
  private String password;
  private String repositoryName;
  private String serverURL;
  private String repositoryURL;
  private Repository repository;
  private RepositoryConnection connection;

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
    // TODO: connection.isOpen()
  }

  @Test
  public void testClose() throws RepositoryException {
    connection.close();
    assertFalse(connection.isOpen());
  }

  @Test
  public void testQuery() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareQuery()
  }

  @Test
  public void testTupleQuery() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareTupleQuery()
  }

  @Test
  public void testGraphQuery() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareGraphQuery()
  }

  @Test
  public void testBooleanQuery() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareBooleanQuery()
  }

  @Test
  public void testUpdate() throws RepositoryException, MalformedQueryException {
    // TODO: connection.prepareUpdate()
  }

  @Test
  public void testGetContextIDs() throws RepositoryException {
    // TODO: connection.getContextIDs()
  }

  @Test
  public void testGetStatements() throws RepositoryException {
    // TODO: connection.getStatements()
  }

  @Test
  public void testHasStatement() throws RepositoryException {
    // TODO: connection.hasStatement()
  }

  @Test
  public void testExportStatements() throws RepositoryException, RDFHandlerException {
    // TODO: connection.exportStatements()
  }

  @Test
  public void testExport() throws RepositoryException, RDFHandlerException {
    // TODO: connection.export()
  }

  @Test
  public void testSize() throws RepositoryException {
    // TODO: connection.size()
  }

  @Test
  public void testIsEmpty() throws RepositoryException {
    assertFalse(connection.isEmpty());
  }

  @Test
  public void testAutoCommit() throws RepositoryException {
    connection.setAutoCommit(true);
    assertTrue(connection.isAutoCommit());
    connection.setAutoCommit(false);
    assertFalse(connection.isAutoCommit());
  }

  @Test
  public void testCommit() throws RepositoryException {
    // TODO: connection.commit()
  }

  @Test
  public void testRollback() throws RepositoryException {
    // TODO: connection.rollback()
  }

  @Test
  public void testAdd() throws IOException, RDFParseException, RepositoryException {
    // TODO: connection.add()
  }

  @Test
  public void testRemove() throws RepositoryException {
    // TODO: connection.remove()
  }

  @Test
  public void testClear() throws RepositoryException {
    // TODO: connection.clear()
  }

  @Test
  public void testGetNamespaces() throws RepositoryException {
    // TODO: connection.getNamespaces()
  }

  @Test
  public void testGetNamespace() throws RepositoryException {
    // TODO: connection.getNamespace()
  }

  @Test
  public void testSetNamespace() throws RepositoryException {
    // TODO: connection.setNamespace()
  }

  @Test
  public void testRemoveNamespace() throws RepositoryException {
    // TODO: connection.removeNamespace()
  }

  @Test
  public void testClearNamespaces() throws RepositoryException {
    // TODO: connection.clearNamespaces()
  }
}
