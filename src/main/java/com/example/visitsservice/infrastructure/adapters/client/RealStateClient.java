package com.example.visitsservice.infrastructure.adapters.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "real-state-service", url = "${real-state.service.url}",
                configuration = RealStateClientConfig.class)
public interface RealStateClient {
    @GetMapping("/api/v1/real-state/exists-real-state/{id}")
    boolean existsRealState(@PathVariable("id") Long id);
}
