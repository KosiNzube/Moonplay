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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class storyx extends AppCompatActivity {
  private static String dirPath;

  Button buttonOne, buttonTwo;
  int downloadIdOne, downloadIdTwo;
  ProgressBar progressBarOne, progressBarTwo;
  TextView textViewProgressOne, textViewProgressTwo,newalbum;
  String name;
  String URL2;
  FirebaseFirestore db;

  ImageView imageView;
  ProgressBar progressBar;

  TextView textView;
  private FirebaseAuth firebaseAuth;
  FirebaseUser user;
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


    Intent intent=getIntent();
    String x=intent.getExtras().getString("id");
    String y=intent.getExtras().getString("name");


    imageView=findViewById(R.id.backlnx);


    textView=findViewById(R.id.genre);
    textView.setText(y);



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

    CollectionReference valbum = db.collection("instant");






  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

}
