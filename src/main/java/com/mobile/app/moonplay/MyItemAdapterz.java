package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemAdapterz extends RecyclerView.Adapter<MyItemAdapterz.MyViewHolder> {


  private Context context;
  private List<Movie> itemDataList;
  private final static int FADE_DURATION = 590;


  public MyItemAdapterz(Context context, List<Movie> itemDataList) {
    this.context = context;
    this.itemDataList = itemDataList;

  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view= LayoutInflater.from(context).inflate(R.layout.layout_item,viewGroup,false);
    return new MyViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

    myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
    myViewHolder.genre.setText(itemDataList.get(i).getGenre());

    try{
      Glide.with(context)
              .load(itemDataList.get(i).getImage())
              .centerCrop()
              .into(myViewHolder.img_item);
      //myViewHolder.genre.setVisibility(View.INVISIBLE);
      //  myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
    }catch (OutOfMemoryError error){
      error.printStackTrace();

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
  private void setFadeAnimation(View view) {
    AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
    anim.setDuration(FADE_DURATION);
    view.startAnimation(anim);
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView txt_item_title,genre;
    ImageView img_item,io;
    CardView card;




    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      txt_item_title=itemView.findViewById(R.id.tvTitle);
      img_item=itemView.findViewById(R.id.itemImage);

      card=itemView.findViewById(R.id.card);
      genre=itemView.findViewById(R.id.type);





    }





  }
}
