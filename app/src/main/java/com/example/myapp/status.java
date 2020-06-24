package com.example.myapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<carditem2> cardItems;
    Button btn1;
    private static final String URL="https://admintesting.herokuapp.com/seestatus";
    private RequestQueue queue;
    static String accessTkn;
    static String eid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        getSupportActionBar().setTitle("Application Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = (Button) findViewById(R.id.button1);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardItems=new ArrayList<>();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(status.this,homepage.class);
                startActivity(i1);

            }
        });

        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray array = new JSONArray(s);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject j=array.getJSONObject(i);
                                carditem2 item =new carditem2(
                                       j.getString("Application_id"),
                                       j.getString("Dept_name"),
                                        j.getString("Position_vacant"),
                                       j.getString("Roll_id"),
                                       j.getString("id_Status")

                                );
                                cardItems.add(item);
                            }
                            adapter=new MyCardAdapter2(cardItems,getApplicationContext());
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
                headers.put("Authorization", "Bearer "+accessTkn);
                return headers;
            }
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("EmailId","xcv@gmail.com");
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

/*

        JSONObject data = new JSONObject();
            try {
                data.put("EmailId", eid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject j=array.getJSONObject(i);
                                carditem2 item =new carditem2(
                                            j.getString("Application_id"),
                                            j.getString("Dept_name"),
                                            j.getString("Position_vacant"),
                                            j.getString("Roll_id"),
                                            j.getString("id_Status")

                                );
                                cardItems.add(item);
                            }
                            adapter=new MyCardAdapter2(cardItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
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

        };
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        queue.add(objectRequest);*/
        }

    }


