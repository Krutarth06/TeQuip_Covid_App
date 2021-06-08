package com.tequip.covitrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    private int position_country;
    com.github.mikephil.charting.charts.PieChart newPieChart;
    ProgressBar loader, Affected_p, Deaths_p, Active_p, Recovered_p;
    ImageView FlagImage, back_btn;
    TextView countryName, cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, Active, critical, tests, population, population_w,
            cases_w, todayCases_w, deaths_w, todayDeaths_w, recovered_w, todayRecovered_w, Active_w, critical_w, tests_w, affected_percent, death_percent, recovered_percent, active_percent;
    ScrollView mainlayout;
    String flagUrl;
    String perPopulation = "% of the Population.", perAffected = "% of total Affected.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        back_btn = findViewById(R.id.back_btn_id);

        Intent intent = getIntent();
        position_country = intent.getIntExtra("position", 0);
        mainlayout = findViewById(R.id.country_frag_scroll_view);
        FlagImage = findViewById(R.id.flag_image_id);
        countryName = findViewById(R.id.Country_name_textview_id);
        cases = findViewById(R.id.TotalCases_Textview_id);
        todayCases = findViewById(R.id.TodayCases_textview_id);
        deaths = findViewById(R.id.death_Textview_id);
        todayDeaths = findViewById(R.id.todayDeaths_textview_id);
        recovered = findViewById(R.id.recovered_Textview_id);
        todayRecovered = findViewById(R.id.TodayRecovered_textview_id);
        Active = findViewById(R.id.active_Textview_id);
        critical = findViewById(R.id.critical_textview_id);
        tests = findViewById(R.id.tests_textview_id);
        population = findViewById(R.id.Population_Textview_id);

        cases_w = findViewById(R.id.TotalCases_words_Textview_id);
        todayCases_w = findViewById(R.id.TodayCases_words_textview_id);
        deaths_w = findViewById(R.id.death_words_Textview_id);
        todayDeaths_w = findViewById(R.id.todayDeaths_words_textview_id);
        recovered_w = findViewById(R.id.recovered_words_Textview_id);
        todayRecovered_w = findViewById(R.id.TodayRecovered_words_textview_id);
        Active_w = findViewById(R.id.active_words_Textview_id);
        critical_w = findViewById(R.id.critical_words_textview_id);
        tests_w = findViewById(R.id.tests_words_textview_id);
        population_w = findViewById(R.id.Population_words_Textview_id);
        loader = findViewById(R.id.country_detail_progressbar);

        Affected_p = findViewById(R.id.Affected_progress_bar);
        Active_p = findViewById(R.id.Active_progress_bar);
        Deaths_p = findViewById(R.id.Death_progress_bar);
        Recovered_p = findViewById(R.id.Recovered_progress_bar);

        affected_percent = findViewById(R.id.progress_affected_percent_textview);
        death_percent = findViewById(R.id.progress_death_percent_textview);
        recovered_percent = findViewById(R.id.progress_recovered_percent_textview);
        active_percent = findViewById(R.id.progress_active_percent_textview);
        newPieChart = findViewById(R.id.new_pie_chart);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        loader.animate();
        flagUrl = country.country_model_list.get(position_country).getFlag();
        Glide.with(this).load(flagUrl).into(FlagImage);

        countryName.setText(country.country_model_list.get(position_country).getCountry());
        cases.setText(country.country_model_list.get(position_country).getCases());
        todayCases.setText(country.country_model_list.get(position_country).getTodayCases());
        deaths.setText(country.country_model_list.get(position_country).getDeaths());
        todayDeaths.setText(country.country_model_list.get(position_country).getTodayDeaths());
        recovered.setText(country.country_model_list.get(position_country).getRecovered());
        todayRecovered.setText(country.country_model_list.get(position_country).getTodayRecovered());
        Active.setText(country.country_model_list.get(position_country).getActive());
        critical.setText(country.country_model_list.get(position_country).getCritical());
        tests.setText(country.country_model_list.get(position_country).getTests());
        population.setText(country.country_model_list.get(position_country).getPopulation());

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

        loader.clearAnimation();
        loader.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);
    }

    private float progress(long progressDone, long total) {
        float percent = (float) ((progressDone * 100F) / total);
        return percent;
    }

}