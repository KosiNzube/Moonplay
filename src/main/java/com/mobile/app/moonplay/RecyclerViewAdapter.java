package com.mobile.app.moonplay;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.mobile.app.moonplay.comments.arrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> mData;

    public RecyclerViewAdapter(Context context, List<Movie> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.cardview_item_image,viewGroup,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, final int i) {

        String video=mData.get(i).getVideo();

        viewHolder.host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, players.class);
                intent.putExtra("video", mData.get(i).getVideo());
                intent.putExtra("name",mData.get(i).getName());
                intent.putExtra("genre",mData.get(i).getGenre());
                intent.putExtra("image",mData.get(i).getImage());
                context.startActivity(intent);
            }
        });


        viewHolder.post.setText(mData.get(i).getUploader());


        if (mData.get(i).getType().equals("4.5")){
            viewHolder.ratingBar.setRating(((float )9/2));

        }
        if (mData.get(i).getType().equals("5")){
            viewHolder.ratingBar.setRating(((float )5));
        }

        if (mData.get(i).getType().equals("4")){
            viewHolder.ratingBar.setRating(((float )4));
        }
        if (mData.get(i).getType().equals("3.5")){
            viewHolder.ratingBar.setRating(((float )3.5));
        }
        if (mData.get(i).getType().equals("3")){
            viewHolder.ratingBar.setRating(((float )3));
        }
        viewHolder.itemImage.setVisibility(View.VISIBLE);
        viewHolder.image.setVisibility(View.VISIBLE);
        viewHolder.koko.setVisibility(View.VISIBLE);
        viewHolder.moviename.setVisibility(View.VISIBLE);
        viewHolder.dex.setVisibility(View.VISIBLE);
        viewHolder.genre.setVisibility(View.VISIBLE);

        viewHolder.vivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!arrayList.contains(mData.get(i).getName())) {
                    arrayList.add(mData.get(i).getName());

                }
            }
        });
        viewHolder.downlll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                        String[]permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};

                    }else {
                        if (mData.get(i).getVideo().startsWith("http")) {
                            downlondFile(context, mData.get(i).getVideo(),mData.get(i).getName());
                        }
                    }
                }


            }
        });


        viewHolder.dex.setText(mData.get(i).getDescription());




        viewHolder.genre.setText(mData.get(i).getGenre());
        viewHolder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, "downloading. .", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        viewHolder.downl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.contains(mData.get(i).getName())) {
                    arrayList.add(mData.get(i).getName());

                }
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String kiki=preferences.getString("names","");
        if (mData.get(i).getName().equals(kiki)){
            //holder.button.setVisibility(View.VISIBLE);
        }




        viewHolder.moviename.setText(mData.get(i).getName());


        viewHolder.resolution.setText(mData.get(i).getResolution());
        viewHolder.mib.setText(mData.get(i).getMb());
//
        Picasso.get().load(mData.get(i).getImage()).fit().centerCrop().into(viewHolder.image);
        Picasso.get().load(mData.get(i).getImage()).into(viewHolder.itemImage);

/*
        if (mData.get(i).getType()=="trailer"){
            viewHolder.videoView.setVisibility(View.VISIBLE);
            viewHolder.layout.setVisibility(View.INVISIBLE);
            viewHolder.videoView.setVideoURI(Uri.parse(mData.get(i).getVideo()));
            viewHolder.videoView.setBackgroundColor(Color.DKGRAY);
            viewHolder.videoView.setZOrderOnTop(true);
            viewHolder.videoView.start();
            onViewRecycled(viewHolder);
            viewHolder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    viewHolder.videoView.start();
                }
            });
            viewHolder.videoView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context,"Wish listed",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            viewHolder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    viewHolder.videoView.start();
                }
            });






            viewHolder.watch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,playmovie.class);
                    context.startActivity(intent);
                }
            });


            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });




            viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });

            viewHolder.imgF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
        */



    }

    private void downlondFile(Context context, String s, String video) {
        DownloadManager downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(video.toString().trim());
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(s);
        request.setDescription("Downloading movie...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
        DownloadManager manager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView,moviename,dex,genre;

        ImageButton imgF;
        ImageButton love;
        ImageButton button;
        RelativeLayout cardView;
        ImageView watch;
        ImageView download;

        Button downl,cart;

        ImageView itemImage;
        RelativeLayout layout;

        CardView koko;
        TextView post;
        ImageView vivi;



        TextView resolution;

        TextView mib;
        ImageView image;
        Button host;
        RelativeLayout ninini;
        RatingBar ratingBar;
        ImageView downlll;

        VideoView videoView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView);
            image=(ImageView) itemView.findViewById(R.id.videoView1);
            videoView=itemView.findViewById(R.id.videoView);
            vivi=itemView.findViewById(R.id.cart);
            watch=itemView.findViewById(R.id.comentid);
            downl=itemView.findViewById(R.id.highres);
            cart=itemView.findViewById(R.id.standard);
            itemImage=itemView.findViewById(R.id.itemImage);
            resolution=itemView.findViewById(R.id.resolution);
            post=itemView.findViewById(R.id.post);
            koko=itemView.findViewById(R.id.koko);
            mib=itemView.findViewById(R.id.mib);
            layout=itemView.findViewById(R.id.ricky);
            moviename=itemView.findViewById(R.id.moviename);
            dex=itemView.findViewById(R.id.textView7);
            genre=itemView.findViewById(R.id.genre);

            downlll=itemView.findViewById(R.id.downloads);
            host=itemView.findViewById(R.id.hostbutton);
            ninini=itemView.findViewById(R.id.host);

            imgF=itemView.findViewById(R.id.button8);

            ratingBar=itemView.findViewById(R.id.rate);
            button=(ImageButton)itemView.findViewById(R.id.button7);
            cardView= itemView.findViewById(R.id.cardview);








        }
    }
}
