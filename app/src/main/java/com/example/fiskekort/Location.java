package com.example.fiskekort;

import java.util.ArrayList;

public class Location {

    public ArrayList<Area> getAreas() {
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Lake> lakes = new ArrayList<>();
        getAllLakes(lakes);
        Municipality mun1 = new Municipality("Kristianstads kommun");

        Area krist = new Area(mun1, lakes.get(0), lakes.get(1), lakes.get(2), lakes.get(3), lakes.get(4), lakes.get(5));
        areas.add(krist);

        Municipality mun2 = new Municipality("Osby kommun");

        Area osby = new Area(mun2, lakes.get(6));
        areas.add(osby);

        return areas;
    }
/*
    public Lake[] getLakesByArea(Municipality municipality){
        for (int i = 0; i < getAreas().size(); i++) {
            if (getAreas().get(i).getMun().equals(municipality)){
                return getAreas().get(i).getLakes();
            }
        }

        return null;
    }*/

    public Lake[] getLakesByArea(String municipalityName) {
        for (int i = 0; i < getAreas().size(); i++) {
            if (getAreas().get(i).getMun().getName().equals(municipalityName)) {
                return getAreas().get(i).getLakes();
            }
        }
        return null;
    }

    public ArrayList<Lake> getAllLakes(ArrayList <Lake> lakeArrayList) {
        ArrayList<Lake> lakes = new ArrayList<>();
        //Not sorted by Municipality yet
        Lake Borringsjon = new Lake("Borringsjon", 55.485911608606564, 13.315884787005805);
        Lake Havgardssjon = new Lake("Havgardssjon", 55.484172960930216, 13.356868939878268);
        Lake Fjallfotasjon = new Lake("Fjallfotasjon", 55.527719547851476, 13.301392783873526);
        Lake Yddingesjon = new Lake("Yddingesjön", 55.54494055343786, 13.25420480600194);
        Lake Krageholmssjon = new Lake("Krageholmssjon", 55.501699999018236, 13.74415827458187);
        Lake Ellestadsjon = new Lake("Ellestadsjön", 55.531113651076694, 13.73342702616106);
        Lake Snogholmssjon = new Lake("Snogholmssjon", 55.561318731953286, 13.72372413304001);
        Lake Sovdesjon = new Lake("Sovdesjon", 55.57578377109281, 13.662284067081533);
        Lake Vombsjon = new Lake("Vombsjon", 55.6850753685276, 13.589313707127603);
        Lake Krankesjon = new Lake("Krankesjon", 55.70007444895006, 13.479053600913856);
        Lake OstraRingsjon = new Lake("OstraRingsjon", 55.86019066425007, 13.558398750694453);
        Lake VastraRingsjon = new Lake("VastraRingsjon", 55.89254701796561, 13.462783369707653);
        Lake Satoftasjon = new Lake("Satoftasjon", 55.89389461425486, 13.546725777791218);
        Lake Finjasjon = new Lake("Finjasjon", 56.1344074624344, 13.702793975891582);
        Lake Kosen = new Lake("Kosen", 56.78997919458905, 13.761014112231528);
        Lake Hammarsjon = new Lake("Hammarsjon", 55.98000333995548, 14.221280450869578);
        Lake Ivosjon = new Lake("Ivosjon", 56.07518315049528, 14.411953319024981);
        Lake Rabelovssjon = new Lake("Rabelovssjon", 56.10041949978017, 14.232223867617401);
        Lake Araslovsjon = new Lake("Araslovsjon", 56.06004298829865, 14.118069059078397);
        Lake Immeln = new Lake("Immeln", 56.280004474466324, 14.332962337525835);
        Lake Vesljungasjon = new Lake("Vesljungasjon", 56.42112909771548, 13.757748660467303);
        Lake BronaSjo = new Lake(" BronaSjo", 56.42187103024562, 13.690213257532003);




        //Kristianstad
        Lake l1 = new Lake("Abborragylet", 56.2982149, 14.7018751);
        Lake l2 = new Lake("Araslövssjön", 56.0579588, 14.1223577);
        Lake l3 = new Lake("Arkelstorpsviken", 56.1030141, 14.3218526);
        Lake l4 = new Lake("Bäen", 56.2090295, 14.4544021);
        Lake l5 = new Lake("Filkesjön", 56.2915829, 14.4022204);
        Lake l6 = new Lake("Gillsjön", 56.2890709, 14.4310673);

        //Osby
        Lake l101 = new Lake("Osbysjön", 56.3500000, 13.9833333);

        lakeArrayList.add(l1);
        lakeArrayList.add(l2);
        lakeArrayList.add(l3);
        lakeArrayList.add(l4);
        lakeArrayList.add(l5);
        lakeArrayList.add(l6);
        lakeArrayList.add(Borringsjon);
        lakeArrayList.add(Havgardssjon);
        lakeArrayList.add(Fjallfotasjon);
        lakeArrayList.add(Yddingesjon);
        lakeArrayList.add(Krageholmssjon);
        lakeArrayList.add(Ellestadsjon);
        lakeArrayList.add(Snogholmssjon);
        lakeArrayList.add(Sovdesjon);
        lakeArrayList.add(Vombsjon);
        lakeArrayList.add(Krankesjon);
        lakeArrayList.add(OstraRingsjon);
        lakeArrayList.add(VastraRingsjon);
        lakeArrayList.add(Satoftasjon);
        lakeArrayList.add(Finjasjon);
        lakeArrayList.add(Kosen);
        lakeArrayList.add(Hammarsjon);
        lakeArrayList.add(Ivosjon);
        lakeArrayList.add(Rabelovssjon);
        lakeArrayList.add(Araslovsjon);
        lakeArrayList.add(Immeln);
        lakeArrayList.add(Vesljungasjon);
        lakeArrayList.add(BronaSjo);

        lakes.add(l101);
        return lakeArrayList;
    }
}
