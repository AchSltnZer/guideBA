package com.example.bainfogame.equipment;

public class eqmodel {
    String tier;
    String nama;
    String mapd;

    public eqmodel(String tier, String nama, String mapd) {
        this.tier = tier;
        this.nama = nama;
        this.mapd = mapd;
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
