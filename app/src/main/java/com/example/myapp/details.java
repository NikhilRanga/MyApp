package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class details extends AppCompatActivity {
    private Button button1;

    static String accessTkn;
    EditText roleid, prefsub, email, research;
    static final String Key_roleid = "Roll_id";
    static final String Key_prefsub = "preferred_subj";
    static final String Key_email = "EmailId";
    static final String Key_research = "Research_details";
    private String role_id;
    private String email_id;
    private String pref_sub;
    private String research_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1=(Button)findViewById(R.id.button1);
        roleid= (EditText) findViewById(R.id.roleid);
        email= (EditText) findViewById(R.id.emailid);
        prefsub= (EditText) findViewById(R.id.prefsubject);
        research= (EditText) findViewById(R.id.researchdet);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails();
            }
        });

    }
    public void userDetails()  {
        role_id= roleid.getText().toString().trim();
        email_id= email.getText().toString().trim();
        pref_sub= prefsub.getText().toString().trim();
        research_details= research.getText().toString().trim();

        String URL = "https://admintesting.herokuapp.com/appdetails";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG);
                        toast.show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Key_roleid,role_id);
                params.put(Key_email,email_id);
                params.put(Key_prefsub,pref_sub);
                params.put(Key_research,research_details);
                return params;
            }
        };
       stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}






