package com.tequip.covidapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    Button seeStat, seeStateStatistics, cowinLink, CountryWiseStat, callHelpline, emailHelpline, Symptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar_id);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        seeStat = findViewById(R.id.See_stat_btn_id);
        cowinLink = findViewById(R.id.Cowin_portal_btn_id);
        seeStateStatistics = findViewById(R.id.See_states_btn_id);
        Symptoms = findViewById(R.id.symptom_btn_id);
        CountryWiseStat = findViewById(R.id.countries_stat_btn_id);
        callHelpline = findViewById(R.id.call_btn);
        emailHelpline = findViewById(R.id.email_btn);

        callHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        emailHelpline.setOnClickListener(new View.OnClickListener() {
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

        Symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), symptom_checker.class));
            }
        });

        seeStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Statistics.class));
            }
        });

        seeStateStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), States_list.class));
            }
        });

        cowinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.cowin.gov.in/home");
            }
        });

        CountryWiseStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Countries_list.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_menu,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_wp:
                boolean installed = isAppInstalled("com.whatsapp");
                if(installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?text= Hey,Tequip covid tracker is a fast, simple and secure app that I use to check covid statistics of world and India. Get it for free at: https://play.google.com/store/apps/details?id=com.tequip.covidapp"));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Whatsapp is not installed on your device.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rate_us:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.covidapp");
                break;
        }

        return true;
    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;
        try{
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return  is_installed;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_symptoms:
                startActivity(new Intent(getApplicationContext(), symptom_checker.class));
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(getApplicationContext(), about_us.class));
                break;
            case R.id.nav_privacy_policy:
                startActivity(new Intent(getApplicationContext(), privacy_policy.class));
                break;
            case R.id.nav_share_app:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plane");
                share.putExtra(Intent.EXTRA_TEXT, "Hey,Tequip covid tracker is a fast, simple and secure app that I use to check covid statistics of " +
                        "world and India. Get it for free at: https://play.google.com/store/apps/details?id=com.tequip.covidapp");
                startActivity(share);
                break;
            case R.id.nav_rate_app:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.covidapp");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}