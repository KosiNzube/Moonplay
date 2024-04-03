package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class laysq extends RecyclerView.Adapter<laysq.MyViewHolder> {

    protected xx delistener;
    private Context context;
    RequestManager requestManager;
    private List<instV> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public laysq(Context context, List<instV> itemDataList,xx q) {
        this.context = context;
        this.itemDataList = itemDataList;
        this.delistener=q;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.laysq,viewGroup,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



        myViewHolder.maingenre.setText(itemDataList.get(i).getDes());
        myViewHolder.maindes.setText(itemDataList.get(i).getDes());


      //  myViewHolder.maindes.setText(itemDataList.get(i).getDescription());

        if (itemDataList.get(i).getDes().length() > 30) {
            myViewHolder.maingenre.setText(itemDataList.get(i).getDes().substring(0, 30) + "...");
        } else {
            myViewHolder.maingenre.setText(itemDataList.get(i).getDes());
        }

        if (itemDataList.get(i).getPoster().length() > 30) {
            myViewHolder.maindes.setText(itemDataList.get(i).getPoster().substring(0, 30) + "...");
        } else {
            myViewHolder.maindes.setText(itemDataList.get(i).getPoster());
        }



        RequestOptions requestOptions = new RequestOptions();
        requestOptions.isMemoryCacheable();
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(itemDataList.get(i).getVideo()).centerCrop().into(myViewHolder.img_item);


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, oneplay.class);
                intent.putExtra("image", itemDataList.get(i).getThumbnail());
                intent.putExtra("des",itemDataList.get(i).getDes());
                intent.putExtra("id",itemDataList.get(i).getId());
                intent.putExtra("poster",itemDataList.get(i).getPoster());
                intent.putExtra("video",itemDataList.get(i).getVideo());

                context.startActivity(intent);
            }
        });


        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

        myViewHolder.dtbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable() == true) {
                    delistener.ondel(myViewHolder.getAdapterPosition(),itemDataList.get(i).getPoster());


                }else {
                    Toast.makeText(context, "Check your internet connection", Toast.LENGTH_LONG).show();

                }
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
    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.splash_background)
                .error(R.drawable.splash_background);

        return Glide.with(context)
                .setDefaultRequestOptions(options);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView maindes,maingenre;
        ImageView img_item,io,dtbut;
        RelativeLayout card;

        ImageView watch;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item=itemView.findViewById(R.id.itemImage);


            dtbut=itemView.findViewById(R.id.button);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);


           // watch=itemView.findViewById(R.id.watch);




        }

    }
    public interface xx{
        void ondel(int path,String x);
    }
    public void removeAt(int p){
        itemDataList.remove(p);
        notifyItemRemoved(p);
        notifyItemRangeChanged(p,itemDataList.size());
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }


}
