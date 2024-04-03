package com.mobile.app.moonplay;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


public class genmusic extends RecyclerView.Adapter<genmusic.MyViewHolder> {



    private Context context;
    private List<musicw> itemDataList;
    private LocalBroadcastManager broadcaster;
    private boolean mBound = false;
    private SimpleExoPlayer player;

    private MusicService musicService;
    String string, video, img, name, cx;
    private AudioManager audio;
    private long downloadID;
    private BroadcastReceiver broadcastReceiver;

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

    public genmusic(Context context, List<musicw> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.simple_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {



        Intent intent = new Intent(context, MusicService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(context).registerReceiver((broadcastReceiver),
                new IntentFilter("com.example.exoplayer.PLAYER_STATUS")
        );

        Bundle bundle=new Bundle();
        bundle.putString("name",itemDataList.get(i).getGenre());
        bundle.putInt("len",itemDataList.get(i).getLen());
        bundle.putString("link",itemDataList.get(i).getLink());


     //  myViewHolder.progressBar.setVisibility(View.INVISIBLE);

        string = String.valueOf(itemDataList.get(i).getLen());
        video = itemDataList.get(i).getLink();
        name = itemDataList.get(i).getGenre();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
       // AdaptiveTrackSelection.Factory trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl=new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true,16))
                .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,VideoPlayerConfig.MAX_BUFFER_DURATION,VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .build();

        @DefaultRenderersFactory.ExtensionRendererMode int exte=DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER;

        RenderersFactory renderersFac=new DefaultRenderersFactory(context).setExtensionRendererMode(exte);

        player = new SimpleExoPlayer.Builder(context,renderersFac).setLoadControl(loadControl).build();

    //    myViewHolder.txt_item_title.setText(name);
        if (name == null) {
                name="Moon Music";
        }

        myViewHolder.txt_item_title.setText(name);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
                if (tm != null) {
                    if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
                        if (musicService.isPlaying()) {
                            musicService.stop();

                        }
                    }
                }

                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                    if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {


                    } else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {


                    }
                }

                int playerState = intent.getIntExtra("state", 0);
                if (playerState == PlaybackStateCompat.STATE_BUFFERING) {
                    //  Glide.with(MainActivity.this).load(R.drawable.not_playing).into(imageViewTarget);
                 //   myViewHolder.progressBar.setVisibility(View.VISIBLE);
                } else if (playerState == PlaybackStateCompat.STATE_PLAYING) {

                  //  myViewHolder.progressBar.setVisibility(View.INVISIBLE);
                    // Glide.with(MainActivity.this).load(R.drawable.playing).into(imageViewTarget);
                    int musicVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if (musicVolume == 0) {
                        Toast.makeText(context, "Volume is muted", Toast.LENGTH_LONG).show();
                    }
                } else if (playerState == PlaybackStateCompat.STATE_PAUSED) {
                  //  myViewHolder.progressBar.setVisibility(View.INVISIBLE);
                    // Glide.with(MainActivity.this).load(R.drawable.not_playing).into(imageViewTarget);
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(broadcastReceiver, filter);

      //  createNotificationChannel();

        audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);




     //   myViewHolder.genre.setText(itemDataList.get(i).getLen());

        if (itemDataList.get(i).isOwn()==true){
            myViewHolder.ava.setText("Sold Out");
        }else{
            myViewHolder.ava.setText("Available");
        }

        /*
        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownload(itemDataList.get(i).getLink(), itemDataList.get(i).getGenre(),itemDataList.get(i).getImage());
            }
        });
*/
        myViewHolder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService.isPlaying()){
                    musicService.stop();
                    myViewHolder.txt_item_title.setTextColor(Color.WHITE);
                }
            }
        });


    }

    private void startDownload(String url, String name, String photo) {



        /*
        Intent intent = new Intent(context, musiclocality.class);
        intent.putExtra("url", url);
        intent.putExtra("namemovie",name);
        intent.putExtra("picture",photo);
        context.startActivity(intent);
        */
    }


    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    public void playStop(View view) {
        if (!musicService.isPlaying()) {
          //  musicService.play(video);
        } else {
            musicService.stop();
        }

    }




    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Moon Music";
            String description = "test channel for streaming radio";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("radio_playback_channel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView txt_item_title;
        TextView genre,ava;
        CardView cardView;
        ImageView button;
        RelativeLayout card;
        ImageView stop;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.textView);
            cardView=itemView.findViewById(R.id.pausecardy);
            button=itemView.findViewById(R.id.button);
            card=itemView.findViewById(R.id.card);
          //  genre=itemView.findViewById(R.id.textView2);
            ava=itemView.findViewById(R.id.textViewx);

            stop=itemView.findViewById(R.id.stop);

            card.setOnClickListener(this);



        }


        @Override
        public void onClick(View view) {
            if (musicService.isPlaying()){
                musicService.stop();
            }

            txt_item_title.setTextColor(Color.YELLOW);
          //  musicService.play(itemDataList.get(getAdapterPosition()).getLink());
          //  mComminication.respond(getAdapterPosition(),itemDataList.get(getAdapterPosition()).getName(),itemDataList.get(getAdapterPosition()).getLink(),itemDataList.get(getAdapterPosition()).getLength(),itemDataList.get(getAdapterPosition()).isOwn());
        }
    }
}
