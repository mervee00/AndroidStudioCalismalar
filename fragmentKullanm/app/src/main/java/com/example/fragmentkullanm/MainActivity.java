package com.example.fragmentkullanm;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame1,new Fragment1());
        ft.add(R.id.frame2,new Fragment2());
        ft.commit();

    }


    //@Override yazınca error: method does not override or implement a method from a supertype hatası veriyor.
    //bu sekilde yazmadan bırakırsam kaydet butonuna basınca kapanıyor.
    public void kaydetClick(String isim, String soyisim) {
        FragmentManager fm = getFragmentManager();
        Fragment2 f2 = (Fragment2)fm.findFragmentById(R.id.frame2);
        f2.textDegistir(isim,soyisim);

    }

}