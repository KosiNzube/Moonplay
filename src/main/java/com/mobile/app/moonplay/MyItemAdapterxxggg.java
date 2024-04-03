package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.DownloadRequest;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.Timestamp;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemAdapterxxggg extends RecyclerView.Adapter<MyItemAdapterxxggg.MyViewHolder> {


    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 590;

    public MyItemAdapterxxggg(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.grid,viewGroup,false);
        return new MyViewHolder(view);
    }

    public void xx(List<Movie> datas){
        itemDataList.clear();
        itemDataList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {


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

            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);

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
                showbs(itemDataList.get(i),itemDataList.get(i).getSubtitle(),itemDataList.get(i).getMb(),itemDataList.get(i).getVideo(),itemDataList.get(i).getImage(),itemDataList.get(i).getGenre(),itemDataList.get(i).getDescription(),itemDataList.get(i).getName(),itemDataList.get(i).getResolution());

                /*
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
                context.startActivity(intent);

                 */
            }
        });

        myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return true;
            }
        });

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
    private void showbs(Movie movie, String subtitle, String mb, String video, String image, String genre, String description, String name, String resolution) {
        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.didi);


        ImageView itemview=bottomSheetDialog.findViewById(R.id.itemImage);
        LinearLayout pall=bottomSheetDialog.findViewById(R.id.pall);
        Button sub=bottomSheetDialog.findViewById(R.id.sub);
        Button play=bottomSheetDialog.findViewById(R.id.play);
        Button save=bottomSheetDialog.findViewById(R.id.save);


        if (!video.toLowerCase().contains("http")){
            sub.setVisibility(View.VISIBLE);
            pall .setVisibility(View.GONE);
        }else {
            pall.setVisibility(View.VISIBLE);
            sub.setVisibility(View.GONE);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HLSAsset hlsAsset=new HLSAsset(video,video);
                downloadAsset(hlsAsset);
            }
        });


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
        Button fave=bottomSheetDialog.findViewById(R.id.fave);
        fave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date1=new Date();
                Timestamp timestamp=new Timestamp(date1);
                movie.setTimestamp(timestamp);
                Main7Activity.db.collection("subusers").document(Main7Activity.user.getUid()).collection("watchl").add(movie);
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



    @Override
    public int getItemCount() {
        if (itemDataList.size()<6){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 6;
        }


        return itemDataList.size();
    }
    public void downloadAsset(HLSAsset asset) {
        MediaItem mediaItem=asset.getMediaItem();

        DownloadHelper downloadHelper=DownloadHelper.forMediaItem(context, mediaItem, null, new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return null;
            }
        });
        downloadHelper.prepare(new DownloadHelper.Callback() {
            @Override
            public void onPrepared(DownloadHelper downloadHelper) {
                DownloadRequest request=downloadHelper.getDownloadRequest(asset.getId(),null);

                DownloadService.sendAddDownload(context,DownloadService.class,request,false);

            }

            @Override
            public void onPrepareError(DownloadHelper downloadHelper, IOException e) {

            }
        });

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
