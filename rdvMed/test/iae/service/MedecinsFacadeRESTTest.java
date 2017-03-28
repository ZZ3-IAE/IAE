package iae.service;

import fr.isima.rdvmed.Medecins;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.fail;


public class MedecinsFacadeRESTTest {

	private final String URL = "http://localhost:8080/rdvMed/ws/medecins";

	private Client client;
        
        private short id;
        private String CLEF;


	@Before
	public void setup() {
            client = ClientBuilder.newClient();
            CLEF = "TEST";
            id=1;
	}

	@Test
	public void create() {
		WebTarget target = client.target(URL);

		Medecins m = new Medecins();
                m.setNom(CLEF);
                
		Response response = target.request().post(
				Entity.entity(m, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}else{
                    Medecins created = (Medecins)response.getEntity();
                    id = created.getId();
                }
                
	}

	@Test
	public void find() {
		WebTarget target = client.target(URL + "/" + id);
		Response response = target.request().get();
		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}

	}
        
        @Test
	public void list() {
		WebTarget target = client.target(URL);
		Response response = target.request().get();
		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}
	
	@Test
	public void count() {
		WebTarget target = client.target(URL + "/count");
		Response response = target.request().get();
		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}

	}
	
	@Test
	public void findRange() {
		WebTarget target = client.target(URL + "/1/3");
		Response response = target.request().get();
		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}

	}

	@Test
	public void update() {
		WebTarget target = client.target(URL + "/" + id);

		Medecins m = new Medecins((short)0);
		Response response = target.request().put(
				Entity.entity(m, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 204) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}

	@Test
	public void delete() {
		WebTarget target = client.target(URL + "/" + id);
		Response response = target.request().delete();
		if (response.getStatus() != 204) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}
}
