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

import com.bosphere.fadingedgelayout.FadingEdgeLayout;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mainfirst extends RecyclerView.Adapter<mainfirst.MyViewHolder> {


    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 590;

    public mainfirst(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lofix2,viewGroup,false);
        return new MyViewHolder(view);
    }

    public void xx(List<Movie> datas){
        itemDataList.clear();
        itemDataList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        myViewHolder.genre.setText(itemDataList.get(i).getGenre());

        myViewHolder.mFadingEdgeLayout.setFadeEdges(true,false,true,false);
        myViewHolder.mFadingEdgeLayout.setFadeSizes(50, 200, 200, 200);

            if (itemDataList.get(i).getMb()!=null) {
                if (itemDataList.get(i).getMb().startsWith("http")) {
                    Glide.with(context)
                            .load(itemDataList.get(i).getMb())
                            .centerCrop()
                            .into(myViewHolder.img_item);
                }
            }else {
                Glide.with(context)
                        .load(itemDataList.get(i).getImage())
                        .centerCrop()
                        .into(myViewHolder.img_item);
            }


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
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
        if (itemDataList.size()<1){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 1;
        }


        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        FadingEdgeLayout mFadingEdgeLayout ;

        TextView txt_item_title,genre;
        ImageView img_item,io;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.moviename);
            img_item=itemView.findViewById(R.id.thumbnail);

            mFadingEdgeLayout=itemView.findViewById(R.id.fade);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.genre);





        }





    }
}
