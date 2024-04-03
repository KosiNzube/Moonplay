package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class iceplayerX extends AppCompatActivity {
    ImageView imageView;
    TextView back,play;
    RecyclerView recyclerView;
    TextView nodown;
    ViewPager viewPager;
    PageViewAdaptery pageViewAdapter;
    TextView popular,genre;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    List<icemodel> icemodels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iceplayer);

        viewPager=findViewById(R.id.viewp);

        popular=findViewById(R.id.popular);
        genre=findViewById(R.id.genre);
        pageViewAdapter=new PageViewAdaptery(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdapter);

        genre=findViewById(R.id.genre);
        genre.setTypeface(null, Typeface.BOLD);
        popular.setTypeface(null,Typeface.NORMAL);
        viewPager.setCurrentItem(0,true);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        play=findViewById(R.id.playlist);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }

        });
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1,true);
                popular.setTypeface(null, Typeface.BOLD);
                genre.setTypeface(null,Typeface.NORMAL);
                genre.setTextColor(Color.GRAY);
                popular.setTextColor(Color.WHITE);

            }
        });

        genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0,true);
                genre.setTypeface(null, Typeface.BOLD);
                popular.setTypeface(null,Typeface.NORMAL);
                popular.setTextColor(Color.GRAY);
                genre.setTextColor(Color.WHITE);

            }
        });



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








        // nodown=findViewById(R.id.textView5);



        // imageView=findViewById(R.id.backln);
        // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);



        //imageView.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //   public void onClick(View v) {
        //     finish();
        //   }
        // });
        //  ImageView button;
        // button=findViewById(R.id.back);








    }
    private void onChangetab(int i) {

        if (i==1) {
            viewPager.setCurrentItem(1, true);
            popular.setTypeface(null, Typeface.BOLD);
            genre.setTypeface(null, Typeface.NORMAL);
            genre.setTextColor(Color.GRAY);
            popular.setTextColor(Color.WHITE);
        }

        if (i==0) {
            viewPager.setCurrentItem(0,true);
            genre.setTypeface(null, Typeface.BOLD);
            popular.setTypeface(null,Typeface.NORMAL);
            popular.setTextColor(Color.GRAY);
            genre.setTextColor(Color.WHITE);

        }


    }



    private void refresh() {
        this.finish();
        this.overridePendingTransition(0,0);
        startActivity(this.getIntent());
        this.overridePendingTransition(0,0);
    }

}
