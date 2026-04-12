package com.example.bff_libros.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.bff_libros.client.LibrosClient;
import com.example.bff_libros.client.PrestamoClient;
import com.example.bff_libros.client.ReportesClient;
import com.example.bff_libros.client.UsuarioClient;

@Service
public class BibliotecaService {

    private final UsuarioClient usuarioClient;
    private final PrestamoClient prestamoClient;
    private final LibrosClient librosClient;
    private final ReportesClient reportesClient;

    public BibliotecaService(UsuarioClient usuarioClient, PrestamoClient prestamoClient,
                             LibrosClient librosClient, ReportesClient reportesClient) {
        this.usuarioClient = usuarioClient;
        this.prestamoClient = prestamoClient;
        this.librosClient = librosClient;
        this.reportesClient = reportesClient;
    }

    // ─── Usuarios ─────────────────────────────────────────────────────────────

    public List<Map<String, Object>> getUsuarios() {
        return usuarioClient.getUsuarios();
    }

    public String crearUsuario(String body) {
        return usuarioClient.crearUsuario(body);
    }

    public String actualizarUsuario(Long id, String body) {
        return usuarioClient.actualizarUsuario(id, body);
    }

    public String eliminarUsuario(Long id) {
        return usuarioClient.eliminarUsuario(id);
    }

    // ─── Prestamos ────────────────────────────────────────────────────────────

    public List<Map<String, Object>> getPrestamos() {
        return prestamoClient.getPrestamos();
    }

    public String crearPrestamo(String body) {
        return prestamoClient.crearPrestamo(body);
    }

    public String eliminarPrestamo(Long id) {
        return prestamoClient.eliminarPrestamo(id);
    }

    // ─── Libros (GraphQL) ─────────────────────────────────────────────────────

    public List<Map<String, Object>> getLibros() {
        String query = "{ libros { idLibro titulo autor disponible } }";
        Map<String, Object> response = librosClient.graphql(buildQuery(query));
        return extractList(response, "libros");
    }

    public Map<String, Object> agregarLibro(String titulo, String autor) {
        String query = """
                mutation AgregarLibro($titulo: String!, $autor: String) {
                    agregarLibro(titulo: $titulo, autor: $autor) {
                        idLibro titulo autor disponible
                    }
                }""";
        Map<String, Object> variables = new HashMap<>();
        variables.put("titulo", titulo);
        variables.put("autor", autor);
        Map<String, Object> response = librosClient.graphql(buildQuery(query, variables));
        return extractObject(response, "agregarLibro");
    }

    public Map<String, Object> actualizarLibro(Long id, String titulo, String autor) {
        String query = """
                mutation ActualizarLibro($id: Int!, $titulo: String, $autor: String) {
                    actualizarLibro(id: $id, titulo: $titulo, autor: $autor) {
                        idLibro titulo autor disponible
                    }
                }""";
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id.intValue());
        variables.put("titulo", titulo);
        variables.put("autor", autor);
        Map<String, Object> response = librosClient.graphql(buildQuery(query, variables));
        return extractObject(response, "actualizarLibro");
    }

    public String eliminarLibro(Long id) {
        String query = """
                mutation EliminarLibro($id: Int!) {
                    eliminarLibro(id: $id)
                }""";
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id.intValue());
        Map<String, Object> response = librosClient.graphql(buildQuery(query, variables));
        return extractObject(response, "eliminarLibro").toString();
    }

    // ─── Reportes (GraphQL) ───────────────────────────────────────────────────

    public Map<String, Object> getEstadisticas() {
        String query = """
                { estadisticas {
                    totalLibros librosDisponibles librosPrestados
                    totalUsuarios prestamosActivos
                } }""";
        Map<String, Object> response = reportesClient.graphql(buildQuery(query));
        return extractObject(response, "estadisticas");
    }

    public List<Map<String, Object>> getPrestamosActivos() {
        String query = """
                { prestamosActivos {
                    idPrestamo usuario email libro autor fecha
                } }""";
        Map<String, Object> response = reportesClient.graphql(buildQuery(query));
        return extractList(response, "prestamosActivos");
    }

    public List<Map<String, Object>> getLibrosMasPrestados() {
        String query = "{ librosMasPrestados { titulo autor vecesPrestado } }";
        Map<String, Object> response = reportesClient.graphql(buildQuery(query));
        return extractList(response, "librosMasPrestados");
    }

    public List<Map<String, Object>> getPrestamosDeUsuario(Long idUsuario) {
        String query = """
                query PrestamosUsuario($idUsuario: Int!) {
                    prestamosDeUsuario(idUsuario: $idUsuario) {
                        idPrestamo usuario email libro autor fecha
                    }
                }""";
        Map<String, Object> variables = new HashMap<>();
        variables.put("idUsuario", idUsuario.intValue());
        Map<String, Object> response = reportesClient.graphql(buildQuery(query, variables));
        return extractList(response, "prestamosDeUsuario");
    }

    // ─── Helpers GraphQL ──────────────────────────────────────────────────────

    private Map<String, Object> buildQuery(String query) {
        return buildQuery(query, null);
    }

    private Map<String, Object> buildQuery(String query, Map<String, Object> variables) {
        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        if (variables != null) {
            body.put("variables", variables);
        }
        return body;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> extractList(Map<String, Object> response, String key) {
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return (List<Map<String, Object>>) data.get(key);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> extractObject(Map<String, Object> response, String key) {
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return (Map<String, Object>) data.get(key);
    }
}
