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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class layse12xx extends RecyclerView.Adapter<layse12xx.MyViewHolder> {


    private Context context;
    private List<doc> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public layse12xx(Context context, List<doc> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lays,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {




        myViewHolder.maindes.setText(itemDataList.get(i).getCat());

        myViewHolder.maingenre.setText(itemDataList.get(i).getDescription());



            myViewHolder.list.setText(itemDataList.get(i).getLikes()+" clips");


            if (itemDataList.get(i).getLikes()==1){
                myViewHolder.list.setText(" Single ");
            }
        //  myViewHolder.maindes.setText(itemDataList.get(i).getDescription());

        if (itemDataList.get(i).getPicture().startsWith("http")) {
            try {



                Glide.with(context)
                        .load(itemDataList.get(i).getPicture())
                        .centerCrop()
                        .into(myViewHolder.img_item);

                // setFadeAnimation(myViewHolder.img_item);
                // myViewHolder.genre.setVisibility(View.INVISIBLE);
                //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();

            }
        }
    //    setFadeAnimation(myViewHolder.img_item);



        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, instantplayxxx.class);
                intent.putExtra("image", itemDataList.get(i).getPicture());
                intent.putExtra("genre",itemDataList.get(i).getCat());
                intent.putExtra("video",itemDataList.get(i).getName());
          //      intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("dex",itemDataList.get(i).getDescription());
                intent.putExtra("name",itemDataList.get(i).getId());
                intent.putExtra("plays",itemDataList.get(i).getLikes());
                intent.putExtra("upl",itemDataList.get(i).getPoster());
                intent.putExtra("pins",itemDataList.get(i).getPins());
            //    intent.putExtra("mb",itemDataList.get(i).getMb());
              //  intent.putExtra("type",itemDataList.get(i).getType());
               // intent.putExtra("res",itemDataList.get(i).getResolution());
                //intent.putExtra("upl",itemDataList.get(i).getUploader());
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

        return itemDataList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView maindes,maingenre,list;
        ImageView img_item,io;
        RelativeLayout card;

        ImageView watch;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item=itemView.findViewById(R.id.itemImage);


            list=itemView.findViewById(R.id.listx);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);


           // watch=itemView.findViewById(R.id.watch);




        }





    }
}
