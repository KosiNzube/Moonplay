package com.mobile.app.moonplay;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.tonyodev.fetch2.Fetch;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class animeluno extends RecyclerView.Adapter<animeluno.MyViewHolder>{


    private Fetch fetch;
    private Context context;
    private List<anime> itemDataList;
    private TextToSpeech mtts;
    SimpleExoPlayer simpleExoPlayer;
    String string,link,img,name,cx;
    boolean available;
    private long downloadID;

    public animeluno(Context context, List<anime> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lunoitems,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        name=itemDataList.get(i).getName();

   //     name=itemDataList.get(i).getName();
        myViewHolder.txt_item_title.setText(name);






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

    }

    private void speaak(String h) {
        mtts.setLanguage(Locale.ENGLISH);

        mtts.speak(h, TextToSpeech.QUEUE_FLUSH,null);
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


        TextView txt_item_title;
      //  TextView genre,musicgen;
        CardView buy;
        LinearLayout card;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.music_file_name);
            buy=itemView.findViewById(R.id.pausecardy);
           // musicgen=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.audio_item);
        //    genre=itemView.findViewById(R.id.type);





        }



    }

}
