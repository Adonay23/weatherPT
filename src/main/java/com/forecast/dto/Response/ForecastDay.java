package com.forecast.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastDay {
    private String date;
    private long date_epoch;
    private Day day;
    private Astro astro;
    private List<Hour> hour;
}
