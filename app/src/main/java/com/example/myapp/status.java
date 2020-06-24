package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class status extends AppCompatActivity {
    Button btn1;
    Button btn3;
    EditText email;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<carditem2> cardItems;
    private static final String URL = "https://admintesting.herokuapp.com/mydetails";
    static String accessTkn;
    private static final String Key_Email = "EmailId";
    private String emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        getSupportActionBar().setTitle("Application Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = (Button) findViewById(R.id.button1);
        btn3 = (Button) findViewById(R.id.button3);
        email = (EditText) findViewById(R.id.email);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardItems = new ArrayList<>();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailid = email.getText().toString().trim();
                 userIds();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status2.accessTkn = accessTkn;
                Intent i1 = new Intent(status.this, status2.class);
                startActivity(i1);
            }
        });

    }
    public void userIds() {
       emailid = email.getText().toString().trim();
        Toast toast = Toast.makeText(getApplicationContext(),emailid , Toast.LENGTH_LONG);
        toast.show();
      StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray array = new JSONArray(s);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject j = array.getJSONObject(i);
                                carditem2 item = new carditem2(
                                        j.getString("Role ID"),
                                        j.getString("Application ID"));
                                cardItems.add(item);
                            }
                            adapter = new MyCardAdapter2(cardItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + accessTkn);
                return headers;
            }
          @Override
          public Map<String, String> getParams() throws AuthFailureError {
              Map<String, String> params = new HashMap<String, String>();
              params.put("EmailId","xcv@gmail.com");
              return params;
          }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest );
    }
}


