package com.mobile.app.moonplay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.mobile.app.moonplay.instantplay.setwinflag;



public class sub extends AppCompatActivity {

    private Button free,skip;
    private ImageView imageView;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.hihiggi);

        setContentView(R.layout.activity_sub);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);

            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }

        progressBar=findViewById(R.id.progressBar);
        firebaseAuth= FirebaseAuth.getInstance();

        imageView=findViewById(R.id.thumbnail);

        Picasso.get().load(R.drawable.screen).fit().centerCrop().into(imageView);
        free=findViewById(R.id.button3);
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    Intent intent=new Intent(sub.this,userspage.class);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                }else {
                    firebaseAuth.signInAnonymously()
                            .addOnCompleteListener(sub.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Intent intent=new Intent(sub.this,userspage.class);
                                        intent.addCategory(Intent.CATEGORY_HOME);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(sub.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });


                    /*
                    Intent intent=new Intent(sub.this,splash.class);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                     */
                }

            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if (user!=null){
            Intent o = new Intent(sub.this, userspage.class);
            startActivity(o);

        }
    }
}
