package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class PersonelAnaMenuGUI {

    private CRS crsSystem;

    public PersonelAnaMenuGUI(CRS crsSystem) {
        this.crsSystem = crsSystem;


        JFrame frame = new JFrame("Personel Yönetim Paneli");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));


        JLabel titleLabel = new JLabel("Personel Yönetim Paneli", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);


        JButton listDoctorsButton = new JButton("Doktorları Listele");
        JButton listPatientsButton = new JButton("Hastaları Listele");
        JButton listHospitalsButton = new JButton("Hastaneleri Listele");
        JButton listSectionsButton = new JButton("Bölümleri Listele");
        JButton listRendezvousButton = new JButton("Randevuları Listele");
        JButton addDoctorButton = new JButton("Doktor Ekle");
        JButton addPatientButton = new JButton("Hasta Ekle");
        JButton addHospitalButton = new JButton("Hastane Ekle");
        JButton addSectionButton = new JButton("Bölüm Ekle");
        JButton addRendezvousButton = new JButton("Randevu Ekle");
        JButton backButton= new JButton("Geri Dön");


        listDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder doctorList = new StringBuilder("Doktorlar:\n");
                for (Hospital hospital : crsSystem.getHospitals().values()) {
                    for (Section section : hospital.getSections()) {
                        for (Doctor doctor : section.getDoctors()) {
                            doctorList.append(doctor.toString()).append("\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(frame, doctorList.toString());

            }
        });



        listPatientsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                StringBuilder patientList = new StringBuilder("Hastalar:\n");
                for (Patient patient : crsSystem.getPatients().values()) {
                    patientList.append(patient.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, patientList.toString());
            }
        });




        listHospitalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder hospitalList = new StringBuilder("Hastaneler:\n");
                for (Hospital hospital : crsSystem.getHospitals().values()) {
                    hospitalList.append(hospital.getName()).append(" id: ").append(hospital.getId());
                }
                JOptionPane.showMessageDialog(frame, hospitalList.toString());
            }
        });






        listSectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sectionList = new StringBuilder("Bölümler:\n");
                for (Hospital hospital : crsSystem.getHospitals().values()) {
                    for (Section section : hospital.getSections()) {
                        sectionList.append(section.getName()).append(" id: ").append(section.getId()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, sectionList.toString());
            }
        });

        listRendezvousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder rendezvousList = new StringBuilder("Randevular:\n");
                for (Hospital hospital : crsSystem.getHospitals().values()) {
                    for (Section section : hospital.getSections()) {
                        for (Doctor doctor : section.getDoctors()) {
                            for (Rendezvous rendezvous : doctor.getSchedule().sessions) {
                                rendezvousList.append("Hasta: ").append(rendezvous.getPatient().getName())
                                        .append(", Doktor: ").append(doctor.getName())
                                        .append(", Tarih: ").append(rendezvous.getDateTime())
                                        .append("\n");
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(frame, rendezvousList.toString());
            }
        });


        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nameField = new JTextField();
                JTextField idField = new JTextField();
                JTextField diplomaField = new JTextField();
                JTextField sectionField = new JTextField();
                Object[] fields = {
                        "Doktor İsmi:", nameField,
                        "Doktor Kimlik No:", idField,
                        "Diploma ID:", diplomaField,
                        "Bölüm ID:", sectionField
                };
                int option = JOptionPane.showConfirmDialog(frame, fields, "Yeni Doktor Ekle", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    long id = Long.parseLong(idField.getText());
                    int diplomaID = Integer.parseInt(diplomaField.getText());
                    int sectionID = Integer.parseInt(sectionField.getText());
                    try {
                        for (Hospital hospital : crsSystem.getHospitals().values()) {
                            Section section = hospital.getSection(sectionID);
                            if (section != null) {
                                section.addDoctor(new Doctor(name, id, diplomaID, new Schedule(5, null)));
                                JOptionPane.showMessageDialog(frame, "Doktor başarıyla eklendi!");
                                crsSystem.saveTablesToDisk("veritabani.ser");
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(frame, "Bölüm bulunamadı!");
                    } catch (DuplicateInfoException ex) {
                        JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage());
                    }
                }
            }
        });




        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nameField = new JTextField();
                JTextField idField = new JTextField();
                Object[] fields = {
                        "Hasta İsmi:", nameField,
                        "Hasta Kimlik No:", idField
                };
                int option = JOptionPane.showConfirmDialog(frame, fields, "Yeni Hasta Ekle", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String name = nameField.getText();
                    long id = Long.parseLong(idField.getText());
                    crsSystem.getPatients().put(id, new Patient(name, id));
                    JOptionPane.showMessageDialog(frame, "Hasta başarıyla eklendi!");
                    crsSystem.saveTablesToDisk("veritabani.ser");
                }
            }
        });


        addHospitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField hospitalIDField = new JTextField();
                JTextField hospitalNameField = new JTextField();
                Object[] fields = {
                        "Hastane ID:", hospitalIDField,
                        "Hastane Adı:", hospitalNameField
                };

                int option = JOptionPane.showConfirmDialog(frame, fields, "Yeni Hastane Ekle", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int hospitalID = Integer.parseInt(hospitalIDField.getText());
                        String hospitalName = hospitalNameField.getText();

                        if (crsSystem.getHospitals().containsKey(hospitalID)) {
                            JOptionPane.showMessageDialog(frame, "Bu ID'ye sahip bir hastane zaten kayıtlı!", "Hata", JOptionPane.ERROR_MESSAGE);
                        } else {
                            crsSystem.getHospitals().put(hospitalID, new Hospital(hospitalID, hospitalName));
                            JOptionPane.showMessageDialog(frame, "Hastane başarıyla eklendi!");
                            crsSystem.saveTablesToDisk("veritabani.ser");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        addSectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField hospitalIDField = new JTextField();
                JTextField sectionIDField = new JTextField();
                JTextField sectionNameField = new JTextField();

                Object[] fields = {
                        "Hastane ID:", hospitalIDField,
                        "Bölüm ID:", sectionIDField,
                        "Bölüm Adı:", sectionNameField
                };

                int option = JOptionPane.showConfirmDialog(frame, fields, "Yeni Bölüm Ekle", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int hospitalID = Integer.parseInt(hospitalIDField.getText());
                        int sectionID = Integer.parseInt(sectionIDField.getText());
                        String sectionName = sectionNameField.getText();

                        Hospital hospital = crsSystem.getHospitals().get(hospitalID);
                        if (hospital != null) {
                            hospital.addSection(new Section(sectionID, sectionName, new LinkedList<>()));
                            JOptionPane.showMessageDialog(frame, "Bölüm başarıyla eklendi!");
                            crsSystem.saveTablesToDisk("veritabani.ser");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Hastane bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (DuplicateInfoException ex) {
                        JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        addRendezvousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField patientIDField = new JTextField();
                JTextField hospitalIDField = new JTextField();
                JTextField sectionIDField = new JTextField();
                JTextField doctorIDField = new JTextField();
                JTextField dateField = new JTextField("yyyy-MM-dd HH:mm");

                Object[] fields = {
                        "Hasta Kimlik No:", patientIDField,
                        "Hastane ID:", hospitalIDField,
                        "Bölüm ID:", sectionIDField,
                        "Doktor Diploma ID:", doctorIDField,
                        "Tarih (yyyy-MM-dd HH:mm):", dateField
                };

                int option = JOptionPane.showConfirmDialog(frame, fields, "Yeni Randevu Ekle", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        long patientID = Long.parseLong(patientIDField.getText());
                        int hospitalID = Integer.parseInt(hospitalIDField.getText());
                        int sectionID = Integer.parseInt(sectionIDField.getText());
                        int doctorID = Integer.parseInt(doctorIDField.getText());
                        Date desiredDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateField.getText());

                        boolean success = crsSystem.makeRendezvous(patientID, hospitalID, sectionID, doctorID, desiredDate);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Randevu başarıyla eklendi!");
                            crsSystem.saveTablesToDisk("veritabani.ser");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Randevu eklenemedi, gün dolu!", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AnaMenuGUI(crsSystem);
            }
        });




        frame.add(listDoctorsButton);
        frame.add(listPatientsButton);
        frame.add(listHospitalsButton);
        frame.add(listSectionsButton);
        frame.add(listRendezvousButton);
        frame.add(addDoctorButton);
        frame.add(addPatientButton);
        frame.add(addHospitalButton);
        frame.add(addSectionButton);
        frame.add(addRendezvousButton);
        frame.add(backButton);

        frame.setVisible(true);
    }
}
