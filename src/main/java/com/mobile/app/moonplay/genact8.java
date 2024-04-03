package com.mobile.app.moonplay;

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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class genact8 extends AppCompatActivity {
    boolean isloading=false;
    String cast;
    TextView x,y,z;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference medieval=db.collection("Spanish");
    int a;
    genreplaylist aps;
    Query query;
    TextView zas;
    ArrayList xax=new ArrayList();
    Button button3,xbb;
    RecyclerView recyclerView;
    ScrollView scroll;
    ProgressBar xcx;
    ImageView opti;
    private String tags;
    private String id;

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


        query = medieval.orderBy("timestamp", Query.Direction.DESCENDING).limit(55);




        zas=findViewById(R.id.zas);

        xcx=findViewById(R.id.prozzz);

        aps=new genreplaylist(genact8.this);

        xbb=findViewById(R.id.fix);


        opti=findViewById(R.id.backward);

        scroll=findViewById(R.id.scroll);
        scroll.setVisibility(View.INVISIBLE);
        x=findViewById(R.id.genre);

        button3=findViewById(R.id.xxxx);

        button3.setVisibility(View.GONE);

        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");
        tags=intent.getExtras().getString("tags");
        id=intent.getExtras().getString("id");




        x.setText("genre: "+cast.toLowerCase());

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
        GridLayoutManager glm = new GridLayoutManager(genact8.this, 2);

        recyclerView = findViewById(R.id.yiyii);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(aps);


         loadata();


    }
    private void loadata() {


        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    zas.setVisibility(View.INVISIBLE);
                    ArrayList<Movie> others = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Movie movie = doc.toObject(Movie.class);
                        movie.setId(doc.getId());
                        movie.setUploader(cast);
                      //  movie.setVideo(doc.getId());
                       // movie.setUploader(medieval.getId());


                        if (!others.contains(movie))
                        others.add(movie);
                        xax.add(movie);

                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                xcx.setVisibility(View.VISIBLE);
                                query = medieval.orderBy("timestamp").startAfter(doc).limit(18);
                                loadata();
                            }
                        });


                    }

                    if (others.size()>0){
                        adede.setVisibility(View.INVISIBLE);
                        zas.setVisibility(View.INVISIBLE);
                        scroll.setVisibility(View.VISIBLE);
                        xcx.setVisibility(View.GONE);
                    }
                    if (xax.size()>5){
                        xbb.setVisibility(View.VISIBLE);
                        button3.setVisibility(View.VISIBLE);
                    }

                    if (others.size()==0){
                        adede.setVisibility(View.INVISIBLE);

                        xcx.setVisibility(View.GONE);
                        //   xcx.setVisibility(View.GONE);
                    }
                    if (xax.size()==0){
                        zas.setVisibility(View.VISIBLE);
                        loadata();
                    }

                    aps.setItems(others);
                    aps.notifyDataSetChanged();

                    isloading=false;                }
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
