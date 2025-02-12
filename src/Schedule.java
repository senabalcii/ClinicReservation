package ClinicReservationSystem;

import java.util.LinkedList;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;


public class Schedule implements Serializable {

    protected LinkedList<Rendezvous> sessions;
    private int maxPatientDay;
    private Doctor doctor;

    public Schedule(int maxPatientDay,Doctor doctor){
        this.maxPatientDay=maxPatientDay;
        this.doctor = doctor;
        this.sessions=new LinkedList<>();
    }




    public synchronized boolean addRendezvous(Patient p, Date desired) {
        int sessionCount = 0;

        Calendar desiredCal = Calendar.getInstance();
        desiredCal.setTime(desired);

        for (Rendezvous r : sessions) {
            Calendar controlledCal = Calendar.getInstance();
            controlledCal.setTime(r.getDateTime());


            if (desiredCal.get(Calendar.YEAR) == controlledCal.get(Calendar.YEAR) &&
                    desiredCal.get(Calendar.DAY_OF_YEAR) == controlledCal.get(Calendar.DAY_OF_YEAR) &&
                    desiredCal.get(Calendar.HOUR_OF_DAY) == controlledCal.get(Calendar.HOUR_OF_DAY) &&
                    desiredCal.get(Calendar.MINUTE) == controlledCal.get(Calendar.MINUTE)) {
                System.out.println("Bu saat ve dakikada zaten bir randevu var.");
                return false;
            }


            if (desiredCal.get(Calendar.YEAR) == controlledCal.get(Calendar.YEAR) &&
                    desiredCal.get(Calendar.DAY_OF_YEAR) == controlledCal.get(Calendar.DAY_OF_YEAR)) {
                sessionCount++;
            }
        }


        if (sessionCount >= maxPatientDay) {
            System.out.println("Günlük randevu limiti doldu.");
            return false;
        }

        sessions.add(new Rendezvous(desired, p));
        System.out.println("Randevunuz başarıyla eklendi.");
        return true;
    }

}
