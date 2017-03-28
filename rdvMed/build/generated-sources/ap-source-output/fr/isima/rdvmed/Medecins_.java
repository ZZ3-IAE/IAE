package fr.isima.rdvmed;

import fr.isima.rdvmed.Creneaux;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-28T09:50:11")
@StaticMetamodel(Medecins.class)
public class Medecins_ { 

    public static volatile CollectionAttribute<Medecins, Creneaux> creneauxCollection;
    public static volatile SingularAttribute<Medecins, Short> id;
    public static volatile SingularAttribute<Medecins, String> nom;
    public static volatile SingularAttribute<Medecins, String> prenom;

}