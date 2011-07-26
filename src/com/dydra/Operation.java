/* This is free and unencumbered software released into the public domain. */

package com.dydra;

import com.dydra.annotation.*;

/**
 * Represents a Dydra.com operation.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public class Operation extends Resource {
  public static final String STATUS_UNKNOWN   = "unknown";
  public static final String STATUS_PENDING   = "pending";
  public static final String STATUS_RUNNING   = "running";
  public static final String STATUS_COMPLETED = "completed";
  public static final String STATUS_FAILED    = "failed";
  public static final String STATUS_ABORTED   = "aborted";

  /**
   * The operation UUID.
   */
  public final String uuid;

  /**
   * Constructs an operation instance.
   *
   * @param uuid    a valid operation UUID
   * @param session
   */
  public Operation(@NotNull final String uuid, @NotNull final Session session) {
    super(uuid, session); // FIXME
    this.uuid = uuid;
  }

  /**
   * Returns a string representation of the operation UUID.
   *
   * @return the operation UUID
   */
  @Override @NotNull
  public String toString() {
    return this.uuid;
  }

  /**
   * Returns `true` if this operation is currently pending to run.
   */
  public boolean isPending() {
    return this.getStatus() == STATUS_PENDING;
  }

  /**
   * Returns `true` if this operation is currently running.
   */
  public boolean isRunning() {
    return this.getStatus() == STATUS_RUNNING;
  }

  /**
   * Returns `true` if this operation has already completed.
   */
  public boolean isCompleted() {
    return this.getStatus() == STATUS_COMPLETED;
  }

  /**
   * Returns `true` if this operation failed for some reason.
   */
  public boolean isFailed() {
    return this.getStatus() == STATUS_FAILED;
  }

  /**
   * Returns `true` if this operation was aborted for any reason.
   */
  public boolean isAborted() {
    return this.getStatus() == STATUS_ABORTED;
  }

  /**
   * Returns `true` if this operation has completed or was aborted, and
   * `false` if it's currently pending or running.
   */
  public boolean isDone() {
    return false; // TODO
  }

  /**
   * Returns the current status of this operation.
   */
  @NotNull
  public String getStatus() {
    return STATUS_UNKNOWN; // TODO
  }

  /**
   * Waits until this operation is done.
   *
   * @return this
   */
  @NotNull
  public Operation waitUntilDone() {
    return this.waitUntilDone(-1);
  }

  /**
   * Waits until this operation is done.
   *
   * @param  timeout
   * @return this
   */
  @NotNull
  public Operation waitUntilDone(double timeout) {
    // TODO
    return this;
  }

  /**
   * Aborts this operation if it is currently pending or running.
   *
   * @return this
   */
  @NotNull
  public Operation abort() {
    // TODO
    return this;
  }
}
