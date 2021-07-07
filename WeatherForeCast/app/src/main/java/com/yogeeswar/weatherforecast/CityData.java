package com.yogeeswar.weatherforecast;

public class CityData {
    private String cityName;
    private String lon;
    private String lat;

    public CityData(String cityName, String lon, String lat) {
        this.cityName = cityName;
        this.lon = lon;
        this.lat = lat;
    }

    public CityData() {
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

    public String toString() {
        return "city name: "+cityName+"lat: "+lat+"lon: "+lon;
    }
}