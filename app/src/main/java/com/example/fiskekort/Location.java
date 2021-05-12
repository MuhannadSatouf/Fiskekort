package com.example.fiskekort;

import java.util.ArrayList;

public class Location {
    private static ArrayList<Area> areas = new ArrayList<>();


    public ArrayList<Area> getAreas() {
        Municipality mun1 = new Municipality("Kristianstads kommun");
        Lake l1 = new Lake("Abborragylet", 56.2982149, 14.7018751);
        Lake l2 = new Lake("Araslövssjön", 56.0579588, 14.1223577);
        Lake l3 = new Lake("Arkelstorpsviken", 56.1030141, 14.3218526);
      //  Lake l4 = new Lake("  ", 56.1030141, 14.3218526);
        ArrayList<Lake> lakes = new ArrayList<>();
        lakes.add(l1);
        lakes.add(l2);
        lakes.add(l3);
        //lakes.add(l4);


        Area krist = new Area(mun1, l1, l2, l3);


        areas.add(krist);
        return areas;
    }

    public Lake[] getAreasLakes(Municipality municipality){
        int index = areas.indexOf(municipality);
        return areas.get(index).getLakes();
    }

    public Lake[] getAreasLakes(String municipalityName){
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).getMun().getName().equals(municipalityName)){
                return areas.get(i).getLakes();
            }
        }
       return null;
    }
}
