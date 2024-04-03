package com.mobile.app.moonplay;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;


public class uxo extends AppCompatActivity {


    VideoPlayerRecyclerView mRecyclerView;
    Button back;
    TextView textView, albumx, descr, plays;
    ArrayList<instantv> instant = new ArrayList<>();
    ArrayList<instantv> instantx = new ArrayList<>();
    public static String imagex;
    public static String namex;
    public String genre;
    CardView vx;
    ImageView x;
    String id;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference meme = db.collection("instvid");
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    insadapter insadapter;
    public static String des;
    ArrayList movieArray;
    private BroadcastReceiver broadcastReceiver;
    private boolean mBound = false;
    MusicServicex musicService;
    String path;
    TextView textViewx;
    ShapeableImageView imageView;
    int size;
    EditText editText;
    Button got,uploader;
    StorageReference storageReference;
    ImageView imageViewx;

    String homepoint;
    ProgressBar progressBar;
    FirebaseUser user;
    private static final int PICKx=1;
    VideoView videoView;
    MediaController mediaController;
    Uri videouri;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicServicex.MusicBinder mServiceBinder = (MusicServicex.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ux);
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

        vx = findViewById(R.id.vx);

        Intent intent = getIntent();

        path=intent.getExtras().getString("path");


        mediaController=new MediaController(this);

        user = FirebaseAuth.getInstance().getCurrentUser();


        videoView=findViewById(R.id.videoview);




        db=FirebaseFirestore.getInstance();

        progressBar=findViewById(R.id.pro);
        got=findViewById(R.id.got);
        storageReference= FirebaseStorage.getInstance().getReference("shorts").child("instant");


        editText = findViewById(R.id.des);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                homepoint = editText.getText().toString();



                return true;
            }
        });


        progressBar.setVisibility(View.INVISIBLE);
        got.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chovideo();
            }
        });






        vx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uplvideo();


            }
        });
        videouri=Uri.fromFile(new File(path));
        videoView.setVideoURI(videouri);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
        imageViewx=findViewById(R.id.stu);
        imageViewx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        //  mRecyclerView.setLayoutManager(new speedy(this, speedy.VERTICAL, false));
        //  SnapHelper snapHelper = new PagerSnapHelper();
        //  snapHelper.attachToRecyclerView(mRecyclerView);
    }




    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();


        overridePendingTransition(0,0);
    }

    private void chovideo() {
        Intent intent=new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICKx);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICKx&&resultCode==RESULT_OK &&data!=null&&data.getData()!=null);


        Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_SHORT).show();
    }
    private void uplvideo(){
        progressBar.setVisibility(View.VISIBLE);
        if (videouri!=null){
            StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileext(videouri));
            reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    progressBar.setVisibility(View.INVISIBLE);
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

                    Date date = new Date();
                    sdf.format(date);
                    Timestamp timestamp = new Timestamp(date);
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            instV instant=new instV(uri.toString(),editText.getText().toString(),user.getUid(),"Starboy",user.getDisplayName(),0,"","",timestamp);
                           // Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_SHORT).show();
                            upload(instant);

                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(uxo.this,"failed 1"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            Toast.makeText(uxo.this,"No video selected",Toast.LENGTH_SHORT).show();
        }
    }
    private void upload(instV instant) {



        db.collection("instvid").add(instant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();



                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"failed2"+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




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


}
