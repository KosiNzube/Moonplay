package com.mobile.app.moonplay;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    ImageButton three;
    private boolean doublein=false;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("for you");
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        viewPager=findViewById(R.id.viewp);

        pageViewAdapter=new PageViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);

        viewPager.setCurrentItem(0,false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onChangetab(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void onBackPressed() {


        if (doublein) {
            super.onBackPressed();
            //this.finishAffinity();
            return;
        }
        this.doublein = true;


        Toast toast = Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                doublein = false;

            }
        }, 2000);
    }



    private void onChangetab(int i) {


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);


    }
}
