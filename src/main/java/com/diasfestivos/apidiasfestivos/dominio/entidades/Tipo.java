package com.diasfestivos.apidiasfestivos.dominio.entidades;


import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_seleccion")
    @GenericGenerator(name = "secuencia_seleccion", strategy = "increment")
    private int id;

    @Column(name = "tipo", unique = true)
    private String tipo;

      @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Festivo> festivos; // Relación inversa con Festivo

    // Constructor por defecto
    public Tipo() {
    }

    // Constructor con parámetros
    public Tipo(int id,String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}


