package com.example.sayfalararasgecis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView ad;
TextView sifre;
Button giris;
String kuladi;
String parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad=(EditText) findViewById(R.id.editTextTextPersonName3);
        sifre=(EditText) findViewById(R.id.editTextTextPassword);


        giris=(Button) findViewById(R.id.button);
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               kuladi=ad.getText().toString();
                parola=sifre.getText().toString();
               if(kuladi.equals("191816068")&& parola.equals("gazi")){
                    Intent gecis=new Intent(MainActivity.this,Giris.class);
                    startActivity(gecis);
                }
                else{
                    Toast.makeText(MainActivity.this,"Basarısız Oldu.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}