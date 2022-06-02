package controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Playlist id = " + id);
        Reading latestReading = StationAnalytics.getLatestReading(station.readings);
        Reading previousReading = StationAnalytics.getPreviousReading(station.readings);
        String weatherNow = StationAnalytics.getWeatherNow(latestReading.code);
        double fahrenheit = StationAnalytics.celsiusToFahrenheit(latestReading.temp);
        double minTemp = StationAnalytics.getMinTemp(station.readings);
        double maxTemp = StationAnalytics.getMaxTemp(station.readings);
        String tempTrend = StationAnalytics.getTempTrend(latestReading.temp, previousReading.temp);
        int beaufortLevel = StationAnalytics.getBeaufortLevel(latestReading.windSpeed);
        String beaufortScale = StationAnalytics.getBeaufortScale(beaufortLevel);
        double windChill = StationAnalytics.getWindChill(latestReading.temp, latestReading.windSpeed);
        String windCompass = StationAnalytics.getWindCompass(latestReading.windDir);
        double minWindSpeed = StationAnalytics.getMinWindSpeed(station.readings);
        double maxWindSpeed = StationAnalytics.getMaxWindSpeed(station.readings);
        String windTrend = StationAnalytics.getWindTrend(latestReading.windSpeed, previousReading.windSpeed);
        int minPressure = StationAnalytics.getMinPressure(station.readings);
        int maxPressure = StationAnalytics.getMaxPressure(station.readings);
        String pressureTrend = StationAnalytics.getPressureTrend(latestReading.pressure, previousReading.pressure);
        render("station.html", station, latestReading,previousReading,weatherNow, fahrenheit,
                minTemp, maxTemp, tempTrend, beaufortLevel, beaufortScale, windChill,windCompass,
                minWindSpeed, maxWindSpeed, windTrend, minPressure, maxPressure, pressureTrend);
    }
    public static void deleteReading (Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing " + station.name+reading.id);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect ("/stations/" + id);
    }
    public static void addReading(Long id, int code, float temp, double windSpeed,int windDir,int pressure)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Reading reading = new Reading( timeStamp,code,temp, windSpeed,windDir,pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect ("/stations/" + id);
    }
}