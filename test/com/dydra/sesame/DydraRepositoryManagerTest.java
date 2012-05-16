/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.test.*;
import org.junit.*;
import org.junit.experimental.categories.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryReadOnlyException;
import org.openrdf.repository.config.RepositoryConfig;
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
  public void setUp() throws RepositoryException {
    this.properties     = System.getProperties();
    this.accountName    = properties.getProperty("com.dydra.sesame.account", "jhacker");
    this.password       = properties.getProperty("com.dydra.sesame.password", null);
    this.repositoryName = properties.getProperty("com.dydra.sesame.repository", "test");
    this.serverURL      = properties.getProperty("com.dydra.sesame.url",
      "http://api.dydra.com/sesame2") + "/" + this.accountName + "/";
    this.repositoryURL  = this.serverURL + "repositories/" + this.repositoryName;
    this.manager        = new DydraRepositoryManager(accountName);
    this.manager.initialize();
  }

  @After
  public void tearDown() {
    this.manager = null;
  }

  @Test(expected=RepositoryReadOnlyException.class) @Category(Mutative.class)
  public void testAddRepositoryConfig()
      throws RepositoryException, RepositoryConfigException {
    manager.addRepositoryConfig(new RepositoryConfig(this.repositoryName));
  }

  @Test
  public void testGetAllRepositoryInfos()
      throws RepositoryException {
    final Collection<RepositoryInfo> infos = manager.getAllRepositoryInfos(true);
    assertNotNull(infos);
    assertFalse(infos.isEmpty());
  }

  @Test
  public void testGetNewRepositoryID()
      throws RepositoryException, RepositoryConfigException {
    final String repositoryID = manager.getNewRepositoryID(this.repositoryName);
    assertNotNull(repositoryID);
    assertFalse(repositoryID.equals(this.repositoryName));
  }

  @Test
  public void testGetRepository()
      throws RepositoryException, RepositoryConfigException {
    final Repository repository = manager.getRepository(this.repositoryName);
    assertNotNull(repository);
    assertSame(repository, manager.getRepository(this.repositoryName));
    assertNull(manager.getRepository(UUID.randomUUID().toString()));
  }

  @Test
  public void testGetRepositoryIDs()
      throws RepositoryException {
    final Set<String> repositoryIDs = manager.getRepositoryIDs();
    assertNotNull(repositoryIDs);
    assertFalse(repositoryIDs.isEmpty());
    assertTrue(repositoryIDs.contains(SystemRepository.ID));
    assertTrue(repositoryIDs.contains(this.repositoryName));
  }

  @Test
  public void testGetRepositoryInfo()
      throws RepositoryException {
    final RepositoryInfo info = manager.getRepositoryInfo(this.repositoryName);
    assertNotNull(info);
    assertEquals(this.repositoryName, info.getId());
    assertEquals(this.repositoryURL, info.getLocation().toString());
    assertNotNull(info.getDescription());
    assertTrue(info.isReadable());
  }

  @Test
  public void testGetRepositoryConfig()
      throws RepositoryException, RepositoryConfigException {
    final RepositoryConfig config = manager.getRepositoryConfig(this.repositoryName);
    assertNotNull(config);
    assertNotNull(config.getID());
    assertNotNull(config.getTitle());
    assertNull(manager.getRepositoryConfig(UUID.randomUUID().toString()));
  }

  @Test
  public void testGetServerURL() {
    assertEquals(serverURL, manager.getServerURL());
  }

  @Test
  public void testGetSystemRepository() {
    final Repository repository = manager.getSystemRepository();
    assertNotNull(repository);
    assertSame(repository, manager.getSystemRepository());
  }

  @Test
  public void testHasRepositoryConfig()
      throws RepositoryException, RepositoryConfigException {
    assertTrue(manager.hasRepositoryConfig(SystemRepository.ID));
    assertTrue(manager.hasRepositoryConfig(this.repositoryName));
    assertFalse(manager.hasRepositoryConfig(UUID.randomUUID().toString()));
  }

  @Test(expected=RepositoryReadOnlyException.class) @Category(Mutative.class)
  public void testRemoveRepositoryConfig()
      throws RepositoryException, RepositoryConfigException {
    manager.removeRepositoryConfig(this.repositoryName);
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

  @Test
  public void testShutDown() {
    manager.shutDown();
  }
}
