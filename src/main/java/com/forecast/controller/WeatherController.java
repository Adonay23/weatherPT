package com.forecast.controller;

import com.forecast.dto.Request.WeatherRequestDto;
import com.forecast.dto.ResponseSimple.WeatherForecastDto;
import com.forecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping("/forecast")
    public ResponseEntity<WeatherForecastDto> getTomorrowForecast(
            @RequestBody WeatherRequestDto city) {

        WeatherForecastDto forecast = weatherService.getTomorrowForecast(city);
        return ResponseEntity.ok(forecast);
    }

}
