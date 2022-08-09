package com.example.labuyg2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button ekle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekle=(Button) findViewById(R.id.button);
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ekle.setText("yazı değiştirildi");
                ekle.setTextColor(Color.BLUE);
            }
        });
    }
}