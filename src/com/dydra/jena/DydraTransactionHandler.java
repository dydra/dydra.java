/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.TransactionHandler;
import com.hp.hpl.jena.graph.impl.TransactionHandlerBase;
import com.hp.hpl.jena.shared.Command;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/TransactionHandler.html
 */
public class DydraTransactionHandler extends TransactionHandlerBase implements TransactionHandler {
  protected final DydraGraph graph;

  /**
   * @throws NullPointerException if <code>graph</code> is null
   */
  public DydraTransactionHandler(@NotNull final DydraGraph graph) {
    super();

    if (graph == null)
      throw new NullPointerException("graph cannot be null");

    this.graph = graph;
  }

  @Override
  public void abort() {
    throw new UnsupportedOperationException("not implemented"); // TODO
  }

  @Override
  public void begin() {
    throw new UnsupportedOperationException("not implemented"); // TODO
  }

  @Override
  public void commit() {
    throw new UnsupportedOperationException("not implemented"); // TODO
  }

  @Override
  public Object executeInTransaction(@NotNull final Command command) {
    if (command == null)
      throw new NullPointerException("command cannot be null");

    return super.executeInTransaction(command);
  }

  @Override
  public boolean transactionsSupported() {
    return false; // TODO
  }
}
