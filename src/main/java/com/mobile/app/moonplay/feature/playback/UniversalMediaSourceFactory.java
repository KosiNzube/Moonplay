package com.mobile.app.moonplay.feature.playback;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

import com.mobile.app.moonplay.util.Logging;

public class UniversalMediaSourceFactory
{
    /**
     * The maximum cache size of the player cache
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final long MAX_CACHE_SIZE = 100 * 1024 * 1024;//bytes

    /**
     * Data source factory of this app
     */
    private final DataSource.Factory dataSourceFactory;

    /**
     * The Cache directory
     */
    private File cacheDir;

    /**
     * The cache used to download video data
     */
    private Cache downloadCache;

    /**
     * Database provider for cached files
     */
    private ExoDatabaseProvider databaseProvider;

    /**
     * Initialize the Universal MediaSource Factory
     *
     * @param context   the context to create the factory in
     * @param userAgent the useragent to use when streaming media
     */
    public UniversalMediaSourceFactory(Context context, String userAgent)
    {
        //initialize cached files database provider
        databaseProvider = new ExoDatabaseProvider(context);

        //initialize download cache
        cacheDir = new File(context.getCacheDir(), "media");
        downloadCache = new SimpleCache(cacheDir, new LeastRecentlyUsedCacheEvictor(MAX_CACHE_SIZE), databaseProvider);

        //create http data source factory that allows http -> https redirects
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent, null,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS, DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);

        //initialize data source factory
        DefaultDataSourceFactory ddsf = new DefaultDataSourceFactory(context, httpDataSourceFactory);
        dataSourceFactory = new CacheDataSourceFactory(downloadCache, ddsf, CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR | CacheDataSource.FLAG_BLOCK_ON_CACHE);
    }

    /**
     * Create a MediaSource from the given uri
     *
     * @param uri the uri to create the media source from
     * @return the media source created, or null if the media type is NOT supported
     */
    public MediaSource createMediaSource(Uri uri)
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

    /**
     * Release resources allocated by the UniversalMediaSourceFactory
     */
    public void release()
    {
        //log release + cached bytes
        Logging.logD("Releasing UniversalMediaSourceFactory and clearing %d bytes of cache...", downloadCache.getCacheSpace());

        //release cache
        if (downloadCache != null)
        {
            downloadCache.release();
            Logging.logD("Cache released!");
        }

        //--clear cache &-- close database connection
        if (databaseProvider != null)
        {
            //SimpleCache.delete(cacheDir, databaseProvider);
            databaseProvider.close();
            Logging.logD("database connection closed!");
        }
    }
}
