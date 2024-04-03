package com.mobile.app.moonplay;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class xy extends AppCompatActivity  {

    private static final String TAG = "MainActivity";
    private VideoView videoView;
    private MediaController mediaController;
    private EditText edtFixedGap, edtMinGap, edtMinFrom, edtMAxTo;
    private int trimType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy);


    }




}