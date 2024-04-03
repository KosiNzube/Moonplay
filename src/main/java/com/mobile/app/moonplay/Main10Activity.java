package com.mobile.app.moonplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;

import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main10Activity extends AppCompatActivity  implements SeekBar.OnSeekBarChangeListener {
    PlayerView videoView;
    SeekBar seekBar;
    boolean rotation;
    boolean viewer;
    int duration;
    ImageView pauseP, back;
    ProgressBar loadingPanel;
    boolean pauseee;

    Handler handlery;
    Runnable runnable;
    ProgressBar progressBar;
    static int mio;
    String name;
    String namei;
    ImageView next10, previous10;

    ExoPlayer exoPlayer;
    ImageView bac;

    private boolean playWhenReady = true;
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
    private int sWidth,sHeight;
    private View decorView;
    private int uiImmersiveOptions;
    private int brightness, mediavolume,device_height,device_width;
    private Display display;
    private Point size;

    private boolean isPlaying;
    private int pos;
    String image;
    private PlaybackState playbackState;
    static String Dlink;
    static String Dname;
    String genre;
    private boolean dragging;
    TextView txt;

    private int position;
    Runnable updatePlayer;
    private Handler mainHandler;
    private TextView noin;
    static HashMap<String,Integer> hashMap=new HashMap<>();
    HashMap<String,Integer> testHashMap2= new HashMap<>();
    private int x;
    private int savedint;
    private int cony;
    private static int splashy=150;
    TextView nameofmovie,lengthmov;
    private int xxx;
    private View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        LoadControl loadControl=new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true,16))
                .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,VideoPlayerConfig.MAX_BUFFER_DURATION,VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .build();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(this).setExtensionRendererMode(exte);

        exoPlayer = new SimpleExoPlayer.Builder(this,renderersFac).setLoadControl(loadControl).build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        next10 = findViewById(R.id.straight);
        previous10 = findViewById(R.id.rotation);
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        sWidth = size.x;
        sHeight = size.y;

        txt=findViewById(R.id.curmovie);

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
        Intent intent = getIntent();

        exoPlayer = new ExoPlayer() {
            @Override
            public Looper getPlaybackLooper() {
                return null;
            }

            @Override
            public Clock getClock() {
                return null;
            }

            @Override
            public void retry() {

            }

            @Override
            public void prepare(MediaSource mediaSource) {

            }

            @Override
            public void prepare(MediaSource mediaSource, boolean resetPosition, boolean resetState) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources, boolean resetPosition) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources, int startWindowIndex, long startPositionMs) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource, long startPositionMs) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource, boolean resetPosition) {

            }

            @Override
            public void addMediaSource(MediaSource mediaSource) {

            }

            @Override
            public void addMediaSource(int index, MediaSource mediaSource) {

            }

            @Override
            public void addMediaSources(List<MediaSource> mediaSources) {

            }

            @Override
            public void addMediaSources(int index, List<MediaSource> mediaSources) {

            }

            @Override
            public void setShuffleOrder(ShuffleOrder shuffleOrder) {

            }

            @Override
            public PlayerMessage createMessage(PlayerMessage.Target target) {
                return null;
            }


            @Override
            public void setSeekParameters(@Nullable SeekParameters seekParameters) {

            }

            @Override
            public SeekParameters getSeekParameters() {
                return null;
            }

            @Override
            public void setForegroundMode(boolean foregroundMode) {

            }

            @Override
            public void setPauseAtEndOfMediaItems(boolean pauseAtEndOfMediaItems) {

            }

            @Override
            public boolean getPauseAtEndOfMediaItems() {
                return false;
            }

            @Override
            public void experimentalSetOffloadSchedulingEnabled(boolean offloadSchedulingEnabled) {

            }

            @Override
            public boolean experimentalIsSleepingForOffload() {
                return false;
            }

            @Nullable
            @Override
            public AudioComponent getAudioComponent() {
                return null;
            }

            @Nullable
            @Override
            public VideoComponent getVideoComponent() {
                return null;
            }

            @Nullable
            @Override
            public TextComponent getTextComponent() {
                return null;
            }

            @Nullable
            @Override
            public MetadataComponent getMetadataComponent() {
                return null;
            }

            @Nullable
            @Override
            public DeviceComponent getDeviceComponent() {
                return null;
            }

            @Override
            public void addAudioOffloadListener(AudioOffloadListener listener) {

            }

            @Override
            public void removeAudioOffloadListener(AudioOffloadListener listener) {

            }

            @Override
            public Looper getApplicationLooper() {
                return null;
            }

            @Override
            public void addListener(EventListener listener) {

            }

            @Override
            public void addListener(Listener listener) {

            }

            @Override
            public void removeListener(EventListener listener) {

            }

            @Override
            public void removeListener(Listener listener) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems, boolean resetPosition) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems, int startWindowIndex, long startPositionMs) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem, long startPositionMs) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem, boolean resetPosition) {

            }

            @Override
            public void addMediaItem(MediaItem mediaItem) {

            }

            @Override
            public void addMediaItem(int index, MediaItem mediaItem) {

            }

            @Override
            public void addMediaItems(List<MediaItem> mediaItems) {

            }

            @Override
            public void addMediaItems(int index, List<MediaItem> mediaItems) {

            }

            @Override
            public void moveMediaItem(int currentIndex, int newIndex) {

            }

            @Override
            public void moveMediaItems(int fromIndex, int toIndex, int newIndex) {

            }

            @Override
            public void removeMediaItem(int index) {

            }

            @Override
            public void removeMediaItems(int fromIndex, int toIndex) {

            }

            @Override
            public void clearMediaItems() {

            }

            @Override
            public boolean isCommandAvailable(int command) {
                return false;
            }

            @Override
            public Commands getAvailableCommands() {
                return null;
            }

            @Override
            public void prepare() {

            }

            @Override
            public int getPlaybackState() {
                return playbackState.getState();
            }


            @Override
            public int getPlaybackSuppressionReason() {
                return exoPlayer.getPlaybackSuppressionReason();
            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Nullable
            @Override
            public ExoPlaybackException getPlayerError() {
                return null;
            }

            @Nullable
            @Override
            public ExoPlaybackException getPlaybackError() {
                return null;
            }

            @Override
            public void play() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void setPlayWhenReady(boolean playWhenReady) {

            }

            @Override
            public boolean getPlayWhenReady() {
                return false;
            }

            @Override
            public void setRepeatMode(int repeatMode) {

            }

            @Override
            public int getRepeatMode() {
                return exoPlayer.getRepeatMode();
            }

            @Override
            public void setShuffleModeEnabled(boolean shuffleModeEnabled) {

            }

            @Override
            public boolean getShuffleModeEnabled() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }

            @Override
            public void seekToDefaultPosition() {

            }

            @Override
            public void seekToDefaultPosition(int windowIndex) {

            }

            @Override
            public void seekTo(long positionMs) {

            }

            @Override
            public void seekTo(int windowIndex, long positionMs) {

            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public void previous() {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public void next() {

            }

            @Override
            public void setPlaybackParameters(@Nullable PlaybackParameters playbackParameters) {

            }

            @Override
            public void setPlaybackSpeed(float speed) {

            }

            @Override
            public PlaybackParameters getPlaybackParameters() {
                return null;
            }

            @Override
            public void stop() {

            }

            @Override
            public void stop(boolean reset) {

            }

            @Override
            public void release() {

            }

            @Override
            public int getRendererCount() {
                return 0;
            }

            @Override
            public int getRendererType(int index) {
                return 0;
            }

            @Nullable
            @Override
            public TrackSelector getTrackSelector() {
                return null;
            }

            @Override
            public TrackGroupArray getCurrentTrackGroups() {
                return null;
            }

            @Override
            public TrackSelectionArray getCurrentTrackSelections() {
                return null;
            }

            @Override
            public List<Metadata> getCurrentStaticMetadata() {
                return null;
            }

            @Override
            public MediaMetadata getMediaMetadata() {
                return null;
            }

            @Nullable
            @Override
            public Object getCurrentManifest() {
                return null;
            }

            @Override
            public Timeline getCurrentTimeline() {
                return null;
            }

            @Override
            public int getCurrentPeriodIndex() {
                return 0;
            }

            @Override
            public int getCurrentWindowIndex() {
                return 0;
            }

            @Override
            public int getNextWindowIndex() {
                return 0;
            }

            @Override
            public int getPreviousWindowIndex() {
                return 0;
            }

            @Nullable
            @Override
            public Object getCurrentTag() {
                return null;
            }

            @Nullable
            @Override
            public MediaItem getCurrentMediaItem() {
                return null;
            }

            @Override
            public int getMediaItemCount() {
                return 0;
            }

            @Override
            public MediaItem getMediaItemAt(int index) {
                return null;
            }

            @Override
            public long getDuration() {
                return 0;
            }

            @Override
            public long getCurrentPosition() {
                return 0;
            }

            @Override
            public long getBufferedPosition() {
                return 0;
            }

            @Override
            public int getBufferedPercentage() {
                return 0;
            }

            @Override
            public long getTotalBufferedDuration() {
                return 0;
            }

            @Override
            public boolean isCurrentWindowDynamic() {
                return false;
            }

            @Override
            public boolean isCurrentWindowLive() {
                return false;
            }

            @Override
            public long getCurrentLiveOffset() {
                return 0;
            }

            @Override
            public boolean isCurrentWindowSeekable() {
                return false;
            }

            @Override
            public boolean isPlayingAd() {
                return false;
            }

            @Override
            public int getCurrentAdGroupIndex() {
                return 0;
            }

            @Override
            public int getCurrentAdIndexInAdGroup() {
                return 0;
            }

            @Override
            public long getContentDuration() {
                return 0;
            }

            @Override
            public long getContentPosition() {
                return 0;
            }

            @Override
            public long getContentBufferedPosition() {
                return 0;
            }

            @Override
            public AudioAttributes getAudioAttributes() {
                return null;
            }

            @Override
            public void setVolume(float audioVolume) {

            }

            @Override
            public float getVolume() {
                return 0;
            }

            @Override
            public void clearVideoSurface() {

            }

            @Override
            public void clearVideoSurface(@Nullable Surface surface) {

            }

            @Override
            public void setVideoSurface(@Nullable Surface surface) {

            }

            @Override
            public void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {

            }

            @Override
            public void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {

            }

            @Override
            public void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {

            }

            @Override
            public void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {

            }

            @Override
            public void setVideoTextureView(@Nullable TextureView textureView) {

            }

            @Override
            public void clearVideoTextureView(@Nullable TextureView textureView) {

            }

            @Override
            public VideoSize getVideoSize() {
                return null;
            }

            @Override
            public List<Cue> getCurrentCues() {
                return null;
            }

            @Override
            public DeviceInfo getDeviceInfo() {
                return null;
            }

            @Override
            public int getDeviceVolume() {
                return 0;
            }

            @Override
            public boolean isDeviceMuted() {
                return false;
            }

            @Override
            public void setDeviceVolume(int volume) {

            }

            @Override
            public void increaseDeviceVolume() {

            }

            @Override
            public void decreaseDeviceVolume() {

            }

            @Override
            public void setDeviceMuted(boolean muted) {

            }
        };
        noin = findViewById(R.id.textView5);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
        xxx=intent.getExtras().getInt("curi",0);

        SharedPreferences sharedPreferences1=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor1=sharedPreferences1.edit();
        editor1.putString("curname",name);
        editor1.putString("curlink",linkk);
        editor1.commit();


        image=name;

        genre=intent.getExtras().getString("genre");

        String v=genre.toLowerCase().trim();

        nameofmovie=findViewById(R.id.nameofmovie);
        nameofmovie.setText(name);
        seekBar = findViewById(R.id.seekBar);
        final View view = findViewById(R.id.view);

        relativeLayout = findViewById(R.id.control);
        relativeLayout.setVisibility(View.INVISIBLE);
        seekBar.setVisibility(View.INVISIBLE);
        videoView = findViewById(R.id.videoviewio);
        videoView.requestFocus();
        seekBar.requestFocus();


        initializeSeekBar();
        pauseee = false;
        bac = findViewById(R.id.stopV);

        pauseP = findViewById(R.id.pauseP);


        back = findViewById(R.id.backkk);
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

                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("curint", (int) exoPlayer.getCurrentPosition());


                editor.commit();
                if (exoPlayer != null) {
                    exoPlayer.release();
                }


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

        viewer = true;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewer) {
                    viewer = false;
                    relativeLayout.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                    bac.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout.setVisibility(View.INVISIBLE);
                    seekBar.setVisibility(View.INVISIBLE);
                    bac.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    viewer = true;
                }
            }
        });
        rotation = true;
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
        if (savedInstanceState != null) {
            pos = savedInstanceState.getInt("pos");
        }


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
            Toast.makeText(Main10Activity.this,"Recovering state",Toast.LENGTH_SHORT).show();






        }


        next10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition() + 10000);
            }
        });

        previous10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(exoPlayer.getCurrentPosition() - 10000);
            }
        });

        initializePlayer();


        loadingPanel = findViewById(R.id.proo);





        mainHandler = new Handler();

        execute();

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
                    case ExoPlayer.STATE_ENDED:
                        viewer = false;
                        relativeLayout.setVisibility(View.VISIBLE);
                        back.setVisibility(View.VISIBLE);
                        exoPlayer.seekTo(0);
                        isPlaying = false;
                        exoPlayer.setPlayWhenReady(false);
                        pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        seekBar.setVisibility(View.VISIBLE);

                        break;

                    case ExoPlayer.STATE_IDLE:


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
                txt.setText(curDur);
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

    private void initializePlayer(){

        videoView.setPlayer(exoPlayer);
        Uri uri=Uri.parse(linkk);
        MediaSource mediaSource=buildMediaSource(uri);

        exoPlayer.seekTo(0);
        if (x>0) {
            exoPlayer.seekTo(x);
        }else{
            if (xxx>0){
                exoPlayer.seekTo(xxx);
            }
        }

        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare(mediaSource,false,false);




        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("curint", (int) exoPlayer.getCurrentPosition());

        editor.commit();



    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        exoPlayer.release();
        refresh();

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


        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("curint", (int) exoPlayer.getCurrentPosition());

        editor.commit();
        if (exoPlayer != null) {
            exoPlayer.release();
        }
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
    protected void onDestroy() {
        super.onDestroy();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();

            }
        }

    }
    private MediaSource buildMediaSource(Uri uri){
        DataSource.Factory dataSourceFac=new DefaultDataSourceFactory(this,"exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFac).createMediaSource(uri);

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
    private void saveHashmap(String image, int obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Main10Activity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(image,json);
        editor.apply();     // This line is IMPORTANT !!!
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
}
