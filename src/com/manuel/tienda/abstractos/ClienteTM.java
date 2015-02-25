/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.manuel.tienda.abstractos;

import com.manuel.tienda.modelos.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class ClienteTM extends AbstractTableModel{
    private ArrayList<Cliente> clientes;
    private String[] cabecera = {"idCliente", "dpi", "nombre"};

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public ClienteTM(ArrayList<Cliente> clientes){
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return cabecera.length;
    }
    
    @Override
    public String getColumnName(int column){
        return cabecera[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch(columnIndex){
            case 0 : return cliente.getIdCliente();
            case 1 : return cliente.getDpi();
            case 2 : return cliente.getNombre();
            default: return null;
        }
    }
    
}
