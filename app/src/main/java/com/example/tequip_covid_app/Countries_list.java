package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.leo.simplearcloader.SimpleArcLoader;

public class Countries_list extends AppCompatActivity {
EditText Search_view;
ListView CountriesListView;
SimpleArcLoader arcLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        Search_view = findViewById(R.id.search_bar_id);
        CountriesListView = findViewById(R.id.countries_list_view_id);
        arcLoader = findViewById(R.id.loader1);
    }
}