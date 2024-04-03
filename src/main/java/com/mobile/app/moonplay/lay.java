package com.mobile.app.moonplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class lay extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mRecyclerViewItems ;
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The unified native ad view type.
    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;
    private Context context;

    private final static int FADE_DURATION = 690;
    static ArrayList arrayList=new ArrayList();
    public lay(Context context, List<Object> itemDataList) {
        this.context = context;
        this.mRecyclerViewItems = itemDataList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                View unifiedNativeLayoutView = LayoutInflater.from(
                        context).inflate(R.layout.ad_unified,
                        viewGroup, false);
                return new UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
            case MENU_ITEM_VIEW_TYPE:
                // Fall through.
            default:
                View view= LayoutInflater.from(context).inflate(R.layout.lay,viewGroup,false);
                return new MyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);


        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                UnifiedNativeAd nativeAd = (UnifiedNativeAd) mRecyclerViewItems.get(position);
                populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder).getAdView());
                break;
            case MENU_ITEM_VIEW_TYPE:
                // fall through
            default:

                MyViewHolder myViewHolder= (MyViewHolder) holder;
                Movie x=(Movie) mRecyclerViewItems.get(position);





                myViewHolder.txt_item_title.setText(x.getName());
                myViewHolder.genre.setText(x.getGenre());
                myViewHolder.maingenre.setText(x.getGenre());

                if (x.getName().length()>35){
                    myViewHolder.mb.setText(x.getName().substring(0, 35) + "...");
                }else {
                    myViewHolder.mb.setText(x.getName());
                }
                myViewHolder.maindes.setText(x.getDescription());
                try{
                    Glide.with(context)
                            .load(x.getImage())
                            .centerCrop()
                            .into(myViewHolder.img_item);

                    // setFadeAnimation(myViewHolder.img_item);
                    // myViewHolder.genre.setVisibility(View.INVISIBLE);
                    //myViewHolder.txt_item_title.setVisibility(View.INVISIBLE);
                }catch (OutOfMemoryError error){
                    error.printStackTrace();

                }
                setFadeAnimation(myViewHolder.img_item);



                myViewHolder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, comments.class);
                        intent.putExtra("image",x.getImage());
                        intent.putExtra("genre",x.getGenre());
                        intent.putExtra("name",x.getName());
                        intent.putExtra("video",x.getVideo());
                        intent.putExtra("dex",x.getDescription());
                        intent.putExtra("mb",x.getMb());
                        intent.putExtra("type",x.getType());
                        intent.putExtra("res",x.getResolution());
                        intent.putExtra("upl",x.getUploader());
                        context.startActivity(intent);
                    }
                });

                myViewHolder.wtach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!arrayList.contains(x.getName())) {
                            arrayList.add(x.getName());

                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sp.edit();

                            editor.putInt("Status", arrayList.size());
                            for (int i = 0; i < arrayList.size(); i++) {
                                editor.remove("Status_" + i);
                                editor.putString("Status_" + i, (String) arrayList.get(i));
                                editor.commit();
                            }

                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();


                        }
                    }
                });

                myViewHolder.card.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {


                        return true;
                    }
                });






        }







    }



    @Override
    public int getItemViewType(int position) {

        Object recyclerViewItem = mRecyclerViewItems.get(position);
        if (recyclerViewItem instanceof UnifiedNativeAd) {
            return UNIFIED_NATIVE_AD_VIEW_TYPE;
        }
        return MENU_ITEM_VIEW_TYPE;
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }





    @Override
    public int getItemCount() {



        if (mRecyclerViewItems.size()<4){
            return mRecyclerViewItems.size();
        }else
        if (mRecyclerViewItems.size()>0) {
            return 4;
        }


        return mRecyclerViewItems.size();

    }
    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
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
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_item_title,genre,maindes,maingenre;
        ImageView img_item,io;
        RelativeLayout card;

        ImageView wtach;

        Button mb;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.tvTitle);
            img_item=itemView.findViewById(R.id.itemImage);

            mb=itemView.findViewById(R.id.mb);
            maindes=itemView.findViewById(R.id.des);
            maingenre=itemView.findViewById(R.id.genre);
            card=itemView.findViewById(R.id.card);
            genre=itemView.findViewById(R.id.type);

            wtach=itemView.findViewById(R.id.watch);




        }





    }
}
