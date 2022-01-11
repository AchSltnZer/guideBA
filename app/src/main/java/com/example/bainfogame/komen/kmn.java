package com.example.bainfogame.komen;

public class kmn {
    private String nama, komen;
    private Integer usd_id;
    private String cm_id;
    public kmn( String cm_id, String nama ,String komen ){

        this.cm_id = cm_id;
        this.nama = nama;
        this.komen = komen;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getKomen() {
        return komen;
    }
    public void setKomen(String komen) { this.komen = komen; }
    public Integer getUsd_id(){ return usd_id;}
    public void setStar(Integer usd_id){ this.usd_id = usd_id;}
    public String getCm_id(){ return cm_id;}
    public void setCm_id(String cm_id){ this.cm_id = cm_id;}
}
