package com.example.bainfogame.equipment;

public class eqmodel {
    String tier;
    String nama;
    String mapd;
    String type;
    String img;


    public eqmodel(String tier, String nama, String mapd, String type, String img) {
        this.tier = tier;
        this.nama = nama;
        this.mapd = mapd;
        this.type = type;
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }
    public String getTier() {
        return tier;
    }

    public String getNama() {
        return nama;
    }

    public String getMapd() {
        return mapd;
    }
}
