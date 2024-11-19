package com.diasfestivos.apidiasfestivos.presentacion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diasfestivos.apidiasfestivos.core.interfaces.servicios.ITipoServicio;
import com.diasfestivos.apidiasfestivos.dominio.entidades.Tipo;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TipoControlador {

    private final ITipoServicio servicio;

    public TipoControlador(ITipoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Tipo>> listar() {
        List<Tipo> tipos = servicio.listar();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> obtener(@PathVariable int id) {
        try {
            Tipo tipo = servicio.obtener(id); // Lanza excepción si no se encuentra
            return ResponseEntity.ok(tipo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<Tipo> agregar(@RequestBody Tipo tipo) {
        Tipo nuevoTipo = servicio.agregar(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> modificar(@PathVariable int id, @RequestBody Tipo tipo) {
        tipo.setId(id); // Establecer el ID en el objeto recibido
        Tipo tipoModificado = servicio.modificar(tipo);
        return ResponseEntity.ok(tipoModificado); // No es necesario verificar aquí, ya que se lanza una excepción si no se encuentra
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            servicio.eliminar(id); // Si no se encuentra, lanza una excepción
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}