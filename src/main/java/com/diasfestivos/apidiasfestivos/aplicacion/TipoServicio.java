package com.diasfestivos.apidiasfestivos.aplicacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diasfestivos.apidiasfestivos.core.interfaces.repositorios.ITipoRepositorio;
import com.diasfestivos.apidiasfestivos.core.interfaces.servicios.ITipoServicio;
import com.diasfestivos.apidiasfestivos.dominio.entidades.Tipo;

import jakarta.persistence.EntityNotFoundException;


@Service
public class TipoServicio implements ITipoServicio {

    private final ITipoRepositorio repositorio;

    public TipoServicio(ITipoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> listar() {
        return repositorio.findAll(); 
    }

    @Override
    public Tipo obtener(int id) {
    return repositorio.findById(id).orElseThrow(() -> 
        new EntityNotFoundException("Tipo no encontrado con ID: " + id));
}


    @Override
    public Tipo agregar(Tipo tipo) {
        return repositorio.save(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (!repositorio.existsById(tipo.getId())) {
            throw new EntityNotFoundException("Tipo no encontrado con ID: " + tipo.getId());
        }
        return repositorio.save(tipo);
    }

    @Override
    public void eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new EntityNotFoundException("Tipo no encontrado con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}
