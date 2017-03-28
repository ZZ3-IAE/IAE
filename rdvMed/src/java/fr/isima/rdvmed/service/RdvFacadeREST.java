/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.Rdv;
import fr.isima.rdvmed.ejb.RdvEJB;
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
@Path("rdv")
public class RdvFacadeREST {

    @EJB
    private RdvEJB rdv;

    @POST    
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Rdv entity) {
        rdv.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Short id, Rdv entity) {
        rdv.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        rdv.remove(rdv.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Rdv find(@PathParam("id") Short id) {
        return rdv.find(id);
    }

    @GET    
    @Produces({MediaType.APPLICATION_JSON})
    public List<Rdv> findAll() {
        return rdv.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Rdv> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return rdv.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(rdv.count());
    }

}
