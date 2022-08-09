package com.example.hastanerandevusistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RandevuActivity extends AppCompatActivity {

    HastaVeritabani hastaVeritabani;
    RandevuVeritabani randevuVeritabani;
    ArrayList<HashMap<String, String>> randevu_liste;
    String[] hasta;
    Button btnRandevuAl,btnRandevularim,btnBilgiler;
    CalendarView calender;
    Spinner spinDoktor,spinBransh,spinSaat;
    ArrayAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7,adapter8;
    String tmpSaat,tmpBrans,tmpDoktor,tmpTarih;
    String[] saat = {"09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00"};

    String[] doktorGenelcerrahi = {"Dr. Merve","Dr. Mehmet"};
    String[] doktorCocuk = {"Dr. Aliye","Dr. Mustafa","Dr. Selin"};
    String[] doktorCildiye = {"Dr. Mehmet"};
    String[] doktorGöz = {"Dr. Hidayet","Dr. Ayşe","Dr. Serhat"};
    String[] doktorPsikiyatri = {"Dr. Burak","Dr. Fatma"};
    String[] doktorBeslenme = {"Dr. Ahmet","Dr. Fatih"};
    String tmpTc,tmpAd,tmpSifre,tmpTel;
    String[] brans = {"BESLENME VE DİYET","GENEL CERRAHİ","ÇOCUK HASTALIKLARI","DERMATOLOJİ (CİLDİYE)","GÖZ HASTALIKLARI","PSİKİYATRİ"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);

        //Oluşturduğumuz toolbar activityde görünür
        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        //Veritabanı baglantıları
        randevuVeritabani = new RandevuVeritabani(this);
        try {
            randevuVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        hastaVeritabani = new HastaVeritabani(this);
        try {
            hastaVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorGenelcerrahi);
        adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorCocuk);
        adapter3 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorCildiye);
        adapter4 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorGöz);
        adapter5 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorPsikiyatri);
        adapter6 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorBeslenme);

        btnRandevuAl = findViewById(R.id.buttonRandevuAl);
        btnRandevularim = findViewById(R.id.buttonRandevularım);
        btnBilgiler = findViewById(R.id.buttonBilgilerim);
        calender = findViewById(R.id.calendarView);
        spinBransh = findViewById(R.id.spinnerBranch);
        spinDoktor = findViewById(R.id.spinnerDoktor);
        spinSaat = findViewById(R.id.spinnerSaat);

        adapter7 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, saat);
        spinSaat.setAdapter(adapter7);
        spinSaat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tmpSaat = saat[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Secilen bransh'a göre doktor listesi güncellenir
        adapter8 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,brans);
        spinBransh.setAdapter(adapter8);
        spinBransh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tmpBrans = brans[position];
                if (spinBransh.getSelectedItemPosition() == 0){
                    spinDoktor.setAdapter(adapter1);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorGenelcerrahi[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (spinBransh.getSelectedItemPosition() == 1){
                    spinDoktor.setAdapter(adapter2);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorCocuk[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spinBransh.getSelectedItemPosition() == 2){
                    spinDoktor.setAdapter(adapter3);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorCildiye[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spinBransh.getSelectedItemPosition() == 3){
                    spinDoktor.setAdapter(adapter4);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorGöz[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spinBransh.getSelectedItemPosition() == 4){
                    spinDoktor.setAdapter(adapter5);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorPsikiyatri[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spinBransh.getSelectedItemPosition() == 5){
                    spinDoktor.setAdapter(adapter6);
                    spinDoktor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorBeslenme[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Takvimde seçilen tarih
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int yil, int ay, int gun) {
                tmpTarih = gun + "/" + (ay+1) + "/" + yil;
            }
        });


        //Randevu kayıt
        String hastatc = getIntent().getExtras().getString("hastatc");
        btnRandevuAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randevuVeritabani.randevuAdd(hastatc,tmpDoktor,tmpBrans,tmpTarih,tmpSaat);
                Toast.makeText(RandevuActivity.this, "Randevu alma işlemi başarılı", Toast.LENGTH_SHORT).show();
            }
        });



        // Kişi bilgileri görüntülenir
        btnBilgiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasta = hastaVeritabani.hastaBring(hastatc);
                tmpSifre = hasta[0];
                tmpAd = hasta[1];
                tmpTel = hasta[2];

                AlertDialog.Builder builder = new AlertDialog.Builder(RandevuActivity.this);
                builder.setCancelable(true);
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                builder.setView(customLayout);
                EditText txt_Tc = customLayout.findViewById(R.id.et_text1);
                EditText txt_Sifre = customLayout.findViewById(R.id.et_text2);
                EditText txt_Adsoyad = customLayout.findViewById(R.id.et_text3);
                EditText txt_Telefon = customLayout.findViewById(R.id.et_text4);
                txt_Tc.setText(hastatc);
                txt_Sifre.setText(tmpSifre);
                txt_Adsoyad.setText(tmpAd);
                txt_Telefon.setText(tmpTel);
                builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                //hasta bilgileri güncellenir
                builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hastaVeritabani.hastaUpdate(hastatc,txt_Sifre.getText().toString(),txt_Adsoyad.getText().toString(),txt_Telefon.getText().toString());
                    }
                });
                builder.create().show();
            }
        });

        //
        AlertDialog.Builder builder = new AlertDialog.Builder(RandevuActivity.this);
        builder.setTitle("Randevularım");
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        //Hastaya ait randevular listelenir
        btnRandevularim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randevu_liste = randevuVeritabani.randevuBring(hastatc);
                String randevular = "";
                for(int i=0;i<randevu_liste.size();i++){
                    tmpDoktor = randevu_liste.get(i).get("doktor");
                    tmpBrans = randevu_liste.get(i).get("brans");
                    tmpTarih = randevu_liste.get(i).get("tarih");
                    tmpSaat = randevu_liste.get(i).get("saat");
                    randevular += (i+1)+") Doktor: "+tmpDoktor+"\nPolikinlik: "+tmpBrans+"\nTarih: "+tmpTarih+" "+tmpSaat+"\n";
                }
                builder.setMessage(randevular);
                builder.show();
            }
        });

    }

    //Activity'de görünen toolbar'a menüyü ekler
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //Menüdeki itema bastıgımızda çıkıs ve geri işlemini yapar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.geri_toolbar) {
            Toast.makeText(RandevuActivity.this, "Geri", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(RandevuActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.cıkıs_toolbar) {
            Toast.makeText(RandevuActivity.this, "Kapat", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }
}