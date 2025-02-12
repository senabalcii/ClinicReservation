package ClinicReservationSystem;

public class Doctor extends Person{

    private final int diploma_id;
    private Schedule schedule;

    public Doctor(String name , long national_id , int diploma_id,Schedule schedule){
        super(name, national_id);
        this.diploma_id=diploma_id;
        this.schedule=schedule;
    }




    public int getDiploma_id(){
        return diploma_id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "diploma_id=" + diploma_id +
                " name: "+getName()+
                '}';
    }
}
