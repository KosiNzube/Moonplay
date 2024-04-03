package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


public class polomenu extends RecyclerView.Adapter<polomenu.MyViewHolder> {



    private Context context;
    private List<menu> itemDataList;
    private LocalBroadcastManager broadcaster;
    private boolean mBound = false;
    private SimpleExoPlayer player;
    protected PhotoListenerxqq photoListener;
    private MusicService musicService;
    String string, video, img, name, cx;
    private AudioManager audio;
    private long downloadID;
    private BroadcastReceiver broadcastReceiver;
    int selectedPosition=-1;
    private PlayerNotificationManager playerNotificationManager;
    int cu=0;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicService.MusicBinder mServiceBinder = (MusicService.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };

    public polomenu(Context context, List<menu> itemDataList, PhotoListenerxqq photoListener) {
        this.context = context;
        this.itemDataList = itemDataList;
        this.photoListener=photoListener;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.vintage2, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        Intent intent = new Intent(context, MusicService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        if(selectedPosition==i)
            myViewHolder.textView.setTextColor(Color.MAGENTA);


        else
            myViewHolder.textView.setTextColor(Color.WHITE);



        myViewHolder.textView.setText(itemDataList.get(i).getGenre());

        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition=i;
                notifyDataSetChanged();


                photoListener.onPhotoClick(itemDataList.get(i).getGenre());
            }
        });


    }



    @Override
    public int getItemCount() {


        return itemDataList.size();
    }







    public class MyViewHolder extends RecyclerView.ViewHolder  {



        MaterialCardView card;

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card=itemView.findViewById(R.id.save);
           // genre=itemView.findViewById(R.id.textView2);

            textView=itemView.findViewById(R.id.genre);

        }


    }
    public interface PhotoListenerxqq{
        void onPhotoClick( String name);
    }

}
