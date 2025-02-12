package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TestSchedule {

    @Test
    public void testAddRendezvousSuccess() {

        Schedule schedule = new Schedule(3, null);
        Patient patient = new Patient("Ali Veli", 12345678901L);
        Date date = new Date();


        assertTrue(schedule.addRendezvous(patient, date), "Randevu eklenemedi.");
    }

    @Test
    public void testAddRendezvousMaxLimitReached() {

        Schedule schedule = new Schedule(1, null);
        Patient patient = new Patient("Ali Veli", 12345678901L);
        Date date = new Date();


        schedule.addRendezvous(patient, date);


        assertFalse(schedule.addRendezvous(patient, date), "Maksimum randevu sınırı aşıldı.");
    }

    @Test
    public void testAddRendezvousDifferentDates() {

        Schedule schedule = new Schedule(2, null);
        Patient patient = new Patient("Ali Veli", 12345678901L);
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + (24 * 60 * 60 * 1000));


        assertTrue(schedule.addRendezvous(patient, date1), "İlk gün randevusu eklenemedi.");
        assertTrue(schedule.addRendezvous(patient, date2), "İkinci gün randevusu eklenemedi.");
    }

    @Test
    public void testAddRendezvousSameDayDifferentPatients() {

        Schedule schedule = new Schedule(2, null);
        Patient patient1 = new Patient("Ali Veli", 12345678901L);
        Patient patient2 = new Patient("Ayşe Yılmaz", 98765432101L);
        Date date = new Date();


        assertTrue(schedule.addRendezvous(patient1, date), "İlk hastanın randevusu eklenemedi.");
        assertTrue(schedule.addRendezvous(patient2, date), "İkinci hastanın randevusu eklenemedi.");
    }
}
