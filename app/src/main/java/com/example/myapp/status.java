package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class status extends AppCompatActivity {
    private ListView list;
    private Button Button1;
    static String accessTkn;
    String[] roles ={
            "Role1 : ","Role2 : ",
            "Role3 : ",
    };

    String[] status ={
            "Received","Pending",
            "Declined",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        list=(ListView)findViewById(R.id.lv);
        MyListAdapter adapter=new MyListAdapter(this, roles, status);
        list.setAdapter(adapter);
        list.setAdapter(adapter);
        Button1=(Button)findViewById(R.id.button1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent i1=new Intent(status.this,homepage.class);
                  startActivity(i1);
            }
        });
    }
}