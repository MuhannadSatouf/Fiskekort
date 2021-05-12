package com.example.fiskekort;

import java.io.Serializable;

public class Location implements Serializable {
    Municipality mun;
    Lake[] lakes;

    public Location(){}

    public Location(Municipality mun, Lake... lakes){
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
