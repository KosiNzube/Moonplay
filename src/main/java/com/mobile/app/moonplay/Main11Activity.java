package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Main11Activity extends AppCompatActivity {
    RecyclerView trendingRecy,con,popo,action,randomrecy;
    TextView progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView,musical,romance,thriller,random;
    ImageView button,koko;
    Bitmap bi;
    private  static  int splashy=10;
    private  static  int splashyy=15;
    ArrayList arrayList=new ArrayList();
    List<Movie> bookList;

    ArrayList<Movie> music=new ArrayList<>();
    TextView loading,ii,cwatch;
    String name;
    String dex;
    String gen;
    String image;
    String mb;
    String type;
    String uplo;
    String res;

    String music1;
    ArrayList<Movie> movieArrayLists = new ArrayList<>();
    String vid;
    int x;//action
    int y;//romance
    agentsadapter adapter;
    ProgressBar progressBarx;
    List<agents> agentList;
    Button button3;
    FloatingActionButton v;
    ImageView album;
    TextView tctc;
    CardView xx;
    ImageView back;
    RelativeLayout relative,part1;
    RecyclerView view;
    ArrayList<video> shorts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agentsfrag);



        xx=findViewById(R.id.pausecardy);



    }
}
