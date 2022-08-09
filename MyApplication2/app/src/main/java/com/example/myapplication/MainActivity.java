package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button toplama, cikarma, carpma, bolme, silme, hesaplama;
    TextView textView;
    Double ilkSayi;
    String islemDurum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        toplama = (Button) findViewById(R.id.toplama);
        cikarma = (Button) findViewById(R.id.cikarma);
        carpma = (Button) findViewById(R.id.carpma);
        bolme = (Button) findViewById(R.id.bolme);
        silme = (Button) findViewById(R.id.silme);
        hesaplama = (Button) findViewById(R.id.hesapla);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("0");

        ilkSayi = 0.0;
        islemDurum = "0";

        silme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });

        hesaplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SEMBOL("=");
            }
        });

        toplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SEMBOL("+");
            }
        });

        cikarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SEMBOL("-");
            }
        });
        carpma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SEMBOL("*");
            }
        });
        bolme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SEMBOL("/");
            }
        });


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(0);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NUMARA(9);
            }
        });


    }

    private void NUMARA(int sayi)
    {
        if(textView.getText().toString() == "0"){
            textView.setText("");
        }
        else if(
                textView.getText().toString() == "+" ||
                        textView.getText().toString() == "*" ||
                        textView.getText().toString() == "/"||
                        textView.getText().toString() == "-"        )
        {
            textView.setText("");
        }
        textView.setText(textView.getText() + String.valueOf(sayi));
    }


    private void SEMBOL(String sembol)
    {
        switch (sembol)
        {
            default:
            {
                if(textView.getText().toString() == "+" || textView.getText().toString() == "*"
                        || textView.getText().toString() == "/" || textView.getText().toString() == "-")
                {
                    if(islemDurum == "*" || islemDurum == "/")
                        ilkSayi = -1*ilkSayi;

                    textView.setText(sembol);
                    islemDurum = sembol;
                }
                else
                {
                    ilkSayi = Double.parseDouble(textView.getText().toString());
                    textView.setText(sembol);
                    islemDurum = sembol;
                }
            }
            break;
            case "=":
            {
                if(textView.getText().toString() == "+" || textView.getText().toString() == "*"
                        || textView.getText().toString() == "/" || textView.getText().toString() == "-")
                {

                }
                else
                {
                    switch (islemDurum)
                    {
                        default:
                        {
                            textView.setText(ilkSayi.toString());
                        }
                        break;
                        case "+":
                        {
                            ilkSayi = ilkSayi + Double.parseDouble(textView.getText().toString());
                            textView.setText(ilkSayi.toString());
                        }
                        break;
                        case "-":
                        {
                            ilkSayi = ilkSayi - Double.parseDouble(textView.getText().toString());
                            textView.setText(ilkSayi.toString());
                        }
                        break;
                        case "/":
                        {
                            ilkSayi = ilkSayi / Double.parseDouble(textView.getText().toString());
                            textView.setText(ilkSayi.toString());
                        }
                        break;
                        case "*":
                        {
                            ilkSayi = ilkSayi * Double.parseDouble(textView.getText().toString());
                            textView.setText(ilkSayi.toString());
                        }
                        break;
                    }
                }
            }
            break;
        }
    }
}