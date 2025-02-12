

package ClinicReservationSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AnaMenuGUI {

    private CRS crsSystem;

    public AnaMenuGUI(CRS crsSystem) {
        this.crsSystem = crsSystem;


        JFrame frame = new JFrame("Ana Menü");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        JLabel titleLabel = new JLabel("Klinik Sistemine Hoşgeldiniz", SwingConstants.CENTER);
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.add(titleLabel);


        JButton personelGirisButton = new JButton("Personel Girişi");
        JButton hastaGirisButton = new JButton("Hasta Girişi");


        personelGirisButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        hastaGirisButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        frame.add(personelGirisButton);
        frame.add(hastaGirisButton);


        personelGirisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new PersonelAnaMenuGUI(crsSystem);
            }
        });



        hastaGirisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HastaGirisGUI(crsSystem);
            }
        });




        frame.setVisible(true);
    }


    public static void main(String[] args) {


        CRS crs = new CRS();

        crs.systemMode=false;


        String filePath = "veritabani.ser";

        crs.loadTablesToDisk(filePath);



        new AnaMenuGUI(crs);
    }
}
