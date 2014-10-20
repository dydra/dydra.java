/* This is free and unencumbered software released into the public domain. */

package com.dydra.sesame;

import com.dydra.annotation.*;

import org.openrdf.query.resultio.BooleanQueryResultFormat;
import org.openrdf.query.resultio.TupleQueryResultFormat;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;
import org.openrdf.repository.manager.SystemRepository;
import org.openrdf.rio.RDFFormat;

public class DydraRepository extends HTTPRepository implements Repository {
  protected final String id;

  public DydraRepository(@NotNull final String serverURL,
                         @NotNull final String repositoryID) {
    super(serverURL, repositoryID);
    this.id = repositoryID;
    this.setPreferredTupleQueryResultFormat(TupleQueryResultFormat.SPARQL);
    this.setPreferredRDFFormat(RDFFormat.TURTLE);
    //this.getHTTPClient().setPreferredBooleanQueryResultFormat(BooleanQueryResultFormat.SPARQL);
  }

  @Override @NotNull
  public RepositoryConnection getConnection()
      throws RepositoryException {
    return super.getConnection(); // TODO
  }

  @Override
  public boolean isWritable()
      throws RepositoryException {
    // All repositories except for the SYSTEM repository are writable:
    return !SystemRepository.ID.equals(this.id);
  }
}
