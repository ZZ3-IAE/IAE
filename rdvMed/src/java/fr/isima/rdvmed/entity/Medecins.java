/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.isima.rdvmed.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vagrant
 */
@Entity
@Table(name = "medecins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecins.findAll", query = "SELECT m FROM Medecins m")
    , @NamedQuery(name = "Medecins.findById", query = "SELECT m FROM Medecins m WHERE m.id = :id")
    , @NamedQuery(name = "Medecins.findByNom", query = "SELECT m FROM Medecins m WHERE m.nom = :nom")
    , @NamedQuery(name = "Medecins.findByPrenom", query = "SELECT m FROM Medecins m WHERE m.prenom = :prenom")})
public class Medecins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Size(max = 64)
    @Column(name = "nom")
    private String nom;
    @Size(max = 64)
    @Column(name = "prenom")
    private String prenom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
    private Collection<Creneaux> creneauxCollection;

    public Medecins() {
    }

    public Medecins(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlTransient
    public Collection<Creneaux> getCreneauxCollection() {
        return creneauxCollection;
    }

    public void setCreneauxCollection(Collection<Creneaux> creneauxCollection) {
        this.creneauxCollection = creneauxCollection;
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
        if (!(object instanceof Medecins)) {
            return false;
        }
        Medecins other = (Medecins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.isima.rdvmed.Medecins[ id=" + id + " ]";
    }
    
}
