package com.mobile.app.moonplay;



import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;

import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;

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
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created on : May 24, 2019
 * Author     : AndroidWave
 */
public class ExoPlayerRecyclerView extends RecyclerView {

    private static final String TAG = "ExoPlayerRecyclerView";
    private static final String AppName = "Android ExoPlayer";
    /**
     * PlayerViewHolder UI component
     * Watch PlayerViewHolder class
     */
    File cf=null;
    SimpleCache  cac;
    private ProgressBar progressBar;
    private TextView time;
    private View viewHolderParent;
    private FrameLayout mediaContainer;
    private TextView trend,caption;
    private Button mb;
    private PlayerView videoSurfaceView;
    private RelativeLayout relativeLayout;
    private CardView save;
    RelativeLayout zzz;
    private ImageView restart, full, mute,sjare;

    private SimpleExoPlayer videoPlayer=null;
    /**
     * variable declaration
     */
    // Media List
    LeastRecentlyUsedCacheEvictor ce=null;
    int position=0;
    private ArrayList<instantv> mediaObjects = new ArrayList<>();
    private int videoSurfaceDefaultHeight = 0;
    private int screenDefaultHeight = 0;
    private Context context;
    private int playPosition = -1;
    private boolean isVideoViewAdded;
    private RequestManager requestManager;
    // controlling volume state
    private VolumeState volumeState;
    private OnClickListener videoViewClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleVolume();
        }
    };

    public ExoPlayerRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ExoPlayerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        this.context = context.getApplicationContext();
        Display display = ((WindowManager) Objects.requireNonNull(
                getContext().getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        videoSurfaceDefaultHeight = point.x;
        screenDefaultHeight = point.y;

        videoSurfaceView = new PlayerView(this.context);
        videoSurfaceView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
       // TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector();

        //Create the player using ExoPlayerFactory
        DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(32*1024, 64*1024, 1024, 1024).createDefaultLoadControl();
        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(context).setExtensionRendererMode(exte);

        videoPlayer = new SimpleExoPlayer.Builder(context,renderersFac).setLoadControl(loadControl).build();
        // Disable Player Control
        videoSurfaceView.setUseController(false);
        // Bind the player to the view.
        videoSurfaceView.setPlayer(videoPlayer);
        // Turn on Volume
      //  setVolumeControl(VolumeState.OFF);


        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {


                    // There's a special case when the end of the list has been reached.
                    // Need to handle that with this bit of logic
                    if (!recyclerView.canScrollVertically(1)) {
                        playVideo(true);
                    } else {
                        playVideo(false);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                if (viewHolderParent != null && viewHolderParent.equals(view)) {
                    resetVideoView();
                }
            }
        });

        videoPlayer.addListener(new Player.EventListener() {
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
                        if (progressBar != null) {
                            //progressBar.setVisibility(VISIBLE);
                        }
                        break;
                    case Player.STATE_ENDED:
                        Log.d(TAG, "onPlayerStateChanged: Video ended.");
                        videoPlayer.seekTo(0);
                        break;
                    case Player.STATE_IDLE:
                        if (progressBar != null) {
                            progressBar.setVisibility(GONE);
                        }

                        break;



                    case Player.STATE_READY:
                        Log.e(TAG, "onPlayerStateChanged: Ready to play.");
                        if (progressBar != null) {
                            progressBar.setVisibility(GONE);
                        }
                        if (!isVideoViewAdded) {
                            addVideoView();
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
    }

    public void playVideo(boolean isEndOfList) {

        int targetPosition;

        if (!isEndOfList) {
            int startPosition = ((LinearLayoutManager) Objects.requireNonNull(getLayoutManager())).findFirstVisibleItemPosition();
            int endPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();

            // if there is more than 2 list-items on the screen, set the difference to be 1
            if (endPosition - startPosition > 1) {
                endPosition = startPosition + 1;
            }

            // something is wrong. return.
            if (startPosition < 0 || endPosition < 0) {
                return;
            }

            // if there is more than 1 list-item on the screen
            if (startPosition != endPosition) {

                int startPositionVideoHeight = getVisibleVideoSurfaceHeight(startPosition);
                int endPositionVideoHeight = getVisibleVideoSurfaceHeight(endPosition);

                targetPosition =
                        startPositionVideoHeight > endPositionVideoHeight ? startPosition : endPosition;
            } else {
                targetPosition = startPosition;
            }
        } else {
            targetPosition = mediaObjects.size() - 1;
        }

        Log.d(TAG, "playVideo: target position: " + targetPosition);

        // video is already playing so return
        if (targetPosition == playPosition) {

            return;
        }

        // set the position of the list-item that is to be played
        playPosition = targetPosition;
        if (videoSurfaceView == null) {

            return;
        }

        // remove any old surface views from previously playing videos
        videoSurfaceView.setVisibility(INVISIBLE);
        removeVideoView(videoSurfaceView);

        int currentPosition =
                targetPosition - ((LinearLayoutManager) Objects.requireNonNull(
                        getLayoutManager())).findFirstVisibleItemPosition();

        View child = getChildAt(currentPosition);
        if (child == null) {
            return;
        }

        PlayerViewHolder holder = (PlayerViewHolder) child.getTag();
        if (holder == null) {
            playPosition = 0;
            return;
        }
        progressBar = holder.progressBar;
     //   time=holder.time;
        viewHolderParent = holder.itemView;
        mediaContainer = holder.mediaContainer;
        save=holder.save;

        sjare=holder.sjare;
        restart=holder.restart;
        full=holder.full;
       // mute=holder.mute;
        zzz=holder.zzz;

        //mediaContainer.setBackgroundColor(getResources().getColor(R.color.kokocolor));


        videoSurfaceView.setPlayer(videoPlayer);
        viewHolderParent.setOnClickListener(videoViewClickListener);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, AppName));
        String mediaUrl = mediaObjects.get(targetPosition).getVideo();


        if (mediaUrl != null) {
          //  zzz.setBackgroundColor(getResources().getColor(R.color.purple));
             cf=new File(context.getExternalCacheDir(),"instantmedia");
            if (ce==null) {
                 ce = new LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024);
            }
            ExoDatabaseProvider exoDatabaseProvider=new ExoDatabaseProvider(context);


            if (cac==null) {
                cac = new SimpleCache(cf, ce, exoDatabaseProvider);
            }
            CacheDataSourceFactory cacheDataSourceFactory =
                    new CacheDataSourceFactory(cac, dataSourceFactory,
                            new FileDataSourceFactory(),
                            new CacheDataSinkFactory(cac, CacheDataSink.DEFAULT_FRAGMENT_SIZE),
                            0,null,new CacheKeyProvider());
            ProgressiveMediaSource mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse(mediaUrl));
            videoPlayer.prepare(mediaSource);
            videoPlayer.seekTo(0);
            setVolumeControl(VolumeState.ON);


            String totDur = String.format("%02d.%02d.%02d",
                    TimeUnit.MILLISECONDS.toHours(videoPlayer.getDuration()),
                    TimeUnit.MILLISECONDS.toMinutes(videoPlayer.getDuration()) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(videoPlayer.getDuration())), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(videoPlayer.getDuration()) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(videoPlayer.getDuration())));
            if (totDur.contains("-")){
                //time.setText("00.00");
            }else {
                //time.setText(totDur);
            }


            //mute.setOnClickListener(new OnClickListener() {
           //     @Override
            //    public void onClick(View view) {
             //       videoPlayer.setVolume(0f);

          //          toggleVolume();
          //      }
          //  });

