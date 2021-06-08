package com.tequip.covitrackapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class india extends Fragment {

    com.github.mikephil.charting.charts.PieChart newPieChart;
    ProgressBar loader, Affected_p, Deaths_p, Active_p, Recovered_p;
    ScrollView mainScrollView;
    TextView cases, deaths, recovered, Active, critical, tests, cases_w, population, population_w, todayCases, todayRecovered, todayDeaths, todayCases_w, todayDeaths_w, todayRecovered_w,
            deaths_w, recovered_w, Active_w, critical_w, tests_w, affected_percent, death_percent, recovered_percent, active_percent;
    ImageView flagImage;
    String perPopulation = "% of the Population.", perAffected = "% of total Affected.";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_india, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = view.findViewById(R.id.main_loader_india);
        mainScrollView = view.findViewById(R.id.india_frag_scroll_view);
        //mPieChart = view.findViewById(R.id.piechart_i);
        cases = view.findViewById(R.id.TotalCases_Textview_id);
        todayCases = view.findViewById(R.id.TodayCases_textview_id);
        deaths = view.findViewById(R.id.death_Textview_id);
        todayDeaths = view.findViewById(R.id.todayDeaths_textview_id);
        recovered = view.findViewById(R.id.recovered_Textview_id);
        todayRecovered = view.findViewById(R.id.TodayRecovered_textview_id);
        Active = view.findViewById(R.id.active_Textview_id);
        critical = view.findViewById(R.id.critical_textview_id);
        tests = view.findViewById(R.id.tests_textview_id);
        population = view.findViewById(R.id.Population_Textview_id);

        Affected_p = view.findViewById(R.id.Affected_progress_bar);
        Active_p = view.findViewById(R.id.Active_progress_bar);
        Deaths_p = view.findViewById(R.id.Death_progress_bar);
        Recovered_p = view.findViewById(R.id.Recovered_progress_bar);

        affected_percent = view.findViewById(R.id.progress_affected_percent_textview);
        death_percent = view.findViewById(R.id.progress_death_percent_textview);
        recovered_percent = view.findViewById(R.id.progress_recovered_percent_textview);
        active_percent = view.findViewById(R.id.progress_active_percent_textview);

        cases_w = view.findViewById(R.id.TotalCases_words_Textview_id);
        todayCases_w = view.findViewById(R.id.TodayCases_words_textview_id);
        deaths_w = view.findViewById(R.id.death_words_Textview_id);
        todayDeaths_w = view.findViewById(R.id.todayDeaths_words_textview_id);
        recovered_w = view.findViewById(R.id.recovered_words_Textview_id);
        todayRecovered_w = view.findViewById(R.id.TodayRecovered_words_textview_id);
        Active_w = view.findViewById(R.id.active_words_Textview_id);
        critical_w = view.findViewById(R.id.critical_words_textview_id);
        tests_w = view.findViewById(R.id.tests_words_textview_id);
        population_w = view.findViewById(R.id.Population_words_Textview_id);
        newPieChart = view.findViewById(R.id.new_pie_chart);
        flagImage = view.findViewById(R.id.flag_image_id_i);
        fetchData();

    }

    private void fetchData() {
        String url = "https://disease.sh/v3/covid-19/countries/india/";
        loader.animate();
        StringRequest requestdata = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //fetching data from API and representing it in respective text view
                    JSONObject jsonObject = new JSONObject(response);
                    cases.setText(jsonObject.getString("cases"));
                    todayCases.setText(jsonObject.getString("todayCases"));
                    deaths.setText(jsonObject.getString("deaths"));
                    todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    todayRecovered.setText(jsonObject.getString("todayRecovered"));
                    Active.setText(jsonObject.getString("active"));
                    critical.setText(jsonObject.getString("critical"));
                    tests.setText(jsonObject.getString("tests"));
                    population.setText(jsonObject.getString("population"));

                    JSONObject object = jsonObject.getJSONObject("countryInfo");
                    String flagUrl = object.getString("flag");
                    Glide.with(requireContext()).load(flagUrl).into(flagImage);

                    Affected_p.setProgress((int) progress(Long.parseLong(cases.getText().toString()), Long.parseLong(population.getText().toString())));
                    Recovered_p.setProgress((int) progress(Long.parseLong(recovered.getText().toString()), Long.parseLong(cases.getText().toString())));
                    Deaths_p.setProgress((int) progress(Long.parseLong(deaths.getText().toString()), Long.parseLong(cases.getText().toString())));
                    Active_p.setProgress((int) progress(Long.parseLong(Active.getText().toString()), Long.parseLong(cases.getText().toString())));

                    float af_p = progress(Long.parseLong(cases.getText().toString()), Long.parseLong(population.getText().toString()));
                    float r_p = progress(Long.parseLong(recovered.getText().toString()), Long.parseLong(cases.getText().toString()));
                    float d_p = progress(Long.parseLong(deaths.getText().toString()), Long.parseLong(cases.getText().toString()));
                    float ac_p = progress(Long.parseLong(Active.getText().toString()), Long.parseLong(cases.getText().toString()));

                    String affected_percentage = String.format("%.2f", af_p) + perPopulation;
                    String recovered_percentage = String.format("%.2f", r_p) + perAffected;
                    String deaths_percentage = String.format("%.2f", d_p) + perAffected;
                    String active_percentage = String.format("%.2f", ac_p) + perAffected;

                    affected_percent.setText(affected_percentage);
                    recovered_percent.setText(recovered_percentage);
                    death_percent.setText(deaths_percentage);
                    active_percent.setText(active_percentage);
                    newPieChart.setDrawHoleEnabled(true);
                    //newPieChart.setUsePercentValues(true);
                    newPieChart.setEntryLabelTextSize(12);
                    newPieChart.setEntryLabelColor(Color.BLACK);
                    newPieChart.setCenterText("Affected");
                    newPieChart.setCenterTextSize(18);
                    newPieChart.getDescription().setEnabled(false);

                    Legend l = newPieChart.getLegend();
                    l.setEnabled(false);

                    ArrayList<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry(Integer.parseInt(recovered.getText().toString()),""));
                    entries.add(new PieEntry(Integer.parseInt(Active.getText().toString()),""));
                    entries.add(new PieEntry(Integer.parseInt(deaths.getText().toString()),""));

                    ArrayList<Integer> colors = new ArrayList<>();
                    colors.add(getResources().getColor(R.color.light_green));
                    colors.add(getResources().getColor(R.color.yellow));
                    colors.add(getResources().getColor(R.color.red));

                    PieDataSet dataSet = new PieDataSet(entries,"");
                    dataSet.setColors(colors);

                    PieData data = new PieData(dataSet);
                    data.setDrawValues(true);
                    data.setValueTextSize(0f);
                    data.setValueTextColor(Color.WHITE);

                    newPieChart.setData(data);
                    newPieChart.invalidate();
                    newPieChart.animateY(1500, Easing.EaseInOutQuad);

                    //Numbers for number to word converter
                    cases_w.setText(Converter.format(Long.parseLong(cases.getText().toString())));
                    todayCases_w.setText(Converter.format(Long.parseLong(todayCases.getText().toString())));
                    deaths_w.setText(Converter.format(Long.parseLong(deaths.getText().toString())));
                    todayDeaths_w.setText(Converter.format(Long.parseLong(todayDeaths.getText().toString())));
                    recovered_w.setText(Converter.format(Long.parseLong(recovered.getText().toString())));
                    todayRecovered_w.setText(Converter.format(Long.parseLong(todayRecovered.getText().toString())));
                    Active_w.setText(Converter.format(Long.parseLong(Active.getText().toString())));
                    critical_w.setText(Converter.format(Long.parseLong(critical.getText().toString())));
                    tests_w.setText(Converter.format(Long.parseLong(tests.getText().toString())));
                    population_w.setText(Converter.format(Long.parseLong(population.getText().toString())));

                    if (getActivity() != null) {
                        loader.clearAnimation();
                        loader.setVisibility(View.GONE);
                        mainScrollView.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                }
            }
//
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.clearAnimation();
                loader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Please switch on your Wifi or Mobile Data and try again later", Toast.LENGTH_SHORT).show();
            }
        });
        if (getActivity() != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(requestdata);
        }
    }

    private float progress(long progressDone, long total) {
        float percent = (float) ((progressDone * 100F) / total);
        return percent;
    }
}