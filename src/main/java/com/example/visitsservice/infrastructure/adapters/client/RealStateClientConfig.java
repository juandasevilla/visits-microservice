package com.example.visitsservice.infrastructure.adapters.client;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

public class RealStateClientConfig {
    @Value("${real-state.service.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-API-KEY", apiKey);
        };
    }
}
