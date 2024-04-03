
package com.mobile.app.moonplay;


import android.app.DownloadManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.R;


public class PlayerViewHolder extends RecyclerView.ViewHolder {

    /**
     * below view have public modifier because
     * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
     */
    public FrameLayout mediaContainer;
    public Button button,button1,mb;
    public ProgressBar progressBar;
    private View parent;
     TextView textView,two,time;
    public RelativeLayout relativeLayout;
    ImageView sav2;
    CardView save;
    ImageView restart, full, mute;
     ImageView vivix;
    RelativeLayout zzz;
     ImageView love, sjare, download;
    private  static  int splashy=400;
    public PlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        mediaContainer = itemView.findViewById(R.id.mediaContainer);
        progressBar = itemView.findViewById(R.id.progressBar);
    //    mb=itemView.findViewById(R.id.mb);
        restart=itemView.findViewById(R.id.c);
        full=itemView.findViewById(R.id.b);
        mute=itemView.findViewById(R.id.a);
        zzz=itemView.findViewById(R.id.zzz);
        two=itemView.findViewById(R.id.des);
     //   sav2=itemView.findViewById(R.id.download);
       // save=itemView.findViewById(R.id.pausecardy);
        textView=itemView.findViewById(R.id.genre);
//        relativeLayout=itemView.findViewById(R.id.cc);
        love=itemView.findViewById(R.id.imageButton3);
        sjare=itemView.findViewById(R.id.share);
        time=itemView.findViewById(R.id.time);
        //vivix=itemView.findViewById(R.id.vivix);
        download=itemView.findViewById(R.id.download);
    }

    void onBind(instantv mediaObject, RequestManager requestManager) {
        parent.setTag(this);

//        mb.setText(mediaObject.getName());
        textView.setText(mediaObject.getDescription());
        two.setText(mediaObject.getCat());


        love.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                love.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        love.setVisibility(View.VISIBLE);
                    }
                },splashy);


                love.setColorFilter(Color.RED);
                love.setEnabled(false);
            }

        });

//        vivix.setVisibility(View.GONE);
/*
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saved(mediaObject.getVideo(),mediaObject.getName(),mediaObject.getDescription());
            }
        });
        sav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saved(mediaObject.getVideo(),mediaObject.getName(),mediaObject.getDescription());
            }
        });
*/
       // mediaContainer.setBackgroundColor(Color.CYAN);



    }

    private void saved(String video, String name, String mcaption) {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(video));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE).setAllowedOverRoaming(false).setTitle(name).setDescription(mcaption).setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,name+".mp4");
    }
}