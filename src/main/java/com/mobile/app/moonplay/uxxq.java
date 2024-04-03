package com.mobile.app.moonplay;

import android.content.BroadcastReceiver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;


public class uxxq extends AppCompatActivity {


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
    String up, video;
    TextView textViewx;
    ShapeableImageView imageView;
    int size;
    EditText editText;
    Button got,uploader;
    StorageReference storageReference;

    String homepoint;
    ProgressBar progressBar;
    FirebaseUser user;
    private static final int PICKx=1;
    VideoView videoView;
    MediaController mediaController;
    Uri videouri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uxx);
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




        singlemaker2 fragmentB=new singlemaker2();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bibi, fragmentB, fragmentB.toString());
        fragmentTransaction.addToBackStack(fragmentB.toString());
        fragmentTransaction.commit();




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


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        overridePendingTransition(0,0);
    }

    public String videox(){
        return video;
    }

    public String upx(){
        return up;
    }

}
