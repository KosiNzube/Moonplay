package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import com.mobile.app.moonplay.mainrecycler.VerticalSpacingItemDecorator;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerAdapter;
import com.mobile.app.moonplay.mainrecycler.VideoPlayerRecyclerView;


public class trailers extends AppCompatActivity  {
    YouTubeVideoInfoRetriever retriever = new YouTubeVideoInfoRetriever();


    public FirebaseUser user;
    VideoPlayerRecyclerView mRecyclerView;
    Button back;
    TextView textView,albumx,descr, plays;
    ArrayList<instantv> instant=new ArrayList<>();
    ArrayList<instantv> instantx=new ArrayList<>();
    public static   String imagex;
    public  static String namex;
    public   String genre;
    LinearLayout progressBar;
    ImageView x;
    String id;
    String cast;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference meme = db.collection("instvid");
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    insadapter insadapter;
    String trailerid="null";
    String site="null";
    String format;
    JSONObject trail=new JSONObject();
    ArrayList<recomagent> tutorialList=new ArrayList();
    public  static   String des;
    private BroadcastReceiver broadcastReceiver;
    private boolean mBound = false;
    ArrayList<String> zx=new ArrayList();
    MaterialCardView materialCardView;
    TextView rem;
    MusicServicex musicService;
    ImageView thums;
    ArrayList<String> viewed=new ArrayList();
    public  ArrayList<instV> movieArrayxxx=new ArrayList();
    String up,video;
    private FirebaseAuth firebaseAuth;
    ShapeableImageView imageView;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicServicex.MusicBinder mServiceBinder = (MusicServicex.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.exit(0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ikox);
        if (Build.VERSION.SDK_INT>=19){
            setwinflag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);

            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true);

