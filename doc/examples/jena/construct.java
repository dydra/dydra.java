import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.Model;

public class construct {
  public static final String ENDPOINT = "http://api.dydra.com/jhacker/foaf/sparql";
  public static final String QUERY    = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o} LIMIT 10";

  public static void main(String[] args) {
    QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, QUERY);
    System.out.println("# " + exec + "\n");

    try {
      Model data = exec.execConstruct();
      data.write(System.out, "TURTLE");
    }
    finally {
      exec.close();
    }
  }
}
