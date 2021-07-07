package com.yogeeswar.weatherforecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class WeatherForecastFragment extends Fragment {
    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final RecyclerView forecastRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        WeatherDataService dataService = new WeatherDataService(getContext());

        final ArrayList<WeatherData> data = new ArrayList<WeatherData>();
        final WeatherForecastAdapter adapter = new WeatherForecastAdapter(data);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        forecastRecycler.setAdapter(adapter);
        forecastRecycler.setLayoutManager(layoutManager);
        dataService.getWeatherForecast("chennai", new WeatherDataService.ForecastListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(CityData cityData, ArrayList<WeatherData> weatherData) {
                Toast.makeText(getContext(), cityData.toString(), Toast.LENGTH_SHORT).show();
                for(WeatherData i : weatherData ){
                    data.add(i);
                }
                adapter.notifyDataSetChanged();
            }
        });

        return forecastRecycler;
    }
}