package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {
    private Button button1;
    private TextView tev1;
    private TextView tev2;
    static String accessTkn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(homepage.this,MainActivity.class);
                startActivity(i1);
            }
        });
        tev1= (TextView) findViewById(R.id.textView1);
        tev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolelist.accessTkn =accessTkn;
                Intent i2 = new Intent(homepage.this,rolelist.class);
                startActivity(i2);

            }
        });
        tev2= (TextView) findViewById(R.id.textView2);
        tev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.accessTkn =accessTkn;
                Intent i3 = new Intent(homepage.this,status.class);
                startActivity(i3);
            }
        });

    }
}