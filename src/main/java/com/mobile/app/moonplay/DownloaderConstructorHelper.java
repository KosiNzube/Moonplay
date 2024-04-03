package com.mobile.app.moonplay;


import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DummyDataSource;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.PriorityDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;

import androidx.annotation.Nullable;

/** A helper class that holds necessary parameters for {@link Downloader} construction. */
public final class DownloaderConstructorHelper {

    private final Cache cache;
    private final Factory upstreamDataSourceFactory;
    private final Factory cacheReadDataSourceFactory;
    private final DataSink.Factory cacheWriteDataSinkFactory;
    private final PriorityTaskManager priorityTaskManager;

    /**
     * @param cache Cache instance to be used to store downloaded data.
     * @param upstreamDataSourceFactory A {@link Factory} for downloading data.
     */
    public DownloaderConstructorHelper(Cache cache, Factory upstreamDataSourceFactory) {
        this(cache, upstreamDataSourceFactory, null, null, null);
    }

    /**
     * @param cache Cache instance to be used to store downloaded data.
     * @param upstreamDataSourceFactory A {@link Factory} for downloading data.
     * @param cacheReadDataSourceFactory A {@link Factory} for reading data from the cache. If null
     *     then standard {@link FileDataSource} instances will be used.
     * @param cacheWriteDataSinkFactory A {@link DataSink.Factory} for writing data to the cache. If
     *     null then standard {@link CacheDataSink} instances will be used.
     * @param priorityTaskManager A {@link PriorityTaskManager} to use when downloading. If non-null,
     *     downloaders will register as tasks with priority {@link C#PRIORITY_DOWNLOAD} whilst
     *     downloading.
     */
    public DownloaderConstructorHelper(
            Cache cache,
            Factory upstreamDataSourceFactory,
            @Nullable Factory cacheReadDataSourceFactory,
            @Nullable DataSink.Factory cacheWriteDataSinkFactory,
            @Nullable PriorityTaskManager priorityTaskManager) {
        Assertions.checkNotNull(upstreamDataSourceFactory);
        this.cache = cache;
        this.upstreamDataSourceFactory = upstreamDataSourceFactory;
        this.cacheReadDataSourceFactory = cacheReadDataSourceFactory;
        this.cacheWriteDataSinkFactory = cacheWriteDataSinkFactory;
        this.priorityTaskManager = priorityTaskManager;
    }

    /** Returns the {@link Cache} instance. */
    public Cache getCache() {
        return cache;
    }

    /** Returns a {@link PriorityTaskManager} instance. */
    public PriorityTaskManager getPriorityTaskManager() {
        // Return a dummy PriorityTaskManager if none is provided. Create a new PriorityTaskManager
        // each time so clients don't affect each other over the dummy PriorityTaskManager instance.
        return priorityTaskManager != null ? priorityTaskManager : new PriorityTaskManager();
    }

    /**
     * Returns a new {@link CacheDataSource} instance. If {@code offline} is true, it can only read
     * data from the cache.
     */
    public CacheDataSource buildCacheDataSource(boolean offline) {
        DataSource cacheReadDataSource = cacheReadDataSourceFactory != null
                ? cacheReadDataSourceFactory.createDataSource() : new FileDataSource();
        if (offline) {
            return new CacheDataSource(cache, DummyDataSource.INSTANCE,
                    cacheReadDataSource, null, CacheDataSource.FLAG_BLOCK_ON_CACHE, null);
        } else {
            DataSink cacheWriteDataSink = cacheWriteDataSinkFactory != null
                    ? cacheWriteDataSinkFactory.createDataSink()
                    : new CacheDataSink(cache, CacheDataSink.DEFAULT_FRAGMENT_SIZE);
            DataSource upstream = upstreamDataSourceFactory.createDataSource();
            upstream = priorityTaskManager == null ? upstream
                    : new PriorityDataSource(upstream, priorityTaskManager, C.PRIORITY_DOWNLOAD);
            return new CacheDataSource(cache, upstream, cacheReadDataSource,
                    cacheWriteDataSink, CacheDataSource.FLAG_BLOCK_ON_CACHE, null);
        }
    }

}