package com.tequip.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class intro_slider_main extends AppCompatActivity {
    private ViewPager introSlider;
    private TextView next;
    private LinearLayout layoutDots;
    private intro_sliders_preference sliders_preference;
    private int[] Layouts;
    private TextView[] dots;
    private MyViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider_main);
        next = findViewById(R.id.Next_textview_id);
        layoutDots = findViewById(R.id.layoutDots_id);
        introSlider = findViewById(R.id.IntroSlider_viewPager_id);

        sliders_preference = new intro_sliders_preference(this);
        if (!sliders_preference.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        Layouts = new int[]{
                R.layout.intro_slider_one,
                R.layout.intro_slider_two,
                R.layout.intro_slider_three,
        };

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < Layouts.length) {
                    introSlider.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        viewPagerAdapter = new MyViewPagerAdapter();
        introSlider.setAdapter(viewPagerAdapter);
        introSlider.addOnPageChangeListener(onPageChangeListener);
        addBottomDots(0);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == Layouts.length - 1) {
                next.setText("Get Started");
            } else {
                next.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[Layouts.length];
        int[] activeColors = getResources().getIntArray(R.array.active);
        int[] inactiveColors = getResources().getIntArray(R.array.inactive);
        layoutDots.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(inactiveColors[currentPage]);
            layoutDots.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[currentPage].setTextColor(activeColors[currentPage]);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {

        }

        @NotNull
        @Override
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(Layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return Layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NotNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private int getItem(int i) {
        return introSlider.getCurrentItem() + 1;
    }

    private void launchHomeScreen() {
        sliders_preference.setIsFirstTimeLaunch(false);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}