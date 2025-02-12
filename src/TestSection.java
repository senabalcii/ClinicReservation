package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

public class TestSection {


    @Test
    public void testAddDoctor() {
        Section section = new Section(1, "Kardiyoloji", new LinkedList<>());
        Doctor doctor = new Doctor("Dr. Ayşe", 98765432101L, 202, null);

        section.getDoctors().add(doctor);

        assertTrue(section.getDoctors().contains(doctor), "Doktor bölüme eklenemedi.");
    }

    @Test
    public void testListDoctors() {

        Section section = new Section(1, "Kardiyoloji", new LinkedList<>());
        Doctor doctor1 = new Doctor("Dr. Ayşe", 98765432101L, 202, null);
        Doctor doctor2 = new Doctor("Dr. Ahmet", 12345678901L, 101, null);

        section.getDoctors().add(doctor1);
        section.getDoctors().add(doctor2);


        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));


        section.listDoctors();

        String expectedOutput = "Doctor{diploma id=202}\nDoctor{diploma id=101}\n";
        assertEquals(expectedOutput, outContent.toString(), "Doktorların listesi doğru değil.");


        System.setOut(System.out);
    }
}
