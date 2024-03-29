package com.tequip.covitrackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

    public class about_us extends AppCompatActivity {
        ImageView back_btn;
        private String webUrl = "https://tequipsoftwaresolutions.netlify.app/";
        WebView aboutUs;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_about_us);
            back_btn = findViewById(R.id.about_back_id);
            aboutUs = (WebView) findViewById(R.id.about_web_view_id);
            aboutUs.getSettings().setJavaScriptEnabled(true);
            aboutUs.loadUrl(webUrl);

            back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }


        @Override
        public void onBackPressed() {
            if (aboutUs.canGoBack()) {
                aboutUs.goBack();
            } else {
                super.onBackPressed();
            }
        }
    }

