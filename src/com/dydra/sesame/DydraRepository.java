/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.annotation.*;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class DydraRepository extends HTTPRepository implements Repository {
  public DydraRepository(@NotNull final String serverURL,
                         @NotNull final String repositoryID) {
    super(serverURL, repositoryID);
  }

  public DydraRepository(@NotNull final String repositoryURL) {
    super(repositoryURL);
  }
}
