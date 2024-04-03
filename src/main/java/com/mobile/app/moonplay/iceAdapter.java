package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.util.ConfigKeys;


public class iceAdapter extends RecyclerView.Adapter<iceAdapter.IceHolder> {

    private Context context;
    private List<icemodel> itemDataList;
    private SharedPreferences appPreferences;



    public iceAdapter(Context context, List<icemodel> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public IceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ice,parent,false);
        return new IceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IceHolder holder, @SuppressLint("RecyclerView") final int i) {
        appPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);

        holder.thumbnail.setImageBitmap(itemDataList.get(i).getThumbnail());



        holder.name.setText(itemDataList.get(i).getResolution().replace(".mp4",""));


        String totDur = String.format("%02d.%02d",
                TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime()),
                TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getTime()) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime())) // The change is in this line
        );




        String time=String.format("%02d.%02d.%02d",
                TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime()),
                TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getTime()) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime())), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(itemDataList.get(i).getTime()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getTime())));
        int kb=itemDataList.get(i).getTime()/1024;
        int mb=kb/1024;
        holder.length.setText(String.valueOf(mb)+" MB");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String kiki=preferences.getString("name","");
        if (itemDataList.get(i).getResolution().equals(kiki)){
            holder.button.setVisibility(View.VISIBLE);
        }
        holder.ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, streamplayer4Allx.class);

                intent.putExtra("photo", "");
                if (itemDataList.get(i).getSubtitle()!=null){
                    intent.putExtra("subt",itemDataList.get(i).getSubtitle());
                }else {
                    intent.putExtra("subt", "");
                }
                intent.putExtra("name", itemDataList.get(i).getResolution());
                intent.putExtra("video", itemDataList.get(i).getVideo());
                intent.putExtra("genre", "");
                //intent.putExtra("pos",itemDataList.get(i).getMio());
                context.startActivity(intent);



                /*
                Intent playbackIntent = new Intent(context, PlaybackActivity2.class);
                playbackIntent.setAction(Intent.ACTION_VIEW);
                playbackIntent.setData(Uri.parse(itemDataList.get(i).getVideo()));
                playbackIntent.putExtra(Intent.EXTRA_TITLE, itemDataList.get(i).getResolution());


                if (canResumePlayback(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getResolution()))
                {
                    playbackIntent.putExtra(PlaybackActivity.INTENT_EXTRA_JUMP_TO, getResumePosition());
                }

                //dump launch intent


                //save the playback url as last played
                updateLastPlayed(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getResolution());



                context.startActivity(playbackIntent);
*/
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File x= new File(itemDataList.get(i).getVideo());

                File y=new File(itemDataList.get(i).getSubtitle());

                x.delete();

                y.delete();
                removeAt(holder.getAdapterPosition());


            }
        });






    }
    public void removeAt(int p){
        itemDataList.remove(p);
        notifyItemRemoved(p);
        notifyItemRangeChanged(p,itemDataList.size());
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


    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    public class IceHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        RelativeLayout ice;
        TextView name,length;

        ImageView button;

        public IceHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.button);
            thumbnail=itemView.findViewById(R.id.itemImage);
            ice=itemView.findViewById(R.id.card);
            name=itemView.findViewById(R.id.tvTitle);
            length=itemView.findViewById(R.id.type);

        }
    }
}
