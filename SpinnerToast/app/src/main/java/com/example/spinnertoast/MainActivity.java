package com.example.spinnertoast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] city= { "Ankara", "İstanbul", "Eskişehir", "İzmir", "Bursa"  };
    ArrayAdapter aa;
    Spinner spin;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimlama();
        veriCek();
        veriDoldur();
        islemYap();
        textView1=findViewById(R.id.);
    }
    public void tanimlama() {
        spin= findViewById(R.id.spinner1); // Bir spinner oluştur
    }
    public void veriCek() {
        aa=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, city); // Bir ArrayAdapter oluştur ve şehir listesini ekle. this dediği mainActivity
    }
    public void veriDoldur() {
        spin.setAdapter(aa); // ArrayAdapter verilerini Spinner ile bağla
    }
    public void islemYap() {
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // Bir eleman seçildiğinde yapılacakları ayarla
                Toast.makeText(getApplicationContext(),spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
               /*  veya
               String text= parent.getItemAtPosition(position).toString();
               Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                */
                Toast.makeText(getApplicationContext(), "SEÇİLEN ELEMAN: " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "SEÇİLEN ELEMANIN SIRASI: " + parent.getItemIdAtPosition(position), Toast.LENGTH_LONG).show();
                textView1.setText(city[position] + "  iline hoşgeldiniz!");

                //Uygulama ilk çalıştığında toast mesajı da gözüküyor. Bunu değiştiremedim. Bu konuda önerisi olan varsa eposta gönderebilir :)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { // Hiç bir eleman seçilmediğinde yapılacakları ayarla
            }
        });
    }
}
