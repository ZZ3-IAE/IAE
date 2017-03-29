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
    public void a_create() {
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
        cal.set(2017, 02, 11, 11, 00);
        c.setDebut(cal.getTime());
        cal.set(2017, 02, 11, 12, 00);
        c.setFin(cal.getTime());
        c.setMedecin(new Medecins(idMedecin1));

        target = client.target(URL);

        Rdv r = new Rdv();
        cal.set(2017, 02, 11, 11, 00);
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
            idCreneau1 = created.getCreneau().getId();
        }
    }

    @Test
    public void b_find() {
            WebTarget target = client.target(URL + "/" + id);
            Response response = target.request().get();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }

    }

    @Test
    public void c_list() {
            WebTarget target = client.target(URL);
            Response response = target.request().get();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }
    }

    @Test
    public void d_count() {
            WebTarget target = client.target(URL + "/count");
            Response response = target.request().get();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }

    }

    @Test
    public void e_findRange() {
            WebTarget target = client.target(URL + "/1/3");
            Response response = target.request().get();
            if (response.getStatus() != 200) {
                    fail("RESPONSE STATUS" + response.getStatus());
            }

    }

    @Test
    public void f_update() {
            WebTarget target = client.target(URL + "/" + id);
            Creneaux c = new Creneaux(idCreneau1);
            cal.set(2017, 03, 11, 11, 00);
            c.setDebut(cal.getTime());
            cal.set(2017, 03, 11, 12, 00);
            c.setFin(cal.getTime());
            c.setMedecin(new Medecins(idMedecin1));
            c.setId(idCreneau1);

            Rdv r = new Rdv();
            r.setDate(cal.getTime());
            r.setId(id);
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
    public void g_deleteUnauthorized() {
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
    public void g_delete() {
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

        target = client.target("http://localhost:8080/rdvMed/ws/medecins/" + idMedecin1);
        response = target.request().delete();
        if (response.getStatus() != 200) {
                fail("RESPONSE STATUS" + response.getStatus());
        }
    }
}
