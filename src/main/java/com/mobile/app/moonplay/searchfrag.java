package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class searchfrag extends Fragment  {
    private ListViewAdapter adapterx;
    ArrayList<String> arrayListx=new ArrayList<>();
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
    private static int splashyy = 15;
    ArrayList arrayList = new ArrayList();
    List<Movie> bookList;

    private CollectionReference medieval=db.collection("shorts");

    int textlength = 0;
    private CollectionReference valbum = db.collection("Valbum");
    private FloatingSearchView mSearchView;
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

    private CollectionReference meme = db.collection("instvid");
    int h;//crime
    ArrayList<Movie> movieArrayLists = new ArrayList<>();
    String vid;
    int x;//action
    int y;//romance

    private CollectionReference memex = db.collection("Italy");
    List<genrex> agentList;
    ProgressBar progressBarx;
    List<music> musicList;
    Button button3, studio;
    FloatingActionButton v;
    //ImageView album;
    TextView tctc;
    ImageView back;
    private TextToSpeech mtts;
    RelativeLayout relative, part1, part2;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    ArrayList<video> shorts = new ArrayList<>();
    public static ArrayList<MovieNames> movieNamesArrayList;
    public static ArrayList<MovieNames> array_sort;
    private ListView list;

    RecyclerView view1;
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

        list = (ListView) view.findViewById(R.id.listView);

        movieNamesArrayList = new ArrayList<>();
        array_sort = new ArrayList<>();


        medieval.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    List<quick> movies=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot: Objects.requireNonNull(task.getResult())){
                        quick gigi=documentSnapshot.toObject(quick.class);

                        movies.add(gigi);
                    }
                    int size=movies.size();
                    ArrayList<Movie> movieArrayList=new ArrayList<>();
                    for (int i=size-1;i>=0;i--){

                        int rand = new Random().nextInt(size);

                        quick movie=movies.get(i);


                        MovieNames movieNames = new MovieNames(movie.getName());


                        if (!arrayList.contains(movie))
                            arrayList.add(movie);
                        //Collections.shuffle(arrayList);

                        if (!movieNamesArrayList.contains(movieNames)) {
                            movieNamesArrayList.add(movieNames);
                            //Collections.shuffle(movieNamesArrayList);
                        }

                        if (!array_sort.contains(movieNames)) {
                            array_sort.add(movieNames);
                            //ollections.shuffle(movieArrayList);
                        }



                        if (!arrayListx.contains(movie.getName())) {
                            arrayListx.add(movie.getName());
                            //Collections.shuffle(arrayListx);

                        }
                        if (arrayList.size()>0){


                            //  Collections.shuffle(movieNamesArrayList);

                            adapterx = new ListViewAdapter(getActivity(),movieNamesArrayList);
                            list.setAdapter(adapterx);



                        }




                    }

                }
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(lily.this, array_sort.get(position).getMovieName(), Toast.LENGTH_SHORT).show();

                Intent ne=new Intent(getActivity(),Main13Activity.class);
                ne.putExtra("search",array_sort.get(position).getMovieName());
                startActivity(ne);
            }
        });
        mSearchView=view.findViewById(R.id.floating_search_view);

        mSearchView.setShowMoveUpSuggestion(true);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                //mSearchView.swapSuggestions(movieArray);


                textlength = newQuery.length();
                array_sort.clear();
                for (int i = 0; i < movieNamesArrayList.size(); i++) {
                    if (textlength <= movieNamesArrayList.get(i).getMovieName().length()) {
                        Log.d("ertyyy", movieNamesArrayList.get(i).getMovieName().toLowerCase().trim());
                        if (movieNamesArrayList.get(i).getMovieName().toLowerCase().trim().contains(
                                newQuery.toString().toLowerCase().trim())) {
                            array_sort.add(movieNamesArrayList.get(i));
                        }
                    }
                }
                adapterx = new ListViewAdapter(getActivity(), array_sort);
                list.setAdapter(adapterx);

            }


            //get suggestions based on newQuery

            //     adapter.getFilter().filter(newQuery);

            //pass them on to the search view


        });





        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {



                Intent ne=new Intent(getActivity(),Main13Activity.class);
                ne.putExtra("search",searchSuggestion.getBody());
                startActivity(ne);
            }


            @Override
            public void onSearchAction(String currentQuery) {
                Intent ne=new Intent(getActivity(),Main13Activity.class);
                ne.putExtra("search",currentQuery);
                startActivity(ne);

            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this songsfragment
        return inflater.inflate(R.layout.searchfrag, container, false);

    }





    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

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

