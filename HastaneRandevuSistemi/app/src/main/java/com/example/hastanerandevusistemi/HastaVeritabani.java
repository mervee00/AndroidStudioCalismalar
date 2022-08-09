package com.example.hastanerandevusistemi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HastaVeritabani {
    //Hastane adında database  oluşturulur
    private static final String DATABASE_ISIM = "Hastane";
    private static final int DATABASE_VERSION = 1;
    private final Context con;
    private VeritabaniHelper veritabanihelper;
    private SQLiteDatabase veritabanim;

    //Hastalar adında Tablo oluşturulur. Tabloda tc, sifre, adSoyad ve telefon verilerini tutan tablo alanları vardır.
    private static final String DATABASE_TABLO = "hastalar";
    public static final String HASTA_TC = "tc";
    public static final String HASTA_SIFRE = "sifre";
    public static final String HASTA_ADSOYAD = "adSoyad";
    public static final String HASTA_TELEFON = "telefon";

    public HastaVeritabani(Context c) {
        this.con = c;
    }

    //Veritabaı baglantı acılır
    public HastaVeritabani connectionOpen() throws SQLException {
        veritabanihelper = new VeritabaniHelper(con);
        veritabanim = veritabanihelper.getWritableDatabase();
        return this;
    }

    //Veritabaı baglantı kapatılır
    public void connectionClose() {
        veritabanihelper.close();
    }

    //Tabloya hasta ekler
    public void hastaAdd(String tc, String sifre, String adsoyad, String telefon){
           Cursor cursor = veritabanim.query("hastalar", null, " tc=?", new String[]{tc}, null, null, null);
           if (cursor.getCount() < 1) {
               ContentValues val = new ContentValues();
               val.put("tc", tc);
               val.put("sifre", sifre);
               val.put("adsoyad", adsoyad);
               val.put("telefon", telefon);
               veritabanim.insert("hastalar", null, val);
               cursor.close();
           }
    }

    //Hasta giris tc ile arar sifreyi bulur. Giris işlemi yapılır
    public String hastaLogin(String tc)
    {
        Cursor cursor = veritabanim.query("hastalar", null, " tc=?", new String[]{tc}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String sifre = cursor.getString(cursor.getColumnIndex("sifre"));
        cursor.close();
        return sifre;
    }

    //Hasta Listeleme
    public List<String> hastaList(){
        List<String> veriler = new ArrayList<String>();
        veritabanim  = veritabanihelper.getReadableDatabase();
        try {
            String[] sutunlar = {HASTA_TC,HASTA_SIFRE,HASTA_ADSOYAD,HASTA_TELEFON};
            Cursor cursor = veritabanim.query(DATABASE_TABLO, sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()){
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3));
            }
        }catch (Exception e){
        }
        return veriler;
    }

    //Hasta arama
    public List<String> hastaSearch(String hastatc){
        List<String> veriler = new ArrayList<String>();
        veritabanim  = veritabanihelper.getReadableDatabase();
        try {
            String selectQuery = "SELECT * FROM " + DATABASE_TABLO + " WHERE tc="+hastatc;
            Cursor cursor = veritabanim.rawQuery(selectQuery, null);
            while (cursor.moveToNext()){
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3));
            }
        }catch (Exception e){
        }
        return veriler;
    }

    //Hasta bilgileri getirir
    public String[] hastaBring(String tc){

        Cursor cursor = veritabanim.query("hastalar", null, " tc=?", new String[]{tc}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String sifre = cursor.getString(cursor.getColumnIndex("sifre"));
        String adsoyad  = cursor.getString(cursor.getColumnIndex("adSoyad"));
        String tel  = cursor.getString(cursor.getColumnIndex("telefon"));
        String[] hasta = {sifre,adsoyad,tel};
        cursor.close();
        return hasta;
    }

    //Hasta Sil
    public void hastaDelete (String tc) {
        veritabanim.delete("hastalar","tc=?",new String[]{tc});
    }

    //Hasta Güncelle
    public void hastaUpdate(String updateTc, String updateSifre, String updateAdsoyad, String updateTelefon) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(HASTA_SIFRE, updateSifre);
        cvUpdate.put(HASTA_ADSOYAD, updateAdsoyad);
        cvUpdate.put(HASTA_TELEFON, updateTelefon);
        veritabanim.update("hastalar",cvUpdate,"tc=?",new String[]{updateTc});
    }

    private static class VeritabaniHelper extends SQLiteOpenHelper {
        public VeritabaniHelper(Context contextim) {
            super(contextim, DATABASE_ISIM, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLO + " (" + HASTA_TC + " TEXT UNIQUE NOT NULL, " + HASTA_SIFRE + " TEXT NOT NULL, " + HASTA_ADSOYAD + " TEXT NOT NULL, " + HASTA_TELEFON + " TEXT NOT NULL);");
         }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLO);
            onCreate(db);
        }
    }
}

