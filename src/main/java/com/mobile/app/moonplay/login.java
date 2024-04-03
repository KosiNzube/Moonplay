
package com.mobile.app.moonplay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    EditText name,passwordx;
    Button loginn;
    boolean substate;
    ImageView backward;
    Button signups;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    ProgressDialog dialog;
    ImageView vivi;
    private FirebaseAuth mAuth;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    ActionCodeSettings actionCodeSettings;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private List<users> usernames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        passwordx=findViewById(R.id.pass);

        loginn = findViewById(R.id.submit);
        String newUser = name.getText().toString();
        String pass = passwordx.getText().toString();
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
                Toast.makeText(login.this, "Verified", Toast.LENGTH_SHORT).show();
                //   signInWithPhoneAuthCredential(credential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //  Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(login.this, "Please check your number", Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(login.this, "mmmm",
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


        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (passwordx.length()>8) {
                    Intent intent = new Intent(login.this, signin.class);
                    intent.putExtra("num", "+234" + passwordx.getText().toString());
                    startActivity(intent);

                }
                else {
                    Toast.makeText(login.this, "Invalid number",
                            Toast.LENGTH_SHORT).show();
                }
                //showProgressBar();
            }
        });









//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, name.getText().toString());




        signups=findViewById(R.id.signup);
        signups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, name.getText().toString());

                //   signInWithPhoneAuthCredential(credential);


                Intent intent=new Intent(login.this,Main7Activity.class);
                startActivity(intent);
                login.this.finish();
            }
        });


/*
        SharedPreferences sett=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean hasLoggedIn=sett.getBoolean("hasLoggedIn",false);
        if (hasLoggedIn){
            Intent intent=new Intent(login.this,Main7Activity.class);
            startActivity(intent);
            login.this.finish();

        }*/
        vivi=findViewById(R.id.thumbnail);
        Picasso.get().load(R.drawable.screen).fit().centerCrop().into(vivi);

        backward=findViewById(R.id.backward);
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });









    }



    private void hideProgressBar() {
        dialog.dismiss();
    }

    private void showProgressBar() {
        dialog= ProgressDialog.show(login.this, "",
                "Please wait...", true);

    }



    private void checkForMultiFactorFailure(Exception e) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        if (e instanceof FirebaseAuthMultiFactorException) {
            Intent intent = new Intent();
            MultiFactorResolver resolver = ((FirebaseAuthMultiFactorException) e).getResolver();
            intent.putExtra("EXTRA_MFA_RESOLVER", resolver);
            //   setResult(MultiFactorActivity.RESULT_NEEDS_MFA_SIGN_IN, intent);
            finish();
        }
    }


    private void sendEmail() {
        // Disable button


        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button


                        if (task.isSuccessful()) {
                            Toast.makeText(login.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(login.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {


            Intent intent=new Intent(login.this,Main7Activity.class);
            startActivity(intent);
            login.this.finish();


        }



    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);



    }



}
