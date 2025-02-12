package ClinicReservationSystem;


import java.util.LinkedList;
import java.io.Serializable;

public class Hospital implements Serializable {

    private final int id;
    private String name;
    private LinkedList<Section> sections;
    CRS system;


    public Hospital(int id,String name){
        this.id = id;
        this.name=name;
        sections=new LinkedList<>();
    }

    public Section getSection(int id){

        for (Section s:sections){
            if (id==s.getId()){
                return s;
            }
        }

        return null;
    }


    public LinkedList<Section> getSections() {
        return sections;
    }

    public int getId() {
        return id;
    }

    private Section getSection(String name){

        for (Section s:sections){
            if (name==s.getName()){
                return s;
            }
        }

        return null;
    }


    public String getName() {
        return name;
    }

    public synchronized void addSection(Section section) throws DuplicateInfoException{

        for(Section s:sections){

            if(s.getId()==section.getId()||s.getName()==section.getName()){

                if (system.isSystemMode()){
                    throw new DuplicateInfoException("Bu bölüm zaten sisteme kayıtlı.");
                }

                else {
                    System.err.println(" Hata oluştu: Bölüm sisteme kayıtlı.");
                }

            }

        }
        sections.add(section);
    }
}
