package com.forecast.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${weather.api.base-url}")
    private String weatherApiBaseUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Bean
    public WebClient weatherApiWebClient() {
        return WebClient.builder()
                .baseUrl(weatherApiBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
