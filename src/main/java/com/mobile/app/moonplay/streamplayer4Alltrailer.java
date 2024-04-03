package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.media.session.PlaybackState;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TrackSelectionDialogBuilder;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.reflect.TypeToken;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import com.mobile.app.moonplay.feature.controlview.GesturePlayerControlView;
import com.mobile.app.moonplay.feature.controlview.TapToHidePlayerControlView;
import com.mobile.app.moonplay.feature.gl.GLAnime4K;
import com.mobile.app.moonplay.feature.playerview.YavpEPlayerView;
import com.mobile.app.moonplay.feature.playerview.YavpPlayerView;
import com.mobile.app.moonplay.ui.playback.views.ControlQuickSettingsButton;
import com.mobile.app.moonplay.util.ConfigKeys;
import com.mobile.app.moonplay.util.ConfigUtil;
import com.mobile.app.moonplay.util.Logging;

public class streamplayer4Alltrailer extends AppCompatActivity implements  YAVPApp.ICrashListener, GesturePlayerControlView.Listener  {
    PlayerView videoView;
    private YavpPlayerView playerView;

    boolean rotation;
    private ControlQuickSettingsButton goPipQSButton;
    boolean viewer;
    private ImaAdsLoader adsLoader;
    private final long MAX_CACHE_SIZE = 100 * 1024 * 1024;//bytes
    private boolean isended=false;
    private int originalVolumeIndex;
    private boolean isBufferingIndicatorBlocked = false;
    private ProgressWheel bufferingIndicatorNormal;
    @Nullable
    private YavpEPlayerView glPlayerView;
    private DrawerLayout quickAccessDrawer;
    private AudioManager audioManager;
    private BroadcastReceiver pipBroadcastReceiver;
    private ImageView pip;
    public static  String pname;
    public static  String plink;
    public static int ppos;
    public static  String pgenre;
    public static String pimage;
    public static String psub;
    private boolean isPlayerInitialized;

    @Override
    public void onCrash(Throwable ex) {

    }

    private static final class PIPConstants
    {
        /**
         * Intent Action for media controls
         */
        private static final String ACTION_MEDIA_CONTROL = "media_control";

        /**
         * Intent Extra key for the request id
         */
        private static final String EXTRA_REQUEST_ID = "control_type";

        /**
         * Request id to Play/Pause playback
         */
        private static final int REQUEST_PLAY_PAUSE = 0;

        /**
         * Request id to Replay the video
         */
        private static final int REQUEST_REPLAY = 1;

        /**
         * Request id to fast- forward the video
         */
        private static final int REQUEST_FAST_FORWARD = 2;

        /**
         * Request id to rewind the video
         */
        private static final int REQUEST_REWIND = 3;
    }
    private boolean isBufferingState = false;
    private boolean isShowingTrackSelectionDialog;

    private static final class Messages
    {
        /**
         * Message id to reset wasBackPressedOnce flag
         */
        private static final int RESET_BACK_PRESSED = 2;

        /**
         * Message to check the battery level. (only call once - this message calls itself)
         */
        private static final int BATTERY_WARN_CHECK = 3;

        /**
         * Message to make the buffering indicator visible (with a *optional* delay)
         */
        private static final int SHOW_BUFFERING_INDICATOR = 4;

        /**
         * Message to unblock the buffering indicator
         */
        private static final int UNBLOCK_BUFFERING_INDICATOR = 5;

        /**
         * hide the system ui navbar
         */


        private static final int HIDE_SYSTEM_UI_NAVBAR = 6;
    }
    private boolean wasBackPressedOnce = false;
    private boolean isPictureInPicture = false;
    private ProgressWheel bufferingIndicatorPip;

