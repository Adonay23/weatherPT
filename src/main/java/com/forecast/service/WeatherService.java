package com.forecast.service;

import com.forecast.dto.Request.WeatherRequestDto;
import com.forecast.dto.Response.Day;
import com.forecast.dto.Response.ForecastDay;
import com.forecast.dto.Response.WeatherDtoResponse;
import com.forecast.dto.ResponseSimple.HourlyForecast;
import com.forecast.dto.ResponseSimple.WeatherForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient weatherApiWebClient;

    Logger logger = Logger.getLogger(WeatherService.class.getName());

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherForecastDto getTomorrowForecast(WeatherRequestDto city) {
        // Obtener datos de la API externa para 2 días (hoy y mañana)
        WeatherDtoResponse weatherResponse = weatherApiWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city.getCity())
                        .queryParam("days", 2)
                        .queryParam("aqi", "no")
                        .queryParam("alerts", "no")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new RuntimeException("Error al consultar WeatherAPI: " + response.statusCode())))
                .bodyToMono(WeatherDtoResponse.class)
                .block();

        return processWeatherResponse(weatherResponse,city.getCity());
    }

    private WeatherForecastDto processWeatherResponse(WeatherDtoResponse weatherResponse,String city) {
        WeatherForecastDto forecastDto = new WeatherForecastDto();
        try {
            String tomorrowDate = LocalDate.now().plusDays(1).toString();
            logger.info("ERROR API WEATHER: "+city);
            ForecastDay tomorrowForecast = weatherResponse.getForecast().getForecastday().stream()
                    .filter(day -> day.getDate().equals(tomorrowDate))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontró pronóstico para mañana"));


            forecastDto.setLocation(weatherResponse.getLocation().getName());
            forecastDto.setDate(tomorrowForecast.getDate());


            Day dayData = tomorrowForecast.getDay();
            forecastDto.setMaxTemp(dayData.getMaxtemp_c());
            forecastDto.setMinTemp(dayData.getMintemp_c());
            forecastDto.setAvgTemp(dayData.getAvgtemp_c());
            forecastDto.setCondition(dayData.getCondition().getText());
            forecastDto.setIcon(dayData.getCondition().getIcon());
            forecastDto.setMaxWind(dayData.getMaxwind_kph());
            forecastDto.setTotalPrecip(dayData.getTotalprecip_mm());
            forecastDto.setAvgHumidity(dayData.getAvghumidity());


            forecastDto.setSunrise(tomorrowForecast.getAstro().getSunrise());
            forecastDto.setSunset(tomorrowForecast.getAstro().getSunset());


            List<HourlyForecast> hourlyForecasts = tomorrowForecast.getHour().stream()
                    .map(hour -> new HourlyForecast(
                            hour.getTime(),
                            hour.getTemp_c(),
                            hour.getCondition().getText(),
                            hour.getWind_kph(),
                            hour.getCondition().getIcon(),
                            hour.getHumidity()
                    ))
                    .collect(Collectors.toList());

            forecastDto.setHourlyForecasts(hourlyForecasts);

            logger.info("CITY: "+city);
        } catch (Exception e) {
            logger.info("NO SE ENCONTRARON DATOS");
            throw new RuntimeException(e);
        }

        return forecastDto;
    }
}


