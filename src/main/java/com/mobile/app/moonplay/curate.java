package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

 */
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.util.ConfigKeys;

import static android.content.Context.MODE_PRIVATE;


public class curate extends Fragment {
    Button nollymore,bollymore,koreamore,tvbut,animbut,medbut;
    private lays videoAdapter;
    ExoPlayerRecyclerView mRecyclerView;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    ImageView imageView,buttonx;
    RecyclerView recyclerView,yy;
    ArrayList romance = new ArrayList();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference medieval = db.collection("Spanish");
    ArrayList<Movie> movieArrayList,mx;
    MyItemAdapterxx adapter1;


    ArrayList<recom> recoms=new ArrayList<>();
    private CollectionReference music = db.collection("music");
    private CollectionReference valbum = db.collection("Valbum");
    private CollectionReference instant = db.collection("instant");
    ScrollView x;
    RecyclerView yiyii;
    laysq adapter;
    private CollectionReference xxx=db.collection("recom");
    private CollectionReference ge=db.collection("Genre");
    TextView mus,musy;
    layse adapter2;
    layse adapterx,adapterxi,adapterx2;
    layse adapter3;
    layse adapter4;
    SeriesAd adapter5;
    LinearLayout more;
    private SharedPreferences appPreferences;
    ImageView ref,payb;
    ScrollView scrollView;
    private RecyclerView.LayoutManager layoutManager;
    private static final int READ_PHONE_STATE_REQUEST_CODE = 22;
    FrameLayout frameLayout;
    Button notif;
    ArrayList<recom> noti=new ArrayList<>();
    String vi;
    String vi2;
    ProgressBar progressBar;
    List<genrex> agentList;
    ArrayList fashion=new ArrayList();
    ArrayList soccer=new ArrayList();
    ArrayList pre=new ArrayList();
    ArrayList digix=new ArrayList();
    ArrayList channels=new ArrayList();
    ArrayList<Movie> all=new ArrayList<>();
    RecyclerView trendingRecy1;
    ArrayList<Streampostion> xxy;
    RecyclerView gigi,foryou;
    private CollectionReference series = db.collection("Series");
    private FirebaseAuth firebaseAuth;
   // private AdLoader adLoader;
    FirebaseUser user;
    TextView muse;


    RelativeLayout conn;
    Button conb;
    RelativeLayout conre;
    String p;
    private CollectionReference meme = db.collection("instvid");
    private FragmentTransaction ft;
    String yx="";

