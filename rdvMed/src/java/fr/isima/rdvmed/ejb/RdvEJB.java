/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.Rdv;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author vagrant
 */
@Stateless
public class RdvEJB extends AbstractFacade<Rdv> {

    @PersistenceContext(unitName = "rdvMedPU")
    private EntityManager em;

    public RdvEJB() {
        super(Rdv.class);
    }

    @Override
    public void create(Rdv entity) {
        super.create(entity);
    }

    public void edit(Short id, Rdv entity) {
        super.edit(entity);
    }

    public void remove(Short id) {
        super.remove(super.find(id));
    }

    public Rdv find(Short id) {
        return super.find(id);
    }

    @Override
    public List<Rdv> findAll() {
        return super.findAll();
    }

    public List<Rdv> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
