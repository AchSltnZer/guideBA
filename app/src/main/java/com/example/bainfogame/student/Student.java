package com.example.bainfogame.student;

public class Student {
    private String nama,type, aff, attack, defend;
    private String img;
    private Integer star;
    public Student(String nama , String type, String aff, String attack, String defend ,String img,Integer star){
        this.nama = nama;
        this.type = type;
        this.aff = aff;
        this.attack = attack;
        this.defend = defend;
        this.img = img;
        this.star = star;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) { this.type = type; }
    public String getAff(){ return aff; }
    public void setAff(){   this.aff=aff; }
    public String getAttack() { return attack;}
    public String getDefend() { return defend;}
    public String getImg(){ return img;}
    public void setImg(String img){ this.img = img;}
    public Integer getStar(){ return star;}
    public void setStar(Integer star){ this.star = star;}
}
