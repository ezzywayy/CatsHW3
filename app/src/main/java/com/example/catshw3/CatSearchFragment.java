package com.example.catshw3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    public CatSearchAdapter searchAdapter;
    public EditText editText;
    public String url;

    public CatSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_cat_search, container, false);
        final View view = inflater.inflate(R.layout.fragment_cat_search, container, false);

        recyclerView = view.findViewById(R.id.rv_search);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final CatSearchAdapter searchAdapter = new CatSearchAdapter(catArrayList);

//        ImageButton searchButton = frameLayout.findViewById(R.id.searchButton);

        //filling recyclerview with api data
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
         url = "https://api.thecatapi.com/v1/breeds?api-key=2b4f1963-46bd-4341-a1e6-6d15ca0dd538";
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
        final EditText editText = view.findViewById(R.id.searchBar);
        ImageButton searchButton = view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String searchText = editText.getText().toString().toLowerCase();
                if (searchText.equals("")) {
                    url = "https://api.thecatapi.com/v1/breeds?api-key=52d832db-85e1-4b0a-9eef-5b553017258d";
                } else {
                    url = "https://api.thecatapi.com/v1/breeds/search?q=" + searchText;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] catObjectArray = gson.fromJson(response, Cat[].class);
                        ArrayList<Cat> catsList = new ArrayList<>(Arrays.asList(catObjectArray));
                        searchAdapter.setData(catsList);
                        recyclerView.setAdapter(searchAdapter);
                    }
                };
                Response.ErrorListener errorListener1 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                };
                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, responseListener, errorListener1);
                requestQueue.add(stringRequest1);
            }

        });
        return view;

    }
}






