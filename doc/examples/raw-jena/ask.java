import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;

public class ask {
  public static final String ENDPOINT = "http://api.dydra.com/jhacker/foaf/sparql";
  public static final String QUERY    = "ASK WHERE {?s ?p ?o}";

  public static void main(String[] args) {
    QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, QUERY);
    System.out.println("# " + exec + "\n");

    try {
      System.out.println(exec.execAsk());
    }
    finally {
      exec.close();
    }
  }
}
