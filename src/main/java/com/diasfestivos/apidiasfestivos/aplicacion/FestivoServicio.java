package com.diasfestivos.apidiasfestivos.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diasfestivos.apidiasfestivos.core.interfaces.repositorios.IFestivoRepositorio;
import com.diasfestivos.apidiasfestivos.core.interfaces.servicios.IFestivoServicio;
import com.diasfestivos.apidiasfestivos.dominio.entidades.Festivo;

import jakarta.persistence.EntityNotFoundException;

import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    private IFestivoRepositorio repositorio;

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @Override
    public Festivo obtener(int id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        return repositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        if (!repositorio.existsById(festivo.getId())) {
            throw new EntityNotFoundException("Festivo no encontrado con ID: " + festivo.getId());
        }
        return repositorio.save(festivo);
    }

    @Override
    public boolean eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new EntityNotFoundException("Festivo no encontrado con ID: " + id);
        }
        repositorio.deleteById(id);
        return true;
    }
@Override
public boolean esFestivo(LocalDate fecha) {
    // Primero, verifica si la fecha está en el repositorio de días festivos
    if (!repositorio.findByDiaAndMes(fecha.getDayOfMonth(), fecha.getMonthValue()).isEmpty()) {
        return true; // Es un día festivo fijo
    }

    // Calcular el inicio de Semana Santa para el año de la fecha
    int año = fecha.getYear();
    Date inicioSemanaSanta = getInicioSemanaSanta(año);
    Date domingoDePascua = agregarDias(inicioSemanaSanta, 7); // Domingo de Pascua

    // Calcular Jueves Santo y Viernes Santo
    @SuppressWarnings("deprecation")
    LocalDate juevesSanto = LocalDate.of(año, domingoDePascua.getMonth() + 1, domingoDePascua.getDate()).minusDays(3);
    @SuppressWarnings("deprecation")
    LocalDate viernesSanto = LocalDate.of(año, domingoDePascua.getMonth() + 1, domingoDePascua.getDate()).minusDays(2);

    // Verificar si la fecha es Jueves Santo o Viernes Santo
    if (fecha.equals(juevesSanto) || fecha.equals(viernesSanto)) {
        return true; // Es Jueves o Viernes Santo
    }

    return false; // No es festiva
}

// Método para calcular el inicio de Semana Santa
@SuppressWarnings("deprecation")
public static Date getInicioSemanaSanta(int año) {
    int a = año % 19;
    int b = año % 4;
    int c = año % 7;
    int d = (19 * a + 24) % 30;

    int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

    int dia = 15 + dias;
    int mes = 3; // Marzo
    if (dia > 31) {
        dia = dia - 31;
        mes = 4; // Abril
    }
    return new Date(año - 1900, mes - 1, dia); 
}

// Método para agregar días a una fecha
public static Date agregarDias(Date fecha, int dias) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(Calendar.DATE, dias);
    return calendar.getTime();
}

}


    
  


