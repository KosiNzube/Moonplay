package com.mobile.app.moonplay;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class videoing extends RecyclerView.Adapter<videoing.Mihold> {
    private Context context;
    private List<video> mData;
    boolean viewer = true;
    boolean isPlaying;

    SeekBar h;
    VideoView videoView;
    public videoing(Context context, List<video> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Mihold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.shortvideo,parent,false);
        return new Mihold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Mihold holder, int i) {
        holder.videoView.setVideoURI(Uri.parse(mData.get(i).getVideo()));


        holder.videoView.start();
        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.videoView.start();
            }
        });

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.videoView.start();
            }
        });





        isPlaying = false;



    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class Mihold extends RecyclerView.ViewHolder {
        VideoView videoView;

        public Mihold(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.videoviewio);

        }
    }
}
