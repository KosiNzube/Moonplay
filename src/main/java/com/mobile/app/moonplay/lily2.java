package com.mobile.app.moonplay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.mobile.app.moonplay.mainrecycler.trailers2;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class lily2 extends AppCompatActivity {
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
    MovieNames movieNames;
TextView yyy;
TextView Series,seriesButton;
ArrayList<quick> arrayList=new ArrayList<>();
    ArrayList<String> arrayListx=new ArrayList<>();
TextView toprated;
TextView cont;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private AdLoader adLoader;
    private long downloadID;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    ImageView three;
    private boolean doublein=false;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference medieval=db.collection("shorts");
    private CollectionReference genre=db.collection("Genre");
    ArrayList<Object> movieArray=new ArrayList<>();
    private CollectionReference series = db.collection("Series");
    private CollectionReference korean = db.collection("animename");
    private FloatingSearchView mSearchView;
    Button uiui;
    popular adapter;
    ImageView imageView;
    RecyclerView gigi,trendingRecy1;
    TextView popular;
    public static ArrayList<File> fileArrayList=new ArrayList<>();
    File direcyory;
    boolean boo_perm;
    ImageView ioio;
    TextView noInternet;
    public static int REQUEST_PERMISSION=1;
    private Fragment pins;
    private Fragment account;
    private FrameLayout frameLayout;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView view1,x;
    private EditText etsearch;
    ArrayList<MovieNames> movieNames1;
    private ListView list;
    private ListViewAdapter adapterx;
    private String[] moviewList;
    public static ArrayList<MovieNames> movieNamesArrayList;
    public static ArrayList<MovieNames> array_sort;
    public static ArrayList<String> xy;
    int textlength = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lily);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }
        list = (ListView) findViewById(R.id.listView);

        movieNamesArrayList = new ArrayList<>();
        array_sort = new ArrayList<>();
        trendingRecy1=findViewById(R.id.fifi);
        movieNames1 = new ArrayList<>();






        korean.orderBy("name").whereGreaterThan("name","0").limit(100).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                }else {
                    List<anime> movies = new ArrayList<>();


                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        anime gigi = doc.toObject(anime.class);
                        movieNames = new MovieNames(gigi.getName());

                        if (!movies.contains("movie")){
                            movies.add(gigi);
                        }

                        if (!movieNames1.contains(movieNames)){
                            movieNames1.add(movieNames);
                        }
                        if (!movieNamesArrayList.contains(movieNames)) {
                            movieNamesArrayList.add(movieNames);
                            //Collections.shuffle(movieNamesArrayList);
                        }

                        if (!array_sort.contains(movieNames)) {
                            array_sort.add(movieNames);
                            //ollections.shuffle(movieArrayList);
                        }


                        if (!arrayListx.contains(gigi.getName())) {
                            arrayListx.add(gigi.getName());
                            //Collections.shuffle(arrayListx);

                        }

                        //  gigi.setVideo(doc.getId());

                    }

                }
            }
        });
















        adapterx = new ListViewAdapter(lily2.this,movieNamesArrayList);
        list.setAdapter(adapterx);




        if (arrayList.size()>0){


            //  Collections.shuffle(movieNamesArrayList);

            adapterx = new ListViewAdapter(lily2.this,movieNamesArrayList);
            list.setAdapter(adapterx);



        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(lily2.this, array_sort.get(position).getMovieName(), Toast.LENGTH_SHORT).show();

                Intent ne=new Intent(lily2.this, trailers2.class);
                ne.putExtra("search",array_sort.get(position).getMovieName());
                startActivity(ne);
            }
        });
        noInternet=findViewById(R.id.back);
        noInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mSearchView=findViewById(R.id.floating_search_view);

      //  mSearchView.setSearchFocused(true);

        if (mSearchView.isSearchBarFocused()){

            trendingRecy1.setVisibility(View.GONE);
        }
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
                adapterx = new ListViewAdapter(lily2.this, array_sort);
                list.setAdapter(adapterx);

            }


            //get suggestions based on newQuery

            //     adapter.getFilter().filter(newQuery);

            //pass them on to the search view


        });





        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {



                Intent ne=new Intent(lily2.this,trailers2.class);
                ne.putExtra("search",searchSuggestion.getBody());
                startActivity(ne);
            }


            @Override
            public void onSearchAction(String currentQuery) {
                Intent ne=new Intent(lily2.this,trailers2.class);
                ne.putExtra("search",currentQuery);
                startActivity(ne);

            }
        });





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }
}
