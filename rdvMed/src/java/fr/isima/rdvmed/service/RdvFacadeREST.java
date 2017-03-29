/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.service;

import fr.isima.rdvmed.entity.Rdv;
import fr.isima.rdvmed.ejb.RdvEJB;
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
@Path("rdv")
public class RdvFacadeREST {

    @EJB
    private RdvEJB rdv;

    @POST    
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Rdv create(Rdv entity) {
        return rdv.create(entity);
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Rdv edit(@PathParam("id") Short id, Rdv entity) {
        entity.setId(id);
        return rdv.edit(entity);
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response remove(@PathParam("id") Short id) {
        Response r = Response.notModified().build();
        if(rdv.remove(id))
            r = Response.ok().build();
        return r;
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
