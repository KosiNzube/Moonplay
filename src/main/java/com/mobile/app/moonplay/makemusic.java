package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class  makemusic extends Fragment {
    RecyclerView trendingRecy,con,popo,action,randomrecy;
    ProgressBar progressBar;
    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView,musical,romance,thriller,random;
    ImageView button,koko;
    CardView cardView,res,theme,cast,vid,info;
    int requestCode = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    ImageView playpause;
    private  static  int splashy=10;
    SimpleExoPlayer simpleExoPlayer;
    private  static  int splashyy=15;
    ArrayList arrayList=new ArrayList();
    CardView newp;
    private boolean isPlaying;
    TextView loading,ii,cwatch;
    String name;
    private SimpleExoPlayer videoPlayer=null;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private ArrayList<instantv> mediaObjectListx = new ArrayList<>();
    private TextToSpeech mtts;
    private ImageView talker;
    ArrayList<genx> gen = new ArrayList<>();
    ArrayList<music> sound = new ArrayList<>();
    //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //   private CollectionReference nolly=db.collection("music");
    LeastRecentlyUsedCacheEvictor ce=null;
    File cf=null;
    SimpleCache cac;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    RecyclerView trendingRecyx,foru;

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

        res=view.findViewById(R.id.res);
        theme=view.findViewById(R.id.theme);
        cast=view.findViewById(R.id.cast);
        vid=view.findViewById(R.id.vid);
        info=view.findViewById(R.id.info);


        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Ai Video Compressor")
                        .setMessage("High resolution with little data consumption")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        .setIcon(android.R.drawable.ic_menu_preferences)
                        .show();
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        TextView username=view.findViewById(R.id.username);
        username.setText("Anonymous");



        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("About this app")
                        .setMessage("First version(1.0). Created and distributed by IcePlay")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        .setIcon(android.R.drawable.ic_menu_preferences)
                        .show();


            }
        });

        cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Subscription needed")
                        .setMessage("Not currently available for Free version. Subscribe to Prime to cast contents to big screen and other privileges")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        .setIcon(android.R.drawable.ic_menu_preferences)
                        .show();

                //  Toast.makeText(getActivity(),"Subscription ", Toast.LENGTH_SHORT).show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


/*

        trendingRecy=view.findViewById(R.id.yiyii);
        trendingRecyx=view.findViewById(R.id.yiyiix);
        gen=new ArrayList<>();

        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Fsound-classical-music.gif?alt=media&token=f19d91a4-5709-4420-9ef2-73aa377f0f0c","Trap beats",14,"Drugs and paper","x","y"));
        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(7).gif?alt=media&token=082615fd-3e99-40ca-b27e-aef31ce3d485","Trap beats",14,"Drugs and paper","x","y"));
        gen.add(new genx("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(12).gif?alt=media&token=a335088a-4dd3-455f-ad1b-4bc6e582ef7f","Afro beats",14,"Experience Africa through music","x","y"));



        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Pop rock",3,false));
        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));
        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Afro beats",3,false));
        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Dark R&B",3,false));
        sound.add(new music("https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Soft-ambient-track-relaxing-travel.mp3?alt=media&token=079a851b-2a14-424a-8418-7699e1469d24","Calm Music",3,false));


        foru=view.findViewById(R.id.foru);

        foru.setHasFixedSize(true);
        foru.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        foru.setItemAnimator(new DefaultItemAnimator());
        genmusic g = new genmusic(getActivity(), sound);
        foru.setAdapter(g);


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
        return inflater.inflate(R.layout.makemusic2, container, false);

    }




}


