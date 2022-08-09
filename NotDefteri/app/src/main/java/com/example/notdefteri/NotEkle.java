package com.example.notdefteri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NotEkle extends AppCompatActivity {
    CalendarView takvim;
    EditText edt_aktivite;
    Button btn_saat,btn_kaydet,btn_guncelle;
    String secilenSaat,secilenTarih;
    int saat,dakika;
    int yil,gun,ay,secilenTarihId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);
        takvim=findViewById(R.id.takvim);
        edt_aktivite=findViewById(R.id.edt_aktivite);
        btn_saat=findViewById(R.id.btn_saat);
        btn_kaydet=findViewById(R.id.btn_kaydet);
        btn_guncelle=findViewById(R.id.btn_guncelle);
        takvim.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                yil=year;
                ay=month;
                gun=day;
                String tamTarih=String.valueOf(gun)+String.valueOf(ay)+String.valueOf(yil);
                secilenTarihId=Integer.valueOf(tamTarih);
                secilenTarih=gun+"/"+ay+"/"+yil;
            }
        });
    }
    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                saat=selectedHour;
                dakika=selectedMinute;
                btn_saat.setText(String.format(Locale.getDefault(),"%02d:%02d",saat,dakika));
                secilenSaat=btn_saat.getText().toString();
            }
        };
        int style= AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog=new TimePickerDialog(this,style,onTimeSetListener,saat,dakika,true);
        timePickerDialog.setTitle("Saat Seciniz");
        timePickerDialog.show();
    }
    public void Kaydet(View view){
        int notId=Integer.valueOf(secilenTarihId);
        String notsaat=secilenSaat;
        String nottarih=secilenTarih;
        String aktivite=edt_aktivite.getText().toString();
        if(TextUtils.isEmpty(aktivite)){
            Toast.makeText(NotEkle.this,"Lutfen planladiniz aktiviteyi giriniz",Toast.LENGTH_SHORT).show();
        }
        else{
            Not not=new Not();
            not.setId(notId);
            not.setNotsaat(notsaat);
            not.setNottarih(nottarih);
            not.setNot(aktivite);
            MainActivity.veritabani.dao().notEkle(not);
            Toast.makeText(NotEkle.this,"Aktiviteniz basariyla olusturuldu",Toast.LENGTH_SHORT).show();
            edt_aktivite.setText("");
            btn_saat.setText("");
            Intent intent=new Intent(NotEkle.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void Guncelle(String listeId){
        List<Not> notlarGuncelle=MainActivity.veritabani.dao().getNot();
        ArrayList arrayListGuncelle=new ArrayList();
        int veriler;
        for(Not GuncelleListe:notlarGuncelle){
            int id=GuncelleListe.getId();
            veriler=id;
            arrayListGuncelle.add(veriler);
            veriler=0;
        }
        String sId=listeId;
        int id=Integer.valueOf(sId);
        int eleman=(int) arrayListGuncelle.get(id);
        Not not=new Not();
        not.setId(eleman);
        MainActivity.veritabani.dao().notGuncelle(not);

        finish();
        startActivity(getIntent());
    }
}