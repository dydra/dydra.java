import com.dydra.Repository;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.rdf.model.Model;

public class construct {
  public static final String REPOSITORY = "jhacker/foaf";
  public static final String QUERY      = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o} LIMIT 10";

  public static void main(String[] args) {
    QueryExecution exec = new Repository(REPOSITORY).prepareQueryExecution(QUERY);
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
