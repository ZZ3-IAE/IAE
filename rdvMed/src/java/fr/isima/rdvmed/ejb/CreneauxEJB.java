/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Rdv;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vagrant
 */
@Stateless
public class CreneauxEJB extends AbstractFacade<Creneaux> {
    
    private static Logger LOG = Logger.getLogger("creneaux");

    @PersistenceContext
    private EntityManager em;

    public CreneauxEJB() {
        super(Creneaux.class);
    }
    
    @Override
    public Creneaux create(Creneaux entity) {
        return super.create(entity);
    }
    
    public Creneaux edit(Short id, Creneaux entity) {
        super.edit(entity);
        return super.find(id);
    }
    
    public boolean remove(Short id) {
        Creneaux c = super.find(id);
        if(c!=null)
            return super.remove(c);
        return false;
    }
    
    public Creneaux find(Short id) {
        return super.find(id);
    }
    
    @Override
    public List<Creneaux> findAll() {
        return super.findAll();
    }

    public List<Creneaux> findRange(Integer from, Integer to) {
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
