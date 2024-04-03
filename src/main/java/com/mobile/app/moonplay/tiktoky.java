package com.mobile.app.moonplay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Point;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;

import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSinkFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class tiktoky extends RecyclerView.Adapter<tiktoky.MyViewHolder> {

    boolean isplay,isidle,isready,buff;
    private Context context;
    private List<instV> itemDataList;
    private final static int FADE_DURATION = 590;
    private View viewHolderParent;
    private FrameLayout frameLayout;
    private PlayerView videoSurfaceView;
    private ArrayList<instV> mediaObjects = new ArrayList<>();
    private int videoSurfaceDefaultHeight = 0;
    private int screenDefaultHeight = 0;
    SimpleCache  cac;
    private  static  int splashy=100;
    LeastRecentlyUsedCacheEvictor ce=null;

    private int playPosition = -1;
    private boolean isVideoViewAdded;
    private RequestManager requestManager;
    // controlling playback state
    SessionManager sessionManager;
    private MusicServicex musicService;
    String current_id = null;
    String user_id = null;
    String mediaUrl = null;
    String titled = null;
    private boolean mBound = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicServicex.MusicBinder mServiceBinder = (MusicServicex.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };

    public tiktoky(Context context, List<instV> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.air_item,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Intent intent = new Intent(context, MusicServicex.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        myViewHolder.title.setText(itemDataList.get(i).getDes());
        this.context = context.getApplicationContext();
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        videoSurfaceDefaultHeight = point.x;
        screenDefaultHeight = point.y;
        videoSurfaceView = new PlayerView(this.context);
        videoSurfaceView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
        SimpleExoPlayer player;
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
      //  AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl=new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true,16))
                .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,VideoPlayerConfig.MAX_BUFFER_DURATION,VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .build();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(context).setExtensionRendererMode(exte);

        player = new SimpleExoPlayer.Builder(context,renderersFac).setLoadControl(loadControl).build();
        videoSurfaceView.setUseController(false);
        // Bind the player to the view.
        videoSurfaceView.setPlayer(player);





        myViewHolder.media_container.addView(videoSurfaceView);
        isVideoViewAdded = true;
        videoSurfaceView.requestFocus();
        videoSurfaceView.setVisibility(VISIBLE);
        videoSurfaceView.setAlpha(1);
        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups,
                                        TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {

                    case Player.STATE_BUFFERING:
                        Log.e(TAG, "onPlayerStateChanged: Buffering video.");
                        if (myViewHolder.progressBar != null) {
                            //progressBar.setVisibility(VISIBLE);
                        }
                        break;
                    case Player.STATE_ENDED:
                        Log.d(TAG, "onPlayerStateChanged: Video ended.");
                        player.seekTo(0);
                        break;
                    case Player.STATE_IDLE:
                        if (myViewHolder.progressBar != null) {
                            myViewHolder.progressBar.setVisibility(GONE);
                        }

                        break;



                    case Player.STATE_READY:
                        Log.e(TAG, "onPlayerStateChanged: Ready to play.");
                        if (myViewHolder.progressBar != null) {
                            myViewHolder.progressBar.setVisibility(GONE);
                        }
                        break;
                    default:
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




        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "Android ExoPlayer"));
        String mediaUrl = itemDataList.get(i).getVideo();


        if (mediaUrl != null) {
            File cf = new File(context.getExternalCacheDir(), "instantmediaxxx");


            if (ce == null) {
                ce = new LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024);
            }
            ExoDatabaseProvider exoDatabaseProvider = new ExoDatabaseProvider(context);

            if (cac == null) {
                cac = new SimpleCache(cf, ce, exoDatabaseProvider);
            }
            CacheDataSourceFactory cacheDataSourceFactory =
                    new CacheDataSourceFactory(cac, dataSourceFactory,
                            new FileDataSourceFactory(),
                            new CacheDataSinkFactory(cac, CacheDataSink.DEFAULT_FRAGMENT_SIZE),
                            0, null, new CacheKeyProvider());
            ProgressiveMediaSource mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse(mediaUrl));


            player.prepare(mediaSource, true, false);

            player.setPlayWhenReady(true);



        }




    }


    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {


        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        FrameLayout media_container;
        AppCompatTextView title,name;
        ImageView  volumeControl;
        ProgressBar progressBar;
        View parent;
        RequestManager requestManager;
        AppCompatImageView download;
        ShapeableImageView imageView;
        AppCompatImageView shareBtn;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            media_container = itemView.findViewById(R.id.media_container);
            title = itemView.findViewById(R.id.text_view_video_description);
            name = itemView.findViewById(R.id.text_view_account_handle);
            imageView=itemView.findViewById(R.id.image_view_profile_pic);
            progressBar = itemView.findViewById(R.id.progressBar);
            //   volumeControl = itemView.findViewById(R.id.volume_control);
            shareBtn=itemView.findViewById(R.id.image_view_option_share);
            download = itemView.findViewById(R.id.image_view_option_comment);
        }


        }
}
