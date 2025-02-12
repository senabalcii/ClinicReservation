package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDoctor {

    @Test
    public void testGetDiplomaId() {
        Doctor doctor = new Doctor("Dr. Ahmet", 12345678901L, 101, null);
        assertEquals(101, doctor.getDiploma_id(), "Diploma ID doğru alınamadı.");
    }

    @Test
    public void testToString() {
        Doctor doctor = new Doctor("Dr. Ahmet", 12345678901L, 101, null);
        String expected = "Doctor{diploma id=101}";
        assertEquals(expected, doctor.toString(), "toString metodu yanlış çıktı döndü.");
    }
}
