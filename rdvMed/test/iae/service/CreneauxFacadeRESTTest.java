package iae.service;

import fr.isima.rdvmed.entity.Creneaux;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.fail;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreneauxFacadeRESTTest {

	private final String URL = "http://localhost:8080/rdvMed/ws/creneaux";

	private Client client;
        
        private static short id = 0;
        private final Calendar cal = new GregorianCalendar();
        
	@Before
	public void setup() {
            client = ClientBuilder.newClient();
	}

	@Test
	public void create() {
            
            WebTarget target = client.target(URL);

            Creneaux c = new Creneaux();
            c.setDebut(cal.getTime());
            c.setFin(cal.getTime());
            
            Response response = target.request().post(
                            Entity.entity(c, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
            }else{
                Creneaux created = response.readEntity(Creneaux.class);
                id = created.getId();
            }
	}

	@Test
	public void find() {
		WebTarget target = client.target(URL + "/" + id);
		Response response = target.request().get();
		if (response.getStatus() != 204) {
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

		Creneaux c = new Creneaux();
                c.setDebut(cal.getTime());
                c.setFin(cal.getTime());
            
		Response response = target.request().put(
				Entity.entity(c, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200) {
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
