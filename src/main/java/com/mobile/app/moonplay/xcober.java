package com.mobile.app.moonplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class xcober extends Fragment {
    private List<beat> audioItemList;
    RecyclerView recyclerView,dance,afro,soul,techno;
    beatadapter beatx;
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


    }


    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


   //     recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView= (RecyclerView)getActivity().findViewById(R.id.x);
        audioItemList=new ArrayList<beat>();
        audioItemList.add(new beat("https://www","8mins","Forever","https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Fsound-classical-music.gif?alt=media&token=f19d91a4-5709-4420-9ef2-73aa377f0f0c",false,"Xcober","calm","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Heaven","https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(7).gif?alt=media&token=082615fd-3e99-40ca-b27e-aef31ce3d485",true,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Uranium","https://firebasestorage.googleapis.com/v0/b/videogenix-aed11.appspot.com/o/gif%2Ftenor%20(12).gif?alt=media&token=a335088a-4dd3-455f-ad1b-4bc6e582ef7f",false,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Tail Fox","http://www.",false,"Xcober","r&b","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Forever","http://www.",false,"Xcober","calm","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Heaven","http://www.",true,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Uranium","http://www.",false,"Xcober","afro","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));
        audioItemList.add(new beat("https://www","8mins","Tail Fox","http://www.",false,"Xcober","r&b","#Afrobeat, Mind Blowing, Meditative.. Rudimentary"));


        beatx=new beatadapter(getActivity(),audioItemList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(beatx);

        dance=getActivity().findViewById(R.id.dancex);
        dance.setHasFixedSize(true);
        dance.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        dance.setAdapter(beatx);


        afro=getActivity().findViewById(R.id.afrox);
        afro.setHasFixedSize(true);
        afro.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        afro.setAdapter(beatx);

        afro=getActivity().findViewById(R.id.afrox);
        afro.setHasFixedSize(true);
        afro.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        afro.setAdapter(beatx);

        soul=getActivity().findViewById(R.id.soulx);
        soul.setHasFixedSize(true);
        soul.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        soul.setAdapter(beatx);

        techno=getActivity().findViewById(R.id.technox);
        techno.setHasFixedSize(true);
        techno.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        techno.setAdapter(beatx);
    }



        @Override
        public void onStart () {
            super.onStart();
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            return inflater.inflate(R.layout.xcobar, container, false);

        }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active=connectivityManager.getActiveNetworkInfo();
        return active !=null && active.isConnected();

    }

}
