package com.diasfestivos.apidiasfestivos.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diasfestivos.apidiasfestivos.dominio.entidades.Tipo;

import java.util.List;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {

    @Query("SELECT t FROM Tipo t WHERE t.tipo LIKE '%' || ?1 || '%'")
    List<Tipo> buscarPorNombre(String tipo);

    List<Tipo> findByTipoContaining(String tipo);
}


