/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.ejb.CreneauxEJB;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.ejb.MedecinsEJB;
import fr.isima.rdvmed.entity.Creneaux;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vagrant
 */
@Path("medecins")
public class MedecinsFacadeREST {

    @EJB
    private MedecinsEJB medecins;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Medecins create(Medecins entity) {
        return (Medecins)medecins.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Medecins edit(@PathParam("id") Short id, Medecins entity) {
        entity.setId(id);
        return medecins.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Short id) {
        Response r = Response.notModified().build();
        if(medecins.remove(id))
            r = Response.ok().build();
        return r;
    }

    @GET
    @Path("creneaux/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Creneaux> creneaux(@PathParam("id") Short id) {
        Medecins m = medecins.find(id);
        Collection<Creneaux> crx = null;
        if(m!=null)
            crx = medecins.findAllCreneaux(m);
        return crx;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Medecins find(@PathParam("id") Short id) {
        return medecins.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Medecins> findAll() {
        return medecins.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Medecins> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return medecins.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(medecins.count());
    }
    
}
