package com.example.hastanerandevusistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class YoneticiActivity extends AppCompatActivity {

    HastaVeritabani hastaVeritabani;
    RandevuVeritabani randevuVeritabani;
    ListView listView;
    Spinner spin;
    EditText tcText;
    String tmpTc,tmpSifre,tmpAd,tmpTel,tmpId,tmphastatc,tmpdoktor,tmpbrans,tmptarih,tmpsaat;
    boolean tmp = true;
    ArrayAdapter adapter1;
    String[] secimListe = {"Hastaları Listele","Randevuları Listele"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici);

        //Oluşturduğumuz toolbar activityde görünür
        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);


        tcText = findViewById(R.id.tcText3);
        spin = findViewById(R.id.spinner4);
        listView= findViewById(R.id.listView1);

        //Veritabanı baglantıları acılır
        hastaVeritabani = new HastaVeritabani(this);
        try {
            hastaVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        randevuVeritabani = new RandevuVeritabani(this);
        try {
            randevuVeritabani.connectionOpen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //secilen adaptera göre randevu ve hasta listeleri güncellenir
        adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,secimListe);
        spin.setAdapter(adapter1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin.getSelectedItemPosition() == 0){
                    tmp = true;
                    hastaList();
                    ListViewItem();
                }
                else{
                    tmp = false;
                    randevuList();
                    ListViewItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tcText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (tmp == true){
                        String hastatc = tcText.getText().toString();
                        List<String> list = hastaVeritabani.hastaSearch(hastatc);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
                        listView.setAdapter(adapter);
                        if (tcText.length() == 0)
                            hastaList();
                    }
                    else if(tmp == false){
                        String hastatc = tcText.getText().toString();
                        List<String> list = randevuVeritabani.randevuSearch(hastatc);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
                        listView.setAdapter(adapter);
                        if (tcText.length() == 0)
                            randevuList();
                    }
                }catch(Exception ex){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //Hasta ve randevu bilgileri listelenir. Güncelleme silme yapılır.
    public void ListViewItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tmp == true) {
                    String item = listView.getItemAtPosition(position).toString();
                    String[] itemBol = item.split(" - ");
                    tmpTc = itemBol[0].toString();
                    tmpSifre = itemBol[1].toString();
                    tmpAd = itemBol[2].toString();
                    tmpTel = itemBol[3].toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(YoneticiActivity.this);
                    builder.setCancelable(true);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                    builder.setView(customLayout);
                    EditText txt_tc = customLayout.findViewById(R.id.et_text1);
                    EditText txt_sifre = customLayout.findViewById(R.id.et_text2);
                    EditText txt_ad = customLayout.findViewById(R.id.et_text3);
                    EditText txt_telefon= customLayout.findViewById(R.id.et_text4);
                    txt_tc.setText(tmpTc);
                    txt_sifre.setText(tmpSifre);
                    txt_ad.setText(tmpAd);
                    txt_telefon.setText(tmpTel);
                    //Hasta güncelleme yapılır
                    builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hastaVeritabani.hastaUpdate(tmpTc,txt_sifre.getText().toString(),txt_ad.getText().toString(),txt_telefon.getText().toString());
                            hastaList();
                        }
                    });
                    //Hasta silme yapılır
                    builder.setPositiveButton("SİL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            hastaVeritabani.hastaDelete(tmpTc);
                            hastaList();
                        }
                    });
                    builder.create().show();
                }else if(tmp == false){
                    String item = listView.getItemAtPosition(position).toString();
                    String[] itemBol = item.split(" - ");
                    tmpId = itemBol[0].toString();
                    tmphastatc = itemBol[1].toString();
                    tmpdoktor = itemBol[2].toString();
                    tmpbrans = itemBol[3].toString();
                    tmptarih = itemBol[4].toString();
                    tmpsaat = itemBol[5].toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(YoneticiActivity.this);
                    builder.setCancelable(true);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog2, null);
                    builder.setView(customLayout);
                    EditText txt_id = customLayout.findViewById(R.id.et_text1);
                    EditText txt_hastatc = customLayout.findViewById(R.id.et_text2);
                    EditText txt_doktor = customLayout.findViewById(R.id.et_text3);
                    EditText txt_branch = customLayout.findViewById(R.id.et_text4);
                    EditText txt_tarih = customLayout.findViewById(R.id.et_text5);
                    EditText txt_saat = customLayout.findViewById(R.id.et_text6);
                    txt_id.setText(tmpId);
                    txt_hastatc.setText(tmphastatc);
                    txt_doktor.setText(tmpdoktor);
                    txt_branch.setText(tmpbrans);
                    txt_tarih.setText(tmptarih);
                    txt_saat.setText(tmpsaat);
                    //Randevu güncelleme
                    builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            randevuVeritabani.randevuUpdate(tmpId,txt_hastatc.getText().toString(),txt_doktor.getText().toString(),txt_branch.getText().toString(),txt_tarih.getText().toString(),txt_saat.getText().toString());
                            randevuList();
                        }
                    });
                    //Randevu silme
                    builder.setPositiveButton("SİL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            randevuVeritabani.randevuDelete(tmpId);
                            randevuList();
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }

    //Hasta Listele
    public void hastaList(){
        List<String> list = hastaVeritabani.hastaList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);
    }

    //Randevu Listele
    public void randevuList(){
        List<String> list = randevuVeritabani.randevuList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        listView.setAdapter(adapter);
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
            Toast.makeText(YoneticiActivity.this, "Geri", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(YoneticiActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.cıkıs_toolbar) {
            Toast.makeText(YoneticiActivity.this, "Kapat", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }
}