/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;

/**
 * An object for which a Dydra.com identifier can be computed.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public interface Identifiable {
  /**
   * Returns the identifier for this object.
   *
   * @return a Dydra.com identifier
   */
  @NotNull public Identifier toIdentifier();
}
