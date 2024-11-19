package com.diasfestivos.apidiasfestivos.core.interfaces.servicios;

import java.util.List;

import com.diasfestivos.apidiasfestivos.dominio.entidades.Tipo;

public interface ITipoServicio {

    List<Tipo> listar();

    Tipo obtener(int id); 

    Tipo agregar(Tipo tipo);

    Tipo modificar(Tipo tipo); 

    void eliminar(int id); 
}