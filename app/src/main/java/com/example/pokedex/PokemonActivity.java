package com.example.pokedex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView numberTextView;
    private String url;
    private TextView type1Pokemon;
    private TextView type2Pokemon;
    private RequestQueue requestQueue;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        url = getIntent().getStringExtra("url");
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        nameTextView = findViewById(R.id.pokemon_name);
        numberTextView = findViewById(R.id.pokemon_number);
        type1Pokemon = findViewById(R.id.pokemon_type1);
        type2Pokemon = findViewById(R.id.pokemon_type2);
        load();
    }



    // Getting data from internet
    public void  load() {
        type1Pokemon.setText("");
        type2Pokemon.setText("");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    nameTextView.setText(response.getString("name"));
                    numberTextView.setText(String.format("#%03d",response.getInt("id")));
                    JSONArray typeEntries = response.getJSONArray("types");
                    for (int i = 0; i < typeEntries.length(); i++)
                    {
                        JSONObject result = typeEntries.getJSONObject(i);
                        int slot = result.getInt("slot");
                        String type = result.getJSONObject("type").getString("name");
                        if (slot == 1) {
                            type1Pokemon.setText(type);
                        } else if (slot == 2) {
                            type2Pokemon.setText(type);
                        }
                    }

                } catch (JSONException e) {
                    Log.e("cs50", "Json error loading pokemon", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "Pokemon details error");
            }
        });

        requestQueue.add(request);
    }
}
