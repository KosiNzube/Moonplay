package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.mobile.app.moonplay.mainrecycler.trailers2;

public class xxxx extends Fragment {
    ArrayList m = new ArrayList();
    ImageView imageButton, riri;
    TextView textView, textView2, textView3;
    RecyclerView myRecyclerView;
    RelativeLayout host;
    Button button, conbu;
    TextView playlist;
    Button cardView;
    EditText editText;
    ListView listView;
    ProgressBar loading;
    FloatingActionButton fab;
    MovieNames movieNames;
    TextView yyy;
    TextView Series, seriesButton;
    ArrayList<quick> arrayList = new ArrayList<>();
    ArrayList<String> arrayListx = new ArrayList<>();
    TextView toprated;
    TextView cont;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private AdLoader adLoader;
    private long downloadID;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    ImageView three;
    private boolean doublein = false;
    ViewPager viewPager;


    PageViewAdapter pageViewAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference medieval = db.collection("shorts");
    private CollectionReference genre = db.collection("Genre");
    ArrayList<Object> movieArray = new ArrayList<>();
    private CollectionReference series = db.collection("Series");
    private CollectionReference korean = db.collection("animename");
    private FloatingSearchView mSearchView;
    Button uiui;
    popular adapter;
    ImageView imageView;
    RecyclerView gigi;
    LinearLayout ddedee;
    List<JSONObject> array=new ArrayList<JSONObject>();
    List<algo> alos=new ArrayList<algo>();
    TextView popular;
    public static ArrayList<File> fileArrayList = new ArrayList<>();
    File direcyory;
    boolean boo_perm;
    ImageView ioio;
    TextView noInternet;
    public static int REQUEST_PERMISSION = 1;
    private Fragment pins;
    private Fragment account;
    private FrameLayout frameLayout;
    BottomSheetDialog bottomSheetDialog;
    private EditText etsearch;
    ArrayList<MovieNames> movieNames1;
    private ListView list;
    private ListViewAdapter adapterx;
    private String[] moviewList;
    public static ArrayList<MovieNames> movieNamesArrayList;
    public static ArrayList<MovieNames> array_sort;
    public static ArrayList<String> xy;
    int textlength = 0;
    Button vvv;
    private CollectionReference ge=db.collection("Genre");
    private CollectionReference memex = db.collection("recom");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }

        list = (ListView) view.findViewById(R.id.listView);
        list.setVisibility(View.INVISIBLE);
        ddedee=view.findViewById(R.id.dededed);
        ddedee.setVisibility(View.INVISIBLE);
        movieNamesArrayList = new ArrayList<>();
        array_sort = new ArrayList<>();
        movieNames1 = new ArrayList<>();

        Client client=new Client("1EIQD0HPDF","693621668957f1118f5628198fa88009");
        Index index=client.getIndex("IcePlay");

         vvv=view.findViewById(R.id.searchtxt);







        ge.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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

                        ddedee.setVisibility(View.VISIBLE);

                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);

                        RecyclerView view1 = view.findViewById(R.id.yiyii);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(glm);
                      //  view1.setItemAnimator(new DefaultItemAnimator());
                        Adaptergen adapter = new Adaptergen(getActivity(), movies);
                        view1.setAdapter(adapter);
                    }
                }
            }
        });








         /*
        korean.orderBy("name").whereGreaterThan("name", "0").limit(6000).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                } else {
                    List<anime> movies = new ArrayList<>();



                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        anime gigi = doc.toObject(anime.class);

                        try {
                            JSONObject json = new JSONObject();
                            json.put("name", gigi.getName());
                            json.put("objectID", gigi.getName());


                            array.add(json);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }


                        movieNames = new MovieNames(gigi.getName());

                        if (!movies.contains("movie")) {
                            movies.add(gigi);
                        }





                        if (!movieNames1.contains(movieNames)) {
                            movieNames1.add(movieNames);
                        }
                        if (!movieNamesArrayList.contains(movieNames)) {
                            movieNamesArrayList.add(movieNames);
                            //Collections.shuffle(movieNamesArrayList);
                        }

                        if (!array_sort.contains(movieNames)) {
                            array_sort.add(movieNames);
                            Collections.shuffle(array_sort);

                            adapterx = new ListViewAdapter(getActivity(), array_sort);
                            list.setAdapter(adapterx);

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





        vvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                        if (array.size()>0) {

                            index.addObjectsAsync(new JSONArray(array), null);

                        }


            }
        });



          */





        mSearchView = view.findViewById(R.id.floating_search_view);

        //  mSearchView.setSearchFocused(true);

        if (mSearchView.isSearchBarFocused()) {


        }
      //  mSearchView.setShowMoveUpSuggestion(true);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                //mSearchView.swapSuggestions(movieArray);

              //  textlength = newQuery.length();
             //    array_sort.clear();




                if (newQuery.length()>0) {
                    list.setVisibility(View.VISIBLE);
                    ddedee.setVisibility(View.GONE);

                   // listx.clear();
                    Query query = new Query(newQuery).setAttributesToRetrieve("name").setHitsPerPage(5);

                    index.searchAsync(query, new CompletionHandler() {
                        @Override
                        public void requestCompleted(@Nullable JSONObject content, @Nullable AlgoliaException e) {
                            try {
                                if (content!=null) {
                                    JSONArray hits = content.getJSONArray("hits");
                                    ArrayList<MovieNames> listx = new ArrayList<>();
                                    for (int i = 0; i < hits.length(); i++) {
                                        JSONObject jsonObject = hits.getJSONObject(i);
                                        String name = jsonObject.getString("name");

                                        listx.add(new MovieNames(name));
                                    }
                                    adapterx = new ListViewAdapter(getActivity(), listx);

                                    list.setAdapter(adapterx);
                                    adapterx.notifyDataSetChanged();


                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //  Toast.makeText(getActivity(), array_sort.get(position).getMovieName(), Toast.LENGTH_SHORT).show();

                                            Intent ne = new Intent(getActivity(), trailers2.class);
                                            ne.putExtra("search", listx.get(position).getMovieName());
                                            startActivity(ne);
                                        }
                                    });

                                }
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }
                    });
                }else {
                    ddedee.setVisibility(View.VISIBLE);
                    list.setVisibility(View.INVISIBLE);

                }





            }

            //get suggestions based on newQuery

            //     adapter.getFilter().filter(newQuery);

            //pass them on to the search view


        });


        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {


                Intent ne = new Intent(getActivity(), trailers2.class);
                ne.putExtra("search", searchSuggestion.getBody());
                startActivity(ne);
            }


            @Override
            public void onSearchAction(String currentQuery) {

                if (currentQuery.length()>0) {
                    Intent ne = new Intent(getActivity(), trailers2.class);
                    ne.putExtra("search", currentQuery);
                    startActivity(ne);
                }
            }
        });


    }





    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_lily, container, false);

    }




}
