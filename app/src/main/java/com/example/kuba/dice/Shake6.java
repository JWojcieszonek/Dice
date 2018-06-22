package com.example.kuba.dice;

import java.util.Random;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Shake6 extends AppCompatActivity implements  SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 13;
    private TextView mNumber;
    private ImageView image0;
    private Intent intent ;
    private MediaPlayer mp;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake6);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mp = MediaPlayer.create(this, R.raw.sound);

        mNumber = findViewById(R.id.number);
        image0  = findViewById(R.id.imageView4);
        intent= new Intent(this,Shake6m.class);


        type = getIntent().getIntExtra("type",6);
        intent.putExtra("type",type);
        switch (type)
        {
            case 4:
                image0.setImageResource(R.drawable.t4);
                break;
            case 6:
                image0.setImageResource(R.drawable.d6_6);
                break;
            case 8:
                image0.setImageResource(R.drawable.t8);
                break;
            case 10:
                image0.setImageResource(R.drawable.d10);
                break;
            case 12:
                image0.setImageResource(R.drawable.d12);
                break;
            case 20:
                image0.setImageResource(R.drawable.t20);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void generateRandomNumber(int type) {

        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(type) + 1;
        int res=0;
        switch (type)
        {
            case 4:
                res= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                break;
            case 6:
                res= getResources().getIdentifier("d6_" + randomNum, "drawable", "com.example.kuba.dice");
                break;
            case 8:
                res= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                break;
            case 10:
                res= getResources().getIdentifier("d10_" + randomNum, "drawable", "com.example.kuba.dice");
                break;
            case 12:
                res= getResources().getIdentifier("d12_" + randomNum, "drawable", "com.example.kuba.dice");
                break;
            case 20:
                res= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                break;

        }

        image0.setImageResource(res);
        mp.start();
        mNumber.setText(Integer.toString(randomNum));
        mNumber.setTextSize(30);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

        if (acceleration > SHAKE_THRESHOLD) {
            generateRandomNumber(type);
        }
    }

    public void showPopup1(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.number, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.item1:
                        return true;
                    case R.id.item2:
                        intent.putExtra("num",2);
                        startActivity(intent);
                        return true;
                    case R.id.item3:
                        intent.putExtra("num",3);
                        startActivity(intent);
                        return true;
                    case R.id.item4:
                        intent.putExtra("num",4);
                        startActivity(intent);
                        return true;
                    case R.id.item5:
                        intent.putExtra("num",5);
                        startActivity(intent);
                        return true;
                    case R.id.item6:
                        intent.putExtra("num",6);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        popup.show();
    }

}

