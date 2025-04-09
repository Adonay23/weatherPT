package com.forecast.dto.ResponseSimple;

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
}

