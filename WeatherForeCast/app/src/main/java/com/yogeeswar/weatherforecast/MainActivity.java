package com.yogeeswar.weatherforecast;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    EditText search_bar;
    WeatherDataService dataInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting up view pagers for tabs
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        //instantiating WeatherDataService
        dataInstance = new WeatherDataService(MainActivity.this);
    }

    public void onClickSearchBtn(View view) {
        //search_bar = (EditText) findViewById(R.id.search_bar);
        //final String cityName = search_bar.getText().toString();
        final String cityName = "chennai";
        dataInstance.getDataByCityName(cityName, new WeatherDataService.ResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(CityData cityData, WeatherData weatherData) {
                String msg = cityData.toString() + weatherData.toString();
                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
                setImage(weatherData.getImgIcon(), (ImageView) findViewById(R.id.image));
            }
        });
    }

//    public void onCLickSearchByCoord(View view) {
//        EditText lat,lon;
//        lat = (EditText) findViewById(R.id.lat);
//        lon = (EditText) findViewById(R.id.lon);
//        dataInstance.getDataByCoord(lon.getText().toString(), lat.getText().toString(), new WeatherDataService.ResponseListener() {
//            @Override
//            public void onError(String msg) {
//                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(WeatherData data) {
//                String msg = data.toString();
//                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
//                setImage(data.getImgIcon(), (ImageView) findViewById(R.id.image));
//            }
//        });
//    }
    private void setImage(String icon, ImageView view) {
        String img_url = "http://openweathermap.org/img/wn/"+icon+"@2x.png";
        Picasso.get().load(img_url).resize(300,300).into(view);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new CurrentWeatherFragment();
                case 1:
                    return new WeatherForecastFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getResources().getText(R.string.tab1_name);
                case 1:
                    return getResources().getText(R.string.tab2_name);
            }
            return null;
        }
    }
}