package com.mobile.app.moonplay;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.mobile.app.moonplay.instantplay.setwinflag;


public class Main5Activity extends AppCompatActivity {
    List<Movie> movies;
    private ImageView imageView;
    users users;
    RecyclerView recyclerView;
    private ViewPager viewPager;
    magicplaya pageViewAdapter;
    String que=null;
    TextView back;
    String userTaste;
    String a="iron man";
    RecyclerView fifi;
    static ArrayList bp=new ArrayList();
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setTheme(R.style.hihiggi);

        setContentView(R.layout.kx);

        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }


        imageView=findViewById(R.id.refresh);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        back = findViewById(R.id.backln);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();


            }
        });



        viewPager=findViewById(R.id.viewp);
        pageViewAdapter=new magicplaya(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);

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

    private void onChangetab(int i) {
    }
    private void refresh() {
        this.finish();
        this.overridePendingTransition(0,0);
        startActivity(this.getIntent());
        this.overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);

    }
}
