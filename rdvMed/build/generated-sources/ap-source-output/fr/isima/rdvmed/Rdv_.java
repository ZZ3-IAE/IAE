package fr.isima.rdvmed;

import fr.isima.rdvmed.Creneaux;
import fr.isima.rdvmed.Patients;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-28T17:14:17")
@StaticMetamodel(Rdv.class)
public class Rdv_ { 

    public static volatile SingularAttribute<Rdv, Date> date;
    public static volatile SingularAttribute<Rdv, Creneaux> creneau;
    public static volatile SingularAttribute<Rdv, Patients> patient;
    public static volatile SingularAttribute<Rdv, Short> id;

}