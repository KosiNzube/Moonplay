package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class disco extends Fragment {
    RecyclerView trendingRecy,con,popo,action,randomrecy;
    ProgressBar progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView,musical,romance,thriller,random;
    ImageView button,koko;
    int requestCode = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    ImageView playpause;
    private  static  int splashy=10;
    SimpleExoPlayer simpleExoPlayer;
    private  static  int splashyy=15;
    ArrayList arrayList=new ArrayList();
    private boolean isPlaying;
    TextView loading,ii,cwatch;
    String name;
    private SimpleExoPlayer videoPlayer=null;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private ArrayList<instantv> mediaObjectListx = new ArrayList<>();
    private TextToSpeech mtts;
    private ImageView talker;
    ArrayList<genx> gen = new ArrayList<>();
    ArrayList<musicw> sound = new ArrayList<>();
    //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
 //   private CollectionReference nolly=db.collection("music");
    LeastRecentlyUsedCacheEvictor ce=null;
    File cf=null;
    SimpleCache cac;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    RecyclerView trendingRecyx,foru,forui,foruix;
    private List<beat> audioItemList;
    ExoPlayerRecyclerView mRecyclerView;
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }

        trendingRecy=view.findViewById(R.id.yiyii);

        audioItemList=new ArrayList<beat>();
        audioItemList.add(new beat("https://www","8mins","Forever","http://www.",false,"Xcober","calm","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Heaven","http://www.",true,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Uranium","http://www.",false,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Tail Fox","http://www.",false,"Xcober","r&b","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Forever","http://www.",false,"Xcober","calm","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Heaven","http://www.",true,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Uranium","http://www.",false,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Tail Fox","http://www.",false,"Xcober","r&b","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        initView();
        mRecyclerView.setMediaObjects(mediaObjectList);
        mAdapter = new MediaRecyclerAdapter(mediaObjectList, initGlide());
        //Set Adapter
        mRecyclerView.setAdapter(mAdapter);
        if (firstTime) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.playVideo(false);
                }
            });
            firstTime = false;
        }






/*
        nolly.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<genx> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        genx gigi = documentSnapshot.toObject(genx.class);
                        gigi.setId(documentSnapshot.getId());
                        gigi.setId(nolly.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<genx> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {

                        genx movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);

                    }
                    if (movieArrayList.size() > 0) {


                        trendingRecy.setHasFixedSize(true);
                        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        trendingRecy.setItemAnimator(new DefaultItemAnimator());
                        MyItemAdapterxx adapter = new MyItemAdapterxx(getActivity(), movieArrayList);
                        trendingRecy.setAdapter(adapter);


                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });




*/









        }
    @SuppressLint("WrongConstant")

    private void initView() {
        mRecyclerView = mRecyclerView.findViewById(R.id.exoPlayerRecyclerView);
        mRecyclerView.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
    }
    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


    @Override
    public void onStart () {
        super.onStart();
    }
    private void speaak(String h) {
        mtts.setLanguage(Locale.US);

        mtts.setPitch(0.4f);
        mtts.setSpeechRate(.8f);
        mtts.speak(h, TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.aires, container, false);

    }




}
