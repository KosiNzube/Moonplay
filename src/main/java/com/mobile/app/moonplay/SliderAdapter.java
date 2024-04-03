package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    private Context context;
    // list for storing urls of images.
    private final List<Movie> itemDataList;

    // Constructor
    public SliderAdapter(Context context, ArrayList<Movie> sliderDataArrayList) {
        this.context = context;
        this.itemDataList = sliderDataArrayList;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int i) {

        final Movie sliderItem = itemDataList.get(i);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

            }
        });

        viewHolder.x.setText("" +sliderItem.getName()+" ");
        viewHolder.x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        // Glide is use to load image
        // from url in your imageview.

        if (sliderItem.getMb()!=null) {
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getMb())
                    .centerCrop()
                    .into(viewHolder.imageViewBackground);
        }else {
            Glide.with(viewHolder.itemView)
                    .load(sliderItem.getImage())
                    .centerCrop()
                    .into(viewHolder.imageViewBackground);

        }


    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        if (itemDataList.size()<5){
            return itemDataList.size();
        }else
        if (itemDataList.size()>0) {
            return 5;
        }


        return itemDataList.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;
        RelativeLayout card;

        Button x;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            card=itemView.findViewById(R.id.xz);
            x=itemView.findViewById(R.id.x);
            this.itemView = itemView;
        }
    }
}
