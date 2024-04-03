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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class storyy extends AppCompatActivity {
  private static String dirPath;

  Button buttonOne, buttonTwo;
  int downloadIdOne, downloadIdTwo;
  ProgressBar progressBarOne, progressBarTwo;
  TextView textViewProgressOne, textViewProgressTwo,newalbum;
  String name;
  String description,up,video;
  FirebaseFirestore db;
  laysq adapter;
  ImageView imageView;
  ProgressBar progressBar;

  ArrayList movieArray=new ArrayList();
  Button textView;
  private FirebaseAuth firebaseAuth;
  CollectionReference collectionReference;
  FirebaseUser user;
  TextView genre,noo;
  @SuppressLint("WrongConstant")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0, 0);
    super.onCreate(savedInstanceState);
    setTheme(R.style.hihiggi);
    setContentView(R.layout.albumx);
    if (Build.VERSION.SDK_INT>=21){
      setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
      getWindow().setStatusBarColor(Color.rgb(5,5,9));
      getWindow().setNavigationBarColor(Color.BLACK);

    }

    noo=findViewById(R.id.noo);


    noo.setVisibility(View.INVISIBLE);


    genre=findViewById(R.id.genre);




    imageView=findViewById(R.id.backlnx);

    buttonOne=findViewById(R.id.button3);

    buttonOne.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent=new Intent(storyy.this,uxx.class);
        startActivity(intent);
      }
    });

    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });


    db=FirebaseFirestore.getInstance();
    progressBar=findViewById(R.id.progressBar);
    firebaseAuth=FirebaseAuth.getInstance();
    user= firebaseAuth.getCurrentUser();

    Intent intent=getIntent();
    description=intent.getExtras().getString("dex");
    up=intent.getExtras().getString("upl");
    video=intent.getExtras().getString("video");
    genre.setText(description);

    buttonOne.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent=new Intent(storyy.this,uxx.class);

        intent.putExtra("video",video);
        intent.putExtra("upl",up);
        startActivity(intent);
      }
    });


    try{
      FirebaseFirestore db = FirebaseFirestore.getInstance();
      collectionReference=db.collection(up);
      collectionReference=collectionReference.document(video).collection("parts");
      collectionReference.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
          if (task.isSuccessful()) {


            final List<instV> movies = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
              instV gigi = documentSnapshot.toObject(instV.class);
              gigi.setPoster(documentSnapshot.getId());

              movies.add(gigi);

            }



            int size = movies.size();

            for (int i = size - 1; i >= 0; i--) {

              instV movie = movies.get(i);


              if (!movieArray.contains(movie))
                movieArray.add(movie);

                        /*
                       if (movie.getType().contains("3")){
                           movieArrayList.remove(movie);
                       }
                       */
            }
            if (movieArray.size() > 0) {

              progressBar.setVisibility(View.INVISIBLE);

              RecyclerView view1=findViewById(R.id.fifi);
               adapter = new laysq(storyy.this, movieArray, new laysq.xx() {
                @Override
                public void ondel(int path, String x) {
                  collectionReference.document(x).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
              GridLayoutManager glm = new GridLayoutManager(storyy.this, 2);
              view1.setLayoutManager(glm);
              view1.setAdapter(adapter);


            }

            if (movieArray.isEmpty()){
              progressBar.setVisibility(View.INVISIBLE);
              noo.setVisibility(View.VISIBLE);
            }

            else {



            }


          }
          if (!task.isSuccessful()) {
            Toast.makeText(storyy.this, "Could not connect", Toast.LENGTH_SHORT).show();
          }
        }
      });
    }catch (OutOfMemoryError e){
      e.printStackTrace();
    }






  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }
  @Override
  public void onResume()
  {  // After a pause OR at startup
    super.onResume();

    //Refresh your stuff here
  }
}
