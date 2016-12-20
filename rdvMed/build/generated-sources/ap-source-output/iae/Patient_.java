package iae;

import iae.Rdv;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T11:56:19")
@StaticMetamodel(Patient.class)
public class Patient_ { 

    public static volatile SingularAttribute<Patient, Short> id;
    public static volatile SingularAttribute<Patient, String> nom;
    public static volatile SingularAttribute<Patient, String> prenom;
    public static volatile ListAttribute<Patient, Rdv> rdvList;

}