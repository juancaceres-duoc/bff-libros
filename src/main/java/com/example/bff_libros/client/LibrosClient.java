package com.example.bff_libros.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "librosClient", url = "${azure.functions.prestamos-url}")
public interface LibrosClient {

    @GetMapping("/libros")
    List<Map<String, Object>> getLibros();
}