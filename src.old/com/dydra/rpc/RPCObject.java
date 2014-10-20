/* This is free and unencumbered software released into the public domain. */

package com.dydra.rpc;

import com.dydra.annotation.*;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Base class for RPC requests, responses, and errors.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public abstract class RPCObject {
  /**
   * The protocol version.
   */
  public final String jsonrpc;

  /**
   * The request identifier.
   */
  public final int id;

  /**
   * @param  jsonrpc ...
   * @param  ide     ...
   */
  public RPCObject(@NotNull final String jsonrpc, final int id) {
    this.jsonrpc = jsonrpc;
    this.id      = id;
  }

  /**
   * Returns the string representation of this RPC object.
   *
   * @return a JSON string
   */
  @Override @NotNull
  public String toString() {
    return toJSON();
  }

  /**
   * Returns the JSON string representation of this RPC object.
   *
   * @return a JSON string
   */
  @NotNull
  public String toJSON() {
    try {
      return (new ObjectMapper()).writeValueAsString(this);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
