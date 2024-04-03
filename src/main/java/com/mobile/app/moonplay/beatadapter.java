package com.mobile.app.moonplay;

import android.content.Context;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;

import com.tonyodev.fetch2.Fetch;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class beatadapter extends RecyclerView.Adapter<beatadapter.MyViewHolder>{


    private Fetch fetch;
    private Context context;
    private List<beat> itemDataList;
    private TextToSpeech mtts;
    SimpleExoPlayer simpleExoPlayer;
    String string,link,img,name,cx;
    boolean available;
    private long downloadID;

    public beatadapter(Context context, List<beat> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.xcobar_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        string=itemDataList.get(i).getLength();
        name=itemDataList.get(i).getName();
        cx=itemDataList.get(i).getProducer();

        img=itemDataList.get(i).getImage();
        available=itemDataList.get(i).isOwn();
        link=itemDataList.get(i).getLink();
   //     name=itemDataList.get(i).getName();
        myViewHolder.txt_item_title.setText(name);
        myViewHolder.pro.setText(cx);





        myViewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



            myViewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            /*
            if (img!=null){
                myViewHolder.v.setVisibility(View.INVISIBLE);
                myViewHolder.vx.setVisibility(View.VISIBLE);
            }else {
                myViewHolder.v.setVisibility(View.VISIBLE);
                myViewHolder.vx.setVisibility(View.INVISIBLE);
            }
*/

            if (available==true){
                myViewHolder.list.setText("Sold out");
            }else {
                myViewHolder.list.setText("Buy");
            }


        /*
        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {
                    startDownload(itemDataList.get(i).getVideo(),itemDataList.get(i).getName());





                }


                return true;
            }
        });
        */


    }

    private void speaak(String h) {
        mtts.setLanguage(Locale.ENGLISH);

        mtts.speak(h, TextToSpeech.QUEUE_FLUSH,null);
    }






    @Override
    public int getItemCount() {

        if (itemDataList.size()>4){

            return 4;
        }
        return itemDataList.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,pro,list;
      //  TextView genre,musicgen;
        CardView buy,vx;
        ImageView more;
        LinearLayout card;
        RelativeLayout v;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.music_file_name);
            pro=itemView.findViewById(R.id.pro);
            list=itemView.findViewById(R.id.list);
            buy=itemView.findViewById(R.id.pausecardy);
            v=itemView.findViewById(R.id.v);
           more=itemView.findViewById(R.id.more);
           vx=itemView.findViewById(R.id.vx);
           // musicgen=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.audio_item);
        //    genre=itemView.findViewById(R.id.type);





        }



    }

}
