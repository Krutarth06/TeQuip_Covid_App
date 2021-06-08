package com.tequip.covitrackapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class country extends Fragment {
    EditText Search_view;
    ListView CountriesListView;
    ProgressBar loader;
    LinearLayout mainLayout;

    public static List<Model_class_country> country_model_list = new ArrayList<>();
    Model_class_country ModelCountry;
    custom_adapter myCustomAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_country, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Search_view = view.findViewById(R.id.search_bar_id);
        CountriesListView = view.findViewById(R.id.countries_list_view_id);
        loader = view.findViewById(R.id.main_loader_country);
        mainLayout = view.findViewById(R.id.mainLayout_countryList_id);

        fetchData();

        CountriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(), Details.class).putExtra("position", position));
            }
        });

        Search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/countries/";
        loader.animate();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest requestdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String todayRecovered = jsonObject.getString("todayRecovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");
                        String tests = jsonObject.getString("tests");
                        String continent = jsonObject.getString("continent");
                        String population = jsonObject.getString("population");
                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagUrl = object.getString("flag");

                        if (getActivity() != null) {
                            ModelCountry = new Model_class_country(population, countryName, flagUrl, cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, active, critical, tests, continent);
                            country_model_list.add(ModelCountry);
                        }
                    }

                    myCustomAdapter = new custom_adapter(getActivity(), country_model_list);
                    CountriesListView.setAdapter(myCustomAdapter);
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.clearAnimation();
                loader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Please switch on your Wifi or Mobile Data and try again later", Toast.LENGTH_SHORT).show();
            }
        });
        if(getActivity()!= null) {
            requestQueue.add(requestdata);
        }
    }
}