package com.mobile.app.moonplay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class Maincat extends AppCompatActivity {
    ListView listView;
    private ArrayList arrayList=new ArrayList();
     String genree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.hihiggi);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }

        listView = findViewById(R.id.grid);
        arrayList.add("Horror");
        arrayList.add("Comedy");
        arrayList.add("Romance");
        arrayList.add("Sci-Fi");
        arrayList.add("Education");
        arrayList.add("Moments");
        arrayList.add("Gaming");
        arrayList.add("Anime");
        arrayList.add("Others");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Maincat.this, android.R.layout.simple_selectable_list_item, arrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent o = new Intent(Maincat.this, result.class);

                genree= (String) arrayList.get(position);
                o.putExtra("bobo",genree);
                startActivity(o);
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }
}
