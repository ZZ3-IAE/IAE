package iae;

import iae.Creneau;
import iae.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T11:56:19")
@StaticMetamodel(Rdv.class)
public class Rdv_ { 

    public static volatile SingularAttribute<Rdv, Date> date;
    public static volatile SingularAttribute<Rdv, Creneau> creneau;
    public static volatile SingularAttribute<Rdv, Patient> patient;
    public static volatile SingularAttribute<Rdv, Short> id;

}