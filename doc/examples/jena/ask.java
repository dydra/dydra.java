import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;

public class ask {
  public static final String ENDPOINT = "http://dydra.com/jhacker/foaf/sparql";
  public static final String QUERY    = "ASK WHERE {?s ?p ?o}";

  public static void main(String[] args) {
    Query query = QueryFactory.create(QUERY);
    QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, query);
    System.out.println(exec);

    try {
      System.out.println(exec.execAsk());
    }
    finally {
      exec.close();
    }
  }
}
