package com.dydra.rpc;

import com.dydra.annotation.*;

/**
 * Represents a Dydra.com RPC exception.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rpc
 */
public class RPCException extends RuntimeException {
  /**
   * The underlying RPC error that resulted in this exception.
   */
  public final RPCError error;

  /**
   * Constructs an RPC exception with <code>null</code> as its detail
   * message.
   */
  public RPCException() {
    super();
    this.error = null;
  }

  /**
   * Constructs an RPC exception with the given underlying RPC error.
   *
   * @param  error the underlying RPC error
   */
  public RPCException(@Nullable final RPCError error) {
    super((error != null) ? error.toString() : null);
    this.error = error;
  }

  /**
   * Constructs an RPC exception with the given detail message.
   *
   * @param  message the detail message
   */
  public RPCException(@Nullable final String message) {
    super(message);
    this.error = null;
  }

  /**
   * Constructs an RPC exception with the given detail message and cause.
   *
   * Note that the detail message associated with <code>cause</code> is
   * <i>not</i> automatically incorporated in this exception's detail
   * message.
   *
   * @param  message the detail message
   * @param  cause   the cause
   */
  public RPCException(@Nullable final String message,
                      @Nullable final Throwable cause) {
    super(message, cause);
    this.error = null;
  }

  /**
   * Constructs an RPC exception with the given cause.
   *
   * @param  cause   the cause
   */
  public RPCException(@Nullable final Throwable throwable) {
    super(throwable);
    this.error = null;
  }
}
