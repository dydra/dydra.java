import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;

public class construct {
  public static final String ENDPOINT = "http://dydra.com/jhacker/foaf/sparql";
  public static final String QUERY    = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o} LIMIT 10";

  public static void main(String[] args) {
    Query query = QueryFactory.create(QUERY);
    QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, query);
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
