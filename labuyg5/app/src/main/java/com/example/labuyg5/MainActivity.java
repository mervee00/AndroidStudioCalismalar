package com.example.labuyg5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
Button aslan;
Button maymun;
Button fare;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aslan=(Button) findViewById(R.id.button);
        aslan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image.setImageResource(R.drawable.aslan);

            }
        });

        maymun=(Button) findViewById(R.id.button2);
        maymun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image.setImageResource(R.drawable.maymun);

            }
        });

        fare=(Button) findViewById(R.id.button3);
        fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image.setImageResource(R.drawable.fare);

            }
        });
    }
}