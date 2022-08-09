package com.ders.sensoraccelermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  SensorEventListener {
    Sensor accelerometer;
    SensorManager sm;
    TextView acceleration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        acceleration = (TextView) findViewById(R.id.accelerate1);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    public void  onSensorChanged(SensorEvent arg0){
        if(arg0.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float[] values = arg0.values;
            float x= values[0];
            float y=values[1];
            float z=values[2];
            acceleration.setText("x: "+x+"\nY: "+y+"\nZ: "+z);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    public void gec(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}