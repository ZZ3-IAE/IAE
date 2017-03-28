/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.Creneaux;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vagrant
 */
@Stateless
public class CreneauxEJB extends AbstractFacade<Creneaux> {

    @PersistenceContext(unitName = "rdvMedPU")
    private EntityManager em;

    public CreneauxEJB() {
        super(Creneaux.class);
    }
    
    @Override
    public void create(Creneaux entity) {
        super.create(entity);
    }
    
    public void edit(Short id, Creneaux entity) {
        super.edit(entity);
    }
    
    public void remove(Short id) {
        super.remove(super.find(id));
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
