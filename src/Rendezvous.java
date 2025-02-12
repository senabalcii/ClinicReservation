package ClinicReservationSystem;

import java.util.Date;
import java.io.Serializable;

public class Rendezvous implements Serializable {

    private Date dateTime;
    private Patient patient;
    private Doctor doctor;

    public Rendezvous(Date dateTime,Patient patient){

        this.dateTime=dateTime;
        this.patient=patient;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Patient getPatient() {
        return patient;
    }
}
