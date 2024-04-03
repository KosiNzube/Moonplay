package com.mobile.app.moonplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class Main13Activity extends AppCompatActivity {

    String cast;
    TextView x,y,z;
    boolean isloading=false;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> otherx=new ArrayList<>();

    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference korean=db.collection("Spanish");
    private CollectionReference series=db.collection("Series");
    int a;
    MyItemAdapterxxgrid aps;
    Query query;
    TextView zas;
    ArrayList xax=new ArrayList();
    Button button3,xbb;
    TextView sese;
    RecyclerView recyclerView;
    ScrollView scroll;
    ProgressBar xcx;
    ImageView opti;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");
        a=cast.length();
        a=a/2;


        zas=findViewById(R.id.zas);

        sese=findViewById(R.id.genre);

        sese.setText(cast.toLowerCase());


        zas.setText(" no search results ");


        xcx=findViewById(R.id.prozzz);

       // query = medieval.orderBy("timestamp", Query.Direction.ASCENDING).limit(25);

       // aps=new MyItemAdapterxxgrid(this);

        xbb=findViewById(R.id.fix);


        opti=findViewById(R.id.backward);

        scroll=findViewById(R.id.scroll);
       // scroll.setVisibility(View.INVISIBLE);
        x=findViewById(R.id.genre);

        button3=findViewById(R.id.xxxx);

        button3.setVisibility(View.GONE);


        x.setText(cast.toLowerCase());

        adede=findViewById(R.id.adede);


        z=findViewById(R.id.backx);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        opti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
/*
        recyclerView = findViewById(R.id.yiyii);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(aps);


        loadata();



        valbum.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<doc> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        doc gigi=documentSnapshot.toObject(doc.class);

                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<doc> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        doc movie=movies.get(i);
                        if (!meme.contains(movie))
                            meme.add(movie);

                        if (movie.getName()!=null) {
                            if (!movie.getName().trim().toLowerCase().contains(cast.substring(0, a).trim().toLowerCase())) {
                                meme.remove(movie);
                            }

                            if (movie.getName().trim().toLowerCase().contains(cast.substring(a).trim().toLowerCase())) {
                                if (!meme.contains(movie))
                                    meme.add(movie);

                            }
                        }
                        if (others.size()>0){
                            RecyclerView view1 = findViewById(R.id.yiyii);

                            layst adapter44 = new layst(Main13Activity.this, meme);
                            GridLayoutManager glm = new GridLayoutManager(Main13Activity.this, 2);
                            view1.setLayoutManager(glm);
                            view1.setAdapter(adapter44);

                        }




                    }

                }
            }
        });

*/



        series.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(series.getId());
                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        Movie movie=movies.get(i);

                        if (movie.getName().trim().toLowerCase().contains(cast.substring(a).trim().toLowerCase())){
                            if (!otherx.contains(movie)) {
                                otherx.add(movie);
                            }
                        }

                        if (otherx.size()>0){
                            scroll.setVisibility(View.VISIBLE);
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.fifix);
                            view1.setHasFixedSize(true);
                            GridLayoutManager glm = new GridLayoutManager(Main13Activity.this, 2);
                            view1.setLayoutManager(glm);
                            MyItemAdapterxxggg22 adapter = new MyItemAdapterxxggg22(Main13Activity.this, otherx);
                            view1.setAdapter(adapter);

                        }




                    }

                }
            }
        });

        korean.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<Movie> otherx=new ArrayList<>();
                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);
                      //  gigi.setVideo(documentSnapshot.getId());
                      //  gigi.setUploader(korean.getId());
                        movies.add(gigi);
                    }
                    int size=movies.size();

                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        Movie movie=movies.get(i);

                        if (movie.getName().trim().toLowerCase().contains(cast.substring(a).trim().toLowerCase())){
                            if (!otherx.contains(movie)) {
                                otherx.add(movie);
                            }
                        }

                        if (otherx.size()>0){
                            scroll.setVisibility(View.VISIBLE);
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.yiyii);
                            view1.setHasFixedSize(true);
                            GridLayoutManager glm = new GridLayoutManager(Main13Activity.this, 2);

                            view1.setLayoutManager(glm);
                            MyItemAdapterxxggg22 adapter = new MyItemAdapterxxggg22(Main13Activity.this, otherx);
                            view1.setAdapter(adapter);

                        }




                    }

                }
            }
        });



    }
    /*
    private void loadata() {



        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @SuppressLint("WrongConstant")



            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Movie> others = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie movie = documentSnapshot.toObject(Movie.class);
                        movie.setVideo(documentSnapshot.getId());
                        movie.setUploader(medieval.getId());

                        if (movie.getName().trim().toLowerCase().contains(cast.substring(a).trim().toLowerCase())){
                            if (!others.contains(movie))
                                others.add(movie);
                            xax.add(movie);

                        }


                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                xcx.setVisibility(View.VISIBLE);
                                query = medieval.orderBy("timestamp", Query.Direction.ASCENDING).startAfter(documentSnapshot).limit(20);
                                loadata();
                            }
                        });



                    }

                    if (others.size()>0){
                        adede.setVisibility(View.INVISIBLE);

                        scroll.setVisibility(View.VISIBLE);
                        xcx.setVisibility(View.GONE);
                    }
                    if (others.size()>20){
                        button3.setVisibility(View.VISIBLE);
                        xbb.setVisibility(View.VISIBLE);
                    }

                    if (others.size()==0){
                        adede.setVisibility(View.INVISIBLE);

                        xcx.setVisibility(View.GONE);
                        //   xcx.setVisibility(View.GONE);
                    }
                    if (xax.size()==0){
                        zas.setVisibility(View.VISIBLE);
                    }

                    aps.setItems(others);
                    aps.notifyDataSetChanged();

                    isloading=false;


                }
                if (task.isSuccessful()) {

                    //Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }


            }




        });


    }

*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }

}
