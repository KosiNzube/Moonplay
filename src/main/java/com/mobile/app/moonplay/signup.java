package com.mobile.app.moonplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class signup extends AppCompatActivity {
    TextView login;
    ImageView back, koko,bot;
    ListView listView;
    EditText editText;
    ArrayList<Movie> movieArrayList = new ArrayList<>();
    String x;
    Button kim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        login=findViewById(R.id.kimberly);

        /*
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        login = findViewById(R.id.koko);
    //    Toast.makeText(this,Main6Activity.largest,Toast.LENGTH_SHORT).show();

        bot=findViewById(R.id.bot);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(signup.this, login.class);
                //startActivity(intent);
            }
        });
        editText = findViewById(R.id.name);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String name = editText.getText().toString();
                Intent intent = new Intent(signup.this, request.class);
                intent.putExtra("name", name);
                startActivity(intent);
                return true;
            }
        });
        listView = findViewById(R.id.grid);
        listView.setVisibility(View.INVISIBLE);
        koko = findViewById(R.id.array);

        editor.putInt("Status", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            editor.remove("Status_" + i);
            editor.putString("Status_" + i, (String) arrayList.get(i));
            editor.commit();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(signup.this);
      arrayList.clear();
        int size = sharedPreferences.getInt("Status", 0);
        for (int i = 0; i < size; i++) {
           arrayList.add(sharedPreferences.getString("Status_" + i, null));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup.this, android.R.layout.simple_list_item_activated_1, arrayList);
            listView.setAdapter(adapter);
            koko.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setVisibility(View.VISIBLE);


                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*

                    Intent o = new Intent(signup.this, search.class);
                    o.putExtra("bobo",(String) arrayList.get(position));
                    startActivity(o);*/


    }
}