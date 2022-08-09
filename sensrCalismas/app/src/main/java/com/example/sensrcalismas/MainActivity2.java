package com.example.sensrcalismas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Sensor accelerometer1;
    SensorManager sm;
    TextView acceleration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer1 = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, accelerometer1,SensorManager.SENSOR_DELAY_NORMAL);
        acceleration = (TextView) findViewById(R.id.proxy);
    }
    @Override
    public void onSensorChanged(SensorEvent arg0) {
        if(arg0.sensor.getType()==Sensor.TYPE_PROXIMITY){
            float[] values = arg0.values;
            float x= values[0];

            acceleration.setText("x: "+x);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    public void gec1(View view) {
        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(intent);
    }
}