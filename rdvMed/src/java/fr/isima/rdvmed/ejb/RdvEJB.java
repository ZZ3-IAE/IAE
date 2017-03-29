/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Rdv;
import java.util.List;
import javax.ejb.EJB;
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
    
    @EJB
    private CreneauxEJB creneaux;

    public RdvEJB() {
        super(Rdv.class);
    }

    @Override
    public Rdv create(Rdv entity) {
        Creneaux c = creneaux.create(entity.getCreneau());
        entity.setCreneau(c);
        return super.create(entity);
    }

    public Rdv edit(Short id, Rdv entity) {
        super.edit(entity);
        return super.find(id);
    }

    public boolean remove(Short id) {
        Rdv r = super.find(id);
        if(r!=null) {
            super.remove(r);
            return creneaux.remove(r.getCreneau().getId());
        }
        return false;
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
