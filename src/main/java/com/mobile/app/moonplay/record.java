package com.mobile.app.moonplay;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class record extends AppCompatActivity {
    ArrayList m=new ArrayList();
    ImageView imageButton,riri;
    TextView textView,textView2,textView3;
    RecyclerView myRecyclerView;
    RelativeLayout host;
    Button button,conbu;
    TextView playlist;
    Button cardView;
    EditText editText;
    ListView listView;
    ProgressBar loading;
    FloatingActionButton fab;
    TextView yyy;
    TextView Series,seriesButton;
    TextView toprated;
    TextView cont;
    private long downloadID;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    ImageView three;
    private boolean doublein=false;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;



    Button uiui;

    ImageView imageView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    RecyclerView gigi;
    TextView popular,genre;
    public static ArrayList<File> fileArrayList=new ArrayList<>();
    File direcyory;
    boolean boo_perm;
    ImageView ioio;
    TextView noInternet;
    public static int REQUEST_PERMISSION=1;
    private Fragment home;
    private Fragment pins;
    private Fragment account;
    int requestCode = 0;
    private FrameLayout frameLayout;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark)));
        }

        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
               // Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
               // Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
