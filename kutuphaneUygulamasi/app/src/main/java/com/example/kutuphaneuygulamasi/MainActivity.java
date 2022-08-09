package com.example.kutuphaneuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.kitapekle_toolbar){
            Intent intent=new Intent(getApplicationContext(),kitapEkle_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.kitapguncelle_toolbar){
            Intent intent=new Intent(getApplicationContext(),kitapGuncelle_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.kitapsil_toolbar){
            Intent intent=new Intent(getApplicationContext(),kitapSil_Activity.class);
            startActivity(intent);
        }
        return true;
    }
}