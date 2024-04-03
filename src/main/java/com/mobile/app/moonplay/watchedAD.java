package com.mobile.app.moonplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class watchedAD extends RecyclerView.Adapter<watchedAD.MyViewHolder>{
    private Context context;
    private List<watched> itemDataList;

    public watchedAD(Context context, List<watched> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.txt_item_title.setText(itemDataList.get(i).getName());
        Picasso.get().load(itemDataList.get(i).getPhoto()).fit().centerCrop().into(holder.img_item);
        holder.genre.setText(itemDataList.get(i).getGenre());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item_title,genre;
        ImageView img_item;
        RelativeLayout card;


        public MyViewHolder(View view) {
            super(view);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);

        }
    }
}
