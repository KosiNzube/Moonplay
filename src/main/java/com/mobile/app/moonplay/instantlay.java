package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class instantlay extends RecyclerView.Adapter<instantlay.MyViewHolder> {


    private Context context;
    private List<mainpost> itemDataList;
    private final static int FADE_DURATION = 690;

    public instantlay(Context context, List<mainpost> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lay2,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


//        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());

        myViewHolder.maingenre.setText(itemDataList.get(i).getMcaption());

        if (itemDataList.get(i).getName().length()>18){
            myViewHolder.mb.setText(itemDataList.get(i).getName().substring(0, 18) + "...");
        }else {
            myViewHolder.mb.setText(itemDataList.get(i).getName());
        }
        myViewHolder.maindes.setText("4 Moments");
        try{
            Glide.with(context)
                    .load(itemDataList.get(i).getPicture1())
                    .centerCrop()
                    .into(myViewHolder.img_item);

            // setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }
        setFadeAnimation(myViewHolder.img_item);



        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });
        myViewHolder.hihi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });

        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });
        myViewHolder.vvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });
        /*
        myViewHolder.zzzii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });


        myViewHolder.zzzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
            }
        });
        */
        myViewHolder.zzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, instantplay.class);



                intent.putExtra("video",itemDataList.get(i).getId());
                intent.putExtra("hash",itemDataList.get(i).getName());
                intent.putExtra("id",itemDataList.get(i).getId());
                context.startActivity(intent);
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
        if (itemDataList.size()<7){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 7;
        }


        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,maindes,maingenre;
        ImageView img_item,io;
        CardView zzz;
        LinearLayout kiki;
        HorizontalScrollView vvv;
        RelativeLayout card,hihi;


        Button mb;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            zzz=itemView.findViewById(R.id.zzz);

            vvv=itemView.findViewById(R.id.vvv);
            hihi=itemView.findViewById(R.id.hihi);
            mb=itemView.findViewById(R.id.mb);
            kiki=itemView.findViewById(R.id.kiki);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);
           // genre=itemView.findViewById(R.id.type);





        }





    }
}
