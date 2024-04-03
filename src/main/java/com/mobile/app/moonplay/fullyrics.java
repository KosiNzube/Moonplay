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


public class fullyrics extends Fragment {

    RecyclerView trendingRecy, con, popo, action, randomrecy;
    TextView progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView, musical, romance, thriller, random;
    ImageView button, koko;
    Bitmap bi;
    private static int splashy = 10;
    private static int splashyy = 15;
    ArrayList arrayList = new ArrayList();
    List<Movie> bookList;

    ArrayList<Movie> music = new ArrayList<>();
    TextView loading, ii, cwatch;
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
    String x;//action
    int y;//romance
    agentsadapter adapter;
    ProgressBar progressBarx;
    List<music> musicList;
    Button button3;
    FloatingActionButton v;
    ImageView album;
    TextView tctc;
    ImageView back;
    RelativeLayout relative, part1;
    RecyclerView view;
    List<agents> agentList;
    ArrayList<video> shorts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ii = view.findViewById(R.id.pro);
        agentList = new ArrayList<>();

        x = "Ella, Ella, Ella. I fit die for love. I cant enough of you. Rain, Rain go away, Dice dont want to play. I dont want to be a player no more";
        agentList.add(new agents("Ivory", "Tenor", x, "Female"));
        agentList.add(new agents("Jackson", "Bass-Tenor", x, "Male"));
        agentList.add(new agents("Helen", "Tenor", x, "Female"));
        agentList.add(new agents("Brown", "Soprano", x, "Male"));
        agentList.add(new agents("Kelly", "Bass", x, "Male"));


        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifi);
        view1.setHasFixedSize(true);
        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
        adapter = new agentsadapter(getActivity(), agentList, new agentsadapter.OnItemClickListener() {
            @Override
            public void onItemClick(agents item) {
                // Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_LONG).show();

            }
        });
        view1.setAdapter(adapter);

        ii.setText(x);

        ii.setSingleLine(false);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void loadNotes() {

    }

    public void addNotes() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this songsfragment
        return inflater.inflate(R.layout.lyrics, container, false);

    }


}