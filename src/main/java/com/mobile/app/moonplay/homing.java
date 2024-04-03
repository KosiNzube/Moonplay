package com.mobile.app.moonplay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class homing extends Fragment {

    ImageView imageView;
    RecyclerView recyclerView;
    Fetch fetch;
    ArrayList complete;
    String sub;
    TextView nodown;
    String one;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    List<icemodel> icemodels=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nodown=view.findViewById(R.id.textView5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            File x=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"IcePlay");
            File x1=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"subtitles");
            if (!x.exists()){
                x.mkdir();
            }
            if (!x1.exists()){
                x1.mkdir();
            }


            complete=new ArrayList();
            FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(getActivity())
                    .setDownloadConcurrentLimit(10)
                    .build();







            if (getList(x).size()>0) {
                for (int i = 0; i < getList(x).size(); i++) {
                    Bitmap b = ThumbnailUtils.createVideoThumbnail(getList(x).get(i).toString(), MediaStore.Images.Thumbnails.MINI_KIND);

                    if (getList(x1).size()>0) {
                        sub= getList(x1).get(i).toString();
                    }

                        if (b!=null) {

                        icemodels.add(new icemodel(getList(x).get(i).toString(), b, (int) getList(x).get(i).length(), getList(x).get(i).getName(),sub));

                    }

                }


            }else {
                nodown.setVisibility(View.INVISIBLE);
            }




            RecyclerView view1 = (RecyclerView) getActivity().findViewById(R.id.recycler_id);
            view1.setHasFixedSize(true);
            view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
            iceAdapter adapter = new iceAdapter(getActivity(), icemodels);
            view1.setAdapter(adapter);

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this songsfragment

        return inflater.inflate(R.layout.fragment_homing, container, false);
    }


    private void requestPermission() {

    }
    private List<File> getList(File parentDir){
        ArrayList<File> inFiles=new ArrayList<File>();
        File[] files=parentDir.listFiles();


        for (File file: files) {
            if (file.isDirectory()) {
                inFiles.addAll(getList(file));
            } else {
                inFiles.add(file);

            }

        }
        return inFiles;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case  REQUEST_WRITE_PERMISSION:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){

                    }
                    File x=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"IcePlay");
                    File x1=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"subtitles");
                    if (!x.exists()){
                        x.mkdir();
                    }
                    if (!x1.exists()){
                        x1.mkdir();
                    }

                    if (getList(x).size()==0||getList(x).size()<0){

                    }



                    if (getList(x).size()>0) {
                        for (int i = 0; i < getList(x).size(); i++) {
                            Bitmap b = ThumbnailUtils.createVideoThumbnail(getList(x).get(i).toString(), MediaStore.Images.Thumbnails.MINI_KIND);

                            if (getList(x1).size()>0) {
                                sub= getList(x1).get(i).toString();
                            }

                            if (b!=null) {

                                icemodels.add(new icemodel(getList(x).get(i).toString(), b, (int) getList(x).get(i).length(), getList(x).get(i).getName(),sub));

                            }

                        }


                    }

                    if (getList(x).size()==0||getList(x).size()<0){

                    }


                    RecyclerView view1 = (RecyclerView) getActivity().findViewById(R.id.recycler_id);
                    view1.setHasFixedSize(true);
                    view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));


                    iceAdapter adapter = new iceAdapter(getActivity(), icemodels);
                    view1.setAdapter(adapter);

                }
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        File x=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"IcePlay");
        if (!x.exists()){
            x.mkdir();
        }
        getList(x);




    }


    private void refresh() {
        getActivity().finish();
        getActivity().overridePendingTransition(0,0);
        startActivity(getActivity().getIntent());
        getActivity().overridePendingTransition(0,0);
    }

}
