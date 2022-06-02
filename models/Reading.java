package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

@Entity
public class Reading extends Model {
  public int code;
  public float temp;
  public double windSpeed;
  public int windDir;
  public int pressure;
  public String timeStamp;

  public Reading(String timeStamp, int code, float temp, double windSpeed, int windDir, int pressure) {
    this.timeStamp = timeStamp;
    this.code = code;
    this.temp = temp;
    this.windSpeed = windSpeed;
    this.windDir = windDir;
    this.pressure = pressure;
  }
}
