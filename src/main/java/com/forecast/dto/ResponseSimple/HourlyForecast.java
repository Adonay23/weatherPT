package com.forecast.dto.ResponseSimple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyForecast {
    private String time;
    private double temp;
    private String condition;
    private String icon;
    private double wind;
    private int humidity;


    public HourlyForecast(String time, double temp, String condition, double wind, String icon, int humidity) {
        this.time = time;
        this.temp = temp;
        this.condition = condition;
        this.wind = wind;
        this.icon = icon;
        this.humidity = humidity;
    }
}

