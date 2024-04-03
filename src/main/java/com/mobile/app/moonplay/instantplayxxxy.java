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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class instantplayxxxy extends AppCompatActivity  {


    RecyclerView mRecyclerView;
    ImageView back;
    TextView textView,albumx,descr;
    ArrayList<instantv> instant=new ArrayList<>();
    ArrayList<instantv> instantx=new ArrayList<>();
    public static   String imagex;
    public  static String namex;
    public static   String genre;
    ImageView x;
    String id;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference meme = db.collection("instvid");
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    insadapter insadapter;
    public  static   String des;
    public  static ArrayList movieArray;
    private BroadcastReceiver broadcastReceiver;
    private boolean mBound = false;
    MusicServicex musicService;
    String up,video;
    TextView textViewx;
    ShapeableImageView imageView;
    ArrayList pins=new ArrayList();
    MaterialCardView pinx;
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
        setContentView(R.layout.ikoxxx);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        Intent intentx = new Intent(this, MusicServicex.class);
        this.bindService(intentx, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter("com.example.exoplayer.PLAYER_STATUS")
        );

        pinx=findViewById(R.id.itemImagey);





        imageView=findViewById(R.id.image_view_profile_pic);

        TextView clip=findViewById(R.id.clips);

        Intent intent=getIntent();
        video=intent.getExtras().getString("video");
        up=intent.getExtras().getString("upl");


        imagex= intent.getExtras().getString("image");
        genre= intent.getExtras().getString("genre");
        des=intent.getExtras().getString("name");
        namex=intent.getExtras().getString("dex");

        int pinxx=intent.getExtras().getInt("pins");
        id=intent.getExtras().getString("id");

        MovieNames x=new MovieNames(video);
        db.collection("Users").document(Main7Activity.user.getUid()).collection("pins").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {


                    final List<MovieNames> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        MovieNames gigi = documentSnapshot.toObject(MovieNames.class);
                        movies.add(gigi);

                    }



                    int size = movies.size();

                    for (int i = 0; i < size; i++) {

                        MovieNames movie = movies.get(i);

                        Toast.makeText(getApplicationContext(), movie.getMovieName(), Toast.LENGTH_SHORT).show();
                        if (!pins.contains(movie.getMovieName())) {
                            pins.add(movie.getMovieName());
                        }


                        /*
                       if (movie.getType().contains("3")){
                           movieArrayList.remove(movie);
                       }
                       */

                    }
                }

            }
        });


        if (pins.contains(x)){
            TextView textView=findViewById(R.id.list);
            textView.setTextColor(Color.YELLOW);
        }

        pinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!pins.contains(x)) {
                    db.collection("Valbum").document(video).update("pins", FieldValue.increment(1));
                    db.collection("Users").document(Main7Activity.user.getUid()).collection("pins").add(x).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Pinned", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

        Button xx=findViewById(R.id.bitcj);
        xx.setText(namex);

        clip.setText(genre);
        albumx=findViewById(R.id.albumname);
        descr=findViewById(R.id.des);

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

        Glide.with(this)
                .load(imagex)
                .centerCrop()
                .into(imageView);



        mRecyclerView=findViewById(R.id.exoPlayerRecyclerView);
        movieArray=new ArrayList();
        back=findViewById(R.id.download);
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


                            RecyclerView view1=findViewById(R.id.exoPlayerRecyclerView);
                            laysq2 adapter = new laysq2(instantplayxxxy.this, movieArray);
                            GridLayoutManager glm = new GridLayoutManager(instantplayxxxy.this, 2);
                            view1.setLayoutManager(glm);
                            view1.setAdapter(adapter);


                        }



                    }
                    if (!task.isSuccessful()) {
                        Toast.makeText(instantplayxxxy.this, "Could not connect", Toast.LENGTH_SHORT).show();
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
