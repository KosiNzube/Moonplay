package com.mobile.app.moonplay;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class awayselect extends AppCompatActivity {
    ListView listView;
    private ArrayList arrayList=new ArrayList();
    private WebView webView;
    StorageReference storageReference;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    Uri videouri;
    String z;
    int size;
    EditText editText;
    Button got,uploader;
    ProgressBar progressBar;
    FirebaseFirestore db;
    String homepoint;
    FirebaseUser user;
    private static final int PICK=1;
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awayselect);


        mediaController=new MediaController(this);

        user = FirebaseAuth.getInstance().getCurrentUser();


        videoView=findViewById(R.id.videoview);




        db=FirebaseFirestore.getInstance();
        uploader=findViewById(R.id.up);
        progressBar=findViewById(R.id.pro);
        got=findViewById(R.id.got);
        storageReference= FirebaseStorage.getInstance().getReference("shorts").child("instant");


        editText = findViewById(R.id.des);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                homepoint = editText.getText().toString();
                Intent intent1=new Intent(awayselect.this,awayselect.class);
                startActivity(intent1);


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
        
        uploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uplvideo();
            }
        });





    }
    private void chovideo() {
        Intent intent=new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK&&resultCode==RESULT_OK &&data!=null&&data.getData()!=null);

        videouri=data.getData();
        videoView.setVideoURI(videouri);

        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_SHORT).show();
    }

    private String  getFileext(Uri videouri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(videouri));
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
                            instV instant=new instV(uri.toString(),editText.getText().toString(),user.getUid(),"Starboy"," ",0,"","",timestamp);
                            Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_SHORT).show();
                            upload(instant);

                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(awayselect.this,"failed 1"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            Toast.makeText(awayselect.this,"No video selected",Toast.LENGTH_SHORT).show();
        }
    }


    private void upload(instV instant) {
        db.collection("instvid").add(instant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"failed2"+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
