package com.mobile.app.moonplay;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;

import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class stagger extends RecyclerView.Adapter<stagger.MyViewHolder>{
    private Context context;
    private List<video> itemDataList;

    ExoPlayer exoPlayer;


    public stagger(Context context, List<video> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.love,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {

        try {

        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }

       // holder.genre.setText(itemDataList.get(i).getName());






       // TrackSelection.Factory adaptiveTrackSelection = new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter());
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

        holder.videoView.setPlayer(exoPlayer);
        Uri uri=Uri.parse(itemDataList.get(i).getVideo());
        Handler mainHandler = new Handler();
        //      MediaSource mediaSource = new HlsMediaSource(uri, dataSourceFactory, mainHandler, null);
        MediaSource mediaSource =buildMediaSource(uri);

        holder.videoView.findFocus();


       exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare(mediaSource,false,false);
        exoPlayer.getAudioComponent().setVolume(0);

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


                        break;
                    case ExoPlayer.STATE_BUFFERING:

                        break;

                    case ExoPlayer.STATE_ENDED:

                        exoPlayer.seekTo(0);
                        exoPlayer.setPlayWhenReady(true);
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

    @Override
    public int getItemCount() {

        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_title,genre;
        ImageView img_item,image;
        RelativeLayout card;
        PlayerView videoView;
        ExoPlayer exoPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

         card=itemView.findViewById(R.id.jiji);

            videoView=itemView.findViewById(R.id.videoviewio);

           // genre=itemView.findViewById(R.id.type);

        }
    }
    private void initializePlayer(String linkk, PlayerView videoView){



    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFac=new DefaultDataSourceFactory(context,"exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFac).createMediaSource(uri);

    }

}
