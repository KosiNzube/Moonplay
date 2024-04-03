package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class notelin extends Fragment {
    String cast;
    TextView x,y,z,xo,thriller;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference medieval = db.collection("comp");
    //private CollectionReference collectionReference = db.collection("Movies");
    private CollectionReference series = db.collection("Series");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setBackgroundDrawable(
                    new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.kokocolor)));
        }


        x=view.findViewById(R.id.genre);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        //      Date date= (Date) tutorialsObject.get("date");

        Date date1=new Date();
        sdf.format(date1);



        thriller=view.findViewById(R.id.genrex);
        Timestamp timestamp = new Timestamp(date1);



        series.orderBy("timestamp", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<SeriesObject> movieArrayList = new ArrayList<>();

                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        SeriesObject movie = documentSnapshot.toObject(SeriesObject.class);
                        movie.setVideo(documentSnapshot.getId());
                        movie.setUploader(series.getId());


                        movieArrayList.add(movie);
                    }


                    if (movieArrayList.size() > 0) {

                        thriller.setVisibility(View.INVISIBLE);
                        RecyclerView trendingRecy1=view.findViewById(R.id.yiyii);
                        SeriesAd2 adapter1 = new SeriesAd2(getActivity(), movieArrayList);
                        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
                        trendingRecy1.setLayoutManager(glm);
                        trendingRecy1.setAdapter(adapter1);



                    }else {
                        thriller.setVisibility(View.VISIBLE);
                    }

                }
                if (!task.isSuccessful()){
                    Toast.makeText(getActivity(),"Could not connect",Toast.LENGTH_SHORT).show();
                }
            }
        });






    }









    @Override
    public void onStart () {
        super.onStart();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main13tyy, container, false);

    }




}


