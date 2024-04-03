package com.mobile.app.moonplay;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class homingdown extends Fragment {

    ImageView imageView;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    RecyclerView recyclerView;
    TextView nodown;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    private static final int ui = 786;
    List<music2> icemodels=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nodown=view.findViewById(R.id.textView5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, ui);
        } else {
            File x=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"KxMusic");
            if (!x.exists()){
                x.mkdir();
            }


            if (getList(x).size()>0) {
                for (int i = 0; i < getList(x).size(); i++) {

                        icemodels.add(new music2(getList(x).get(i).toString(), (int) getList(x).get(i).length(), getList(x).get(i).getName()));
                }


            }else {
                nodown.setVisibility(View.INVISIBLE);
            }




            RecyclerView view1 = (RecyclerView) getActivity().findViewById(R.id.recycler_id);
            view1.setHasFixedSize(true);
            view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));
            musicada adapter = new musicada(getActivity(), icemodels);
            view1.setAdapter(adapter);

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this songsfragment

        return inflater.inflate(R.layout.homingdown, container, false);
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
        if (requestCode == ui && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            File x=new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"KxMusic");
            if (!x.exists()){
                x.mkdir();
            }

            if (getList(x).size()==0||getList(x).size()<0){

            }



            if (getList(x).size()>0) {
                for (int i = 0; i < getList(x).size(); i++) {
                        icemodels.add(new music2(getList(x).get(i).toString(), (int) getList(x).get(i).length(), getList(x).get(i).getName()));

                }

            }

            RecyclerView view1 = (RecyclerView) getActivity().findViewById(R.id.recycler_id);
            view1.setHasFixedSize(true);
            view1.setLayoutManager(new speedy(getActivity(), speedy.VERTICAL, false));


            musicada adapter = new musicada(getActivity(), icemodels);
            view1.setAdapter(adapter);

        }
    }

}
