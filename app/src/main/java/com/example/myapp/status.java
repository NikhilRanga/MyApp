package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
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
    private static final String URL="https://admintesting.herokuapp.com/seestatus";
    static String accessTkn;
    private static final String Key_Email = "EmailId";
    static String eid;

    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        getSupportActionBar().setTitle("Application Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = (Button) findViewById(R.id.button1);

        btn3 = (Button) findViewById(R.id.button3);
        email = (EditText) findViewById(R.id.email);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardItems=new ArrayList<>();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIds();
                queue.add(objectRequest);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  status2.accessTkn =accessTkn;
                  Intent i1=new Intent(status.this,status2.class);
                  startActivity(i1);
            }
        });

    }

    public void userIds() {
        data = new JSONObject();
        try {
            data.put(Key_Email, eid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queue = Volley.newRequestQueue(this);
        objectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String s =response.toString();
                            JSONArray array = new JSONArray(s);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject j=array.getJSONObject(i);
                                carditem2 item =new carditem2(
                                        j.getString("Role ID"),
                                        j.getString("Application ID"));
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
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                params.put(Key_Email, eid);
                return params;
            }
        };


    }

}
