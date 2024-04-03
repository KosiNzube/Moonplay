package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.alirezabdn.wp7progress.WP7ProgressBar;
import com.mobile.app.moonplay.util.ConfigKeys;

public class offthewall2 extends Fragment {
    RecyclerView trendingRecy,con,popo,action,randomrecy;

    private static final int REQUEST_WRITE_PERMISSION = 787;
    private ImageView imageView;
    TextView textView,musical,romance,thriller,random;
    ImageView button,koko;
    CardView cardView,res,theme,cast,vid,info;
    Boolean isScrolling=false;
    int requestCode = 0;
    private SharedPreferences appPreferences;
    String filePath = Environment.getExternalStorageDirectory() + "/AirMix.wav";
    Bitmap bi;
    TextView xyz,abc;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView playpause,result;
    private  static  int splashy=10;
    ArrayList trend=new ArrayList();
    ArrayList randy=new ArrayList();
    private CollectionReference meme = db.collection("instvid");
    SimpleExoPlayer simpleExoPlayer;
    private  static  int splashyy=15;
    Boolean xxx=false;
    ArrayList<String> x=new ArrayList();
    ArrayList arrayList=new ArrayList();
    CardView newp;
    public static ArrayList movieArrayxxx=new ArrayList();
    int currentItems, totalitems, scrollistener;
    private boolean isPlaying;
    TextView loading,ii,cwatch;
    String name;
    private SimpleExoPlayer videoPlayer=null;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private ArrayList<instantv> mediaObjectListx = new ArrayList<>();
    private FirebaseAuth firebaseAuth;
    Button but;
    TextView inter,len,backn;
    int cu=0;
    private CollectionReference medieval = db.collection("Spanish");
    public   FirebaseUser user;
    private TextToSpeech mtts;
    private ImageView talker;
    TextView muse;
    ArrayList<genx> gen = new ArrayList<>();

    ArrayList<music> sound = new ArrayList<>();
    private CollectionReference valbum = db.collection("Valbum");
    RecyclerView trendy,rand;
    //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
 //   private CollectionReference nolly=db.collection("music");
    LeastRecentlyUsedCacheEvictor ce=null;
    File cf=null;
    SimpleCache cac;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    RecyclerView trendingRecyx,foru;
    private CollectionReference series = db.collection("Series");
    ArrayList movieArrayList;
    ScrollView scrollView;
    ArrayList<menu > menustring=new ArrayList<>();
    ArrayList<Streampostion> xxy;
    RelativeLayout conn;

    WP7ProgressBar progressBar;
    Button conb;
    public static ArrayList movieArrayxxxy;
    RelativeLayout conre;
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

        progressBar=view.findViewById(R.id.wp7progressBar);

        if (Main7Activity.user!=null) {
            Main7Activity.db.collection("subusers").document(Main7Activity.user.getUid()).collection("watchl").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                    } else {
                        List<Movie> movies = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {

                            Movie gigi = doc.toObject(Movie.class);
                            gigi.setId(doc.getId());
                         //   gigi.setVideo(doc.getId());
                           // gigi.setUploader(medieval.getId());
                            //  gigi.setVideo(doc.getId());
                            if (!movies.contains(gigi)) {
                                movies.add(gigi);
                            }

                        }

                        if (movies.size() > 0) {
                            TextView textView = view.findViewById(R.id.mus);
                            progressBar.setVisibility(View.INVISIBLE);
                              textView.setVisibility(View.INVISIBLE);

                            //  Collections.shuffle(movieArrayList);
                            RecyclerView trendingRecy1 = view.findViewById(R.id.keep);
                            GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                            trendingRecy1.setLayoutManager(glm);
                            MyItemAdapterxxggg22 adapter1 = new MyItemAdapterxxggg22(getActivity(), movies);
                            trendingRecy1.setAdapter(adapter1);
                        } else if (movies.size() == 0) {
                            progressBar.setVisibility(View.INVISIBLE);
                            TextView textView = view.findViewById(R.id.mus);
                            textView.setVisibility(View.VISIBLE);
                        }


                    }
                }
            });


        }



        }


    private void updateLastPlayed(Uri url, String title)
    {
        //set values
        appPreferences.edit().putString(ConfigKeys.KEY_LAST_PLAYED_URL, url.toString())
                .putString(ConfigKeys.KEY_LAST_PLAYED_TITLE, title).apply();
    }

    private long getResumePosition()
    {
        return appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, 0); //TODO: remove a few seconds (10s)
    }

    private boolean canResumePlayback(Uri url, String title)
    {
        //check if there is a playback position to resume stored
        if (appPreferences.getLong(ConfigKeys.KEY_LAST_PLAYED_POSITION, -1) <= 0) return false;

        //check that url or title is the same as the last played
        return url.toString().equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_URL, ""))
                || title.equalsIgnoreCase(appPreferences.getString(ConfigKeys.KEY_LAST_PLAYED_TITLE, ""));
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
        return inflater.inflate(R.layout.makemusicxxx, container, false);

    }




}
