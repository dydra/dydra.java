package com.dydra.rpc;

import com.dydra.annotation.*;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

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
  public Object result;

  /**
   * Constructs an RPC response.
   */
  public RPCResponse() {
    super(RPCClient.VERSION, 1);
  }

  /**
   * Constructs an RPC response.
   *
   * @param  result ...
   */
  public RPCResponse(@Nullable final Object result) {
    super(RPCClient.VERSION, 1);

    this.result = result;
  }

  /**
   * Parses a JSON string to construct an RPC response.
   *
   * @param  json the JSON string
   * @return an RPC object
   */
  public static RPCResponse parse(String json) {
    try {
      return (new ObjectMapper()).readValue(json, RPCResponse.class);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
