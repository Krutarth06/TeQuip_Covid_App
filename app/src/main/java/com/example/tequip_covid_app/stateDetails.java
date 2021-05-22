package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class stateDetails extends AppCompatActivity {
    private int position_state;
    PieChart mPieChart;
    ImageView back_btn;
    TextView state, totalCasesIndia, totalCasesForeign, recovered, deaths, totalCases,
            totalCasesIndia_w, totalCasesForeign_w, recovered_w, deaths_w, totalCases_w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_details);
        back_btn = findViewById(R.id.back_btn_id);

        Intent intent = getIntent();
        position_state = intent.getIntExtra("position", 0);

        mPieChart = findViewById(R.id.piechart);
        state = findViewById(R.id.Title_name_id);
        totalCasesIndia = findViewById(R.id.affected_Indians_textview_id);
        totalCasesForeign = findViewById(R.id.affected_foreigners_textview_id);
        recovered = findViewById(R.id.recovered_Textview_id);
        deaths = findViewById(R.id.death_Textview_id);
        totalCases = findViewById(R.id.TotalCases_Textview_id);

        totalCasesIndia_w = findViewById(R.id.affected_Indians_words_Textview_id);
        totalCasesForeign_w = findViewById(R.id.affected_foreigners_words_textview_id);
        recovered_w = findViewById(R.id.recovered_words_Textview_id);
        deaths_w = findViewById(R.id.death_words_Textview_id);
        totalCases_w = findViewById(R.id.TotalCases_words_Textview_id);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), States_list.class));
            }
        });

        state.setText(States_list.states_model_list.get(position_state).getState());
        totalCasesIndia.setText(States_list.states_model_list.get(position_state).getTotalCasesIndia());
        totalCasesForeign.setText(States_list.states_model_list.get(position_state).getTotalCasesForeign());
        recovered.setText(States_list.states_model_list.get(position_state).getRecovered());
        deaths.setText(States_list.states_model_list.get(position_state).getDeaths());
        totalCases.setText(States_list.states_model_list.get(position_state).getTotalCases());

        totalCasesIndia_w.setText(Converter.format(Long.parseLong(totalCasesIndia.getText().toString())));
        totalCasesForeign_w.setText(Converter.format(Long.parseLong(totalCasesForeign.getText().toString())));
        recovered_w.setText(Converter.format(Long.parseLong(recovered.getText().toString())));
        deaths_w.setText(Converter.format(Long.parseLong(deaths.getText().toString())));
        totalCases_w.setText(Converter.format(Long.parseLong(totalCases.getText().toString())));

        mPieChart.addPieSlice(new PieModel("Affected", Integer.parseInt(totalCases.getText().toString()), Color.parseColor("#ffaf1a")));
        mPieChart.addPieSlice(new PieModel("Death", Integer.parseInt(deaths.getText().toString()), Color.parseColor("#ff3300")));
        mPieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(recovered.getText().toString()), Color.parseColor("#00e600")));
        mPieChart.startAnimation();
    }
}