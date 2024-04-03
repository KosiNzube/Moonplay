package com.mobile.app.moonplay;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 4/8/2016.
 */
public class songQuery {
    private Context context;
    private int count = 0;
    private Cursor cursor;
    List<MusicFiles> videoItems;
    public songQuery(Context context){
        this.context=context;
    }
    public List<MusicFiles> getAllAudio() {
        String selection = null;

        String[] projections={
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST

        };
        cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projections,
                selection,
                null,
                null);

        videoItems = new ArrayList<MusicFiles>();
        MusicFiles videoItem;
        while (cursor.moveToNext()) {
            videoItem = new MusicFiles();
            videoItem.setAlbum(cursor.getString(0));
            videoItem.setTitle(cursor.getString(1));
            videoItem.setDuration(cursor.getString(2));
            videoItem.setPath(cursor.getString(3));
            videoItem.setArtist(cursor.getString(4));
            videoItems.add(0,videoItem);
        }
    return videoItems;
    }
    public int getVideoCount(){
        int count=0;
        count=(getAllAudio()).size();
        return count;

    }
}
