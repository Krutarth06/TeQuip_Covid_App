package com.example.tequip_covid_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class symptom_checker extends AppCompatActivity {
    private String webUrl = "https://symptomate.com/diagnosis/";
    WebView SymptomChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);
        SymptomChecker = (WebView) findViewById(R.id.symptom_web_view_id);
        SymptomChecker.getSettings().setJavaScriptEnabled(true);
        SymptomChecker.loadUrl(webUrl);

        SymptomChecker.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(webUrl);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (SymptomChecker.canGoBack()) {
            SymptomChecker.goBack();
        } else {
            super.onBackPressed();
        }
    }
}