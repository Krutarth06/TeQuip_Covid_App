package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Details extends AppCompatActivity {
    private int position_country;
    PieChart mPieChart;
    Button trackCountries;
    SimpleArcLoader archloader;
    TextView countryName, cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, Active, critical, affectedCountries, tests,
            cases_w, todayCases_w, deaths_w, todayDeaths_w, recovered_w, todayRecovered_w, Active_w, critical_w, affectedCountries_w, tests_w;
    LinearLayout mainlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        position_country = intent.getIntExtra("position", 0);
        mainlayout = findViewById(R.id.main_linear_layout_id);
        mPieChart = findViewById(R.id.piechart);

        countryName = findViewById(R.id.Title_name_id);
        cases = findViewById(R.id.TotalCases_Textview_id);
        todayCases = findViewById(R.id.TodayCases_textview_id);
        deaths = findViewById(R.id.death_Textview_id);
        todayDeaths = findViewById(R.id.todayDeaths_textview_id);
        recovered = findViewById(R.id.recovered_Textview_id);
        todayRecovered = findViewById(R.id.TodayRecovered_textview_id);
        Active = findViewById(R.id.active_Textview_id);
        critical = findViewById(R.id.critical_textview_id);
        tests = findViewById(R.id.tests_textview_id);

        cases_w = findViewById(R.id.TotalCases_words_Textview_id);
        todayCases_w = findViewById(R.id.TodayCases_words_textview_id);
        deaths_w = findViewById(R.id.death_words_Textview_id);
        todayDeaths_w = findViewById(R.id.todayDeaths_words_textview_id);
        recovered_w = findViewById(R.id.recovered_words_Textview_id);
        todayRecovered_w = findViewById(R.id.TodayRecovered_words_textview_id);
        Active_w = findViewById(R.id.active_words_Textview_id);
        critical_w = findViewById(R.id.critical_words_textview_id);
        tests_w = findViewById(R.id.tests_words_textview_id);
        archloader = findViewById(R.id.loader);

        countryName.setText(Countries_list.country_model_list.get(position_country).getCountry());
        cases.setText(Countries_list.country_model_list.get(position_country).getCases());
        todayCases.setText(Countries_list.country_model_list.get(position_country).getTodayCases());
        deaths.setText(Countries_list.country_model_list.get(position_country).getDeaths());
        todayDeaths.setText(Countries_list.country_model_list.get(position_country).getTodayDeaths());
        recovered.setText(Countries_list.country_model_list.get(position_country).getRecovered());
        todayRecovered.setText(Countries_list.country_model_list.get(position_country).getTodayRecovered());
        Active.setText(Countries_list.country_model_list.get(position_country).getActive());
        critical.setText(Countries_list.country_model_list.get(position_country).getCritical());
        tests.setText(Countries_list.country_model_list.get(position_country).getTests());
        countryName.setText(Countries_list.country_model_list.get(position_country).getCountry());

        mPieChart.addPieSlice(new PieModel("Affected", Integer.parseInt(cases.getText().toString()), Color.parseColor("#ffcc00")));
        mPieChart.addPieSlice(new PieModel("Death", Integer.parseInt(deaths.getText().toString()), Color.parseColor("#ff3300")));
        mPieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recovered.getText().toString()), Color.parseColor("#00e600")));
        mPieChart.addPieSlice(new PieModel("Active", Integer.parseInt(Active.getText().toString()), Color.parseColor("#00ccff")));
        mPieChart.startAnimation();

//        cases_w.setText(Statistics.convert(Integer.parseInt(cases.getText().toString())));
//        todayCases_w.setText(Statistics.convert(Integer.parseInt(todayCases.getText().toString())));
//        deaths_w.setText(Statistics.convert(Integer.parseInt(deaths.getText().toString())));
//        todayDeaths_w.setText(Statistics.convert(Integer.parseInt(todayDeaths.getText().toString())));
//        recovered_w.setText(Statistics.convert(Integer.parseInt(recovered.getText().toString())));
//        todayRecovered_w.setText(Statistics.convert(Integer.parseInt(todayRecovered.getText().toString())));
//        Active_w.setText(Statistics.convert(Integer.parseInt(Active.getText().toString())));
//        critical_w.setText(Statistics.convert(Integer.parseInt(critical.getText().toString())));
//        affectedCountries_w.setText(Statistics.convert(Integer.parseInt(affectedCountries.getText().toString())));
//        tests_w.setText(Statistics.convert(Integer.parseInt(tests.getText().toString())));
    }

}