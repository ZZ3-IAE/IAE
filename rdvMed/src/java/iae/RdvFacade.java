/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iae;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vagrant
 */
@Stateless
public class RdvFacade extends AbstractFacade<Rdv> {

    @PersistenceContext(unitName = "rdvMedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RdvFacade() {
        super(Rdv.class);
    }
    
}
