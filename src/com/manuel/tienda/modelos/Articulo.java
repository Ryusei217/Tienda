/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.manuel.tienda.modelos;

import java.math.BigDecimal;

/**
 *
 * @author Escobar
 */
public class Articulo {
    private int IdArticulo;
    private String numSerie;
    private String nombre;
    private BigDecimal precio; 
    
    public Articulo(){
    
    }
    
    public Articulo(int IdArticulo, String numSerie, String nombre, BigDecimal precio){
        this.IdArticulo=IdArticulo;
        this.numSerie=numSerie;
        this.nombre=nombre;
        this.precio=precio;
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int IdArticulo) {
        this.IdArticulo = IdArticulo;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "#" + numSerie + " - " + nombre;
    }
}
