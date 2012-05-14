/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class DydraRepositoryTest {
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(DydraRepositoryTest.class.getName());
  }

  private DydraRepository repository;

  @Before
  public void setUp() {
    this.repository = null; // TODO
  }

  @After
  public void tearDown() {
    this.repository = null;
  }

  @Test
  public void test() throws RepositoryException {
    // TODO
  }
}
