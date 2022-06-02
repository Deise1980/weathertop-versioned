package utils;
import models.Reading;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static controllers.StationCtrl.addReading;

public class StationAnalytics {

  public static Reading getLatestReading(List<Reading> readings) {
    Reading latestReading = null;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.id > latestReading.id) {
          latestReading = reading;
        }
      }
    } else {
      latestReading = new Reading("", 100, 1, 1, 1, 1);
    }
    return latestReading;
  }

  public static Reading getPreviousReading(List<Reading> readings) {
    Reading latestReading = null;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.id == latestReading.id) {
          latestReading = reading;
        }
      }
    }
    return latestReading;
  }

  public static String getWeatherNow(Integer latestCode) {
    HashMap<Integer, String> wCodes = new HashMap<Integer, String>();
    wCodes.put(100, "Clear");
    wCodes.put(200, "Clouds");
    wCodes.put(300, "Cloudy");
    wCodes.put(400, "Light Showers");
    wCodes.put(500, "Heavy Showers");
    wCodes.put(600, "Rain");
    wCodes.put(700, "Snow");
    wCodes.put(800, "Thunder");
    String weatherNow = wCodes.get(latestCode);
    return weatherNow;
  }

  public static double celsiusToFahrenheit(double latestTemp) {
    double celsius = latestTemp;
    double fahrenheit = (celsius * 9 / 5) + 32;
    return fahrenheit;
  }

  public static double getMinTemp(List<Reading> readings) {
    Reading latestReading = null;
    double minTemp = readings.get(0).temp;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temp < latestReading.temp) {
          latestReading = reading;
          minTemp = latestReading.temp;
        }
      }
    }
    return minTemp;
  }

  public static double getMaxTemp(List<Reading> readings) {
    Reading latestReading = null;
    double maxTemp = readings.get(0).temp;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temp > latestReading.temp) {
          latestReading = reading;
          maxTemp = latestReading.temp;
        }
      }
    }
    return maxTemp;
  }

  public static String getTempTrend(double latestTemp, double previousTemp) {
    String tempTrend = "Steady";
    double tempNew = latestTemp;
    double tempBefore = previousTemp;
    if (tempNew > tempBefore) {
      tempTrend = "Rising";
    } else if (tempNew < tempBefore) {
      tempTrend = "Falling";
    }
    return tempTrend;
  }

  public static int getBeaufortLevel(Double windSpeed) {
    TreeMap<Double, Integer> bCodes = new TreeMap<Double, Integer>();
    bCodes.put(1.0, 0);
    bCodes.put(5.0, 1);
    bCodes.put(11.0, 2);
    bCodes.put(19.0, 3);
    bCodes.put(28.0, 4);
    bCodes.put(38.0, 5);
    bCodes.put(49.0, 6);
    bCodes.put(61.0, 7);
    bCodes.put(74.0, 8);
    bCodes.put(88.0, 9);
    bCodes.put(102.0, 10);
    bCodes.put(217.0, 11);
    Map.Entry<Double, Integer> m = bCodes.ceilingEntry(windSpeed);
    int beaufortLevel = m.getValue();
    return beaufortLevel;
  }

  public static String getBeaufortScale(int beaufortLevel) {
    String beaufortScale = "";
    HashMap<Integer, String> bScale = new HashMap<Integer, String>();
    bScale.put(0, "calm");
    bScale.put(1, "Light Air");
    bScale.put(2, "Light Breeze");
    bScale.put(3, "Gentle Breeze");
    bScale.put(4, "Moderate Breeze");
    bScale.put(5, "Fresh Breeze");
    bScale.put(6, "Strong Breeze");
    bScale.put(7, "Near Gale");
    bScale.put(8, "Gale");
    bScale.put(9, "Severe Gale");
    bScale.put(10, "Strong Storm");
    bScale.put(11, "Violent Storm");
    beaufortScale = bScale.get(beaufortLevel);
    return beaufortScale;
  }

  public static double getWindChill(float temp, double velocity) {
    double windChilla =
        13.12 + (0.6215 * temp) -
            (11.37 * (Math.pow(velocity, 0.16))) + (0.3965 * temp * (Math.pow(velocity, 0.16)));
    double windChill = Math.round(windChilla);
    return windChill;
  }

  public static String getWindCompass(int windDir) {
    TreeMap<Double, String> cCodes = new TreeMap<Double, String>();
    cCodes.put(11.25, "N");
    cCodes.put(33.75, "NNE");
    cCodes.put(56.25, "NE");
    cCodes.put(78.75, "ENE");
    cCodes.put(101.25, "E");
    cCodes.put(123.75, "ESE");
    cCodes.put(146.25, "SE");
    cCodes.put(168.75, "SSE");
    cCodes.put(191.25, "S");
    cCodes.put(213.75, "SSW");
    cCodes.put(236.25, "SW");
    cCodes.put(258.75, "WSW");
    cCodes.put(281.25, "W");
    cCodes.put(303.75, "WNW");
    cCodes.put(326.25, "NW");
    cCodes.put(348.55, "NNW");
    cCodes.put(360.00, "N");
    Map.Entry<Double, String> m = cCodes.ceilingEntry(Double.valueOf(windDir));
    String windCompass = m.getValue();
    return windCompass;
  }

  public static double getMinWindSpeed(List<Reading> readings) {
    Reading latestReading = null;
    double minWindSpeed = readings.get(0).windSpeed;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed < latestReading.windSpeed) {
          latestReading = reading;
          minWindSpeed = latestReading.windSpeed;
        }
      }
    }
    return minWindSpeed;
  }

  public static double getMaxWindSpeed(List<Reading> readings) {
    Reading latestReading = null;
    double maxWindSpeed = readings.get(0).windSpeed;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed > latestReading.windSpeed) {
          latestReading = reading;
          maxWindSpeed = latestReading.windSpeed;
        }
      }
    }
    return maxWindSpeed;
  }

  public static String getWindTrend(double latestWindSpeed, double previousWindSpeed) {
    String windTrend = "Steady";
    double windNew = latestWindSpeed;
    double windBefore = previousWindSpeed;
    if (windNew > windBefore) {
      windTrend = "Rising";
    } else if (windNew < windBefore) {
      windTrend = "Falling";
    }
    return windTrend;
  }

  public static int getMinPressure(List<Reading> readings) {
    Reading latestReading = null;
    int minPressure = readings.get(0).pressure;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure < latestReading.pressure) {
          latestReading = reading;
          minPressure = latestReading.pressure;
        }
      }
    }
    return minPressure;
  }

  public static int getMaxPressure(List<Reading> readings) {
    Reading latestReading = null;
    int maxPressure = readings.get(0).pressure;
    if (readings.size() > 0) {
      latestReading = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure > latestReading.pressure) {
          latestReading = reading;
          maxPressure = latestReading.pressure;
        }
      }
    }
    return maxPressure;
  }

  public static String getPressureTrend(int latestPressure, int previousPressure) {
    String pressureTrend = "Steady";
    int pressureNew = latestPressure;
    int pressureBefore = previousPressure;
    if (pressureNew > pressureBefore) {
      pressureTrend = "Rising";
    } else if (pressureNew < pressureBefore) {
      pressureTrend = "Falling";
    }
    return pressureTrend;
  }

}
