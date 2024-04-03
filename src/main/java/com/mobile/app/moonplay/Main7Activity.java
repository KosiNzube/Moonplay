package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main7Activity extends AppCompatActivity  {
ArrayList m=new ArrayList();
ImageView imageButton,riri;
TextView textView;
MusicService musicService;

private SharedPreferences appPreferences;
Button trend, lib,luno;
    ImageView pl;
    static ImageView playlist;
SwipeRefreshLayout swipeRefreshLayout;
    public static final int fxx = 1009;
ListView listView;

static ImageView acc,setting;


    static ImageView yyy;
    Fragment fragment=null;
    static boolean sub=false;
    static String id,nameplam;

    private boolean doublein=false;
    static ViewPager viewPager;
    PageViewAdapter pageViewAdapter;

    private boolean isPlaying;
    static ImageView imageView;
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView gigi;
    TextView popular,genre,context,gen;
    private boolean mBound = false;
    ImageView ioio,xx;
    private int sWidth,sHeight;
    private View decorView;
    private int uiImmersiveOptions;
    private int brightness, mediavolume,device_height,device_width;
    List<quick> movies = new ArrayList<>();
    private Display display;
    private Point size;
    //RelativeLayout playview;
    private FirebaseAuth firebaseAuth;
    static ImageView but;
    TextView inter,len,backn;
    int cu=0;
    public  static FirebaseUser user;
    RelativeLayout payb;
     String images;
     String Dname;
     String Dlink;
     ImageView ref;
     String geenre;
     String subb;
     int vx;
    private CollectionReference memex = db.collection("shorts");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setTheme(R.style.hihiggi);
        setContentView(R.layout.activity_main7);

        but=findViewById(R.id.setting);

        pl=findViewById(R.id.itemImage);
        context=findViewById(R.id.context);
        context.setSelected(true);

        luno=findViewById(R.id.luno);
        gen=findViewById(R.id.gen);

         ref=findViewById(R.id.ref);

         ref.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 refresh();
             }
         });

         xx=findViewById(R.id.love);


        firebaseAuth= FirebaseAuth.getInstance();
        user= firebaseAuth.getCurrentUser();




        //  playview=findViewById(R.id.vx);

        trend=findViewById(R.id.curaye);
        lib=findViewById(R.id.prime);

        setting=findViewById(R.id.setting);
       // trend.setTextColor(Color.WHITE);
        lib.setTextColor(Color.rgb(0,112,128));
        acc=findViewById(R.id.account);


        appPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);


        payb=findViewById(R.id.playb);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        images=preferences1.getString("images","");
        Dname=preferences1.getString("names","");
         Dlink=preferences1.getString("streamLink","");
         geenre=preferences1.getString("geenre","");
         subb=preferences1.getString("subt","");
         vx=preferences1.getInt("uioii",0);




        if (Dname.length()>0) {
            String totDur = String.format("%02d.%02d.%02d",
                    TimeUnit.MILLISECONDS.toHours(vx),
                    TimeUnit.MILLISECONDS.toMinutes(vx) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(vx)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(vx) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(vx)));
            gen.setText(totDur);



            if (Dname.length() > 20) {

                context.setText(Dname);

                Glide.with(Main7Activity.this)
                        .load(images)
                        .diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(pl);

            } else {

                context.setText(Dname);
                Glide.with(Main7Activity.this)
                        .load(images)
                        .diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(pl);

            }
        }
        if (Dname.length()==0){

            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("uioii",0);
            editor.putString("names","Mutafukaz ");
            editor.putString("geenre","Action");
            editor.putString("images", "https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fanime%2FMutafukaz%2Fmuta.jpg?alt=media&token=0b26f9de-022b-4033-a727-16630b81cc0a");
            editor.putString("streamLink", "https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fanime%2FMutafukaz%2FMutafukaz.2017.1080p.BluRay.AAC5.1.x264-Rapta.mp4?alt=media&token=828d71c9-b812-4e47-bcaf-0a6ded2203ca");
            editor.putString("subt","https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/Movies%2Fanime%2FMutafukaz%2FMFKZ.2017.720p.BluRay.x264-%5BYTS.LT%5D.srt?alt=media&token=49896b47-0459-4db8-97b0-be57403ab360");
            editor.commit();

        }

        payb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Dname.length()>0) {
                    Intent intent=new Intent(Main7Activity.this,popo.class);
                    intent.putExtra("photo", images);
                    intent.putExtra("name",Dname);
                    intent.putExtra("video",Dlink);
                    intent.putExtra("genre",geenre);
                    intent.putExtra("pos",vx);
                    intent.putExtra("subt",subb);
                    startActivity(intent);

                }else{
                    Toast toast = Toast.makeText(Main7Activity.this,"You haven't watched any anime yet", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }

            }
        });


        playlist=findViewById(R.id.playlist);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Main7Activity.this, onprogress.class);
                startActivity(intent);
            }
        });




        riri=findViewById(R.id.backln);

        yyy=findViewById(R.id.searchy);


        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0,true);
                luno.setTextColor(Color.WHITE);
                lib.setTextColor(Color.rgb(0,112,128));
                trend.setTextColor(Color.rgb(0,112,128));
                acc.getDrawable().setTint(Color.MAGENTA);
                playlist.getDrawable().setTint(Color.WHITE);
                imageView.getDrawable().setTint(Color.WHITE);
                but.getDrawable().setTint(Color.WHITE);
                yyy.getDrawable().setTint(Color.WHITE);
                // Intent intent = new Intent(Main7Activity.this, lily2.class);
               // startActivity(intent);
            }
        });

        popular=findViewById(R.id.popular);


        imageView=findViewById(R.id.refresh);

        viewPager=findViewById(R.id.viewp);



        pageViewAdapter=new PageViewAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pageViewAdapter);

        viewPager.setOffscreenPageLimit(4+1);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //     Intent intent = new Intent(Main7Activity.this, lily2.class);
               // startActivity(intent);

                viewPager.setCurrentItem(1,true);
                luno.setTextColor(Color.rgb(0,112,128));
                lib.setTextColor(Color.rgb(0,112,128));
                trend.setTextColor(Color.rgb(0,112,128));


                acc.getDrawable().setTint(Color.WHITE);
                playlist.getDrawable().setTint(Color.WHITE);
                imageView.getDrawable().setTint(Color.WHITE);
                but.getDrawable().setTint(Color.WHITE);
                setting.getDrawable().setTint(Color.MAGENTA);
                yyy.getDrawable().setTint(Color.WHITE);

            }
        });


       // genre=findViewById(R.id.genre);
      //  genre.setTextColor(Color.rgb(0,112,128));




        //genre.setText("Movies & Tv");
      //  genre.setTypeface(null,Typeface.BOLD);
