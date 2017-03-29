package iae.service;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Patients;
import fr.isima.rdvmed.entity.Rdv;
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
public class TestLibres {

	private final String URL = "http://localhost:8080/rdvMed/ws/rdv";

	private Client client;
        
        private static short id = 1;
        private String CLEF;


	@Before
	public void setup() {
            client = ClientBuilder.newClient();
            CLEF = "TEST";
	}

        /**
         * Test creation of a rendez-vous
         */
	@Test
	public void create() {
            WebTarget target = client.target(URL);

            Medecins m = new Medecins();
            m.setId(Short.valueOf("1"));
            m.setPrenom("Gerard");
            m.setNom("Bouchard");
            Patients p = new Patients();
            p.setId(Short.valueOf("2"));
            p.setPrenom("Pipi");
            p.setNom("Chevalier");
            Creneaux c = new Creneaux();
            Calendar cal = new GregorianCalendar(2017,03,11,10,00);
            c.setMedecin(m);
            c.setDebut(cal.getTime());
            cal.set(2017, 03, 11, 10, 45);
            c.setFin(cal.getTime());
            Rdv r = new Rdv();
            r.setId(Short.valueOf("0"));
            r.setCreneau(c);
            r.setPatient(p);
            r.setDate(cal.getTime());

            Response response = target.request().post(
                            Entity.entity(r, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("RESPONSE STATUS " + response.getStatus() + response.getStatusInfo());
            } else {
                Rdv created = response.readEntity(Rdv.class);
                id = created.getId();
            }
	}

}
