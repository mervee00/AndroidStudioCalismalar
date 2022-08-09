package com.example.notdefteri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Veritabani veritabani;
    ListView listView;
    int image[] = {R.drawable.ic_baseline_event_note_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        veritabani = Room.databaseBuilder(getApplicationContext(), Veritabani.class, "notdb").allowMainThreadQueries().build();
        Button btn_notEkle = (Button) findViewById(R.id.btnNotEkle);
        btn_notEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotEkle.class);
                startActivity(intent);
            }
        });
        List<Not> notlar = MainActivity.veritabani.dao().getNot();
        ArrayList arrayList = new ArrayList();
        ArrayList a_not=new ArrayList();
        ArrayList a_saat=new ArrayList();
        ArrayList a_tarih=new ArrayList();
        String veriler = "";
        for (Not not : notlar) {
            int id = not.getId();
            String nnotsaat = not.getNotsaat();
            String nnottarih = not.getNottarih();
            String nnot = not.getNot();
            veriler = veriler + nnot + nnotsaat + nnottarih;
            arrayList.add(veriler);
            veriler = "";
            a_not.add(nnot);
            a_saat.add(nnotsaat);
            a_tarih.add(nnottarih);
        }
        MyAdapter adapter=new MyAdapter(this,a_not,a_saat,a_tarih,image);
        listView.setAdapter(adapter);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder diologOlusturucu = new AlertDialog.Builder(MainActivity.this);
                long veriId=arrayAdapter.getItemId(position);
                String listeId=String.valueOf(veriId);

                diologOlusturucu.setMessage("Not silinsin mi?").setCancelable(false)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                VeriSil(listeId);
                            }
                        }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), NotEkle.class);
                        startActivity(intent);
                        // dialogInterface.dismiss();
                    }
                });
                diologOlusturucu.create().show();
            }
        });

    }
    private void VeriSil(String listeId){
        List<Not> notlarSil=MainActivity.veritabani.dao().getNot();
        ArrayList arrayListSil=new ArrayList();
        int veriler;
        for(Not silListe:notlarSil){
            int id=silListe.getId();
            veriler=id;
            arrayListSil.add(veriler);
            veriler=0;
        }
        String sId=listeId;
        int id=Integer.valueOf(sId);
        int eleman=(int) arrayListSil.get(id);
        Not not=new Not();
        not.setId(eleman);
        MainActivity.veritabani.dao().notSil(not);
        Toast.makeText(getApplicationContext(),"Not silindi",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList rNot;
        ArrayList rSaat;
        ArrayList rTarih;
        int rImg[];
        MyAdapter(Context c,ArrayList not,ArrayList saat,ArrayList tarih,int imgs[]){
            super(c,R.layout.activity_custom_view,R.id.txt_not,not);
            this.context=c;
            this.rNot=not;
            this.rSaat=saat;
            this.rTarih=tarih;
            this.rImg=image;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.activity_custom_view,parent,false);
            ImageView imageView=row.findViewById(R.id.resim);
            TextView myNot=row.findViewById(R.id.txt_not);
            TextView mySaat=row.findViewById(R.id.txt_saat);
            TextView myTarih=row.findViewById(R.id.txt_tarih);
            imageView.setImageResource(rImg[0]);
            myNot.setText((CharSequence) rNot.get(position));
            mySaat.setText((CharSequence) rSaat.get(position));
            myTarih.setText((CharSequence) rTarih.get(position));
            return row;
        }
    }
}