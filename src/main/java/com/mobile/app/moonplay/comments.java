package com.mobile.app.moonplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.mainrecycler.trailers2;

import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class comments extends AppCompatActivity  {
  public static  String largest;
  TextView dexi,namemo,uploader,gen,reao,mbb,review;
  ImageView imageView,ioio;
  RatingBar ratingBar;
  ArrayList<Movie> array;
  TextView notyet,twenty;
  ImageView down1,downlll;
  ArrayList<Movie> movieArray=new ArrayList();
  ImageView host;
  BottomSheetDialog bottomSheetDialog;
    private TextToSpeech mtts;
    LinearLayout vgx, iceplayer,download;
  String video;
  private RecyclerView recyclerView,trendingRecy;
  String mbi;
  String img;
  String name;
  TextView mb;
  List<parts> arrayLists=new ArrayList<>();
  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private CollectionReference zubi = db.collection("Spanish");
  String string;
  public static  String like;
  ArrayList<String> comedy=new ArrayList<>();
  ArrayList<String> adventure=new ArrayList<>();
  ArrayList<String> action=new ArrayList<>();
  ArrayList<String> horror=new ArrayList<>();
  ArrayList<String> scifi=new ArrayList<>();
  ArrayList<String> romantic=new ArrayList<>();
  ArrayList<String> animation=new ArrayList<>();
  ArrayList<String> crime=new ArrayList<>();
  ArrayList<String> drama=new ArrayList<>();
  ArrayList<String> thriller=new ArrayList<>();
  int wordstring;
  ProgressBar progressBar;
  String a="comedy";
  String b="adventure";
  String c="action";
  String d="horror";
  String e="scifi";
  String f="romance";
  String g="animation";
  String h="thriller";
  String o="drama";
  String z="crime";
  int adv,act;
  static int hor;
  int sci;
  int rom;
  int anime;
  int crim;
  int dra;
  int epi;

  static ArrayList arrayList=new ArrayList();
  static LinkedList list=new LinkedList();
  private String cast;
  private TextView casttext;
  private ImageView imageView2;
  private String x;
  private String m;
  String up;
  String avail;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    overridePendingTransition(0,0);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.koloko);

      List<Dlink> movies = new ArrayList<>();



    //  review=findViewById(R.id.reviews);
    notyet=findViewById(R.id.pausecard);
    notyet.setVisibility(View.GONE);




    twenty=findViewById(R.id.twenty);
    twenty.setVisibility(View.GONE);




    //  createBottomSheetDialog();

    trendingRecy=findViewById(R.id.trendingRecy);





    dexi=findViewById(R.id.textView7);
    // uploader=findViewById(R.id.postl);
    //downlll=findViewById(R.id.downloads);


    notyet=findViewById(R.id.pausecard);



    //ratingBar=findViewById(R.id.rate);

    //reao=findViewById(R.id.resolution);
    //mbb=findViewById(R.id.mib);

    gen=findViewById(R.id.genre);
    Intent intent = getIntent();
    cast=intent.getExtras().getString("res");
    img = intent.getExtras().getString("image");
    name=intent.getExtras().getString("name");
    String dex=intent.getExtras().getString("dex");
     up=intent.getExtras().getString("upl");
    string=intent.getExtras().getString("genre");
    mbi=intent.getExtras().getString("mb");
    video=intent.getExtras().getString("video");
    avail=intent.getExtras().getString("type");
    String trailer=intent.getExtras().getString("trailer");
