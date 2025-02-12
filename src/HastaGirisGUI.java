
package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HastaGirisGUI {

    private CRS crsSystem;

    public HastaGirisGUI(CRS crsSystem) {
        this.crsSystem = crsSystem;


        JFrame frame = new JFrame("Hasta Giriş Ekranı");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));


        JLabel titleLabel = new JLabel("Hasta Giriş Ekranı", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel idLabel = new JLabel("Hasta Kimlik Numarası: ");
        JTextField idField = new JTextField(20);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        frame.add(inputPanel);


        JButton girisButton = new JButton("Giriş");
        frame.add(girisButton);


        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = idField.getText();

                try {
                    long patientID = Long.parseLong(input);


                    if (crsSystem.getPatients().containsKey(patientID)) {
                        Patient selectedPatient = crsSystem.getPatients().get(patientID);
                        JOptionPane.showMessageDialog(frame, "Hasta sistemde kayıtlı!", "Başarılı Giriş", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Giriş ekranını kapat
                        new HastaneSecimGUI(crsSystem, selectedPatient);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Hasta sistemde bulunamadı! Lütfen klinik personeliyle iletişime geçin.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir kimlik numarası giriniz!", "Geçersiz Giriş", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        frame.setVisible(true);
    }
}
