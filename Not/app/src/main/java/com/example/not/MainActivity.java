package com.example.not;

import androidx.appcompat.app.AppCompatActivity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static veritabani veritabanim;
    ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view=findViewById(R.id.list_view);
        veritabanim= Room.databaseBuilder(getApplicationContext(),veritabani.class,"notdb")
                .allowMainThreadQueries().build();

        Button btn_notEkle=findViewById(R.id.btnNotEkle);
        btn_notEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),notEkle.class);
                startActivity(intent);
            }
        });
    }
}