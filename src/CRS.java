package ClinicReservationSystem;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Date;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class CRS {

    protected HashMap<Long,Patient> patients;
    private LinkedList<Rendezvous> rendezvous;
    protected HashMap<Integer,Hospital> hospitals;
    boolean systemMode;  //0 ise gui 1 ise konsol



    public CRS() {
        this.patients = new HashMap<>();
        this.hospitals = new HashMap<>();
    }


    public boolean isSystemMode() {
        return systemMode;
    }




    public HashMap<Long, Patient> getPatients() {
        return patients;
    }

    public HashMap<Integer, Hospital> getHospitals() {
        return hospitals;
    }

    public LinkedList<Rendezvous> getRendezvous() {
        return rendezvous;
    }




    public boolean makeRendezvous(long patientID, int hospitalID, int sectionID, int diplomaID, Date desiredDate) throws IDException {
        synchronized (this) {


            // hasta id
            if (!patients.containsKey(patientID)) {
                if (isSystemMode()) {
                    throw new IDException("Hasta bulunamadı");
                } else {
                    System.err.println("Hata oluştu: Hasta bulunamadı");
                    return false;
                }
            }

            // hastane id
            if (!hospitals.containsKey(hospitalID)) {
                if (isSystemMode()) {
                    throw new IDException("Hastane bulunamadı");
                } else {
                    System.err.println("Hata oluştu: Hastane bulunamadı");
                    return false;
                }
            }

            // bölüm id
            Section section = hospitals.get(hospitalID).getSection(sectionID);
            if (section == null) {
                if (isSystemMode()) {
                    throw new IDException("Bölüm bulunamadı");
                } else {
                    System.err.println("Hata oluştu: Bölüm bulunamadı");
                    return false;
                }
            }

            // doktor id
            Doctor doctor = section.getDoctor(diplomaID);
            if (doctor == null) {
                if (isSystemMode()) {
                    throw new IDException("Doktor bulunamadı");
                } else {
                    System.err.println("Hata oluştu: Doktor bulunamadı");
                    return false;
                }
            }

            // randevu ekle
            boolean success = doctor.getSchedule().addRendezvous(patients.get(patientID), desiredDate);
            if (!success) {
                System.out.println("Randevu eklenemedi: Günlük randevu limiti doldu.");
                return false;
            }

            System.out.println("Randevu başarıyla eklendi.");
            return true;
        }
    }



    public synchronized void saveTablesToDisk(String fullPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullPath))) {
            oos.writeObject(this.patients);
            oos.writeObject(this.hospitals);
            oos.writeObject(this.rendezvous);
            System.out.println("Veriler başarıyla diske kaydedildi: " + fullPath);
        } catch (IOException e) {
            System.err.println("Hata: Veriler diske kaydedilemedi. " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public synchronized void loadTablesToDisk(String fullPath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullPath))) {
            this.patients = (HashMap<Long, Patient>) ois.readObject();
            this.hospitals = (HashMap<Integer, Hospital>) ois.readObject();
            this.rendezvous = (LinkedList<Rendezvous>) ois.readObject();
            System.out.println("Veriler başarıyla diskten yüklendi: " + fullPath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Hata: Veriler diskten yüklenemedi. " + e.getMessage());
        }
    }

}
