package com.example.bff_libros.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuariosClient", url = "${azure.functions.usuarios-url}")
public interface UsuarioClient {

    @GetMapping("/usuarios")
    List<Map<String, Object>> getUsuarios();

    @PostMapping("/usuarios")
    String crearUsuario(@RequestBody String body);

    @PutMapping("/usuarios")
    String actualizarUsuario(@RequestParam("id") Long id, @RequestBody String body);

    @DeleteMapping("/usuarios")
    String eliminarUsuario(@RequestParam("id") Long id);
}