    RelativeLayout vt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }

        ref=view.findViewById(R.id.ref);
        payb=view.findViewById(R.id.refx);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), noti.class);
                startActivity(intent);
            }
        });

        muse=view.findViewById(R.id.mus);



        conn=view.findViewById(R.id.con);
        conb=view.findViewById(R.id.conb);
        conre=view.findViewById(R.id.conRecycler);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task list",null);
        Type type=new TypeToken<ArrayList<Streampostion>>(){}.getType();
        xxy=gson.fromJson(json,type);

        if (xxy==null){
            xxy=new ArrayList<>();
        }


        if (xxy.size()>0) {
            Collections.shuffle(xxy);
            RecyclerView view1 = view. findViewById(R.id.recent);
            view1.setHasFixedSize(true);
            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            view1.setItemAnimator(new DefaultItemAnimator());
            streamposx adapter = new streamposx(getActivity(), xxy);
            view1.setAdapter(adapter);
        }else {
            conn.setVisibility(View.GONE);
            conb.setVisibility(View.GONE);
            conre.setVisibility(View.GONE);
        }

        appPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String images=preferences1.getString("images","");
        final String Dname=preferences1.getString("names","");
        final String Dlink=preferences1.getString("streamLink","");
        final String geenre=preferences1.getString("geenre","");
        final int vx=preferences1.getInt("uioii",0);






        final String gen=preferences1.getString("geenre","");
        if (gen.length()>0){
            if (gen.contains(",")){
                int KO = gen.indexOf(",");
                p = gen.substring(0, KO);
            }else {
                p=gen;
            }
        }
        final String geenrex=preferences1.getString("names","");
        Button textView=view.findViewById(R.id.tastetext);
        RelativeLayout allrel=view.findViewById(R.id.allrel);
        Button allbut=view.findViewById(R.id.allbut);
        RelativeLayout allrecy=view.findViewById(R.id.allrecy);
        if (geenrex.length()>0) {

            String x = "Similar to " + geenrex;

            if (geenrex.length()>6){
                yx=geenrex.substring(0,6);
            }
            if (geenrex.length() > 25) {
                textView.setText(x.substring(0, 25) + "...");
            } else {
                textView.setText(x);
            }
        }

        if (p==null){

            allbut.setVisibility(View.GONE);
            allrecy.setVisibility(View.GONE);
            allrel.setVisibility(View.GONE);
        }

        if (Dname.length()>0) {
            String totDur = String.format("%02d.%02d.%02d",
                    TimeUnit.MILLISECONDS.toHours(vx),
                    TimeUnit.MILLISECONDS.toMinutes(vx) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(vx)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(vx) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(vx)));
            //   len.setText("Resume");
            //backn.setText(Dname);


            if (Dname.length() > 20) {
                //     backn.setText(Dname.substring(0, 20) + "...");
                //   ImageView pl=findViewById(R.id.pl);
                // pl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            } else {
                //backn.setText(Dname);
                //ImageView pl=findViewById(R.id.pl);
                //pl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        }
        if (Dname.length()==0){
//            backn.setText("Binge something fun");

        }
        CardView cardView=view.findViewById(R.id.vx);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (streamplayer4All.plink!=null){
                    Intent intent=new Intent(getActivity(),popo.class);
                    intent.putExtra("photo", streamplayer4All.pimage);
                    intent.putExtra("name",streamplayer4All.pname);
                    intent.putExtra("video",streamplayer4All.plink);
                    intent.putExtra("genre",streamplayer4All.pgenre);
                    intent.putExtra("pos",streamplayer4All.ppos);
                    intent.putExtra("subt",streamplayer4All.psub);
                    startActivity(intent);
                }else {
                    if (Dname.length()>0) {
                        Intent intent = new Intent(getActivity(), splashActivity.class);
                        startActivity(intent);

                    }else{
                        Toast toast = Toast.makeText(getActivity(),"You haven't watched any movie yet", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                }






            }
        });
        payb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                new AlertDialog.Builder(getActivity())
                        .setTitle("Subscription needed")
                        .setMessage("Not currently available for Free version. Subscribe to Prime to cast contents to big screen and other privileges")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        .setIcon(android.R.drawable.ic_menu_preferences)
                        .show();
