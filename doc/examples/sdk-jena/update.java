import com.dydra.Repository;
import com.dydra.Session;
import com.hp.hpl.jena.sparql.modify.request.UpdateClear;
import com.hp.hpl.jena.update.UpdateProcessor;

public class update {
  public static final String REPOSITORY = "jhacker/foaf";     // TODO: change this to your own repository

  public static void main(String[] args) {
    Session session = new Session("username", "password");    // TODO: change these to your own credentials

    UpdateProcessor update = new Repository(REPOSITORY, session)
      .prepareUpdate(new UpdateClear("http://example.org/")); // CLEAR GRAPH <http://example.org/>

    update.execute();
  }
}
