/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.modelos;

import java.util.Date;

/**
 *
 * @author Cristian
 */
public class Pedido {
    private int idPedido;
    private Date fecha;
    private int idCliente;
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(int idPedido, Date fecha, int idCliente) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + '}';
    }
}
