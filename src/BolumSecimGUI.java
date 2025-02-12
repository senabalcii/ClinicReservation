

package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BolumSecimGUI {

    private CRS crsSystem;
    private Patient selectedPatient;
    private Hospital selectedHospital;

    public BolumSecimGUI(CRS crsSystem, Patient selectedPatient, Hospital selectedHospital) {
        this.crsSystem = crsSystem;
        this.selectedPatient = selectedPatient;
        this.selectedHospital = selectedHospital;


        JFrame frame = new JFrame("Bölüm Seçim Ekranı");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));


        JLabel titleLabel = new JLabel("Lütfen bir bölüm seçiniz: " + selectedHospital.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);


        for (Section section : selectedHospital.getSections()) {
            JButton sectionButton = new JButton(section.getName());
            sectionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Bölüm seçim ekranını kapat
                    new DoktorSecimGUI(crsSystem, selectedPatient, selectedHospital, section);
                }
            });
            frame.add(sectionButton);
        }


        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HastaneSecimGUI(crsSystem, selectedPatient);
            }
        });
        frame.add(backButton);


        frame.setVisible(true);
    }
}