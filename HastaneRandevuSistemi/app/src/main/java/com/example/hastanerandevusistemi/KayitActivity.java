package com.example.hastanerandevusistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitActivity extends AppCompatActivity {

    HastaVeritabani hastaVeritabani;
    EditText tc,sifre,adSoyad,telefon;
    Button btnKayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        //Oluşturduğumuz toolbar activityde görünür
        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        tc = findViewById(R.id.tcText);
        sifre = findViewById(R.id.sifreText);
        adSoyad = findViewById(R.id.adsoyadText);
        telefon = findViewById(R.id.telefonText);
        btnKayit = findViewById(R.id.buttonKayit);

        //Veritabanı baglantısı
        hastaVeritabani = new HastaVeritabani(this);
        try {
            hastaVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Hasta kayıt
            btnKayit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hastaVeritabani.hastaAdd(tc.getText().toString(),sifre.getText().toString(),adSoyad.getText().toString(),telefon.getText().toString());
                    /*if(hastaVeritabani.hastaSearch(tc.getText().toString())!=null){
                        hastaVeritabani.hastaAdd(tc.getText().toString(),sifre.getText().toString(),adSoyad.getText().toString(),telefon.getText().toString());
                        Toast.makeText(KayitActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(KayitActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(KayitActivity.this, "TC Kimlik numarası ile zaten kayıt var.", Toast.LENGTH_SHORT).show();
                */
                }
            });
    }


    //Activity'de görünen toolbar'a menüyü ekler
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //Menüdeki itema bastıgımızda çıkıs işlemini yapar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.geri_toolbar) {
            Toast.makeText(KayitActivity.this, "Geri", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(KayitActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.cıkıs_toolbar) {
            Toast.makeText(KayitActivity.this, "Kapat", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }
}