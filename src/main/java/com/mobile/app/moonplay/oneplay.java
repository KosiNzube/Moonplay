package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import com.mobile.app.moonplay.mainrecycler.VerticalSpacingItemDecorator;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerAdapter;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;


public class oneplay extends AppCompatActivity  {


    VideoPlayerRecyclerView mRecyclerView;
    Button back;
    TextView textView,albumx,descr, plays;
    ArrayList<instantv> instant=new ArrayList<>();
    ArrayList<instantv> instantx=new ArrayList<>();
    public static   String imagex;
    public  static String namex;
    public   String genre;
    ImageView x;
    String id;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference meme = db.collection("instvid");
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    insadapter insadapter;
    public  static   String des;
    ArrayList movieArray;
    private BroadcastReceiver broadcastReceiver;
    private boolean mBound = false;
    MusicServicex musicService;
    String up,video;
    ShapeableImageView imageView;
    TextView textViewx;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicServicex.MusicBinder mServiceBinder = (MusicServicex.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneplay);
        if (Build.VERSION.SDK_INT>=19){
            setwinflag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);

            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);

            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }
        Intent intentx = new Intent(this, MusicServicex.class);
        this.bindService(intentx, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter("com.example.exoplayer.PLAYER_STATUS")
        );








        textViewx=findViewById(R.id.noo);
        imageView=findViewById(R.id.image_view_profile_pic);


        x=findViewById(R.id.download);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        Intent intent=getIntent();
        video=intent.getExtras().getString("video");
        textView=findViewById(R.id.noo);
        textView.setVisibility(View.INVISIBLE);
        imagex= intent.getExtras().getString("image");
        des=intent.getExtras().getString("des");
        namex=intent.getExtras().getString("poster");
        id=intent.getExtras().getString("id");


        mRecyclerView=findViewById(R.id.exoPlayerRecyclerView);
        movieArray=new ArrayList();
        back=findViewById(R.id.backintime);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initView();
        createNotificationChannel();
    }

    public static void setwinflag(@NotNull Activity instantplay, final int flagTranslucentStatus, boolean b) {
        Window win=instantplay.getWindow();
        WindowManager.LayoutParams winp=win.getAttributes();
        if (b){
            winp.flags|=flagTranslucentStatus;
        }else {
            winp.flags&= ~flagTranslucentStatus;
        }
        win.setAttributes(winp);
    }


    @SuppressLint("WrongConstant")
    private void initView() {
        ///////////////////////////////////////////////////////////////


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Date date = new Date();
        sdf.format(date);
        Timestamp timestamp = new Timestamp(date);


        movieArray.add(new instV("", " ","","","",0,"","",timestamp));


        movieArray.add(new instV(video,des,id,namex,"",0,"","",timestamp));
        if (movieArray.size() > 0) {


            // Collections.shuffle(movieArray);
            LinearLayoutManager layoutManager = new LinearLayoutManager(oneplay.this);
            mRecyclerView.setLayoutManager(layoutManager);
            VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
            mRecyclerView.addItemDecoration(itemDecorator);


            mRecyclerView.setMediaObjects(movieArray);
            VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(movieArray,initGlide());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setKeepScreenOn(true);

            adapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(1);
            SnapHelper mSnapHelper = new PagerSnapHelper();
            mSnapHelper.attachToRecyclerView(mRecyclerView);


        }


        ////////////////////////////////////////////////////////////////////////////////



        //  mRecyclerView.setLayoutManager(new speedy(this, speedy.VERTICAL, false));
        //  SnapHelper snapHelper = new PagerSnapHelper();
        //  snapHelper.attachToRecyclerView(mRecyclerView);
    }

    private void LoadHorizontalProducts() {


    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mRecyclerView!=null)
            mRecyclerView.onPausePlayer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.paye();

    }

    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();


        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        overridePendingTransition(0,0);
    }
    public RequestManager initGlide(){
        RequestOptions options = new RequestOptions();
        options.isMemoryCacheable();
        //  options.placeholder(R.drawable.background_undo);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String description = " Instant";
            CharSequence name = " Instant";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Instant P", name, importance);//   channel.shouldVibrate();
            channel.setDescription(description);
            channel.setName(name);

            channel.setLightColor(Color.DKGRAY);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);


            notificationManager.createNotificationChannel(channel);

        }
    }

}
