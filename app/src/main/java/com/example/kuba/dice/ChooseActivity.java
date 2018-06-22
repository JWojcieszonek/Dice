package com.example.kuba.dice;

import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private FragmentStatePagerAdapter mPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        mPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position%6) {
                    case 0:
                        Fragment0_D4 d4 = new Fragment0_D4();
                        return d4;

                    case 1:
                        Fragment1_D6 d6 = new Fragment1_D6();
                        return d6;

                    case 2:
                        Fragment2_D8 d8 = new Fragment2_D8();
                        return d8;

                    case 3:
                        Fragment3_D10 d10 = new Fragment3_D10();
                        return d10;

                    case 4:
                        Fragment4_D12 d12 = new Fragment4_D12();
                        return d12;

                    case 5:
                        Fragment5_D20 d20 = new Fragment5_D20();
                        return d20;
                    default:
                        return null;
                }
            }
        };
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
    }


    public void goTo4(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",4);
        startActivity(intent);
    }

    public void goTo6(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",6);
        startActivity(intent);
    }

    public void goTo8(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",8);
        startActivity(intent);
    }
    public void goTo10(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",10);
        startActivity(intent);
    }
    public void goTo12(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",12);
        startActivity(intent);
    }
    public void goTo20(View view)
    {
        Intent intent = new Intent(this,Shake6.class);
        intent.putExtra("type",20);
        startActivity(intent);
    }
}
