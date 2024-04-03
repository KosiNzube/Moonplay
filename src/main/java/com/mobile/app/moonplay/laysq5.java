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

public class laysq5 extends RecyclerView.Adapter<laysq5.MyViewHolder> {

    private Context context;
    RequestManager requestManager;
    private List<instV> itemDataList;
    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public laysq5(Context context, List<instV> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.reelslay,viewGroup,false);
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
            myViewHolder.maindes.setText(itemDataList.get(i).getThumbnail().substring(0, 30) + "...");
        } else {
            myViewHolder.maindes.setText(itemDataList.get(i).getThumbnail());
        }



        RequestOptions requestOptions = new RequestOptions();
        requestOptions.isMemoryCacheable();
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(itemDataList.get(i).getVideo()).centerCrop().into(myViewHolder.img_item);


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, singlesplay.class);
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

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }


}
