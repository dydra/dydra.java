import com.dydra.Repository;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class select {
  public static final String REPOSITORY = "jhacker/foaf";
  public static final String QUERY      = "SELECT * WHERE {?s ?p ?o} LIMIT 10";

  public static void main(String[] args) {
    QueryExecution exec = new Repository(REPOSITORY).prepareQueryExecution(QUERY);
    System.out.println("# " + exec + "\n");

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
