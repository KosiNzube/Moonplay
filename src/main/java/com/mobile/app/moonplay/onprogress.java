package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.Status;
import com.tonyodev.fetch2core.Func;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class onprogress extends AppCompatActivity {
  ImageView imageView;
  TextView back,play,backer;
  RecyclerView recyclerView;
  TextView nodown;
  ViewPager viewPager;
  PageViewAdaptery pageViewAdapter;
  ArrayList<id> xxy;
  TextView refresh;
  TextView popular,genre;
  id cc;
  private static final int REQUEST_WRITE = 706;
  private Fetch fetch;

  TextView progrezz;
  List<icemodel> icemodels=new ArrayList<>();
  @SuppressLint("WrongConstant")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.onp);
    if (Build.VERSION.SDK_INT>=19){
      setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);

      getWindow().setStatusBarColor(Color.rgb(5,5,9));
      getWindow().setNavigationBarColor(Color.BLACK);

    }
    if (Build.VERSION.SDK_INT>=21){
      setwinflag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);

      getWindow().setStatusBarColor(Color.rgb(5,5,9));
      getWindow().setNavigationBarColor(Color.BLACK);

    }

    FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
            .setDownloadConcurrentLimit(9)
            .build();


    backer=findViewById(R.id.backx);
    refresh=findViewById(R.id.refresh);

    refresh.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intent = new Intent(onprogress.this, iceplayer.class);
        startActivity(intent);
      }
    });

    backer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    play=findViewById(R.id.back);
    recyclerView=findViewById(R.id.recycler_id);

    progrezz=findViewById(R.id.playlist);
    fetch = Fetch.Impl.getInstance(fetchConfiguration);


    xxy=new ArrayList<id>();


    fetch.getDownloadsWithStatus(Status.NONE,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {




          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
          }

          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });

    fetch.getDownloadsWithStatus(Status.ADDED,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {




          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
          }

          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });




    fetch.getDownloadsWithStatus(Status.PAUSED,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {




          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
          }
          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });



    fetch.getDownloadsWithStatus(Status.FAILED,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {




          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
          }

          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });



    fetch.getDownloadsWithStatus(Status.QUEUED,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {




          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
          }

          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });







    fetch.getDownloadsWithStatus(Status.DOWNLOADING,new Func<List<Download>>() {
      @Override
      public void call(@NotNull List<Download> result) {
        for (int i=0;i<result.size();i++) {


          if (!result.get(i).getTag().contains("srt")){

            xxy.add(new id(result.get(i).getTag(), result.get(i).getId(), result.get(i).getRequest().getId(), result.get(i).getProgress(), result.get(i).getStatus()));
        }
          RecyclerView view1 = findViewById(R.id.recycler_id);
          view1.setHasFixedSize(true);
          view1.setLayoutManager(new speedy(onprogress.this, speedy.VERTICAL, false));
          idy adapter = new idy(onprogress.this, xxy);
          view1.setAdapter(adapter);

        }
      }
    });


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
