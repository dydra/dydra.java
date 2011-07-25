/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;
import java.util.Iterator;

/**
 * Implementing this interface enables an object to be used in a
 * <code>foreach<code> statement for traversing nodes.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://en.wikipedia.org/wiki/Graph_traversal
 */
public interface Traversable extends Iterable<Node> {
  @NotNull public Iterator<Node> iterator();
}
