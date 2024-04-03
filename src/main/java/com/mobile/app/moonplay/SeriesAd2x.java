package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesAd2x extends RecyclerView.Adapter<SeriesAd2x.MyViewHolder> {


    private Context context;
    private List<SeriesObject> itemDataList;

    private final static int FADE_DURATION = 690;
    public SeriesAd2x(Context context, List<SeriesObject> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.lofix,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
//        holder.txt_item_title.setText(itemDataList.get(i).getName());
      //  holder.genre.setText(itemDataList.get(i).getGenre());

        Glide.with(context)
                .load(itemDataList.get(i).getMb())
                .centerCrop()
                .into(holder.img_item);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, comments.class);
                intent.putExtra("image", itemDataList.get(i).getImage());
                intent.putExtra("genre",itemDataList.get(i).getGenre());
                intent.putExtra("name",itemDataList.get(i).getName());
                intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("dex",itemDataList.get(i).getDescription());
                intent.putExtra("mb",itemDataList.get(i).getMb());
                intent.putExtra("type",itemDataList.get(i).getType());
                intent.putExtra("res",itemDataList.get(i).getResolution());
                intent.putExtra("upl",itemDataList.get(i).getUploader());
                context.startActivity(intent);
            }
        });
        setFadeAnimation(holder.img_item);
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView txt_item_title,genre,des,watch;
        ImageView img_item,io;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item=itemView.findViewById(R.id.thumbnail);


            card=itemView.findViewById(R.id.card);





        }



    }
}
