/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iae;

import java.io.Serializable;
import java.util.List;
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
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m")
    , @NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.id = :id")
    , @NamedQuery(name = "Medecin.findByNom", query = "SELECT m FROM Medecin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Medecin.findByPrenom", query = "SELECT m FROM Medecin m WHERE m.prenom = :prenom")})
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Short id;
    @Size(max = 64)
    @Column(name = "nom", length = 64)
    private String nom;
    @Size(max = 64)
    @Column(name = "prenom", length = 64)
    private String prenom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
    private List<Creneau> creneauList;

    public Medecin() {
    }

    public Medecin(Short id) {
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
    public List<Creneau> getCreneauList() {
        return creneauList;
    }

    public void setCreneauList(List<Creneau> creneauList) {
        this.creneauList = creneauList;
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
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iae.Medecin[ id=" + id + " ]";
    }
    
}
