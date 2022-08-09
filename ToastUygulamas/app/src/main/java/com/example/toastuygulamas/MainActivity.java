package com.example.toastuygulamas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button gorunum;
TextView yazıdegis;
ImageView images;
Integer sayac=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yazıdegis=(TextView) findViewById(R.id.textView);
        images=(ImageView) findViewById(R.id.imageView);


        gorunum=(Button) findViewById(R.id.button);
        gorunum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayac++;
                if(sayac%2==1) {
                    gorunum.setText("Gizle");
                    yazıdegis.setText("yazı değiştirildi");
                    yazıdegis.setTextColor(Color.BLUE);
                    images.setImageResource(R.drawable.image);
                }
                else{
                    gorunum.setText("Göster");
                    yazıdegis.setText("TextView");
                    yazıdegis.setTextColor(Color.BLACK);
                    images.setImageResource(R.drawable.ic_launcher_foreground);
                }
            }
        });
    }
}