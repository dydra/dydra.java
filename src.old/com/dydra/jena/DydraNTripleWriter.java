/* This is free and unencumbered software released into the public domain. */

package com.dydra.jena;

import com.dydra.annotation.*;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Node_Variable;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.impl.NTripleWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @see http://docs.dydra.com/sdk/java/jena
 * @see http://openjena.org/javadoc/com/hp/hpl/jena/rdf/model/RDFWriter.html
 */
public class DydraNTripleWriter extends NTripleWriter implements RDFWriter {
  @NotNull
  public static String formatQuery(@NotNull final String queryTemplate,
                                   @Nullable final Node... nodes) {
    if (nodes == null || nodes.length == 0) {
      return queryTemplate;
    }
    else {
      final String[] args = new String[nodes.length];
      for (int i = 0; i < nodes.length; i++) {
        args[i] = (nodes[i] != null) ? formatNode(nodes[i]) : "[]";
      }
      return String.format(queryTemplate, (Object[])args);
    }
  }

  @NotNull
  public static String formatNode(@NotNull final Node node) {
    if (node == null)
      throw new NullPointerException("node cannot be null");

    if (node instanceof Node_Variable) {
      return node.toString(); // special case for variables
    }

    final Model model = ModelFactory.createDefaultModel();
    try {
      final RDFNode rdfNode = model.getRDFNode(node);
      final StringWriter writer = new StringWriter();
      NTripleWriter.writeNode(rdfNode, new PrintWriter(writer));
      return writer.toString();
    } finally { model.close(); }
  }

  @NotNull
  public static String formatNode(@NotNull final RDFNode node) {
    if (node == null)
      throw new NullPointerException("node cannot be null");

    final StringWriter writer = new StringWriter();
    NTripleWriter.writeNode(node, new PrintWriter(writer));
    return writer.toString();
  }
}
