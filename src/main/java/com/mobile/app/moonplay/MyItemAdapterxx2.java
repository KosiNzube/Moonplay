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

public class MyItemAdapterxx2 extends RecyclerView.Adapter<MyItemAdapterxx2.MyViewHolder> {


    private Context context;
    private List<recom> itemDataList;
    private final static int FADE_DURATION = 590;

    public MyItemAdapterxx2(Context context, List<recom> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_itemxx2,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
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
                Intent intent = new Intent(context, Main13Activityx.class);
                intent.putExtra("moviename", itemDataList.get(i).getMoviename());
                intent.putExtra("name",itemDataList.get(i).getName());
                intent.putExtra("image",itemDataList.get(i).getImage());
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
            genre=itemView.findViewById(R.id.type);





        }





    }
}
