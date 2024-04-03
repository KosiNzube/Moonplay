package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
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
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class keepw extends RecyclerView.Adapter<keepw.MyViewHolder> {


    private Context context;
    private List<watchlist> itemDataList;
    private final static int FADE_DURATION = 590;

    public keepw(Context context, List<watchlist> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_itemxxkeep,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {


      //  myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        String totDur = String.format("%02d.%02d.%02d",
                TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getPos()),
                TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getPos()) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getPos())), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(itemDataList.get(i).getPos()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getPos())));
        myViewHolder.genre.setText(totDur);


        if (itemDataList.get(i).getName().length() > 40) {
            myViewHolder.txt_item_title.setText(itemDataList.get(i).getName().substring(0, 40) + "...");
        } else {
            myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        }

      //  myViewHolder.genre.setText(itemDataList.get(i).getGenre());
        try{

            /*
            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);

*/

            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);

            // setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }

       // setScaleAnimation(myViewHolder.img_item);
       // setFadeAnimation(myViewHolder.img_item);


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,popo.class);
                intent.putExtra("photo", itemDataList.get(i).getImage());
                intent.putExtra("name",itemDataList.get(i).getName());
                intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("genre",itemDataList.get(i).getGenre());
                intent.putExtra("pos",itemDataList.get(i).getPos());
                intent.putExtra("subt",itemDataList.get(i).getSub());
                context.startActivity(intent);
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
        if (itemDataList.size()<12){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 12;
        }


        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre;
        ImageView img_item,io;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.des);





        }





    }
}
