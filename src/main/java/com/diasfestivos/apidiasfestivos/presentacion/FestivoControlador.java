package com.diasfestivos.apidiasfestivos.presentacion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diasfestivos.apidiasfestivos.core.interfaces.servicios.IFestivoServicio;
import com.diasfestivos.apidiasfestivos.dominio.entidades.Festivo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoServicio servicio;

    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Festivo>> listar() {
        List<Festivo> festivos = servicio.listar();
        return ResponseEntity.ok(festivos);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Festivo>> buscarPorNombre(@PathVariable String nombre) {
        List<Festivo> festivos = servicio.buscar(nombre);
        return festivos.isEmpty() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.ok(festivos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Festivo> obtener(@PathVariable int id) {
        Festivo festivo = servicio.obtener(id);
        return festivo != null 
            ? ResponseEntity.ok(festivo) 
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Festivo> agregar(@RequestBody Festivo festivo) {
        Festivo nuevoFestivo = servicio.agregar(festivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoFestivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Festivo> modificar(@PathVariable int id, @RequestBody Festivo festivo) {
        festivo.setId(id); // Establece el ID en el objeto recibido
        Festivo festivoModificado = servicio.modificar(festivo);
        return festivoModificado != null 
            ? ResponseEntity.ok(festivoModificado) 
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean eliminado = servicio.eliminar(id);
        return eliminado 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.notFound().build();
    }
    
    
    @GetMapping("/validar/{fecha}")
public ResponseEntity<String> validarFecha(@PathVariable String fecha) {
    try {
        LocalDate fechaLocal = LocalDate.parse(fecha);
        // Verificar si es festiva
        boolean esFestivo = servicio.esFestivo(fechaLocal);
        
        return ResponseEntity.ok(esFestivo ? "Es un día festivo" : "No es un día festivo");
        
    } catch (DateTimeParseException e) {
        // Manejar el caso de fecha no válida
        return ResponseEntity.badRequest().body("Fecha no válida: " + fecha);
    }
}

    
    }

    


