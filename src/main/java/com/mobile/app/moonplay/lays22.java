package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.ui.playback.PlaybackActivity;
import com.mobile.app.moonplay.util.ConfigKeys;

public class lays22 extends RecyclerView.Adapter<lays22.MyViewHolder> {

    private SharedPreferences appPreferences;
    private Context context;
    private List<learn> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public lays22(Context context, List<learn> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lays2,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        myViewHolder.maingenre.setText(itemDataList.get(i).getDescription());
      //  myViewHolder.maindes.setText(itemDataList.get(i).getCat());


        myViewHolder.maindes.setText(itemDataList.get(i).getCat());



        if (itemDataList.get(i).getPicture().startsWith("http")) {
            try {
                Glide.with(context)
                        .load(itemDataList.get(i).getPicture())
                        .centerCrop()
                        .into(myViewHolder.img_item);

                // setFadeAnimation(myViewHolder.img_item);
                // myViewHolder.genre.setVisibility(View.INVISIBLE);
                //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();

            }
        }
        setFadeAnimation(myViewHolder.img_item);


        myViewHolder.len.setText(String.valueOf(itemDataList.get(i).getLength())+ " mins");



        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {


                    Intent playbackIntent = new Intent(context, PlaybackActivity.class);
                    playbackIntent.setAction(Intent.ACTION_VIEW);
                    playbackIntent.setData(Uri.parse(itemDataList.get(i).getVideo()));
                    playbackIntent.putExtra(Intent.EXTRA_TITLE, itemDataList.get(i).getDescription());
                    playbackIntent.putExtra("genre",itemDataList.get(i).getCat());
                    playbackIntent.putExtra("video",itemDataList.get(i).getVideo());
                    if (itemDataList.get(i).getPicture().startsWith("http")) {
                        playbackIntent.putExtra("photo", itemDataList.get(i).getPicture());
                    }

                    if (canResumePlayback(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getDescription()))
                    {
                        playbackIntent.putExtra(PlaybackActivity.INTENT_EXTRA_JUMP_TO, getResumePosition());
                    }

                    //dump launch intent


                    //save the playback url as last played
                    updateLastPlayed(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getDescription());



                    context.startActivity(playbackIntent);

                }

            }
        });
        myViewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startDownload(itemDataList.get(i).getVideo(), itemDataList.get(i).getDescription(),itemDataList.get(i).getPicture());
            }
        });


        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }





    @Override
    public int getItemCount() {
            return 3;

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

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView maindes,len,maingenre;
        ImageView img_item,io;
        LinearLayout card;

        ImageView save;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item=itemView.findViewById(R.id.itemImage);

            len=itemView.findViewById(R.id.len);
            save=itemView.findViewById(R.id.button);

            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);


           // watch=itemView.findViewById(R.id.watch);




        }





    }
}
