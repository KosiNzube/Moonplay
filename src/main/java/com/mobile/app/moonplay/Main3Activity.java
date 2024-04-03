package com.mobile.app.moonplay;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {
    private  static  int splashy=300;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setTheme(R.style.hihii);
        setContentView(R.layout.activity_main3);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent=new Intent(Main3Activity.this,sub.class);
                startActivity(homeIntent);
                finish();
            }
        },splashy);


    }
}
