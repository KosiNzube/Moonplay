package com.mobile.app.moonplay;

import android.content.Context;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class VideoCache {
    private static SimpleCache sDownloadCache;

    public static SimpleCache getInstance(Context context) {
      LeastRecentlyUsedCacheEvictor  ce = new LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024);

        if (sDownloadCache == null) sDownloadCache = new SimpleCache(new File(context.getCacheDir(), "instantmedia"), ce, new ExoDatabaseProvider(context));
        return sDownloadCache;
    }
}