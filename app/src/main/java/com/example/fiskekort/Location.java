package com.example.fiskekort;

import java.util.ArrayList;

public class Location {

    public ArrayList<Area> getAreas() {
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Lake> lakes = getAllLakes();
        Municipality mun1 = new Municipality("Kristianstads kommun");

        Area krist = new Area(mun1, lakes.get(0), lakes.get(1), lakes.get(2), lakes.get(3), lakes.get(4), lakes.get(5));
        areas.add(krist);

        Municipality mun2 = new Municipality("Osby kommun");

        Area osby = new Area(mun2, lakes.get(6));
        areas.add(osby);

        return areas;
    }

    public Lake[] getLakesByArea(String municipalityName){
        for (int i = 0; i < getAreas().size(); i++) {
            if (getAreas().get(i).getMun().getName().equals(municipalityName)){
                return getAreas().get(i).getLakes();
            }
        }
       return null;
    }

    public ArrayList<Lake> getAllLakes(){
        ArrayList<Lake> lakes = new ArrayList<>();
        //Kristianstad
        Lake l1 = new Lake("Abborragylet", 56.2982149, 14.7018751);
        Lake l2 = new Lake("Araslövssjön", 56.0579588, 14.1223577);
        Lake l3 = new Lake("Arkelstorpsviken", 56.1030141, 14.3218526);
        Lake l4 = new Lake("Bäen", 56.2090295, 14.4544021);
        Lake l5 = new Lake("Filkesjön", 56.2915829, 14.4022204);
        Lake l6 = new Lake("Gillsjön", 56.2890709, 14.4310673);

        //Osby
        Lake l101 = new Lake("Osbysjön", 56.3500000, 13.9833333);

        lakes.add(l1);
        lakes.add(l2);
        lakes.add(l3);
        lakes.add(l4);
        lakes.add(l5);
        lakes.add(l6);
        lakes.add(l101);
        return lakes;
    }
}
