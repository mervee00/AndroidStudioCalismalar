package com.example.hastanerandevusistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    HastaVeritabani hastaVeritabani;
    Button btnGiris,btnUyeOl,btnYoneticiGiris,btnCikis;
    EditText tc,sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Oluşturduğumuz toolbar activityde görünür
        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        tc = findViewById(R.id.tcText);
        sifre = findViewById(R.id.sifreText);

        //Yeni bir veritabanı açılır ve Veritabani class ile baglantı oluşturulur.
        hastaVeritabani = new HastaVeritabani(this);
        try {
            hastaVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Veritabanında kayıtlı ise randevuactivity yönlendirir
        btnGiris = findViewById(R.id.buttonHGiris);
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hastatc = tc.getText().toString();
                String hastapassword = sifre.getText().toString();
                String sifre = hastaVeritabani.hastaLogin(hastatc);
                //Hasta tc ve sifre karşılaştırır
                if(hastapassword.equals(sifre))
                {
                    Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_LONG).show();
                    Intent intentlogin = new Intent(getApplicationContext(),RandevuActivity.class);
                    intentlogin.putExtra("hastatc", hastatc);
                    startActivity(intentlogin);
                } else
                {
                    Toast.makeText(MainActivity.this, "Hasta TC No veya şifresi yanlış!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Hasta Kayıt Activity'e yönlendirir
        btnUyeOl = findViewById(R.id.buttonKayitOl);
        btnUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, KayitActivity.class);
                startActivity(intent);
            }
        });

        //TC ve şifre dogru ise Yönetici Activity'e yönlendirir
        btnYoneticiGiris = findViewById(R.id.buttonYGiris);
        btnYoneticiGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc.getText().toString().equals("1234") && sifre.getText().toString().equals("1234")){
                    Intent intent=new Intent(MainActivity.this, YoneticiActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(MainActivity.this, "Yönetici TC No veya şifresi yanlış!", Toast.LENGTH_LONG).show();
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
        if (id == R.id.cıkıs_toolbar) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }
}