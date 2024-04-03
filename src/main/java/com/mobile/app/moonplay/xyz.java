package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class xyz extends Fragment {
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

        gen=new ArrayList<>();

        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Fsound-classical-music.gif?alt=media&token=f19d91a4-5709-4420-9ef2-73aa377f0f0c","Trap beats",14,"Drugs and paper","x","y"));
        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(7).gif?alt=media&token=082615fd-3e99-40ca-b27e-aef31ce3d485","Trap beats",14,"Drugs and paper","x","y"));
        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(12).gif?alt=media&token=a335088a-4dd3-455f-ad1b-4bc6e582ef7f","Afro beats",14,"Experience Africa through music","x","y"));

        trendingRecy.setHasFixedSize(true);
        trendingRecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trendingRecy.setItemAnimator(new DefaultItemAnimator());
        MyItemAdapterxxw adapter = new MyItemAdapterxxw(getActivity(), gen);
        trendingRecy.setAdapter(adapter);



        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Pop rock",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Dark R&B",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Pop rock",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Dark R&B",3,false));
        sound.add(new musicw("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));



        foru=view.findViewById(R.id.foru);

        foru.setHasFixedSize(true);
        foru.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        foru.setItemAnimator(new DefaultItemAnimator());
        genmusic g = new genmusic(getActivity(), sound);
        foru.setAdapter(g);

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
        return inflater.inflate(R.layout.xyz, container, false);

    }




}
