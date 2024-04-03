package com.mobile.app.moonplay;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSinkFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;
import com.tonyodev.fetch2core.Func;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media.session.MediaButtonReceiver;
import kotlin.Pair;

public class MusicService extends Service implements FetchListener {
    SimpleCache  cac;
    private  static  int splashy=100;
    LeastRecentlyUsedCacheEvictor ce=null;
    private DefaultRenderersFactory renderersFac;
    private static String TAG = "MusicService";
    String mediaUrl;
    String p,lyric,gen;
    private LocalBroadcastManager broadcaster;
    String currlin;
    Boolean koko=false;
    SimpleExoPlayer player;
    public boolean vvv=false;
    static MediaSessionCompat mMediaSession;
    Fetch fetch;
    Boolean zaz=false;
    boolean isplay,isidle,isready,buff;
    private final Binder mBinder = new MusicBinder();
    private PlayerNotificationManager playerNotificationManager;
    ProgressiveMediaSource mediaSource;
    PlaybackStateCompat.Builder mStateBuilder;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    NotificationManager mNotificationManager;
    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
        mMediaSession = new MediaSessionCompat(this, TAG);

        //startp();




        super.onCreate();
    }

    public void startp(){




    }
    public void downlo(String URL2, String name,String subt,String photo) {
        File x=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/IcePlay");
        File x1=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/subtitles");
        if (!x.exists()){
            x.mkdir();
        }
        if (!x1.exists()){
            x1.mkdir();
        }


        String file=x+"/"+name+".mp4";
        String sub=x1+"/"+name+"subtitle"+".srt";
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(9)
                .build();

        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        final Request request = new Request(URL2, file);
        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");

        Request request1=new Request(subt,sub);
        request1.setPriority(Priority.HIGH);
        request1.setNetworkType(NetworkType.ALL);
        request1.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");





        request.setTag(name+"xxxxxx"+photo);
        request1.setTag("srt");
        ArrayList<Request> arrayList=new ArrayList<>();
        arrayList.add(request);
        arrayList.add(request1);

        fetch.enqueue(arrayList, new Func<List<Pair<Request, Error>>>() {
            @Override
            public void call(@NotNull List<Pair<Request, Error>> result) {


            }
        });

        fetch.addListener(this);


    }

    @Override
    public void onAdded(@NonNull Download download) {

    }

    @Override
    public void onCancelled(@NonNull Download download) {

    }

    @Override
    public void onCompleted(@NonNull Download download) {

    }

    @Override
    public void onDeleted(@NonNull Download download) {

    }

    @Override
    public void onDownloadBlockUpdated(@NonNull Download download, @NonNull DownloadBlock downloadBlock, int i) {

    }

    @Override
    public void onError(@NonNull Download download, @NonNull Error error, @Nullable Throwable throwable) {

    }

    @Override
    public void onPaused(@NonNull Download download) {

    }

    @Override
    public void onProgress(@NonNull Download download, long l, long l1) {

    }

    @Override
    public void onQueued(@NonNull Download download, boolean b) {

    }

    @Override
    public void onRemoved(@NonNull Download download) {

    }

    @Override
    public void onResumed(@NonNull Download download) {

    }

    @Override
    public void onStarted(@NonNull Download download, @NonNull List<? extends DownloadBlock> list, int i) {


    }

    @Override
    public void onWaitingNetwork(@NonNull Download download) {

    }


    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