    private final Handler delayHandler = new Handler(Looper.getMainLooper())
    {
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case Messages.RESET_BACK_PRESSED:
                {
                    wasBackPressedOnce = false;
                    break;
                }
                case Messages.SHOW_BUFFERING_INDICATOR:
                {
                    setBufferingIndicatorVisible(true, isPictureInPicture);
                }
                case Messages.UNBLOCK_BUFFERING_INDICATOR:
                {
                    //set visibility
                    bufferingIndicatorNormal.setWillNotDraw(!isBufferingState || isPictureInPicture);

                    //reset progress + start spinning again
                    bufferingIndicatorNormal.setInstantProgress(0f);
                    bufferingIndicatorNormal.setLinearProgress(false);
                    bufferingIndicatorNormal.spin();

                    //unblock
                    isBufferingIndicatorBlocked = false;
                    break;
                }
                case Messages.HIDE_SYSTEM_UI_NAVBAR:
                {
                    //hide system ui
                    View decor = getWindow().getDecorView();
                    decor.setSystemUiVisibility(decor.getVisibility() | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                }
            }
        }
    };

    RelativeLayout xl;
    int duration;
    ImageView pauseP, back,pip5;
    ProgressBar loadingPanel;
    boolean pauseee;
    static int yoyoo;
    Handler handlery;
    DataSource.Factory dataSourceFactory;
    private GesturePlayerControlView playerControlView;
    Runnable runnable;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    static int mio;
    String name;
    static ArrayList<Streampostion> xx=new ArrayList<>();
    String namei;

    SimpleExoPlayer exoPlayer;
    SimpleCache  cac;
    LeastRecentlyUsedCacheEvictor ce=null;
    private File cacheDir;
    private GLAnime4K anime4KFilter;
    /**
     * The cache used to download video data
     */    private ExoDatabaseProvider databaseProvider;

    private Cache downloadCache;

    /**
     * Database provider for cached files
     */
    private boolean playWhenReady = true;
    String linkk;
    static int lastWatched;
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
    Uri uri;
    private boolean isPlaying;
    private int pos;
    String image;
    String subtitleUri=null;
    String originame;
    private PlaybackState playbackState;
    static String Dlink;
    static String Dname;
    String genre;
    watchlist watchlist;
    private boolean dragging;
    private FrameLayout playerViewPlaceholder;
    ImageView vivi;
    String imgz;
    private int position;
    Runnable updatePlayer;
    private Handler mainHandler;
    private TextView noin;
    private MusicService musicService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicService.MusicBinder mServiceBinder = (MusicService.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
          //  mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };
    static HashMap<String,Integer> hashMap=new HashMap<>();
    HashMap<String,Integer> testHashMap2= new HashMap<>();
    private int x;
    private int savedint;
    private int cony;
    private static int splashy=150;
    private TextView txt_ct;
    private FirebaseAuth firebaseAuth;
    Timestamp timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Intent intentx = new Intent(this, MusicService.class);
        this.bindService(intentx, serviceConnection, Context.BIND_AUTO_CREATE);


        setUseController(true);
        cacheDir=new File(this.getCacheDir(), "media");
        databaseProvider =new ExoDatabaseProvider(this);
        downloadCache=new SimpleCache(cacheDir, new LeastRecentlyUsedCacheEvictor(MAX_CACHE_SIZE), databaseProvider);
        playerViewPlaceholder = findViewById(R.id.pb_playerViewPlaceholder);

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();
        adsLoader=new ImaAdsLoader.Builder(this).build();

        txt_ct=findViewById(R.id.exo_position);
        pip5=findViewById(R.id.pip);
        pip5.setImageResource(R.drawable.ic_offline_pin_black_24dp);

        vivi=findViewById(R.id.imagex);
        vivi.setVisibility(View.GONE);


        xl=findViewById(R.id.x1);


        Intent intent = getIntent();

        originame=intent.getExtras().getString("origin");

        noin = findViewById(R.id.textView5);


        playerControlView = findViewById(R.id.pb_playerControlView);
        playerControlView.getPlayerControls().setVisibilityChangeCallback(new PlayerControlsVisibilityListener());
        int seekIncrement = ConfigUtil.getConfigInt(this, ConfigKeys.KEY_SEEK_BUTTON_INCREMENT, R.integer.DEF_SEEK_BUTTON_INCREMENT);
        playerControlView.getPlayerControls().setFastForwardIncrementMs(seekIncrement);
        playerControlView.getPlayerControls().setRewindIncrementMs(seekIncrement);

        //set volume / brightness change listener
        playerControlView.setListener(this);

        bufferingIndicatorNormal = findViewById(R.id.pb_playerBufferingWheel_normal);
        bufferingIndicatorPip = findViewById(R.id.pb_playerBufferingWheel_pipmode);

        linkk = intent.getExtras().getString("video");

        name = intent.getExtras().getString("name");

        imgz=intent.getExtras().getString("photo");
        image = intent.getExtras().getString("name");
        genre = intent.getExtras().getString("genre");

        noin=findViewById(R.id.pb_streamTitle);
        noin.setText(name);

        if (intent.getExtras().getString("subt")!=null) {
            subtitleUri = intent.getExtras().getString("subt");
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Date date = new Date();
        sdf.format(date);
         timestamp = new Timestamp(date);

        if (imgz.startsWith("http")) {
            Picasso.get().load(imgz).fit().centerCrop().into(vivi);
        }


        String v = genre.toLowerCase().trim();







        videoView = findViewById(R.id.videoviewio);
        videoView.requestFocus();
        



        pauseee = false;


        pauseP = findViewById(R.id.exo_play);


        back = findViewById(R.id.backkk);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                if (exoPlayer.getDuration()>1) {

                    db.collection("Users").document(firebaseAuth.getUid()).collection("watchlist").add(watchlist).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //  Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Toast.makeText(getApplicationContext(),"failed2"+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
                }

*/
               onBackPressed();
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

        rotation = true;


        if (savedInstanceState != null) {
            pos = savedInstanceState.getInt("pos");
        }


    }



    //endregion



    private void updateGlEffectsDrawer()
    {
        boolean disableGl = ConfigUtil.getConfigBoolean(getApplicationContext(), ConfigKeys.KEY_DISABLE_GL_EFFECTS, R.bool.DEF_DISABLE_GL_EFFECTS);

        //lock effects drawer
        if (quickAccessDrawer != null)
            quickAccessDrawer.setDrawerLockMode(disableGl ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void playback_OnClick(View view)
    {
        switch (view.getId())
        {
            //region ~~ Screen Rotation Buttons (cycle modes >auto>portrait>landscape>auto>...)~~
            case R.id.pb_screen_rotation_auto:
            {

                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();

                //automatic/default screen rotation button
             //   screenRotationManager.setScreenMode(PlaybackActivity.ScreenRotationManager.SCREEN_LOCK_PORTRAIT);
                break;
            }
            case R.id.pb_screen_rotation_portrait:
            {
                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();
                //lock screen to portrait button
                //screenRotationManager.setScreenMode(PlaybackActivity.ScreenRotationManager.SCREEN_LOCK_LANDSCAPE);
                break;
            }
            case R.id.pb_screen_rotation_landscape:
            {
                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();
                //lock screen to landscape button
               // screenRotationManager.setScreenMode(PlaybackActivity.ScreenRotationManager.SCREEN_AUTO);
                break;
            }
            //endregion

            //region ~~ Player Quick Settings ~~
            case R.id.qs_btn_quality:
            {
                //show quality chooser dialog
                //first check that the playback service is ok and the player is valid
                if (isPlayerValid())
                {
                    Toast.makeText(getApplicationContext(),"Wait for connection",Toast.LENGTH_SHORT).show();

                    return;
                }

                //get track selector from service
                DefaultTrackSelector trackSelector = (DefaultTrackSelector) exoPlayer.getTrackSelector();
                if (trackSelector == null) return;

                //build the track selection dialog
                TrackSelectionDialogBuilder selectionDialogBuilder = new TrackSelectionDialogBuilder(this, " Quality", trackSelector, 0)
                        .setAllowAdaptiveSelections(false)
                        .setAllowMultipleOverrides(false)
                        .setShowDisableOption(false);

                //build dialog and show
                selectionDialogBuilder.build().show();

                //hide player controls
                playerControlView.getPlayerControls().hide();

                //hide drawers
                quickAccessDrawer.closeDrawers();
                break;
            }
            case R.id.qs_btn_jump_to:
            {
                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();
                //jump to position in video, show dialog
              //  JumpToFragment jumpTo = new JumpToFragment();
              //  jumpTo.show(getSupportFragmentManager(), playbackService.getPlayerInstance());

                //hide player controls
             //   playerControlView.getPlayerControls().hide();

                //hide drawers
             //   quickAccessDrawer.closeDrawers();
                break;
            }
            case R.id.qs_btn_pip:
            {

                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();
                //open pip player
              //  tryGoPip();
                break;
            }
            case R.id.qs_btn_repeat_tgl:
            {
                Toast.makeText(getApplicationContext(),"Screen locked",Toast.LENGTH_SHORT).show();


                //current video repeat toggle
             //   ControlQuickSettingsButton btnRepeatMode = findViewById(R.id.qs_btn_repeat_tgl);
            //    if (btnRepeatMode == null) break;

                //toggle looping state
             //   playbackService.setLooping(!playbackService.getLooping());

                //update ui
             //   btnRepeatMode.setIconTint(playbackService.getLooping() ? getColor(R.color.quick_settings_item_tint_active) : getColor(R.color.quick_settings_item_tint_default));
                break;
            }
            case R.id.qs_btn_cast:
            {
                //player cast mode
                break;
            }
            case R.id.qs_btn_captions:
            {
                //subtitles toggle
                break;
            }
            case R.id.qs_btn_skip_intro:
            {
                //skip the (anime) intro
                //quick hack: pretend you use some fancy API to get how long a anime opening is,
                //but actually just always skip 85 seconds since that is (roughly) how long most openings are :P
                //nobody could tell anyways ;)
            //    if (playbackService != null)
            //        playbackService.seekRelative(85000);

                break;
            }
            case R.id.qs_btn_app_settings:
            {
                //open global app settings
                //   startActivity(new Intent(this, AppSettingsActivity.class));
                break;
            }
            //endregion

            //region ~~ Player Effect Settings ~~
            case R.id.qs_btn_a4k_tgl:
            {
                //toggle anime4k on/off




              //  setAnime4kEnabled(!getIsAnime4kEnabled());

                //update button
                //anime4kQSButton.setIconTint(getIsAnime4kEnabled() ? getColor(R.color.quick_settings_item_tint_active) : getColor(R.color.quick_settings_item_tint_default));
                break;
            }
            //endregion

            case R.id.pb_quick_settings:
            {
                //open quick settings
                // quickAccessDrawer.openDrawer(GravityCompat.END);
                break;
            }
        }
    }

    private boolean isPlayerValid()
    {
        boolean isValid = exoPlayer != null && isPlayerInitialized;
        if (!isValid)
        {
            Logging.logW("Call to isPlayerValid() but player is NOT valid!");
        }

        return isValid;
    }




    @Override
    protected void onStart() {
        super.onStart();



        if (hashMap.containsKey(image)) {
            x = hashMap.get(image);
           // Toast.makeText(streamplayer4All.this,"Recovering state",Toast.LENGTH_SHORT).show();
        }




        initializePlayer();


        loadingPanel = findViewById(R.id.proo);



        mainHandler = new Handler();

        execute();

    }

    private HashMap<String, Integer> getHashmap(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(streamplayer4Alltrailer.this);
        Gson gson = new Gson();
        String json = prefs.getString(key,"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String,Integer>>(){}.getType();
        HashMap<String,Integer> obj = gson.fromJson(json, type);
        return obj;
    }

    private void execute() {

        if(exoPlayer!=null) {


            mainHandler.postDelayed(updatePlayer, 200);


        }
    }


    {
    }


    @SuppressLint("WrongThread")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exoPlayer!=null) {
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.stop();
            exoPlayer.release();

        }

        if (downloadCache!=null) {
            downloadCache.release();
            downloadCache = null;
        }

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
       // refresh();

    }
    private void initializePlayer() {

        BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();

        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(streamplayer4Alltrailer.this, getPackageName()), (TransferListener) bandwidthMeter,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS, DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);



        //initialize download cache

        DefaultDataSourceFactory ddsf = new DefaultDataSourceFactory(this, (TransferListener) bandwidthMeter, httpDataSourceFactory);
        dataSourceFactory = new CacheDataSourceFactory(downloadCache, ddsf, CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR | CacheDataSource.FLAG_BLOCK_ON_CACHE);


        DefaultTrackSelector trackSelector = new DefaultTrackSelector(streamplayer4Alltrailer.this);
        trackSelector.setParameters(
                trackSelector.getParameters()
                        .buildUpon()
                        .setTunnelingEnabled(true)
                        .build());


        LoadControl loadControl=buildLoadControl();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(this).setExtensionRendererMode(exte);






        SingleSampleMediaSource.Factory subtitleSource = new SingleSampleMediaSource.Factory(dataSourceFactory);

        MediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(dataSourceFactory)
                        .setAdsLoaderProvider(unusedAdTagUri -> adsLoader)
                        .setAdViewProvider(videoView);

        pip=findViewById(R.id.kanim);
        pip.setImageResource(R.drawable.ic_baseline_send_24);
        pip.setVisibility(View.GONE);


        exoPlayer = new SimpleExoPlayer.Builder(this).setLoadControl(loadControl).setTrackSelector(trackSelector).setMediaSourceFactory(mediaSourceFactory).build();


        setUseController(true);




        videoView.setPlayer(exoPlayer);

        adsLoader.setPlayer(exoPlayer);
        new YouTubeExtractor(streamplayer4Alltrailer.this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 18;
                    String downloadUrl = ytFiles.get(itag).getUrl();
                    uri = Uri.parse(downloadUrl);



                    pip5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (isNetworkAvailable()) {
                                Toast.makeText(streamplayer4Alltrailer.this, " Saving. . . ", Toast.LENGTH_SHORT).show();
                                musicService.downlo(downloadUrl, name, "", imgz);

                            }
                        }


                    });




                    pip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            downloadxa(downloadUrl,name);
                        }
                    });








                    Uri uri1=Uri.parse(getString(R.string.content_url));
                    Uri adtag=Uri.parse(getString(R.string.ad_tag_url));
                    MediaItem mediaItem = new MediaItem.Builder().setUri(uri).setAdTagUri(adtag).build();
                    exoPlayer.setMediaItem(mediaItem);

                    MediaSource mediaSource = x(uri,dataSourceFactory);

                    MediaSource mediaSources = new MergingMediaSource(mediaSource,subtitleSource.createMediaSource(Uri.parse(subtitleUri),Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP, Format.NO_VALUE, "en"),C.TIME_UNSET));

                    exoPlayer.prepare(mediaSources,false,false);
                    exoPlayer.setPlayWhenReady(true);
                    if (x > 0) {
                        exoPlayer.seekTo(x);
                    } else {
                        exoPlayer.seekTo(mio);
                    }


                    exoPlayer.addListener(new Player.Listener() {
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

                                    bufferingIndicatorNormal.setVisibility(View.INVISIBLE);
                                    bufferingIndicatorPip.setVisibility(View.INVISIBLE);
                                    vivi.setVisibility(View.GONE);
                                    break;
                                case ExoPlayer.STATE_BUFFERING:
                                    //   loadingPanel.setVisibility(View.VISIBLE);
                                    bufferingIndicatorNormal.setVisibility(View.VISIBLE);
                                    //    bufferingIndicatorPip.setVisibility(View.VISIBLE);
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
                    isPlayerInitialized = true;
                    setUseController(true);
                }
            }
        }.extract(linkk);




        //playerViewPlaceholder.removeAllViews();

      //  playerViewPlaceholder.addView(videoView);


        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("curint", (int) exoPlayer.getCurrentPosition());

        editor.commit();



    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }

    private void downloadxa(String mediaUrl, String des) {

        Intent intent=new Intent(this, sharer.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url",mediaUrl);
        intent.putExtra("jim",des);
        startActivity(intent);
    }

    private void downloadx(String mediaUrl, String des) {

        Intent intent=new Intent(streamplayer4Alltrailer.this, saver.class);
      //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url",mediaUrl);
        intent.putExtra("jim",des);
        startActivity(intent);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode)
    {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        //hide controls when entering pip, re-enable when exiting pip
        setUseController(!isInPictureInPictureMode);

        if (isInPictureInPictureMode)
        {
            //changed to pip mode, register broadcast receiver
            initPipBroadcastReceiverOnce();
            registerReceiver(pipBroadcastReceiver, new IntentFilter(PIPConstants.ACTION_MEDIA_CONTROL));

            //TODO: test on real device
            //set screen brightness to 0 (=auto) (kinda hacky, but should work just fine)
            //adjustScreenBrightness(-1000, true);

            //hide info text
            playerControlView.hideInfoText(true);
        }
        else
        {
            //changed to normal mode, remove pip broadcast receiver
            unregisterReceiver(pipBroadcastReceiver);
        }

        //set flag for outside use
        isPictureInPicture = isInPictureInPictureMode;
    }
    private void initPipBroadcastReceiverOnce()
    {
        //only init once
        if (pipBroadcastReceiver != null) return;

        //initialize the receiver
        pipBroadcastReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                //ignore any intent that is not ACTION_MEDIA_CONTROL
                if (intent == null || intent.getAction() == null || !intent.getAction().equals(PIPConstants.ACTION_MEDIA_CONTROL))
                    return;

                //do stuff based on request id
                int rId = intent.getIntExtra(PIPConstants.EXTRA_REQUEST_ID, -1);
                switch (rId)
                {
                    case PIPConstants.REQUEST_PLAY_PAUSE:
                    {
                        //play/pause request, toggle playWhenReady
                        exoPlayer.setPlayWhenReady(!exoPlayer.getPlayWhenReady());
                        break;
                    }
                    case PIPConstants.REQUEST_REPLAY:
                    {
                        //replay request, set playWhenReady and seek to 0
                        exoPlayer.seekTo(0);
                        exoPlayer.setPlayWhenReady(true);


                        break;
                    }
                    case PIPConstants.REQUEST_FAST_FORWARD:
                    {
                        //fast- forward request, fast- forward video
                        exoPlayer.seekTo(ConfigUtil.getConfigInt(getApplicationContext(), ConfigKeys.KEY_SEEK_BUTTON_INCREMENT, R.integer.DEF_SEEK_BUTTON_INCREMENT));
                        break;
                    }
                    case PIPConstants.REQUEST_REWIND:
                    {
                        //rewind request, rewind video
                        exoPlayer.seekTo(-ConfigUtil.getConfigInt(getApplicationContext(), ConfigKeys.KEY_SEEK_BUTTON_INCREMENT, R.integer.DEF_SEEK_BUTTON_INCREMENT));
                        break;
                    }
                    default:
                    {
                        //invalid request id, log
                        Logging.logW("Received Invalid PIP Request id: %d", rId);
                        break;
                    }
                }
            }
        };
    }

    private MediaSource buildMediaSource(Uri uri){
        DataSource.Factory dataSourceFac=new DefaultDataSourceFactory(this,"exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFac).createMediaSource(uri);

    }
    private void tryGoPip()
    {
        //lock out devices below API26 (don't support PIP)
        if (Util.SDK_INT < 26) return;

        //update pip and enter pip if update succeeded
        PictureInPictureParams params = updatePipControls();
        if (params != null)
        {
            //can enter pip mode, hide ui elements:
            //hide player controls
            setUseController(false);

            //hide quick settings and effect drawers
         //   quickAccessDrawer.closeDrawers();

            //hide info text immediately
           playerControlView.hideInfoText(true);

            //enter pip
            enterPictureInPictureMode(params);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();
                isPlayerInitialized = false;

            }
        }

    }

    private DefaultLoadControl buildLoadControl()
    {
        //get durations from res
        int minBuffer = getResources().getInteger(R.integer.playback_min_buffer_duration);
        int maxBuffer = getResources().getInteger(R.integer.playback_max_buffer_duration);
        int minStartBuffer = getResources().getInteger(R.integer.playback_min_start_buffer);
        int minResumeBuffer = getResources().getInteger(R.integer.playback_min_resume_buffer);

        //build load control
        return new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true, 16))
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .setBufferDurationsMs(minBuffer, maxBuffer, minStartBuffer, minResumeBuffer)
                .createDefaultLoadControl();
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


    public MediaSource x(Uri uri, DataSource.Factory dataSourceFactory)
    {
        //return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

        //create MediaSource according to stream type
        switch (Util.inferContentType(uri))
        {
            case C.TYPE_DASH:
            {
                //DASH stream
                Logging.logD("Creating DASH MediaSource from uri %s", uri.toString());
                return new DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            }
            case C.TYPE_HLS:
            {
                //HLS stream
                Logging.logD("Creating HLS MediaSource from uri %s", uri.toString());
                return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            }
            case C.TYPE_SS:
            {
                //SmoothStreaming stream
                Logging.logD("Creating SS MediaSource from uri %s", uri.toString());
                // return new SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
                return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            }
            case C.TYPE_OTHER:
            {
                //Progressive stream
                Logging.logD("Creating Progressive MediaSource from uri %s", uri.toString());
                return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            }
            default:
            {
                //invalid = type not supported
                Logging.logD("Cannot create MediaSource from uri %s : FileType not supported!", uri.toString());
                return null;
            }
        }
    }




    private PictureInPictureParams updatePipControls()
    {
        //lock out devices below API26 (don't support PIP)
        if (Util.SDK_INT < 26) return null;

        //create a list with all actions
        ArrayList<RemoteAction> actions = new ArrayList<>();

        //add buttons:
        //reverse button
        actions.add(createPipAction(PIPConstants.REQUEST_REWIND, R.drawable.ic_fast_rewind_black_24dp, R.string.pip_title_rewind, R.string.exo_controls_rewind_description));

        //region ~~Play/Pause/Replay Button ~~
        if (isended=true)
        {
            //ended, show replay button
            actions.add(createPipAction(PIPConstants.REQUEST_REPLAY, R.drawable.ic_replay_black_24dp, R.string.pip_title_replay, R.string.exo_controls_play_description));
        }
        else
        {
            //playing or paused
            if (exoPlayer.getPlayWhenReady())
            {
                //currently playing, show pause button
                actions.add(createPipAction(PIPConstants.REQUEST_PLAY_PAUSE, R.drawable.ic_pause_black_24dp, R.string.pip_title_pause, R.string.exo_controls_pause_description));
            }
            else
            {
                //currently paused, show play button
                actions.add(createPipAction(PIPConstants.REQUEST_PLAY_PAUSE, R.drawable.ic_play_arrow_black_24dp, R.string.pip_title_play, R.string.exo_controls_play_description));
            }
        }
        //endregion

        //fast- forward button
        actions.add(createPipAction(PIPConstants.REQUEST_FAST_FORWARD, R.drawable.ic_fast_forward_black_24dp, R.string.pip_title_forward, R.string.exo_controls_fastforward_description));

        //built the pip params
        PictureInPictureParams params = new PictureInPictureParams.Builder().setActions(actions).build();

        //this is how you update action items (etc.) for PIP mode.
        //this call can happen even if not in pip mode. In that case, the params
        //will be used for the next call of enterPictureInPictureMode
        setPictureInPictureParams(params);
        return params;
    }
    private RemoteAction createPipAction(int requestId, int resId, int titleId, int contentDescriptionId)
    {
        //make absolutely sure that device is >= API26
        if (Util.SDK_INT < 26) return null;

        //create pending intent with MEDIA_CONTROL action and requestID as extra REQUEST_ID
        //PendingIntent pIntent = PendingIntent.getActivity(this, requestId,
        //        new Intent(PIPConstants.ACTION_MEDIA_CONTROL).putExtra(PIPConstants.EXTRA_REQUEST_ID, requestId), 0);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, requestId,
                new Intent(PIPConstants.ACTION_MEDIA_CONTROL).putExtra(PIPConstants.EXTRA_REQUEST_ID, requestId), 0);

        //get the icon of the action by resId
        Icon icon = Icon.createWithResource(this, resId);

        //create the remote action
        return new RemoteAction(icon, getString(titleId), getString(contentDescriptionId), pIntent);
    }


    @Override
    public void onNoSwipeClick() {

    }

    @Override
    public void onVolumeChange(float volPercent)
    {
        setBufferingIndicatorProgress(volPercent);
    }

    @Override
    public void onBrightnessChange(float brightnessPercent)
    {
        setBufferingIndicatorProgress(brightnessPercent);
    }



    private void setUseController(boolean useController)
    {
        Logging.logD("setUseController() useController= %b", useController);

        //skip if not everything is ok
        if (playerControlView == null || exoPlayer == null )
        {
            Logging.logW("setUseController(): either playerControlView or playbackService is not valid!");
            return;
        }

        if (useController)
        {
            if (playerControlView.getPlayer() !=exoPlayer)
            {
                //player not set, fix that
                playerControlView.setPlayer(exoPlayer);
            }

            //show controls
            playerControlView.getPlayerControls().setControlsHidden(false).show();
        }
        else
        {
            //hide controls
            playerControlView.getPlayerControls().setControlsHidden(true).hide();
        }
    }
    private void setBufferingIndicatorProgress(float progressPercent)
    {
        //block buffering indicator
        isBufferingIndicatorBlocked = true;

        //set progress of normal indicator instantly and make it visible
        bufferingIndicatorNormal.setWillNotDraw(false);
        bufferingIndicatorNormal.setInstantProgress(progressPercent);
        bufferingIndicatorNormal.setLinearProgress(true);

        //reset later
        int indicatorVisibleDuration = getResources().getInteger(R.integer.info_buffering_indicator_duration)
                + ConfigUtil.getConfigInt(this, ConfigKeys.KEY_INFO_TEXT_DURATION, R.integer.DEF_INFO_TEXT_DURATION);
        delayHandler.removeMessages(Messages.UNBLOCK_BUFFERING_INDICATOR);
        delayHandler.sendEmptyMessageDelayed(Messages.UNBLOCK_BUFFERING_INDICATOR, indicatorVisibleDuration);
    }
    private void setBufferingIndicatorVisible(boolean isBuffering, boolean isPip)
    {
        isBufferingState = isBuffering;

        if (isBuffering)
        {
            //make right indicator visible and spin
            if (isPip)
            {
                //pip


                bufferingIndicatorPip.setWillNotDraw(false);
                if (!isBufferingIndicatorBlocked)
                    bufferingIndicatorPip.spin();
            }
            else
            {
                //normal
                bufferingIndicatorNormal.setWillNotDraw(false);
                if (!isBufferingIndicatorBlocked)
                    bufferingIndicatorNormal.spin();
            }

            //if (bufferingIndicatorNormal != null)
            //    bufferingIndicatorNormal.setVisibility(isPip ? View.GONE : View.VISIBLE);
            //if (bufferingIndicatorPip != null)
            //    bufferingIndicatorPip.setVisibility(isPip ? View.VISIBLE : View.GONE);
        }
        else
        {
            //make both invisible
            if (!isBufferingIndicatorBlocked)
                bufferingIndicatorNormal.setWillNotDraw(true);
            bufferingIndicatorPip.setWillNotDraw(true);

            //if (bufferingIndicatorNormal != null) bufferingIndicatorNormal.setVisibility(View.GONE);
            //if (bufferingIndicatorPip != null) bufferingIndicatorPip.setVisibility(View.GONE);
        }
    }
    private void setScreenForcedOn(boolean keepOn)
    {
        if (keepOn)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }



    private class PlayerControlsVisibilityListener implements TapToHidePlayerControlView.VisibilityChangeCallback
    {
        /**
         * Callback when the controls transition from hidden to visible
         */
        public void onShow()
        {
            //controls are visible, unlock drawer
           // quickAccessDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //updateGlEffectsDrawer();
        }

        /**
         * Callback when the controls transition from visible to hidden
         */
        public void onHide()
        {
            //controls are not visible, lock drawers closed
//            quickAccessDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Util.SDK_INT<24){
            if (exoPlayer!=null) {
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.stop();
                exoPlayer.release();

            }

        }
        if (downloadCache!=null) {
            downloadCache.release();
            downloadCache = null;
        }

    }



}
