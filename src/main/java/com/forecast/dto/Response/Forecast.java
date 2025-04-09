package com.forecast.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {

    private List<ForecastDay> forecastday;
}
