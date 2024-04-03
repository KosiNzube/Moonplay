package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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

public class more extends AppCompatActivity {

    String cast;
    TextView x,y,z;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> others=new ArrayList<>();
    ProgressBar adede;
    private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");
    private CollectionReference korean = db.collection("Korean");
    private CollectionReference bolly=db.collection("Bollywood");
    private CollectionReference nolly=db.collection("Nollywood");
    private CollectionReference medieval=db.collection("Italy");
    int a;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);

        TextView textView=findViewById(R.id.backx);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        medieval.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(medieval.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    for (int i = size - 8; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);

                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayList.remove(movie);
                            }
                        }
                    }
                    if (movieArrayList.size() > 0) {



                        RecyclerView view1 = (RecyclerView) findViewById(R.id.fifi);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(more.this, LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        popular adapter = new popular(more.this, movieArrayList);
                        view1.setAdapter(adapter);

                    }

                }
                if (!task.isSuccessful()){
                   // Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });

        }

    }

