package com.example.bainfogame.Adapter;

public class Items {
    private String nama;
    private Integer img;
    public Items(String nama,Integer img) {
        this.nama = nama;
        this.img = img;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public Integer getImg(){ return img;}
    public void setImg(Integer img){ this.img = img;}
}

