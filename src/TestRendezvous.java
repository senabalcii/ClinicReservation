package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TestRendezvous {

    @Test
    public void testGetDateTime() {
        Date now = new Date();
        Patient patient = new Patient("Ali Veli", 12345678901L);
        Rendezvous rendezvous = new Rendezvous(now, patient);

        assertEquals(now, rendezvous.getDateTime(), "Randevu tarihi doğru alınamadı.");
    }

    @Test
    public void testGetPatient() {
        Date now = new Date();
        Patient patient = new Patient("Ali Veli", 12345678901L);
        Rendezvous rendezvous = new Rendezvous(now, patient);

        assertEquals(patient, rendezvous.getPatient(), "Randevunun hastası doğru alınamadı.");
    }
}
