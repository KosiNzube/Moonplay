package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class watchadapter extends RecyclerView.Adapter<watchadapter.MyViewHolder>{
    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 590;

    public watchadapter(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.shortitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.txt_item_title.setText(itemDataList.get(i).getName());
        try {

            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(holder.img_item);


            //   Picasso.get().load(itemDataList.get(i).getImage()).resize(500,500).centerInside().into(myViewHolder.img_item);
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }
      //  setScaleAnimation(holder.img_item);
     //   setFadeAnimation(holder.img_item);
        holder.genre.setText(itemDataList.get(i).getGenre());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,comments.class);
                intent.putExtra("image", itemDataList.get(i).getImage());
                intent.putExtra("genre",itemDataList.get(i).getGenre());
                intent.putExtra("name",itemDataList.get(i).getName());
                intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("dex",itemDataList.get(i).getDescription());
                intent.putExtra("mb",itemDataList.get(i).getMb());
                intent.putExtra("upl",itemDataList.get(i).getUploader());
                intent.putExtra("type",itemDataList.get(i).getType());
                intent.putExtra("res",itemDataList.get(i).getResolution());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {


        /*

        if (itemDataList.size()<7){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 7;
        }

*/
        if (itemDataList.size()<8){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 8;
        }


        return itemDataList.size();
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_title,genre;
        ImageView img_item,image;
        RelativeLayout card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);

        }
    }
}
