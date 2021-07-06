package com.yogeeswar.weatherforecast;

public class WeatherData {
    private String cityName;
    private String lon;
    private String lat;
    private String weatherDesc;
    private String imgIcon;
    private String temp;
    private String humidity;
    private String wind;

    public WeatherData(String cityName, String lon, String lat, String weatherDesc, String imgIcon, String temp, String humidity, String wind) {
        this.cityName = cityName;
        this.lon = lon;
        this.lat = lat;
        this.weatherDesc = weatherDesc;
        this.imgIcon = imgIcon;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
    }

    public WeatherData() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
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
        return "name: "+cityName+"lat: "+lat+"lon: "+lon+"temp: "+temp;
    }
}
