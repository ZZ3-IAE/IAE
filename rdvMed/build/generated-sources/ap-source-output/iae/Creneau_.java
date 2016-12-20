package iae;

import iae.Medecin;
import iae.Rdv;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T11:38:57")
@StaticMetamodel(Creneau.class)
public class Creneau_ { 

    public static volatile SingularAttribute<Creneau, Date> debut;
    public static volatile SingularAttribute<Creneau, Date> fin;
    public static volatile SingularAttribute<Creneau, Short> id;
    public static volatile SingularAttribute<Creneau, Medecin> medecin;
    public static volatile ListAttribute<Creneau, Rdv> rdvList;

}