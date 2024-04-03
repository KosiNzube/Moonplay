package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class chronicley extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView  musical, romance, thriller, random;
    CardView textView;
    Uri videouri;
    private static final int PICK_image=100;
    ImageView button, koko;
    lays2 adapter;
    private ArrayList<mainpost> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    private int notificationId = 1234;
    ScrollView scrollView;
    private ArrayList<quick> quicks = new ArrayList<>();
    ExoPlayerRecyclerView mRecyclerView;
    int requestCode = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    ImageView playpause;
    private static int splashy = 10;
    SimpleExoPlayer simpleExoPlayer;
    private static int splashyy = 15;
    ArrayList arrayList = new ArrayList();
    List<Movie> bookList;

    private boolean isPlaying;
    ArrayList<Movie> music = new ArrayList<>();
    TextView loading, ii, cwatch;
    String name;
    String dex;
    String gen;
    String image;
    String mb;
    String type;
    String uplo;
    ImageView previos, userpic;
    private Handler mainHandler;
    String res;
    Runnable updatePlayer;
    int a; //animation
    int b;//horror
    int c;//fantasy
    int d;//scifi
    int e;//comedy
    int f;//drama
    int h;//crime
    ArrayList<Movie> movieArrayLists = new ArrayList<>();
    String vid;
    int x;//action
    int y;//romance
    TextView username;
    private CollectionReference meme = db.collection("shorts");
    private CollectionReference memex = db.collection("Italy");
    String xx;
    List<genrex> agentList;
    ProgressBar progressBarx;
    List<music> musicList;
    Button button3, studio;
    FloatingActionButton v;
    //ImageView album;
    TextView tctc,noalbums;
    ImageView back;
    private TextToSpeech mtts;
    RelativeLayout relative, part1, part2;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    ArrayList<video> shorts = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chronicley);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        firebaseAuth= FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();

        username=findViewById(R.id.user);

        userpic=findViewById(R.id.itemImage);

        userpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chovideo();
            }
        });


        username.setText(user.getDisplayName());
        noalbums=findViewById(R.id.noo);
        textView=findViewById(R.id.button3);
        if (user.getPhotoUrl()!=null) {
            Picasso.get().load(user.getPhotoUrl()).fit().centerCrop().into(userpic);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o = new Intent(chronicley.this, result.class);
                startActivity(o);
            }
        });

        db= FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);

        CollectionReference valbum = db.collection("Valbum");
        valbum.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<doc> movies = new ArrayList<>();

                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        doc gigi = documentSnapshot.toObject(doc.class);
                        gigi.setName(documentSnapshot.getId());
                        gigi.setPoster(valbum.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    ArrayList movieArray=new ArrayList();
                    for (int i = size - 1; i >= 0; i--) {

                        progressBar.setVisibility(View.INVISIBLE);
                        doc movie = movies.get(i);


                        if (user.getUid().equals(movie.getId())) {
                                movieArray.add(movie);
                        }
                    }
                    if (movieArray.size() > 0) {

                        //  Collections.shuffle(movieArray);
                        RecyclerView view1=findViewById(R.id.fifi);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(chronicley.this, LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                         adapter = new lays2(chronicley.this, movieArray, new lays2.delistener() {
                            @Override
                            public void ondel(int path,String x) {
                                db.collection("Valbum").document(x).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        adapter.removeAt(path);


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });


                        view1.setAdapter(adapter);
                    }
                    if (movieArray.isEmpty()){
                        progressBar.setVisibility(View.INVISIBLE);
                        noalbums.setVisibility(View.VISIBLE);
                    }

                }
            }
        });



    }
    private void chovideo() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_image&&resultCode==RESULT_OK &&data.getData()!=null) {

            videouri = data.getData();


            UserProfileChangeRequest.Builder userProfileChangeRequest = new UserProfileChangeRequest.Builder().setPhotoUri(videouri);

            user.updateProfile(userProfileChangeRequest.build()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Picasso.get().load(videouri).fit().centerCrop().into(userpic);
                    ProgressBar progressBar=findViewById(R.id.progressBarx);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(chronicley.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                }
            });



            // Toast.makeText(result.this, "Selected", Toast.LENGTH_SHORT).show();
        }
    }




    private void refresh() {
        this.finish();
        this.overridePendingTransition(0,0);
        startActivity(this.getIntent());
        this.overridePendingTransition(0,0);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);

    }

}
