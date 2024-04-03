package com.mobile.app.moonplay;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static com.mobile.app.moonplay.instantplay.setwinflag;

public class saver extends AppCompatActivity {
ArrayList m=new ArrayList();
ImageView imageButton,riri;
TextView textView,textView2,textView3;
RecyclerView myRecyclerView;
RelativeLayout host;
Button button,conbu;
TextView playlist;
Button cardView;
EditText editText;
ListView listView;
ProgressBar loading;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    final private int REQUEST_WRITE_PERMISSION = 125;
FloatingActionButton fab;
TextView yyy;
TextView Series,seriesButton;
ArrayList<Movie> arrayList=new ArrayList<>();
    ArrayList<String> arrayListx=new ArrayList<>();
TextView toprated;
TextView cont;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private AdLoader adLoader;
    private long downloadID;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    ImageView three;
    private boolean doublein=false;
    ViewPager viewPager;
    PageViewAdapter pageViewAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference medieval=db.collection("Italy");
    ArrayList<Object> movieArray=new ArrayList<>();

    private FloatingSearchView mSearchView;
    Button uiui;
    popular adapter;
    ImageView imageView;
    RecyclerView gigi;
    TextView popular,genre;
    public static ArrayList<File> fileArrayList=new ArrayList<>();
    File direcyory;
    boolean boo_perm;
    ImageView ioio;
    TextView noInternet;
    public static int REQUEST_PERMISSION=1;
    private Fragment home;
    private Fragment pins;
    private Fragment account;
    private FrameLayout frameLayout;
    BottomSheetDialog bottomSheetDialog;
    RecyclerView view1;
    private EditText etsearch;
    private ListView list;
    private ListViewAdapter adapterx;
    private String[] moviewList;
    public static ArrayList<MovieNames> movieNamesArrayList;
    public static ArrayList<MovieNames> array_sort;
    public static ArrayList<String> xy;
    int textlength = 0;
    String x,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saver);
        if (Build.VERSION.SDK_INT>=21){
            setwinflag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false);
            getWindow().setStatusBarColor(Color.BLACK);
            getWindow().setNavigationBarColor(Color.BLACK);
        }

        Intent intent=getIntent();
         x=intent.getExtras().getString("url");

         des=intent.getExtras().getString("jim");



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkandAskPermission();
        } else {
            downloade(x);
        }


        ImageView imageView=findViewById(R.id.backward);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }

    private void downloade(String x) {

        DownloadVideo DV=new DownloadVideo();
        DV.execute(x);
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
        downloade(x);
    }

    private class DownloadVideo extends AsyncTask<String,String,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            onBackPressed();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            downloadFiles(x);
            return null;
        }
    }

    private void downloadFiles(String x) {
       InputStream inputStream=null;
        OutputStream outputStream=null;
        HttpURLConnection connection=null;
        try {
            URL url=new URL(x);
            connection=(HttpURLConnection) url.openConnection();
            connection.connect();


            int filelength=connection.getContentLength();

            inputStream=connection.getInputStream();

            String rootDir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+File.separator+des+".mp4";
            File rootFile=new File(rootDir);

            outputStream=new FileOutputStream(rootFile);

            byte data[]=new byte[4096];

            long total=0;
            int count;
            while ((count=inputStream.read(data))!=-1){
                total+=count;
                outputStream.write(data,0,count);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                for (int i = permissions.length - 1; i >= 0; i--)
                    perms.put(permissions[i], grantResults[i]);
                if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    downloade(x);
                } else {
                    // Permission Denied
                    Toast.makeText(saver.this, "Some Permission is Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {

        new AlertDialog.Builder(saver.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (saver.this.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
        overridePendingTransition(0,0);

    }
}
