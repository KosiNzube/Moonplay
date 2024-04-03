package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tonyodev.fetch2.Fetch;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class downpage extends AppCompatActivity {
  ImageView imageView;
  TextView back,play,backer;
  RecyclerView recyclerView;
  TextView nodown;
  ViewPager viewPager;

  TextView refresh;
  TextView popular,genre;
  private static final int REQUEST_WRITE = 706;
  private Fetch fetch;

  TextView ioio;
  TextView progrezz,video;
  List<icemodel> icemodels=new ArrayList<>();
  @SuppressLint("WrongConstant")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.downpage);
    ioio=findViewById(R.id.cur);

    video=findViewById(R.id.video);

    video.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast toast = Toast.makeText(downpage.this,"Loading", Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Intent intent = new Intent(downpage.this, iceplayer.class);
        startActivity(intent);
      }
    });

    ioio.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intent = new Intent(downpage.this, onprogress.class);
        startActivity(intent);
      }
    });


  }

  private void refresh() {
    this.finish();
    this.overridePendingTransition(0,0);
    startActivity(this.getIntent());
    this.overridePendingTransition(0,0);



  }

}
