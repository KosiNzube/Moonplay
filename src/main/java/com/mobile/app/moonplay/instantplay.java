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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import com.mobile.app.moonplay.mainrecycler.VerticalSpacingItemDecorator;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerAdapter;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;


public class instantplay extends AppCompatActivity  {


    VideoPlayerRecyclerView mRecyclerView;
    Button back;
    TextView textView,albumx,descr, plays;
    ArrayList<instantv> instant=new ArrayList<>();
    ArrayList<instantv> instantx=new ArrayList<>();
    public static   String imagex;
    public  static String namex;
    public   String genre;
    CardView x;
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
    TextView textViewx;
    ShapeableImageView imageView;
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
        setContentView(R.layout.iko);
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


        x=findViewById(R.id.vxw);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        Intent intent=getIntent();
        video=intent.getExtras().getString("video");
        up=intent.getExtras().getString("upl");

        textView=findViewById(R.id.noo);
        textView.setVisibility(View.INVISIBLE);
        imagex= intent.getExtras().getString("image");
        genre= intent.getExtras().getString("genre");
        des=intent.getExtras().getString("name");
        namex=intent.getExtras().getString("dex");

        int play=intent.getExtras().getInt("plays");
        id=intent.getExtras().getString("id");


        albumx=findViewById(R.id.albumname);
        descr=findViewById(R.id.des);
        plays=findViewById(R.id.xcx);
        albumx.setText(namex);
       // descr.setText(des);

        if (namex!=null) {
            if (namex.length() > 25) {
                albumx.setText(namex.substring(0, 25) + "...");
            } else {
                albumx.setText(namex);
            }
        }

        if (genre!=null) {
            descr.setText(genre);
        }
        plays.setText(String.valueOf(play)+"PL");
        Glide.with(this)
                .load(imagex)
                .centerCrop()
                .into(imageView);


        mRecyclerView=findViewById(R.id.exoPlayerRecyclerView);
        movieArray=new ArrayList();

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




        try{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference collectionReference=db.collection(up);
            collectionReference=collectionReference.document(video).collection("parts");
            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {


                        final List<instV> movies = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                            instV gigi = documentSnapshot.toObject(instV.class);
                            movies.add(gigi);

                        }



                        int size = movies.size();

                        for (int i = 0; i < size; i++) {

                            instV movie = movies.get(i);


                            if (!movieArray.contains(movie))
                                movieArray.add(movie);

                        /*
                       if (movie.getType().contains("3")){
                           movieArrayList.remove(movie);
                       }
                       */
                        }
                        if (movieArray.size() > 0) {


                            LinearLayoutManager layoutManager = new LinearLayoutManager(instantplay.this);
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

                        if (movieArray.isEmpty()){

                            textViewx.setVisibility(View.VISIBLE);

                        }


                    }
                    if (!task.isSuccessful()) {
                        Toast.makeText(instantplay.this, "Could not connect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (OutOfMemoryError e){
            e.printStackTrace();
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
    public RequestManager initGlide(){
        RequestOptions options = new RequestOptions();
        options.isMemoryCacheable();
        //  options.placeholder(R.drawable.background_undo);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


}
