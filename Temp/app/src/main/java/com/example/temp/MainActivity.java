package com.example.temp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_MESSAGE = "com.example.temp.MESSAGE";
    EditText ad, soyad, dt;
    String bolum, dy, uni;
    Spinner spinner_dy, spinner_uni, spinner_bolum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {

        spinner_dy = (Spinner) findViewById(R.id.spinner_dy);
        spinner_dy.setOnItemSelectedListener(this);

        spinner_uni = (Spinner) findViewById(R.id.spinner_uni);
        spinner_uni.setOnItemSelectedListener(this);

        spinner_bolum = (Spinner) findViewById(R.id.spinner_bolum);
        spinner_bolum.setOnItemSelectedListener(this);



    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String secim = parent.getItemAtPosition(pos).toString();

        switch (parent.getId()){
            case R.id.spinner_bolum:
                bolum = secim;

                if(!bolum.equals("Seciniz"))
                    Toast.makeText(getApplicationContext(),
                        "Okudugu Bolum: " + bolum,
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.spinner_uni:
                uni = secim;

                if(!uni.equals("Seciniz"))
                    Toast.makeText(getApplicationContext(),
                        "Okudugu Universite: " + uni,
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.spinner_dy:
                dy = secim;

                if(!dy.equals("Seciniz"))
                    Toast.makeText(getApplicationContext(),
                        "Dogum yeri: " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
                break;
            }



    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void butonaBasildi(View view) {

        Intent intent = new Intent(this, MainActivity2.class);
        ad = findViewById(R.id.editText_ad);
        soyad = findViewById(R.id.editText_soyad);
        dt = findViewById(R.id.editText_dt);
        bolum = (bolum.equals("Seciniz"))?"Secilmedi":bolum;
        uni = (uni.equals("Seciniz"))?"Secilmedi":uni;
        dy = (dy.equals("Seciniz"))?"Secilmedi":dy;


        String adGonder = (ad.getText().length()==0)?"Ad: Girilmedi":"Ad: "+ad.getText();
        String soyadGonder = (soyad.getText().length()==0)?"Soyad: Girilmedi":"Soyad: "+soyad.getText();
        String dtGonder = (dt.getText().length()==0)?"Dogum Tarihi: Girilmedi":"Dogum Tarihi: "+dt.getText();
        String dyGonder = "Dogum Yeri: " + dy;
        String uniGonder = "Universite: " + uni;
        String bolumGonder = "Bolum: " + bolum;

        String gonder = adGonder + "\n" +
                soyadGonder + "\n" +
                dtGonder + "\n" +
                dyGonder + "\n" +
                uniGonder + "\n" +
                bolumGonder;
        intent.putExtra(EXTRA_MESSAGE, gonder);
        startActivity(intent);

    }

}
