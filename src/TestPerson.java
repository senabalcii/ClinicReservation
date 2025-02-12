package ClinicReservationSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    @Test
    public void testGetName() {
        Person person = new Person("Ali Veli", 12345678901L);
        assertEquals("Ali Veli", person.getName(), "Kişi adı doğru alınamadı.");
    }

    @Test
    public void testToString() {
        Person person = new Person("Ali Veli", 12345678901L);
        String expected = "Person{name='Ali Veli', national_id=12345678901}";
        assertEquals(expected, person.toString(), "toString metodu yanlış çıktı döndü.");
    }
}
