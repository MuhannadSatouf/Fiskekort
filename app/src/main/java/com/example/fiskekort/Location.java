package com.example.fiskekort;

import java.util.ArrayList;

public class Location {

    public ArrayList<Area> getAreas() {
        ArrayList<Area> areas = new ArrayList<>();
        ArrayList<Lake> lakes = getAllLakes();

        Municipality mun1 = new Municipality("Kristianstads kommun");

        Area krist = new Area(mun1, lakes.get(0), lakes.get(1), lakes.get(2), lakes.get(3), lakes.get(4), lakes.get(5), lakes.get(6), lakes.get(7), lakes.get(8));
        areas.add(krist);

        Municipality mun2 = new Municipality("Osby kommun");
        Area osby = new Area(mun2, lakes.get(9), lakes.get(10));
        areas.add(osby);

        Municipality mun3 = new Municipality("Svedala kommun");
        Area svedala = new Area(mun3, lakes.get(11), lakes.get(12), lakes.get(13), lakes.get(14));
        areas.add(svedala);

        Municipality mun4 = new Municipality("Ystads kommun");
        Area ystad = new Area(mun4, lakes.get(15), lakes.get(16));
        areas.add(ystad);

        Municipality mun5 = new Municipality("Sjöbo kommun");
        Area sjobo = new Area(mun5, lakes.get(16), lakes.get(17), lakes.get(18), lakes.get(19));
        areas.add(sjobo);

        Municipality mun6 = new Municipality("Hässleholms kommun");
        Area hassleholm = new Area(mun6, lakes.get(21), lakes.get(22));
        areas.add(hassleholm);

        Municipality mun7 = new Municipality("Bromölla kommun");
        Area bromolla = new Area(mun7, lakes.get(7));
        areas.add(bromolla);

        Municipality mun8 = new Municipality("Lunds kommun");
        Area lund = new Area(mun8, lakes.get(19), lakes.get(20));
        areas.add(lund);

        Municipality mun9 = new Municipality("Höör kommun");
        Area hoor = new Area(mun9, lakes.get(23), lakes.get(24), lakes.get(25));
        areas.add(hoor);

        Municipality mun10 = new Municipality("Östra Göinge kommun");
        Area ostragoinge = new Area(mun10, lakes.get(26));
        areas.add(ostragoinge);

        Municipality mun11 = new Municipality("Eslövs kommun");
        Area eslov = new Area(mun11, lakes.get(19), lakes.get(25));
        areas.add(eslov);

        Municipality mun12 = new Municipality("Hörby kommun");
        Area horby = new Area(mun12, lakes.get(24));
        areas.add(horby);

        return areas;
    }

    public Lake[] getLakesByArea(String municipalityName) {
        for (int i = 0; i < getAreas().size(); i++) {
            if (getAreas().get(i).getMun().getName().equals(municipalityName)) {
                return getAreas().get(i).getLakes();
            }
        }
        return null;
    }

