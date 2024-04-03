package com.mobile.app.moonplay;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class playlist extends Fragment {

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

    ArrayList<Movie> movieArrayLists = new ArrayList<>();
    String vid;
    int x;//action
    int y;//romance

    ProgressBar progressBarx;
    List<music> musicList;
    Button button3;
    FloatingActionButton v;
    ImageView album;
    TextView tctc;
    ImageView back;
    RelativeLayout relative,part1;
    RecyclerView view;
    ArrayList<video> shorts=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);










    }

        @Override
        public void onStart () {
            super.onStart();
        }

        public void loadNotes () {

        }

        public void addNotes () {

        }
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){
            // Inflate the layout for this songsfragment
            return inflater.inflate(R.layout.lyrics, container, false);

        }

}
