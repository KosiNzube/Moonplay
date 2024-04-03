package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.util.ConfigKeys;

public class MyItemAdapterxxgrid extends RecyclerView.Adapter<MyItemAdapterxxgrid.MyViewHolder> {

    private String subx;
    private Context context;
    private List<Movie> itemDataList=new ArrayList<>();
    private final static int FADE_DURATION = 590;
    private SharedPreferences appPreferences;
    public MyItemAdapterxxgrid(Context context) {
        this.context = context;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.grid2,viewGroup,false);
        return new MyViewHolder(view);
    }
    public void setItems(ArrayList<Movie> quickie){
        itemDataList.addAll(quickie);
    }

    public void xx(List<Movie> datas){
        itemDataList.clear();
        itemDataList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {

        appPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
      //  myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());


        if (itemDataList.get(i).getName().length() > 30) {
            myViewHolder.txt_item_title.setText(itemDataList.get(i).getName().substring(0, 30) + "...");
        } else {
            myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        }

      //  myViewHolder.genre.setText(itemDataList.get(i).getGenre());
        try{

            /*
            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);

*/

            if(itemDataList.get(i).getMb()!=null&&!itemDataList.get(i).getMb().contains("null")){
                Glide.with(context)
                        .load(itemDataList.get(i).getMb())
                        .centerCrop()
                        .into(myViewHolder.img_item);

            }else {

                Glide.with(context)
                        .load(itemDataList.get(i).getImage()).centerCrop()
                        .into(myViewHolder.img_item);
            }
            // setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }

       // setScaleAnimation(myViewHolder.img_item);
       // setFadeAnimation(myViewHolder.img_item);


        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  showbs(itemDataList.get(i).getUploader(),itemDataList.get(i),itemDataList.get(i).getSubtitle(),itemDataList.get(i).getMb(),itemDataList.get(i).getVideo(),itemDataList.get(i).getImage(),itemDataList.get(i).getGenre(),itemDataList.get(i).getDescription(),itemDataList.get(i).getName(),itemDataList.get(i).getResolution());


                Intent intent = new Intent(context, comments.class);
                intent.putExtra("image", itemDataList.get(i).getImage());
                intent.putExtra("genre",itemDataList.get(i).getGenre());
                intent.putExtra("name",itemDataList.get(i).getName());
                intent.putExtra("video",itemDataList.get(i).getVideo());
                intent.putExtra("dex",itemDataList.get(i).getDescription());
                intent.putExtra("mb",itemDataList.get(i).getMb());
                intent.putExtra("type",itemDataList.get(i).getType());
                intent.putExtra("res",itemDataList.get(i).getResolution());
                intent.putExtra("upl",itemDataList.get(i).getUploader());
                if (itemDataList.get(i).getSubtitle()!=null) {
                    intent.putExtra("trailer", itemDataList.get(i).getSubtitle());
                }else {
                    intent.putExtra("trailer", "");
                }
                context.startActivity(intent);


            }
        });

        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

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


    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void showbs(String trailer, Movie movie, String subtitle, String mb, String video, String image, String genre, String description, String name, String resolution) {
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.didi);


        ImageView itemview=bottomSheetDialog.findViewById(R.id.itemImage);
        LinearLayout pall=bottomSheetDialog.findViewById(R.id.pall);
        MaterialCardView sub=bottomSheetDialog.findViewById(R.id.sub);
        MaterialCardView play=bottomSheetDialog.findViewById(R.id.play);
        MaterialCardView save=bottomSheetDialog.findViewById(R.id.save);


        if (video!=null) {
            if (!video.toLowerCase().contains("http")) {
                sub.setVisibility(View.VISIBLE);
                pall.setVisibility(View.GONE);
            } else {
                pall.setVisibility(View.VISIBLE);
                sub.setVisibility(View.GONE);
            }
        }else {
            sub.setVisibility(View.VISIBLE);
            pall.setVisibility(View.GONE);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video.startsWith("http")) {



                    Intent intent = new Intent(context, streamplayer4All.class);
                    if (mb.startsWith("http")) {
                        intent.putExtra("photo", mb);
                    } else {
                        intent.putExtra("photo", image);
                    }

                    if (subtitle!=null){
                        intent.putExtra("subt",subtitle);
                    }else {
                        intent.putExtra("subt", "");
                    }
                    intent.putExtra("name", name);
                    intent.putExtra("video", video);
                    intent.putExtra("genre", genre);
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HLSAsset hlsAsset=new HLSAsset(video,video);
                //downloadAsset(hlsAsset);

                if (subtitle!=null){
                    subx=subtitle;
                }else {
                    subx="http/xx";
                }
                startDownload(video,name, image,subx);

            }
        });

        MaterialCardView fave=bottomSheetDialog.findViewById(R.id.fave);
        fave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date1=new Date();
                Timestamp timestamp=new Timestamp(date1);
                movie.setTimestamp(timestamp);
                Main7Activity.db.collection("subusers").document(Main7Activity.user.getUid()).collection("watchl").add(movie).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast toast = Toast.makeText(context, movie.getName()+" Added ", Toast.LENGTH_SHORT);

                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
            }
        });


        MaterialCardView similar=bottomSheetDialog.findViewById(R.id.similar);

        if (trailer!=null) {
            if (trailer.length()>3) {

              //  similar.setText("TRAILER UNAVAILABLE");
            }
        }else {
          //  similar.setText("TRAILER UNAVAILABLE");
        }

        similar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trailer.startsWith("http")) {



                    Intent intent = new Intent(context, streamplayer4Alltrailer.class);
                    if (mb.startsWith("http")) {
                        intent.putExtra("photo", mb);
                    } else {
                        intent.putExtra("photo", image);
                    }

                    if (subtitle!=null){
                        intent.putExtra("subt",subtitle);
                    }else {
                        intent.putExtra("subt", "");
                    }
                    intent.putExtra("name", name);
                    intent.putExtra("video", trailer);
                    intent.putExtra("genre", genre);
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

        TextView textView=bottomSheetDialog.findViewById(R.id.tvTitle);
        TextView genrex=bottomSheetDialog.findViewById(R.id.type);
        TextView des=bottomSheetDialog.findViewById(R.id.typee);
        TextView cast=bottomSheetDialog.findViewById(R.id.cast);
        Glide.with(context)
                .load(image)
                .centerCrop()
                .into(itemview);

        textView.setText(name);
        genrex.setText(genre);
        des.setText(description);
        if (resolution.length()>8) {
            cast.setText("Cast: " + resolution);
        }else {
            cast.setText("Rating: " + resolution);
        }
        bottomSheetDialog.show();

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
    public int getItemCount() {

        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre;
        ImageView img_item,io;
        RelativeLayout card;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);





        }





    }
}
