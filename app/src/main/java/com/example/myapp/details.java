package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class details extends AppCompatActivity {
    private Button button1;
    private Button button2;
    static String accessTkn;
    EditText roleid, prefsub, email, research;
    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    static final String Key_roleid = "Roll_id";
    static final String Key_prefsub = "preferred_subj";
    static final String Key_email = "EmailId";
    static final String Key_research = "Research_details";

    private String role_id;
    private String email_id;
    private String pref_sub;
    private String research_details;
    JSONObject data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        button1=(Button)findViewById(R.id.button1);
        roleid= (EditText) findViewById(R.id.roleid);
        email= (EditText) findViewById(R.id.emailid);
        prefsub= (EditText) findViewById(R.id.prefsubject);
        research= (EditText) findViewById(R.id.researchdet);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails();
                queue.add(objectRequest);

            }
        });
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i1=new Intent(details.this,rolelist.class);
               startActivity(i1);
            }
        });
    }
    public void userDetails()
    {
        role_id= roleid.getText().toString().trim();
        email_id= email.getText().toString().trim();
        pref_sub= prefsub.getText().toString().trim();
        research_details= research.getText().toString().trim();

        String URL = "https://admintesting.herokuapp.com/appdetails";
        data = new JSONObject();
        try {
            data.put(Key_roleid,role_id);
            data.put(Key_email,email_id);
            data.put(Key_prefsub,pref_sub);
            data.put(Key_research,research_details);
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
                            Toast toast = Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG);
                            toast.show();
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
                return params;
            }
        };


    }

}



