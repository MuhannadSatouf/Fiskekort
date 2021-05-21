package com.example.fiskekort;

public class Area {
    private Municipality mun;
    private Lake[] lakes;

    public Area(){}

    public Area(Municipality mun, Lake... lakes){
        this.mun = mun;
        this.lakes = lakes;
    }

    public Municipality getMun() {
        return mun;
    }

    public void setMun(Municipality mun) {
        this.mun = mun;
    }

    public Lake[] getLakes() {
        return lakes;
    }

    public void setLakes(Lake[] lakes) {
        this.lakes = lakes;
    }
}
