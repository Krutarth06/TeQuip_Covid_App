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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class stateDetails extends AppCompatActivity {
    private int position_state;

    com.github.mikephil.charting.charts.PieChart newPieChart;
    ProgressBar loader, Deaths_p, Active_p, Recovered_p;
    ImageView back_btn;
    ScrollView mainlayout;
    TextView state, totalCasesIndia, totalCasesForeign, recovered, deaths, totalCases, Active, Active_w,
            totalCasesIndia_w, totalCasesForeign_w, recovered_w, deaths_w, totalCases_w, death_percent, recovered_percent, active_percent;
    String perAffected = "% of total Affected.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_details);
        back_btn = findViewById(R.id.back_btn_id);

        Intent intent = getIntent();
        position_state = intent.getIntExtra("position", 0);

        state = findViewById(R.id.Title_name_id);
        totalCasesIndia = findViewById(R.id.affected_Indians_textview_id);
        totalCasesForeign = findViewById(R.id.affected_foreigners_textview_id);
        recovered = findViewById(R.id.recovered_Textview_id);
        deaths = findViewById(R.id.death_Textview_id);
        totalCases = findViewById(R.id.TotalCases_Textview_id);
        Active = findViewById(R.id.active_Textview_id);

        totalCasesIndia_w = findViewById(R.id.affected_Indians_words_Textview_id);
        totalCasesForeign_w = findViewById(R.id.affected_foreigners_words_textview_id);
        recovered_w = findViewById(R.id.recovered_words_Textview_id);
        deaths_w = findViewById(R.id.death_words_Textview_id);
        totalCases_w = findViewById(R.id.TotalCases_words_Textview_id);
        Active_w = findViewById(R.id.active_words_Textview_id);

        loader = findViewById(R.id.state_detail_progressbar);

        Active_p = findViewById(R.id.Active_progress_bar);
        Deaths_p = findViewById(R.id.Death_progress_bar);
        Recovered_p = findViewById(R.id.Recovered_progress_bar);

        death_percent = findViewById(R.id.progress_death_percent_textview);
        recovered_percent = findViewById(R.id.progress_recovered_percent_textview);
        active_percent = findViewById(R.id.progress_active_percent_textview);

        mainlayout = findViewById(R.id.state_frag_scroll_view);
        newPieChart = findViewById(R.id.new_pie_chart);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        loader.animate();

        state.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getState());
        totalCasesIndia.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getTotalCasesIndia());
        totalCasesForeign.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getTotalCasesForeign());
        recovered.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getRecovered());
        deaths.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getDeaths());
        totalCases.setText(com.tequip.covitrackapp.state.states_model_list.get(position_state).getTotalCases());

        String active = "" + (int) (Long.parseLong(totalCases.getText().toString()) - Long.parseLong(deaths.getText().toString()) - Long.parseLong(recovered.getText().toString()));
        Active.setText(active);

        Recovered_p.setProgress((int) progress(Long.parseLong(recovered.getText().toString()), Long.parseLong(totalCases.getText().toString())));
        Deaths_p.setProgress((int) progress(Long.parseLong(deaths.getText().toString()), Long.parseLong(totalCases.getText().toString())));
        Active_p.setProgress((int) progress(Long.parseLong(Active.getText().toString()), Long.parseLong(totalCases.getText().toString())));

        float r_p = progress(Long.parseLong(recovered.getText().toString()), Long.parseLong(totalCases.getText().toString()));
        float d_p = progress(Long.parseLong(deaths.getText().toString()), Long.parseLong(totalCases.getText().toString()));
        float ac_p = progress(Long.parseLong(Active.getText().toString()), Long.parseLong(totalCases.getText().toString()));

        String recovered_percentage = String.format("%.2f", r_p) + perAffected;
        String deaths_percentage = String.format("%.2f", d_p) + perAffected;
        String active_percentage = String.format("%.2f", ac_p) + perAffected;

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


        totalCasesIndia_w.setText(Converter.format(Long.parseLong(totalCasesIndia.getText().toString())));
        totalCasesForeign_w.setText(Converter.format(Long.parseLong(totalCasesForeign.getText().toString())));
        recovered_w.setText(Converter.format(Long.parseLong(recovered.getText().toString())));
        deaths_w.setText(Converter.format(Long.parseLong(deaths.getText().toString())));
        totalCases_w.setText(Converter.format(Long.parseLong(totalCases.getText().toString())));
        Active_w.setText(Converter.format(Long.parseLong(Active.getText().toString())));


        loader.clearAnimation();
        loader.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);
    }

    private float progress(long progressDone, long total) {
        float percent = (float) ((progressDone * 100F) / total);
        return percent;
    }
}