// Request audio focus for playback
        int result = am.requestAudioFocus(focusChangeListener,
// Use the music stream.
                AudioManager.STREAM_MUSIC,
// Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);


        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            if (player == null) {
                startp();
            }
        }
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //  playerNotificationManager.setPlayer(null);
        return super.onUnbind(intent);
    }

    public void play(String channelUrl, String x,String lyrics,String genre) {
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

// Request audio focus for playback
        int result = am.requestAudioFocus(focusChangeListener,
// Use the music stream.
                AudioManager.STREAM_MUSIC,
// Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);


        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
// other app had stopped playing song now , so u can do u stuff now .

            p = x;

            lyrics.replaceAll(":", ".");
            lyrics.replaceAll(",", ".");
            lyrics.replaceAll("can't", "can not");
            lyrics.replaceAll("won't", "will not");
            lyrics.replaceAll("shouldn't", "should not");
            lyrics.replaceAll("couldn't", "could not");
            lyric = lyrics;
            gen = genre;
            String z = vx(x);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    this, Util.getUserAgent(this, "Android ExoPlayer"));
            String mediaUrl = channelUrl;


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
                if (genre.toLowerCase().contains("your music")) {
                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaUrl));
                } else {
                    mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse(mediaUrl));
                }


                player.prepare(mediaSource, true, false);

                player.setPlayWhenReady(true);

                if (player.isPlaying()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, splashy);
                }

                player.addListener(new SimpleExoPlayer.EventListener() {
                    @Override
                    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                        switch (playbackState) {
                            case ExoPlayer.STATE_READY:

                                vvv = true;


                                break;
                            case ExoPlayer.STATE_BUFFERING:
                                //   loadingPanel.setVisibility(View.VISIBLE);

                                //    bufferingIndicatorPip.setVisibility(View.VISIBLE);
                                break;


                        }


                        if (playbackState == ExoPlayer.STATE_BUFFERING) {
                            Intent intent = new Intent("com.example.exoplayer.PLAYER_STATUS");
                            intent.putExtra("state", PlaybackStateCompat.STATE_BUFFERING);
                            buff = true;
                            //  broadcaster.sendBroadcast(intent);
                            // mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, player.getCurrentPosition(), 1f);
                        } else if (playbackState == ExoPlayer.STATE_READY) {
                            vvv = true;
                            isready = true;
                            //  mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                            //   player.getCurrentPosition(), 1f);
                            Intent intent = new Intent("com.example.exoplayer.PLAYER_STATUS");
                            if (playWhenReady) {
                                intent.putExtra("state", PlaybackStateCompat.STATE_PLAYING);
                                vvv = true;
                                isplay = true;
                            } else {
                                intent.putExtra("state", PlaybackStateCompat.STATE_PAUSED);
                                isplay = false;
                            }
                            if (playWhenReady) {
                                intent.putExtra("state", PlaybackStateCompat.STATE_PLAYING);
                            } else {
                                intent.putExtra("state", PlaybackStateCompat.STATE_PAUSED);
                            }
                            //   broadcaster.sendBroadcast(intent);
                        }
                        if (playbackState == ExoPlayer.STATE_IDLE) {

                            isidle = true;
                        } else {
                            isidle = false;
                        }


                        if (playbackState == ExoPlayer.STATE_READY) {
                            vvv = true;
                            isready = true;
                        }

                        if (playbackState == ExoPlayer.STATE_ENDED) {

                            player.seekTo(0);
                            player.setPlayWhenReady(true);
                        }

                    }
                });


            }
        }
    }

    public void playlist(ArrayList channelUrl, String x, String lyrics, String genre) {
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int result = am.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            p = x;

            lyric = lyrics;
            gen = genre;
            String z = vx(x);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                    this, Util.getUserAgent(this, "Android ExoPlayer"));

            ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource();


            for (int i = 0; i < channelUrl.size(); i++) {
                mediaUrl = (String) channelUrl.get(i);
                MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaUrl));
                concatenatingMediaSource.addMediaSource(mediaSource);

            }


            player.prepare(concatenatingMediaSource);

            currlin = (String) channelUrl.get(player.getCurrentWindowIndex());
            player.setPlayWhenReady(true);


            Intent intent = new Intent("com.example.exoplayer.PLAYER_STATUS");
            player.addListener(new SimpleExoPlayer.EventListener() {
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    switch (playbackState) {
                        case ExoPlayer.STATE_READY:

                            break;
                        case ExoPlayer.STATE_BUFFERING:
                            //   loadingPanel.setVisibility(View.VISIBLE);

                            //    bufferingIndicatorPip.setVisibility(View.VISIBLE);
                            break;



                    }

                    if (playWhenReady) {
                        intent.putExtra("state", PlaybackStateCompat.STATE_PLAYING);
                        isplay = true;
                    } else {
                        intent.putExtra("state", PlaybackStateCompat.STATE_PAUSED);
                        isplay = false;
                    }


                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    switch (error.type) {

                        case ExoPlaybackException.TYPE_SOURCE:
                            zaz=true;
                            Toast.makeText(MusicService.this, genre+" currently not available ", Toast.LENGTH_LONG).show();
                            stopx();
                            break;

                        case ExoPlaybackException.TYPE_RENDERER:

                            break;

                        case ExoPlaybackException.TYPE_UNEXPECTED:
                            break;
                    }
                }
            });

        }
        }



        // mMediaSession.setPlaybackState(mStateBuilder.build());
        // showNotification(mStateBuilder.build());




    public String vx(String x) {

        return x;
    }
    public String link() {

        return currlin;
    }

    public String xx(){
        return p;
    }

    public String xix(){
        return lyric;
    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            player.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            player.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious(){
            player.seekTo(0);
        }
    }

    public void stop() {
        player.setPlayWhenReady(false);
        player.stop();

        if (cac!=null) {
            cac.release();
            cac = null;
        }
        player.release();


    }
    public void stopx() {


        player.setPlayWhenReady(false);
        player.stop();

        //  player.stop();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();

    }



    public int id(){
        return player.getAudioSessionId();
    }
    public void pause(){
        if (player!=null) {
            player.setPlayWhenReady(false);
        }
    }
    public void playy(){
        if (player!=null) {

            player.setPlayWhenReady(true);
        }
    }

    public String b(){
        return lyric;
    }

    public boolean isPlaying() {



        if (player.isPlaying()){
            vvv=true;
            return true;
        }else {
            return false;
        }


    }

    public SimpleExoPlayer semd(){
        return player;
    }
    public void releasePlayer(){
        if (playerNotificationManager!=null) {
            stopForeground(true);
            playerNotificationManager.setPlayer(null);

        }
        if (player!=null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.release();
            player = null;
        }
        if (cac!=null) {
            cac.release();
            cac = null;
        }

    }
    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver(){
        }

        @Override
        public void onReceive(Context context, Intent intent){
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }
    private AudioManager.OnAudioFocusChangeListener focusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    AudioManager am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    switch (focusChange) {

                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) :
                            // Lower the volume while ducking.
                         //   player.setVolume(0.2f);
                            break;
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) :
                            pause();
                            break;

                        case (AudioManager.AUDIOFOCUS_LOSS) :
                            pause();
                            ComponentName component =new ComponentName(MusicService.this,Main7Activity.class);
                            am.unregisterMediaButtonEventReceiver(component);
                            break;

                        case (AudioManager.AUDIOFOCUS_GAIN) :
                            // Return the volume to normal and resume if paused.
                          //  player.setVolume(1f);

                            if(player!=null) {
                                player.setPlayWhenReady(true);
                            }
                            break;
                        default: break;
                    }
                }
            };
}
