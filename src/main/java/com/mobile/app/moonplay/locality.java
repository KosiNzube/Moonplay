package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Pair;

import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;
import com.tonyodev.fetch2core.Func;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class locality extends AppCompatActivity implements FetchListener, RewardedVideoAdListener {
    ListView listView;
    ArrayList arrayList=new ArrayList();
    RecyclerView myRecyclerView;
    ImageView imageView,imageVie2w;
    int x;
    DataSource.Factory dataSourceFactory;
    Button buttonCancelTwo, buttonCancelone;
    private static String dirPath;
    RelativeLayout free,sub;
    static int cici;
    private RewardedVideoAd mRewardedVideoAd;
    Fetch fetch;
    Button buttonOne, buttonTwo;
    private Cache downloadCache;
    private ExoDatabaseProvider databaseProvider;
    RelativeLayout relativeLayout;
    int downloadIdOne, downloadIdTwo;
    ProgressBar progressBarOne, progressBarTwo;
    TextView backoo, textViewProgressTwo,m;
    String name;
    private boolean mBound = false;
    String subt;
    private File cacheDir;
    private final long MAX_CACHE_SIZE = 100 * 1024 * 1024;//bytes

    String URL2;
    String photo;
    String size;
    TextView genex;
    DownloadService downloadService  ;
    private AdLoader adLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locality);

        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        /*
        cacheDir=new File(this.getCacheDir(), "media");
        databaseProvider =new ExoDatabaseProvider(this);
        downloadCache=new SimpleCache(cacheDir, new LeastRecentlyUsedCacheEvictor(MAX_CACHE_SIZE), databaseProvider);


         */
        genex=findViewById(R.id.genrex);

        relativeLayout=findViewById(R.id.playb);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(locality.this)
                        .setTitle("Quality: Standard")
                        .setMessage("Subscription needed for much higher resolution")


                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.

                        .setIcon(android.R.drawable.ic_menu_preferences)
                        .show();
            }
        });

        imageVie2w=findViewById(R.id.thumbnail);

        mRewardedVideoAd=MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        textViewProgressTwo=findViewById(R.id.playlist);
        textViewProgressTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(locality.this, onprogress.class);
                startActivity(intent);
            }
        });
        progressBarOne=findViewById(R.id.pro);
        Intent intent = getIntent();
        m=findViewById(R.id.genre);

        subt=intent.getExtras().getString("subt");
        URL2 = intent.getExtras().getString("url");
        name = intent.getExtras().getString("namemovie");
        photo=intent.getExtras().getString("picture");

        size=intent.getExtras().getString("size");
        genex.setText("Quality: Standard("+size+")");
        m.setText(name);
        free=findViewById(R.id.free);
        sub=findViewById(R.id.sub);

        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    progressBarOne.setVisibility(View.VISIBLE);
                    loadRewardedVideoAd();



                    /*
// A download cache should not evict media, so should use a NoopCacheEvictor.
                    BandwidthMeter bandwidthMeter=new DefaultBandwidthMeter();
                    DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(locality.this, getPackageName()), (TransferListener) bandwidthMeter,
                            DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS, DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, true);

// Create a factory for reading the data from the network.
                    DefaultDataSourceFactory ddsf = new DefaultDataSourceFactory(locality.this, (TransferListener) bandwidthMeter, httpDataSourceFactory);
                    dataSourceFactory = new CacheDataSourceFactory(downloadCache, ddsf, CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR | CacheDataSource.FLAG_BLOCK_ON_CACHE);



                    DownloadHelper downloadHelper =
                            DownloadHelper.forMediaItem(
                                    locality.this,
                                    MediaItem.fromUri(URL2),
                                    new DefaultRenderersFactory(locality.this),
                                    dataSourceFactory);
                    downloadHelper.prepare(new DownloadHelper.Callback() {
                        @Override
                        public void onPrepared(DownloadHelper helper) {

                            List<TrackKey> trackKeys=new ArrayList<>();
                            for (int i=0;i<helper.getPeriodCount();i++){
                                TrackGroupArray trackGroupArray=helper.getTrackGroups(i);

                                for (int j=0;j<trackGroupArray.length;j++){
                                    TrackGroup trackGroup=trackGroupArray.get(j);
                                    for (int k=0;k<trackGroup.length;k++){
                                        Format track=trackGroup.getFormat(k);
                                        trackKeys.add(new TrackKey(i,j,k));


                                    }
                                }
                            }

                            downlo(downloadHelper.getDownloadRequest(null).uri.toString(), name,subt);

                        }





                        @Override
                        public void onPrepareError(DownloadHelper helper, IOException e) {

                        }
                    });



                     */

                }else {
                    progressBarOne.setVisibility(View.INVISIBLE);
                    Toast.makeText(locality.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }

                if (mRewardedVideoAd.isLoaded()) {

                    progressBarOne.setVisibility(View.INVISIBLE);
                    mRewardedVideoAd.show();
                }else {

                    //Toast.makeText(locality.this, "Waiting for connection", Toast.LENGTH_SHORT).show();
                }


            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(locality.this, "Feature not yet available", Toast.LENGTH_LONG).show();
            }
        });

        backoo=findViewById(R.id.back);

        backoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });






    }
    private void loadRewardedVideoAd() {



        mRewardedVideoAd.loadAd("ca-app-pub-2149611848096918/6569951624",
                new AdRequest.Builder().build());
    }
    private void downlo(String URL2, String name,String subt) {
        File x=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/IcePlay");
        File x1=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/subtitles");
        if (!x.exists()){
            x.mkdir();
        }
        if (!x1.exists()){
            x1.mkdir();
        }


        String file=x+"/"+name+".mp4";
        String sub=x1+"/"+name+"subtitle"+".srt";
        FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(9)
                .build();

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fetch = Fetch.Impl.getInstance(fetchConfiguration);
                final Request request = new Request(URL2, file);
                request.setPriority(Priority.HIGH);
                request.setNetworkType(NetworkType.ALL);
                request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");

                Request request1=new Request(subt,sub);
                request1.setPriority(Priority.HIGH);
                request1.setNetworkType(NetworkType.ALL);
                request1.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");





                request.setTag(name+"xxxxxx"+photo);
                request1.setTag("srt");
                ArrayList<Request> arrayList=new ArrayList<>();
                arrayList.add(request);
                arrayList.add(request1);

                fetch.enqueue(arrayList, new Func<List<Pair<Request, Error>>>() {
                    @Override
                    public void call(@NotNull List<Pair<Request, Error>> result) {


                    }
                });

            }
        });

        fetch.addListener(this);


    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }
    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        adView = (UnifiedNativeAdView) adView.findViewById(R.id.ad_view);

        // The MediaView will display a video asset if one is present in the ad, and the
        // first image asset otherwise.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Register the view used for each individual asset.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));


        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
            adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
    }

    @Override
    public void onAdded(@NotNull Download download) {
        //Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelled(@NotNull Download download) {

    }

    @Override
    public void onCompleted(@NotNull Download download) {

    }

    @Override
    public void onDeleted(@NotNull Download download) {

    }

    @Override
    public void onDownloadBlockUpdated(@NotNull Download download, @NotNull DownloadBlock downloadBlock, int i) {

    }

    @Override
    public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
        //Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaused(@NotNull Download download) {

    }

    @Override
    public void onProgress(@NotNull Download download, long l, long l1) {


    }

    @Override
    public void onQueued(@NotNull Download download, boolean b) {

    }

    @Override
    public void onRemoved(@NotNull Download download) {

    }

    @Override
    public void onResumed(@NotNull Download download) {

    }

    @Override
    public void onStarted(@NotNull Download download, @NotNull List<? extends DownloadBlock> list, int i) {

    }

    @Override
    public void onWaitingNetwork(@NotNull Download download) {

    }


    @Override
    public void onRewardedVideoAdLoaded() {
       // Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
        progressBarOne.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdClosed() {

        //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        if (isNetworkAvailable()) {
            downlo(URL2, name,subt);
            Toast.makeText(locality.this, "Film currently downloading", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(locality.this, "No internet connection", Toast.LENGTH_LONG).show();
        }
        mRewardedVideoAd.destroy();

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

        if (isNetworkAvailable()) {
            downlo(URL2, name,subt);
            Toast.makeText(locality.this, "Film currently downloading", Toast.LENGTH_LONG).show();
            progressBarOne.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onRewardedVideoCompleted() {


    //0    Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();


    }
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }
}
