package model;
// Generated 19.May.2018 16:23:17 by Hibernate Tools 4.3.1



/**
 * Ogretimuyesi generated by hbm2java
 */
public class Ogretimuyesi  implements java.io.Serializable {


     private int ogretimuyesiid;
     private String sicilno;
     private String adsoyad;
     private String sehir;
     private String telno;

    public Ogretimuyesi() {
    }

	
    public Ogretimuyesi(int ogretimuyesiid) {
        this.ogretimuyesiid = ogretimuyesiid;
    }
    public Ogretimuyesi(int ogretimuyesiid, String sicilno, String adsoyad, String sehir, String telno) {
       this.ogretimuyesiid = ogretimuyesiid;
       this.sicilno = sicilno;
       this.adsoyad = adsoyad;
       this.sehir = sehir;
       this.telno = telno;
    }
   
    public int getOgretimuyesiid() {
        return this.ogretimuyesiid;
    }
    
    public void setOgretimuyesiid(int ogretimuyesiid) {
        this.ogretimuyesiid = ogretimuyesiid;
    }
    public String getSicilno() {
        return this.sicilno;
    }
    
    public void setSicilno(String sicilno) {
        this.sicilno = sicilno;
    }
    public String getAdsoyad() {
        return this.adsoyad;
    }
    
    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }
    public String getSehir() {
        return this.sehir;
    }
    
    public void setSehir(String sehir) {
        this.sehir = sehir;
    }
    public String getTelno() {
        return this.telno;
    }
    
    public void setTelno(String telno) {
        this.telno = telno;
    }




}

