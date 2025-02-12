package ClinicReservationSystem;


import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Date;

public class Main{

    public static void PersonelGirisi(CRS crs) {
        Scanner scanner = new Scanner(System.in);

        int secim;
        System.out.println("1. Doktorları Listele");
        System.out.println("2. Hastaları Listele");
        System.out.println("3. Hastaneleri Listele");
        System.out.println("4. Bölümleri Listele");
        System.out.println("5. Randevuları Listele");
        System.out.println("6. Doktor Ekle");
        System.out.println("7. Hasta Ekle");
        System.out.println("8. Hastane Ekle");
        System.out.println("9. Bölüm Ekle");
        System.out.println("10. Randevu Ekle");

        secim = scanner.nextInt();

        switch (secim) {
            case 1:
                System.out.println("Hastane ID'sini ve Bölüm ID'sini giriniz.");
                System.out.print("Hastane ID: ");
                int hastaneID = scanner.nextInt();
                System.out.print("Bölüm ID: ");
                int bolumID = scanner.nextInt();
                crs.getHospitals().get(hastaneID).getSection(bolumID).listDoctors();
                break;

            case 2:
                for (Patient patient : crs.getPatients().values()) {
                    System.out.println(patient.getName());
                }
                break;

            case 3:
                for (Hospital hospital : crs.getHospitals().values()) {
                    System.out.println(hospital.getName());
                }
                break;

            case 4:
                for (Hospital hospital : crs.getHospitals().values()) {
                    for (Section section : hospital.getSections()) {
                        System.out.println(section.getName());
                    }
                }
                break;

            case 5:
                for (Hospital hospital : crs.getHospitals().values()) {
                    for (Section section : hospital.getSections()) {
                        for (Doctor doctor : section.getDoctors()) {
                            for (Rendezvous rendezvous : doctor.getSchedule().sessions) {
                                System.out.println("Hasta: " + rendezvous.getPatient().getName() +
                                        ", Doktor: " + doctor.getName() +
                                        ", Tarih: " + rendezvous.getDateTime());
                            }
                        }
                    }
                }
                break;

            case 6:
                System.out.print("Doktor adı: ");
                String doktorAdi = scanner.next();
                System.out.print("Doktor kimlik numarası: ");
                long doktorKimlik = scanner.nextLong();
                System.out.print("Doktor diploma ID: ");
                int diplomaID = scanner.nextInt();
                System.out.print("Bölüm ID: ");
                bolumID = scanner.nextInt();

                for (Hospital hospital : crs.getHospitals().values()) {
                    Section section = hospital.getSection(bolumID);
                    if (section != null) {
                        section.addDoctor(new Doctor(doktorAdi, doktorKimlik, diplomaID, new Schedule(5, null)));
                        System.out.println("Doktor başarıyla eklendi.");
                        return;
                    }
                }
                System.out.println("Bölüm bulunamadı.");
                break;

            case 7:



                System.out.print("\nHasta kimlik numarası: ");

                scanner.nextLine();
               long hastaKimlik = scanner.nextLong();


                System.out.print("Hasta adı: ");
                String hastaAdi = scanner.next();




                crs.getPatients().put(hastaKimlik, new Patient(hastaAdi, hastaKimlik));
                System.out.println("Hasta başarıyla eklendi.");
                break;

            case 8:
                System.out.print("Hastane ID: ");
                int hastaneIDYeni = scanner.nextInt();
                System.out.print("Hastane adı: ");
                String hastaneAdi = scanner.next();
                crs.getHospitals().put(hastaneIDYeni, new Hospital(hastaneIDYeni, hastaneAdi));
                System.out.println("Hastane başarıyla eklendi.");
                break;

            case 9:
                System.out.print("Hastane ID: ");
                hastaneID = scanner.nextInt();
                System.out.print("Bölüm ID: ");
                bolumID = scanner.nextInt();
                System.out.print("Bölüm adı: ");
                String bolumAdi = scanner.next();
                Hospital hospital = crs.getHospitals().get(hastaneID);
                if (hospital != null) {
                    hospital.addSection(new Section(bolumID, bolumAdi, new LinkedList<>()));
                    System.out.println("Bölüm başarıyla eklendi.");
                } else {
                    System.out.println("Hastane bulunamadı.");
                }
                break;

            case 10:
                System.out.print("Hasta kimlik numarası: ");
                long hastaID = scanner.nextLong();
                System.out.print("Hastane ID: ");
                hastaneID = scanner.nextInt();
                System.out.print("Bölüm ID: ");
                bolumID = scanner.nextInt();
                System.out.print("Doktor diploma ID: ");
                diplomaID = scanner.nextInt();
                System.out.print("Randevu tarihi (yyyy-MM-dd HH:mm): ");
                String tarihStr = scanner.next();
                try {
                    Date tarih = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tarihStr);
                    boolean success = crs.makeRendezvous(hastaID, hastaneID, bolumID, diplomaID, tarih);
                    if (success) {
                        crs.saveTablesToDisk("veritabani.ser");
                        System.out.println("Randevu başarıyla oluşturuldu.");
                    } else {
                        System.out.println("Randevu oluşturulamadı.");
                    }
                } catch (Exception e) {
                    System.out.println("Tarih formatı hatalı.");
                }
                break;

            default:
                System.out.println("Geçersiz seçim.");
        }
    }

    public static void HastaGiris(CRS crs) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hasta kimlik numaranızı giriniz: ");
        long hastaID = scanner.nextLong();

        if (crs.getPatients().containsKey(hastaID)) {
            Patient patient = crs.getPatients().get(hastaID);
            System.out.println("Hoşgeldiniz, " + patient.getName() + "!");
            System.out.println("1. Yeni Randevu Al");
            System.out.println("2. Çıkış");

            int secim = scanner.nextInt();
            switch (secim) {

                case 1:

                    System.out.print("Hastane ID: ");
                    int hastaneID = scanner.nextInt();
                    System.out.print("Bölüm ID: ");
                    int bolumID = scanner.nextInt();
                    crs.hospitals.get(hastaneID).getSection(bolumID).listDoctors();
                    System.out.print("Doktor diploma ID: ");
                    int diplomaID = scanner.nextInt();
                    System.out.print("Randevu tarihi (yyyy-MM-dd): ");
                    String tarihInput = scanner.next();
                    System.out.print("Randevu saati (HH:mm): ");
                    String saatInput = scanner.next();
                    try {
                        String tarihSaatStr = tarihInput + " " + saatInput;
                        Date tarih = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tarihSaatStr);
                        boolean success = crs.makeRendezvous(hastaID, hastaneID, bolumID, diplomaID, tarih);
                        if (success) {
                            crs.saveTablesToDisk("/Users/senabalci/Desktop/java/nypProje/veritabani.ser");
                            System.out.println("Randevu başarıyla oluşturuldu.");
                        } else {
                            System.out.println("Randevu oluşturulamadı.");
                        }
                    } catch (Exception e) {
                        System.out.println("Tarih veya saat formatı hatalı. Lütfen yyyy-MM-dd ve HH:mm formatında giriniz.");
                    }
                    break;

                case 2:
                    System.out.println("Çıkış yapılıyor.");
                    break;

                default:
                    System.out.println("Geçersiz seçim.");
            }
        } else {
            System.out.println("Kimlik numarası bulunamadı. Lütfen doğru girdiğinizden emin olun.");
        }
    }


    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);

        CRS crs = new CRS();
        crs.systemMode=true;

        crs.loadTablesToDisk("veritabani.ser");

        System.out.println("Klinik Rezervasyon Sistemine Hoşgeldiniz" );
        System.out.println("Personel girişi için 1'i, Hasta girişi için 2'yi giriniz:");




        int number = scanner.nextInt();

        if (number==1){
            PersonelGirisi(crs);
        } else if (number==2) {
            HastaGiris(crs);
        }
        else {
            System.out.println("Geçersiz numara.");

        }

    }
}

