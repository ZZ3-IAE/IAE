/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iae;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vagrant
 */
@Entity
@Table(name = "creneaux")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creneau.findAll", query = "SELECT c FROM Creneau c")
    , @NamedQuery(name = "Creneau.findById", query = "SELECT c FROM Creneau c WHERE c.id = :id")
    , @NamedQuery(name = "Creneau.findByDebut", query = "SELECT c FROM Creneau c WHERE c.debut = :debut")
    , @NamedQuery(name = "Creneau.findByFin", query = "SELECT c FROM Creneau c WHERE c.fin = :fin")})
public class Creneau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date debut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creneau")
    private List<Rdv> rdvList;
    @JoinColumn(name = "medecin", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Medecin medecin;

    public Creneau() {
    }

    public Creneau(Short id) {
        this.id = id;
    }

    public Creneau(Short id, Date debut, Date fin) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @XmlTransient
    public List<Rdv> getRdvList() {
        return rdvList;
    }

    public void setRdvList(List<Rdv> rdvList) {
        this.rdvList = rdvList;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creneau)) {
            return false;
        }
        Creneau other = (Creneau) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iae.Creneau[ id=" + id + " ]";
    }
    
}
