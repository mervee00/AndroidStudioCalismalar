package com.example.not;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class notEkle extends AppCompatActivity {

    CalendarView takvim;
    EditText edt_aktivite;
    Button btn_saat,btn_kaydet;

    String secilenSaat,secilenTarih;
    int saat,dakika;
    int yil,gun,ay,secilenTarihID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);

        takvim=findViewById(R.id.takvim);
        edt_aktivite=findViewById(R.id.edtActivite);
        btn_saat=findViewById(R.id.btnSaat);
        btn_kaydet=findViewById(R.id.btn_Kaydet);

        takvim.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int secilenYil, int secilenAy, int secilenGun) {

                yil=secilenYil;
                gun=secilenGun;
                ay=secilenAy+1;

                String birlesik=String.valueOf(gun)+String.valueOf(ay)+String.valueOf(yil);//tarihi veritabanina 05042022 olarak kaydederiz.
                secilenTarihID=Integer.valueOf(birlesik);
                secilenTarih=gun+"/"+ay+"/"+yil;//ekrana 05/04/2022 diye yazdırırız.

            }
        });
    }

    public  void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedhour, int selectedminute) {
                saat=selectedhour;
                dakika=selectedminute;
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
        int notId=Integer.valueOf(secilenTarihID);
        String notSaat=secilenSaat;
        String notTarih=secilenTarih;
        String aktivite=edt_aktivite.getText().toString();

        if(TextUtils.isEmpty(aktivite)){
            Toast.makeText(notEkle.this,"Lütfen Bir Aktivite Girin",Toast.LENGTH_SHORT ).show();
        }
        else{
            Not not=new Not();
            not.setId(notId);
            not.setNotTarih(notTarih);
            not.setNotSaat(notSaat);

            not.setNot(aktivite);


        }
    }
}