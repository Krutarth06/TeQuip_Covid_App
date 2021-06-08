package com.tequip.covitrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class additional_features extends AppCompatActivity {
    LinearLayout viewStats, vaccination_booking, symptom_check, covid_precautions;
    Button call_btn, email_btn;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_features);
        viewStats = findViewById(R.id.view_stats);
        vaccination_booking = findViewById(R.id.vaccination_booking);
        symptom_check = findViewById(R.id.medical_test);
        covid_precautions = findViewById(R.id.covid_precautions);
        call_btn = findViewById(R.id.call_btn);
        email_btn = findViewById(R.id.email_btn);
        back_btn = findViewById(R.id.back_btn_id);

        viewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        vaccination_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), cowin_portal_webView.class));
            }
        });
        symptom_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), symptom_checker.class));
            }
        });
        covid_precautions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), covid_info.class));
            }
        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ncov2019@gov.in"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "I am having Covid-19 Emergency.");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: \nPhone number: \nAddress: \n I am having following symptoms:");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    private void call() {
        String number = "1075";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}