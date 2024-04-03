package com.mobile.app.moonplay;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class notix extends AppCompatActivity {
    private static final int REQUEST_WRITE_PERMISSION = 787;
    ImageView imageView;
    TextView nodown;
    ListView listView;
    List<icemodel> icemodels=new ArrayList<>();
    private ArrayList<String> names=new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference xxx=db.collection("noti");
    ArrayList<bibi> noti=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        imageView = findViewById(R.id.backln);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nodown=findViewById(R.id.iceplay);
        nodown.setText("Notifications");
        listView=findViewById(R.id.grid);

        xxx.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        bibi gigi = documentSnapshot.toObject(bibi.class);
                        noti.add(gigi);
                    }
                    ArrayList<bibi> x=new ArrayList<>();
                    for (int i = 0;i<noti.size();i++) {
                        bibi movie = noti.get(i);
                        if (!x.contains(movie)){
                            x.add(movie);
                        }
                        if (!names.contains(movie.getName())){
                            names.add(movie.getName());
                        }
                    }
                    if (x.size()>0){
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(notix.this, android.R.layout.simple_list_item_activated_1,names );
                        listView.setAdapter(adapter);

                    }
                }
            }
        });



    }

}