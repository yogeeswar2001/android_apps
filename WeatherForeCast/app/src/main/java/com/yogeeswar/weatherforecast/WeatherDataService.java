package com.yogeeswar.weatherforecast;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {
    private static final String API_KEY = "811d92599eddb881e0f4b6cb41ee64e8";
    private static final String CITY_NAME_URL = "https://api.openweathermap.org/data/2.5/weather?appid="+API_KEY+"&q=";
    private static final String COORD_URL = "http://api.openweathermap.org/data/2.5/weather?appid="+API_KEY;

    private Context context;

    public interface ResponseListener {
        void onError(String msg);
        void onResponse(WeatherData data);
    }

    public void getDataByCityName(String cityName, final ResponseListener listener) {
        String url = CITY_NAME_URL +cityName;
        makeRequest(url, listener);
    }

    public void getDataByCoord(String lon, String lat, ResponseListener listener ){
        String url = COORD_URL+"&lat="+lat+"&lon="+lon;
        makeRequest(url, listener);
    }

    private void makeRequest(String url, final ResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherData data;
                        //Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject coord = response.getJSONObject("coord");
                            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                            JSONObject main = response.getJSONObject("main");
                            JSONObject wind = response.getJSONObject("wind");

                            data = new WeatherData();
                            data.setLon(coord.getString("lon"));
                            data.setLat(coord.getString("lat"));
                            data.setWeatherDesc(weather.getString("description"));
                            data.setImgIcon(weather.getString("icon"));
                            data.setTemp(main.getString("temp"));
                            data.setHumidity(main.getString("humidity"));
                            data.setWind(wind.getString("speed"));
                            data.setCityName(response.getString("name"));

                            listener.onResponse(data);
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
