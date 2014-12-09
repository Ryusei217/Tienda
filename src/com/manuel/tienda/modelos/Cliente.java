/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.manuel.tienda.modelos;

/**
 *
 * @author Escobar
 */
public class Cliente {
    
    private int idCliente;
    private String dpi;
    private String nombre;
    
    public Cliente(){
    
}
    public Cliente(int idCliente, String dpi, String nombre){
    this.idCliente=idCliente;
    this.dpi=dpi;
    this.nombre=nombre;
}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
