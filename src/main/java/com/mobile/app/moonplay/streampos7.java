package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class streampos7 extends RecyclerView.Adapter<streampos7.MyViewHolder>{
    private Context context;
    private List<Streampostion> itemDataList;

    public streampos7(Context context, List<Streampostion> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.accountcard,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int i) {
        holder.txt_item_title.setText(itemDataList.get(i).getDname());
       Picasso.get().load(itemDataList.get(i).getImages()).into(holder.img_item);

        String totDur = String.format("%02d.%02d.%02d",
                TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getMio()),
                TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getMio()) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getMio())), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(itemDataList.get(i).getMio()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getMio())));

        if (itemDataList.get(i).getMio() > 3) {
            holder.genre.setText(totDur);
        } else {
            holder.genre.setText("Favorite");
        }

        holder.vcvc.setText("Binge ");


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,popo.class);
                intent.putExtra("photo", itemDataList.get(i).getImages());
                intent.putExtra("name",itemDataList.get(i).getDname());
                intent.putExtra("video",itemDataList.get(i).getDlink());
                intent.putExtra("genre",itemDataList.get(i).getGeenre());
                intent.putExtra("pos",itemDataList.get(i).getMio());
                intent.putExtra("subt",itemDataList.get(i).getSub());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_title,genre,vcvc;
        ImageView img_item,image;
        RelativeLayout card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            vcvc=itemView.findViewById(R.id.type);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.typee);

        }
    }
}
