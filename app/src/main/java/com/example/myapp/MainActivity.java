package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText uname,pass;
    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);

        String URL = "http://ec2-3-7-131-60.ap-south-1.compute.amazonaws.com/login";
        data = new JSONObject();
        try {
            data.put("username","admin");
            data.put("password","admin@123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queue = Volley.newRequestQueue(this);
        objectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            MainActivity2.accessTkn = response.getString("access_token");
                            openActivity2();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG);
                        toast.show();
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(objectRequest);
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}