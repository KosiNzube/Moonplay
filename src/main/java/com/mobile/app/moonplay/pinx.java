package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.alirezabdn.wp7progress.WP7ProgressBar;


public class pinx extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView progressBar;
    String vi;
    ArrayList<Object> movieArrayListxx = new ArrayList<>();
    TextView textView,romance,random,musy,mus;
    ImageView button;
    ImageView buttonx,nollymore,bollymore,koreamore,tvbut,animbut,medbut;
    private AdLoader adLoader;
    private FloatingSearchView mSearchView;
    LinearLayout linear;
    public static final int NUMBER_OF_ADS = 1;
    ArrayList arrayList=new ArrayList();
    List<Movie> bookList;
    private CollectionReference bolly=db.collection("Nollywood");
    ArrayList<Movie> movieArrayListxxy = new ArrayList<>();
  //  private CollectionReference bollym=db.collection("Bollywood");
    private CollectionReference korea=db.collection("Korean");
    ArrayList<Movie> music=new ArrayList<>();
    ArrayList<Movie> taste=new ArrayList<>();
    ArrayList<Movie> you=new ArrayList<>();
    TextView ii,cwatch;
    ProgressBar loading;
    String name;
    String dex;
    String gen;
    Button bu;
    String image;
    String mb;
    String type;
    String uplo;
    String res;
    int a; //animation
    int b;//horror
    int c;//fantasy
    int d;//scifi
    int e;//comedy
    int f;//drama
    int h;//crime
    RecyclerView gigi;
    private CollectionReference meme = db.collection("shorts");
     ArrayList<Movie> all=new ArrayList<>();
    ArrayList<Movie> nnn=new ArrayList<>();
    ArrayList<Movie> shorts=new ArrayList<>();
    ArrayList<String> soon=new ArrayList<>();
    ScrollView scrollView;
    int x;//action
    int y;//romance
    private CollectionReference series = db.collection("Series");
    private CollectionReference korean = db.collection("Spanish");

    ScrollView scrollViewx;
    FloatingActionButton v;
    TextView tctc;
    List<genrex> agentList;
    TextView p;
    ImageView ref;
    ArrayList<bibi> noti=new ArrayList<>();
    String yx="";
    Button notif;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private CollectionReference medieval=db.collection("Spanish");
    private CollectionReference medievaly=db.collection("Medieval");
    private CollectionReference xxx=db.collection("noti");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WP7ProgressBar progressBar = view.findViewById(R.id.wp7progressBar);
        ref=view.findViewById(R.id.ref);

        p=view.findViewById(R.id.alltext);
        p.setSelected(true);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getActivity().getIntent();
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);
            }
        });
        progressBar.showProgressBar();
        scrollViewx=view.findViewById(R.id.scroll);
        scrollViewx.setVisibility(View.INVISIBLE);

        medieval.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<Movie> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Movie gigi = doc.toObject(Movie.class);
                        gigi.setVideo(doc.getId());
                        gigi.setUploader(medieval.getId());
                        if (gigi.getType()!=null) {
                            if (gigi.getType().toLowerCase().contains("x")) {
                                movies.add(gigi);
                                if (soon.size()<12) {
                                    soon.add(gigi.getName() + "  |  ");
                                }
                            }
                        }


                    }

                    if (movies.size() > 0) {
                        progressBar.setVisibility(View.INVISIBLE);
                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                        scrollViewx.setVisibility(View.VISIBLE);
                        //  Collections.shuffle(movieArrayList);
                        RecyclerView trendingRecy1=view.findViewById(R.id.yiyii);
                        trendingRecy1.setLayoutManager(glm);
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxxggg adapter1 = new MyItemAdapterxxggg(getActivity(), movies);
                        trendingRecy1.setAdapter(adapter1);
                        p.setText(TextUtils.join(", ",soon).replaceAll(",",""));

                    }
                }
            }
        });





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
            return inflater.inflate(R.layout.luno, container, false);

        }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }

}
