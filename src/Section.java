package ClinicReservationSystem;

import java.util.LinkedList;
import java.io.Serializable;


public class Section implements Serializable{

    private final int id;
    private String name;
    private LinkedList<Doctor> doctors;
    CRS system;

    public Section(int id, String name, LinkedList<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.doctors = doctors;
    }

    public LinkedList<Doctor> getDoctors() {
        return doctors;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }


    public void listDoctors(){
        for(Doctor doctor:doctors){
            System.out.println(doctor.toString());
        }
    }



    public Doctor getDoctor(int diploma_id){

        for (Doctor doctor:doctors){
            if(doctor.getDiploma_id()==diploma_id){
                return doctor;
            }
        }
        return null;
    }




    public synchronized void addDoctor(Doctor doctor) throws DuplicateInfoException{

        for (Doctor d:doctors){
            if (d.getDiploma_id()== doctor.getDiploma_id()){


                if (system.isSystemMode()){
                    throw new DuplicateInfoException("Bu ID'ye sahip doktor zaten sisteme kayıtlı.");
                }

                else {
                    System.err.println(" Hata oluştu: ID sisteme kayıtlı.");
                }

            }
        }
        doctors.add(doctor);
    }


}
