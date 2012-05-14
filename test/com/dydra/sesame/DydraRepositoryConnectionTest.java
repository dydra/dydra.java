/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

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
  public void test() throws RepositoryException {
    // TODO
  }
}
