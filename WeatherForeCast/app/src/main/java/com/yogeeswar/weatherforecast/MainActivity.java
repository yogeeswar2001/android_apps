package com.yogeeswar.weatherforecast;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.*;

import com.squareup.picasso.Picasso;
import com.yogeeswar.weatherforecast.model.CityData;
import com.yogeeswar.weatherforecast.model.WeatherData;

import java.util.ArrayList;
import java.util.Locale;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //instantiating WeatherDataService
                dataInstance = new WeatherDataService(MainActivity.this);
                final String cityName = query;
                dataInstance.getDataByCityName(cityName, new WeatherDataService.ResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(CityData cityData, WeatherData weatherData) {
                        TextView cityName = (TextView) findViewById(R.id.cityName);
                        TextView currentDt = (TextView) findViewById(R.id.currentDt);
                        TextView lon = (TextView) findViewById(R.id.lon);
                        TextView lat = (TextView) findViewById(R.id.lat);

                        cityName.setText(cityData.getCityName().toUpperCase(Locale.ROOT));
                        currentDt.setText(weatherData.getDate());
                        lon.setText("LONGITUDE: "+cityData.getLon());
                        lat.setText("LATITUDE: "+cityData.getLat());

                        ImageView icon = (ImageView) findViewById(R.id.currentIcon);
                        TextView CurrentWeatherDesc = (TextView) findViewById(R.id.CurrentWeatherDesc);
                        TextView CurrentTemp = (TextView) findViewById(R.id.CurrentTemp);

                        String img_url = "http://openweathermap.org/img/wn/"+weatherData.getImgIcon()+"@2x.png";
                        Picasso.get().load(img_url).fit().into(icon);
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

                WeatherForecastFragment.data.clear();
                dataInstance.getWeatherForecast(query, new WeatherDataService.ForecastListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(CityData cityData, ArrayList<WeatherData> weatherData) {
                        for(WeatherData i : weatherData ){
                            WeatherForecastFragment.data.add(i);
                        }
                        WeatherForecastFragment.adapter.notifyDataSetChanged();
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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