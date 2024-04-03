package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;

public class makemusicx extends Fragment {
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
    private  static  int splashyy=15;
    ArrayList arrayList=new ArrayList();
    CardView newp;
    private boolean isPlaying;
    TextView loading,ii,cwatch;
    String name;
    private TextToSpeech mtts;
    public static final int fxx2dance = 6009;
    private ImageView talker;
    //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //   private CollectionReference nolly=db.collection("music");
    File cf=null;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 9976;
    RecyclerView trendingRecyx,foru;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference memexn = db.collection("price");

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

        progressBar=view.findViewById(R.id.adede);
        textView=view.findViewById(R.id.primesub);
        musical=view.findViewById(R.id.prm);
        res=view.findViewById(R.id.res);
        theme=view.findViewById(R.id.theme);
        cast=view.findViewById(R.id.cast);
        vid=view.findViewById(R.id.vid);
        info=view.findViewById(R.id.info);

        if (Main7Activity.sub==true) {
            textView.setText("Subscription "+"("+Main7Activity.nameplam+")");
            musical.setText("Activated");
        }


        memexn.orderBy("timestamp", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<fic> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        fic gigi = documentSnapshot.toObject(fic.class);

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<fic> movieArray=new ArrayList();
                    for (int i = size - 1; i >= 0; i--) {

                        fic movie = movies.get(i);


                        if (!movieArray.contains(movie)){

                            movieArray.add(movie);
                        }


                    }

                    if (movieArray.size() > 0) {

                        progressBar.setVisibility(View.GONE);
                        RecyclerView view1=view.findViewById(R.id.specialx);
                        view1.setHasFixedSize(true);
                        view1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        view1.setItemAnimator(new DefaultItemAnimator());
                        fi adapter = new fi(getActivity(), movieArray);
                        view1.setAdapter(adapter);


                    }





                }
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }


            }
        });



/*


   res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.sub==false) {
                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    intent.putExtra("name", "Weekly");
                    intent.putExtra("amount", 1500);
                    intent.putExtra("usd","$4.81");

                    startActivityForResult(intent, fxx2dance);
                }else if(MainActivity.sub==true){
                    Toast.makeText(getActivity(),"Your on a plan already ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.sub==false) {

                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    intent.putExtra("name", "Monthly");
                    intent.putExtra("amount", 8000);
                    intent.putExtra("usd","$19.24");

                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"Your on a plan already ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView username=view.findViewById(R.id.username);




        cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.sub==false) {

                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    intent.putExtra("name", "6 months");
                    intent.putExtra("amount", 48000);
                    intent.putExtra("usd","$115.43");

                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"Your on a plan already ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.sub==false) {

                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                intent.putExtra("name","Yearly");
                intent.putExtra("amount",60000);
                intent.putExtra("usd","$144.49");

                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"Your on a plan already ", Toast.LENGTH_SHORT).show();
                }

                //  Toast.makeText(getActivity(),"Subscription ", Toast.LENGTH_SHORT).show();
            }
        });



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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==fxx2dance && resultCode==RESULT_OK){


            String cu=data.getExtras().getString("pos");
            String cu2=data.getExtras().getString("len");

            textView.setText("Subscription "+"("+cu+")");
            musical.setText(cu2);
            refresh();

        }


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
        return inflater.inflate(R.layout.makemusic2x, container, false);

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
        Intent intent=getActivity().getIntent();
        getActivity().finish();
        getActivity().overridePendingTransition(0,0);
        startActivity(intent);
        getActivity().overridePendingTransition(0,0);

    }


    
}


