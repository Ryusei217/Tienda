/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.modelos;

import java.math.BigDecimal;

/**
 *
 * @author Cristian
 */
public class DetallePedido {
    private int idArticulo;
    private int idPedido;
    private int cantidad;
    private BigDecimal precio; 

    public DetallePedido() {
    }

    public DetallePedido(int idArticulo, int idPedido, int cantidad, BigDecimal precio) {
        this.idArticulo = idArticulo;
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "DetallePedido{" + "idArticulo=" + idArticulo + ", idPedido=" + idPedido + ", cantidad=" + cantidad + '}';
    }
    
    
}
