package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class xadapter extends RecyclerView.Adapter<xadapter.MyViewHolder>{
    private Context context;
    private List<shorts> itemDataList;

    public xadapter(Context context, List<shorts> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.scene,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        //holder.txt_item_title.setText(itemDataList.get(i).getName());
       Picasso.get().load(itemDataList.get(i).getImage()).into(holder.img_item);
//        holder.genre.setText(itemDataList.get(i).getGenre());

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
                intent.putExtra("res",itemDataList.get(i).getResolution());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_title,genre;
        ImageView img_item,image;
        CardView card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
         //   txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);
          //  genre=itemView.findViewById(R.id.type);

        }
    }
}
