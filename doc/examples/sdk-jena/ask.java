import com.dydra.Repository;
import com.hp.hpl.jena.query.QueryExecution;

public class ask {
  public static final String REPOSITORY = "jhacker/foaf";
  public static final String QUERY      = "ASK WHERE {?s ?p ?o}";

  public static void main(String[] args) {
    QueryExecution exec = new Repository(REPOSITORY).prepareQueryExecution(QUERY);
    System.out.println("# " + exec + "\n");

    try {
      System.out.println(exec.execAsk());
    }
    finally {
      exec.close();
    }
  }
}
