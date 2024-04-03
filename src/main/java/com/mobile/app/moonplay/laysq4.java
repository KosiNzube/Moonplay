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

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class laysq4 extends RecyclerView.Adapter<laysq4.MyViewHolder> {

    private Context context;
    RequestManager requestManager;
    private List<instV> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public laysq4(Context context, List<instV> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.laysq3,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



        myViewHolder.maingenre.setText(itemDataList.get(i).getDes());
        myViewHolder.maindes.setText(itemDataList.get(i).getDes());


      //  myViewHolder.maindes.setText(itemDataList.get(i).getDescription());

        if (itemDataList.get(i).getDes()!=null) {
            if (itemDataList.get(i).getDes().length() > 45) {
                myViewHolder.maingenre.setText(itemDataList.get(i).getDes().substring(0, 45) + "...");
            } else {
                myViewHolder.maingenre.setText(itemDataList.get(i).getDes());
            }

            if (itemDataList.get(i).getPoster().length() > 45) {
                myViewHolder.maindes.setText(itemDataList.get(i).getPoster().substring(0, 45) + "...");
            } else {
                myViewHolder.maindes.setText(itemDataList.get(i).getPoster());
            }

        }


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.isMemoryCacheable();
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(itemDataList.get(i).getVideo()).centerCrop().into(myViewHolder.img_item);






    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }





    @Override
    public int getItemCount() {

        if (itemDataList.size()<100){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 100;
        }


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


        TextView maingenre,list;
        ImageView img_item,io;
        RelativeLayout card;

        TextView maindes;

        ImageView watch;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item=itemView.findViewById(R.id.itemImage);

            list=itemView.findViewById(R.id.list);

            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);


            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();

                    Intent intent = new Intent(context, singlesplayxy.class);


                    intent.putExtra("image", pos);

                    context.startActivity(intent);

                    /*

                    intent.putExtra("image", itemDataList.get(i).getThumbnail());
                    intent.putExtra("des",itemDataList.get(i).getDes());
                    intent.putExtra("id",itemDataList.get(i).getId());
                    intent.putExtra("poster",itemDataList.get(i).getPoster());
                    intent.putExtra("video",itemDataList.get(i).getVideo());

                    */
                }
            });


           // watch=itemView.findViewById(R.id.watch);




        }

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
