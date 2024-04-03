package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;


public class pins extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView progressBar;
    String vi;
    ArrayList<Object> movieArrayListxx = new ArrayList<>();
    TextView textView,romance,random,musy,mus;
    ImageView button;
    Button buttonx,nollymore,bollymore,koreamore,tvbut,animbut,medbut;
    private AdLoader adLoader;

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
    ScrollView scrollView;
    int x;//action
    int y;//romance
    private CollectionReference series = db.collection("Series");
    private CollectionReference korean = db.collection("Spanish");

    FloatingActionButton v;
    TextView tctc;
    List<genrex> agentList;
    String p;
    ImageView back;
    ArrayList<bibi> noti=new ArrayList<>();
    String yx="";
    Button notif;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private CollectionReference medieval=db.collection("Italy");
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

       // AdLoader.Builder builder = new AdLoader.Builder(getActivity(), "ca-app-pub-3940256099942544/2247696110");
        //loadNativeAds();
        gigi=view.findViewById(R.id.gigii);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String gen=preferences1.getString("geenre","");
        if (gen.length()>0){
            if (gen.contains(",")){
                int KO = gen.indexOf(",");
               p = gen.substring(0, KO);
            }else {
                p=gen;
            }
        }
/*
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        RelativeLayout frameLayout =
                                view.findViewById(R.id.btn8);
                        // Assumes that your ad layout is in a file call ad_unified.xml
                        // in the res/layout folder
                        UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                                .inflate(R.layout.ad_unifiedx, null);
                        // This method sets the text, images and the native ad, etc into the ad
                        // view.
                        populateNativeAdView(unifiedNativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                        if (getActivity().isDestroyed()) {
                            unifiedNativeAd.destroy();
                            return;
                        }

                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {

                        }
                    }
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
        */
        notif=view.findViewById(R.id.backintimez);

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), noti.class);
                startActivity(intent);
            }
        });



      //  Toast.makeText(getActivity(),p,Toast.LENGTH_SHORT).show();
        final String geenre=preferences1.getString("names","");



        TextView textView=view.findViewById(R.id.tastetext);

        if (geenre.length()>0) {

            String x = "Similar to " + geenre;

            if (geenre.length()>6){
                yx=geenre.substring(0,6);
            }
            if (geenre.length() > 30) {
                textView.setText(x.substring(0, 30) + "...");
            } else {
                textView.setText(x);
            }
        }
        if (p==null){
            p="";
        }
       // bu=view.findViewById(R.id.uiui);
        buttonx=view.findViewById(R.id.trendbut);
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), more.class);
                startActivity(intent);
            }
        });

        nollymore=view.findViewById(R.id.nollyx);

        koreamore=view.findViewById(R.id.koreamore);
        tvbut=view.findViewById(R.id.titii);
        medbut=view.findViewById(R.id.medbut);
        animbut=view.findViewById(R.id.backbutton);


        Button xv=view.findViewById(R.id.comingbut);

        xv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Coming Soon");
                startActivity(intent);
            }
        });

        tvbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Tv Shows");
                startActivity(intent);
            }
        });
        medbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Medieval");
                startActivity(intent);
            }
        });

        animbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Anime");
                startActivity(intent);
            }
        });

        nollymore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Nollywood");
                startActivity(intent);
            }
        });
        koreamore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","Korean");
                startActivity(intent);
            }
        });


        scrollView=view.findViewById(R.id.scroll);
        scrollView.setVisibility(View.INVISIBLE);

        tctc=view.findViewById(R.id.mus);
        loading=view.findViewById(R.id.progressBar);
        if (isNetworkAvailable()==false){
            //    Toast.makeText(this,"Check your internet connection", Toast.LENGTH_LONG).show();

            loading.setVisibility(View.INVISIBLE);
            // inter.setVisibility(View.VISIBLE);

        }

        agentList=new ArrayList<>();
        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fmusicals%2Ffantasy-wallpaper-35.jpg?alt=media&token=e7dd626f-b4fb-4464-a3d4-ae94b462347f", "Fantasy"));
        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fmusicals%2Fhorror.jpeg?alt=media&token=1fd5d4c3-4514-41cd-b396-375dfea923a4", "Horror"));

        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fmusicals%2Ftitanic%20(1).jpg?alt=media&token=dc42cbd6-9d1a-4614-9a52-f5c56732f007", "Drama"));

        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fmusicals%2F5a8607fd7708e925fb3e5ae3.jpg?alt=media&token=3a164841-09df-4e33-9771-42d95eec772c", "Action"));
        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/rom.png?alt=media&token=3c863a77-7ec5-420e-a078-6541c742c1da", "Romance"));
        agentList.add(new genrex("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/come.jpg?alt=media&token=bf0f4a28-8e00-4a3b-aac0-21127484c2dd", "Comedy"));

        RecyclerView trendingRecy=view.findViewById(R.id.genrecy);
        trendingRecy.setHasFixedSize(true);
        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trendingRecy.setItemAnimator(new DefaultItemAnimator());
        genre adapter = new genre(getActivity(), agentList);
        trendingRecy.setAdapter(adapter);

        mus=view.findViewById(R.id.mus);
        musy=view.findViewById(R.id.musy);
        xxx.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        bibi gigi = documentSnapshot.toObject(bibi.class);
                        noti.add(gigi);
                    }
                    for (int i = 0;i<noti.size();i++) {
                        bibi movie = noti.get(i);
                        vi=noti.get(0).getName();
                        notif.setText(vi);
                    }
                }
            }
        });
        medieval.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    loading.setVisibility(View.INVISIBLE);
                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(medieval.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            if (!nnn.contains(movie)) {
                                nnn.add(movie);
                            }

                        if (movie.getGenre().trim().toLowerCase().contains(p.trim().toLowerCase())) {
                            if (!all.contains(movie)) {
                                all.add(movie);
                            }
                        }
                        if (movie.getName().contains(yx)){

                                all.remove(movie);

                        }

                        if (!you.contains(movie)) {
                            you.add(movie);
                        }

                        if (movie.getType() != null) {
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                movieArrayList.add(movie);


                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                nnn.remove(movie);
                                you.remove(movie);
                            }
                        }
                        if (movie.getType() != null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayList.remove(movie);

                                nnn.remove(movie);
                                you.remove(movie);
                                all.remove(movie);


                            }

                        }
                        /*
                        if (movie.getType() != null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                if (!kkk.contains(movie)) {
                                    kkk.add(movie);
                                }
                            }
*/


                    }
                    if (movieArrayList.size() > 0) {
                        tctc.setVisibility(View.INVISIBLE);
                        scrollView.setVisibility(View.VISIBLE);


                        mus.setVisibility(View.INVISIBLE);
                        musy.setVisibility(View.INVISIBLE);

                        RecyclerView trendingRecy1=view.findViewById(R.id.yiyii);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), movieArrayList);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (nnn.size() > 0) {

                        RecyclerView trendingRecy=view.findViewById(R.id.trendrecy);
                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapter adapter = new MyItemAdapter(getActivity(), nnn);
                        trendingRecy.setAdapter(adapter);


                    }
                    /*
                    if (kkk.size() > 0) {

                        Collections.shuffle(kkk);
                        RecyclerView trendingRecy=view.findViewById(R.id.comingrecy);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        trendingRecy.setHasFixedSize(true);

                        layz adapter = new layz(getActivity(), kkk);
                        trendingRecy.setAdapter(adapter);


                    }
                    */
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                    if (you.size() > 0) {

                        Collections.shuffle(you);
                        RecyclerView trendingRecy1=view.findViewById(R.id.fifi);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));

                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        popular11 adapter1 = new popular11(getActivity(), you);
                        trendingRecy1.setAdapter(adapter1);


                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });
        series.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //gigi.setVisibility(View.VISIBLE);

                    final List<SeriesObject> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        SeriesObject gigi = documentSnapshot.toObject(SeriesObject.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(series.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<SeriesObject> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {
                        int rand = new Random().nextInt(size);
                        SeriesObject movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);

                        /*
                       if (movie.getType().contains("3")){
                           movieArrayList.remove(movie);
                       }
                       */
                    }

                    if (movieArrayList.size() > 0) {
                        gigi.setHasFixedSize(true);
                        gigi.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        gigi.setItemAnimator(new DefaultItemAnimator());
                        SeriesAd adapter = new SeriesAd(getActivity(), movieArrayList);
                        gigi.setAdapter(adapter);
                    }
                }
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        korean.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(korean.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    ArrayList movieArray=new ArrayList();
                    for (int i = size - 1; i >= 0; i--) {

                        Movie movie = movies.get(i);

                        if (!movieArray.contains(movie))
                            movieArray.add(movie);
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }

                        }
                        if (!you.contains(movie)){

                            you.add(movie);


                        }

                    }
                    if (movieArray.size() > 0) {

                        RecyclerView trendingRecy=view.findViewById(R.id.backrecy);
                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapter adapter = new MyItemAdapter(getActivity(), movieArray);
                        trendingRecy.setAdapter(adapter);


                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }


                }                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }


            }
        });
        medievaly.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(medievaly.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);
                        if (!you.contains(movie)){

                            you.add(movie);

                        }
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }

                        }
                    }
                    if (movieArrayList.size() > 0) {

                        RecyclerView trendingRecy=view.findViewById(R.id.medrecy);
                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        watchadapter adapter = new watchadapter(getActivity(), movieArrayList);
                        trendingRecy.setAdapter(adapter);


                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });
        korea.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(korea.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);


                        if (!movieArrayListxxy.contains(movie))
                            movieArrayListxxy.add(movie);
                        if (!you.contains(movie)){

                            you.add(movie);

                        }
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }
                        }
                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayListxxy.remove(movie);
                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                movieArrayListxxy.remove(movie);
                            }

                        }
                    }
                    if (movieArrayListxxy.size() > 0) {

                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.yiyiix);

                        //Collections.shuffle(movieArrayListxx);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        layx adapter = new layx(getActivity(), movieArrayListxxy);
                        view1.setAdapter(adapter);
                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bolly.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(bolly.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);



                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);
                        if (!you.contains(movie)){

                            you.add(movie);

                        }
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }
                        }
                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayList.remove(movie);
                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                movieArrayList.remove(movie);
                            }

                        }
                    }
                    if (movieArrayList.size() > 0) {

                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.nollyrecy);

                        //Collections.shuffle(movieArrayListxx);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        layx adapter = new layx(getActivity(), movieArrayList);
                        view1.setAdapter(adapter);
                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        bollym.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(bollym.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayListxx = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);


                        if (!movieArrayListxx.contains(movie))
                            movieArrayListxx.add(movie);
                        if (!you.contains(movie)){

                            you.add(movie);

                        }
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }
                        }
                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayListxx.remove(movie);
                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                movieArrayListxx.remove(movie);
                            }

                        }
                    }
                    if (movieArrayListxx.size() > 0) {

                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.bollyrecy);

                        //Collections.shuffle(movieArrayListxx);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        layz adapter = new layz(getActivity(), movieArrayListxx);
                        view1.setAdapter(adapter);
                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bolly.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(bolly.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);


                        if (!movieArrayListxx.contains(movie))
                            movieArrayListxx.add(movie);

                        if (!you.contains(movie)){

                            you.add(movie);

                        }
                        if (movie.getGenre().contains(p)) {
                            if (!all.contains(movie)) {

                                all.add(movie);
                            }
                        }
                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayListxx.remove(movie);
                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                movieArrayListxx.remove(movie);
                            }

                        }
                    }
                    if (movieArrayListxx.size() > 0) {

                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.yiyiix);

                        //Collections.shuffle(movieArrayListxx);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        lay adapter = new lay(getActivity(), movieArrayListxx);
                        view1.setAdapter(adapter);
                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = view.findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter1 = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        x = preferences.getInt("action", 0);
        y=preferences.getInt("romance",0);

        a=preferences.getInt("animation",0);
        b=preferences.getInt("horror",0);
        c=preferences.getInt("fantasy",0);
        d=preferences.getInt("scifi",0);
        e=preferences.getInt("comedy",0);
        f=preferences.getInt("drama",0);
        h=preferences.getInt("crime",0);
        final int highest=getHighest(x,y,a,b,c,d,e,f,h);


        medieval.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(medieval.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);



                            if (!you.contains(movie)) {

                                you.add(movie);

                            }
                        if (!all.contains(movie)){

                            all.add(movie);
                        }

                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                you.remove(movie);
                                all.remove(movie);
                            }
                            if (movie.getType().toLowerCase().trim().contains("trend")) {
                                you.remove(movie);
                                all.remove(movie);

                            }


                            if (movie.getGenre().toLowerCase().trim().contains("romance")) {
                                all.remove(movie);
                            }


                        }
                        final String gen=preferences1.getString("geenre","");
                        if (gen.length()>0) {
                        }

                    }


                    final String geenre=preferences1.getString("names","");

                    TextView textView=view.findViewById(R.id.tastetext);

                    if (geenre.length()>0) {

                        String x="Similar to " + geenre;

                        if (geenre.length()>30){
                            textView.setText(x.substring(0,30)+"...");
                        }else {
                            textView.setText(x);
                        }

                    }


                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy=view.findViewById(R.id.tasterecy);
                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterzz adapter = new MyItemAdapterzz(getActivity(), all);
                        trendingRecy.setAdapter(adapter);


                    }else if (all.size()==0||all.size()<0){
                        RecyclerView trendingRecy=view.findViewById(R.id.tasterecy);
                        TextView textView1=view.findViewById(R.id.tastetext);
                        Button button=view.findViewById(R.id.tastebut);

                        trendingRecy.setVisibility(View.GONE);
                        textView1.setVisibility(View.GONE);
                        button.setVisibility(View.GONE);
                    }
                    if (you.size() > 0) {

                        Collections.shuffle(you);
                        RecyclerView trendingRecy=view.findViewById(R.id.fifi);
                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        layx adapter = new layx(getActivity(), you);
                        trendingRecy.setAdapter(adapter);


                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

        /*

        button3=view.findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"Feature not yet available", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });
        part1=view.findViewById(R.id.part1);


        progressBarx=view.findViewById(R.id.progressBar);
        musicList=new ArrayList<>();
        progressBar=view.findViewById(R.id.pro);


      //  part1.setVisibility(View.INVISIBLE);
        relative=view.findViewById(R.id.relative);

        /*
        musicList.add(new music("Loving you", 2,"....","Piano",true,"All the love we had in one bit"));
        musicList.add(new music("Ne Yo", 4,"....","Piano",true,"If it was meant to be.. it will be"));
        musicList.add(new music("Kelly", 5,"....","Piano",true,"All the love we had in one bit"));
        musicList.add(new music("Jojo", 3,"....","Piano",true,"All the love we had in one bit"));
        musicList.add(new music("New Me", 4,"....","Piano",true,"All the love we had in one bit"));
        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifi);
        view1.setHasFixedSize(true);
        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
        musicadapter adapter = new musicadapter(getActivity(), musicList);
        view1.setAdapter(adapter);


        album.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<albums> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        albums gigi=documentSnapshot.toObject(albums.class);

                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<albums> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        albums movie=movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);



                    }
                    if (movieArrayList.size()>0){
                        progressBarx.setVisibility(View.INVISIBLE);
                      //  part1.setVisibility(View.VISIBLE);
                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifi);
                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(glm);
                        card adapter = new card(getActivity(), movieArrayList);
                        view1.setAdapter(adapter);



                    }
                    if (movieArrayList.size()<0||movieArrayList.size()==0){

                    }
                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });





*/

        /*
        bookList = new ArrayList<>();

        v=view.findViewById(R.id.fab);
        tctc=view.findViewById(R.id.tctc);

        textView=view.findViewById(R.id.textView5);
        textView.setVisibility(View.INVISIBLE);
        loading=view.findViewById(R.id.loading);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),signup.class);
                startActivity(intent);

            }
        });

        back=view.findViewById(R.id.foryou);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    loading.setVisibility(View.INVISIBLE);
                    tctc.setVisibility(View.INVISIBLE);
                    List<Movie> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        Movie gigi=documentSnapshot.toObject(Movie.class);
                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand=new Random().nextInt(size);
                        Movie movie=movies.get(i);
                            if (!movieArrayList.contains(movie))
                                movieArrayList.add(movie);
                    }
                    if (movieArrayList.size()>0){
                        textView.setVisibility(View.INVISIBLE);

                    }
                    if (movieArrayList.size()<0||movieArrayList.size()==0){

                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.recycler_id);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        SnapHelper snapHelper = new PagerSnapHelper();
                        snapHelper.attachToRecyclerView(view1);
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), movieArrayList);
                        view1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                        view1.setAdapter(adapter);
                        textView.setVisibility(View.VISIBLE);
                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });

/*
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Movie movie = documentSnapshot.toObject(Movie.class);
                     name = movie.getName();
                     dex = movie.getDescription();
                     gen = movie.getGenre();
                     image = movie.getImage();
                     mb = movie.getMb();
                     type = movie.getType();
                     uplo = movie.getUploader();
                     res = movie.getResolution();
                     vid = movie.getVideo();
                     bookList.add(movie);



                }
            }
        });
*/


/*



        bookList.add(new Book("once upon a time","android.resource://"+getActivity().getPackageName()+"/"+R.raw.bibi,"Horror",R.drawable.ride));
        bookList.add(new Book("Lord of the rings","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Sci-Fi",0));
        bookList.add(new Book("Hunger games","android.resource://"+getActivity().getPackageName()+"/"+R.raw.baby,"Horror",0));
        bookList.add(new Book("","android.resource://"+getActivity().getPackageName()+"/"+R.raw.iii,"Horror",R.drawable.ic_headset_black_24dp));
        bookList.add(new Book("","android.resource://"+getActivity().getPackageName()+"/"+R.raw.bibi,"Horror",R.drawable.ic_headset_black_24dp));
        bookList.add(new Book("","android.resource://"+getActivity().getPackageName()+"/"+R.raw.fif,"Comedy",R.drawable.ic_headset_black_24dp));
        bookList.add(new Book("","android.resource://"+getActivity().getPackageName()+"/"+R.raw.baby,"Comedy",R.drawable.ic_headset_black_24dp));
        bookList.add(new Book("","","Description music",R.drawable.ic_headset_black_24dp));
        */


        //  bookList.add(new Movie("A cyborg on a mission of self discovery","Horror",R.drawable.alita,"315Mb","Once upon a time","trailer","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,""));
        //bookList.add(new Movie("A cyborg on a mission of self discovery","Horror",R.drawable.alita,"315Mb","Once upon a time","trailer","android.resource://"+getActivity().getPackageName()+"/"+R.raw.bibi,""));
     /*   bookList.add(new Movie("A cyborg on a mission of self discovery. A revolutionary cyborg on a mission","Horror",R.drawable.mech,"115 MB","Alita","movie","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Fleming","Standard res"));
        bookList.add(new Movie("A cyborg on a mission of self discovery. A revolutionary cyborg on a mission","Horror",R.drawable.wive,"305 MB","Alita","movie","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Paul Dike","Standard res"));
        bookList.add(new Movie("A cyborg on a mission of self discovery. A revolutionary cyborg on a mission","Horror",R.drawable.hunsman,"1.9 GB","Alita","movie","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Fleming","High resolution"));
        bookList.add(new Movie("A cyborg on a mission of self discovery. A revolutionary cyborg on a mission","Horror",R.drawable.sniper,"98 MB","Alita","movie","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Fleming","Low resolution"));
        bookList.add(new Movie("A cyborg on a mission of self discovery. A revolutionary cyborg on a mission","Horror",R.drawable.ride,"119 MB","Alita","movie","android.resource://"+getActivity().getPackageName()+"/"+R.raw.ccc,"Eikon studio","Standard res"));

        bookList.add(new Movie("A cyborg on a mission of self discovery","Horror",R.drawable.alita,"1.4 GB","Once upon a time","movie","","","High resolution"));
        bookList.add(new Movie("A cyborg on a mission of self discovery","Horror",R.drawable.paper,"415 MB","Alita","movie","","","Standard res"));
des


*/
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
            return inflater.inflate(R.layout.fragment_pins, container, false);

        }
    private int getHighest(int x, int y, int a, int b, int c, int d, int e, int f, int h) {
        int[] decmax = {x, y, a, b,c,d,e,f,h};
        List<Integer> list=new ArrayList<Integer>();
        for (int i=0;i<decmax.length;i++){
            list.add(decmax[i]);
        }
        return Collections.max(list);
    }
    private void loadNativeAds() {

        AdLoader.Builder builder = new AdLoader.Builder(getActivity(), "ca-app-pub-3940256099942544/2247696110");
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        mNativeAds.add(unifiedNativeAd);

                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems();
                        }

                        if (getActivity().isDestroyed()) {
                            unifiedNativeAd.destroy();
                            return;
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        if (!adLoader.isLoading()) {
                            insertAdsInMenuItems();
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }

    private void insertAdsInMenuItems() {
        if (mNativeAds.size() <= 0) {
            return;
        }

        int offset = (movieArrayListxx.size() / mNativeAds.size()) + 1;
        int offx=(movieArrayListxxy.size() / mNativeAds.size()) + 1;
        int index = 0;
        int inx=0;

        for (UnifiedNativeAd ad : mNativeAds) {

            movieArrayListxx.add(inx, ad);



        }
       // RecyclerView view1 = (RecyclerView) getActivity().findViewById(R.id.yiyiix);

        //Collections.shuffle(movieArrayListxx);
        //view1.setHasFixedSize(true);
        //view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
        //lay adapter = new lay(getActivity(), movieArrayListxx);
        //view1.setAdapter(adapter);
        index = index + offset;
        inx=inx+offx;
       // loadMenu();
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }
    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        adView = (UnifiedNativeAdView) adView.findViewById(R.id.ad_view);

        // The MediaView will display a video asset if one is present in the ad, and the
        // first image asset otherwise.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Register the view used for each individual asset.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));


        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
          //  adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
          //  adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
    }

}
