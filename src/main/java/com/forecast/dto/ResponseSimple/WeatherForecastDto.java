package com.forecast.dto.ResponseSimple;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherForecastDto {

    private String location;
    private String date;
    private double maxTemp;
    private double minTemp;
    private double avgTemp;
    private String condition;
    private String icon;
    private double maxWind;
    private double totalPrecip;
    private int avgHumidity;
    private String sunrise;
    private String sunset;
    private List<HourlyForecast> hourlyForecasts;



}
