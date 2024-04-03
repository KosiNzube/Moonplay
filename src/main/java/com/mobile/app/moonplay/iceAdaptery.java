package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class iceAdaptery extends RecyclerView.Adapter<iceAdaptery.IceHolder> {

  private Context context;
  private List<icemodel> itemDataList;




  public iceAdaptery(Context context, List<icemodel> itemDataList) {
    this.context = context;
    this.itemDataList = itemDataList;
  }

  @NonNull
  @Override
  public IceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.ice,parent,false);
    return new IceHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull IceHolder holder, final int i) {


    holder.thumbnail.setImageBitmap(itemDataList.get(i).getThumbnail());



    holder.name.setText(itemDataList.get(i).getResolution());






    String time=String.format("%02d.%02d.%02d",
            TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime()),
            TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getTime()) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(itemDataList.get(i).getTime())), // The change is in this line
            TimeUnit.MILLISECONDS.toSeconds(itemDataList.get(i).getTime()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(itemDataList.get(i).getTime())));
    holder.length.setText(time);
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

    String kiki=preferences.getString("name","");
    if (itemDataList.get(i).getResolution().equals(kiki)){
      holder.button.setVisibility(View.VISIBLE);
    }
    holder.ice.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent=new Intent(context,Main10Activity.class);
        intent.putExtra("video", itemDataList.get(i).getVideo());
        intent.putExtra("genre","null");
        intent.putExtra("name",itemDataList.get(i).getResolution());
        context.startActivity(intent);
      }
    });

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        File x= new File(itemDataList.get(i).getVideo());


        x.delete();


        Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
      }
    });






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
