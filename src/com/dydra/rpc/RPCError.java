package com.dydra.rpc;

import com.dydra.annotation.*;
import java.util.Map;

/**
 * Represents a Dydra.com RPC error.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCError extends RPCObject {
  /**
   * The error.
   */
  public final Map<String, Object> error;

  /**
   * Constructs an RPC error.
   *
   * @param  error ...
   */
  public RPCError(@NotNull final Map<String, Object> error) {
    super(RPCClient.VERSION, 1);

    if (error == null)
      throw new NullPointerException("error cannot be null");

    this.error = error;
  }
}
