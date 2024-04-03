package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class SeriesAd extends RecyclerView.Adapter<SeriesAd.MyViewHolder> {


    private Context context;
    private List<SeriesObject> itemDataList;

    private final static int FADE_DURATION = 690;
    public SeriesAd(Context context, List<SeriesObject> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.serieslayout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.txt_item_title.setText(itemDataList.get(i).getName());
      //  holder.genre.setText(itemDataList.get(i).getGenre());
        holder.genre.setText(itemDataList.get(i).getGenre());

        Glide.with(context)
                .load(itemDataList.get(i).getImage())
                .centerCrop()
                .into(holder.img_item);
        holder.genre.setSelected(true);

        holder.txt_item_title.setSelected(true);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // showbs(itemDataList.get(i).getImage(),itemDataList.get(i).getGenre(),itemDataList.get(i).getDescription(),itemDataList.get(i).getName(),itemDataList.get(i).getResolution());


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




        if (itemDataList.size()<12){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 12;
        }


        return itemDataList.size();
    }

    private void showbs(String image, String genre, String description, String name, String resolution) {
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.didi);


        ImageView itemview=bottomSheetDialog.findViewById(R.id.itemImage);
        TextView textView=bottomSheetDialog.findViewById(R.id.tvTitle);
        TextView genrex=bottomSheetDialog.findViewById(R.id.type);
        TextView des=bottomSheetDialog.findViewById(R.id.typee);
        TextView cast=bottomSheetDialog.findViewById(R.id.cast);
        Glide.with(context)
                .load(image)
                .centerCrop()
                .into(itemview);

        textView.setText(name);
        genrex.setText(genre);
        des.setText(description);
        cast.setText("Cast: "+resolution);
        bottomSheetDialog.show();

    }


    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre;
        ImageView img_item;
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
