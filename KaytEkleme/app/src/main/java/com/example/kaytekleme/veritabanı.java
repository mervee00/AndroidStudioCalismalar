package com.example.kaytekleme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class veritabanı extends SQLiteOpenHelper {
    private static final String veritabaniAdi="kayitlar";
    private static final int Surum=1;
    public veritabanı(Context c){super(c,veritabaniAdi,null,Surum);}
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE bilgiler (ad TEXT, soyad TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS bilgiler ");
        onCreate(db);
    }
}
