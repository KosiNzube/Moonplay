package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.DownloadBlock;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.util.ConfigKeys;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class DlinkAdapter extends RecyclerView.Adapter<DlinkAdapter.MyViewHolder> implements FetchListener{

    private SharedPreferences appPreferences;
    private Fetch fetch;
    private Context context;
    private List<Dlink> itemDataList;
    Notification.Builder builder;


    private MusicService musicService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MusicService.MusicBinder mServiceBinder = (MusicService.MusicBinder) iBinder;
            musicService = mServiceBinder.getService();
            //  mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }

        @Override
        public void onNullBinding(ComponentName name) {

        }
    };
        NotificationManagerCompat nmc;
    Request request;
    private ArrayList<id> id = new ArrayList<>();
    String subx;

    BottomSheetDialog bottomSheetDialog;
    String string,video,img,name,cx;
    private long downloadID;

    public DlinkAdapter(Context context, List<Dlink> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_itemal,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);


        Intent intentx = new Intent(context, MusicService.class);
        context.bindService(intentx, serviceConnection, Context.BIND_AUTO_CREATE);



        myViewHolder.genre.setText(itemDataList.get(i).getName());

        string=itemDataList.get(i).getGenre();
        video=itemDataList.get(i).getVideo();
        name=itemDataList.get(i).getName();
        img=itemDataList.get(i).getPhoto();

        String mb=itemDataList.get(i).getMb();

        if (mb!=null) {
            myViewHolder.txt_item_title.setText(mb);
        }

        /*
 RequestOptions requestOptions = new RequestOptions();
        requestOptions.isMemoryCacheable();
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(itemDataList.get(i).getVideo()).centerCrop().into(myViewHolder.img_item);


        try {
            Glide.with(context)
                    .load(img)
                    .centerCrop()
                    .into(myViewHolder.img_item);
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }

*/

        Glide.with(context)
                .load(itemDataList.get(i).getPhoto())
                .centerCrop()

                .into(myViewHolder.img_item);
        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {



                    Intent intent = new Intent(context, streamplayer4All.class);
                    if (itemDataList.get(i).getX().startsWith("http")) {
                        intent.putExtra("photo", itemDataList.get(i).getX());
                    } else {
                        intent.putExtra("photo", itemDataList.get(i).getPhoto());
                    }

                    if (itemDataList.get(i).getSubtitle()!=null){
                        intent.putExtra("subt",itemDataList.get(i).getSubtitle());
                    }else {
                        intent.putExtra("subt", "");
                    }
                    intent.putExtra("name", itemDataList.get(i).getName());
                    intent.putExtra("video", itemDataList.get(i).getVideo());
                    intent.putExtra("genre", itemDataList.get(i).getGenre());
                    //intent.putExtra("pos",itemDataList.get(i).getMio());
                    context.startActivity(intent);





/*

                    Intent playbackIntent = new Intent(context, PlaybackActivity.class);
                    playbackIntent.setAction(Intent.ACTION_VIEW);
                    playbackIntent.setData(Uri.parse(itemDataList.get(i).getVideo()));
                    playbackIntent.putExtra(Intent.EXTRA_TITLE, itemDataList.get(i).getName());
                    playbackIntent.putExtra("genre", itemDataList.get(i).getGenre());
                    playbackIntent.putExtra("video", itemDataList.get(i).getVideo());
                                        if (itemDataList.get(i).getSubtitle()!=null){
                        intent.putExtra("subt",itemDataList.get(i).getSubtitle());
                    }

                    if (itemDataList.get(i).getX().startsWith("http")) {
                        playbackIntent.putExtra("photo", itemDataList.get(i).getX());
                    } else {
                        playbackIntent.putExtra("photo", itemDataList.get(i).getPhoto());
                    }


                    if (canResumePlayback(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getName())) {
                        playbackIntent.putExtra(PlaybackActivity.INTENT_EXTRA_JUMP_TO, getResumePosition());
                    }

                    //dump launch intent


                    //save the playback url as last played
                    updateLastPlayed(Uri.parse(itemDataList.get(i).getVideo()), itemDataList.get(i).getName());
                    context.startActivity(playbackIntent);
*/
                }

                }
        });
        /*
        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {
                    startDownload(itemDataList.get(i).getVideo(),itemDataList.get(i).getName());





                }


                return true;
            }
        });
        */

        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {

                    if (itemDataList.get(i).getSubtitle() != null) {
                        subx = itemDataList.get(i).getSubtitle();
                    } else {
                        subx = "http/xx";
                    }
                     startDownload(itemDataList.get(i).getVideo(), itemDataList.get(i).getName(),itemDataList.get(i).getPhoto(),subx);

                   // Toast.makeText(context, " Saving. . . ", Toast.LENGTH_SHORT).show();
                   // musicService.downlo(itemDataList.get(i).getVideo(), itemDataList.get(i).getName(), itemDataList.get(i).getPhoto(), subx );
                }else {
                    Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

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



    private void startDownload(String url, String name, String photo,String subt) {


      /*
        cx=name;

        DownloadManager mManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(video));
        request.setTitle("MagicPlay");
        request.setDescription(name);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalFilesDir(context,Environment.DIRECTORY_DOWNLOADS, File.separator+"IcePlay"+File.separator+name);
        mManager.enqueue(request);
        long idx=mManager.enqueue(request);

        id.add(new id(name,idx));
        save(id);url
,        //downloadID=mManager.enqueue(request);

        File x=new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"/IcePlay");
        if (!x.exists()){
            x.mkdir();
        }

        String file=x+"/"+cx+".mp4";



            request = new Request(url, file);

        request.setPriority(Priority.HIGH);
        request.setNetworkType(NetworkType.ALL);
        request.setAutoRetryMaxAttempts(3);
        request.addHeader("clientKey", "SD78DF93_3947&MVNGHE1WONG");

        request.setTag(cx);



        fetch.enqueue(request, new Func<Request>() {
            @Override
            public void call(@NotNull Request result) {
                Toast.makeText(context, "Call made", Toast.LENGTH_SHORT).show();
            }
        }, new Func<Error>() {
            @Override
            public void call(@NotNull Error result) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });



        fetch.addListener(this);

*/



        Intent intent = new Intent(context, locality.class);
        intent.putExtra("url", url);
        intent.putExtra("namemovie",name);
        intent.putExtra("picture",photo);
        intent.putExtra("subt",subt);
        context.startActivity(intent);

        //fetch.close();

    }

    private void startDownloadx(String url, String name, String photo,String subt){

        DownloadRequest downloadRequest =
                new DownloadRequest.Builder(url, Uri.parse(url)).build();
        DownloadService.sendAddDownload(
                context,
                DownloadService.class,
                downloadRequest,
                /* foreground= */ false);
    }


    private void save(ArrayList<id> xx) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(xx);
        editor.putString("task",json);
        editor.apply();
    }

    private void createBottomSheetDialog(){


        if (bottomSheetDialog==null){

            View view= LayoutInflater.from(context).inflate(R.layout.bottom_sheet,null);
            LinearLayout vgx, iceplayer,download;

            vgx=view.findViewById(R.id.vgx);
            iceplayer=view.findViewById(R.id.iceplayer);
            download=view.findViewById(R.id.downloader);

            bottomSheetDialog=new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(view);


            vgx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String x=string;
                    if (x.contains(",")) {

                        String y = x.toLowerCase();
                        int KO = y.indexOf(",");
                        String p = y.substring(0, KO);
                        String m = p.trim();

                    }
                    if (!x.contains(",")){
                        String y = x.toLowerCase();
                        String m = y.trim();


                    }
                    Intent intent = new Intent(context, streamplayer.class);
                    intent.putExtra("video", video);
                    intent.putExtra("photo",img);
                    intent.putExtra("name",name);
                    intent.putExtra("genre",string);
                    context.startActivity(intent);

                }
            });

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (video.startsWith("http")){
                        //startDownload(video,name);
                    }
                }
            });



        }
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }




    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    @Override
    public void onAdded(@NotNull Download download) {

        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onCancelled(@NotNull Download download) {
        Toast.makeText(context, "error1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted(@NotNull Download download) {
        Toast.makeText(context, "Downloaded", Toast.LENGTH_SHORT).show();

        if (builder!=null){
            builder.setContentText("Download complete");
            builder.setProgress(0,0,false);
            nmc.notify(download.getId(),builder.build());
        }
    }

    @Override
    public void onDeleted(@NotNull Download download) {
        Toast.makeText(context, "error5", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadBlockUpdated(@NotNull Download download, @NotNull DownloadBlock downloadBlock, int i) {
        Toast.makeText(context, "error2", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
        Toast.makeText(context, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaused(@NotNull Download download) {

    }

    @Override
    public void onProgress(@NotNull Download download, long l, long l1) {
        int progress=download.getProgress();
        builder.setProgress(100,progress,false);
        nmc.notify(download.getId(),builder.build());
    }

    @Override
    public void onQueued(@NotNull Download download, boolean b) {
        if (request.getId() == download.getId()) {
            Toast.makeText(context, download.getTag(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(context, "queued", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoved(@NotNull Download download) {
        Toast.makeText(context, "error6", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResumed(@NotNull Download download) {

    }

    @Override
    public void onStarted(@NotNull Download download, @NotNull List<? extends DownloadBlock> list, int i) {
       // showNotification(download.getId());
        Toast.makeText(context, "started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWaitingNetwork(@NotNull Download download) {
        Toast.makeText(context, "error4", Toast.LENGTH_SHORT).show();
    }
    private void showNotification(int id) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("channelid1","001", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription(cx);
            NotificationManager manager= (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            builder=new Notification.Builder(context.getApplicationContext(),"channelid1");
            builder.setSmallIcon(R.drawable.ic_file_download_black_24dp);

            builder.setContentTitle("MagicPlay");
            builder.setContentText("mmm");
            builder.setAutoCancel(false);
            builder.setProgress(100,0,false);
            builder.setWhen(System.currentTimeMillis());
            builder.setPriority(Notification.PRIORITY_DEFAULT);

            nmc=NotificationManagerCompat.from(context.getApplicationContext());
            nmc.notify(id,builder.build());


        }else {
            builder=new Notification.Builder(context.getApplicationContext());
            builder.setSmallIcon(R.drawable.ic_file_download_black_24dp);

            builder.setContentTitle("MagicPlay");
            builder.setContentText("mmm");
            builder.setAutoCancel(false);
            builder.setProgress(100,0,false);
            builder.setWhen(System.currentTimeMillis());
            builder.setPriority(Notification.PRIORITY_DEFAULT);

            nmc=NotificationManagerCompat.from(context.getApplicationContext());
            nmc.notify(id,builder.build());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title;
        TextView genre;
        ImageView img_item,button;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.music_img);

            button=itemView.findViewById(R.id.button);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);





        }



    }
}
