/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.Capabilities;
import com.hp.hpl.jena.graph.impl.AllCapabilities;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/graph/Capabilities.html
 */
public class DydraCapabilities extends AllCapabilities implements Capabilities {
  /**
   * Returns <code>true</code> to indicate that Dydra graphs compare literals
   * for equality by value rather than by lexical form.
   *
   * This method is explicitly overridden as it is a crucial characteristic
   * of Dydra's storage implementation.
   *
   * @return <code>true</code>
   */
  @Override
  public boolean handlesLiteralTyping() {
    return true;
  }
}
