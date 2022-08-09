package com.example.hesapmakinesiprogrami;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    public  int ara;
    public  int sonuc;
    public  int eski;
    public String islem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editTextNumber);

    }
    public void topla(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="topla";
        eski=0; }

    public void cikar(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="cikar";
        eski=0; }

    public void carp(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="carp";
        eski=0; }

    public void bol(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="bol";
        eski=0; }

    public void mod(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="mod";
        eski=0; }

    public void ust(View view){
        ara= Integer.parseInt(editText.getText().toString());
        editText.setText("");
        islem="ust";
        eski=0; }

    public void hesapla(View view){
        if (islem=="topla"){
            sonuc=ara+Integer.parseInt(editText.getText().toString());
            textView.setText("sonuc "+sonuc);
        }
        else if (islem=="cikar"){
            sonuc=ara-Integer.parseInt(editText.getText().toString());
            textView.setText("sonuc "+sonuc);
        }
        else if (islem=="carp"){
            sonuc=ara*Integer.parseInt(editText.getText().toString());
            textView.setText("sonuc "+sonuc);
        }
        else if (islem=="bol"){
            sonuc=ara/Integer.parseInt(editText.getText().toString());
            textView.setText("sonuc "+sonuc);
        }
        else if (islem=="mod"){
            sonuc=ara%Integer.parseInt(editText.getText().toString());
            textView.setText("sonuc "+sonuc);
        }
        else if (islem=="ust"){
            sonuc=(int)Math.pow(ara,Integer.parseInt(editText.getText().toString()));
            textView.setText("sonuc "+sonuc);
        }
    }

    public void bir(View view){
        editText.setText(eski+"1");
        eski=Integer.parseInt(editText.getText().toString());
    }
    public void iki(View view){
        editText.setText(eski+"2");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void uc(View view){
        editText.setText(eski+"3");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void dort(View view){
        editText.setText(eski+"4");
        eski=Integer.parseInt(editText.getText().toString());  }
    public void bes(View view){
        editText.setText(eski+"5");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void alti(View view){
        editText.setText(eski+"6");
        eski=Integer.parseInt(editText.getText().toString());    }
    public void yedi(View view){
        editText.setText(eski+"7");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void sekiz(View view){
        editText.setText(eski+"8");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void dokuz(View view){
        editText.setText(eski+"9");
        eski=Integer.parseInt(editText.getText().toString());   }
    public void sifir(View view){
        editText.setText(eski+"0");
        eski=Integer.parseInt(editText.getText().toString());   }

    public void temizle(View view){
        editText.setText("");
        eski=0;
    }

}