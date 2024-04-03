package com.mobile.app.moonplay;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.Status;
import com.tonyodev.fetch2core.DownloadBlock;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class idy extends RecyclerView.Adapter<idy.MyViewHolder>{
    private Context context;
    private List<id> itemDataList;
    private Fetch fetch;
    int ii;
    boolean viewer;
    String photo;
    int x;

    private int z;
    private int k;
    private  static  int splashy=200;

    public idy(Context context, List<id> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.id,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(context)
                .setDownloadConcurrentLimit(9)
                .build();
        fetch = Fetch.Impl.getInstance(fetchConfiguration);




        holder.progressBar.setVisibility(View.VISIBLE);
        holder.progressBar.setProgress(itemDataList.get(i).getProgress());






        int v=itemDataList.get(i).getName().indexOf("xxxxxx");



        holder.txt_item_title.setText(itemDataList.get(i).getName().substring(0,v));


        photo=itemDataList.get(i).getName().substring(v).replace("xxxxxx","");

        if (photo.startsWith("http")) {
            Glide.with(context)
                    .load(photo)
                    .centerCrop()
                    .into(holder.img_item);
        }else {
            holder.nini.setVisibility(View.GONE);
        }

        holder.cancelcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetch.cancel(itemDataList.get(i).getId());
                fetch.remove(itemDataList.get(i).getId());
                fetch.delete(itemDataList.get(i).getId());
                holder.genre.setText("Cancelled");
                holder.pause.setVisibility(View.GONE);
                holder.card.setTextColor(Color.DKGRAY);
                holder.cancelcard.setEnabled(false);
                holder.retrycard.setEnabled(false);
                holder.retry.setTextColor(Color.DKGRAY);


            }
        });










        if (itemDataList.get(i).getStatus()==Status.PAUSED){
            holder.genre.setText("Paused");

            itemDataList.get(i).setStatus(Status.PAUSED);


            holder.pause.setText("Resume");



        }
        if (itemDataList.get(i).getStatus()==Status.FAILED){
            holder.genre.setText("Interrupted");
            holder.pause.setVisibility(View.GONE);
            holder.retry.setText("Continue");
            holder.retry.setTextColor(Color.WHITE);


            holder.retrycard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isNetworkAvailable()) {
                        fetch.retry(itemDataList.get(i).getId());
                        holder.pause.setVisibility(View.VISIBLE);
                        holder.genre.setText("Retrying");

                    }else {
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        if (itemDataList.get(i).getStatus()==Status.ADDED){
            holder.genre.setText("Downloading");
        }

        if (itemDataList.get(i).getStatus()==Status.NONE){
            holder.genre.setText("Waiting for connection");
        }
        if (itemDataList.get(i).getStatus()==Status.REMOVED){
            holder.genre.setText("Disconnected");
        }
        if (itemDataList.get(i).getStatus()==Status.DELETED){
            holder.genre.setText("Deleted");
        }
        if (itemDataList.get(i).getStatus()==Status.DOWNLOADING){
            holder.genre.setText("Downloading");
            holder.pause.setVisibility(View.VISIBLE);

        }

        // viewer=true;


        holder.pausecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemDataList.get(i).getStatus()==Status.PAUSED) {
                    // viewer=false;
                    if (isNetworkAvailable()) {

                        fetch.resume(itemDataList.get(i).getRequestid());

                        holder.genre.setText("Downloading");

                        holder.pause.setText("Pause");
                        itemDataList.get(i).setStatus(Status.DOWNLOADING);

                    }else {
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
                    }


                }else {


                    if (isNetworkAvailable()) {
                        fetch.pause(itemDataList.get(i).getRequestid());
                        holder.genre.setText("Paused");
                        holder.pause.setText("Resume");
                        itemDataList.get(i).setStatus(Status.PAUSED);

                    }else {
                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                    //viewer=true;
                }
            }
        });

        FetchListener fetchListener=new FetchListener() {
            @Override
            public void onAdded(@NotNull Download download) {

            }

            @Override
            public void onQueued(@NotNull Download download, boolean b) {

            }

            @Override
            public void onWaitingNetwork(@NotNull Download download) {

            }

            @Override
            public void onCompleted(@NotNull Download download) {
                if (itemDataList.get(i).getId()==download.getId()) {

                    holder.genre.setText("Complete");
                    holder.pausecard.setEnabled(false);
                    holder.pause.setText(". . .");
                    holder.card.setTextColor(Color.DKGRAY);
                    holder.retry.setTextColor(Color.DKGRAY);
                    holder.retrycard.setEnabled(false);
                    holder.cancelcard.setEnabled(false);
                    fetch.remove(itemDataList.get(i).getId());
                }

            }

            @Override
            public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
                if (itemDataList.get(i).getId()==download.getId()) {

                    holder.genre.setText("Error. Check your internet...");
                }

            }

            @Override
            public void onDownloadBlockUpdated(@NotNull Download download, @NotNull DownloadBlock downloadBlock, int i) {

            }

            @Override
            public void onStarted(@NotNull Download download, @NotNull List<? extends DownloadBlock> list, int i) {




            }

            @Override
            public void onProgress(@NotNull Download download, long l, long l1) {



                if (download.getId()==itemDataList.get(i).getId()) {

                    holder.progressBar.setProgress(download.getProgress());
                    int kb= (int) download.getTotal()/1024;
                    int mb=kb/1024;
                    holder.button.setText("Total Size: "+String.valueOf(mb)+" MB");
                }
            }

            @Override
            public void onPaused(@NotNull Download download) {
                if (itemDataList.get(i).getId()==download.getId()) {

                    holder.genre.setText("Paused");
                    int kb= (int) download.getTotal()/1024;
                    int mb=kb/1024;
                    holder.button.setText("Total Size: "+String.valueOf(mb)+" MB");

                }

            }

            @Override
            public void onResumed(@NotNull Download download) {

                if (itemDataList.get(i).getId()==download.getId()) {

                    holder.genre.setText("Downloading");
                }

            }

            @Override
            public void onCancelled(@NotNull Download download) {
                if (itemDataList.get(i).getId()==download.getId()) {

                    holder.genre.setText("Cancelled");
                    holder.pausecard.setVisibility(View.GONE);
                }

            }

            @Override
            public void onRemoved(@NotNull Download download) {
                if (itemDataList.get(i).getId()==download.getId()) {


                    holder.pausecard.setVisibility(View.GONE);
                }

            }

            @Override
            public void onDeleted(@NotNull Download download) {
                if (itemDataList.get(i).getId()==download.getId()) {


                    holder.pausecard.setVisibility(View.GONE);
                }

            }
        };
        fetch.addListener(fetchListener);
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

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
        TextView txt_item_title,retry;
        TextView card,pause;


        ImageView img_item,nini;
        CardView pausecard,cancelcard,retrycard;
        TextView genre;
        ProgressBar progressBar;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            retry=itemView.findViewById(R.id.retry);

            button=itemView.findViewById(R.id.hostbuttony);
            nini=itemView.findViewById(R.id.nini);
            pausecard=itemView.findViewById(R.id.pausecard);
            retrycard=itemView.findViewById(R.id.retrycard);
            cancelcard=itemView.findViewById(R.id.cancelcard);
            img_item=itemView.findViewById(R.id.itemImage);
            txt_item_title=itemView.findViewById(R.id.name);
            card=itemView.findViewById(R.id.butn);
            pause=itemView.findViewById(R.id.butnn);
            progressBar=itemView.findViewById(R.id.pro);
            genre=itemView.findViewById(R.id.doe);


        }
    }
}
