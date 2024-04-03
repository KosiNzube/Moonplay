package com.mobile.app.moonplay;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.util.MimeTypes;

import java.util.Collections;

public class HLSAsset {
    private String id;
    private String hlsUrl;

    public HLSAsset(){

    }

    public HLSAsset(String id, String hlsUrl) {
        this.id = id;
        this.hlsUrl = hlsUrl;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    com.google.android.exoplayer2.MediaItem getMediaItem(){
        return new MediaItem.Builder().setUri(hlsUrl).setStreamKeys(Collections.singletonList(new StreamKey(HlsMasterPlaylist.GROUP_INDEX_VARIANT,0))).setMimeType(MimeTypes.APPLICATION_M3U8).build();

    }
}
