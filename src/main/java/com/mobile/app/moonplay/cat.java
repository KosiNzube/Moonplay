package com.mobile.app.moonplay;

import android.content.Context;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class cat extends RecyclerView.Adapter<cat.MyViewHolder> {


    private Context context;
    private List<catitem> itemDataList;
    private final static int FADE_DURATION = 590;

    public cat(Context context, List<catitem> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.cat,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getTitle());

        try{
            Glide.with(context).asGif()
                    .load(itemDataList.get(i).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.DATA).fitCenter().into(myViewHolder.img_item);
          //  Picasso.get().load(itemDataList.get(i).getImage()).fit().centerCrop().into(myViewHolder.img_item);
             //setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }

       // setScaleAnimation(myViewHolder.img_item);
        setFadeAnimation(myViewHolder.img_item);


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

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



    @Override
    public int getItemCount() {
        if (itemDataList.size()<8){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 8;
        }


        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title;
        ImageView img_item;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);






        }





    }
}