            getWindow().setStatusBarColor(Color.rgb(5,5,9));
            getWindow().setNavigationBarColor(Color.BLACK);

        }
        Intent intentx = new Intent(this, MusicServicex.class);
        this.bindService(intentx, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter("com.example.exoplayer.PLAYER_STATUS")
        );
        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");


        thums=findViewById(R.id.thumbs);
        Glide.with(trailers.this).asGif()
                .load(R.raw.tv)
                .diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(thums);




        progressBar=findViewById(R.id.progressBar);




        materialCardView=findViewById(R.id.pausecardyx);




        rem=findViewById(R.id.rem);
        rem.setText(cast.toLowerCase());
        rem.setVisibility(View.INVISIBLE);

        imageView=findViewById(R.id.image_view_profile_pic);


        x=findViewById(R.id.download);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        textView=findViewById(R.id.noo);
        textView.setVisibility(View.INVISIBLE);


        mRecyclerView=findViewById(R.id.exoPlayerRecyclerView);

        back=findViewById(R.id.backintime);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initView();

    }

    public static void setwinflag(@NotNull Activity instantplay, final int flagTranslucentStatus, boolean b) {




        Window win=instantplay.getWindow();
        WindowManager.LayoutParams winp=win.getAttributes();
        if (b){
            winp.flags|=flagTranslucentStatus;
        }else {
            winp.flags&= ~flagTranslucentStatus;
        }
        win.setAttributes(winp);
    }


    @SuppressLint("WrongConstant")
    private void initView() {
        ///////////////////////////////////////////////////////////////
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Date date = new Date();
        sdf.format(date);
        Timestamp timestamp = new Timestamp(date);



        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://anime-recommender.p.rapidapi.com/?anime_title="+cast+"&number_of_anime=18",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        try {

                          //  rem.setVisibility(View.VISIBLE);
                            String crappyPrefix = "null";


                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named tutorial inside the object
                            //so here we are getting that json array
                            JSONArray tutorialsArray = new JSONArray(obj.get("data").toString()); // Works!!!



                            //now looping through all the elements of the json array
                            for (int i = 0; i < tutorialsArray.length(); i++) {
                                JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);
                                JSONObject coverImage =tutorialsObject.getJSONObject("coverImage");
                                JSONObject tit =tutorialsObject.getJSONObject("title");




                                if (tutorialsObject.get("trailer").toString().contains("null")){
                                    trail=tit;

                                }else {
                                    trail =tutorialsObject.getJSONObject("trailer");
                                    trailerid=trail.getString("id");
                                    site=trail.getString("site");
                                }

                                String id=tutorialsObject.getString("id");
                                String cover=coverImage.getString("large");
                                String title=((tit.getString("userPreferred").contains("null")?"No title":tit.getString("userPreferred")));
                                String description=((tutorialsObject.getString("description").contains("null")?"No synopsis available ":tutorialsObject.getString("description")));

                                String banner=tutorialsObject.getString("bannerImage");
                                String avrg=tutorialsObject.getString("averageScore");
                                format=tutorialsObject.getString("format");
                                String sY=tutorialsObject.getString("seasonYear");

                              //  recomagent recom=new recomagent(banner,cover,id,description,avrg,title,trailerid,site,sY,format);

                                //tutorialList.add(recom);

                                if (trailerid.contains("null")){
                                  //  movieArrayxxx.add(new instV("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/202057130757345734.mp4?alt=media&token=be7b9a2d-a394-40fa-884a-ea104de976dd", description,"id",cover,title,0,banner,"",timestamp));

                                }else {

                                   // retriever.retrieve("md3ryIqKOIQ");
                                    String youtubeLink = "http://youtube.com/watch?v="+trailerid;
                                    instV xx= new instV(youtubeLink, description, sY, cover, title, 0, banner, avrg, timestamp);

                                    if (!movieArrayxxx.contains(xx)) {
                                        movieArrayxxx.add(xx);
                                    }

                                    /*

                                                                        movieArrayxxx.stream().filter(o ->!o.getVideo().contains(xx.getVideo())).forEach(
                                            o ->{

                                            }
                                    );


                                    new YouTubeExtractor(trzil.this) {
                                        @Override
                                        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                                            if (ytFiles != null) {
                                                int itag = 18;
                                                String downloadUrl = ytFiles.get(itag).getUrl();



                                            }
                                        }
                                    }.extract(youtubeLink);
                                */




                                }




                            }


                            if (movieArrayxxx.size() > 0) {

                                ArrayList<instV> xrxr=movieArrayxxx.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(instV::getVideo))),ArrayList::new));
                                rem.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                thums.setVisibility(View.GONE);
                                //Collections.shuffle(movieArrayxxx);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(trailers.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
                                mRecyclerView.addItemDecoration(itemDecorator);


                                mRecyclerView.setMediaObjects(xrxr);
                                VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(xrxr,initGlide());
                                mRecyclerView.setAdapter(adapter);
                                mRecyclerView.setKeepScreenOn(true);

                                adapter.notifyDataSetChanged();
                                mRecyclerView.smoothScrollToPosition(1);
                                SnapHelper mSnapHelper = new PagerSnapHelper();
                                mSnapHelper.attachToRecyclerView(mRecyclerView);




                            }else {
                                progressBar.setVisibility(View.INVISIBLE);
                                TextView textView=findViewById(R.id.wwww);
                                textView.setText("no recommendations available");
                                textView.setVisibility(View.VISIBLE);
                            }





                            //  Toast.makeText(Main13Activityvvv.this, format, Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(trailers.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }

                })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();


                params.put("x-rapidapi-host", "anime-recommender.p.rapidapi.com");
                params.put("x-rapidapi-key", "b2d4eb54c2mshb58db8af9ec6ef9p1c3f90jsn494b02d920ce");




                //..add other headers
                return params;
            }



        };


        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 90000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 90000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });




//        Toast.makeText(getApplicationContext(), tutorialList.size(), Toast.LENGTH_SHORT).show();
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(trailers.this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);










        ////////////////////////////////////////////////////////////////////////////////



        //  mRecyclerView.setLayoutManager(new speedy(this, speedy.VERTICAL, false));
        //  SnapHelper snapHelper = new PagerSnapHelper();
        //  snapHelper.attachToRecyclerView(mRecyclerView);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(mRecyclerView!=null)
            mRecyclerView.onPausePlayer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.paye();

    }


    public RequestManager initGlide(){
        RequestOptions options = new RequestOptions();
        options.isMemoryCacheable();
      //  options.placeholder(R.drawable.background_undo);

        return Glide.with(this).setDefaultRequestOptions(options);
    }




    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();


        overridePendingTransition(0,0);
    }
    @SuppressLint("WrongThread")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        finish();
        overridePendingTransition(0,0);
    }

}
