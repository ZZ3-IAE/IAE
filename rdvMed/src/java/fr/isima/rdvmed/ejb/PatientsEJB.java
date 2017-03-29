/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Patients;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vagrant
 */
@Stateless
public class PatientsEJB extends AbstractFacade<Patients> {

    @PersistenceContext
    private EntityManager em;

    public PatientsEJB() {
        super(Patients.class);
    }

    @Override
    public Patients create(Patients entity) {
        return super.create(entity);
    }

    public Patients edit(Short id, Patients entity) {
        super.edit(entity);
        return super.find(id);
    }

    public boolean remove(Short id) {
        Patients p = super.find(id);
        if(p!=null && p.getRdvCollection().isEmpty())
            return super.remove(p);
        return false;
    }

    public Patients find(Short id) {
        return super.find(id);
    }

    @Override
    public List<Patients> findAll() {
        return super.findAll();
    }

    public List<Patients> findRange(Integer from, Integer to) {
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
