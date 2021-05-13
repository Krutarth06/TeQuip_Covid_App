package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class Statistics extends AppCompatActivity {
    PieChart mPieChart;
    Button trackCountries;
    SimpleArcLoader archloader;
    TextView cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, Active, critical, affectedCountries, tests,
            cases_w, todayCases_w, deaths_w, todayDeaths_w, recovered_w, todayRecovered_w, Active_w, critical_w, affectedCountries_w, tests_w;
    LinearLayout mainlayout;
//    private static final String[] tens_names = {
//            "",
//            "Ten",
//            "Twenty",
//            "Thirty",
//            "Forty",
//            "Fifty",
//            "Sixty",
//            "Seventy",
//            "Eighty",
//            "Ninety",
//    };
//    private static final String[] num_names = {
//            "",
//            "Zero",
//            "One",
//            "Two",
//            "Three",
//            "Four",
//            "Five",
//            "Six",
//            "Seven",
//            "Eight",
//            "Nine",
//            "Ten",
//            "Eleven",
//            "Twelve",
//            "Thirteen",
//            "Fourteen",
//            "Fifteen",
//            "Sixteen",
//            "Seventeen",
//            "Eighteen",
//            "Nineteen",
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mainlayout = findViewById(R.id.main_linear_layout_id);
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
        archloader = findViewById(R.id.loader);


//      Initializing and functioning of button;
        trackCountries = findViewById(R.id.Track_countries_btn_id);
        trackCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackCountries();
            }
        });
        //     Main method to fetch and display data
        fetchData();
    }

    private void TrackCountries() {
        Intent intent = new Intent(this, Countries_list.class);
        startActivity(intent);

    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/all/";
        archloader.start();
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

                    // Stopping  the loading progressbar

                    archloader.stop();
                    archloader.setVisibility(View.GONE);
                    mainlayout.setVisibility(View.VISIBLE);



//                    cases_w.setText(convert((long) Integer.parseInt(cases.getText().toString())));
//                    todayCases_w.setText(convert((long) Integer.parseInt(todayCases.getText().toString())));
//                    deaths_w.setText(convert((long) Integer.parseInt(deaths.getText().toString())));
//                    todayDeaths_w.setText(convert((long) Integer.parseInt(todayDeaths.getText().toString())));
//                    recovered_w.setText(convert((long) Integer.parseInt(recovered.getText().toString())));
//                    todayRecovered_w.setText(convert((long) Integer.parseInt(todayRecovered.getText().toString())));
//                    Active_w.setText(convert((long) Integer.parseInt(Active.getText().toString())));
//                    critical_w.setText(convert((long) Integer.parseInt(critical.getText().toString())));
//                    affectedCountries_w.setText(convert((long) Integer.parseInt(affectedCountries.getText().toString())));
//                    tests_w.setText(convert((long) Integer.parseInt(tests.getText().toString())));

                } catch (JSONException e) {
                    e.printStackTrace();
                    archloader.stop();
                    archloader.setVisibility(View.GONE);
                    mainlayout.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                archloader.stop();
                archloader.setVisibility(View.GONE);
                mainlayout.setVisibility(View.VISIBLE);
                Toast.makeText(Statistics.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestdata);
    }

//    private static String convertLessThenOneThousand(int number) {
//        String soFar;
//        if (number % 100 < 20) {
//            soFar = num_names[number % 10];
//            number /= 100;
//        } else {
//            soFar = num_names[number % 10];
//            number /= 10;
//            soFar = tens_names[number % 10] + soFar;
//            number /= 100;
//        }
//        if (number == 0) {
//            return soFar;
//        }
//        return num_names[number] + "Hundred" + soFar;
//    }
//
//    public static String convert(Long number) {
//        if (number == 0) {
//            return "Zero";
//        }
//        String snumber = Long.toString(number);
//
//        String mask = "000000000000";
//        DecimalFormat df = new DecimalFormat(mask);
//        snumber = df.format(number);
//
//        int billions = Integer.parseInt(snumber.substring(0,3));
//
//        int millions = Integer.parseInt(snumber.substring(3,6));
//
//        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
//
//        int thousands = Integer.parseInt(snumber.substring(9,12));
//
//        String tradbillions ;
//        switch (billions){
//            case 0:
//                tradbillions ="";
//                break;
//
//            case 1:
//                tradbillions = convertLessThenOneThousand(billions)+ " billion ";
//                break;
//            default:
//                tradbillions = convertLessThenOneThousand(billions)+ " billion ";
//        }
//        String result = tradbillions;
//
//        String tradmillions ;
//        switch (millions){
//            case 0:
//                tradmillions ="";
//                break;
//
//            case 1:
//                tradmillions = convertLessThenOneThousand(millions)+ " million ";
//                break;
//            default:
//                tradmillions = convertLessThenOneThousand(millions)+ " million ";
//        }
//         result += tradmillions;
//
//        String tradHundredThousands ;
//        switch (hundredThousands){
//            case 0:
//                tradHundredThousands ="";
//                break;
//
//            case 1:
//                tradHundredThousands = " one thousand ";
//                break;
//            default:
//                tradHundredThousands = convertLessThenOneThousand(hundredThousands)+ " thousand ";
//        }
//        result += tradHundredThousands;
//
//        String tradThousand;
//        tradThousand = convertLessThenOneThousand(thousands);
//        result += tradThousand;
//
//        return result;
//    }
}