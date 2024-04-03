package com.mobile.app.moonplay;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class sery extends Fragment {
    Button nollymore,bollymore,koreamore,tvbut,animbut,medbut;
    private lays videoAdapter;
    ExoPlayerRecyclerView mRecyclerView;
    private ArrayList<instantv> mediaObjectList = new ArrayList<>();
    private MediaRecyclerAdapter mAdapter;
    private boolean firstTime = true;
    ImageView imageView,buttonx;
    RecyclerView recyclerView,yy;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference music = db.collection("music");
    private CollectionReference valbum = db.collection("Valbum");
    private CollectionReference instant = db.collection("instant");
    ScrollView x;
    RecyclerView yiyii;
    private CollectionReference xxx=db.collection("noti");
    TextView mus,musy;

    ScrollView scrollView;
    private RecyclerView.LayoutManager layoutManager;
    private static final int READ_PHONE_STATE_REQUEST_CODE = 22;
    FrameLayout frameLayout;
    Button notif;
    ArrayList<bibi> noti=new ArrayList<>();
    String vi;
    ProgressBar progressBar;
    List<genrex> agentList;
    private CollectionReference medieval=db.collection("Italy");
    RecyclerView gigi;
    private CollectionReference series = db.collection("Series");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }
        scrollView=view.findViewById(R.id.scroll);
      //  mRecyclerView = view.findViewById(R.id.exoPlayerRecyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        gigi=view.findViewById(R.id.yiyii);




        series.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //gigi.setVisibility(View.VISIBLE);

                    final List<SeriesObject> movies = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        SeriesObject gigi = documentSnapshot.toObject(SeriesObject.class);
                        gigi.setVideo(documentSnapshot.getId());
                        gigi.setUploader(series.getId());

                        movies.add(gigi);
                    }
                    int size = movies.size();
                    ArrayList<SeriesObject> movieArrayList = new ArrayList<>();
                    for (int i = size - 1; i >= 0; i--) {
                        int rand = new Random().nextInt(size);
                        SeriesObject movie = movies.get(i);
                        if (!movieArrayList.contains(movie))
                            movieArrayList.add(movie);
                    }

                    if (movieArrayList.size() > 0) {

                        progressBar.setVisibility(View.INVISIBLE);
                        SeriesAd adapter = new SeriesAd(getActivity(), movieArrayList);
                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                        gigi.setLayoutManager(glm);
                        gigi.setAdapter(adapter);

                    }
                }
                if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Could not connect", Toast.LENGTH_SHORT).show();
                }
            }
        });










    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.sery, container, false);


    }






    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions();
        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }


    @Override
    public void onPause() {
        super.onPause();
      //  mRecyclerView.onPausePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mRecyclerView.paye();
    }
}
