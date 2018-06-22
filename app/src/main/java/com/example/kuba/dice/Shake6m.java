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


import java.util.Random;


public class Shake6m extends AppCompatActivity implements  SensorEventListener{


    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 13;
    private Intent intent ;
    private TextView textView;
    private MediaPlayer mp;
    private int num;
    private int type;
    private int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake6m);

        image1 = findViewById(R.id.imageView5);
        image2 = findViewById(R.id.imageView6);
        image3 = findViewById(R.id.imageView7);
        image4 = findViewById(R.id.imageView8);
        image5 = findViewById(R.id.imageView9);
        image6 = findViewById(R.id.imageView10);
        textView=findViewById(R.id.textView4);

        num = getIntent().getIntExtra("num",2);
        type = getIntent().getIntExtra("type",6);
        System.out.println("KURWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+num);
        res=0;
        switch (type)
        {
            case 4:
                res= getResources().getIdentifier("t4" , "drawable", "com.example.kuba.dice");
                break;
            case 6:
                res= getResources().getIdentifier("d6_6" , "drawable", "com.example.kuba.dice");
                break;
            case 8:
                res= getResources().getIdentifier("t8" , "drawable", "com.example.kuba.dice");
                break;
            case 10:
                res= getResources().getIdentifier("d10_10" , "drawable", "com.example.kuba.dice");
                break;
            case 12:
                res= getResources().getIdentifier("d12_12" , "drawable", "com.example.kuba.dice");
                break;
            case 20:
                res= getResources().getIdentifier("t20", "drawable", "com.example.kuba.dice");
                break;

        }
        setNumber(num);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mp = MediaPlayer.create(this, R.raw.sound);

        intent= new Intent(this,Shake6m.class);
    }
    public void setNumber(int num)
    {
        switch (num)
        {
            case 2:
                image1.setImageResource(res);
                image2.setImageResource(res);
                image3.setImageResource(R.drawable.blank);
                image4.setImageResource(R.drawable.blank);
                image5.setImageResource(R.drawable.blank);
                image6.setImageResource(R.drawable.blank);
                break;
            case 3:
                image1.setImageResource(res);
                image2.setImageResource(res);
                image3.setImageResource(res);
                image4.setImageResource(R.drawable.blank);
                image5.setImageResource(R.drawable.blank);
                image6.setImageResource(R.drawable.blank);
                break;
            case 4:
                image1.setImageResource(res);
                image2.setImageResource(res);
                image3.setImageResource(res);
                image4.setImageResource(res);
                image5.setImageResource(R.drawable.blank);
                image6.setImageResource(R.drawable.blank);
                break;
            case 5:
                image1.setImageResource(res);
                image2.setImageResource(res);
                image3.setImageResource(res);
                image4.setImageResource(res);
                image5.setImageResource(res);
                image6.setImageResource(R.drawable.blank);
                break;
            case 6:
                image1.setImageResource(res);
                image2.setImageResource(res);
                image3.setImageResource(res);
                image4.setImageResource(res);
                image5.setImageResource(res);
                image6.setImageResource(res);
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
        ImageView[] tab ={image1,image2,image3,image4,image5,image6};
        Random randomGenerator = new Random();
        int randomNum;
        int sum=0;
        int res2=0;
        for(int i=0;i<num;i++)
        {
            randomNum = randomGenerator.nextInt(type) + 1;
            sum+=randomNum;
            switch (type)
            {
                case 4:
                    res2= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                    break;
                case 6:
                    res2= getResources().getIdentifier("d6_" + randomNum, "drawable", "com.example.kuba.dice");
                    break;
                case 8:
                    res2= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                    break;
                case 10:
                    res2= getResources().getIdentifier("d10_" + randomNum, "drawable", "com.example.kuba.dice");
                    break;
                case 12:
                    res2= getResources().getIdentifier("d12_" + randomNum, "drawable", "com.example.kuba.dice");
                    break;
                case 20:
                    res2= getResources().getIdentifier("t" + randomNum, "drawable", "com.example.kuba.dice");
                    break;

            }
            tab[i].setImageResource(res2);
        }
        textView.setText("   "+Integer.toString(sum));
        mp.start();

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


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.number, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.item1:

                        startActivity(intent);
                        return true;
                    case R.id.item2:
                        setNumber(2);
                        num=2;
                        return true;
                    case R.id.item3:
                        setNumber(3);
                        num=3;
                        return true;
                    case R.id.item4:
                        setNumber(4);
                        num=4;
                        return true;
                    case R.id.item5:
                        setNumber(5);
                        num=5;
                        return true;
                    case R.id.item6:
                        setNumber(6);
                        num=6;
                        return true;
                }
                return false;
            }
        });

        popup.show();
    }
}
