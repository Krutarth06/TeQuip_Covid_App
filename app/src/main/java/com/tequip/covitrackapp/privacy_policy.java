package com.tequip.covitrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class privacy_policy extends AppCompatActivity {
    ImageView back_btn;
    private String webUrl = "https://sites.google.com/view/covitrackprivacypolicy/home";
    WebView privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        back_btn = findViewById(R.id.privacy_policy_back_id);
        privacy_policy = (WebView) findViewById(R.id.privacy_policy_web_view_id);
        privacy_policy.getSettings().setJavaScriptEnabled(true);
        privacy_policy.loadUrl(webUrl);
        privacy_policy.setWebViewClient(new privacy_policy.MyWebViewClient());

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
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
        if (privacy_policy.canGoBack()) {
            privacy_policy.goBack();
        } else {
            super.onBackPressed();
        }
    }
}