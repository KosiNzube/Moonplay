package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.ui.playback.PlaybackActivity;
import com.mobile.app.moonplay.util.ConfigKeys;

public class quickadapter extends RecyclerView.Adapter<quickadapter.MyViewHolder>{

    private SharedPreferences appPreferences;
    private Fetch fetch;
    private Context context;
    private List<quick> itemDataList;
    Notification.Builder builder;
    NotificationManagerCompat nmc;
    Request request;
    private ArrayList<id> id = new ArrayList<>();

    BottomSheetDialog bottomSheetDialog;
    String string,video,img,name,cx;
    private long downloadID;

    public quickadapter(Context context, List<quick> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.quick,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        myViewHolder.genre.setText(itemDataList.get(i).getGenre());

        myViewHolder.txt_item_title.setText(itemDataList.get(i).getDescription());

        myViewHolder.cast.setText(itemDataList.get(i).getCast());
        string=itemDataList.get(i).getGenre();
        video=itemDataList.get(i).getLink();
        name=itemDataList.get(i).getName();
        img=itemDataList.get(i).getPhoto();

        myViewHolder.namee.setText(name);
        myViewHolder.time.setText(itemDataList.get(i).getLength());



        try {
            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }




        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemDataList.get(i).getLink().startsWith("http")) {


                    Intent playbackIntent = new Intent(context, PlaybackActivity.class);
                    playbackIntent.setAction(Intent.ACTION_VIEW);
                    playbackIntent.setData(Uri.parse(itemDataList.get(i).getLink()));
                    playbackIntent.putExtra(Intent.EXTRA_TITLE, itemDataList.get(i).getName());
                    playbackIntent.putExtra("genre",itemDataList.get(i).getGenre());
                    playbackIntent.putExtra("video",itemDataList.get(i).getLink());
                    if (itemDataList.get(i).getPhoto().startsWith("http")) {
                        playbackIntent.putExtra("photo", itemDataList.get(i).getPhoto());
                    }

                    if (canResumePlayback(Uri.parse(itemDataList.get(i).getLink()), itemDataList.get(i).getName()))
                    {
                        playbackIntent.putExtra(PlaybackActivity.INTENT_EXTRA_JUMP_TO, getResumePosition());
                    }

                    //dump launch intent


                    //save the playback url as last played
                    updateLastPlayed(Uri.parse(itemDataList.get(i).getLink()), itemDataList.get(i).getName());



                    context.startActivity(playbackIntent);

                }
            }
        });

        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDownload(itemDataList.get(i).getLink(), itemDataList.get(i).getName(),itemDataList.get(i).getImage());
            }
        });

    }
    private void updateLastPlayed(Uri url, String title)
    {
        //set values
        appPreferences.edit().putString(ConfigKeys.KEY_LAST_PLAYED_URL, url.toString())
                .putString(ConfigKeys.KEY_LAST_PLAYED_TITLE, title).apply();
    }

    private long getResumePosition()
    {
        return appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, 0); //TODO: remove a few seconds (10s)
    }

    private boolean canResumePlayback(Uri url, String title)
    {
        //check if there is a playback position to resume stored
        if (appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, -1) <= 0) return false;

        //check that url or title is the same as the last played
        return url.toString().equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_URL, ""))
                || title.equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_TITLE, ""));
    }


    private void startDownload(String url, String name, String photo) {



        Intent intent = new Intent(context, locality.class);
        intent.putExtra("url", url);
        intent.putExtra("namemovie",name);
        intent.putExtra("picture",photo);
        context.startActivity(intent);
    }


    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }




    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title;
        TextView genre,cast,time;
        ImageView img_item;
        RelativeLayout card;

        Button namee;
        CardView button;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.des);
            img_item=itemView.findViewById(R.id.vivix);

            button=itemView.findViewById(R.id.pausecardy);
            card=itemView.findViewById(R.id.mmm);
            genre=itemView.findViewById(R.id.genre);

            namee=itemView.findViewById(R.id.backintimez);
            time=itemView.findViewById(R.id.time);
            cast=itemView.findViewById(R.id.cast);




        }



    }
}
