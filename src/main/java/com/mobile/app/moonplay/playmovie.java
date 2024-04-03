package com.mobile.app.moonplay;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class playmovie extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageView back;
    VideoView videoView;
    SeekBar seekBar;
    Handler handler;
    ImageView pauseplay;
    ImageView save;
    int duration;
    Boolean isPlaying;
    ImageButton bac;
    Handler handlery;
    Runnable runnable;
    private int current;
    private int currentProgress;
    private int currentPercent;
    private int pos;
    boolean pauseee;
    boolean rotation;
    private boolean doublein=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmovie);
        Intent intent = getIntent();
        // final String filmname = intent.getExtras().getString("link");
        isPlaying = false;
        back = findViewById(R.id.back);
        bac=findViewById(R.id.bac3);
        bac.setImageResource(R.drawable.ic_screen_rotation_black_24dp);
        rotation=true;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        seekBar = findViewById(R.id.seekBar);
        save = findViewById(R.id.saveButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoView = findViewById(R.id.videoView);


        videoView.requestFocus();

        setHandler();
        initializeSeekBar();
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   if (rotation) {
                //      rotation=false;
                //    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //   }else {
                // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //   rotation=true;
                //           }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pauseplay = findViewById(R.id.pauseplay);
        pauseplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    isPlaying = false;
                    videoView.pause();
                    pauseplay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                } else {

                    videoView.start();
                    isPlaying = true;
                    pauseplay.setImageResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();


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

        videoView.start();
        pauseee=false;
        isPlaying = true;
        pauseplay.setImageResource(R.drawable.ic_pause_black_24dp);

        pos=0;
        if (savedInstanceState!=null){
            pos=savedInstanceState.getInt("pos");
        }

        videoView.seekTo(pos);


    }

    private void initializeSeekBar() {
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(this);
    }


    private void publish() {
    }
    private void setHander(){


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (videoView.isPlaying()||pauseee) {
            outState.putInt("pos", videoView.getCurrentPosition());

        }
    }

    private void releaseMediaPlayer(){
        if (videoView !=null){
            handlery.removeCallbacks(runnable);
            videoView=null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        pauseee=true;
    }

    private void updateProgress() {
        handler.postDelayed(updateTime, 100);

    }

    private Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        isPlaying = false;
    }


    public void onBackPressed() {


        if (doublein){
            super.onBackPressed();
            return;
        }
        this.doublein=true;
        Toast toast=Toast.makeText(playmovie.this,"Press again to exit",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                doublein=false;

            }
        },2000);



    }

    private void setHandler(){
        handlery=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                if (videoView.getDuration()>0){
                    int cvd=videoView.getCurrentPosition();
                    seekBar.setProgress(cvd);

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
            int currentVD=videoView.getCurrentPosition();


        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
