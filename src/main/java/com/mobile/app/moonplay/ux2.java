package com.mobile.app.moonplay;

import android.content.ContentResolver;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class ux2 extends Fragment  {
    String descr,name,genre;
    ImageView imageView;
    private static final int PICK=22;
    Button button;
    Uri videouri;
    ListView listView;
    private ArrayList arrayList=new ArrayList();
    String genree;

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    TextView gallery_number;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    StorageReference storageReference;
    Uri y;
    ProgressBar progressBar;
    TextView tecat;
    FirebaseUser user;
    String path;
    EditText editText,editname,editgenre;
    private static  final int MY_READ=101;
    String video,up;
    CardView vx;

    VideoView videoView;
    String homepoint;

    Button uploader;

    MediaController mediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }

        path=getArguments().getString("path");

        videouri=Uri.parse(path);
        uxx ux= (uxx) getActivity();

        video=ux.videox();

        up=ux.upx();


        vx = view.findViewById(R.id.vx);



        user = FirebaseAuth.getInstance().getCurrentUser();


        videoView=view.findViewById(R.id.videoview);
        mediaController=new MediaController(getActivity());
        videoView.setVideoURI(videouri);

       // videoView.setMediaController(mediaController);
        //mediaController.setAnchorView(videoView);
        videoView.start();

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!videoView.isPlaying()) {
                    videoView.start();
                }else {
                    videoView.pause();
                }
            }
        });



        db=FirebaseFirestore.getInstance();
        uploader=view.findViewById(R.id.up);
        progressBar=view.findViewById(R.id.pro);
        storageReference= FirebaseStorage.getInstance().getReference("shorts").child("instant");


        editText = view.findViewById(R.id.des);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                homepoint = editText.getText().toString();



                return true;
            }
        });


        progressBar.setVisibility(View.INVISIBLE);


        vx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uplvideo();


            }
        });



    }
    private void uplvideo(){
        progressBar.setVisibility(View.VISIBLE);
        if (videouri!=null){
            StorageReference reference=storageReference.child(System.currentTimeMillis()+"."+getFileext(videouri));
            reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    progressBar.setVisibility(View.INVISIBLE);

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

                            Date date = new Date();
                            sdf.format(date);
                            Timestamp timestamp = new Timestamp(date);
                            instV instant=new instV(uri.toString(),editText.getText().toString(),user.getUid(),"Starboy"," ",0,"","",timestamp);
                            Toast.makeText(getActivity(),uri.toString(),Toast.LENGTH_SHORT).show();
                            upload(instant);

                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"failed 1"+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            Toast.makeText(getActivity(),"No video selected",Toast.LENGTH_SHORT).show();
        }
    }
    private void upload(instV instant) {



        db.collection(up).document(video).collection("parts").add(instant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"failed2"+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }



    private String  getFileext(Uri videouri){
        ContentResolver contentResolver=getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(videouri));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.ux2, container, false);


    }








    @Override
    public void onPause() {
        super.onPause();
      //  mRecyclerView.onPausePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mRecyclerView.paye();
    }

}
