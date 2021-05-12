package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.justinclicks.numbertowords.NumberToWordConverterMillionFormat;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Statistics extends AppCompatActivity {
    PieChart mPieChart;
//    SimpleArcLoader archloader;
    TextView cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, Active, critical, affectedCountries, tests,
        cases_w, todayCases_w, deaths_w, todayDeaths_w, recovered_w, todayRecovered_w, Active_w, critical_w, affectedCountries_w, tests_w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mPieChart = findViewById(R.id.piechart);
        cases = findViewById(R.id.TotalCases_Textview_id);
        todayCases = findViewById(R.id.TodayCases_textview_id);
        deaths = findViewById(R.id.death_Textview_id);
        todayDeaths = findViewById(R.id.todayDeaths_textview_id);
        recovered = findViewById(R.id.recovered_Textview_id);
        todayRecovered = findViewById(R.id.TodayRecovered_textview_id);
        Active = findViewById(R.id.active_Textview_id);
        critical = findViewById(R.id.critical_textview_id);
        affectedCountries = findViewById(R.id.AffectedCountries_textview_id);
        tests = findViewById(R.id.tests_textview_id);

        cases_w = findViewById(R.id.TotalCases_words_Textview_id);
        todayCases_w = findViewById(R.id.TodayCases_words_textview_id);
        deaths_w = findViewById(R.id.death_words_Textview_id);
        todayDeaths_w = findViewById(R.id.todayDeaths_words_textview_id);
        recovered_w = findViewById(R.id.recovered_words_Textview_id);
        todayRecovered_w = findViewById(R.id.TodayRecovered_words_textview_id);
        Active_w = findViewById(R.id.active_words_Textview_id);
        critical_w = findViewById(R.id.critical_words_textview_id);
        affectedCountries_w = findViewById(R.id.AffectedCountries_words_textview_id);
        tests_w = findViewById(R.id.tests_words_textview_id);
//        archloader = findViewById(R.id.loader);


        fetchData();
    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/all/";
//        archloader.start();
        StringRequest requestdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //  fetching data from API and representing it in respective text view
                    JSONObject jsonObject = new JSONObject(response);
                    cases.setText(jsonObject.getString("cases"));
                    todayCases.setText(jsonObject.getString("todayCases"));
                    deaths.setText(jsonObject.getString("deaths"));
                    todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    todayRecovered.setText(jsonObject.getString("todayRecovered"));
                    Active.setText(jsonObject.getString("active"));
                    critical.setText(jsonObject.getString("critical"));
                    affectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tests.setText(jsonObject.getString("tests"));

                    //  Representation of data in pie chart
                    mPieChart.addPieSlice(new PieModel("Affected", Integer.parseInt(cases.getText().toString()), Color.parseColor("#ffcc00")));
                    mPieChart.addPieSlice(new PieModel("Death", Integer.parseInt(deaths.getText().toString()), Color.parseColor("#ff3300")));
                    mPieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recovered.getText().toString()), Color.parseColor("#00e600")));
                    mPieChart.addPieSlice(new PieModel("Active", Integer.parseInt(Active.getText().toString()), Color.parseColor("#00ccff")));
                    mPieChart.startAnimation();

                    NumberToWordConverterMillionFormat Obj = new NumberToWordConverterMillionFormat();
                    String cases1 = Obj.convert(Integer.parseInt(cases.getText().toString()));
                    String cases2 = Obj.convert(Integer.parseInt(todayCases.getText().toString()));
                    String cases3 = Obj.convert(Integer.parseInt(deaths.getText().toString()));
                    String cases4 = Obj.convert(Integer.parseInt(todayDeaths.getText().toString()));
                    String cases5 = Obj.convert(Integer.parseInt(recovered.getText().toString()));
                    String cases6 = Obj.convert(Integer.parseInt(todayRecovered.getText().toString()));
                    String cases7 = Obj.convert(Integer.parseInt(Active.getText().toString()));
                    String cases8 = Obj.convert(Integer.parseInt(critical.getText().toString()));
                    String cases9 = Obj.convert(Integer.parseInt(affectedCountries.getText().toString()));
                    String cases10 = Obj.convert(Integer.parseInt(tests.getText().toString()));

                    cases_w.setText(cases1);
                    todayCases_w.setText(cases2);
                    deaths_w.setText(cases3);
                    todayDeaths_w.setText(cases4);
                    recovered_w.setText(cases5);
                    todayRecovered_w.setText(cases6);
                    Active_w.setText(cases7);
                    critical_w.setText(cases8);
                    affectedCountries_w.setText(cases9);
                    tests_w.setText(cases10);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Statistics.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestdata);
    }
}