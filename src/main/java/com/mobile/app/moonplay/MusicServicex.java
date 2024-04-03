package com.mobile.app.moonplay;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;

import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSinkFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheWriter;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.io.IOException;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MusicServicex extends Service {
    SimpleCache cac;
    private static int splashy = 100;
    LeastRecentlyUsedCacheEvictor ce = null;
    DataSpec dataSpec;
    Uri videoUri;
    private static String TAG = "MusicService";
    private static final int BUFFER_SIZE_BYTES = 128 * 1024;
    String p, lyric, gen;
    private LocalBroadcastManager broadcaster;
    static MediaSessionCompat mMediaSession;
    DefaultHttpDataSource.Factory defaultHttpDataSourceFactory;
    boolean isplay, isidle, isready, buff;
    private final Binder mBinder = new MusicBinder();


    private PlayerNotificationManager playerNotificationManager;
    PlaybackStateCompat.Builder mStateBuilder;

    DefaultDataSourceFactory dataSourceFactory;
    NotificationManager mNotificationManager;
    SimpleCache simpleCache = MyApp.simpleCache;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
        mMediaSession = new MediaSessionCompat(this, TAG);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        DefaultLoadControl loadControl =buildLoadControl();
        @DefaultRenderersFactory.ExtensionRendererMode int exte = DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac = new DefaultRenderersFactory(this).setExtensionRendererMode(exte);


        defaultHttpDataSourceFactory = new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true);
        dataSourceFactory = new DefaultDataSourceFactory(this, defaultHttpDataSourceFactory);




        super.onCreate();
    }

    public class MusicBinder extends Binder {
        public MusicServicex getService() {
            return MusicServicex.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
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

    public void play(String arrayList) {
        File cf=null;
        cf=new File(this.getExternalCacheDir(),"instantmedia");


        if (ce==null) {
            ce = new LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024);
        }
        ExoDatabaseProvider exoDatabaseProvider=new ExoDatabaseProvider(this);


        if (cac==null) {
            cac = new SimpleCache(cf, ce, exoDatabaseProvider);
        }
        CacheWriter.ProgressListener progressListener=new CacheWriter.ProgressListener() {
            @Override
            public void onProgress(long requestLength, long bytesCached, long newBytesCached) {

            }
        };


            videoUri = Uri.parse(arrayList);
            dataSpec = new DataSpec(videoUri);
            CacheDataSourceFactory cacheDataSourceFactory =
                    new CacheDataSourceFactory(cac, dataSourceFactory,
                            new FileDataSourceFactory(),
                            new CacheDataSinkFactory(cac, CacheDataSink.DEFAULT_FRAGMENT_SIZE),
                            0,null,new CacheKeyProvider());



          CacheWriter x=  new CacheWriter(
                    cacheDataSourceFactory.createDataSource(),
                    dataSpec,
                    new byte[BUFFER_SIZE_BYTES], progressListener);


            try {
                x.cache();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }




    // mMediaSession.setPlaybackState(mStateBuilder.build());
    // showNotification(mStateBuilder.build());


    public String vx(String x) {

        return x;
    }

    public String xx() {
        return p;
    }

    public String xix() {
        return lyric;

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

}
