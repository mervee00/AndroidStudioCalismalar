package com.example.kaytekleme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button kaydet;
Button goster;
EditText ad;
EditText soyad;
private veritabanı v1;
EditText adlar;
EditText soyadlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1=new veritabanı(this);
        kaydet=findViewById(R.id.kaydetbutton);
        goster=findViewById(R.id.listelebutton);
        ad=findViewById(R.id.adeditText);
        soyad=findViewById(R.id.soyadeditText);
        adlar=findViewById(R.id.adlareditText);
        soyadlar=findViewById(R.id.soyadlareditText);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ekleme(ad.getText().toString(),soyad.getText().toString());
                }
                finally {
                    v1.close();
                }
            }
        });
        goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bilgileriGoster();
            }
        });
    }


    private void ekleme(String ad,String soyad){
        SQLiteDatabase db=v1.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put("ad",ad);
        cv1.put("soyad",soyad);
        db.insertOrThrow("bilgiler",null,cv1);
    }
    private String[] sutunlar={"ad","soyad"};
    private void bilgileriGoster(){
        SQLiteDatabase db=v1.getReadableDatabase();
        Cursor okunanlar=db.query("bilgiler",sutunlar,null,null,null,null,null);
        while(okunanlar.moveToNext()){
            @SuppressLint("Range") String add=okunanlar.getString(okunanlar.getColumnIndex("ad"));
            @SuppressLint("Range") String soyadd=okunanlar.getString(okunanlar.getColumnIndex("soyad"));
            adlar.setText(add);
            soyadlar.setText(soyadd);
        }

    }
}