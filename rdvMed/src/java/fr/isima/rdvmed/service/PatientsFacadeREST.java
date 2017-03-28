/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.Patients;
import fr.isima.rdvmed.ejb.PatientsEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vagrant
 */
@Stateless
@Path("patients")
public class PatientsFacadeREST {

    @EJB
    private PatientsEJB patients;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Patients entity) {
        patients.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Short id, Patients entity) {
        patients.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        patients.remove(patients.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Patients find(@PathParam("id") Short id) {
        return patients.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Patients> findAll() {
        return patients.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Patients> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return patients.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(patients.count());
    }
    
}