*/
            }
        });


        /*
        AdLoader.Builder builder = new AdLoader.Builder(getActivity(), "ca-app-pub-8117291338xxxx414342/2680115029");
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        FrameLayout frameLayout =
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

        scrollView=view.findViewById(R.id.scroll);
        mRecyclerView = view.findViewById(R.id.exoPlayerRecyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        gigi=view.findViewById(R.id.foryou);
        trendingRecy1=view.findViewById(R.id.digi);
        firebaseAuth= FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();



        ImageView love=view.findViewById(R.id.love);
        ImageView act=view.findViewById(R.id.actiontxt);
        ImageView hoho=view.findViewById(R.id.horrortxt);
        ImageView crimetxt=view.findViewById(R.id.crimetxt);
        ImageView business=view.findViewById(R.id.power);
        ImageView com=view.findViewById(R.id.com);
        ImageView scifix=view.findViewById(R.id.scifix);
        ImageView teenx=view.findViewById(R.id.teenx);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","romance");
                startActivity(intent);
            }
        });
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","action");
                startActivity(intent);
            }
        });
        hoho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","horror");
                startActivity(intent);
            }
        });
        crimetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","drama and crime");
                startActivity(intent);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","business");
                startActivity(intent);
            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","comedy");
                startActivity(intent);
            }
        });
        scifix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","sci");
                startActivity(intent);
            }
        });
        teenx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact.class);
                intent.putExtra("search","teen");
                startActivity(intent);
            }
        });
        x=view.findViewById(R.id.scroll);

        x.setVisibility(View.INVISIBLE);
        mx=new ArrayList<>();




        yiyii=view.findViewById(R.id.yiyii);


        initx();







    }

    private void initx() {


        xxx.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        recom gigi = documentSnapshot.toObject(recom.class);
                        noti.add(gigi);
                    }

                    ArrayList x=new ArrayList();
                    int size = noti.size();
                    for (int i = size - 1; i >= 0; i--) {
                        recom movie = noti.get(i);
                        if (!x.contains(movie)){
                            x.add(movie);
                        }

                    }

                    if (x.size() > 0) {


                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.choices);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx2 adapter1 = new MyItemAdapterxx2(getActivity(), x);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
            }
        });


        medieval.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);

                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().contains("x")) {
                                movieArrayList.add(movie);
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
                      //  Collections.shuffle(movieArrayList);
                        muse.setVisibility(View.INVISIBLE);
                        x.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.digi);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        adapter1 = new MyItemAdapterxx(getActivity(), movieArrayList);
                        trendingRecy1.setAdapter(adapter1);



                        }




                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });



        series.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        adapter5 = new SeriesAd(getActivity(), movieArrayList);
                        gigi.setAdapter(adapter5);
                    }
                }
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ge.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<genrely> noti = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        genrely gigi = documentSnapshot.toObject(genrely.class);
                        noti.add(gigi);
                    }

                    ArrayList<genrely> x=new ArrayList();
                    int size = noti.size();
                    for (int i = size - 1; i >= 0; i--) {
                        genrely movie = noti.get(i);
                        if (!x.contains(movie)){
                            x.add(movie);
                        }

                    }

                    if (x.size() > 0) {


                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.genx);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        Adaptergen adapter1 = new Adaptergen(getActivity(), x);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
            }
        });


        medieval.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);
                        if (!movieArrayList.contains(movie)) {
                            movieArrayList.add(movie);
                        }


                    }

                    if (movieArrayList.size() > 0) {

                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.just);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        adapter1 = new MyItemAdapterxx(getActivity(), movieArrayList);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });

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

                    ArrayList business = new ArrayList();
                    ArrayList fun = new ArrayList();
                    ArrayList fierce=new ArrayList();
                    ArrayList horror=new ArrayList();
                    ArrayList scifi=new ArrayList();
                    ArrayList recomm=new ArrayList();
                    ArrayList teen=new ArrayList();
                    ArrayList crime=new ArrayList();
                    ArrayList others=new ArrayList();


                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);


                        if (p!=null){
                        if (movie.getGenre().trim().toLowerCase().contains(p.trim().toLowerCase())) {
                            if (!all.contains(movie)) {
                                all.add(movie);
                            }
                        }
                        }
                        if (!romance.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("romance")) {
                                romance.add(movie);
                            }
                        }
                        if (!business.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("business")) {
                                business.add(movie);
                            }
                        }

                        if (!fun.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("comedy")) {
                                fun.add(movie);
                            }
                        }
                        if (!fierce.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("action")) {
                                fierce.add(movie);
                            }
                        }
                        if (!horror.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("horror")) {
                                horror.add(movie);
                            }
                        }
                        if (!crime.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("crime")) {
                                crime.add(movie);
                            }
                            if (movie.getGenre().toLowerCase().trim().startsWith("drama")) {
                                crime.add(movie);
                            }

                        }
                        if (!scifi.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("sci")) {
                                scifi.add(movie);
                            }
                        }
                        if (!horror.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("horror")) {
                                horror.add(movie);
                            }
                        }
                        if (!teen.contains(movie)){
                            if (movie.getGenre().toLowerCase().trim().startsWith("teen")) {
                                teen.add(movie);
                            }
                        }




                    }
                    if (all.size() > 0) {

                        Collections.shuffle(all);
                        RecyclerView trendingRecy1 = getActivity().findViewById(R.id.tasterecy);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxxre adapter1 = new MyItemAdapterxxre(getActivity(), all);
                        trendingRecy1.setAdapter(adapter1);
                    }
                    if (all.size()==0){
                        RelativeLayout allrel=getActivity().findViewById(R.id.allrel);
                        Button allbut=getActivity().findViewById(R.id.allbut);
                        RelativeLayout allrecy=getActivity().findViewById(R.id.allrecy);
                        allbut.setVisibility(View.GONE);
                        allrecy.setVisibility(View.GONE);
                        allrel.setVisibility(View.GONE);
                    }

                    if (romance.size() > 0) {
                        Collections.shuffle(romance);

                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.channels);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), romance);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (fierce.size() > 0) {
                        Collections.shuffle(fierce);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.action);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), fierce);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (horror.size() > 0) {
                        Collections.shuffle(horror);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.horror);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), horror);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (crime.size() > 0) {
                        Collections.shuffle(crime);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.crime);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), crime);
                        trendingRecy1.setAdapter(adapter1);

                    }

                    if (business.size() > 0) {
                        Collections.shuffle(business);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.business);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), business);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (fun.size() > 0) {

                        Collections.shuffle(fun);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.fun);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), fun);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (scifi.size() > 0) {
                        Collections.shuffle(scifi);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.scifi);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), scifi);
                        trendingRecy1.setAdapter(adapter1);

                    }
                    if (teen.size() > 0) {

                        Collections.shuffle(romance);
                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.teen);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter1 = new MyItemAdapterxx(getActivity(), teen);
                        trendingRecy1.setAdapter(adapter1);

                    }



                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });













/*

        meme.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {


                    final List<instV> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        instV gigi = documentSnapshot.toObject(instV.class);
                        movies.add(gigi);

                    }



                    int size = movies.size();

                    for (int i = size - 1; i >= 0; i--) {

                        instV movie = movies.get(i);
                        if (!movieArray.contains(movie)) {
                            movieArray.add(movie);
                        }


                    }
                    if (movieArray.size() > 0) {
                        //  Collections.shuffle(movieArray);
                        RecyclerView view1=getActivity().findViewById(R.id.animins);
                        laysq2 adapter = new laysq2(getActivity(), movieArray);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        view1.setAdapter(adapter);
                    }

                    if (movieArray.size()==0){


                    }


                }
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ge.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<genrely> noti = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        genrely gigi = documentSnapshot.toObject(genrely.class);
                        noti.add(gigi);
                    }

                    ArrayList<genrely> x=new ArrayList();
                    int size = noti.size();
                    for (int i = size - 1; i >= 0; i--) {
                        genrely movie = noti.get(i);
                        if (!x.contains(movie)){
                            x.add(movie);
                        }

                    }

                    if (x.size() > 0) {


                        RecyclerView trendingRecy1=getActivity().findViewById(R.id.genx);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        Adaptergen adapter1 = new Adaptergen(getActivity(), x);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
            }
        });


        valbum.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<doc> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        doc gigi = documentSnapshot.toObject(doc.class);
                        gigi.setName(documentSnapshot.getId());
                        gigi.setPoster(valbum.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    ArrayList movieArray = new ArrayList();



                    for (int i = size - 1; i >= 0; i--) {


                        doc movie = movies.get(i);



                            if (!movieArray.contains(movie)) {
                                movieArray.add(movie);
                            }





                        if (movieArray.size() > 0) {
                            RecyclerView view1 = getActivity().findViewById(R.id.shorts);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            layse adapter3 = new layse(getActivity(), movieArray);
                            view1.setAdapter(adapter3);
                        }

                    }

                }
            }
        });





*/
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.curate, container, false);


    }






    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(this)
                .setDefaultRequestOptions(options);
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

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }



    @Override
    public void onDetach() {
        super.onDetach();

    }

    /*
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
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.GONE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.GONE);
        } else {
            adView.getPriceView().setVisibility(View.GONE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.GONE);
        } else {
            adView.getStoreView().setVisibility(View.GONE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.GONE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.GONE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
    }

     */
    private void updateLastPlayed(Uri url, String title)
    {
        //set values
        appPreferences.edit().putString(ConfigKeys.KEY_LAST_PLAYED_URL, url.toString())
                .putString(ConfigKeys.KEY_LAST_PLAYED_TITLE, title).apply();
    }

    private long getResumePosition()
    {
        return appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, 0); //TODO: remove a few seconds (10s)
    }

    private boolean canResumePlayback(Uri url, String title)
    {
        //check if there is a playback position to resume stored
        if (appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, -1) <= 0) return false;

        //check that url or title is the same as the last played
        return url.toString().equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_URL, ""))
                || title.equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_TITLE, ""));
    }

}
