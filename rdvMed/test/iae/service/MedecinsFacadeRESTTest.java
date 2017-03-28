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
        
        private Date newDate;

	@Before
	public void setup() {
            client = ClientBuilder.newClient();
            newDate = new Date();
	}

	@Test
	public void create() {

		WebTarget target = client.target(URL);

		Medecins m = new Medecins((short)0);
		Response response = target.request().post(
				Entity.entity(m, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}

	@Test
	public void find() {
		String id = "0";
		WebTarget target = client.target(URL + "/find/" + id);
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
	public void update() {
		WebTarget target = client.target(URL);

		Medecins m = new Medecins((short)0);
		Response response = target.request().put(
				Entity.entity(m, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 204) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}

	@Test
	public void delete() {
		String id = "0";
		WebTarget target = client.target(URL+"/"+id);
		Response response = target.request().delete();
		if (response.getStatus() != 204) {
			fail("RESPONSE STATUS" + response.getStatus());
		}
	}
}