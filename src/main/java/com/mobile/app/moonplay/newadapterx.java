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
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bosphere.fadingedgelayout.FadingEdgeLayout;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class newadapterx extends RecyclerView.Adapter<newadapterx.MyViewHolder>{
    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 700;
    public newadapterx(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cinema2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {

        try {
            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .placeholder(R.color.kokocolor)
                    .into(holder.img_item);

        }catch (OutOfMemoryError e){
            e.printStackTrace();
        }
        setFadeAnimation(holder.img_item);
       // setFadeAnimation(holder.img_item);
        holder.mFadingEdgeLayout.setFadeEdges(false,false,true,false);
        holder.mFadingEdgeLayout.setFadeSizes(4, 200, 450, 200);

        holder.genre.setText(itemDataList.get(i).getName());

        holder.txt_item_title.setText(itemDataList.get(i).getDescription());
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

        return 1;
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
        CardView card;
        FadingEdgeLayout mFadingEdgeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);
            mFadingEdgeLayout = itemView.findViewById(R.id.fade);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);

        }
    }
}
