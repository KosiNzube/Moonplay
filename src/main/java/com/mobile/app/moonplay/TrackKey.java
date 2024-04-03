package com.mobile.app.moonplay;

public class TrackKey {
    public final int periodIndex;
    /** The group index. */
    public final int groupIndex;
    /** The track index. */
    public final int trackIndex;

    /**
     * @param periodIndex The period index.
     * @param groupIndex The group index.
     * @param trackIndex The track index.
     */
    public TrackKey(int periodIndex, int groupIndex, int trackIndex) {
        this.periodIndex = periodIndex;
        this.groupIndex = groupIndex;
        this.trackIndex = trackIndex;
    }
}
