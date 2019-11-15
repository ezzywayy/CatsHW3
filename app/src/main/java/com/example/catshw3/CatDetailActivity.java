package com.example.catshw3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class CatDetailActivity extends AppCompatActivity {
    private RequestQueue queue;
    private String catId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent1 = getIntent();
        catId = intent1.getStringExtra("id");

        final ConstraintLayout activity_detail = findViewById(R.id.constraintLayout);

        queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + catId;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                CatDetail[] catDetail = gson.fromJson(response, CatDetail[].class);
                ArrayList<CatDetail> catDetailArrayList = new ArrayList<CatDetail>(Arrays.asList(catDetail));
                CatDetail catDetailObject = catDetailArrayList.get(0);

                Cat[] cat = catDetailObject.getBreeds();
                ArrayList<Cat> catArrayList = new ArrayList<Cat>(Arrays.asList(cat));
                Cat catObject = catArrayList.get(0);
                CatWeight catWeightObject = catObject.getWeight();

                TextView catName = activity_detail.findViewById(R.id.catName);
                catName.setText(catObject.getName());

                TextView catDescription = activity_detail.findViewById(R.id.descriptionContent);
                catDescription.setText(catObject.getDescription());

                TextView catOrigin = activity_detail.findViewById(R.id.originContent);
                catOrigin.setText(catObject.getOrigin());

                TextView catWeight = activity_detail.findViewById(R.id.weightContent);
                catWeight.setText(catWeightObject.getMetric() + " kg");

                TextView catTemperament = activity_detail.findViewById(R.id.temperamentContent);
                catTemperament.setText(catObject.getTemperament());

                TextView catLifeSpan = activity_detail.findViewById(R.id.lifeSpanContent);
                catLifeSpan.setText(catObject.getLife_span() + " years");

                TextView wikipediaURL = activity_detail.findViewById(R.id.wikipediaContent);
                wikipediaURL.setText(catObject.getWikipedia_url());

                TextView dogLevel = activity_detail.findViewById(R.id.dogLevelContent);
                dogLevel.setText((Integer.toString(catObject.getDog_friendly()) + " / 5"));

                ImageView catImage = activity_detail.findViewById(R.id.catImage);
                Glide.with(getApplicationContext()).load(catDetailObject.getUrl()).into(catImage);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        queue.add(stringRequest);
    }
}
