package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class jinx extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView progressBar;
    String vi;
    ArrayList<Object> movieArrayListxx = new ArrayList<>();
    TextView textView,romance,random,musy,mus;
    ImageView button;
    Button buttonx,nollymore,bollymore,koreamore,tvbut,animbut,medbut;
    private AdLoader adLoader;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();

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
    ArrayList<Object> movieArray=new ArrayList<>();
    private CollectionReference medieval=db.collection("Italy");
    private CollectionReference medievaly=db.collection("Medieval");
    private CollectionReference xxx=db.collection("noti");
    RecyclerView view1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         view1=view.findViewById(R.id.yiyii);



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
            return inflater.inflate(R.layout.jinko, container, false);

        }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }

}
