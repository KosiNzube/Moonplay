package com.mobile.app.moonplay;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.Request;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

public class genre extends RecyclerView.Adapter<genre.MyViewHolder>{


    private Fetch fetch;
    private Context context;
    private List<genrex> itemDataList;
    Notification.Builder builder;
    NotificationManagerCompat nmc;
    Request request;

    private final static int FADE_DURATION = 690;
    BottomSheetDialog bottomSheetDialog;
    String string,video,img,name,cx;
    private long downloadID;

    public genre(Context context, List<genrex> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.scene,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getGenre());

        if (itemDataList.get(i).getImage().startsWith("http")) {
            try {
                Glide.with(context)
                        .load(itemDataList.get(i).getImage())
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

        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, genact.class);
                intent.putExtra("search",itemDataList.get(i).getGenre());
                context.startActivity(intent);
            }
        });
        /*
        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {
                    startDownload(itemDataList.get(i).getVideo(),itemDataList.get(i).getName());





                }


                return true;
            }
        });
        */


    }



    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }




    @Override
    public int getItemCount() {
        return itemDataList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title;
        ImageView img_item,button;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.genre);
            img_item=itemView.findViewById(R.id.itemImage);
            card=itemView.findViewById(R.id.card);






        }



    }
}
