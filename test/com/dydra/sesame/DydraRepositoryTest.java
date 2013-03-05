/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.test.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.File;
import java.util.*;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class DydraRepositoryTest {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryTest.class.getName());
  }

  private Properties properties;
  private String accountName;
  private String password;
  private String repositoryName;
  private String serverURL;
  private String repositoryURL;
  private DydraRepository repository;

  @Before
  public void setUp() throws RepositoryException {
    this.properties     = System.getProperties();
    this.accountName    = properties.getProperty("com.dydra.sesame.account", "jhacker");
    this.password       = properties.getProperty("com.dydra.sesame.password", null);
    this.repositoryName = properties.getProperty("com.dydra.sesame.repository", "test");
    this.serverURL      = properties.getProperty("com.dydra.sesame.url",
      "http://dydra.com/sesame2") + "/" + this.accountName + "/";
    this.repositoryURL  = this.serverURL + "repositories/" + this.repositoryName;
    this.repository     = new DydraRepository(this.serverURL, this.repositoryName);
    this.repository.initialize();
  }

  @After
  public void tearDown() {
    this.repository = null;
  }

  @Test
  public void testDataDir() {
    assertNull(repository.getDataDir());
    final File tempDir = new File(System.getProperty("java.io.tmpdir", "/tmp"));
    repository.setDataDir(tempDir);
    assertNotNull(repository.getDataDir());
    assertEquals(tempDir, repository.getDataDir());
    repository.setDataDir(null);
    assertNull(repository.getDataDir());
  }

  @Test
  public void testGetConnection() throws RepositoryException {
    final RepositoryConnection connection = repository.getConnection();
    assertNotNull(connection);
  }

  @Test
  public void testGetValueFactory() {
    final ValueFactory factory = repository.getValueFactory();
    assertNotNull(factory);
  }

  @Test
  public void testIsWritable() throws RepositoryException {
    assertTrue(repository.isWritable());
  }

  @Test
  public void testShutDown() throws RepositoryException {
    repository.shutDown();
  }
}
