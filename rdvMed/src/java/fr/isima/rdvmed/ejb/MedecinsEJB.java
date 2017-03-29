/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
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
public class MedecinsEJB extends AbstractFacade<Medecins> {
    
    private static Logger LOG = Logger.getLogger("medecins");

    @PersistenceContext
    private EntityManager em;

    public MedecinsEJB() {
        super(Medecins.class);
    }
    
    @Override
    public Medecins create(Medecins entity) {
        return super.create(entity);
    }
    
    public Medecins edit(Short id, Medecins entity) {
        super.edit(entity);
        return super.find(id);
    }
    
    public boolean remove(Short id) {
        Medecins m = find(id);
        
        if(m!=null && m.getCreneauxCollection().isEmpty())
            return super.remove(m);
        return false;
    }
    
    public Medecins find(Short id) {
        return super.find(id);
    }
    
    @Override
    public List<Medecins> findAll() {
        return super.findAll();
    }
    
    public List<Medecins> findRange(Integer from, Integer to) {
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
