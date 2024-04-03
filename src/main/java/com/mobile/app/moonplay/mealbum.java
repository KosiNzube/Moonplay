package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class mealbum extends AppCompatActivity {

    RecyclerView trendingRecy,con,popo,action,randomrecy;
    ProgressBar progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView,musical,romance,thriller,random;
    ImageView button,koko;
    CardView cardView,res,theme,cast,vid,info;
    Boolean isScrolling=false;
    int requestCode = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    TextView xyz,abc;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView playpause,result;
    private  static  int splashy=10;
    private CollectionReference meme = db.collection("instvid");
    SimpleExoPlayer simpleExoPlayer;
    private  static  int splashyy=15;
    Boolean xxx=false;
    ArrayList<String> x=new ArrayList();
    ArrayList arrayList=new ArrayList();
    CardView newp;
    public static ArrayList movieArrayxxxy;
    int currentItems, totalitems, scrollistener;
    private boolean isPlaying;
    TextView loading,ii,cwatch;
    String name;
    private SimpleExoPlayer videoPlayer=null;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private ArrayList<instantv> mediaObjectListx = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    Button but;
    TextView inter,len,backn;
    int cu=0;
    public FirebaseUser user;
    private TextToSpeech mtts;
    private ImageView talker;
    ArrayList<genx> gen = new ArrayList<>();
    ArrayList<music> sound = new ArrayList<>();
    private CollectionReference valbum = db.collection("Valbum");
    //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //   private CollectionReference nolly=db.collection("music");
    LeastRecentlyUsedCacheEvictor ce=null;
    File cf=null;
    SimpleCache cac;

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    RecyclerView trendingRecyx,foru;
    int a;
    TextView muse;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makemusicxxx);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }
        muse=findViewById(R.id.mus);
        firebaseAuth= FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();

        CardView cardView=findViewById(R.id.vx);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mealbum.this, mealbumxxx.class);
                startActivity(intent);




            }
        });
        movieArrayxxxy=new ArrayList();

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

                    ArrayList movieArray = new ArrayList();





                    for (int i = size - 1; i >= 0; i--) {


                        doc movie = movies.get(i);

                        if (!user.getUid().equals(movie.getId())){
                            if (movie.getLikes() > 3) {
                                movieArray.add(movie);

                            }

                        }



                        if (movieArray.size() > 0) {
                            muse.setVisibility(View.INVISIBLE);

                            // Collections.shuffle(movieArray);
                            RecyclerView view1 = findViewById(R.id.yiyii);
                            GridLayoutManager glm = new GridLayoutManager(mealbum.this, 2);
                            view1.setLayoutManager(glm);
                            layse12xx adapter3 = new layse12xx(mealbum.this, movieArray);
                            view1.setAdapter(adapter3);
                        }
                        if (movieArray.size()==0){

                            //  Toast.makeText(mealbum.this, "No pinned album yet", Toast.LENGTH_LONG).show();

                        }

                    }

                }
            }
        });





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }

}
