package com.example.bff_libros.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "prestamosClient", url = "${azure.functions.prestamos-url}")
public interface PrestamoClient {

    @GetMapping("/prestamos")
    List<Map<String, Object>> getPrestamos();

    @PostMapping("/prestamos")
    String crearPrestamo(@RequestBody String body);

    @DeleteMapping("/prestamos")
    String eliminarPrestamo(@RequestParam("id") Long id);
}
