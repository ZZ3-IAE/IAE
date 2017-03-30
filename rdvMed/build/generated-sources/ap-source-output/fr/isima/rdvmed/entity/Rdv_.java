package fr.isima.rdvmed.entity;

import fr.isima.rdvmed.entity.Creneaux;
import fr.isima.rdvmed.entity.Patients;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-29T15:33:58")
@StaticMetamodel(Rdv.class)
public class Rdv_ { 

    public static volatile SingularAttribute<Rdv, Date> date;
    public static volatile SingularAttribute<Rdv, Creneaux> creneau;
    public static volatile SingularAttribute<Rdv, Patients> patient;
    public static volatile SingularAttribute<Rdv, Short> id;

}