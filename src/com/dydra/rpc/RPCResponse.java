package com.dydra.rpc;

import com.dydra.annotation.*;

/**
 * Represents a Dydra.com RPC response.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCResponse extends RPCObject {
  /**
   * The result.
   */
  public final Object result;

  /**
   * Constructs an RPC response.
   *
   * @param  result ...
   */
  public RPCResponse(@Nullable final Object result) {
    super(RPCClient.VERSION, 1);

    this.result = result;
  }
}
