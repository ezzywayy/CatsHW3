package com.example.catshw3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatSearchFragment extends Fragment {


    private ArrayList<Cat> catArrayList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private String url;

    public CatSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_cat_search, container, false);
        View view = inflater.inflate(R.layout.fragment_cat_search, container, false);

        recyclerView = view.findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        final CatSearchAdapter searchAdapter = new CatSearchAdapter();
//        ImageButton searchButton = frameLayout.findViewById(R.id.searchButton);

        //filling recyclerview with api data
        requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://api.thecatapi.com/v1/breeds?api-key=2b4f1963-46bd-4341-a1e6-6d15ca0dd538";
//
//        sendRequest();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] catArray = gson.fromJson(response, Cat[].class);
                        ArrayList<Cat> catList = new ArrayList<Cat>(Arrays.asList(catArray));
                        searchAdapter.setData(catList);
                        recyclerView.setAdapter(searchAdapter);
                    }
                }, new Response.ErrorListener() {
//        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        //StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        //search function

        //search filter function
//        final EditText search = frameLayout.findViewById(R.id.searchBar);
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
        return view;
    }

//    public void sendRequest(){
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for(int i = 0; i < response.length(); i++){
//
//                    Cat cat = new Cat();
//
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//
//                        cat.setName(jsonObject.getString("name"));
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    catArrayList.add(cat);
//
//                }
//
//                catSearchAdapter = new CatSearchAdapter();
//
//                recyclerView.setAdapter(catSearchAdapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//
//    }

//    private void filter(String text) {
//        ArrayList<Cat> filteredList = new ArrayList<>();
//
//        for (Cat cat : catArrayList) {
//            if (cat.getName().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(cat);
//            }
//        }
//        catSearchAdapter.filterList(filteredList);
//    }
}


