package com.dydra;

import com.dydra.annotation.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a Dydra.com resource.
 *
 * This is the base class for all classes that represent dereferenceable
 * HTTP resources on Dydra.com.
 *
 * @see http://docs.dydra.com/sdk/java
 * @see http://docs.dydra.com/api/rest
 */
public class Resource {
  /**
   * The resource path, relative to http://api.dydra.com/.
   */
  public String path;

  /**
   * Constructs...
   *
   * @param name a root-relative resource path, without the initial slash
   */
  public Resource(@NotNull final String path) {
    this.path = path;
  }

  /**
   * Checks whether this resource exists.
   *
   * @return true if the resource exists, false otherwise
   */
  public boolean exists() {
    return false; // TODO: perform an HTTP HEAD request
  }

  /**
   * Returns the URL string for this resource.
   *
   * @return a URL string
   */
  @Override @NotNull
  public String toString() {
    return Client.getPublicURL(this.path);
  }

  /**
   * Returns the URL for this resource.
   *
   * @return a URL instance
   */
  @NotNull
  public URL toURL() {
    try {
      return new URL(toString());
    }
    catch (MalformedURLException e) {
      throw new RuntimeException(e); // should never get here
    }
  }
}
