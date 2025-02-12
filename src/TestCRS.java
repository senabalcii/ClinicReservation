package ClinicReservationSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCRS {

    private CRS crs;
    private Patient patient;
    private Hospital hospital;
    private Section section;
    private Doctor doctor;

    @BeforeEach
    public void setup() {

        crs = new CRS();
        crs.systemMode=true;


        patient = new Patient("Ali Veli", 12345678901L);
        crs.getPatients().put(patient.getNational_id(), patient);


        hospital = new Hospital(1, "Hastane A");
        section = new Section(1, "Kardiyoloji", new LinkedList<>());
        hospital.getSections().add(section);
        crs.getHospitals().put(hospital.getId(), hospital);


        doctor = new Doctor("Dr. Ahmet", 12345678901L, 101, new Schedule(5, doctor));
        section.getDoctors().add(doctor);
    }

    @Test
    public void testMakeRendezvousSuccess() throws IDException {

        Date date = new Date();
        boolean result = crs.makeRendezvous(12345678901L, 1, 1, 101, date);

        assertTrue(result, "Randevu başarılı bir şekilde eklenemedi.");
        assertTrue(doctor.getSchedule().sessions.stream()
                        .anyMatch(r -> r.getPatient().equals(patient) && r.getDateTime().equals(date)),
                "Randevu doktorun programına eklenmedi.");
    }

    @Test
    public void testMakeRendezvousPatientNotFound() {

        Date date = new Date();
        Exception exception = assertThrows(IDException.class, () -> {
            crs.makeRendezvous(99999999999L, 1, 1, 101, date);
        });

        System.out.println("Fırlatılan Hata Mesajı: " + exception.getMessage());
        assertEquals("hasta bulunamadı", exception.getMessage(), "Hata mesajı beklenenden farklı.");
    }

    @Test
    public void testSaveTablesToDisk() {

        String filePath = "test_veritabani.ser";
        crs.saveTablesToDisk(filePath);


        File file = new File(filePath);
        assertTrue(file.exists(), "Tablolar diske kaydedilemedi.");


        file.delete();
    }
}
