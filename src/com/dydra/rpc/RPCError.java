package com.dydra.rpc;

import com.dydra.annotation.*;
import java.io.IOException;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

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
  public Map<String, Object> error;

  /**
   * Constructs an RPC error.
   */
  public RPCError() {
    super(RPCClient.VERSION, 1);
  }

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

  /**
   * Parses a JSON string to construct an RPC error.
   *
   * @param  json the JSON string
   * @return an RPC object
   */
  public static RPCError parse(String json) {
    try {
      return (new ObjectMapper()).readValue(json, RPCError.class);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
