package com.mobile.app.moonplay;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.session.PlaybackState;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;


import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

public class conwatch extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    PlayerView videoView;
    ExoPlayer exoPlayer;
    SeekBar seekBar;
    boolean rotation;
    boolean viewer;
    int duration;
    ImageView pauseP,back;
    Runnable updatePlayer;
    boolean pauseee;
    static int yoyoo;
    Handler handlery;
    Runnable runnable;
    ProgressBar progressBar;
    static int mio;
    String name;
    String namei;
    ImageView next10, previous10;
    private int brightness, mediavolume,device_height,device_width;
    private int sWidth,sHeight;

    ImageView bac;
    TextView noin;
    String linkk;
    static int lastWatched;

    RelativeLayout relativeLayout;
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
    private Display display;
    private Point size;
    public MediaRouteButton mMediaRouteButton;
    private CastContext mCastContext;
    private CastSession mCastSession;
    private PlaybackState mPlaybackState;
    private SessionManager mSessionManager;
    private MediaItem mSelectedMedia;
    private String[] video_url, video_type, video_title;
    private int currentTrackIndex;


    private View decorView;
    private int uiImmersiveOptions;
    private boolean isPlaying;
    private int pos;
    static String image;
    static String Dlink;
    static String Dname;


    String genre;
    private Handler mainHandler;
    private ProgressBar loadingPanel;


    private MediaInfo buildMediaInfo() {
        MediaMetadata movieMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);


        mSelectedMedia = new MediaItem();
        mSelectedMedia.setUrl(video_url[currentTrackIndex]);
        mSelectedMedia.setContentType(video_type[currentTrackIndex]);
        mSelectedMedia.setTitle(video_title[currentTrackIndex]);

        movieMetadata.putString(MediaMetadata.KEY_TITLE, mSelectedMedia.getTitle());

        return new MediaInfo.Builder(mSelectedMedia.getUrl())
                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType("hls")
                .setMetadata(movieMetadata)
                .setStreamDuration(mSelectedMedia.getDuration() * 1000)
                .build();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
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

        namei=preferences.getString("names","");




        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        linkk = intent.getExtras().getString("video");

        name=intent.getExtras().getString("name");



        image=intent.getExtras().getString("photo");

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


        if (!name.isEmpty()) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("images", image);
            editor.putString("streamLink",linkk);
            editor.putString("nameboy",name);
            editor.putString("geenre",genre);
            editor.commit();
        }

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






        seekBar = findViewById(R.id.seekBar);
        final View view=findViewById(R.id.view);

        relativeLayout=findViewById(R.id.control);
        relativeLayout.setVisibility(View.INVISIBLE);
        seekBar.setVisibility(View.INVISIBLE);
        videoView=findViewById(R.id.videoviewio);
        videoView.requestFocus();
        seekBar.requestFocus();

        mio=preferences.getInt("uioii",0);

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
                yoyoo= (int) exoPlayer.getCurrentPosition();
                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("uioii",yoyoo);
                editor.putString("names",name);
                editor.commit();
                if (exoPlayer != null) {
                    exoPlayer.release();
                }

                finish();
            }
        });


        pauseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    isPlaying = false;

                    exoPlayer.setPlayWhenReady(false);

                    pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                } else {

                    isPlaying=true;
                    exoPlayer.setPlayWhenReady(true);

                    pauseP.setImageResource(R.drawable.ic_pause_black_24dp);

                }
            }
        });


        viewer=true;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewer) {
                    viewer=false;
                    relativeLayout.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
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


        if (savedInstanceState!=null){
            pos=savedInstanceState.getInt("pos");
        }


    }

    private void refresh() {

        Intent intent=new Intent(conwatch.this,Main7Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt("uioii", (int) exoPlayer.getCurrentPosition());
        editor.putString("names",name);
        editor.commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        isNet();
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotation) {
                    SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putInt("uioii", (int) exoPlayer.getCurrentPosition());
                    editor.putString("names",name);
                    editor.commit();

                    rotation=false;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }else {
                    SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=sharedPreferences.edit();

                    editor.putInt("uioii", (int) exoPlayer.getCurrentPosition());
                    editor.putString("names",name);
                    editor.commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    rotation=true;


                }

            }
        });

        next10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition()+5000);
            }
        });

        previous10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition()-5000);
            }
        });
        initializePlayer();
        noin=findViewById(R.id.textView5);

        loadingPanel=findViewById(R.id.proo);
        if (isNet()==false){



        }


        mainHandler = new Handler();
        execute();
    }

    private void execute() {
        if(exoPlayer!=null) {


            mainHandler.postDelayed(updatePlayer, 200);


        }

    }


    private void initializeSeekBar() {
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==200){
            int currTime = data.getIntExtra("currTime",0);
            exoPlayer.seekTo(currTime);
        }
    }
    {
        updatePlayer = new Runnable() {
            @Override
            public void run() {
                switch (exoPlayer.getPlaybackState()) {
                    case ExoPlayer.STATE_BUFFERING:
                        if (isNet() == false) {

                            loadingPanel.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.VISIBLE);
                            back.setVisibility(View.VISIBLE);
                            seekBar.setVisibility(View.VISIBLE);
                        }
                        break;

                    case ExoPlayer.STATE_IDLE:
                        loadingPanel.setVisibility(View.VISIBLE);
                        if (isNet() == false) {

                            loadingPanel.setVisibility(View.VISIBLE);

                            relativeLayout.setVisibility(View.VISIBLE);
                            back.setVisibility(View.VISIBLE);
                            seekBar.setVisibility(View.VISIBLE);
                        }

                        break;

                    case ExoPlayer.STATE_READY:
                        loadingPanel.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
                String totDur = String.format("%02d.%02d.%02d",
                        TimeUnit.MILLISECONDS.toHours(exoPlayer.getDuration()),
                        TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getDuration()) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(exoPlayer.getDuration())), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(exoPlayer.getDuration()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getDuration())));
                String curDur = String.format("%02d.%02d.%02d",
                        TimeUnit.MILLISECONDS.toHours(exoPlayer.getCurrentPosition()),
                        TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getCurrentPosition()) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(exoPlayer.getCurrentPosition())), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(exoPlayer.getCurrentPosition()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getCurrentPosition())));
                //   txt_ct.setText(curDur);
                //   txt_td.setText(totDur);

                seekBar.setMax((int) exoPlayer.getDuration());
                seekBar.setProgress((int) exoPlayer.getCurrentPosition());

                mainHandler.postDelayed(updatePlayer, 200);
            }
        };
    }








    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        if (fromUser){




        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        exoPlayer.seekTo(seekBar.getProgress());
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        yoyoo= (int) exoPlayer.getCurrentPosition();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("uioii",yoyoo);
        editor.putString("names",name);
        editor.commit();
        if (exoPlayer != null) {
            exoPlayer.release();
        }

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        exoPlayer.release();
        refresh();

    }
    private void initializePlayer(){
        LoadControl loadControl=new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true,16))
                .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,VideoPlayerConfig.MAX_BUFFER_DURATION,VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .build();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(this).setExtensionRendererMode(exte);

        exoPlayer = new SimpleExoPlayer.Builder(this,renderersFac).setLoadControl(loadControl).build();
        videoView.setPlayer(exoPlayer);
        Uri uri=Uri.parse(linkk);

        MediaSource mediaSource=buildMediaSource(uri);
        exoPlayer.seekTo(mio);
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare(mediaSource,false,false);



    }
    private boolean isNet(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active!=null &&active.isConnected();

    }
    private MediaSource buildMediaSource(Uri uri){
        DataSource.Factory dataSourceFac=new DefaultDataSourceFactory(this,"exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFac).createMediaSource(uri);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();

            }
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();

            }
        }

    }
}
