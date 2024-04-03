package com.mobile.app.moonplay;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


public class currrentp extends Fragment {
    private MusicService musicService;
    ImageView imageView;
    RecyclerView recyclerView;
    private boolean mBound = false;
    CardView pausecardy;
    private BroadcastReceiver broadcastReceiver;
    TextView xy,p,kx;
    static  ArrayList vee;

    private AudioManager audio;
    ScrollView x;
    private boolean isPlaying;
    ImageView pauseP;
    String path;
    ProgressBar progressBar;
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

    private Snackbar snackbar;
    private static final int READ_PHONE_STATE_REQUEST_CODE = 22;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String album=getArguments().getString("album");
        String artis=getArguments().getString("artist");

        String duration=getArguments().getString("duration");

        String title=getArguments().getString("title");
        path=getArguments().getString("path");

        kx=view.findViewById(R.id.kx);
        kx.setText(album);


        xy=view.findViewById(R.id.moviename);
        p=view.findViewById(R.id.genre);
        imageView=view.findViewById(R.id.image);

        pauseP=view.findViewById(R.id.pauseP);
        audio = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        processPhoneListenerPermission();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
                if (tm != null) {
                    if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
                        if (musicService.isPlaying()) {
                            musicService.stop();
                            pauseP.setImageResource(R.drawable.exo_icon_play);
                        }
                    }
                }

                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                    if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                        if (snackbar.isShown()) {
                            snackbar.dismiss();
                        }
                        pauseP.setEnabled(true);
                    } else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
                        pauseP.setEnabled(false);
                        snackbar.show();
                    }
                }

                int playerState = intent.getIntExtra("state", 0);
                if (playerState == PlaybackStateCompat.STATE_BUFFERING) {
                    Glide.with(getActivity()).load(R.drawable.exo_controls_pause).into(pauseP);
                    pauseP.setImageResource(R.drawable.ic_pause_black_24dp);
                } else if (playerState == PlaybackStateCompat.STATE_PLAYING) {
                    pauseP.setImageResource(R.drawable.ic_pause_black_24dp);
                    Glide.with(getActivity()).load(R.drawable.exo_icon_play).into(pauseP);
                    int musicVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                    if (musicVolume == 0) {
                        Toast.makeText(getActivity(), "Volume is muted", Toast.LENGTH_LONG).show();
                    }
                } else if (playerState == PlaybackStateCompat.STATE_PAUSED) {
                    pauseP.setImageResource(R.drawable.exo_icon_play);
                    Glide.with(getActivity()).load(R.drawable.exo_controls_pause).into(pauseP);
                }
            }
        };


        byte[] image=getAlbumArt(path);
        if (image!=null){
            Glide.with(getActivity()).asBitmap().load(image).centerCrop().into(imageView);
        }else {
           // Glide.with(getActivity()).load(R.drawable.ic_audiotrack_dark).centerCrop().into(imageView);
        }

        xy.setText(title);
        p.setText(artis);
        isPlaying = true;

        pauseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService != null) {
                  if (isPlaying) {
                      isPlaying=false;
                      pauseP.setImageResource(R.drawable.ic_pause_black_24dp);

                        if (musicService.isPlaying()) {
                            musicService.stop();

                        }
//                        musicService.play(path);
                    } else{


                      musicService.pause();
                      pauseP.setImageResource(R.drawable.exo_icon_play);
                      isPlaying=true;

                  }

                }
            }
        });















    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver((broadcastReceiver),
                new IntentFilter("com.example.exoplayer.PLAYER_STATUS")
        );
    }
    @Override
    public void onResume() {
        super.onResume();
        if (musicService != null && musicService.isPlaying()) {
            pauseP.setImageResource(R.drawable.ic_pause_black_24dp);
            Glide.with(getActivity()).load(R.drawable.exo_icon_play).into(pauseP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.mix2, container, false);
    }

    private void processPhoneListenerPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_REQUEST_CODE) {
            if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(getActivity().getApplicationContext(), "Permission not granted.\nThe player will not be able to pause music when phone is ringing.", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever=new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art=retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    public void playStop(View view) {
        if (!musicService.isPlaying()) {
           // musicService.play(path);
        } else {
            musicService.stop();
        }
        Animation animFadein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in);
        pauseP.startAnimation(animFadein);
    }


}
