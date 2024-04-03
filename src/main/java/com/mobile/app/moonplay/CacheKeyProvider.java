package com.mobile.app.moonplay;

import android.net.Uri;

import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.CacheKeyFactory;

public class CacheKeyProvider implements CacheKeyFactory
{

    @Override
    public String buildCacheKey(DataSpec dataSpec) {
        if (dataSpec.key!=null) return dataSpec.key;
        else return generate_key(dataSpec.uri);
    }

    private String generate_key(Uri uri){
        String string = uri.toString();
        String[] parts = string.split("\\?");
        String part1 = parts[0];
        String part2 = parts[1];
        return part1;
    }
}