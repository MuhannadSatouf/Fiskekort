package com.example.fiskekort.license;

public class LicenseModel {
    private String name, id, desc;
    private int view;

    public LicenseModel() {

    }

    public LicenseModel(String name, String id, String desc, int view){
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.view = view;
    }


    public String getName() {
        return name;
    }

    public LicenseModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public LicenseModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public LicenseModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public int getView() {
        return view;
    }

    public LicenseModel setView(int view) {
        this.view = view;
        return this;
    }
}
