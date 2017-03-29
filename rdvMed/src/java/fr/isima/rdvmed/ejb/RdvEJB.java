/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.ejb;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Patients;
import fr.isima.rdvmed.entity.Rdv;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static Logger LOG = Logger.getLogger("rdv");

    @PersistenceContext(unitName = "rdvMedPU")
    private EntityManager em;
    
    @EJB
    private CreneauxEJB creneaux;
    
    @EJB
    private MedecinsEJB medecins;
    
    @EJB
    private PatientsEJB patients;

    public RdvEJB() {
        super(Rdv.class);
    }

    @Override
    public Rdv create(Rdv entity) {
        Rdv res = null;
        if(entity != null && entity.getPatient() != null && entity.getCreneau() != null
                && entity.getCreneau().getMedecin() != null && entity.getCreneau().getDebut() != null && entity.getCreneau().getFin() != null ) {
            
            Medecins m = medecins.find(entity.getCreneau().getMedecin().getId());
            Patients p = patients.find(entity.getPatient().getId());
            if(m != null && p != null && checkDispo(m, entity.getCreneau())) {
                Creneaux c = creneaux.create(entity.getCreneau());
                entity.setCreneau(c);
                res = super.create(entity);
            }
            
        }
        return res;
    }

    public Rdv edit(Short id, Rdv entity) {
        Rdv res = null;
        if(entity != null && entity.getPatient() != null && entity.getCreneau() != null
                && entity.getCreneau().getMedecin() != null && entity.getCreneau().getDebut() != null && entity.getCreneau().getFin() != null ) {
            
            Medecins m = medecins.find(entity.getCreneau().getMedecin().getId());
            Patients p = patients.find(entity.getPatient().getId());
            Creneaux c = creneaux.find(entity.getCreneau().getId());
            Rdv r = find(entity.getId());
            
            if(r != null && m != null && p != null && c != null && checkDispo(m, entity.getCreneau())) {
                creneaux.edit(c.getId(), entity.getCreneau());
                entity.setCreneau(creneaux.find(c.getId()));
                super.edit(entity);
                res = super.find(id);
            }
            
        }
        return res;
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
            
            deb.set(aaaa, mm-1, jj, 8, 00);
            
            for (Creneaux c : occupes) {
                end.setTime(c.getDebut());
                if(!(deb.get(Calendar.HOUR_OF_DAY) == end.get(Calendar.HOUR_OF_DAY) && deb.get(Calendar.MINUTE) == end.get(Calendar.MINUTE))) {
                    libres.add(new Creneaux(Short.MIN_VALUE, deb.getTime(), end.getTime()));
                }
                deb.setTime(c.getFin());
            }
            
            end.set(aaaa, mm-1, jj, 20, 00);
            if(!(deb.get(Calendar.HOUR_OF_DAY) == end.get(Calendar.HOUR_OF_DAY) && deb.get(Calendar.MINUTE) == end.get(Calendar.MINUTE))) {
                libres.add(new Creneaux(Short.MIN_VALUE, deb.getTime(), end.getTime()));
            }
            
        }
        
        return libres;
    }

    /**
     * Check if the doctor is available
     * @param m
     * @param creneau
     * @return 
     */
    private boolean checkDispo(Medecins m, Creneaux creneau) {
        boolean res = checkCreneau(creneau);
        if(res) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(creneau.getDebut());
            List<Creneaux> libres = findFreeCreneaux(m, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
            res = checkMatching(libres, creneau);
            
                LOG.log(Level.SEVERE, "BOOMBOOM " + res);
        }                
        return res;
    }
    
    /**
     * Check if the creneau is logical
     * @param c
     * @return 
     */
    private boolean checkCreneau(Creneaux c) {
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        
        c1.setTime(c.getDebut());
        c2.setTime(c.getFin());
        
        return c1.before(c2) && c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) && c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Check if a creneau can take a place in a available creneau list
     * @param libres
     * @param c
     * @return 
     */
    private boolean checkMatching(List<Creneaux> libres, Creneaux c) {
        boolean res = false;
        Iterator<Creneaux> it = libres.iterator();
                LOG.log(Level.SEVERE, "CRE " + c.getDebut() + " " + c.getFin());
        while (!res && it.hasNext()) {
            Creneaux next = it.next();
                LOG.log(Level.SEVERE, "INTER " + next.getDebut() + " " + next.getFin());
            res = (after(c.getDebut(), next.getDebut()) && before(c.getFin(), next.getFin()));
        }
        return res;
    }

    /**
     * Version adaptated from Date.after
     * @param d1
     * @param d2
     * @return 
     */
    private boolean after(Date d1, Date d2) {
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTime(d1);
        c2.setTime(d2);
        boolean res = c1.get(Calendar.YEAR)>c2.get(Calendar.YEAR) || c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && (
                c1.get(Calendar.MONTH)>c2.get(Calendar.MONTH) || c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) && (
                c1.get(Calendar.DAY_OF_MONTH)>c2.get(Calendar.DAY_OF_MONTH) || c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH) && (
                c1.get(Calendar.HOUR_OF_DAY)>c2.get(Calendar.HOUR_OF_DAY) || c1.get(Calendar.HOUR_OF_DAY)==c2.get(Calendar.HOUR_OF_DAY) && (
                c1.get(Calendar.MINUTE)>=c2.get(Calendar.MINUTE)))));
        
        return res;
    }

    /**
     * Version adaptated from Date.before     
     * @param d1
     * @param d2
     * @return 
     */
    private boolean before(Date d1, Date d2) {
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTime(d1);
        c2.setTime(d2);
        boolean res = c1.get(Calendar.YEAR)<c2.get(Calendar.YEAR) || c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && (
                c1.get(Calendar.MONTH)<c2.get(Calendar.MONTH) || c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) && (
                c1.get(Calendar.DAY_OF_MONTH)<c2.get(Calendar.DAY_OF_MONTH) || c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH) && (
                c1.get(Calendar.HOUR_OF_DAY)<c2.get(Calendar.HOUR_OF_DAY) || c1.get(Calendar.HOUR_OF_DAY)==c2.get(Calendar.HOUR_OF_DAY) && (
                c1.get(Calendar.MINUTE)<=c2.get(Calendar.MINUTE)))));
        
        return res;
    }
    
}
