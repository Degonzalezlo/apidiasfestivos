package com.diasfestivos.apidiasfestivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "festivo")
public class Festivo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_seleccion")
    @GenericGenerator(name = "secuencia_seleccion", strategy = "increment")
    private int id;

    @Column(name = "nombre",unique = true)
    private String nombre;

    @Column(name = "dia")
    private int dia; 

    @Column(name = "mes")
    private int mes; 

    @Column(name = "diaspascua")
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private Tipo tipo;

    // Constructores
    public Festivo() {
    }

    
    public Festivo(int id,String nombre, int dia, int mes, int diasPascua, Tipo tipo) {
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipo = tipo;
    }

    // Método para obtener la fecha como LocalDate
    public LocalDate getFecha(int anio) {
        return LocalDate.of(anio, mes, dia); // Construye LocalDate usando el año proporcionado
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}