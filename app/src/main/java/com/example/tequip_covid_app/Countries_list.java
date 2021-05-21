package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries_list extends AppCompatActivity {
    EditText Search_view;
    ListView CountriesListView;
    SimpleArcLoader arcLoader;

    public static List<Model_class_country> country_model_list = new ArrayList<>();
    Model_class_country ModelCountry;
    custom_adapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        Search_view = findViewById(R.id.search_bar_id);
        CountriesListView = findViewById(R.id.countries_list_view_id);
        arcLoader = findViewById(R.id.loader1);

        fetchData();

        CountriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Details.class).putExtra("position", position));
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
        arcLoader.start();
        StringRequest requestdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;
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

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagUrl = object.getString("flag");

                        ModelCountry = new Model_class_country(countryName, flagUrl, cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, active, critical, tests, continent);
                        country_model_list.add(ModelCountry);
                    }

                    myCustomAdapter = new custom_adapter(Countries_list.this, country_model_list);
                    CountriesListView.setAdapter(myCustomAdapter);
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
                Toast.makeText(Countries_list.this, "Please check your Internet connection and try again later", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestdata);
    }
}