package com.dydra;

/**
 * Represents a Dydra.com job.
 *
 * @see http://docs.dydra.com/sdk/java
 */
public class Job extends Resource {
  public static final String STATUS_UNKNOWN   = "unknown";
  public static final String STATUS_PENDING   = "pending";
  public static final String STATUS_RUNNING   = "running";
  public static final String STATUS_COMPLETED = "completed";
  public static final String STATUS_FAILED    = "failed";
  public static final String STATUS_ABORTED   = "aborted";

  /**
   * The job UUID.
   */
  public String uuid;

  /**
   * @param name the job UUID
   */
  public Job(final String uuid) {
    super(uuid); // FIXME
    this.uuid = uuid;
  }

  /**
   * @return true if the job is pending, false otherwise
   */
  public boolean isPending() {
    return this.getStatus() == STATUS_PENDING;
  }

  public boolean isRunning() {
    return this.getStatus() == STATUS_RUNNING;
  }

  public boolean isCompleted() {
    return this.getStatus() == STATUS_COMPLETED;
  }

  public boolean isFailed() {
    return this.getStatus() == STATUS_FAILED;
  }

  public boolean isAborted() {
    return this.getStatus() == STATUS_ABORTED;
  }

  public boolean isDone() {
    return false; // TODO
  }

  public String getStatus() {
    return STATUS_UNKNOWN; // TODO
  }

  /**
   * @return this
   */
  public Job abort() {
    return this; // TODO
  }

  /**
   * @return this
   */
  public Job waitUntilDone() {
    return this.waitUntilDone(-1, 0.5); // seconds
  }

  /**
   * @return this
   */
  public Job waitUntilDone(double timeout, double interval) {
    return this; // TODO
  }

  /**
   * @return the job UUID
   */
  @Override
  public String toString() {
    return this.uuid;
  }
}
