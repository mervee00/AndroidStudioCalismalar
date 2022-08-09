package com.example.fragmentkullanm;

import android.app.Fragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    TextView txtView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        txtView = (TextView)view.findViewById(R.id.textView1);

        return view;
    }

    public void textDegistir(String isim,String soyisim){
        txtView.setText(isim+" "+soyisim);

    }

}
