package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class genact extends AppCompatActivity {

    String cast;
    TextView x,y,z;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> others=new ArrayList<>();
    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");
    private CollectionReference medieval=db.collection("Spanish");
    int a;

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


        x=findViewById(R.id.genre);

        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");

        x.setText(cast);

        adede=findViewById(R.id.adede);


        z=findViewById(R.id.backx);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        medieval.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(medieval.getId());
                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        Movie movie=movies.get(i);

                        if (!others.contains(movie))
                            if (cast.toLowerCase().contains("romance")) {
                                if (movie.getGenre().toLowerCase().trim().contains("romance")) {
                                    others.add(movie);
                                }


                            }
                        if (cast.toLowerCase().contains("action")) {
                            if (movie.getGenre().toLowerCase().trim().contains("action")) {
                                others.add(movie);
                            }

                        }

                        if (cast.toLowerCase().contains("sci")) {
                            if (movie.getGenre().toLowerCase().trim().contains("sci")) {
                                others.add(movie);
                            }

                        }

                        if (cast.toLowerCase().contains("horror")) {
                            if (movie.getGenre().toLowerCase().trim().contains("horror")) {
                                others.add(movie);
                            }

                        }
                        if (cast.toLowerCase().contains("drama")) {
                            if (movie.getGenre().toLowerCase().trim().contains("drama")) {
                                others.add(movie);
                            }

                        }

                        if (cast.toLowerCase().contains("business")) {
                            if (movie.getGenre().toLowerCase().trim().contains("business")) {
                                others.add(movie);
                            }

                        }
                        if (cast.toLowerCase().contains("comedy")) {
                            if (movie.getGenre().toLowerCase().trim().contains("comedy")) {
                                others.add(movie);
                            }

                        }


                        if (others.size()>0){
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.fifi);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(genact.this, LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            popular adapter = new popular(genact.this, others);
                            view1.setAdapter(adapter);

                        }




                    }

                }
            }
        });
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

                        if (!others.contains(movie))
                            if (cast.toLowerCase().contains("romance")) {
                                if (movie.getGenre().toLowerCase().trim().contains("romance")) {
                                    others.add(movie);
                                }
                            }
                        if (cast.toLowerCase().contains("action")) {
                            if (movie.getGenre().toLowerCase().trim().contains("action")) {
                                others.add(movie);
                            }
                        }
                        if (cast.toLowerCase().contains("horror")) {
                            if (movie.getGenre().toLowerCase().trim().contains("horror")) {
                                others.add(movie);
                            }

                        }
                        if (cast.toLowerCase().contains("business")) {
                            if (movie.getGenre().toLowerCase().trim().contains("business")) {
                                others.add(movie);
                            }

                        }
                        if (cast.toLowerCase().contains("teen")) {
                            if (movie.getGenre().toLowerCase().trim().contains("teen")) {
                                others.add(movie);
                            }

                        }
                        if (cast.toLowerCase().contains("comedy")) {
                            if (movie.getGenre().toLowerCase().trim().contains("comedy")) {
                                others.add(movie);
                            }

                        }

                        if (others.size()>0){
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.fifi);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(genact.this, LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            popular adapter = new popular(genact.this, others);
                            view1.setAdapter(adapter);

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
