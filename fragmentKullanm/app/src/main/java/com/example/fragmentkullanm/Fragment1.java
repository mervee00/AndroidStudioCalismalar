package com.example.fragmentkullanm;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment1 extends Fragment {
    Button kaydet; //Buton referansı
    EditText isim_edittext,soyisim_edittext;
    String isim,soyisim;
    KaydetInterface kaydetinterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        isim_edittext = (EditText)view.findViewById(R.id.isim_edittex);
        soyisim_edittext = (EditText)view.findViewById(R.id.soyisim_edittext);
        kaydet = (Button)view.findViewById(R.id.kaydet);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isim = isim_edittext.getText().toString();
                soyisim = soyisim_edittext.getText().toString();

                kaydetinterface = (KaydetInterface)getActivity();
                kaydetinterface.kaydetClick(isim, soyisim);
            }
        });
        return view;
    }

}
