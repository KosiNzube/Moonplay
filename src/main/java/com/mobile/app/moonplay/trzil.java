package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class trzil extends AppCompatActivity {

    String cast;
    TextView x,y,z;
    boolean isloading=false;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> otherx=new ArrayList<>();
    String trailerid="null";
    String site="null";
    String format;
    JSONObject trail=new JSONObject();
    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference korean=db.collection("Spanish");
    private CollectionReference series=db.collection("Series");
    int a;
    MyItemAdapterxxgrid aps;
    public  ArrayList<Movie> movieArrayxxx=new ArrayList();
    Query query;
    TextView zas,ref;
    ArrayList xax=new ArrayList();
    Button button3,xbb;
    TextView sese;
    RecyclerView recyclerView;
    ScrollView scroll;
    LinearLayout aaaa;
    ProgressBar xcx;
    ImageView opti;
    private ImageView thums;
    private RequestQueue requestQueue;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xxxx);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

         requestQueue = Volley.newRequestQueue(trzil.this);
        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");
        a=cast.length();
        a=a/2;

        ref=findViewById(R.id.refresh);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        aaaa=findViewById(R.id.progressBar);

        zas=findViewById(R.id.zas);

        sese=findViewById(R.id.genre);

        sese.setText(cast.toLowerCase());


        zas.setText(" no search results ");

        thums=findViewById(R.id.thumbs);
        Glide.with(trzil.this).asGif()
                .load(R.raw.tv)
                .diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(thums);



        xcx=findViewById(R.id.prozzz);

       // query = medieval.orderBy("timestamp", Query.Direction.ASCENDING).limit(25);

       // aps=new MyItemAdapterxxgrid(this);

        xbb=findViewById(R.id.fix);


        opti=findViewById(R.id.backward);

        scroll=findViewById(R.id.scroll);
       scroll.setVisibility(View.INVISIBLE);
        x=findViewById(R.id.genre);

        button3=findViewById(R.id.xxxx);

        button3.setVisibility(View.GONE);


        x.setText(cast.toLowerCase());

        adede=findViewById(R.id.adede);


        z=findViewById(R.id.backx);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        opti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (isNetworkAvailable()) {
            initView();
        }else {
            aaaa.setVisibility(View.INVISIBLE);
            TextView textView=findViewById(R.id.wwww);
            textView.setText("no internet connection");
            textView.setVisibility(View.VISIBLE);
        }


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
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        aaaa.setVisibility(View.INVISIBLE);
                       // thums.setVisibility(View.GONE);

                        try {

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
                                String title=((tit.getString("english").contains("null")?"No title":tit.getString("english")));
                                String description=((tutorialsObject.getString("description").contains("null")?"No synopsis available ":tutorialsObject.getString("description")));
                                String banner=tutorialsObject.getString("bannerImage");
                                String avrg=tutorialsObject.getString("averageScore");
                                format=tutorialsObject.getString("format");
                                String sY=tutorialsObject.getString("seasonYear");

                                //  recomagent recom=new recomagent(banner,cover,id,description,avrg,title,trailerid,site,sY,format);

                                //tutorialList.add(recom);

                                if (trailerid.contains("null")){
                                  //  movieArrayxxx.add(new Movie(description, sY,cover,banner,title,avrg,"","",avrg,"",timestamp));

                                }else {
                                    // retriever.retrieve("md3ryIqKOIQ");
                                    String youtubeLink = "http://youtube.com/watch?v="+trailerid;
                                    movieArrayxxx.add(new Movie("",description.replaceAll("<br>","--"), sY,banner,cover,title,avrg,"",youtubeLink,avrg,"","",timestamp));


                                    /*
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
                                Collections.shuffle(movieArrayxxx);
                                aaaa.setVisibility(View.INVISIBLE);
                                thums.setVisibility(View.GONE);

                                scroll.setVisibility(View.VISIBLE);


                                RecyclerView view1 = (RecyclerView) findViewById(R.id.yiyii);
                                view1.setHasFixedSize(true);
                                GridLayoutManager glm = new GridLayoutManager(trzil.this, 2);
                                view1.setLayoutManager(glm);
                                MyItemAdapterxxggg22 adapter = new MyItemAdapterxxggg22(trzil.this, movieArrayxxx);
                                view1.setAdapter(adapter);
                                adapter.notifyDataSetChanged();



                            }else {
                                aaaa.setVisibility(View.INVISIBLE);
                                TextView textView=findViewById(R.id.wwww);
                                textView.setText("no recommendations available");
                                textView.setVisibility(View.VISIBLE);
                            }




                            //  Toast.makeText(Main13Activityvvv.this, format, Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            TextView textView=findViewById(R.id.wwww);
                            textView.setText("no recommendations available");
                            textView.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(trzil.this, error.toString(), Toast.LENGTH_SHORT).show();

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


        //adding the string request to request queue
        requestQueue.add(stringRequest);











        ////////////////////////////////////////////////////////////////////////////////



        //  mRecyclerView.setLayoutManager(new speedy(this, speedy.VERTICAL, false));
        //  SnapHelper snapHelper = new PagerSnapHelper();
        //  snapHelper.attachToRecyclerView(mRecyclerView);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        requestQueue.stop();
        requestQueue.getCache().clear();


        finish();
        overridePendingTransition(0,0);


    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }


    private void refresh() {
            /*
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        final String images=preferences1.getString("images","");
        final String Dname=preferences1.getString("names","");
        final String Dlink=preferences1.getString("streamLink","");
        final String geenre=preferences1.getString("geenre","");



        if (Dname.length()>0) {
            Intent intent1 = new Intent(Main7Activity.this, streamplayer4All.class);
            intent1.putExtra("name", Dname);
            intent1.putExtra("video", Dlink);
            intent1.putExtra("genre", geenre);
            intent1.putExtra("photo", images);

            startActivity(intent1);
        }else{
            Toast toast = Toast.makeText(Main7Activity.this,"You haven't watched any video yet", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
      */
        Intent intent=getIntent();
        finish();
        this.overridePendingTransition(0,0);
        startActivity(intent);
        this.overridePendingTransition(0,0);

    }


}
