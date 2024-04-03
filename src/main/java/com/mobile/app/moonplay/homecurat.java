package com.mobile.app.moonplay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.alirezabdn.wp7progress.WP7ProgressBar;
import com.mobile.app.moonplay.R;

import static com.mobile.app.moonplay.Main7Activity.user;


public class homecurat extends Fragment  {


    public static ArrayList movieArrayxxx=new ArrayList();
    Boolean xxx=false;
    ArrayList<String> x=new ArrayList();

    LinearLayout asasa;
    CardView cardView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView, musical, romance, thriller, random;
    ImageView button, koko;
    private ArrayList<mainpost> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    private int notificationId = 1234;
    ScrollView scrollView;
    private ArrayList<quick> quicks = new ArrayList<>();
    ExoPlayerRecyclerView mRecyclerView;
    int requestCode = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    ImageView playpause;
    private static int splashy = 10;
    SimpleExoPlayer simpleExoPlayer;
    private CollectionReference medieval = db.collection("Spanish");
    private static int splashyy = 15;
    ArrayList arrayList = new ArrayList();
    List<Movie> bookList;
    private CollectionReference ge=db.collection("Genre");
    private CollectionReference xxxx=db.collection("recom");
    ArrayList<recom> noti=new ArrayList<>();

    private boolean isPlaying;
    ArrayList<Movie> music = new ArrayList<>();
    TextView loading, ii, cwatch;
    String name;
    String dex;
    String gen;
    String image;
    String mb;
    String type;
    String uplo;
    ImageView previos, next;
    private Handler mainHandler;
    String res;
    Runnable updatePlayer;
    int a; //animation
    int b;//horror
    int c;//fantasy
    int d;//scifi
    int e;//comedy
    int f;//drama
    int h;//crime
    ArrayList<Movie> movieArrayLists = new ArrayList<>();
    String vid;
    int y;//romance
    private CollectionReference meme = db.collection("instvid");
    private CollectionReference memex = db.collection("recom");

