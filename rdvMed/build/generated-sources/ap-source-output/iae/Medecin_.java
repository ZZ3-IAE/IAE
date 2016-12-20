package iae;

import iae.Creneau;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T11:56:19")
@StaticMetamodel(Medecin.class)
public class Medecin_ { 

    public static volatile ListAttribute<Medecin, Creneau> creneauList;
    public static volatile SingularAttribute<Medecin, Short> id;
    public static volatile SingularAttribute<Medecin, String> nom;
    public static volatile SingularAttribute<Medecin, String> prenom;

}