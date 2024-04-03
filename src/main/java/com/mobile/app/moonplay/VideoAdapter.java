package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.ui.playback.PlaybackActivity;
import com.mobile.app.moonplay.ui.playback.PlaybackActivity2;
import com.mobile.app.moonplay.util.ConfigKeys;

/**
 * Created by Pawan on 2/20/2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private SharedPreferences appPreferences;

  private final static int FADE_DURATION = 800;
  private static final int TYPE_HEADER = 0;
  private static final int TYPE_ITEM = 1;
  private static final int TYPE_FOOTER = 2;
  public static Glide glid;
  Context context;
  String name;
  int days;
  Bundle bundle=new Bundle();
  private List<VideoItem> videoList;
  private String album_name;
  private boolean mWithHeader;
  private boolean mWithFooter;


  public VideoAdapter(List<VideoItem> videoList, Context context, String album, boolean withHeader, boolean withFooter) {
    this.videoList = videoList;
    this.context=context;
    this.album_name=album;
    this.mWithHeader=withHeader;
    this.mWithFooter=withFooter;
  }
  @Override
  public int getItemViewType(int position) {

    if (mWithHeader && isPositionHeader(position))
      return TYPE_HEADER;
    if (mWithFooter && isPositionFooter(position))
      return TYPE_FOOTER;
    return TYPE_ITEM;
  }
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

    if(viewType==TYPE_HEADER) {

      return new headView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false));
    }
    else if(viewType==TYPE_FOOTER){
      return new footer(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer, viewGroup, false));
    }
    else {
      View itemView = LayoutInflater.
              from(viewGroup.getContext()).
              inflate(R.layout.video_item, viewGroup, false);

      VideoViewHolder holder = new VideoViewHolder(itemView);
      itemView.setTag(holder);

      return holder;
    }
  }
  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    appPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
    if(holder instanceof  headView){
      //   ((headView) holder).vName.setText(album_name);
    }
    else if(holder instanceof footer){
      ((footer) holder).context = context;
    }
    else {
      VideoItem mediaObject=getItem(position);

      name=mediaObject.getDISPLAY_NAME();
      if (name.length() > 25) {
        ((VideoViewHolder) holder).vName.setText(name.substring(0, 25) + "...");
      } else {
        ((VideoViewHolder) holder).vName.setText(name);
      }
      //((VideoViewHolder) holder).vName.setText(name);
      //   ((VideoViewHolder) holder).vImage.setImageResource(R.color.kokocolor);
      ((VideoViewHolder) holder).vFilePath = mediaObject.getDATA();
      ((VideoViewHolder) holder).names = mediaObject.getDISPLAY_NAME();

      ((VideoViewHolder) holder).context = context;
      ((VideoViewHolder) holder).b = bundle;
      ((VideoViewHolder) holder).position = position;

      // Picasso.get().load(mediaObject.getDATA()).fit().centerCrop().into(((VideoViewHolder) holder).vImage);


      Glide.with(context)
              .load(mediaObject.getDATA())
              .centerCrop()
              .into(((VideoViewHolder) holder).vImage);

   //   setFadeAnimation(((VideoViewHolder) holder).vImage);
      //  setScaleAnimation(((VideoViewHolder) holder).vImage);

    }
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
  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }


  @Override
  public int getItemCount() {

    return videoList.size();
  }


  private boolean isPositionHeader(int position) {
    return position == 0;
  }

  private boolean isPositionFooter(int position) {
    return position == getItemCount() - 1;
  }

  protected VideoItem getItem(int position) {
    return mWithHeader ? videoList.get(position - 1) : videoList.get(position);
  }

  private int getItemPosition(int position){
    return mWithHeader ? position - 1 : position;
  }

  public class VideoViewHolder extends RecyclerView.ViewHolder {
    protected ImageView vImage;
    protected TextView vName;
    protected TextView vDetails,vNew;
    protected String vFilePath;
    protected String names;
    protected Context context;
    protected Bundle b;
    protected int position;


    public VideoViewHolder(View v) {
      super(v);

      vName =  (TextView) v.findViewById(R.id.media_name);
      vImage = (ImageView)  v.findViewById(R.id.media_img_bck);
      vDetails = (TextView) v.findViewById(R.id.media_details);

      v.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



          Intent playbackIntent = new Intent(context, PlaybackActivity2.class);
          playbackIntent.putExtra("genre","");
          playbackIntent.putExtra("video",vFilePath);
          playbackIntent.setAction(Intent.ACTION_VIEW);
          playbackIntent.setData(Uri.parse(vFilePath));
          playbackIntent.putExtra(Intent.EXTRA_TITLE, names);



          if (canResumePlayback(Uri.parse(vFilePath), names))
          {
            playbackIntent.putExtra(PlaybackActivity.INTENT_EXTRA_JUMP_TO, getResumePosition());
          }

          //dump launch intent


          //save the playback url as last played
          updateLastPlayed(Uri.parse(vFilePath), names);




          context.startActivity(playbackIntent);

                    /*
                    Intent intent = new Intent(context, streamplayer4All.class);

                    intent.putExtra("name", itemDataList.get(i).getName());
                    intent.putExtra("video", itemDataList.get(i).getVideo());
                    intent.putExtra("genre", itemDataList.get(i).getGenre());

                    if (itemDataList.get(i).getX().startsWith("http")) {
                        intent.putExtra("photo", itemDataList.get(i).getX());
                    }else {
                        intent.putExtra("photo", itemDataList.get(i).getPhoto());
                    }
                    intent.putExtra("origin",itemDataList.get(i).getOriginame());

                    context.startActivity(intent);
                    */
        }


      });
    }

    public void clearAnimation() {
      this.clearAnimation();
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

  public class headView extends RecyclerView.ViewHolder {

    protected TextView vName;
    protected Context context;
    protected Bundle b;
    protected int position;
    //  protected AdView adView;

    public headView(View v) {
      super(v);
      vName = (TextView) v.findViewById(R.id.gallery_title);

    }
  }

  public class footer extends RecyclerView.ViewHolder {


    protected Context context;
    protected int position;

    public footer(View v) {
      super(v);


    }


  }
}


