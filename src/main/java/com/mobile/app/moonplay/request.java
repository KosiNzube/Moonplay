package com.mobile.app.moonplay;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class request extends AppCompatActivity {
    Button button;

    private String filmnam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        final String filmnam="AAAA";

        Intent intent = getIntent();
        final String filmname = intent.getExtras().getString("name");
        button=findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(request.this, "You will be notified soon", Toast.LENGTH_SHORT);
                toast.show();


            }
        });
        }
    }


