package com.mobile.app.moonplay;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class result extends AppCompatActivity {
String descr,name;
ImageView imageViewx;
    private static final int PICK_image=100;
    Button popular,genre;
    Uri videouri;
    ListView listView;

    String genree;
    ViewPager viewPager;
    maker pageViewAdapter;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    StorageReference storageReference;
    Uri y;
    Button button;
    MovieNames movieNames;
      ArrayList<MovieNames> arrayList=new ArrayList<>();
    ProgressBar progressBar;
    TextView tecat;
    FirebaseUser user;
EditText editText,editname,editgenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        if (Build.VERSION.SDK_INT >= 19) {
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);

            getWindow().setStatusBarColor(Color.rgb(5, 5, 9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }
        if (Build.VERSION.SDK_INT >= 21) {
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);

            getWindow().setStatusBarColor(Color.rgb(5, 5, 9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }

        ImageView imageView=findViewById(R.id.back);
        ImageView imageView1=findViewById(R.id.refresh);

        imageViewx=findViewById(R.id.itemImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(result.this,chroniclex.class);
                startActivity(intent);
            }
        });




        listView = findViewById(R.id.grid);
        arrayList.add(new MovieNames("Entertainment"));
        arrayList.add(new MovieNames("Soccer"));
        arrayList.add(new MovieNames("Business"));
        arrayList.add(new MovieNames("Story"));
        arrayList.add(new MovieNames("Music"));
        arrayList.add(new MovieNames("Fashion"));
        arrayList.add(new MovieNames("Memes"));
        arrayList.add(new MovieNames("Sports"));
        arrayList.add(new MovieNames("Romance"));
        arrayList.add(new MovieNames("Technology"));
        arrayList.add(new MovieNames("Lifestyle"));
        arrayList.add(new MovieNames("Crime"));
        arrayList.add(new MovieNames("Film"));
        arrayList.add(new MovieNames("Education"));
        arrayList.add(new MovieNames("Moments"));
        arrayList.add(new MovieNames("Gaming"));
        arrayList.add(new MovieNames("Anime"));
        arrayList.add(new MovieNames("Animals"));


        ListViewAdapter adapter = new ListViewAdapter(result.this,arrayList);
        listView.setAdapter(adapter);

        tecat=findViewById(R.id.tecat);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                genree= (String) arrayList.get(position).getMovieName();


                tecat.setText(genree+ "  ");
            }
        });





        db=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference("videoalbum");
        imageView=findViewById(R.id.itemImage);
        firebaseAuth=FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();
        progressBar=findViewById(R.id.pro);
        progressBar.setVisibility(View.INVISIBLE);

        button=findViewById(R.id.up);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chovideo();
            }
        });


        editText = findViewById(R.id.des);

        editname=findViewById(R.id.name);
        editgenre=findViewById(R.id.genre);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                descr = editText.getText().toString();
                return true;
            }
        });



        editgenre.setText("");

        editgenre.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uplvideo();

            }
        });

    }
    private void chovideo() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_image);
    }
    private void uplvideo(){

        if (isNetworkAvailable()==true) {
            if (genree != null) {
                if (!editname.getText().toString().matches("")) {
                    if (videouri != null) {
                        ProgressDialog pd = new ProgressDialog(result.this);

                        pd.setMessage("Please wait");
                        pd.show();
                        pd.setCancelable(true);


                        StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileext(videouri));


                        reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        if (genree.length() > 0) {

                                            final String ideaID = UUID.randomUUID().toString();
                                            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

                                            Date date = new Date();
                                            sdf.format(date);
                                            Timestamp timestamp = new Timestamp(date);
                                            doc instant = new doc(uri.toString(), editname.getText().toString(), editname.getText().toString(), 0, genree, "Anonymous","",0, timestamp, user.getUid());
                                            upload(instant);
                                        }
                                    }
                                });

                                pd.dismiss();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(result.this, "faiiled 1" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        Toast.makeText(result.this, "No cover image selected", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(result.this, "Write the album description", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(result.this, "Choose video category", Toast.LENGTH_SHORT).show();

            }
        }else {
            Toast.makeText(result.this, "Check your internet connection", Toast.LENGTH_SHORT).show();

        }
    }
    private void upload(doc instant) {
        db.collection("Valbum").add(instant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {


                Intent intent = new Intent(result.this, story.class);
                intent.putExtra("video",documentReference.getId());
                intent.putExtra("upl",db.collection("Valbum").getId());
                intent.putExtra("dex",editname.getText().toString());
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(result.this,"Upload failed",Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_image&&resultCode==RESULT_OK &&data.getData()!=null) {

            videouri = data.getData();

            Picasso.get().load(videouri).fit().centerCrop().into(imageViewx);


           // Toast.makeText(result.this, "Selected", Toast.LENGTH_SHORT).show();
        }
    }



    private String  getFileext(Uri videouri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(videouri));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }


}
