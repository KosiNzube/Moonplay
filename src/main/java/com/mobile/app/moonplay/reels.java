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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import com.mobile.app.moonplay.mainrecycler.VerticalSpacingItemDecorator;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerAdapter;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;


public class reels extends AppCompatActivity  {


    public FirebaseUser user;
    VideoPlayerRecyclerView mRecyclerView;
    Button back;
    TextView textView,albumx,descr, plays;
    ArrayList<instantv> instant=new ArrayList<>();
    ArrayList<instantv> instantx=new ArrayList<>();
    public static   String imagex;
    public  static String namex;
    public   String genre;
    ProgressBar progressBar;
    ImageView x;
    String id;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference meme = db.collection("instvid");
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    insadapter insadapter;
    public  static   String des;
    private BroadcastReceiver broadcastReceiver;
    private boolean mBound = false;
    ArrayList<String> zx=new ArrayList();
    MaterialCardView materialCardView;
    Button rem;
    MusicServicex musicService;
    ImageView thums;
    ArrayList<String> viewed=new ArrayList();
    public static ArrayList movieArrayxxx=new ArrayList();
    String up,video;
    private FirebaseAuth firebaseAuth;
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
        setContentView(R.layout.ikox);
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


        thums=findViewById(R.id.thumbs);
        Glide.with(reels.this).asGif()
                .load(R.raw.tv)
                .diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(thums);




        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        firebaseAuth= FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();

        materialCardView=findViewById(R.id.pausecardyx);




        rem=findViewById(R.id.rem);

        imageView=findViewById(R.id.image_view_profile_pic);


        x=findViewById(R.id.download);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        textView=findViewById(R.id.noo);
        textView.setVisibility(View.INVISIBLE);


        mRecyclerView=findViewById(R.id.exoPlayerRecyclerView);

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


      //  movieArray.add(new instV("", " ","","","",0,"",timestamp));


        //movieArray.add(new instV(video,des,id,namex,null));


        movieArrayxxx.clear();

        db.collection("Users").document(user.getUid()).collection("instant").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                final List<instV> movies = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                    instV gigi = documentSnapshot.toObject(instV.class);

                    movies.add(gigi);

                }
                int size = movies.size();
                for (int i = size - 1; i >= 0; i--) {


                    instV movie = movies.get(i);
                  //  zx.add("");
                    zx.add(movie.getVideo());



                }

                meme.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {


                            final List<instV> movies = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                                instV gigi = documentSnapshot.toObject(instV.class);
                               // gigi.setImage(documentSnapshot.getId());
                                movies.add(gigi);

                            }




                            int size = movies.size();

                            for (int i = size - 1; i >= 0; i--) {

                                instV movie = movies.get(i);

                                Uri uri=Uri.parse(movies.get(i).getVideo());





                                if (!zx.contains(movie.getVideo())){
                                    if (!movieArrayxxx.contains(movie)) {
                                        movieArrayxxx.add(movie);

                                    }
                                }
                            }


                            if (movieArrayxxx.size() > 0) {


                                progressBar.setVisibility(View.INVISIBLE);
                                thums.setVisibility(View.GONE);
                                Collections.shuffle(movieArrayxxx);

                                LinearLayoutManager layoutManager = new LinearLayoutManager(reels.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
                                mRecyclerView.addItemDecoration(itemDecorator);


                                mRecyclerView.setMediaObjects(movieArrayxxx);
                                VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(movieArrayxxx,initGlide());
                                mRecyclerView.setAdapter(adapter);
                                mRecyclerView.setKeepScreenOn(true);

                                adapter.notifyDataSetChanged();
                                mRecyclerView.smoothScrollToPosition(1);
                                SnapHelper mSnapHelper = new PagerSnapHelper();
                                mSnapHelper.attachToRecyclerView(mRecyclerView);




                            }

                            if (movieArrayxxx.size()==0){
                                thums.setVisibility(View.GONE);
                                progressBar.setVisibility(View.INVISIBLE);
                                textView.setVisibility(View.VISIBLE);
                            }


                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(reels.this, "Could not connect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });







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


    public RequestManager initGlide(){
        RequestOptions options = new RequestOptions();
        options.isMemoryCacheable();
      //  options.placeholder(R.drawable.background_undo);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
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
    @SuppressLint("WrongThread")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
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

}
