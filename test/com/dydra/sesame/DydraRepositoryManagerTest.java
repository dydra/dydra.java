/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

public class DydraRepositoryManagerTest {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryManagerTest.class.getName());
  }

  private Properties properties;
  private String accountName;
  private String password;
  private String serverURL;
  private DydraRepositoryManager manager;

  @Before
  public void setUp() {
    this.properties  = System.getProperties();
    this.accountName = properties.getProperty("com.dydra.sesame.account", "jhacker");
    this.password    = properties.getProperty("com.dydra.sesame.password", null);
    this.serverURL   = properties.getProperty("com.dydra.sesame.url",
      "http://api.dydra.com/sesame2") + "/" + this.accountName + "/";
    this.manager     = new DydraRepositoryManager(accountName);
  }

  @After
  public void tearDown() {
    this.manager = null;
  }

  @Test
  public void testInitialize() {
    // TODO
  }

  @Test
  public void testGetServerURL() {
    assertEquals(serverURL, manager.getServerURL());
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
  public void testGetNewRepositoryID() {
    // TODO
  }

  @Test
  public void testGetRepositoryIDs() {
    // TODO
  }

  @Test
  public void testGetRepositoryInfo() {
    // TODO
  }

  @Test
  public void testGetRepositoryConfig() {
    // TODO
  }
}
