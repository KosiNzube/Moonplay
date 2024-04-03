package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class Main13Activityvvv extends AppCompatActivity {

    String cast;
    TextView x,y,z;
    boolean isloading=false;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Movie> otherx=new ArrayList<>();
    String format;
    JSONObject trail=new JSONObject();
    ProgressBar adede;
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference korean=db.collection("Spanish");
    private CollectionReference series=db.collection("Series");
    int a;
    MyItemAdapterxxgrid aps;
    String crappyPrefix = "null";
    String site="null";
    Query query;
    TextView zas;
    ArrayList xax=new ArrayList();
    Button button3,xbb;
    TextView sese;
    RecyclerView recyclerView;
    ScrollView scroll;
    ProgressBar xcx;
    String trailerid="null";
    ImageView opti;
    ArrayList<recomagent> tutorialList=new ArrayList();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        Intent intent = getIntent();
        cast=intent.getExtras().getString("search");



        zas=findViewById(R.id.zas);

        sese=findViewById(R.id.genre);

        sese.setText(cast.toLowerCase());


        zas.setText(" no search results ");


        xcx=findViewById(R.id.prozzz);

       // query = medieval.orderBy("timestamp", Query.Direction.ASCENDING).limit(25);

       // aps=new MyItemAdapterxxgrid(this);

        xbb=findViewById(R.id.fix);


        opti=findViewById(R.id.backward);

        scroll=findViewById(R.id.scroll);
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


        loadTutorialList();






    }

    private void loadTutorialList() {
        //getting the progressbar



        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://anime-recommender.p.rapidapi.com/?anime_title="+cast+"&number_of_anime=5",
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        adede.setVisibility(View.INVISIBLE);
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
                                String title=tit.getString("english");

                                String description=tutorialsObject.getString("description");
                                String banner=tutorialsObject.getString("bannerImage");
                                String avrg=tutorialsObject.getString("averageScore");
                                format=tutorialsObject.getString("format");
                                String sY=tutorialsObject.getString("seasonYear");

                                recomagent recom=new recomagent(banner,cover,id,description,avrg,title,trailerid,site,sY,format);

                                tutorialList.add(recom);
                                Toast toast = Toast.makeText(Main13Activityvvv.this,site, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();



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
                        Toast.makeText(Main13Activityvvv.this, error.toString(), Toast.LENGTH_SHORT).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(Main13Activityvvv.this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }

}
