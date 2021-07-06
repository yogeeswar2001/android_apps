package com.yogeeswar.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        dataInstance = new WeatherDataService(MainActivity.this);
    }

    public void onClickSearchBtn(View view) {
        search_bar = (EditText) findViewById(R.id.search_bar);
        final String cityName = search_bar.getText().toString();

        dataInstance.getDataByCityName(cityName, new WeatherDataService.ResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(WeatherData data) {
                String msg = data.toString();
                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
                setImage(data.getImgIcon(), (ImageView) findViewById(R.id.image));
            }
        });
    }

    public void onCLickSearchByCoord(View view) {
        EditText lat,lon;
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.lon);
        dataInstance.getDataByCoord(lon.getText().toString(), lat.getText().toString(), new WeatherDataService.ResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(WeatherData data) {
                String msg = data.toString();
                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();
                setImage(data.getImgIcon(), (ImageView) findViewById(R.id.image));
            }
        });
    }
    private void setImage(String icon, ImageView view) {
        String img_url = "http://openweathermap.org/img/wn/"+icon+"@2x.png";
        Picasso.get().load(img_url).resize(300,300).into(view);
    }
}