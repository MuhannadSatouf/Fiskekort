package com.example.fiskekort;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Municipality implements Serializable {
    String name;
    String fdID;  //fireBase ID

    public Municipality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFdID() {
        return fdID;
    }

    public void setFdID(String fdID) {
        this.fdID = fdID;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("mun_id", fdID);
        return result;
    }

}