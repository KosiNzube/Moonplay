package com.mobile.app.moonplay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class singlemaker2 extends Fragment  {
    String descr,name,genre;
    ImageView imageView;
    private static final int PICK=22;
    Button button;
    Uri videouri;
    ListView listView;
    private ArrayList arrayList=new ArrayList();
    String genree;
    Uri videourix;

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
    EditText editText,editname,editgenre;
    private static  final int MY_READ=101;
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

        gallery_number=view.findViewById(R.id.gallery_number);
        recyclerView=view.findViewById(R.id.recyclerView);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_READ);
        }else {

        }













    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_READ){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){


            }else {
                Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.singlemaker, container, false);


    }



    ActivityResultLauncher activityResultLauncher=new ActivityResultLauncher() {
        @Override
        public void launch(Object input, @Nullable ActivityOptionsCompat options) {


        }

        @Override
        public void unregister() {

        }

        @NonNull
        @Override
        public ActivityResultContract getContract() {
            return null;
        }
    };





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
