package com.mobile.app.moonplay;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.device.DeviceInfo;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheEvictor;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class instantlayx extends RecyclerView.Adapter<instantlayx.MyViewHolder> {


    private Context context;
    private List<instantv> itemDataList;
    private final static int FADE_DURATION = 690;

    SimpleCache  cac;

    PlayerView videoView;
    ExoPlayer exoPlayer;

    public instantlayx(Context context, List<instantv> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_videos,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



      //  myViewHolder.txt_item_title.setText(itemDataList.get(i).getVideo());
       initializePlayer(itemDataList.get(i).getVideo());


        /*
        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        myViewHolder.genre.setText(itemDataList.get(i).getHash());
        myViewHolder.maingenre.setText(itemDataList.get(i).getHash());

        if (itemDataList.get(i).getName().length()>18){
            myViewHolder.mb.setText(itemDataList.get(i).getName().substring(0, 18) + "...");
        }else {
            myViewHolder.mb.setText(itemDataList.get(i).getName());
        }
        myViewHolder.maindes.setText(itemDataList.get(i).getDescription());
        try{
            Glide.with(context)
                    .load(itemDataList.get(i).getPicture())
                    .centerCrop()
                    .into(myViewHolder.img_item);

            // setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }
        setFadeAnimation(myViewHolder.img_item);



        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("hash",itemDataList.get(i).getHash());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });

        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });
*/
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void initializePlayer(String x){
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
                return getPlaybackState();
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

   //     TrackSelection.Factory adaptiveTrackSelection = new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter());


        LoadControl loadControl=new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true,16))
                .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,VideoPlayerConfig.MAX_BUFFER_DURATION,VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .build();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(context).setExtensionRendererMode(exte);

        exoPlayer = new SimpleExoPlayer.Builder(context,renderersFac).setLoadControl(loadControl).build();
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Exo2"), defaultBandwidthMeter);

        videoView.setPlayer(exoPlayer);
        //Uri uri=Uri.parse(x);
      //  Handler mainHandler = new Handler();





        MediaSource mediaSource =buildMediaSource(Uri.parse(x));
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
                        //loadingPanel.setVisibility(View.GONE);

                        break;
                    case ExoPlayer.STATE_BUFFERING:
                        // loadingPanel.setVisibility(View.VISIBLE);
                        break;

                    case ExoPlayer.STATE_ENDED:
                        exoPlayer.seekTo(0);
                        exoPlayer.setPlayWhenReady(true);
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

        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare(mediaSource,false,false);



    }

    private MediaSource buildMediaSource(Uri uri){


        DataSource.Factory dataSourceFac=new DefaultDataSourceFactory(context,"exoplayer-codelab");

        File cf=new File(context.getExternalCacheDir(),"instantmedia");
        CacheEvictor ce=new LeastRecentlyUsedCacheEvictor(1*1024*1024);

      cac = new SimpleCache(cf, ce);
        CacheDataSourceFactory cacheDataSourceFactory=new CacheDataSourceFactory(cac, new DefaultHttpDataSourceFactory("ExoplayerDemo"));
        return null;

    }


    @Override
    public int getItemCount() {

        return itemDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre,maindes,maingenre;
        ImageView img_item,io;
        RelativeLayout card;


        Button mb;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.book_title_id);
            videoView = itemView.findViewById(R.id.videoviewio);
            /*
            img_item=itemView.findViewById(R.id.itemImage);

            mb=itemView.findViewById(R.id.mb);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);
*/




        }







    }

}
