package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;


public class splashActivity extends AppCompatActivity {
  private  static  int splashy=1500;
  TextView name, genre, length,five,watch,textView,adede;
  ImageView imageView;
  int mio;

  ImageView imageView13;
  ArrayList<Streampostion> xxy;
  boolean wat;
  RelativeLayout riri;
  ListView listView;
  RecyclerView recyclerView;
  @SuppressLint("WrongConstant")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);

    if (Build.VERSION.SDK_INT>=21){
      setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
      getWindow().setStatusBarColor(Color.BLACK);
      getWindow().setNavigationBarColor(Color.BLACK);
    }


    adede=findViewById(R.id.adede);
    adede.setVisibility(View.INVISIBLE);

    watch=findViewById(R.id.playlist);
    listView=findViewById(R.id.grid);
    listView.setVisibility(View.INVISIBLE);

    wat=true;
    imageView13=findViewById(R.id.re);
    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
    final String images=preferences1.getString("images","");
    final String Dname=preferences1.getString("names","");
    final String Dlink=preferences1.getString("streamLink","");
    final String geenre=preferences1.getString("geenre","");
    final String subt=preferences1.getString("subt","");
    imageView13.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (Dname.length()>0) {
          Intent intent = new Intent(splashActivity.this, streamplayer4All.class);
          intent.putExtra("name", Dname);
          intent.putExtra("video", Dlink);
          intent.putExtra("genre", geenre);
          intent.putExtra("photo", images);

          intent.putExtra("subt",subt);
          startActivity(intent);
        }else{
          Toast toast = Toast.makeText(splashActivity.this,"You haven't watched any video yet", Toast.LENGTH_SHORT);
          toast.setGravity(Gravity.CENTER, 0, 0);
          toast.show();

        }
      }
    });


    watch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {


        Intent intent = new Intent(splashActivity.this, Main9Activity.class);
        startActivity(intent);

      }
    });

    textView=findViewById(R.id.back);
    textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    mio = preferences1.getInt("uioii", 0);
    String totDur = String.format("%02d.%02d.%02d",
            TimeUnit.MILLISECONDS.toHours(mio),
            TimeUnit.MILLISECONDS.toMinutes(mio) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mio)), // The change is in this line
            TimeUnit.MILLISECONDS.toSeconds(mio) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mio)));
    imageView = findViewById(R.id.itemImage);

    name = findViewById(R.id.tvTitle);
    genre = findViewById(R.id.type);
    length = findViewById(R.id.typee);


    SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
    Gson gson=new Gson();
    String json=sharedPreferences.getString("task list",null);
    Type type=new TypeToken<ArrayList<Streampostion>>(){}.getType();
    xxy=gson.fromJson(json,type);

    if (xxy==null){
      xxy=new ArrayList<>();
    }




    if (xxy.size()>0) {
      RecyclerView view1 = (RecyclerView) findViewById(R.id.fifi);
      view1.setHasFixedSize(true);
      view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      view1.setItemAnimator(new DefaultItemAnimator());
      streampos adapter = new streampos(this, xxy);
      view1.setAdapter(adapter);
    }

    name.setText(Dname);
    if (mio > 3) {
      length.setText(totDur);
    } else {
      length.setText("You only started playing!");
    }

    genre.setText(geenre);

    five = findViewById(R.id.textView5);
    five.setVisibility(View.INVISIBLE);

    riri = findViewById(R.id.hihi);
    if (Dname.length() == 0) {
      five.setVisibility(View.VISIBLE);

      riri.setVisibility(View.INVISIBLE);
    }
    if (Dname.length() > 0) {
      Glide.with(this)
              .load(images)
              .centerCrop()
              .into(imageView);


    }

  }

  private void imple() {
    if (wat) {
      wat = false;


      listView.setVisibility(View.VISIBLE);


    } else {
      wat = true;
      listView.setVisibility(View.INVISIBLE);
    }
  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);

  }

}
