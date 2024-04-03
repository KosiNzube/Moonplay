package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.app.moonplay.mainrecycler.trailers2;

public class curator extends RecyclerView.Adapter<curator.MyViewHolder>{

    private SharedPreferences appPreferences;
    private Fetch fetch;
    private Context context;
    private List<instV> itemDataList;
    Notification.Builder builder;
    NotificationManagerCompat nmc;
    Request request;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<id> id = new ArrayList<>();
    private CollectionReference medieval = db.collection("Spanish");
    BottomSheetDialog bottomSheetDialog;
    String string,video,img,name,cx;
    private long downloadID;

    public curator(Context context, List<instV> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.air_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        myViewHolder.name.setSelected(true);

        if (itemDataList.get(i).getDes()!=null) {


            if (itemDataList.get(i).getDes().length() > 200) {
                myViewHolder.title.setText(itemDataList.get(i).getDes().substring(0, 200) + "......READ MORE");
                myViewHolder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showbs(itemDataList.get(i).getDes());
                    }
                });


            } else {
                myViewHolder.title.setText(itemDataList.get(i).getDes());
            }




        }




        if (itemDataList.get(i).getThumbnail()!=null) {
            myViewHolder.name.setText(itemDataList.get(i).getThumbnail());
        }


        myViewHolder.aaa.setText("Rating: "+itemDataList.get(i).getX());

        Glide.with(context)
                .load(itemDataList.get(i).getImage())
                .centerCrop()
                .into(myViewHolder.thumb);

        Glide.with(context)
                .load(itemDataList.get(i).getPoster())
                .centerCrop()
                .into(myViewHolder.pict);


        myViewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date date1=new Date();
                Timestamp timestamp=new Timestamp(date1);
                Movie movie=new Movie("",itemDataList.get(i).getDes(),itemDataList.get(i).getX(),itemDataList.get(i).getPoster(),itemDataList.get(i).getImage(),itemDataList.get(i).getThumbnail(),"","","",itemDataList.get(i).getId(),itemDataList.get(i).getVideo(),"",timestamp);


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

        myViewHolder.similarcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ne = new Intent(context, trailers2.class);
                ne.putExtra("search", itemDataList.get(i).getThumbnail());
                context.startActivity(ne);

            }
        });

        myViewHolder.tease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemDataList.get(i).getVideo().startsWith("http")) {



                    Intent intent = new Intent(context, streamplayer4Alltrailer.class);

                    intent.putExtra("photo", itemDataList.get(i).getPoster());
                    intent.putExtra("subt","");

                    intent.putExtra("name", itemDataList.get(i).getThumbnail());
                    intent.putExtra("video", itemDataList.get(i).getVideo());
                    intent.putExtra("genre", itemDataList.get(i).getId());
                    //intent.putExtra("pos",itemDataList.get(i).getMio());
                    context.startActivity(intent);


                }else {
                    Toast toast = Toast.makeText(context, " no trailer available ", Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        myViewHolder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  myViewHolder.dislike.setCardBackgroundColor(Color.DKGRAY);
                //myViewHolder.dislike.setEnabled(false);
                //myViewHolder.like.setEnabled(false);
                Toast toast = Toast.makeText(context, " dislike noted ", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Date date1=new Date();
                Timestamp timestamp=new Timestamp(date1);
                Movie movie=new Movie("",itemDataList.get(i).getDes(),itemDataList.get(i).getX(),itemDataList.get(i).getPoster(),itemDataList.get(i).getImage(),itemDataList.get(i).getThumbnail(),"","","",itemDataList.get(i).getId(),itemDataList.get(i).getVideo(),"",timestamp);


                movie.setTimestamp(timestamp);
                medieval.add(movie).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast toast = Toast.makeText(context, movie.getName()+" Added ", Toast.LENGTH_SHORT);

                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
            }
        });

        myViewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  myViewHolder.dislike.setEnabled(false);
             //   myViewHolder.like.setEnabled(false);
               // myViewHolder.like.setCardBackgroundColor(Color.DKGRAY);
                Toast toast = Toast.makeText(context, " like noted ", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });

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


    private void showbs(String description) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.alldes);

        TextView des = bottomSheetDialog.findViewById(R.id.typee);


        des.setText(description);
        bottomSheetDialog.show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        MaterialCardView like,dislike;
        TextView aaa;
        FrameLayout media_container;
        AppCompatTextView title,name;
        ImageView  volumeControl;
        ProgressBar progressBar;
        View parent;
        ImageView thumb,pict;
        Button play,tease;
        LinearLayout constraintLayout;
        AppCompatImageView download;
        ShapeableImageView imageView;
        SimpleCache cache;
        MaterialCardView similarcard;
        AppCompatImageView shareBtn,repeat,fav,mute;
        LinearLayout namx;
        TextView m;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);
            constraintLayout=itemView.findViewById(R.id.recycler_view_options);
            media_container = itemView.findViewById(R.id.media_container);
            title = itemView.findViewById(R.id.text_view_video_description);
            name = itemView.findViewById(R.id.text_view_account_handle);
            imageView=itemView.findViewById(R.id.image_view_profile_pic);
            similarcard=itemView.findViewById(R.id.similarcard);
            play=itemView.findViewById(R.id.play);
            tease=itemView.findViewById(R.id.tease);
            thumb=itemView.findViewById(R.id.thumb);
            aaa=itemView.findViewById(R.id.aaa);
            pict=itemView.findViewById(R.id.itemImage);
            progressBar = itemView.findViewById(R.id.progressBar);
            repeat=itemView.findViewById(R.id.repeat);
            fav=itemView.findViewById(R.id.image_view_option_like);
            //   volumeControl = itemView.findViewById(R.id.volume_control);
            shareBtn=itemView.findViewById(R.id.image_view_option_share);
            mute=itemView.findViewById(R.id.mute);
            download = itemView.findViewById(R.id.image_view_option_comment);

        }



    }
}