//        popular.setTextColor(Color.WHITE);
        viewPager.setCurrentItem(0,true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2,true);
                luno.setTextColor(Color.rgb(0,112,128));
                lib.setTextColor(Color.rgb(0,112,128));
                trend.setTextColor(Color.rgb(0,112,128));


                acc.getDrawable().setTint(Color.WHITE);
                playlist.getDrawable().setTint(Color.WHITE);
                imageView.getDrawable().setTint(Color.MAGENTA);
                but.getDrawable().setTint(Color.WHITE);
                yyy.getDrawable().setTint(Color.WHITE);

            }
        });


        luno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0,true);
                luno.setTextColor(Color.WHITE);
                lib.setTextColor(Color.rgb(0,112,128));
                trend.setTextColor(Color.rgb(0,112,128));


            }
        });



        trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1,true);
                trend.setTextColor(Color.WHITE);
                lib.setTextColor(Color.rgb(0,112,128));
                luno.setTextColor(Color.rgb(0,112,128));

            }
        });
        lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2,true);
                trend.setTextColor(Color.rgb(0,112,128));
                lib.setTextColor(Color.WHITE);
            }
        });
        yyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(Main7Activity.this, Main5Activity.class);
             //   startActivity(intent);
                viewPager.setCurrentItem(2,true);

                acc.getDrawable().setTint(Color.WHITE);
                playlist.getDrawable().setTint(Color.WHITE);
                imageView.getDrawable().setTint(Color.WHITE);
                but.getDrawable().setTint(Color.WHITE);
                setting.getDrawable().setTint(Color.MAGENTA);
                yyy.getDrawable().setTint(Color.WHITE);


            }
        });







        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


                cu=i;
                onChangetab(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }






    private void refresh() {
        Intent intent=getIntent();
        finish();
        this.overridePendingTransition(0,0);
        startActivity(intent);
        this.overridePendingTransition(0,0);

    }





    private void onChangetab(int i) {

        if (i==1) {
            viewPager.setCurrentItem(1, true);
            trend.setTextColor(Color.WHITE);
            luno.setTextColor(Color.rgb(0,112,128));
            lib.setTextColor(Color.rgb(0,112,128));


        }

        if (i==0) {
            viewPager.setCurrentItem(0,true);

            luno.setTextColor(Color.WHITE);
            lib.setTextColor(Color.rgb(0,112,128));
            trend.setTextColor(Color.rgb(0,112,128));


        }
        if (i==2) {
            viewPager.setCurrentItem(2,true);

            luno.setTextColor(Color.rgb(0,112,128));
            trend.setTextColor(Color.rgb(0,112,128));
            lib.setTextColor(Color.WHITE);


        }
        if (i==3) {
            viewPager.setCurrentItem(3,true);


            luno.setTextColor(Color.rgb(0,112,128));
            trend.setTextColor(Color.rgb(0,112,128));
            lib.setTextColor(Color.rgb(0,112,128));



        }
        if (i==4) {
            viewPager.setCurrentItem(4,true);
            trend.setTextColor(Color.rgb(0,112,128));
            lib.setTextColor(Color.rgb(0,112,128));


        }
        if (i==5) {
            viewPager.setCurrentItem(5,true);
            trend.setTextColor(Color.rgb(0,112,128));
            lib.setTextColor(Color.rgb(0,112,128));


        }



    }

    public  static void xyx(){
         viewPager.setCurrentItem(4,true);


        acc.getDrawable().setTint(Color.WHITE);
        playlist.getDrawable().setTint(Color.WHITE);
        imageView.getDrawable().setTint(Color.WHITE);
        but.getDrawable().setTint(Color.WHITE);
        yyy.getDrawable().setTint(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);





    }


    public void onBackPressed() {


        if (doublein) {
            super.onBackPressed();
            this.finishAffinity();
            return;
        }
        this.doublein = true;


        Toast toast = Toast.makeText(Main7Activity.this, "Press again to exit", Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                doublein = false;

            }
        }, 2000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();




    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        images=preferences1.getString("images","");
        Dname=preferences1.getString("names","");
        Dlink=preferences1.getString("streamLink","");
        geenre=preferences1.getString("geenre","");
        subb=preferences1.getString("subt","");
        vx=preferences1.getInt("uioii",0);



        if (Dname.length()>0) {
            String totDur = String.format("%02d.%02d.%02d",
                    TimeUnit.MILLISECONDS.toHours(vx),
                    TimeUnit.MILLISECONDS.toMinutes(vx) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(vx)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(vx) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(vx)));
            gen.setText(totDur);




            if (Dname.length() > 20) {

                context.setText(Dname);

                Glide.with(Main7Activity.this).load(images).diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(pl);


            } else {

                context.setText(Dname);
                Glide.with(Main7Activity.this).load(images).diskCacheStrategy(DiskCacheStrategy.DATA).centerCrop().into(pl);

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user==null){
            Intent o = new Intent(Main7Activity.this, sub.class);
            startActivity(o);

        }else {
            db.collection("subusers").document(user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    users gigi = value.toObject(users.class);
                    id=gigi.getId();
                    sub=gigi.isSubstate();
                    nameplam=gigi.getName();
                }
            });

        }
    }

}
