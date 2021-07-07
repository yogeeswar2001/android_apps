package com.yogeeswar.weatherforecast;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherDataService {
    private static final String API_KEY = "811d92599eddb881e0f4b6cb41ee64e8";
    private static final String CITY_NAME_URL = "https://api.openweathermap.org/data/2.5/weather?appid="+API_KEY+"&q=";
    private static final String COORD_URL = "http://api.openweathermap.org/data/2.5/weather?appid="+API_KEY;
    private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?appid="+API_KEY+"&q=";

    private Context context;

    public interface ResponseListener {
        void onError(String msg);
        void onResponse(CityData cityData, WeatherData weatherData);
    }

    public interface ForecastListener {
        void onError(String msg);
        void onResponse(CityData cityData, ArrayList<WeatherData> weatherData);
    }

    public void getDataByCityName(String cityName, final ResponseListener listener) {
        String url = CITY_NAME_URL +cityName;
        makeRequest(url, listener);
    }

    public void getDataByCoord(String lon, String lat, ResponseListener listener ){
        String url = COORD_URL+"&lat="+lat+"&lon="+lon;
        makeRequest(url, listener);
    }

    public void getWeatherForecast(String cityName, final ForecastListener listener) {
        String url = FORECAST_URL+cityName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();
                        WeatherData data;
                        CityData cityData;
                        try {
                            JSONArray list = response.getJSONArray("list");
                            JSONObject city = response.getJSONObject("city");

                            cityData = new CityData();
                            cityData.setLat(city.getJSONObject("coord").getString("lat"));
                            cityData.setLon(city.getJSONObject("coord").getString("lon"));
                            cityData.setCityName(city.getString("name"));

                            int n = Integer.parseInt(response.getString("cnt"));
                            for(int i=0;i<n;i++ ){
                                JSONObject temp = list.getJSONObject(i);
                                data = new WeatherData();
                                data.setTemp(temp.getJSONObject("main").getString("temp"));
                                data.setHumidity(temp.getJSONObject("main").getString("humidity"));
                                data.setDate(temp.getString("dt_txt"));
                                data.setWind(temp.getJSONObject("wind").getString("speed"));
                                data.setWeatherDesc(temp.getJSONArray("weather").getJSONObject(0).getString("description"));
                                data.setImgIcon(temp.getJSONArray("weather").getJSONObject(0).getString("icon"));

                                weatherData.add(data);
                            }

                            listener.onResponse(cityData, weatherData);
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.toString());
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    private void makeRequest(String url, final ResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherData weatherData;
                        CityData cityData;
                        //Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                            JSONObject main = response.getJSONObject("main");
                            JSONObject wind = response.getJSONObject("wind");

                            weatherData = new WeatherData();
                            cityData = new CityData();

                            cityData.setLon(coord.getString("lon"));
                            cityData.setLat(coord.getString("lat"));
                            cityData.setCityName(response.getString("name"));

                            weatherData.setWeatherDesc(weather.getString("description"));
                            weatherData.setImgIcon(weather.getString("icon"));
                            weatherData.setTemp(main.getString("temp"));
                            weatherData.setHumidity(main.getString("humidity"));
                            weatherData.setWind(wind.getString("speed"));

                            listener.onResponse(cityData, weatherData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                listener.onError(error.toString());
            }
        });

        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    WeatherDataService(Context context) {
        this.context = context;
    }
}