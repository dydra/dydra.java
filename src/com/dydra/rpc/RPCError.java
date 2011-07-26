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
  public static class Error {
    public int code;
    public String message;
    public Object data;
    public Error() {}
  }

  /**
   * The error detail.
   */
  public Error error;

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
  public RPCError(@NotNull final Error error) {
    super(RPCClient.VERSION, 1);

    if (error == null)
      throw new NullPointerException("error cannot be null");

    this.error = error;
  }

  /**
   * Returns the error code.
   *
   * @return an integer, or <code>null</code>
   */
  @Nullable
  public Integer getCode() {
    return (error != null) ? error.code : null;
  }

  /**
   * Returns a short description of the error.
   *
   * @return a string, or <code>null</code>
   */
  @Nullable
  public String getMessage() {
    return (error != null) ? error.message : null;
  }

  /**
   * Returns any additional data associated with the error.
   *
   * @return an arbitrary object, or <code>null</code>
   */
  @Nullable
  public Object getData() {
    return (error != null) ? error.data : null;
  }
}
