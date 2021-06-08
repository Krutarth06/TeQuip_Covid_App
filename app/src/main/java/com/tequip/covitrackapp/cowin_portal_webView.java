package com.tequip.covitrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class cowin_portal_webView extends AppCompatActivity {
    private String webUrl = "https://www.cowin.gov.in/";
    WebView CowinPortal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cowin_portal_web_view);
        CowinPortal = (WebView) findViewById(R.id.cowin_portal_webView_id);
        CowinPortal.getSettings().setJavaScriptEnabled(true);
        CowinPortal.loadUrl(webUrl);
    }


    @Override
    public void onBackPressed() {
        if (CowinPortal.canGoBack()) {
            CowinPortal.goBack();
        } else {
            super.onBackPressed();
        }
    }
}