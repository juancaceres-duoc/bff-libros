package com.example.bff_libros.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.bff_libros.client.LibrosClient;
import com.example.bff_libros.client.PrestamoClient;
import com.example.bff_libros.client.UsuarioClient;

@Service
public class BibliotecaService {

    private final UsuarioClient usuarioClient;
    private final PrestamoClient prestamoClient;
    private final LibrosClient librosClient;

    public BibliotecaService(UsuarioClient usuarioClient, PrestamoClient prestamoClient, LibrosClient librosClient) {
        this.usuarioClient = usuarioClient;
        this.prestamoClient = prestamoClient;
        this.librosClient = librosClient;        
    }

    // Usuarios
    public List<Map<String, Object>> getUsuarios() {
        return usuarioClient.getUsuarios();
    }

    public String crearUsuario(String body) {
        return usuarioClient.crearUsuario(body);
    }

     public String eliminarUsuario(Long id) {
        return usuarioClient.eliminarUsuario(id);
    }

    // Prestamos
    public List<Map<String, Object>> getPrestamos() {
        return prestamoClient.getPrestamos();
    }

    public String crearPrestamo(String body) {
        return prestamoClient.crearPrestamo(body);
    }

    public String eliminarPrestamo(Long id) {
        return prestamoClient.eliminarPrestamo(id);
    }

    public List<Map<String, Object>> getLibros() {
        return librosClient.getLibros();
    }
}
