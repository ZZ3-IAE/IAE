/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.ejb.CreneauxEJB;
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

/**
 *
 * @author vagrant
 */
@Path("creneaux")
public class CreneauxFacadeREST {

    @EJB
    private CreneauxEJB creneaux;

    @POST    
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Creneaux create(Creneaux entity) {
        return creneaux.create(entity);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Creneaux edit(@PathParam("id") Short id, Creneaux entity) {
        entity.setId(id);
        return creneaux.edit(entity);
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        creneaux.remove(creneaux.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Creneaux find(@PathParam("id") Short id) {
        return creneaux.find(id);
    }

    @GET    
    @Produces({MediaType.APPLICATION_JSON})
    public List<Creneaux> findAll() {
        return creneaux.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Creneaux> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return creneaux.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(creneaux.count());
    }

}
