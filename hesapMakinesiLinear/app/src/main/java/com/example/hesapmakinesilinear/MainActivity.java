package com.example.hesapmakinesilinear;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button topla;
    Button cıkar;
    Button carp;
    Button bol;
    EditText sayi1;
    EditText sayi2;
    EditText sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayi1=(EditText) findViewById(R.id.editTextNumber);
        sayi2=(EditText) findViewById(R.id.editTextNumber2);
        sonuc=(EditText) findViewById(R.id.editTextNumber3);

        topla=(Button) findViewById(R.id.button);
        topla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sayii1,sayii2,toplam;

                sayii1=Integer.parseInt(sayi1.getText().toString());

                sayii2=Integer.parseInt(sayi2.getText().toString());

                toplam=sayii1+sayii2;
                sonuc.setText(String.valueOf(toplam));
            }
        });

        cıkar=(Button) findViewById(R.id.button2);
        cıkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sayii1,sayii2,cıkart;

                sayii1=Integer.parseInt(sayi1.getText().toString());

                sayii2=Integer.parseInt(sayi2.getText().toString());

                cıkart=sayii1-sayii2;
                sonuc.setText(String.valueOf(cıkart));
            }
        });

        carp=(Button) findViewById(R.id.button3);
        carp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sayii1,sayii2,carpma;

                sayii1=Integer.parseInt(sayi1.getText().toString());

                sayii2=Integer.parseInt(sayi2.getText().toString());

                carpma=sayii1*sayii2;
                sonuc.setText(String.valueOf(carpma));
            }
        });

        bol=(Button) findViewById(R.id.button4);
        bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sayii1,sayii2,bolme;

                sayii1=Integer.parseInt(sayi1.getText().toString());

                sayii2=Integer.parseInt(sayi2.getText().toString());

                bolme=sayii1-sayii2;
                sonuc.setText(String.valueOf(bolme));
            }
        });
    }
}