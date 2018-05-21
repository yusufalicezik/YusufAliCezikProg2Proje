/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ogretimuyesi;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author HackerRıdvan
 */
public class AkademisyenYonetici {
     private JTable akademisyenTablo;
    private final static String SORGU_KALIP = "from Ogretimuyesi m";
    private Session session;
    SessionFactory ss;
    private Vector<String> sutunlar = new Vector<>();
    private Vector<Object> satir;
    private DefaultTableModel model;
    
     
    public AkademisyenYonetici(JTable akademisyenTablo) {
        this.akademisyenTablo = akademisyenTablo;
        sutunlar.add("Akademisyen ID");
        sutunlar.add("Sicil No");
        sutunlar.add("Ad Soyad");
        sutunlar.add("Şehir");
        sutunlar.add("Tel No");
        model=(DefaultTableModel)akademisyenTablo.getModel();
        model.setColumnIdentifiers(sutunlar);
    }
    
    public void ac() {
       session=HibernateUtil.getSessionFactory().openSession();
    }

    public void kapat() {
        session.close();
    }
    
     public void akademisyenleriGetir(String aranan, String filtre) {
        String sorguMetin = "";
        if (filtre.equalsIgnoreCase("sehir")) {
            sorguMetin = SORGU_KALIP + " where m.sehir like '%" + aranan + "%'";
        }
        session.beginTransaction();
        List akademisyenList = session.createQuery(sorguMetin).list();
        session.getTransaction().commit();
        akademisyenGoster(akademisyenList);

    }
     
     
    public void getir()
     {
         String sorgum=SORGU_KALIP;
         session.beginTransaction();
         List listem=session.createQuery(sorgum).list();
           session.getTransaction().commit();
        akademisyenGoster(listem);
     }
    
    public void yeniKayitEkle(int id,String sicil,String adSoyad,String sehir,String tel)
    {
        session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        Ogretimuyesi e=new Ogretimuyesi(id, sicil, adSoyad, sehir, tel);
        session.save(e);
        tx.commit();
        getir();
    }
    
    public  void kayitSil(int gelenRow)
    {
        String silinecekID=model.getValueAt(gelenRow, 0).toString();  //silinecek id no yu aldık..
        int silinecekIDNo=Integer.valueOf(silinecekID);
        
         session=HibernateUtil.getSessionFactory().openSession();
       
         
      Ogretimuyesi ogr = session.get(Ogretimuyesi.class, silinecekIDNo); 
	         session.delete(ogr); 
	       
        getir();
        
       
    }
    
    
    public ArrayList bilgileriAl(int gelenRow)
    {
        
        ArrayList <String>donecek =new ArrayList<>();
        
        String idNo=model.getValueAt(gelenRow, 0).toString();
        String sicilNo=model.getValueAt(gelenRow, 1).toString();
        String adSoyad=model.getValueAt(gelenRow, 2).toString();
        String sehir=model.getValueAt(gelenRow, 3).toString();
        String tel=model.getValueAt(gelenRow, 4).toString();
        
        
        
        donecek.add(idNo);
        donecek.add(sicilNo);
        donecek.add(adSoyad);
        donecek.add(sehir);
        donecek.add(tel);
        
        return donecek;
                
    }
     
    
      private void akademisyenGoster(List<Ogretimuyesi> akademisyenList) {
       
        model.getDataVector().removeAllElements();
        for (Ogretimuyesi gelenAkademisyen : akademisyenList) {
            satir=new Vector();
            satir.add(gelenAkademisyen.getOgretimuyesiid());
            satir.add(gelenAkademisyen.getSicilno());
            satir.add(gelenAkademisyen.getAdsoyad());
            satir.add(gelenAkademisyen.getSehir());
            satir.add(gelenAkademisyen.getTelno());
            model.addRow(satir);
        }
    }

    public void guncelle(String gelenIdNo, String gelenSicilNo, String gelenAdSoyad, String gelenSehir, String gelenTel,int gelenRow) 
    {
        
        String guncellenecekID=model.getValueAt(gelenRow, 0).toString();  //guncellenecek id no yu aldık..
        int guncellenecekIDNo=Integer.valueOf(guncellenecekID);
      session=HibernateUtil.getSessionFactory().openSession();
      Ogretimuyesi ogr = session.get(Ogretimuyesi.class, guncellenecekIDNo); 
      
      ogr.setSicilno(gelenSicilNo);
      ogr.setAdsoyad(gelenAdSoyad);
      //ogr.setOgretimuyesiid(Integer.valueOf(gelenIdNo));
      ogr.setSehir(gelenSehir);
      ogr.setTelno(gelenTel);
	         session.update(ogr); 
	 getir();
    
    }
    
}
