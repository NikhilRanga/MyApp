package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    static TextView text;

    private RequestQueue queue;
    JsonArrayRequest arrayRequest;
    JSONObject data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView) findViewById(R.id.textView);

        String URL = "http://ec2-3-7-131-60.ap-south-1.compute.amazonaws.com/tracking";
        queue = Volley.newRequestQueue(this);
        arrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            text.setText(response.getJSONObject(1).toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            text.setText(error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTE2OTAwMzUsIm5iZiI6MTU5MTY5MDAzNSwianRpIjoiNjBhZTRkY2UtOGI1Yy00NTMxLWEyOGMtMzU5ODkxYjY0M2I4IiwiaWRlbnRpdHkiOjIsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyJ9.MZx-St-BOGJma1uLPoib9AA0rQRD12aaiiVGxqzPsmo");
                return params;
            }
        };
        queue.add(arrayRequest);
    }

}