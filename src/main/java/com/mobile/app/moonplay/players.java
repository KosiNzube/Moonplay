package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class players extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    VideoView videoView;
    SeekBar seekBar;
    boolean rotation;
    boolean viewer;
    int duration;
    ImageView pauseP,back;
    boolean pauseee;
    static int yoyo;
    Handler handlery;
    Runnable runnable;
    ProgressBar progressBar;
    static int riio;
    String name;
    String namei;
    static List<watched> watcheds=new ArrayList<>();
    ImageView bac;

    String linkk;
    static int lastWatched;
    private int sWidth,sHeight;
    private View decorView;
    private int uiImmersiveOptions;
    private int brightness, mediavolume,device_height,device_width;
    private Display display;
    private Point size;

    static int comedy;
    static int romance;
    static int scifi;
    static int action;
    static int animation;
    static int drama;
    static int fantasy;
    static int crime;
    static int adventure;
    static int horror;

    ImageView next10, previous10;
    private PlaybackState mPlaybackState;


    private boolean isPlaying;
    private int pos;
    private String image;
    String genre;
    static HashMap<String,Integer> hashMap2=new HashMap<>();
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerz);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }



        next10=findViewById(R.id.straight);
        previous10=findViewById(R.id.rotation);
        Intent intent = getIntent();

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        action=preferences.getInt("action",0);
        romance=preferences.getInt("romance",0);
        scifi=preferences.getInt("scifi",0);
        action=preferences.getInt("action",0);

        horror=preferences.getInt("horror",0);
        animation=preferences.getInt("animation",0);
        fantasy=preferences.getInt("fantasy",0);
        drama=preferences.getInt("drama",0);

        namei=preferences.getString("name","");




        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        linkk = intent.getExtras().getString("video");

        name=intent.getExtras().getString("name");



        image=intent.getExtras().getString("image");

        genre=intent.getExtras().getString("genre");

        String v=genre.toLowerCase().trim();

        if (v.startsWith("action")){
            action=action+1;

            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("action",action);
            editor.commit();
        }

        if (v.startsWith("romance")){
            romance=romance+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("romance",romance);
            editor.commit();
        }


        if (v.startsWith("comedy")){
            comedy=comedy+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("comedy",comedy);
            editor.commit();
        }
        if (v.startsWith("animation")){
            animation=animation+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("animation",animation);
            editor.commit();
        }
        if (v.startsWith("fantasy")){
            fantasy=fantasy+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("fantasy",fantasy);
            editor.commit();
        }
        if (v.startsWith("horror")){
            horror=horror+1;
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("horror",horror);
            editor.commit();
        }
        if (v.startsWith("crime")){
            crime=crime+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("crime",crime);
            editor.commit();
        }
        if (v.startsWith("adventure")){
            adventure=adventure+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("adventure",adventure);
            editor.commit();
        }
        if (v.startsWith("sci-fi")){
            scifi=scifi+1;
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putInt("scifi",scifi);
            editor.commit();
        }


        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        sWidth = size.x;
        sHeight = size.y;


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        device_height = displaymetrics.heightPixels;
        device_width = displaymetrics.widthPixels;

        uiImmersiveOptions = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiImmersiveOptions);




        seekBar = findViewById(R.id.seekBar);
        final View view=findViewById(R.id.view);
        final RelativeLayout relativeLayout;
        relativeLayout=findViewById(R.id.control);
        videoView=findViewById(R.id.videoviewio);
        videoView.requestFocus();
        seekBar.requestFocus();
        videoView.setVideoURI(Uri.parse(linkk));
        riio=preferences.getInt("uioi",0);
        setHandler();
        initializeSeekBar();
        pauseee=false;
        bac=findViewById(R.id.stopV);
        pauseP=findViewById(R.id.pauseP);
        isPlaying = false;

        back=findViewById(R.id.backkk);
        back.setVisibility(View.INVISIBLE);
        bac.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hashMap2.containsKey(name)){
                    hashMap2.put(name,(int) videoView.getCurrentPosition());


                    saveHashmap(name,(int) videoView.getCurrentPosition());


                }
                else if (hashMap2.containsKey(name)){

                    hashMap2.put(image,(int) videoView.getCurrentPosition());
                    saveHashmap(image,(int) videoView.getCurrentPosition());
                }



                yoyo=videoView.getCurrentPosition();

                finish();


                refresh();
            }
        });


        pauseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlaying) {
                    isPlaying = false;

                    videoView.pause();
                    pauseP.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);

                } else {
                    isPlaying = true;
                    videoView.start();

                    pauseP.setImageResource(R.drawable.ic_pause_black_24dp);

                }
            }
        });

        seekBar.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);

        viewer=true;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewer) {
                    viewer=false;
                    relativeLayout.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                    bac.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                }else {
                    relativeLayout.setVisibility(View.INVISIBLE);
                    seekBar.setVisibility(View.INVISIBLE);
                    bac.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    viewer=true;
                }
            }
        });

        rotation=true;
        pos=0;
        if (savedInstanceState!=null){
            pos=savedInstanceState.getInt("pos");
        }



        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
                videoView.pause();
                isPlaying = false;
                pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                relativeLayout.setVisibility(View.VISIBLE);

                back.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);

            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration = mp.getDuration() / 1000;

                seekBar.setMax(videoView.getDuration());


            }
        });

        if (videoView.getDuration()>0){
            int cpos=videoView.getCurrentPosition();
            seekBar.setProgress(cpos);
        }






    }

    private void refresh() {

        Intent intent=new Intent(players.this,iceplayer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt("uioi",videoView.getCurrentPosition());
        editor.putString("name",name);
        editor.commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (hashMap2.containsKey(name)) {
            x = hashMap2.get(name);
            Toast.makeText(players.this,"Recovering state",Toast.LENGTH_SHORT).show();
        }

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotation) {
                    SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putInt("uioi",videoView.getCurrentPosition());
                    editor.putString("name",name);
                    editor.commit();

                    rotation=false;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }else {
                    SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putInt("uioi",videoView.getCurrentPosition());
                    editor.putString("name",name);
                    editor.commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    rotation=true;


                }

            }
        });



        next10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition()+5000);
            }
        });

        previous10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition()-5000);
            }
        });




        if (yoyo>0) {
            if (namei.equals(name)) {
                videoView.seekTo(yoyo);
                videoView.start();
                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("uioi",yoyo);
                editor.putString("name",name);
                editor.commit();
            }else {



            }if (yoyo==0||yoyo<0){
                videoView.seekTo(riio);
                videoView.start();
            }


            if (x>0) {
                videoView.seekTo(x);
            }else{
                videoView.seekTo(0);
            }

            videoView.start();
        }





    }



    private void initializeSeekBar() {
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(this);

    }














    private void setHandler(){
        handlery=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                if (videoView.getDuration()>0){
                    int cvd=videoView.getCurrentPosition();
                    seekBar.setProgress(cvd);
                    yoyo=videoView.getCurrentPosition();

                }
                handlery.postDelayed(this,0);

            }
        };



        handlery.postDelayed(runnable,500);
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        if (fromUser){
            videoView.seekTo(progress);
            yoyo=videoView.getCurrentPosition();



        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        videoView.seekTo(seekBar.getProgress());
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        if (!hashMap2.containsKey(name)){
            hashMap2.put(name,(int) videoView.getCurrentPosition());


            saveHashmap(name,(int) videoView.getCurrentPosition());


        }
        else if (hashMap2.containsKey(name)){

            hashMap2.put(image,(int) videoView.getCurrentPosition());
            saveHashmap(image,(int) videoView.getCurrentPosition());
        }

        yoyo=videoView.getCurrentPosition();
        refresh();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        refresh();

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (videoView.isPlaying()||pauseee) {
            outState.putInt("pos", videoView.getCurrentPosition());

        }
    }
    private void saveHashmap(String image, int obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(players.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(image,json);
        editor.apply();     // This line is IMPORTANT !!!
    }

}
