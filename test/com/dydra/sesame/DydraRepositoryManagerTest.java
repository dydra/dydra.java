/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.manager.RepositoryInfo;
import org.openrdf.repository.manager.SystemRepository;

public class DydraRepositoryManagerTest {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryManagerTest.class.getName());
  }

  private Properties properties;
  private String accountName;
  private String password;
  private String repositoryName;
  private String serverURL;
  private String repositoryURL;
  private DydraRepositoryManager manager;

  @Before
  public void setUp() {
    this.properties     = System.getProperties();
    this.accountName    = properties.getProperty("com.dydra.sesame.account", "jhacker");
    this.password       = properties.getProperty("com.dydra.sesame.password", null);
    this.repositoryName = properties.getProperty("com.dydra.sesame.repository", "test");
    this.serverURL      = properties.getProperty("com.dydra.sesame.url",
      "http://api.dydra.com/sesame2") + "/" + this.accountName + "/";
    this.repositoryURL  = this.serverURL + "repositories/" + this.repositoryName;
    this.manager        = new DydraRepositoryManager(accountName);
  }

  @After
  public void tearDown() {
    this.manager = null;
  }

  @Test
  public void testInitialize() throws RepositoryException {
    //manager.initialize();
  }

  @Test
  public void testGetAllRepositoryInfos() throws RepositoryException {
    final Collection<RepositoryInfo> infos = manager.getAllRepositoryInfos(true);
    assertNotNull(infos);
    assertFalse(infos.isEmpty());
  }

  @Test
  public void testGetNewRepositoryID()
      throws RepositoryException, RepositoryConfigException {
    //System.out.println(manager.getNewRepositoryID(this.repositoryName));
  }

  @Test
  public void testGetRepositoryIDs() throws RepositoryException {
    final Set<String> repositoryIDs = manager.getRepositoryIDs();
    assertNotNull(repositoryIDs);
    assertFalse(repositoryIDs.isEmpty());
    assertTrue(repositoryIDs.contains(SystemRepository.ID));
    assertTrue(repositoryIDs.contains(this.repositoryName));
  }

  @Test
  public void testGetRepositoryInfo() throws RepositoryException {
    final RepositoryInfo info = manager.getRepositoryInfo(this.repositoryName);
    assertNotNull(info);
    assertEquals(this.repositoryName, info.getId());
    assertEquals(this.repositoryURL, info.getLocation().toString());
    assertNotNull(info.getDescription());
    assertTrue(info.isReadable());
  }

  @Test
  public void testGetRepositoryConfig() {
    // TODO
  }

  @Test
  public void testGetServerURL() {
    assertEquals(serverURL, manager.getServerURL());
  }

  @Test
  public void testHasRepositoryConfig() {
    //assertTrue(manager.hasRepositoryConfig(this.repositoryName));
  }

  @Test
  public void testSetPassword() {
    assertFalse(manager.isAuthenticated());
    if (this.password != null) {
      manager.setPassword(this.password);
      assertTrue(manager.isAuthenticated());
    }
    manager.setPassword(null);
    assertFalse(manager.isAuthenticated());
  }

  @Test
  public void testSetUsernameAndPassword() {
    assertFalse(manager.isAuthenticated());
    if (this.password != null) {
      manager.setUsernameAndPassword(this.accountName, this.password);
      assertTrue(manager.isAuthenticated());
    }
    manager.setUsernameAndPassword(this.accountName, null);
    assertFalse(manager.isAuthenticated());
  }
}