    public ArrayList<Lake> getAllLakes() {
        ArrayList<Lake> lakes = new ArrayList<>();

        //Kristianstad
        Lake abborragylet = new Lake("Abborragylet", 56.2982149, 14.7018751);
        Lake araslovssjon = new Lake("Araslövssjön", 56.0579588, 14.1223577);
        Lake arkelstorpsviken = new Lake("Arkelstorpsviken", 56.1030141, 14.3218526);
        Lake baen = new Lake("Bäen", 56.2090295, 14.4544021);
        Lake filkesjon = new Lake("Filkesjön", 56.2915829, 14.4022204);
        Lake gillsjon = new Lake("Gillsjön", 56.2890709, 14.4310673);
        Lake hammarsjon = new Lake("Hammarsjön", 55.98000333995548, 14.221280450869578);
        Lake ivosjon = new Lake("Ivosjön", 56.07518315049528, 14.411953319024981);  //Bromölla  #7
        Lake rabelovssjon = new Lake("Rabelövssjön", 56.10041949978017, 14.232223867617401);
//0-8
        lakes.add(abborragylet);
        lakes.add(araslovssjon);
        lakes.add(arkelstorpsviken);
        lakes.add(baen);
        lakes.add(filkesjon);
        lakes.add(gillsjon);
        lakes.add(hammarsjon);
        lakes.add(ivosjon);
        lakes.add(rabelovssjon);

        //Osby
        Lake osbysjon = new Lake("Osbysjön", 56.3500000, 13.9833333);
        Lake vesljungasjon = new Lake("Vesljungasjön", 56.42112909771548, 13.757748660467303);
        lakes.add(osbysjon);
        lakes.add(vesljungasjon);
//9-10

        // Svedala kommun
        Lake borringesjon = new Lake("Borringesjön", 55.485911608606564, 13.315884787005805);
        Lake havgardssjon = new Lake("Havgårdssjön", 55.484172960930216, 13.356868939878268);
        Lake fjallfotasjon = new Lake("Fjällfotasjön", 55.527719547851476, 13.301392783873526);
        Lake yddingesjon = new Lake("Yddingesjön", 55.54494055343786, 13.25420480600194);
        lakes.add(borringesjon);
        lakes.add(havgardssjon);
        lakes.add(fjallfotasjon);
        lakes.add(yddingesjon);
//11-14

        //Ystads kommun
        Lake krageholmssjon = new Lake("Krageholmssjön", 55.501699999018236, 13.74415827458187);
        lakes.add(krageholmssjon);
//15

        //Sjöbo kommun
        Lake ellestadsjon = new Lake("Ellestadsjön", 55.531113651076694, 13.73342702616106); //Ystad
        Lake snogeholmssjon = new Lake("Snogeholmssjön", 55.561318731953286, 13.72372413304001);
        Lake sovdesjon = new Lake("Sövdesjön", 55.57578377109281, 13.662284067081533);
        Lake vombsjon = new Lake("Vombsjön", 55.6850753685276, 13.589313707127603);  //eslöv, lund #19
        lakes.add(ellestadsjon);
        lakes.add(snogeholmssjon);
        lakes.add(sovdesjon);
        lakes.add(vombsjon);
//16-19


        //Lunds kommun
        Lake krankesjon = new Lake("Krankesjön", 55.70007444895006, 13.479053600913856);
        lakes.add(krankesjon);

//20


        //Hässleholm
        Lake bronaSjo = new Lake("Bröna Sjö", 56.42187103024562, 13.690213257532003);
        Lake finjasjon = new Lake("Finjasjön", 56.1344074624344, 13.702793975891582);
        lakes.add(bronaSjo);
        lakes.add(finjasjon);
  //21-22

        //Höör
        Lake satoftasjon = new Lake("Sätoftasjön", 55.89389461425486, 13.546725777791218);
        Lake ostraRingsjon = new Lake("Östra Ringsjön", 55.86019066425007, 13.558398750694453);  //Hörby  #24
        Lake vastraRingsjon = new Lake("Västra Ringsjön", 55.89254701796561, 13.462783369707653); //Eslöv #25
        lakes.add(satoftasjon);
        lakes.add(ostraRingsjon);
        lakes.add(vastraRingsjon);
//23-25

        //Östra Göinge
        Lake immeln = new Lake("Immeln", 56.280004474466324, 14.332962337525835);
        lakes.add(immeln);
 //26


        return lakes;
    }

    public String[] getAllMunicipalityNames(){
        String[] names = new String[getAreas().size()];
        int i = 0;
        for (Area a: getAreas()) {
            names[i] = a.getMun().getName();
            i++;
        }
        return names;
    }

    public String[] getLakesNamesByArea(String municipalityName){
        String[] names = new String[getLakesByArea(municipalityName).length];
        int i = 0;
        for (Lake l: getLakesByArea(municipalityName)) {
            names[i] = l.getName();
            i++;
        }
        return names;
    }
}
