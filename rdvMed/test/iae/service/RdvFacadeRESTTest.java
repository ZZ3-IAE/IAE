package iae.service;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Patients;
import fr.isima.rdvmed.entity.Rdv;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.After;
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
public class RdvFacadeRESTTest {

    private final String URL = "http://localhost:8080/rdvMed/ws/rdv";

    private Client client;

    private static short id = 0;
    private static short idCreneau1 = 0;
    
    private static short idMedecin1= 0;
    
    private static short idPatient1 = 0;

    private final Calendar cal = new GregorianCalendar();


    @Before
    public void setup() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void create() {
        WebTarget target = client.target("http://localhost:8080/rdvMed/ws/medecins");

        Medecins m = new Medecins();

        Response response = target.request().post(
                        Entity.entity(m, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("[Medecin] RESPONSE STATUS" + response.getStatus());
        }else{
            Medecins created = response.readEntity(Medecins.class);
            idMedecin1 = created.getId();
        }

        target = client.target("http://localhost:8080/rdvMed/ws/patients");

        Patients p = new Patients();

        response = target.request().post(
                        Entity.entity(p, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("[Patient] RESPONSE STATUS" + response.getStatus());
        }else{
            Patients created = response.readEntity(Patients.class);
            idPatient1 = created.getId();
        }


        target = client.target("http://localhost:8080/rdvMed/ws/creneaux");

        Creneaux c = new Creneaux();
        c.setDebut(cal.getTime());
        c.setFin(cal.getTime());
        c.setMedecin(new Medecins(idMedecin1));

        response = target.request().post(
                        Entity.entity(c, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("[Creneau] RESPONSE STATUS" + response.getStatus());
        }else{
            Creneaux created = response.readEntity(Creneaux.class);
            idCreneau1 = created.getId();
        }

        target = client.target(URL);

        Rdv r = new Rdv();
        r.setDate(cal.getTime());
        c.setId(idCreneau1);
        r.setCreneau(c);
        r.setPatient(new Patients(idPatient1));

        response = target.request().post(
                        Entity.entity(r, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 200) {
            fail("RESPONSE STATUS" + response.getStatus());
        }else{
            Rdv created = response.readEntity(Rdv.class);
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
            Creneaux c = new Creneaux(idCreneau1);
            c.setDebut(cal.getTime());
            c.setFin(cal.getTime());
            c.setMedecin(new Medecins(idMedecin1));

            Rdv r = new Rdv();
            r.setDate(cal.getTime());

            r.setCreneau(c);
            r.setPatient(new Patients(idPatient1));

            Response response = target.request().put(
                            Entity.entity(r, MediaType.APPLICATION_JSON));
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }else{
                Rdv updated = response.readEntity(Rdv.class);
                if(updated==null){
                    fail("NO UPDATE");
                } 
             }
    }

    @Test
    public void deleteUnauthorized() {
        WebTarget target = client.target("http://localhost:8080/rdvMed/ws/patients/" + idPatient1);
        Response response = target.request().delete();
        if (response.getStatus() != 304) {
                fail("Unauthorized deletion of patient : " + idPatient1);
        }

        target = client.target("http://localhost:8080/rdvMed/ws/creneaux/" + idCreneau1);
        response = target.request().delete();
        if (response.getStatus() != 304) {
                fail("Unauthorized deletion of creneau : " + idCreneau1);
        }

        target = client.target("http://localhost:8080/rdvMed/ws/medecins/" + idMedecin1);
        response = target.request().delete();
        if (response.getStatus() != 304) {
                fail("Unauthorized deletion of medecin : " + idMedecin1);
        }
    }

    @Test
    public void delete() {
        WebTarget target = client.target(URL + "/" + id);
        Response response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }

        target = client.target("http://localhost:8080/rdvMed/ws/patients/" + idPatient1);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }
        
        target = client.target("http://localhost:8080/rdvMed/ws/creneaux/" + idCreneau1);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }

        target = client.target("http://localhost:8080/rdvMed/ws/medecins/" + idMedecin1);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }
    }
}
