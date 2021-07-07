package com.yogeeswar.weatherforecast;

public class WeatherData {
    private String weatherDesc;
    private String imgIcon;
    private String temp;
    private String humidity;
    private String wind;

    private String date;

    public WeatherData(String weatherDesc, String imgIcon, String temp, String humidity, String wind, String date) {
        this.weatherDesc = weatherDesc;
        this.imgIcon = imgIcon;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.date = date;
    }

    public WeatherData() {
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String toString(){
        return "temp: "+temp+"humidity: "+humidity+"wind: "+wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
