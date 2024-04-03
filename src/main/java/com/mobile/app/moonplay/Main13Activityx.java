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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class Main13Activityx extends AppCompatActivity {

    String cast,moviename;
    TextView x,y,z,sese;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> others=new ArrayList<>();
    ArrayList<doc> meme=new ArrayList<>();

    ArrayList<Movie> otherx=new ArrayList<>();
    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");
    private CollectionReference shorts = db.collection("shorts");
    private CollectionReference valbum = db.collection("Valbum");
    private CollectionReference korean = db.collection("Spanish");
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
        cast=intent.getExtras().getString("name");
        moviename=intent.getExtras().getString("moviename");




        x.setText(cast);

        adede=findViewById(R.id.adede);


        z=findViewById(R.id.backx);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        korean.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);

                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<quick> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        Movie movie=movies.get(i);


                        if (moviename.toLowerCase().trim().contains(movie.getName().trim().toLowerCase())){
                            if (!others.contains(movie))
                                others.add(movie);

                        }


                        if (others.size()>0){
                            GridLayoutManager glm = new GridLayoutManager(Main13Activityx.this, 2);

                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.yiyii);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(glm);
                            MyItemAdapterxxggg adapter = new MyItemAdapterxxggg(Main13Activityx.this, others);
                            view1.setAdapter(adapter);

                        }




                    }

                }
            }
        });


        /*
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

                        if (moviename.toLowerCase().trim().contains(movie.getName().trim().toLowerCase())){
                            if (!otherx.contains(movie))
                                otherx.add(movie);

                        }

                        if (otherx.size()>0){
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.fifix);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(Main13Activityx.this, LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            popular adapter = new popular(Main13Activityx.this, otherx);
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

                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(korean.getId());
                        movies.add(gigi);
                    }
                    int size=movies.size();

                    ArrayList<Movie> otherx=new ArrayList<>();
                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        Movie movie=movies.get(i);

                        if (moviename.toLowerCase().trim().contains(movie.getName().trim().toLowerCase())){
                            if (!otherx.contains(movie))
                                otherx.add(movie);

                        }

                        if (otherx.size()>0){
                            adede.setVisibility(View.INVISIBLE);
                            RecyclerView view1 = (RecyclerView) findViewById(R.id.yiyii);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(Main13Activityx.this, LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            popular adapter = new popular(Main13Activityx.this, otherx);
                            view1.setAdapter(adapter);

                        }




                    }

                }
            }
        });

*/




    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }

}
