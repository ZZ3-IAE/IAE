package fr.isima.rdvmed.entity;

import fr.isima.rdvmed.entity.Medecins;
import fr.isima.rdvmed.entity.Rdv;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-29T08:53:03")
@StaticMetamodel(Creneaux.class)
public class Creneaux_ { 

    public static volatile SingularAttribute<Creneaux, Date> debut;
    public static volatile SingularAttribute<Creneaux, Date> fin;
    public static volatile SingularAttribute<Creneaux, Short> id;
    public static volatile CollectionAttribute<Creneaux, Rdv> rdvCollection;
    public static volatile SingularAttribute<Creneaux, Medecins> medecin;

}