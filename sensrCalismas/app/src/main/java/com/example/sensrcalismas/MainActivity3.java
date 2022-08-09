package com.example.sensrcalismas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    Sensor accelerometer1;
    SensorManager sm;
    TextView acceleration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer1 = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener((SensorEventListener) this, accelerometer1,SensorManager.SENSOR_DELAY_NORMAL);

        acceleration = (TextView) findViewById(R.id.press);
    }
    @Override
    public void onSensorChanged(SensorEvent arg0) {
        if (arg0.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float[] values = arg0.values;
            float x = values[0];
            acceleration.setText("x: " + x);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}