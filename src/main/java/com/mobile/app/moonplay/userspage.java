package com.mobile.app.moonplay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class userspage extends AppCompatActivity {
    private  static  int splashy=1000;

    EditText editText,editname,editgenre;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBar;
    public static  int AUTH_REQUEST_CODE=7192;
    CardView cardView;
    private FirebaseAuth firebaseAuth;

    Button button3;
    private FirebaseAuth.AuthStateListener listener;
    private List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setTheme(R.style.hihiggi);

        setContentView(R.layout.userspage);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();



        button3=findViewById(R.id.button3);

        progressBar=findViewById(R.id.progressBar);
        editname=findViewById(R.id.name);

        cardView=findViewById(R.id.vx);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = preferences.edit();
        boolean hasLoggedIn=preferences.getBoolean("hasLoggedIn",false);
        if (hasLoggedIn){
            Intent intent=new Intent(userspage.this,Main7Activity.class);
            startActivity(intent);
            userspage.this.finish();

        }else {


            db.collection("subusers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    final List<usersx> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        usersx gigi = documentSnapshot.toObject(usersx.class);
                        movies.add(gigi);

                    }
                    int size = movies.size();
                    ArrayList movieArray = new ArrayList();
                    for (int i = size - 1; i >= 0; i--) {

                        usersx movie = movies.get(i);
                        if (!movieArray.contains(movie.getId()))
                            movieArray.add(movie.getId());

                    }
                    if (!movieArray.contains(user.getUid())) {
                        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

                        Date date = new Date();
                        sdf.format(date);
                        Timestamp timestamp = new Timestamp(date);
                        usersx xx = new usersx("Never subscribed before", false, firebaseAuth.getUid().toString(), timestamp);
                        db.collection("subusers").document(firebaseAuth.getUid()).set(xx).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                editor.putBoolean("hasLoggedIn", true);
                                editor.commit();


                                Intent intent = new Intent(userspage.this, Main7Activity.class);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                userspage.this.finish();
                            }

                        });
                    } else {
                        editor.putBoolean("hasLoggedIn", true);
                        editor.commit();


                        Intent intent = new Intent(userspage.this, Main7Activity.class);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        userspage.this.finish();
                    }
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
