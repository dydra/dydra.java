/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/shared/PrefixMapping.html
 */
public class DydraPrefixMapping extends PrefixMappingImpl implements PrefixMapping {
  protected final DydraGraph graph;

  /**
   * @throws NullPointerException if <code>graph</code> is null
   */
  public DydraPrefixMapping(@NotNull final DydraGraph graph) {
    super();

    if (graph == null)
      throw new NullPointerException("graph cannot be null");

    this.graph = graph;
  }

  // TODO
}
