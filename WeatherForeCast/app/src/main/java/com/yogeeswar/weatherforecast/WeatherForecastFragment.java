package com.yogeeswar.weatherforecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yogeeswar.weatherforecast.adapter.WeatherForecastAdapter;
import com.yogeeswar.weatherforecast.model.WeatherData;

import java.util.ArrayList;

public class WeatherForecastFragment extends Fragment {
    public static WeatherForecastAdapter adapter;
    public static ArrayList<WeatherData> data;

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final RecyclerView forecastRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        WeatherDataService dataService = new WeatherDataService(getContext());

        data = new ArrayList<WeatherData>();
        adapter = new WeatherForecastAdapter(data);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        forecastRecycler.setAdapter(adapter);
        forecastRecycler.setLayoutManager(layoutManager);

        return forecastRecycler;
    }
}