package com.yogeeswar.movieapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.yogeeswar.movieapp.models.MovieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<MovieData> topMovieData = new ArrayList<MovieData>();
    static ArrayList<MovieData> upcomingMovieData = new ArrayList<MovieData>();
    static ArrayList<MovieData> searchMovieData = new ArrayList<MovieData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position ) {
            switch(position){
                case 0:
                    return new TopMovieFragment();
                case 1:
                    return new UpcomingMovieFragment();
                case 2:
                    return new SearchMovieFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getResources().getText(R.string.title1);
                case 1:
                    return getResources().getText(R.string.title2);
                case 2:
                    return getResources().getText(R.string.title3);
            }
            return null;
        }
    }
}