//    review.setText(mbi.replaceAll("\\.\\s?","\\.\n"));




    progressBar=findViewById(R.id.progressBar);


    initt();

    /*
    if (Main7Activity.sub==true) {
      initt();
    }else {
     // initt();
      progressBar.setVisibility(View.GONE);
      notyet.setVisibility(View.VISIBLE);
      twenty.setVisibility(View.VISIBLE);
    }

*/

    Button buttonz=findViewById(R.id.recap);
    buttonz.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent ne = new Intent(comments.this, trailers2.class);
        ne.putExtra("search", name);
        startActivity(ne);
      }
    });


    Button preview=findViewById(R.id.tease);
    preview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {


        if (trailer != null) {
          if (trailer.contains("http")) {

            Intent intent = new Intent(comments.this, streamplayer4Alltrailer.class);

            intent.putExtra("photo", "");
            intent.putExtra("subt", "");

            intent.putExtra("name", name);
            intent.putExtra("video", trailer);
            intent.putExtra("genre", "");
            //intent.putExtra("pos",itemDataList.get(i).getMio());
            startActivity(intent);
          } else {
            Toast toast = Toast.makeText(comments.this, "no preview available ", Toast.LENGTH_SHORT);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
          }
        }else {
          Toast toast = Toast.makeText(comments.this, "no preview available ", Toast.LENGTH_SHORT);

          toast.setGravity(Gravity.CENTER, 0, 0);
          toast.show();
        }
      }
    });

    Button watchl=findViewById(R.id.play);

    watchl.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        watchl.setEnabled(false);
        Date date1=new Date();
        Timestamp timestamp=new Timestamp(date1);
        Movie movie=new Movie("",dex,string,img,mbi,name,"",video,up,cast,trailer,"",timestamp);


        movie.setTimestamp(timestamp);
        Main7Activity.db.collection("subusers").document(Main7Activity.user.getUid()).collection("watchl").add(movie).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
          @Override
          public void onSuccess(DocumentReference documentReference) {

            Toast toast = Toast.makeText(comments.this, name+" Added ", Toast.LENGTH_SHORT);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
          }
        });

      }
    });


    if (string.length()>4) {

      gen.setText(string);
    }else {
      gen.setText("Rating: "+string);
    }
    //   uploader.setText(up);


    imageView=findViewById(R.id.thumbnail);
    imageView2=findViewById(R.id.itemImage);



    casttext=findViewById(R.id.cast);

    if (cast.length()>6) {
      casttext.setText("Cast:" + " " + cast);
    }else {
      casttext.setText("Year:" + " " + cast);
    }

    dexi.setText(dex);
    namemo=findViewById(R.id.moviename);
    namemo.setText(name);
    ioio=findViewById(R.id.videoView1);


    if (mbi.startsWith("http")){
      Glide.with(comments.this)
              .load(mbi)
              .centerCrop()
              .into(imageView);

    }else {
      Glide.with(comments.this)
              .load(img)
              .centerCrop()
              .into(imageView);

    }

    Glide.with(comments.this)
            .load(img)
            .centerCrop()
            .into(imageView2);


    /*

    zubi.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
          List<Movie> movies = new ArrayList<>();
          for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
            Movie gigi = documentSnapshot.toObject(Movie.class);
            gigi.setVideo(documentSnapshot.getId());
            gigi.setUploader(zubi.getId());

            movies.add(gigi);
          }
          int size = movies.size();


          for (int i = size - 1; i >= 0; i--) {

            Movie movie = movies.get(i);
            if (!movieArray.contains(movie))
              movieArray.add(movie);

          }
          if (movieArray.size() > 0) {
            Collections.shuffle(movieArray);
            RecyclerView trendingRecy1=findViewById(R.id.trendingRecy);
            trendingRecy1.setHasFixedSize(true);
            trendingRecy1.setLayoutManager(new LinearLayoutManager(comments.this, LinearLayoutManager.HORIZONTAL, false));
            trendingRecy1.setItemAnimator(new DefaultItemAnimator());
            MyItemAdapterxx adapter1 = new MyItemAdapterxx(comments.this, movieArray);
            trendingRecy1.setAdapter(adapter1);


          }

        }                if (!task.isSuccessful()) {
          Toast.makeText(comments.this, "Could not connect", Toast.LENGTH_SHORT).show();
        }


      }
    });


    if (avail!=null) {

      if (avail.contains("null")) {

        progressBar.setVisibility(View.INVISIBLE);
        notyet.setVisibility(View.VISIBLE);
        twenty.setVisibility(View.VISIBLE);


      }
      if (avail.contains("soon")){
        progressBar.setVisibility(View.INVISIBLE);
        notyet.setVisibility(View.VISIBLE);
        twenty.setVisibility(View.VISIBLE);
        twenty.setText("Coming soon");
        notyet.setText(" Movie not yet available ");

      }
    }

*/


  }

  private void initt() {
    notyet.setText(" Currently unavailable ");
    twenty.setText(" Check later");
    try {
      if (video.length() > 5) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection(up);
        collectionReference = collectionReference.document(video).collection("parts");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {


              final List<Dlink> movies = new ArrayList<>();
              for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                Dlink gigi = documentSnapshot.toObject(Dlink.class);
                gigi.setGenre(string);


                gigi.setX(mbi);
                gigi.setPhoto(img);


                gigi.setOriginame(name);
                movies.add(gigi);

                if (movies.size() == 0) {


                  if (isNetworkAvailable()) {

                  }
                }
              }


              int size = movies.size();
              ArrayList<Dlink> movieArrayList = new ArrayList<>();
              for (int i = size - 1; i >= 0; i--) {

                Dlink movie = movies.get(i);
                if (!movieArrayList.contains(movie))
                  movieArrayList.add(movie);

                        /*
                       if (movie.getType().contains("3")){
                           movieArrayList.remove(movie);
                       }
                       */
              }
              if (movieArrayList.size() > 0) {

                progressBar.setVisibility(View.INVISIBLE);


                RecyclerView view1 = (RecyclerView) findViewById(R.id.movies);
                view1.setHasFixedSize(true);
                view1.setLayoutManager(new speedy(comments.this, speedy.VERTICAL, false));
                DlinkAdapter adapter = new DlinkAdapter(comments.this, movieArrayList);
                view1.setAdapter(adapter);
              } else {
                progressBar.setVisibility(View.INVISIBLE);
                notyet.setVisibility(View.VISIBLE);
                twenty.setVisibility(View.VISIBLE);

              }

              if (movieArrayList.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
                notyet.setVisibility(View.VISIBLE);
                twenty.setVisibility(View.VISIBLE);


              }


            }
            if (!task.isSuccessful()) {
              Toast.makeText(comments.this, "Could not connect", Toast.LENGTH_SHORT).show();
            }
          }
        });
      }else {
        progressBar.setVisibility(View.INVISIBLE);
        notyet.setVisibility(View.VISIBLE);
        twenty.setVisibility(View.VISIBLE);

      }

    }catch (OutOfMemoryError e){
      e.printStackTrace();
    }
  }

  private boolean isNetworkAvailable(){
    ConnectivityManager connectivityManager= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo active=connectivityManager.getActiveNetworkInfo();
    return active !=null && active.isConnected();

  }

  private int findLargestInt(int size, int size1, int size2, int size3, int size4, int size5, int size6, int size7, int size8, int size9) {
    int[] decmax = {size, size1, size2, size3,size4,size5,size6,size7,size8,size9};
    List<Integer> list=new ArrayList<Integer>();
    for (int i=0;i<decmax.length;i++){
      list.add(decmax[i]);
    }
    return Collections.max(list);


  }


  private void startDownload(String video, String name) {
    DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(video));
    request.setTitle("MagicPlay");
    request.setDescription(name+ "         ");
    request.allowScanningByMediaScanner();
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
    mManager.enqueue(request);
    download.setEnabled(false);
  }

  public void dosomething(){
    ActivityManager.MemoryInfo memoryInfo=getAvailableMemory();
    if (!memoryInfo.lowMemory){


    }
  }
    private void speaak(String h) {
        mtts.setLanguage(Locale.US);

        mtts.speak(h, TextToSpeech.QUEUE_FLUSH,null);
    }

  private ActivityManager.MemoryInfo getAvailableMemory() {
    ActivityManager activityManager= (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
    ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
    activityManager.getMemoryInfo(memoryInfo);
    return memoryInfo;
  }

  private void refresh() {

    Intent intent=new Intent(comments.this,Main7Activity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);

  }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mtts!=null) {
            mtts.shutdown();
            mtts.stop();
        }

    }
}
