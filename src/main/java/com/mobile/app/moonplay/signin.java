package com.mobile.app.moonplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class signin extends AppCompatActivity {
Button signups;
    EditText name,password;
    TextView yuy;
    Button loginn;
    ImageView vivi;
    private FirebaseAuth mAuth;
    String num;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    ProgressDialog dialog;
    String mVerificationId;
    FirebaseUser user;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("userx");
    userx usery;
    users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        FirebaseApp.initializeApp(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }





        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     userx action.
                //     Log.d(TAG, "onVerificationCompleted:" + credential);
                Toast.makeText(signin.this, "Verified", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //  Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(signin.this, "Please check your number", Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(signin.this, "mmmm",
                            Toast.LENGTH_SHORT).show();
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the userx to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //    Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };




        Intent intent = getIntent();
        num=intent.getExtras().getString("num");
        yuy=findViewById(R.id.yuy);
        yuy.setText(num);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                num,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                signin.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        mAuth.setLanguageCode("fr");






        signups=findViewById(R.id.signup);
        signups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vivi=findViewById(R.id.thumbnail);


        mAuth = FirebaseAuth.getInstance();


        name=findViewById(R.id.name);
        password=findViewById(R.id.pass);

        loginn = findViewById(R.id.submit);

        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = name.getText().toString();
                String pass = password.getText().toString();
                if (newUser.length() > 0) {


                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, name.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                   // showProgressBar();


                }else{
                    Toast.makeText(signin.this,"Check your code",Toast.LENGTH_SHORT).show();
                }


                /*



                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String newUser = name.getText().toString();
                String pass = password.getText().toString();


                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor1=sharedPreferences.edit();

                editor1.putString("username",newUser);
                editor1.putString("id",pass);
                editor1.commit();

                if (newUser.length() > 0 && pass.length() > 0) {
                    users users=new users(name.getText().toString(),true,password.getText().toString());
                    collectionReference.add(users);


                    Intent intent = new Intent(login.this, splashActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.commit();

                }else{
                    Toast.makeText(login.this,"Your name please",Toast.LENGTH_SHORT).show();
                }
                */

            }

        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in userx's information
                            //   Log.d(TAG, "signInWithCredential:success");

                             user = task.getResult().getUser();



                            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<users> movies = new ArrayList<>();
                                        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                                            users gigi = documentSnapshot.toObject(users.class);
                                            movies.add(gigi);
                                        }
                                        int size = movies.size();

                                        ArrayList movieArray=new ArrayList();
                                        for (int i = size - 1; i >= 0; i--) {

                                            users = new users("ooo", true, user.getUid(),"","",null,null,null);
                                            if (!movieArray.contains(users))

                                                collectionReference.document(user.getUid()).set(users);

                                        }



                                    }                if (!task.isSuccessful()) {
                                        Toast.makeText(signin.this, "Could not connect", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                            updateUI(user);


                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void hideProgressBar() {
        dialog.dismiss();
    }

    private void showProgressBar() {
        dialog= ProgressDialog.show(signin.this, "",
                "Please wait...", true);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);



    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {



            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor1=sharedPreferences.edit();

            editor1.putString("id",user.getUid());


            Intent intent=new Intent(signin.this,Main7Activity.class);
            startActivity(intent);
            signin.this.finish();


        }



    }

}
