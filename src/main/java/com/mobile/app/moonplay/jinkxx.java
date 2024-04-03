package com.mobile.app.moonplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class jinkxx extends RecyclerView.Adapter<jinkxx.MyViewHolder> {


    private Context context;
    private List<Movie> itemDataList;
    private final static int FADE_DURATION = 590;

    public jinkxx(Context context, List<Movie> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.jinx,viewGroup,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.txt_item_title.setText(itemDataList.get(i).getDescription());

        myViewHolder.genre.setText(itemDataList.get(i).getGenre());
        myViewHolder.name.setText(itemDataList.get(i).getName());
        myViewHolder.cast.setText(itemDataList.get(i).getResolution());
        try{

            /*
            Glide.with(context)
                    .load(itemDataList.get(i).getImage())
                    .centerCrop()
                    .into(myViewHolder.img_item);

*/

            Picasso.get().load(itemDataList.get(i).getImage()).fit().centerCrop().into(myViewHolder.img_item);
            Picasso.get().load(itemDataList.get(i).getMb()).fit().centerCrop().into(myViewHolder.io);
            // setFadeAnimation(myViewHolder.img_item);
            // myViewHolder.genre.setVisibility(View.INVISIBLE);
            //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
        }catch (OutOfMemoryError error){
            error.printStackTrace();

        }

       // setScaleAnimation(myViewHolder.img_item);
       // setFadeAnimation(myViewHolder.img_item);




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
    public int getItemCount() {
        return itemDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre,name,cast;

        ImageView img_item,io;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.textView7);
            img_item=itemView.findViewById(R.id.itemImage);

            name=itemView.findViewById(R.id.tvTitle);
            cast=itemView.findViewById(R.id.cast);
            io=itemView.findViewById(R.id.thumbnail);

            genre=itemView.findViewById(R.id.type);





        }





    }
}
