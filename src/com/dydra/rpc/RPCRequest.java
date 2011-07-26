package com.dydra.rpc;

import com.dydra.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Dydra.com RPC request.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCRequest extends RPCObject {
  /**
   * The request method.
   */
  public final String method;

  /**
   * The request arguments.
   */
  public final List<Object> params;

  /**
   * Constructs an RPC request for the given method.
   *
   * @param  method
   */
  public RPCRequest(@NotNull final String method) {
    this(method, (List<Object>)null);
  }

  /**
   * Constructs an RPC request for the given method with the given
   * arguments.
   *
   * @param  method ...
   * @param  args ...
   */
  public RPCRequest(@NotNull final String method, Object... args) {
    this(method, (List<Object>)null);

    for (Object arg : args) {
      this.params.add(arg);
    }
  }

  /**
   * Constructs an RPC request for the given method with the given
   * arguments.
   *
   * @param  method ...
   * @param  args   ...
   */
  public RPCRequest(@NotNull final String method, @Nullable final List<Object> args) {
    super(RPCClient.VERSION, 1);

    if (method == null)
      throw new NullPointerException("method cannot be null");

    this.method = method;
    this.params = (args != null) ? args : new ArrayList<Object>(0);
  }
}
