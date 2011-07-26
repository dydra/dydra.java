package com.dydra.rpc;

import com.dydra.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Represents a Dydra.com RPC request.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCRequest {
  public static final String VERSION = "2.0";

  /**
   * The protocol version.
   */
  public final String jsonrpc;

  /**
   * The request identifier.
   */
  public final int id;

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
    this(method, null);
  }

  /**
   * Constructs an RPC request for the given method with the given
   * arguments.
   *
   * @param  method
   * @param  params
   */
  public RPCRequest(@NotNull final String method, @Nullable final List<Object> params) {
    if (method == null)
      throw new NullPointerException("method cannot be null");

    this.jsonrpc = VERSION;
    this.id      = 1;
    this.method  = method;
    this.params  = (params != null) ? params : new ArrayList<Object>(0);
  }

  /**
   * Returns the JSON string representation of this RPC request.
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
