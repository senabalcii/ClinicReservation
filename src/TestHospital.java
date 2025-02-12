package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

public class TestHospital {

    @Test
    public void testGetSectionById() {

        Hospital hospital = new Hospital(1, "Hastane A");
        Section section = new Section(1, "Kardiyoloji", new LinkedList<>());
        hospital.getSections().add(section);


        assertEquals(section, hospital.getSection(1), "Bölüm doğru şekilde alınamadı.");
    }

    @Test
    public void testGetSectionByIdNotFound() {

        Hospital hospital = new Hospital(1, "Hastane A");


        assertNull(hospital.getSection(1), "Bölüm bulunamadığında null dönmeliydi.");
    }

    @Test
    public void testAddSection() throws DuplicateInfoException {

        Hospital hospital = new Hospital(1, "Hastane A");
        Section section = new Section(1, "Kardiyoloji", new LinkedList<>());


        hospital.addSection(section);


        assertTrue(hospital.getSections().contains(section), "Bölüm eklenemedi.");
    }

    @Test
    public void testAddSectionDuplicateId() {

        Hospital hospital = new Hospital(1, "Hastane A");
        Section section1 = new Section(1, "Kardiyoloji", new LinkedList<>());
        Section section2 = new Section(1, "Nöroloji", new LinkedList<>());

        try {
            hospital.addSection(section1);
            hospital.addSection(section2);
            fail("Aynı ID ile bölüm eklenmesine izin verilmemeliydi.");
        } catch (DuplicateInfoException e) {
            assertEquals("bu bölüm zaten sisteme kayıtlı", e.getMessage(), "Hata mesajı beklenenden farklı.");
        }
    }
}
