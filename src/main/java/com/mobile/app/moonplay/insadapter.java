package com.mobile.app.moonplay;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class insadapter extends RecyclerView.Adapter<insadapter.IceHolder> {

  private Context context;
  private ArrayList<instV> itemDataList;




  public insadapter(Context context, ArrayList<instV> itemDataList) {
    this.context = context;
    this.itemDataList = itemDataList;
  }

  @NonNull
  @Override
  public IceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.air_item,parent,false);
    return new IceHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull IceHolder holder, final int i) {

    instV mediaobject= itemDataList.get(i);



  }



  @Override
  public int getItemCount() {
    return itemDataList.size();
  }

  public class IceHolder extends RecyclerView.ViewHolder {
    ImageView thumbnail;
    RelativeLayout ice;
    TextView name,length;

    ImageView button;

    public IceHolder(@NonNull View itemView) {
      super(itemView);
      button=itemView.findViewById(R.id.button);
      thumbnail=itemView.findViewById(R.id.itemImage);
      ice=itemView.findViewById(R.id.card);
      name=itemView.findViewById(R.id.tvTitle);
      length=itemView.findViewById(R.id.type);

    }
  }
}
