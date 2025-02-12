
package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HastaneSecimGUI {

    private Patient selectedPatient;
    private CRS crsSystem;

    public HastaneSecimGUI(CRS crsSystem, Patient selectedPatient) {
        this.crsSystem = crsSystem;
        this.selectedPatient = selectedPatient;


        JFrame frame = new JFrame("Hastane Seçim Ekranı");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));


        JLabel titleLabel = new JLabel("Lütfen bir hastane seçiniz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);


        for (HashMap.Entry<Integer, Hospital> entry : crsSystem.getHospitals().entrySet()) {
            JButton hospitalButton = new JButton(entry.getValue().getName());
            hospitalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    new BolumSecimGUI(crsSystem, selectedPatient, entry.getValue());
                }
            });
            frame.add(hospitalButton);
        }

        JButton backButton = new JButton("Geri dön");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HastaGirisGUI(crsSystem);
            }
        });

        frame.add(backButton);


        frame.setVisible(true);
    }
}