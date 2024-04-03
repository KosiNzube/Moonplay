package com.mobile.app.moonplay;

/*
public class VidAdapter extends RecyclerView.Adapter<VidAdapter.VideoHolder>{

    private Context context;
    List<bigboy> videoArray;

    public VidAdapter(Context context, ArrayList<bigboy> videoArray) {
        this.context = context;
        this.videoArray = videoArray;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View myview= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);

        return new VideoHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder videoHolder, int i) {

        String x=videoArray.get(i).getHeaderTitle();
        videoHolder.txtFileName.setText(videoArray.get(i).getHeaderTitle());

        videoHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,Main4Activity.class);
                context.startActivity(intent);

            }
        });





    }

    @Override
    public int getItemCount() {

        if (videoArray.size()>0){
            return videoArray.size();
        }
        else
            return 4;
    }

   class VideoHolder extends RecyclerView.ViewHolder{
        TextView txtFileName;
        ImageView imageThumbnail;
        CardView mCardView;


        VideoHolder(View view){

            super(view);
            txtFileName=view.findViewById(R.id.txt_videoFileName);
            imageThumbnail=view.findViewById(R.id.iv_thumbnail);
            mCardView=view.findViewById(R.id.myCardview);

        }
    }
}
*/