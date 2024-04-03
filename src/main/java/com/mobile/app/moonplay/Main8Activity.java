package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Main8Activity extends AppCompatActivity {
    List<shortsvid> icemodels=new ArrayList<>();
    ImageView backkk;
    private final String KEY="state" ;
    RecyclerView view1;
    private static  Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int last=preferences.getInt("lastly",0);

        backkk=findViewById(R.id.backkk);
        backkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        icemodels.add(new shortsvid("",4));


         view1 = (RecyclerView) findViewById(R.id.recycler_id);
        view1.setLayoutManager(new speedy(this, speedy.VERTICAL, false));


        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(view1);
        shortsAdapter adapter = new shortsAdapter(this, icemodels);
        view1.setAdapter(adapter);




    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        View firstchild=view1.getChildAt(0);
        int firstVisiblePosition=view1.getChildAdapterPosition(firstchild);
        int offset=firstchild.getTop();
        preferences.edit().putInt("position",firstVisiblePosition).putInt("offset",offset).apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        view1.scrollToPosition(preferences.getInt("position",0));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view1.scrollBy(0,-preferences.getInt("offset",0));
            }
        },500);
    }
}
