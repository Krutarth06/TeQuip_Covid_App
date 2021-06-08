package com.tequip.covitrackapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    Button india, state, global, country;
    LinearLayout fragLayout;

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

        india = findViewById(R.id.india_frag_btn);
        state = findViewById(R.id.state_frag_btn);
        global = findViewById(R.id.global_frag_btn);
        country = findViewById(R.id.country_frag_btn);
        fragLayout = findViewById(R.id.frag_layout_id);

        addFragment();

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                india indiaFrag = new india();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_layout_id, indiaFrag);
                transaction.commit();

                india.setBackgroundResource(R.drawable.selected_btn_design);
                india.setTextColor(getResources().getColor(R.color.white));

                global.setBackgroundResource(R.drawable.btn_design);
                global.setTextColor(getResources().getColor(R.color.main_color));
                state.setBackgroundResource(R.drawable.btn_design);
                state.setTextColor(getResources().getColor(R.color.main_color));
                country.setBackgroundResource(R.drawable.btn_design);
                country.setTextColor(getResources().getColor(R.color.main_color));
            }
        });
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state stateFrag = new state();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_layout_id, stateFrag);
                transaction.commit();

                state.setBackgroundResource(R.drawable.selected_btn_design);
                state.setTextColor(getResources().getColor(R.color.white));

                global.setBackgroundResource(R.drawable.btn_design);
                global.setTextColor(getResources().getColor(R.color.main_color));
                india.setBackgroundResource(R.drawable.btn_design);
                india.setTextColor(getResources().getColor(R.color.main_color));
                country.setBackgroundResource(R.drawable.btn_design);
                country.setTextColor(getResources().getColor(R.color.main_color));
            }
        });
        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global globalFrag = new global();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_layout_id, globalFrag);
                transaction.commit();

                global.setBackgroundResource(R.drawable.selected_btn_design);
                global.setTextColor(getResources().getColor(R.color.white));

                state.setBackgroundResource(R.drawable.btn_design);
                state.setTextColor(getResources().getColor(R.color.main_color));
                india.setBackgroundResource(R.drawable.btn_design);
                india.setTextColor(getResources().getColor(R.color.main_color));
                country.setBackgroundResource(R.drawable.btn_design);
                country.setTextColor(getResources().getColor(R.color.main_color));
            }
        });
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country countryFrag = new country();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_layout_id, countryFrag);
                transaction.commit();

                country.setBackgroundResource(R.drawable.selected_btn_design);
                country.setTextColor(getResources().getColor(R.color.white));

                global.setBackgroundResource(R.drawable.btn_design);
                global.setTextColor(getResources().getColor(R.color.main_color));
                india.setBackgroundResource(R.drawable.btn_design);
                india.setTextColor(getResources().getColor(R.color.main_color));
                state.setBackgroundResource(R.drawable.btn_design);
                state.setTextColor(getResources().getColor(R.color.main_color));
            }
        });
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        india indiaFrag = new india();
        fragmentTransaction.add(R.id.frag_layout_id, indiaFrag);
        fragmentTransaction.commit();

        india.setBackgroundResource(R.drawable.selected_btn_design);
        india.setTextColor(getResources().getColor(R.color.white));

        global.setBackgroundResource(R.drawable.btn_design);
        global.setTextColor(getResources().getColor(R.color.main_color));
        state.setBackgroundResource(R.drawable.btn_design);
        state.setTextColor(getResources().getColor(R.color.main_color));
        country.setBackgroundResource(R.drawable.btn_design);
        country.setTextColor(getResources().getColor(R.color.main_color));
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_wp:
                boolean installed = isAppInstalled("com.whatsapp");
                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?text= Hey, CoviTrack is an Android app made by TeQuip Software Solutions that I use to check covid statistics. Get it for free at : https://play.google.com/store/apps/details?id=com.tequip.covitrackapp"));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Whatsapp is not installed on your device.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rate_us:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.covitrackapp");
                break;
        }

        return true;
    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;
        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
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
            case R.id.nav_add_features:
                startActivity(new Intent(getApplicationContext(), additional_features.class));
                break;
            case R.id.nav_share_app:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plane");
                share.putExtra(Intent.EXTRA_TEXT, "Hey, CoviTrack is an Android app made by TeQuip Software Solutions that I use to check covid statistics. Get it for free at: https://play.google.com/store/apps/details?id=com.tequip.covitrackapp");
                startActivity(share);
                break;
            case R.id.nav_rate_app:
                gotoUrl("https://play.google.com/store/apps/details?id=com.tequip.covitrackapp");
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