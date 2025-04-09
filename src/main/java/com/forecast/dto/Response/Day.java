package com.forecast.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Day {
    private double maxtemp_c;
    private double mintemp_c;
    private double avgtemp_c;
    private double maxwind_kph;
    private double totalprecip_mm;
    private int avghumidity;
    private Condition condition;
    private double uv;
}
