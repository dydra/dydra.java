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

  private DydraRepositoryManager manager;

  @Before
  public void setUp() {
    this.manager = new DydraRepositoryManager("jhacker");
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
    assertEquals("http://api.dydra.com/sesame2/jhacker/", this.manager.getServerURL());
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
