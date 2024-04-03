package com.mobile.app.moonplay;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SongsFragment extends Fragment {
    private List<MusicFiles> audioItemList;
    RecyclerView recyclerView;
    songadapter songadapterx;
    private  songQuery mediaQuery;
    private List<MusicFiles> videoItemList;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1124;
    final private int REQUEST_WRITE_PERMISSION = 1125;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkandAskPermission();
        } else {
            initVideo();
        }



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkandAskPermission() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("Storage");


        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 0; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return;
            }

            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }
        initVideo();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {

        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


   //     recyclerView=view.findViewById(R.id.recycler_view);

        }



        @Override
        public void onStart () {
            super.onStart();
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            return inflater.inflate(R.layout.songsfragment, container, false);

        }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }
    private void initVideo() {
        recyclerView= (RecyclerView)getActivity().findViewById(R.id.recycler_view);
        audioItemList=new ArrayList<MusicFiles>();
        mediaQuery=new songQuery(getContext());
        audioItemList=mediaQuery.getAllAudio();
        songadapterx=new songadapter(getActivity(), (ArrayList<MusicFiles>) audioItemList,communication);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(songadapterx);

    }
    FragmentCommunicationx communication=new FragmentCommunicationx() {
        @Override
        public void respond(int position, String genre, String num, String x,String y,String image) {
            currrentp fragmentB=new currrentp();
            Bundle bundle=new Bundle();
            bundle.putString("album",genre);
            bundle.putString("artist",num);
            bundle.putString("duration",x);
            bundle.putString("title",y);
            bundle.putString("path",image);


            fragmentB.setArguments(bundle);

            AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();

            appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.bibi,fragmentB).addToBackStack(null).commit();


        }

    };

}
