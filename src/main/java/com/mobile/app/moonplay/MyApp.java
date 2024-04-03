package com.mobile.app.moonplay;

import android.app.Application;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class MyApp extends Application {
    public static SimpleCache simpleCache;
    File cf=null;
    int exoPlayerCacheSize =90 * 1024 * 1024;
    LeastRecentlyUsedCacheEvictor leastRecentlyUsedCacheEvictor;
    ExoDatabaseProvider exoDatabaseProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        leastRecentlyUsedCacheEvictor = new LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize);
        exoDatabaseProvider = new ExoDatabaseProvider(this);
        cf=new File(this.getExternalCacheDir(),"instantmedia");
        simpleCache = new SimpleCache(cf, leastRecentlyUsedCacheEvictor, exoDatabaseProvider);
    }
}



