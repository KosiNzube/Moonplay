package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class genact2quick extends AppCompatActivity {
    boolean isloading=false;
    String cast;
    TextView x,y,z;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference medieval=db.collection("shorts");
    int a;
    popularyz aps;
    Query query;
    TextView zas;
    ArrayList xax=new ArrayList();
    Button button3,xbb;
    RecyclerView recyclerView;
    ScrollView scroll;
    ProgressBar xcx;
    TextView sese;
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


        query = medieval.orderBy("timestamp").limit(15);

        zas=findViewById(R.id.zas);

        xcx=findViewById(R.id.prozzz);

        aps=new popularyz(genact2quick.this);

        xbb=findViewById(R.id.fix);


        opti=findViewById(R.id.backward);

        scroll=findViewById(R.id.scroll);
        scroll.setVisibility(View.INVISIBLE);
        x=findViewById(R.id.genre);

        button3=findViewById(R.id.xxxx);

        button3.setVisibility(View.GONE);
        sese=findViewById(R.id.genre);


        sese.setText(" recently added ");
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


        recyclerView = findViewById(R.id.yiyii);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
                    ArrayList<quick> others = new ArrayList<>();

                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        quick movie = doc.toObject(quick.class);

                        if (!others.contains(movie)) {
                            others.add(movie);
                            xax.add(movie);
                        }
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

                    isloading=false;



                }
            }
        });



        /*
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


            @SuppressLint("WrongConstant")



            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    zas.setVisibility(View.INVISIBLE);
                    ArrayList<quick> others = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        quick movie = documentSnapshot.toObject(quick.class);

                        if (!others.contains(movie)) {
                            others.add(movie);
                            xax.add(movie);
                        }
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                xcx.setVisibility(View.VISIBLE);
                                query = medieval.orderBy("timestamp").startAfter(documentSnapshot).limit(18);
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

                    isloading=false;


                }
                if (task.isSuccessful()) {

                    //Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
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
