/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.abstractos;

import com.manuel.tienda.controladores.ArticuloController;
import com.manuel.tienda.modelos.DetallePedido;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class DetalleTM extends AbstractTableModel {

    private ArrayList<DetallePedido> detalles;
    private String[] cabecera = {"Articulo", "nombre", "Cantidad", "Precio", "Total"};

    public ArrayList<DetallePedido> getDetallePedidos() {
        return detalles;
    }

    public void setDetallePedidos(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }
    
    public DetalleTM(ArrayList<DetallePedido> detalles){
        this.detalles = detalles;
    }

    @Override
    public int getRowCount() {
        return detalles.size();
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
        DetallePedido detalle = detalles.get(rowIndex);
        switch(columnIndex){
            case 0 : return detalle.getIdArticulo();
            case 1 : {
                try {
                    return ArticuloController.buscarArticulos(detalle.getIdArticulo()).getNombre();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(DetalleTM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 2 : return detalle.getCantidad();
            case 3 : return detalle.getPrecio();
            case 4 : return detalle.getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
            default: return null;
        }
    }
    
}
