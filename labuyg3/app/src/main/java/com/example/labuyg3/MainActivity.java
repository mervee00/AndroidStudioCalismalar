package com.example.labuyg3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
Button sonuc;
 EditText sayi1;
 EditText sayi2;
EditText toplam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sayi1=(EditText) findViewById(R.id.editTextNumber);
        sayi2=(EditText) findViewById(R.id.editTextNumber2);
        toplam=(EditText) findViewById(R.id.editTextNumber3);

        sonuc=(Button) findViewById(R.id.button);
        sonuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sayii1,sayii2,toplamm;

                sayii1=Integer.parseInt(sayi1.getText().toString());

                sayii2=Integer.parseInt(sayi2.getText().toString());

                toplamm=sayii1+sayii2;
                toplam.setText(String.valueOf(toplamm));


            }
        });

    }
}