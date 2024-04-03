package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.auth.AuthUI;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.alirezabdn.wp7progress.WP7ProgressBar;


public class recommeder extends Fragment  {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView  musical, romance, thriller, random;
    CardView textView;
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

    ArrayList<animecard> tutorialList=new ArrayList();
    public static  int AUTH_REQUEST_CODE=7192;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private List<AuthUI.IdpConfig> providers;

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
    int x;//action
    int y;//romance
    private CollectionReference meme = db.collection("shorts");
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

    FirebaseUser user;
    private String xqq;

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


        loadTutorialList(view);








    }

    private void loadTutorialList(View view) {
        //getting the progressbar
        final WP7ProgressBar progressBar =  view.findViewById(R.id.wp7progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://jikan1.p.rapidapi.com/top/anime/1/upcoming",
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named tutorial inside the object
                            //so here we are getting that json array

                            JSONArray tutorialsArray = obj.getJSONArray("top");



                            //now looping through all the elements of the json array
                            for (int i = 0; i < tutorialsArray.length(); i++) {
                                JSONObject jsonObject = tutorialsArray.getJSONObject(i);
                                 xqq= jsonObject.getString("title");

                                 String url=jsonObject.getString("url");
                                 String image_url=jsonObject.getString("image_url");
                                 String type=jsonObject.getString("type");
                                 int mal_id=jsonObject.getInt("mal_id");
                                 int rank=jsonObject.getInt("rank");
                                 String start_date=jsonObject.getString("start_date");

                                 animecard xx=new animecard(xqq,url,image_url,type,start_date,mal_id,rank);



                                 tutorialList.add(xx);



                                //getting the json object of the particular index inside the array
                            }

                            //creating custom adapter object
                            RecyclerView view1=view.findViewById(R.id.yiyii);
                            view1.setHasFixedSize(true);
                            view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            view1.setItemAnimator(new DefaultItemAnimator());
                            animecardholder adapter = new animecardholder(getActivity(),tutorialList);
                            view1.setAdapter(adapter);


                            //adding the adapter to listview



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occur
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                params.put("x-rapidapi-host", "jikan1.p.rapidapi.com");
                params.put("x-rapidapi-key", "b2d4eb54c2mshb58db8af9ec6ef9p1c3f90jsn494b02d920ce");



                //..add other headers
                return params;
            }



        };




//        Toast.makeText(getApplicationContext(), tutorialList.size(), Toast.LENGTH_SHORT).show();
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this songsfragment
        return inflater.inflate(R.layout.animerecom, container, false);

    }





    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
