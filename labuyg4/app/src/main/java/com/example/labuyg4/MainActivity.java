package com.example.labuyg4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button yaziDegis;
    Button cikis;
    TextView yazı;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yazı=(TextView) findViewById(R.id.textView);


        yaziDegis=(Button) findViewById(R.id.button);
        yaziDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yazı.setText("yazı değiştirildi");
                yazı.setTextColor(Color.BLUE);



            }
        });

        cikis=(Button) findViewById(R.id.button2);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                System.exit(0);



            }
        });

    }
}