package com.example.bff_libros.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // ─── Usuarios ─────────────────────────────────────────────────────────────

    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios() {
        return ResponseEntity.ok(service.getUsuarios());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody String body) {
        return ResponseEntity.ok(service.crearUsuario(body));
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody String body) {
        return ResponseEntity.ok(service.actualizarUsuario(id, body));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminarUsuario(id));
    }

    // ─── Prestamos ────────────────────────────────────────────────────────────

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

    // ─── Libros ───────────────────────────────────────────────────────────────

    @GetMapping("/libros")
    public ResponseEntity<?> getLibros() {
        return ResponseEntity.ok(service.getLibros());
    }

    @GetMapping("/libros/disponibles")
    public ResponseEntity<?> getLibrosDisponibles() {
        return ResponseEntity.ok(service.getLibrosDisponibles());
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<?> getLibroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLibroPorId(id));
    }

    @PostMapping("/libros")
    public ResponseEntity<?> agregarLibro(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.agregarLibro(body.get("titulo"), body.get("autor")));
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.actualizarLibro(id, body.get("titulo"), body.get("autor")));
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id) {
        return ResponseEntity.ok(service.eliminarLibro(id));
    }

    // ─── Reportes ─────────────────────────────────────────────────────────────

    @GetMapping("/reportes/estadisticas")
    public ResponseEntity<?> getEstadisticas() {
        return ResponseEntity.ok(service.getEstadisticas());
    }

    @GetMapping("/reportes/prestamos")
    public ResponseEntity<?> getPrestamosActivos() {
        return ResponseEntity.ok(service.getPrestamosActivos());
    }

    @GetMapping("/reportes/libros-populares")
    public ResponseEntity<?> getLibrosMasPrestados() {
        return ResponseEntity.ok(service.getLibrosMasPrestados());
    }

    @GetMapping("/reportes/usuario/{id}/prestamos")
    public ResponseEntity<?> getPrestamosDeUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPrestamosDeUsuario(id));
    }
}
