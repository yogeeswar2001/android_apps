package com.yogeeswar.weatherforecast;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {

    private final ArrayList<WeatherData> data;

    public WeatherForecastAdapter(ArrayList<WeatherData> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        public ViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    @Override
    public WeatherForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        CardView cardview = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weatherdata,parent, false);
        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardview = holder.cv;

        TextView desc = (TextView)cardview.findViewById(R.id.weatherDesc);
        TextView tempTxt = (TextView)cardview.findViewById(R.id.temp);
        TextView windTxt = (TextView)cardview.findViewById(R.id.wind);
        TextView humidTxt = (TextView)cardview.findViewById(R.id.humidity);
        TextView dtTxt = (TextView)cardview.findViewById(R.id.dt);
        ImageView icon = (ImageView)cardview.findViewById(R.id.icon);

        desc.setText(data.get(position).getWeatherDesc());
        tempTxt.setText(data.get(position).getTemp());
        windTxt.setText(data.get(position).getWind());
        humidTxt.setText(data.get(position).getHumidity());
        dtTxt.setText(data.get(position).getDate());

        String img_url = "http://openweathermap.org/img/wn/"+data.get(position).getImgIcon()+"@2x.png";
        Picasso.get().load(img_url).centerCrop().fit().into(icon);
    }
}
