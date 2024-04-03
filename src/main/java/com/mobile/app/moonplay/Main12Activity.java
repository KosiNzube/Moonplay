package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class Main12Activity extends AppCompatActivity {
  RecyclerView trendingRecy,con,popo,action,randomrecy;
  ProgressBar progressBar;
  private static final int REQUEST_WRITE_PERMISSION = 787;
  private ImageView imageView;
  TextView textView,musical,romance,thriller,random;
  ImageView button,koko;
  CardView cardView,res,theme,cast,vid,info;
  int requestCode = 0;
  String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
  Bitmap bi;
  ImageView playpause;
  private  static  int splashy=10;
  SimpleExoPlayer simpleExoPlayer;
  private  static  int splashyy=15;
  ArrayList arrayList=new ArrayList();
  CardView newp;
  private boolean isPlaying;
  TextView loading,ii,cwatch;
  String name;
  private SimpleExoPlayer videoPlayer=null;
  private ArrayList<instantv> mediaObjectList = new ArrayList<>();
  private ArrayList<instantv> mediaObjectListx = new ArrayList<>();
  private TextToSpeech mtts;
  private ImageView talker;
  ArrayList<genx> gen = new ArrayList<>();
  ArrayList<music> sound = new ArrayList<>();
  //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
  //   private CollectionReference nolly=db.collection("music");
  LeastRecentlyUsedCacheEvictor ce=null;
  File cf=null;
  SimpleCache cac;
  private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
  RecyclerView trendingRecyx,foru;
  @SuppressLint("WrongConstant")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.makemusic2);
    if (Build.VERSION.SDK_INT>=21){
      setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
      getWindow().setStatusBarColor(Color.BLACK);
      getWindow().setNavigationBarColor(Color.BLACK);
    }

    res=findViewById(R.id.res);
    theme=findViewById(R.id.theme);
    cast=findViewById(R.id.cast);
    vid=findViewById(R.id.vid);
    info=findViewById(R.id.info);


    res.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(Main12Activity.this)
                .setTitle("Subscription needed for higher resolution")
                .setMessage("Applies to both download and streaming video")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.

                .setIcon(android.R.drawable.ic_menu_preferences)
                .show();
      }
    });

    theme.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {


      }
    });


    TextView username=findViewById(R.id.username);
    username.setText("Anonymous");



    vid.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AlertDialog.Builder(Main12Activity.this)
                .setTitle("About this app")
                .setMessage("First version(1.0). Created and distributed by IcePlay")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.

                .setIcon(android.R.drawable.ic_menu_preferences)
                .show();


      }
    });

    cast.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        new AlertDialog.Builder(Main12Activity.this)
                .setTitle("Subscription needed")
                .setMessage("Not currently available for Free version. Subscribe to Prime to cast contents to big screen and other privileges")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.

                .setIcon(android.R.drawable.ic_menu_preferences)
                .show();

        //  Toast.makeText(getActivity(),"Subscription ", Toast.LENGTH_SHORT).show();
      }
    });

    info.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {


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
