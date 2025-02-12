
/*
package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class RandevuTarihSecimGUI {

    public RandevuTarihSecimGUI(CRS crsSystem, Patient selectedPatient, Doctor selectedDoctor) {

        JFrame frame = new JFrame("Randevu Tarih Seçim Ekranı");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Randevu Tarih ve Saatini Seçiniz", SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel dateLabel = new JLabel("Tarih:");
        JLabel timeLabel = new JLabel("Saat:");


        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setValue(new Date());


        JComboBox<String> timeComboBox = new JComboBox<>();
        for (int i = 8; i <= 17; i++) {
            timeComboBox.addItem(String.format("%02d:00", i));
             timeComboBox.addItem(String.format("%02d:30", i));
        }

        centerPanel.add(dateLabel);
        centerPanel.add(dateSpinner);
        centerPanel.add(timeLabel);
        centerPanel.add(timeComboBox);

        frame.add(centerPanel, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton confirmButton = new JButton("Randevuyu Onayla");
        JButton cancelButton = new JButton("İptal");

        bottomPanel.add(confirmButton);
        bottomPanel.add(cancelButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Date selectedDate = (Date) dateSpinner.getValue();
                    String selectedTime = (String) timeComboBox.getSelectedItem();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectedDate);

                    int hour = Integer.parseInt(selectedTime.split(":")[0]);
                     int minute = Integer.parseInt(selectedTime.split(":")[1]);
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);

                    Date finalDateTime = calendar.getTime();


                    boolean success = selectedDoctor.getSchedule().addRendezvous(selectedPatient, finalDateTime);

                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Randevu başarıyla eklendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                        crsSystem.saveTablesToDisk("veritabani.ser");
                        frame.dispose();
                        new AnaMenuGUI(crsSystem);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Randevu eklenemedi, gün dolu!", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Bir hata oluştu. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AnaMenuGUI(crsSystem);
            }
        });




        frame.setVisible(true);
    }
}*/

package ClinicReservationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class RandevuTarihSecimGUI {

public RandevuTarihSecimGUI(CRS crsSystem, Patient selectedPatient, Doctor selectedDoctor) {

    JFrame frame = new JFrame("Randevu Tarih Seçim Ekranı");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Randevu Tarih ve Saatini Seçiniz", SwingConstants.CENTER);
    frame.add(titleLabel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(3, 2, 10, 10));

    JLabel dateLabel = new JLabel("Tarih:");
    JLabel timeLabel = new JLabel("Saat:");

    JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
    dateSpinner.setValue(new Date());

    JComboBox<String> timeComboBox = new JComboBox<>();
    for (int i = 8; i <= 17; i++) {
        timeComboBox.addItem(String.format("%02d:00", i));
        timeComboBox.addItem(String.format("%02d:30", i));
    }

    centerPanel.add(dateLabel);
    centerPanel.add(dateSpinner);
    centerPanel.add(timeLabel);
    centerPanel.add(timeComboBox);
    frame.add(centerPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());

    JButton confirmButton = new JButton("Randevuyu Onayla");
    JButton cancelButton = new JButton("İptal");

    bottomPanel.add(confirmButton);
    bottomPanel.add(cancelButton);
    frame.add(bottomPanel, BorderLayout.SOUTH);

    confirmButton.addActionListener(e -> {
        try {
            Date selectedDate = (Date) dateSpinner.getValue();
            String selectedTime = (String) timeComboBox.getSelectedItem();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);
            int hour = Integer.parseInt(selectedTime.split(":")[0]);
            int minute = Integer.parseInt(selectedTime.split(":")[1]);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date finalDateTime = calendar.getTime();

            boolean success = selectedDoctor.getSchedule().addRendezvous(selectedPatient, finalDateTime);

            if (success) {
                JOptionPane.showMessageDialog(frame, "Randevu başarıyla eklendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                crsSystem.saveTablesToDisk("veritabani.ser");
                frame.dispose();
                new AnaMenuGUI(crsSystem);
            } else {
                JOptionPane.showMessageDialog(frame, "Randevu eklenemedi, gün dolu!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Bir hata oluştu. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    });

    cancelButton.addActionListener(e -> {
        frame.dispose();
        new AnaMenuGUI(crsSystem);
    });

    frame.setLocationRelativeTo(null); // Ortala
    frame.pack(); // Dinamik boyut ayarla
    frame.setVisible(true);
}
}


