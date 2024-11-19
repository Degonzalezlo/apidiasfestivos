package com.diasfestivos.apidiasfestivos.core.interfaces.servicios;

import java.time.LocalDate;
import java.util.List;
import com.diasfestivos.apidiasfestivos.dominio.entidades.Festivo;

public interface IFestivoServicio {
    
    List<Festivo> listar();

    Festivo obtener(int id);

    List<Festivo> buscar(String nombre);

    Festivo agregar(Festivo festivo);

    Festivo modificar(Festivo festivo);

    boolean eliminar(int id);
       
    boolean esFestivo(LocalDate fecha);
}
