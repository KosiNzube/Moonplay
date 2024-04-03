package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

public class lays2 extends RecyclerView.Adapter<lays2.MyViewHolder> {

    private SharedPreferences appPreferences;
    private Context context;
    private List<doc> itemDataList;
    private final static int FADE_DURATION = 690;
    protected delistener delistener;
    static ArrayList arrayList=new ArrayList();
    public lays2(Context context, List<doc> itemDataList,delistener del) {
        this.context = context;
        this.itemDataList = itemDataList;
        this.delistener=del;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.lays2,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        myViewHolder.maingenre.setText(itemDataList.get(i).getDescription());
      //  myViewHolder.maindes.setText(itemDataList.get(i).getCat());


        myViewHolder.maindes.setText(itemDataList.get(i).getCat());



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
        setFadeAnimation(myViewHolder.img_item);

        myViewHolder.len.setText(String.valueOf(itemDataList.get(i).getLikes())+ " clips");




        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, story.class);
                intent.putExtra("image", itemDataList.get(i).getPicture());
                intent.putExtra("genre",itemDataList.get(i).getCat());
                intent.putExtra("video",itemDataList.get(i).getName());
                //      intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("dex",itemDataList.get(i).getDescription());
                intent.putExtra("name",itemDataList.get(i).getId());
                intent.putExtra("plays",itemDataList.get(i).getLikes());
                intent.putExtra("upl",itemDataList.get(i).getPoster());
                //    intent.putExtra("mb",itemDataList.get(i).getMb());
                //  intent.putExtra("type",itemDataList.get(i).getType());
                // intent.putExtra("res",itemDataList.get(i).getResolution());
                //intent.putExtra("upl",itemDataList.get(i).getUploader());
                context.startActivity(intent);
            }
        });
        myViewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable() == true) {
                    delistener.ondel(myViewHolder.getAdapterPosition(),itemDataList.get(i).getName());


                }else {
                    Toast.makeText(context, "Check your internet connection", Toast.LENGTH_LONG).show();

                }
            }// You can write what you want after the deleting process.
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

    public void removeAt(int p){
        itemDataList.remove(p);
        notifyItemRemoved(p);
        notifyItemRangeChanged(p,itemDataList.size());
    }




    @Override
    public int getItemCount() {
            return itemDataList.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView maindes,len,maingenre;
        ImageView img_item,io;
        RelativeLayout relativeLayout;
        LinearLayout card;

        ImageView save;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout=itemView.findViewById(R.id.a);
            img_item=itemView.findViewById(R.id.itemImage);

            len=itemView.findViewById(R.id.len);
            save=itemView.findViewById(R.id.button);

            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);


           // watch=itemView.findViewById(R.id.watch);




        }





    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }
    public interface delistener{
        void ondel(int path,String x);
    }

}
