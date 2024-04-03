package com.mobile.app.moonplay;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class ImageGallery {

    public static ArrayList<String> listOfImages(Context context){
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_foldername;
        ArrayList<String> listofall= new ArrayList<>();
        String absolutepath;
        uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection={MediaStore.MediaColumns.DATA,MediaStore.Video.Media.BUCKET_DISPLAY_NAME};
        String orderby= MediaStore.Video.Media.DATE_TAKEN;
        cursor=context.getContentResolver().query(uri,projection,null,null,orderby+" DESC");
        column_index_data=cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()){
            absolutepath=cursor.getString(column_index_data);
            listofall.add(absolutepath);
        }
        return listofall;
    }
}