    List<genrex> agentList;
    ProgressBar progressBarx;
    List<com.mobile.app.moonplay.music> musicList;
    Button button3, studio;
    quick movie;
    FloatingActionButton v;
    //ImageView album;
    TextView rem,conxx;
    ImageView back;
    private TextToSpeech mtts;
    RelativeLayout relative, part1, part2;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    ArrayList<video> shorts = new ArrayList<>();
    ImageView search;
    ImageView cxc,ref;
    TextView p;
    ImageView vidp,sett;
    ArrayList<Streampostion> xxy;
    private CollectionReference series = db.collection("Series");
    ArrayList<Streampostion> xxy2=new ArrayList<>();
    private RecyclerView gigi;
    ArrayList<String> soon=new ArrayList<>();
    RelativeLayout vi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }




        WP7ProgressBar progressBar = view.findViewById(R.id.wp7progressBar);

        button3=view.findViewById(R.id.backbutton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), genact23.class);
                intent.putExtra("search", "Currently available ");
                startActivity(intent);
            }
        });

        search=view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), lily.class);
               // intent.putExtra("search", "Currently available ");
                startActivity(intent);
            }
        });

        asasa=view.findViewById(R.id.asasa);
        vidp=view.findViewById(R.id.refresh);
        vidp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Main5Activity.class);
                startActivity(intent);
            }
        });

        sett=view.findViewById(R.id.playlist);
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Main7Activity.xyx();
            }
        });

        progressBar.showProgressBar();
        gigi=view.findViewById(R.id.ser);
        ref=view.findViewById(R.id.ref);
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

        rem=view.findViewById(R.id.rem);


        p=view.findViewById(R.id.alltext);
        p.setSelected(true);

        vi=view.findViewById(R.id.vi);
        vi.setVisibility(View.GONE);


        scrollView=view.findViewById(R.id.scroll);
        scrollView.setVisibility(View.INVISIBLE);
        thriller=view.findViewById(R.id.mus);
        thriller.setVisibility(View.GONE);


        /*
        medieval.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<Movie> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Movie gigi = doc.toObject(Movie.class);
                      //  gigi.setVideo(doc.getId());
                        gigi.setUploader(medieval.getId());
                        if (gigi.getType()!=null) {
                            if (gigi.getType().toLowerCase().contains("x")) {
                                movies.add(gigi);
                                if (soon.size()<6) {
                                    soon.add(gigi.getName() + "  |  ");
                                }
                            }
                        }


                    }

                    if (movies.size() > 0) {
                        progressBar.setVisibility(View.INVISIBLE);


                        //  Collections.shuffle(movieArrayList);
                        RecyclerView trendingRecy1=view.findViewById(R.id.yiyiiq);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxxggg adapter1 = new MyItemAdapterxxggg(getActivity(), movies);
                        trendingRecy1.setAdapter(adapter1);

                        p.setText(TextUtils.join(", ",soon).replaceAll(",",""));
                    }
                }
            }
        });


         */

        if (user!=null) {
            db.collection("subsusers").document(user.getUid()).collection("watchlist").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                    } else {
                        List<watchlist> movies = new ArrayList<>();


                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            watchlist gigi = doc.toObject(watchlist.class);
                            movies.add(gigi);

                        }

                        if (movies.size() > 0) {
                            vi.setVisibility(View.VISIBLE);
                            RecyclerView view1 = view.findViewById(R.id.keep);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            keepw adapter = new keepw(getActivity(), movies);
                            view1.setAdapter(adapter);
                        }else {
                            vi.setVisibility(View.GONE);
                        }
                    }
                }
            });


        }



        /*
        ge.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<genrely> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        genrely gigi = doc.toObject(genrely.class);
                        gigi.setId(doc.getId());
                        movies.add(gigi);

                    }

                    if (movies.size() > 0) {



                        scrollView.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(View.INVISIBLE);
                        RecyclerView view1 = view.findViewById(R.id.yiyii);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        Adaptergen adapter = new Adaptergen(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });



         */




        series.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<SeriesObject> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        SeriesObject gigi = doc.toObject(SeriesObject.class);
                        gigi.setVideo(doc.getId());
                        gigi.setUploader(series.getId());
                        movies.add(gigi);

                    }

                    if (movies.size() > 0) {
                        scrollView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);

                        RecyclerView view1 = view.findViewById(R.id.ser);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        SeriesAd adapter = new SeriesAd(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });







        memex.whereEqualTo("p",true).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<xference> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        xference gigi = doc.toObject(xference.class);
                        gigi.setId(doc.getId());


                        movies.add(gigi);

                    }

                    if (movies.size() > 0) {

                        RecyclerView view1 = view.findViewById(R.id.first);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        xferenx adapter = new xferenx(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });


        memex.whereEqualTo("p",false).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<xference> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        xference gigi = doc.toObject(xference.class);
                        gigi.setId(doc.getId());
                        movies.add(gigi);

                    }

                    if (movies.size() > 0) {
                        Collections.shuffle(movies);

                        RecyclerView view1 = view.findViewById(R.id.first2);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        xferen adapter = new xferen(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });


        medieval.orderBy("timestamp", Query.Direction.ASCENDING).whereEqualTo("type","prime").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<Movie> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Movie gigi = doc.toObject(Movie.class);
                        gigi.setVideo(doc.getId());
                        gigi.setUploader(medieval.getId());

                        movies.add(gigi);

                        /*
                        if (gigi.getType()!=null) {
                            if (gigi.getType().contains("prime")) {



                            }
                        }

                         */
                    }

                    if (movies.size() > 0) {

                        RecyclerView view1 = view.findViewById(R.id.recommended);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter = new MyItemAdapterxx(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }else {
                        asasa.setVisibility(View.GONE);
                    }
                }
            }
        });



        /*
        if (user!=null) {
            db.collection("subsusers").document(user.getUid()).collection("watchlist").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                    } else {
                        List<watchlist> movies = new ArrayList<>();


                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            watchlist gigi = doc.toObject(watchlist.class);
                            movies.add(gigi);

                        }

                        if (movies.size() > 0) {
                            conn.setVisibility(View.VISIBLE);

                            keepcy.setVisibility(View.VISIBLE);

                            RecyclerView view1 = view.findViewById(R.id.keep);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            keepw adapter = new keepw(getActivity(), movies);
                            view1.setAdapter(adapter);
                        }else {
                            conn.setVisibility(View.GONE);
                            conb.setVisibility(View.GONE);
                        }
                    }
                }
            });


        }

*/

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
                     //   gigi.setVideo(doc.getId());
                     //   gigi.setUploader(medieval.getId());
                        movies.add(gigi);

                    }

                    if (movies.size() > 0) {


                        RecyclerView view1 = view.findViewById(R.id.choices);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter = new MyItemAdapterxx(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });




        /*
        xxxx.orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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


                        Collections.shuffle(x);
                        RecyclerView trendingRecy1=view.findViewById(R.id.choicex);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx2 adapter1 = new MyItemAdapterxx2(getActivity(), x);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
            }
        });

         */

        /*
                        xxxx.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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


                        RecyclerView trendingRecy1=view.findViewById(R.id.choicex);
                        trendingRecy1.setHasFixedSize(true);
                        trendingRecy1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy1.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx2 adapter1 = new MyItemAdapterxx2(getActivity(), x);
                        trendingRecy1.setAdapter(adapter1);

                    }

                }
            }
        });








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
            rem.setText(" You haven't seen any movie yet ");
            conxx.setText(" Recommended ");

            memex.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<quick> movies = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                            quick gigi = documentSnapshot.toObject(quick.class);

                            movies.add(gigi);
                        }
                        int size = movies.size();

                        ArrayList<Streampostion> movieArray=new ArrayList();
                        for (int i = size - 1; i >= 0; i--) {

                            thriller.setVisibility(View.INVISIBLE);
                            quick movie = movies.get(i);

                            Streampostion newsr=new Streampostion(movie.getPhoto(),0,movie.getName(),movie.getLink(),movie.getGenre(),movie.getSubtitle());

                            movieArray.add(newsr);


                        }

                        if (movieArray.size() > 0) {
                            Collections.shuffle(movieArray);
                            RecyclerView view1 = view. findViewById(R.id.recent);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            streamposx adapter = new streamposx(getActivity(), movieArray);
                            view1.setAdapter(adapter);


                        }


                    }                if (!task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            cxc.setVisibility(View.INVISIBLE);
        }
*/

                    /*


        if (user!=null) {
            db.collection("Users").document(user.getUid()).collection("instant").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        xxx = true;
                    }
                    final List<MovieNames> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        MovieNames gigi = documentSnapshot.toObject(MovieNames.class);
                        movies.add(gigi);

                    }
                    int size = movies.size();
                    for (int i = size - 1; i >= 0; i--) {


                        MovieNames movie = movies.get(i);
                        x.add("");
                        x.add(movie.getMovieName());


                    }

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

                                    if (!x.contains(movie.getVideo())) {
                                        movieArrayxxx.add(movie);

                                    }
                                }


                                if (movieArrayxxx.size() > 0) {

                                    RecyclerView view1 = view.findViewById(R.id.first);
                                    laysq3 adapter = new laysq3(getActivity(), movieArrayxxx);
                                    GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                                    view1.setLayoutManager(glm);
                                    view1.setAdapter(adapter);

                                }

                                if (movieArrayxxx.size() > 30) {


                                }


                            }
                            if (!task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            });


        }




        memex.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<quick> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        quick gigi = documentSnapshot.toObject(quick.class);

                        movies.add(gigi);
                    }
                    int size = movies.size();

                    ArrayList movieArray=new ArrayList();
                    for (int i = size - 1; i >= 0; i--) {

                        thriller.setVisibility(View.INVISIBLE);
                        quick movie = movies.get(i);

                        if (!movieArray.contains(movie))
                            movieArray.add(movie);

                    }

                    if (movieArray.size() > 0) {
                        scrollView.setVisibility(View.VISIBLE);
                        RecyclerView view1=view.findViewById(R.id.yiyii);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        populary adapter = new populary(getActivity(), movieArray);
                        view1.setAdapter(adapter);


                    }
                    if (movieArray.size() > 0) {
                        scrollView.setVisibility(View.VISIBLE);
                        RecyclerView view1=view.findViewById(R.id.first);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        first adapter = new first(getActivity(), movieArray);
                        view1.setAdapter(adapter);


                    }


                }                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }


            }
        });

                    */






    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this songsfragment
        return inflater.inflate(R.layout.activity_download, container, false);

    }





    @Override
    public void onPause() {
        super.onPause();

    }


    private void refresh() {


    }



}


































