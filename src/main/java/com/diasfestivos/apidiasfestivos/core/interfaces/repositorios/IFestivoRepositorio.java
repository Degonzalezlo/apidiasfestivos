package com.diasfestivos.apidiasfestivos.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diasfestivos.apidiasfestivos.dominio.entidades.Festivo;

import java.util.List;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {

    @Query("SELECT f FROM Festivo f WHERE f.nombre LIKE %?1%")
List<Festivo> buscarPorNombre(String nombre);


// Método para buscar por día y mes
    @Query("SELECT f FROM Festivo f WHERE f.dia = ?1 AND f.mes = ?2")
    List<Festivo> findByDiaAndMes(int dia, int mes);

}
