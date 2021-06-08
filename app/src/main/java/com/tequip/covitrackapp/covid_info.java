package com.tequip.covitrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class covid_info extends AppCompatActivity {
    private final String covid_info = "https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public/";
    WebView Covid_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_info);
        Covid_info = (WebView) findViewById(R.id.covid_info_web_view_id);
        WebSettings webSettings = Covid_info.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Covid_info.loadUrl(covid_info);
    }


    @Override
    public void onBackPressed() {
        if (Covid_info.canGoBack()) {
            Covid_info.goBack();
        } else {
            super.onBackPressed();
        }
    }
}