/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Rdv;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
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
    
    @EJB
    private MedecinsEJB medecins;

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

    /**
     * Return the list of available Creneaux for a specific doctor on a certain day
     * @param entity
     * @param aaaa
     * @param mm
     * @param jj
     * @return 
     */
    public List<Creneaux> findFreeCreneaux(Medecins entity, Integer aaaa, Integer mm, Integer jj) {
        List<Creneaux> libres = new ArrayList<>();
        
        Medecins m = medecins.find(entity.getId());
        Calendar date = new GregorianCalendar(aaaa,mm,jj);
        Calendar deb = new GregorianCalendar();
        Calendar end = new GregorianCalendar();
        
        if(m!=null) {
            List<Creneaux> tmp = new ArrayList<>(medecins.findAllCreneaux(m));
            List<Creneaux> occupes = new ArrayList<>();
            for (Creneaux c : tmp) {
                deb.setTime(c.getDebut());
                if(deb.get(Calendar.YEAR)==aaaa && deb.get(Calendar.MONTH)+1==mm && deb.get(Calendar.DAY_OF_MONTH)==jj) {
                    occupes.add(c);
                }
            }
            
            Collections.sort(occupes, new Comparator<Creneaux>() {
                @Override
                public int compare(Creneaux c1, Creneaux c2) {
                    return c1.getDebut().compareTo(c2.getDebut());
                }
            });
            
            deb.set(aaaa, mm, jj, 8, 00);
            
            for (Creneaux c : occupes) {
                end.setTime(c.getDebut());
                if(!(deb.get(Calendar.HOUR_OF_DAY) == end.get(Calendar.HOUR_OF_DAY) && deb.get(Calendar.MINUTE) == end.get(Calendar.MINUTE))) {
                    libres.add(new Creneaux(Short.MIN_VALUE, deb.getTime(), end.getTime()));
                }
                deb.setTime(c.getFin());
            }
            
            end.set(aaaa, mm, jj, 20, 00);
            if(!(deb.get(Calendar.HOUR_OF_DAY) == end.get(Calendar.HOUR_OF_DAY) && deb.get(Calendar.MINUTE) == end.get(Calendar.MINUTE))) {
                libres.add(new Creneaux(Short.MIN_VALUE, deb.getTime(), end.getTime()));
            }
            
        }
        
        return libres;
    }
    
}
