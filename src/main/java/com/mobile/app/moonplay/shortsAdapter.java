package com.mobile.app.moonplay;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class shortsAdapter extends RecyclerView.Adapter<shortsAdapter.Mihold> {
    private Context context;
    private List<shortsvid> mData;
    boolean viewer = true;
    boolean isPlaying;

    SeekBar h;
    VideoView videoView;
    public shortsAdapter(Context context, List<shortsvid> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Mihold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.cardview_item_book,parent,false);
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
        holder.pauseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isPlaying) {
                    isPlaying = false;

                    holder.videoView.pause();
                    holder.pauseP.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                } else {
                    isPlaying = true;
                    holder.videoView.start();

                    holder.pauseP.setImageResource(R.drawable.ic_pause_black_24dp);

                }
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewer) {
                    viewer=false;
                    holder.relativeLayout.setVisibility(View.VISIBLE);
                    holder.slide.setVisibility(View.VISIBLE);

                    holder.seekBar.setVisibility(View.VISIBLE);
                }else {
                    holder.relativeLayout.setVisibility(View.INVISIBLE);
                    holder.seekBar.setVisibility(View.INVISIBLE);
                    holder.slide.setVisibility(View.INVISIBLE);

                    viewer=true;
                }

            }
        });

        holder.str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.seekTo(holder.videoView.getCurrentPosition()+5000);
            }
        });
        holder.rot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.seekTo(holder.videoView.getCurrentPosition()-5000);
            }
        });

        holder.slide.setText(String.valueOf(mData.get(i).getLast()+"/"+mData.size()));


    }


    @Override
    public int getItemCount() {
        return 1;
    }



    public class Mihold extends RecyclerView.ViewHolder {
        VideoView videoView;
        RelativeLayout relativeLayout;
        SeekBar seekBar;
        ImageView back;
        TextView slide;
        ImageView rot,str;
        View view;
        android.os.Handler handler=new android.os.Handler();
        ImageView pauseP;
        public Mihold(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.videoView);
            view=itemView.findViewById(R.id.view);

            pauseP=itemView.findViewById(R.id.pauseP);
            slide=itemView.findViewById(R.id.stopV);
            seekBar=itemView.findViewById(R.id.seekBar);


            str=itemView.findViewById(R.id.straight);
            rot=itemView.findViewById(R.id.rotation);

            relativeLayout=itemView.findViewById(R.id.control);


        }
    }
}
