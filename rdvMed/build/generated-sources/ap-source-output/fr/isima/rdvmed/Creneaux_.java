package fr.isima.rdvmed;

import fr.isima.rdvmed.Medecins;
import fr.isima.rdvmed.Rdv;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-28T09:50:11")
@StaticMetamodel(Creneaux.class)
public class Creneaux_ { 

    public static volatile SingularAttribute<Creneaux, Date> debut;
    public static volatile SingularAttribute<Creneaux, Date> fin;
    public static volatile SingularAttribute<Creneaux, Short> id;
    public static volatile CollectionAttribute<Creneaux, Rdv> rdvCollection;
    public static volatile SingularAttribute<Creneaux, Medecins> medecin;

}