package iae.service;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Patients;
import fr.isima.rdvmed.entity.Rdv;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.Assert.fail;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RdvPlusTest {

	private final String URL = "http://localhost:8080/rdvMed/ws/rdv";

	private Client client;
        
        private static short idCreneau2 = 0;
        private static short idCreneau3 = 0;

        private static short idMedecin2= 0;

	@Before
	public void setup() {
            client = ClientBuilder.newClient();
	}

        /**
         * Test creation of a rendez-vous
         */
	@Test
        public void libres() {
            // Creation de rdv
            WebTarget target = client.target("http://localhost:8080/rdvMed/ws/medecins");

            Medecins m = new Medecins();

            Response response = target.request().post(
                            Entity.entity(m, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("[Medecin] RESPONSE STATUS" + response.getStatus());
            }else{
                Medecins created = response.readEntity(Medecins.class);
                idMedecin2 = created.getId();
            }

            m.setId(idMedecin2);

            target = client.target("http://localhost:8080/rdvMed/ws/creneaux");

            Calendar cal2 = new GregorianCalendar(2017,03,12,10,0);

            Creneaux c1 = new Creneaux();
            c1.setDebut(cal2.getTime());
            cal2.add(Calendar.HOUR, 2);
            c1.setFin(cal2.getTime());
            c1.setMedecin(new Medecins(idMedecin2));

            response = target.request().post(
                            Entity.entity(c1, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("[Creneau] RESPONSE STATUS" + response.getStatus());
            }else{
                Creneaux created = response.readEntity(Creneaux.class);
                idCreneau2 = created.getId();
            }

            Creneaux c2 = new Creneaux();
            cal2.add(Calendar.HOUR, 2);
            c2.setDebut(cal2.getTime());
            cal2.add(Calendar.HOUR, 2);
            c2.setFin(cal2.getTime());
            c2.setMedecin(new Medecins(idMedecin2));

            response = target.request().post(
                            Entity.entity(c2, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("[Creneau] RESPONSE STATUS" + response.getStatus());
            }else{
                Creneaux created = response.readEntity(Creneaux.class);
                idCreneau3 = created.getId();
            }

            target = client.target("http://localhost:8080/rdvMed/ws/rdv/libres/2017/04/12");

            response = target.request().post(
                            Entity.entity(m, MediaType.APPLICATION_JSON));

            if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
            }else{
                List<Creneaux> created = response.readEntity(new GenericType<List<Creneaux>>() {});

                if (created.get(0).getDebut().getHours() != 8){
                    fail("NEW CRENEAUX 1 STARTS AT "+ created.get(0).getDebut().getHours());
                }
                if (created.get(0).getFin().getHours() != 10){
                    fail("NEW CRENEAUX 1 ENDS AT "+ created.get(0).getDebut().getHours());
                }

                if (created.get(1).getDebut().getHours() != 12){
                    fail("NEW CRENEAUX 2 STARTS AT "+ created.get(1).getDebut().getHours());
                }
                if (created.get(1).getFin().getHours() != 14){
                    fail("NEW CRENEAUX 2 ENDS AT "+ created.get(1).getDebut().getHours());
                }

                if (created.get(2).getDebut().getHours() != 16){
                    fail("NEW CRENEAUX 3 STARTS AT "+ created.get(2).getDebut().getHours());
                }
                if (created.get(2).getFin().getHours() != 20){
                    fail("NEW CRENEAUX 3 ENDS AT "+ created.get(2).getDebut().getHours());
                }
            }

            target = client.target("http://localhost:8080/rdvMed/ws/creneaux/" + idCreneau2);
            response = target.request().delete();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }

            target = client.target("http://localhost:8080/rdvMed/ws/creneaux/" + idCreneau3);
            response = target.request().delete();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }

               target = client.target("http://localhost:8080/rdvMed/ws/medecins/" + idMedecin2);
            response = target.request().delete();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }
        
    }
        
    @Test
    public void chevauchementRdv() {
        short idMedecin = 0;
        short idPatient = 0;
        short idCreneau = 0;
        
        short idRdv1 = 0;
        short idRdv2 = 0;
        
        Calendar cal = new GregorianCalendar(2017,03,12,10,0);
        
        WebTarget target = client.target("http://localhost:8080/rdvMed/ws/medecins");

        Medecins m = new Medecins();

        Response response = target.request().post(
                        Entity.entity(m, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("[Medecin] RESPONSE STATUS" + response.getStatus());
        }else{
            Medecins created = response.readEntity(Medecins.class);
            idMedecin = created.getId();
        }

        target = client.target("http://localhost:8080/rdvMed/ws/patients");

        Patients p = new Patients();

        response = target.request().post(
                        Entity.entity(p, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("[Patient] RESPONSE STATUS" + response.getStatus());
        }else{
            Patients created = response.readEntity(Patients.class);
            idPatient = created.getId();
        }


        target = client.target("http://localhost:8080/rdvMed/ws/creneaux");

        Creneaux c = new Creneaux();
        c.setDebut(cal.getTime());
        cal.add(Calendar.HOUR,2);
        c.setFin(cal.getTime());
        c.setMedecin(new Medecins(idMedecin));

        target = client.target(URL);

        Rdv r1 = new Rdv();
        r1.setDate(cal.getTime());
        c.setId(idCreneau);
        r1.setCreneau(c);
        r1.setPatient(new Patients(idPatient));

        response = target.request().post(
                        Entity.entity(r1, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("RESPONSE STATUS" + response.getStatus());
        }else{
            Rdv created = response.readEntity(Rdv.class);
            idRdv1 = created.getId();
            idCreneau = created.getCreneau().getId();
        }
        
        Rdv r2 = new Rdv();
        r2.setDate(cal.getTime());
        c.setId(idCreneau);
        r2.setCreneau(c);
        r2.setPatient(new Patients(idPatient));

        response = target.request().post(
                        Entity.entity(r1, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 204) {
            fail("RESPONSE STATUS" + response.getStatus() + " SHOULD NOT RECEIVE IT");
        }
        
        
        target = client.target(URL + "/" + idRdv1);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }

        target = client.target("http://localhost:8080/rdvMed/ws/patients/" + idPatient);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }

        target = client.target("http://localhost:8080/rdvMed/ws/medecins/" + idMedecin);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }
    }


}
