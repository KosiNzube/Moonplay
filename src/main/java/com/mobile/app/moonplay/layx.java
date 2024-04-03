package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class layx extends RecyclerView.Adapter<layx.MyViewHolder> {


    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public layx(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.layx,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        myViewHolder.genre.setText(itemDataList.get(i).getGenre());
        myViewHolder.maingenre.setText(itemDataList.get(i).getGenre());

        if (itemDataList.get(i).getName().length()>45){
            myViewHolder.mb.setText(itemDataList.get(i).getName().substring(0, 45) + "...");
        }else {
            myViewHolder.mb.setText(itemDataList.get(i).getName());
        }
        myViewHolder.maindes.setText(itemDataList.get(i).getDescription());

        if (itemDataList.get(i).getMb().startsWith("http")) {
            try {
                Glide.with(context)
                        .load(itemDataList.get(i).getMb())
                        .centerCrop()
                        .into(myViewHolder.img_item);

                // setFadeAnimation(myViewHolder.img_item);
                // myViewHolder.genre.setVisibility(View.INVISIBLE);
                //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();

            }
        }else {
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


        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

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


        TextView txt_item_title,genre,maindes,maingenre;
        ImageView img_item,io;
        RelativeLayout card;

        ImageView watch;

        Button mb;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            mb=itemView.findViewById(R.id.mb);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);

           // watch=itemView.findViewById(R.id.watch);




        }





    }
}
