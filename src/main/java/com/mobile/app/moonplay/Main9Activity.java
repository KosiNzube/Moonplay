package com.mobile.app.moonplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Main9Activity extends AppCompatActivity {
    private  static  int splashy=1500;
    TextView name, genre, length,five,watch,textView,adede;
    ImageView imageView;
    ProgressBar progressBar;
    int mio;

    ImageView imageView13;
    ArrayList<Streampostion> watchxx;
    boolean wat;
    RelativeLayout riri;
    ListView listView;
    RecyclerView recyclerView;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        adede=findViewById(R.id.adede);
        adede.setVisibility(View.INVISIBLE);

        watch=findViewById(R.id.playlist);
        listView=findViewById(R.id.grid);
        listView.setVisibility(View.INVISIBLE);

        wat=true;
        imageView13=findViewById(R.id.re);

        genre=findViewById(R.id.genre);
        progressBar=findViewById(R.id.adedex);
        progressBar.setVisibility(View.VISIBLE);

        five=findViewById(R.id.playlist);
        genre.setText(" Watchlist ");

        textView=findViewById(R.id.back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        five.setText(" refresh ");
        five.setTextColor(Color.DKGRAY);

        Main7Activity.db.collection("subusers").document(Main7Activity.user.getUid()).collection("watchl").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                }else {
                    List<Movie> movies = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : value) {

                        Movie gigi = doc.toObject(Movie.class);
                        //  gigi.setVideo(doc.getId());
                        if (!movies.contains(gigi)){
                            movies.add(gigi);
                        }

                    }

                    if (movies.size() > 0) {
                        progressBar.setVisibility(View.INVISIBLE);


                        //  Collections.shuffle(movieArrayList);
                        RecyclerView trendingRecy1=findViewById(R.id.fifi);
                        GridLayoutManager glm = new GridLayoutManager(Main9Activity.this, 2);
                        trendingRecy1.setLayoutManager(glm);
                        MyItemAdapterxxggg22 adapter1 = new MyItemAdapterxxggg22(Main9Activity.this, movies);
                        trendingRecy1.setAdapter(adapter1);
                    }


                }
            }
        });




        /*

        SharedPreferences sharedPreferences=getSharedPreferences("sharedx",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("taskx",null);
        Type type=new TypeToken<ArrayList<Streampostion>>(){}.getType();
        watchxx=gson.fromJson(json,type);

        if (watchxx==null){
            watchxx=new ArrayList<>();
        }




        if (watchxx.size()>0) {
            RecyclerView view1 = (RecyclerView) findViewById(R.id.fifi);
            view1.setHasFixedSize(true);
            view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            view1.setItemAnimator(new DefaultItemAnimator());
            streampos7 adapter = new streampos7(this, watchxx);
            view1.setAdapter(adapter);
        }



         */

    }

    private void imple() {
        if (wat) {
            wat = false;


            listView.setVisibility(View.VISIBLE);


        } else {
            wat = true;
            listView.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);

    }
}