package com.example.bff_libros.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "reportesClient", url = "${azure.functions.reportes-url}")
public interface ReportesClient {

    @PostMapping("/reportes/graphql")
    Map<String, Object> graphql(@RequestBody Map<String, Object> body);
}
