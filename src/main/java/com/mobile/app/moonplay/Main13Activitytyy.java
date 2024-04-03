package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;


public class Main13Activitytyy extends AppCompatActivity {

    String cast;
    TextView x,y,z,xo,thriller;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference medieval = db.collection("comp");
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13tyy);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        x=findViewById(R.id.genre);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        //      Date date= (Date) tutorialsObject.get("date");

        Date date1=new Date();
        sdf.format(date1);



        thriller=findViewById(R.id.genrex);
        Timestamp timestamp = new Timestamp(date1);






        z=findViewById(R.id.backx);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        series.orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<SeriesObject> movieArrayList = new ArrayList<>();

                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        SeriesObject movie = documentSnapshot.toObject(SeriesObject.class);


                        movieArrayList.add(movie);
                    }


                    if (movieArrayList.size() > 0) {

                        thriller.setVisibility(View.INVISIBLE);
                        RecyclerView trendingRecy1=findViewById(R.id.yiyii);
                        SeriesAd2 adapter1 = new SeriesAd2(Main13Activitytyy.this, movieArrayList);
                        GridLayoutManager glm = new GridLayoutManager(Main13Activitytyy.this, 2);
                        trendingRecy1.setLayoutManager(glm);
                        trendingRecy1.setAdapter(adapter1);



                    }else {
                        thriller.setVisibility(View.VISIBLE);
                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(Main13Activitytyy.this,"Could not connect",Toast.LENGTH_SHORT).show();
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
