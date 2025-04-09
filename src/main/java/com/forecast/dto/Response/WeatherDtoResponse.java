package com.forecast.dto.Response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDtoResponse {

    private Location location;
    private Current current;
    private Forecast forecast;

}
