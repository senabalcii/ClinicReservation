

package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoktorSecimGUI {

    private CRS crsSystem;
    private Patient selectedPatient;
    private Hospital selectedHospital;
    private Section selectedSection;

    public DoktorSecimGUI(CRS crsSystem, Patient selectedPatient, Hospital selectedHospital, Section selectedSection) {
        this.crsSystem = crsSystem;
        this.selectedPatient = selectedPatient;
        this.selectedHospital = selectedHospital;
        this.selectedSection = selectedSection;


        JFrame frame = new JFrame("Doktor Seçim Ekranı");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));


        JLabel titleLabel = new JLabel("Lütfen bir doktor seçiniz: " + selectedSection.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);


        for (Doctor doctor : selectedSection.getDoctors()) {
            JButton doctorButton = new JButton(doctor.getName());
            doctorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new RandevuTarihSecimGUI(crsSystem, selectedPatient, doctor);
                }
            });
            frame.add(doctorButton);
        }


        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Doktor seçim ekranını kapat
                new BolumSecimGUI(crsSystem, selectedPatient, selectedHospital);
            }
        });
        frame.add(backButton);


        frame.setVisible(true);
    }
}


