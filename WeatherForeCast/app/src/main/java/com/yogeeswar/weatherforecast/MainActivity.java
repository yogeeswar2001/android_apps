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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText search_bar;
    WeatherDataService dataInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("--------------- yogi ----------------"+this);
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
        final String cityName = "chennai";
        dataInstance.getDataByCityName(cityName, new WeatherDataService.ResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(CityData cityData, WeatherData weatherData) {
                String msg = cityData.toString() + weatherData.toString();
                //Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
                System.out.println("============="+msg+"============");
                //setImage(weatherData.getImgIcon(), (ImageView) findViewById(R.id.image));
                TextView cityName = (TextView) findViewById(R.id.cityName);
                TextView currentDt = (TextView) findViewById(R.id.currentDt);
                TextView lon = (TextView) findViewById(R.id.lon);
                TextView lat = (TextView) findViewById(R.id.lat);

                System.out.println("++++++++++++++ "+cityData.getLat()+" "+cityData.getLon()+"+++++++++++++");
                cityName.setText(cityData.getCityName().toUpperCase(Locale.ROOT));
                currentDt.setText(weatherData.getDate());
                lon.setText("LONGITUDE: "+cityData.getLon());
                lat.setText("LATITUDE: "+cityData.getLat());

                ImageView icon = (ImageView) findViewById(R.id.currentIcon);
                TextView CurrentWeatherDesc = (TextView) findViewById(R.id.CurrentWeatherDesc);
                TextView CurrentTemp = (TextView) findViewById(R.id.CurrentTemp);

                String img_url = "http://openweathermap.org/img/wn/"+weatherData.getImgIcon()+"@2x.png";
                Picasso.get().load(img_url).resize(200,200).into(icon);
                CurrentWeatherDesc.setText(weatherData.getWeatherDesc());
                CurrentTemp.setText(weatherData.getTemp()+" K");

                TextView pressure = (TextView) findViewById(R.id.pressure);
                TextView humidity = (TextView) findViewById(R.id.humidity);
                TextView wind = (TextView) findViewById(R.id.wind);
                pressure.setText(weatherData.getPressure());
                humidity.setText(weatherData.getHumidity());
                wind.setText(weatherData.getWind());
            }
        });
    }
    
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