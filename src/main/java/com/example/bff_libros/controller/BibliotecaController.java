package com.example.bff_libros.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bff_libros.service.BibliotecaService;

@RestController
@RequestMapping("/api")
public class BibliotecaController {

    private final BibliotecaService service;

    public BibliotecaController(BibliotecaService service) {
        this.service = service;
    }

    // Usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok(service.getUsuarios());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody String body) {
        return ResponseEntity.ok(service.crearUsuario(body));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminarUsuario(id));
    }

    // Prestamos
    @GetMapping("/prestamos")
    public ResponseEntity<?> getPrestamos() {
        return ResponseEntity.ok(service.getPrestamos());
    }

    @PostMapping("/prestamos")
    public ResponseEntity<?> crearPrestamo(@RequestBody String body) {
        return ResponseEntity.ok(service.crearPrestamo(body));
    }

    @DeleteMapping("/prestamos/{id}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminarPrestamo(id));
    }

    //Libros
    @GetMapping("/libros")
    public ResponseEntity<?> getLibros() {
        return ResponseEntity.ok(service.getLibros());
    }
}