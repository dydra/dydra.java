import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class select {
  public static final String ENDPOINT = "http://dydra.com/jhacker/foaf/sparql";
  public static final String QUERY    = "SELECT * WHERE {?s ?p ?o} LIMIT 10";

  public static void main(String[] args) {
    Query query = QueryFactory.create(QUERY);
    QueryExecution exec = QueryExecutionFactory.sparqlService(ENDPOINT, query);
    System.out.println(exec);

    try {
      ResultSet rs = exec.execSelect();
      if (rs.hasNext()) {
        System.out.println(ResultSetFormatter.asText(rs));
      }
    }
    finally {
      exec.close();
    }
  }
}
