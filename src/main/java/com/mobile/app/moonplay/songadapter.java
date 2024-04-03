package com.mobile.app.moonplay;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class songadapter extends RecyclerView.Adapter<songadapter.MyViewHolder> {
    private Context context;
    private MusicService musicService;
    private boolean isPlaying;
    public  ArrayList<MusicFiles> mFiles;
    private FragmentCommunicationx mCommunicator;
    private boolean mBound = false;
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
    private BroadcastReceiver broadcastReceiver;
    songadapter(Context context,ArrayList<MusicFiles> mFiles,FragmentCommunicationx communication){
        this.mFiles=mFiles;
        this.context=context;
        mCommunicator=communication;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.music_items,parent,false);
        return new MyViewHolder(view,mCommunicator);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Intent intent = new Intent(context, MusicService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        holder.file_name.setText(mFiles.get(position).getTitle());
        String name =mFiles.get(position).getTitle();
        String artist=mFiles.get(position).getArtist();


        holder.album.setText(mFiles.get(position).getArtist());
        byte[] image=getAlbumArt(mFiles.get(position).getPath());
        if (image!=null){
            Glide.with(context).asBitmap().load(image).centerCrop().into(holder.album_art);
        }else {
            Glide.with(context).load(R.drawable.ic_music_note_black_24dp).centerCrop().into(holder.album_art);
        }
        if (name.length() > 27) {
           holder.file_name.setText(name.substring(0, 27) + "...");
        } else {
            holder.file_name.setText(name);
        }
        if (artist.length() > 27) {
            holder.album.setText(artist.substring(0, 27) + "...");
        } else {
            holder.album.setText(artist);
        }
        if (artist.contains("unknown")){
            holder.album.setText("Unknown");
        }

        currrentp fragmentB=new currrentp();
        Bundle bundle=new Bundle();
        bundle.putString("album",mFiles.get(position).getAlbum());
        bundle.putString("artist",mFiles.get(position).getArtist());
        bundle.putString("duration",mFiles.get(position).getPath());
        bundle.putString("title",mFiles.get(position).getTitle());
        bundle.putString("path",mFiles.get(position).getPath());


        isPlaying=true;

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService != null) {

                      //  musicService.play(mFiles.get(position).getPath());
                    }

            }
        });

        fragmentB.setArguments(bundle);



    }

    @Override
    public int getItemCount() {
        if (mFiles.size()>5){

            return 5;
        }
        return mFiles.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView file_name, album;
        LinearLayout linearLayout;
        ImageView album_art;
        FragmentCommunicationx mComminication;

        public MyViewHolder(@NonNull View itemView, FragmentCommunicationx mCommunicator) {
            super(itemView);
            file_name = itemView.findViewById(R.id.music_file_name);
            album_art = itemView.findViewById(R.id.music_img);
            album = itemView.findViewById(R.id.album);
            linearLayout = itemView.findViewById(R.id.audio_item);
          //  linearLayout.setOnClickListener(this);
            //  mComminication=mCommunicator;
        }


        /*
        @Override
        public void onClick(View view) {
           // mComminication.respond(getAdapterPosition(),mFiles.get(getAdapterPosition()).getAlbum(),mFiles.get(getAdapterPosition()).getArtist(),mFiles.get(getAdapterPosition()).getPath(),mFiles.get(getAdapterPosition()).getTitle(),mFiles.get(getAdapterPosition()).getPath());
            if (musicService != null) {
                if (isPlaying) {
                    isPlaying=false;
                  //  pauseP.setImageResource(R.drawable.ic_pause_black_24dp);
                    if (musicService.isPlaying()) {
                        musicService.stop();
                    }
                    musicService.play(mFiles.get(getAdapterPosition()).getPath());
                } else{
                    musicService.pause();
                 //   pauseP.setImageResource(R.drawable.exo_icon_play);
                    isPlaying=true;

                }

            }

        }
        */

    }


    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever=new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art=retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

}
