package com.mobile.app.moonplay;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.*;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSinkFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

import static com.mobile.app.moonplay.streamplayer4All.xx;

public class streamplayer extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    PlayerView videoView;
    ExoPlayer exoPlayer;
    SeekBar seekBar;
    boolean rotation;
    boolean viewer;
    int duration;
    private static int splashy=150;
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
    SimpleCache  cac;
    LeastRecentlyUsedCacheEvictor ce=null;

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
    static HashMap<String,Integer> hashMap=new HashMap<>();
    String genre;
    private Handler mainHandler;
    private ProgressBar loadingPanel;

    private int x;
    private TextView txt_ct;


    TextView nameofmovie,lengthmov;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
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

        namei=preferences.getString("names","");




        txt_ct=findViewById(R.id.curmovie);
        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        linkk = intent.getExtras().getString("video");
        name=intent.getExtras().getString("name");
        image=intent.getExtras().getString("photo");

        nameofmovie=findViewById(R.id.nameofmovie);

        nameofmovie.setText(name);


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
        genre=intent.getExtras().getString("genre");

        if (!name.isEmpty()) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("images", image);
            editor.putString("streamLink",linkk);
            editor.putString("nameboy",name);
            editor.putString("geenre",genre);
            editor.commit();
        }



        String v=genre.toLowerCase().trim();






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


        back=findViewById(R.id.backkk);
        back.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hashMap.containsKey(image)){
                    hashMap.put(image,(int) exoPlayer.getCurrentPosition());


                    saveHashmap(image,(int) exoPlayer.getCurrentPosition());


                }
                else if (hashMap.containsKey(image)){

                    hashMap.put(image,(int) exoPlayer.getCurrentPosition());
                    saveHashmap(image,(int) exoPlayer.getCurrentPosition());
                }

                yoyoo= (int) exoPlayer.getCurrentPosition();
                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("uioii",yoyoo);
                editor.putString("names",name);
                editor.putString("geenre",genre);
                editor.putString("images", image);
                editor.commit();
                if (exoPlayer != null) {
                    exoPlayer.release();
                }
                xx.add(0,new Streampostion(image,yoyoo,name,linkk,genre,""));

                save(xx);
                finish();
            }
        });


        isPlaying = true;
        pauseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {


                    isPlaying=false;
                    exoPlayer.setPlayWhenReady(false);

                    pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                } else{


                    exoPlayer.setPlayWhenReady(true);

                    pauseP.setImageResource(R.drawable.ic_pause_black_24dp);
                    isPlaying=true;

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

    private void saveHashmap(String image, int obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(streamplayer.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(image,json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    private void refresh() {


        hashMap.put(image,(int) exoPlayer.getCurrentPosition());
        saveHashmap(image,(int) exoPlayer.getCurrentPosition());
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (hashMap.containsKey(image)) {
            x = hashMap.get(image);
            Toast.makeText(streamplayer.this,"Recovering state",Toast.LENGTH_SHORT).show();






        }

        /*
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rotation) {
                    rotation=false;
                    hashMap.put(image,(int) exoPlayer.getCurrentPosition());
                    saveHashmap(image,(int) exoPlayer.getCurrentPosition());
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);




                } else {

                    hashMap.put(image,(int) exoPlayer.getCurrentPosition());
                    saveHashmap(image,(int) exoPlayer.getCurrentPosition());
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    rotation=true;




                }




            }
        });
*/
        next10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition()+10000);
            }
        });

        previous10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition()-10000);
            }
        });
       initializePlayer();
       noin=findViewById(R.id.textView5);

        loadingPanel=findViewById(R.id.proo);


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
                        break;

                    case ExoPlayer.STATE_IDLE:
                    //    loadingPanel.setVisibility(View.VISIBLE);
                        break;

                    case ExoPlayer.STATE_ENDED:
                        viewer = false;
                        relativeLayout.setVisibility(View.VISIBLE);
                        back.setVisibility(View.VISIBLE);
                        exoPlayer.seekTo(0);
                        seekBar.setVisibility(View.VISIBLE);
                        isPlaying = false;
                        exoPlayer.setPlayWhenReady(false);
                        pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);
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
                   txt_ct.setText(curDur);
                //   txt_td.setText(totDur);

                lengthmov=findViewById(R.id.lengthofmovie);

                if (totDur.contains("-")){
                    lengthmov.setText("00.00.00");
                }else {
                    lengthmov.setText(totDur);
                }
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
        if (!hashMap.containsKey(image)){
            hashMap.put(image,(int) exoPlayer.getCurrentPosition());




            saveHashmap(image,(int) exoPlayer.getCurrentPosition());


        }
        else if (hashMap.containsKey(image)){

            hashMap.put(image,(int) exoPlayer.getCurrentPosition());



            saveHashmap(image,(int) exoPlayer.getCurrentPosition());


        }


        yoyoo= (int) exoPlayer.getCurrentPosition();
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("uioii",yoyoo);
        editor.putString("names",name);
        editor.putString("images", image);
        editor.putString("geenre",genre);
        editor.commit();
        if (exoPlayer != null) {
            exoPlayer.release();
        }
        xx.add(0,new Streampostion(image,yoyoo,name,linkk,genre,""));
        save(xx);

    }
    private void save(ArrayList<Streampostion> xx) {
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(xx);
        editor.putString("task list",json);
        editor.apply();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        exoPlayer.release();
        refresh();

    }
    private void initializePlayer() {

        TrackSelector trackSelector=new DefaultTrackSelector();

        LoadControl loadControl = new DefaultLoadControl();
        DefaultLoadControl loadControl1=new DefaultLoadControl.Builder().setBufferDurationsMs(32*1024,64*1024,1024,1024).createDefaultLoadControl();
       // exoPlayer = (ExoPlayer) ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl1);
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();

        videoView.setPlayer(exoPlayer);
        Uri uri = Uri.parse(linkk);
        Handler mainHandler = new Handler();
        //      MediaSource mediaSource = new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);


        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this, Util.getUserAgent(this, "Android ExoPlayer"));
        String mediaUrl = linkk;


        if (mediaUrl != null) {
            File cf = new File(this.getExternalCacheDir(), "instantmediaxxx");


            if (ce == null) {
                ce = new LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024);
            }
            ExoDatabaseProvider exoDatabaseProvider = new ExoDatabaseProvider(this);

            if (cac == null) {
                cac = new SimpleCache(cf, ce, exoDatabaseProvider);
            }
            CacheDataSourceFactory cacheDataSourceFactory =
                    new CacheDataSourceFactory(cac, dataSourceFactory,
                            new FileDataSourceFactory(),
                            new CacheDataSinkFactory(cac, CacheDataSink.DEFAULT_FRAGMENT_SIZE),
                            0, null, new CacheKeyProvider());
            ProgressiveMediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaUrl));


            exoPlayer.prepare();

            exoPlayer.setPlayWhenReady(true);
            exoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                }

                @Override
                public void onLoadingChanged(boolean isLoading) {
                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    switch (playbackState) {
                        case ExoPlayer.STATE_READY:
                            loadingPanel.setVisibility(View.GONE);
                            break;
                        case ExoPlayer.STATE_BUFFERING:
                          //  loadingPanel.setVisibility(View.VISIBLE);
                            break;
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                }

                @Override
                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                }

                @Override
                public void onPositionDiscontinuity(int reason) {
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                }

                @Override
                public void onSeekProcessed() {
                }
            });
            if (x > 0) {
                exoPlayer.seekTo(x);
            } else {
                exoPlayer.seekTo(mio);
            }
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.prepare(mediaSource, false, false);


        }
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
        if (cac!=null) {
            cac.release();
            cac = null;
        }


    }
    public class VideoPlayerConfig {
        //Minimum Video you want to buffer while Playing
        public static final int MIN_BUFFER_DURATION = 2000;
        //Max Video you want to buffer during PlayBack
        public static final int MAX_BUFFER_DURATION = 5000;
        //Min Video you want to buffer before start Playing it
        public static final int MIN_PLAYBACK_START_BUFFER = 1500;
        //Min video You want to buffer when user resumes video
        public static final int MIN_PLAYBACK_RESUME_BUFFER = 2000;
    }

}