// private PlayerNotificationManager playerNotificationManager;
    /*
    private PlayerNotificationManager.MediaDescriptionAdapter mediaDescriptionAdapter = new PlayerNotificationManager.MediaDescriptionAdapter() {
        @Override
        public String getCurrentSubText(Player player) {
            return "Keep Listening. Don't Stop Til' U Get Enough";
        }

        @Override
        public String getCurrentContentTitle(Player player) {
            return "AirMix DJ";
        }

        @Override
        public PendingIntent createCurrentContentIntent(Player player) {
            return null;
        }

        @Override
        public String getCurrentContentText(Player player) {
            return null;
        }

        @Override
        public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {

            Bitmap bitmap= BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.p);
            return bitmap;
        }
    };
    */

      /*
        collectionReferen.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<Movie> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        Movie gigi = documentSnapshot.toObject(Movie.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(collectionReferen.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<Movie> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        int rand = new Random().nextInt(size);
                        Movie movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            if (movie.getType()!=null) {
                                if (movie.getType().toLowerCase().trim().contains("trend")) {
                                    movieArrayList.add(movie);
                                }
                            }
                        if (movie.getType()!=null) {
                            if (movie.getType().toLowerCase().trim().contains("soon")) {
                                movieArrayList.remove(movie);
                            }

                        }
                    }
                    if (movieArrayList.size() > 0) {

                        progressBarx.setVisibility(View.GONE);
                        rotateLoading.stop();
                        rotateLoading.setVisibility(View.GONE);
                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifi);


                        view1.setHasFixedSize(true);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        lay adapter = new lay(getActivity(), movieArrayList);
                        view1.setAdapter(adapter);

                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });


        album.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<albums> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        albums gigi=documentSnapshot.toObject(albums.class);
                        gigi.setId(documentSnapshot.getId());
                        gigi.setId2(album.getId());
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
                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifii);
                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(glm);
                        card2 adapter = new card2(getActivity(), movieArrayList);
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


        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<music> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        music gigi=documentSnapshot.toObject(music.class);

                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<music> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){
                        int rand = new Random().nextInt(size);
                        music movie=movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);



                    }
                    if (movieArrayList.size()>0){

                        //part1.setVisibility(View.VISIBLE);
                        RecyclerView view1 = (RecyclerView) view.findViewById(R.id.fifi);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
                        musicadapter adapter = new musicadapter(getActivity(), movieArrayList);
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

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 200) {
            int currTime = data.getIntExtra("curTime", 0);
            simpleExoPlayer.seekTo(currTime);
        }
    }

    {
        updatePlayer = new Runnable() {
            @Override
            public void run() {
                switch (simpleExoPlayer.getPlaybackState()) {
                    case ExoPlayer.STATE_BUFFERING:
                        //progressBar.setVisibility(View.VISIBLE);
                        break;

                    case ExoPlayer.STATE_IDLE:


                        break;

                    case ExoPlayer.STATE_ENDED:


                        simpleExoPlayer.seekTo(0);
                        playpause.setImageResource(R.drawable.quantum_ic_play_circle_filled_white_36);
                        simpleExoPlayer.setPlayWhenReady(true);

                      //  isPlaying = false;
                       // simpleExoPlayer.setPlayWhenReady(false);
                       // playpause.setImageResource(R.drawable.quantum_ic_play_circle_filled_white_36);
                      //  tctc.setTextColor(Color.WHITE);
                        //progressBar.setVisibility(View.GONE);
                        break;


                    case ExoPlayer.STATE_READY:
                        //progressBar.setVisibility(View.GONE);

                        break;
                    default:
                        break;
                }



                mainHandler.postDelayed(updatePlayer, 200);
            }
        };

    }*/


/*

    @Override
    public void onPause() {
        super.onPause();
        if (simpleExoPlayer!=null) {
            simpleExoPlayer.setPlayWhenReady(false);
            rotateLoading.setVisibility(View.INVISIBLE);

            // button1.setVisibility(View.VISIBLE);
            playpause.setImageResource(R.drawable.quantum_ic_play_circle_filled_white_36);

        }

    }

*/