/*

            restart.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoPlayer.seekTo(0);
                }
            });
*/
            sjare.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sharer=new Intent(Intent.ACTION_SEND);

                    sharer.setType(".mp4");
                    sharer.putExtra(Intent.EXTRA_STREAM,Uri.parse(mediaUrl));
                    sharer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(Intent.createChooser(sharer,"Share with"));


                }
            });



        }
    }

    /**
     * Returns the visible region of the video surface on the screen.
     * if some is cut off, it will return less than the @videoSurfaceDefaultHeight
     */
    private int getVisibleVideoSurfaceHeight(int playPosition) {
        int at = playPosition - ((LinearLayoutManager) Objects.requireNonNull(
                getLayoutManager())).findFirstVisibleItemPosition();
        Log.d(TAG, "getVisibleVideoSurfaceHeight: at: " + at);

        View child = getChildAt(at);
        if (child == null) {
            return 0;
        }

        int[] location = new int[2];
        child.getLocationInWindow(location);

        if (location[1] < 0) {
            return location[1] + videoSurfaceDefaultHeight;
        } else {
            return screenDefaultHeight - location[1];
        }
    }

    // Remove the old player
    private void removeVideoView(PlayerView videoView) {
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent == null) {
            return;
        }

        int index = parent.indexOfChild(videoView);
        if (index >= 0) {
            parent.removeViewAt(index);
            isVideoViewAdded = false;
            viewHolderParent.setOnClickListener(null);
        }
    }

    private void addVideoView() {
        mediaContainer.addView(videoSurfaceView);
        isVideoViewAdded = true;
        videoSurfaceView.requestFocus();
        videoSurfaceView.setVisibility(VISIBLE);
        videoSurfaceView.setAlpha(1);
    }

    private void resetVideoView() {
        if (isVideoViewAdded) {
            removeVideoView(videoSurfaceView);
            playPosition = -1;
            videoSurfaceView.setVisibility(INVISIBLE);
        }
    }

    public void releasePlayer() {

        if (videoPlayer != null) {

            videoPlayer.setPlayWhenReady(false);
            videoPlayer.stop();
            videoPlayer.release();

            if (cac!=null) {
                //cac.release();
                cac = null;
            }
            videoPlayer = null;
        }

        viewHolderParent = null;
    }

    public void onPausePlayer() {
        if (videoPlayer != null) {
            position= (int) videoPlayer.getCurrentPosition();
            videoPlayer.setPlayWhenReady(false);
           // setVolumeControl(VolumeState.OFF);
        }
    }
    public void paye() {
        if (videoPlayer != null ) {

            videoPlayer.seekTo(position);
            videoPlayer.setPlayWhenReady(false);
            if (relativeLayout != null) {
                relativeLayout.setVisibility(VISIBLE);

                relativeLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setVolumeControl(VolumeState.ON);

                        if (relativeLayout != null) {
                            relativeLayout.setVisibility(INVISIBLE);
                        }

                    }
                });
            }
        }


    }


    private void toggleVolume() {
        if (videoPlayer != null) {
            if (volumeState == VolumeState.OFF) {
                Log.d(TAG, "togglePlaybackState: enabling volume.");
                setVolumeControl(VolumeState.ON);
            } else if (volumeState == VolumeState.ON) {
                Log.d(TAG, "togglePlaybackState: disabling volume.");
                setVolumeControl(VolumeState.OFF);
            }
        }
    }

    private void setVolumeControl(VolumeState state) {
        volumeState = state;
        if (state == VolumeState.OFF) {
          //  videoPlayer.setVolume(0f);
            videoPlayer.setPlayWhenReady(false);
            if (relativeLayout != null) {
                relativeLayout.setVisibility(VISIBLE);
            }

         //   PlayerViewHolder playerViewHolder=new PlayerViewHolder(viewHolderParent);
         //     playerViewHolder.relativeLayout.setVisibility(VISIBLE);
            animateVolumeControl();
        } else if (state == VolumeState.ON) {
          //  videoPlayer.setVolume(1f);
            videoPlayer.setPlayWhenReady(true);
           // PlayerViewHolder playerViewHolder=new PlayerViewHolder(viewHolderParent);
            if (relativeLayout != null) {
                relativeLayout.setVisibility(INVISIBLE);
            }

           // playerViewHolder.relativeLayout.setVisibility(INVISIBLE);

            animateVolumeControl();
        }
    }

    private void animateVolumeControl() {
    }

    public void setMediaObjects(ArrayList<instantv> mediaObjects) {
        this.mediaObjects = mediaObjects;
    }

    /**
     * Volume ENUM
     */
    private enum VolumeState {
        ON, OFF
    }
}