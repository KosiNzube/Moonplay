package com.mobile.app.moonplay;

import com.google.android.exoplayer2.C;

public class DownloadProgress {

    /** The number of bytes that have been downloaded. */
    public volatile long bytesDownloaded;

    /** The percentage that has been downloaded, or {@link C#PERCENTAGE_UNSET} if unknown. */
    public volatile float percentDownloaded;
}

