package fr.isima.rdvmed.entity;

import fr.isima.rdvmed.entity.Rdv;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-29T15:33:58")
@StaticMetamodel(Patients.class)
public class Patients_ { 

    public static volatile SingularAttribute<Patients, Short> id;
    public static volatile SingularAttribute<Patients, String> nom;
    public static volatile SingularAttribute<Patients, String> prenom;
    public static volatile CollectionAttribute<Patients, Rdv> rdvCollection;

}