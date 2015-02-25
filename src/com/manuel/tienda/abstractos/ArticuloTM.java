/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.manuel.tienda.abstractos;

import com.manuel.tienda.modelos.Articulo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Escobar
 */
public class ArticuloTM extends AbstractTableModel {
    private ArrayList<Articulo> producto;
    private String[] Cabecera = {"idArticulo", "numSerie","nombre","precio"};
    
    public ArrayList<Articulo> getArticulo() {
        return producto;
    }
    
    public void setArticulo(ArrayList<Articulo> articulos){
        this.producto= articulos;
    }  
    
    public ArticuloTM(ArrayList<Articulo> articulo){
        this.producto=articulo;
    }
   
    @Override
    public int getRowCount() {
        return producto.size();
    }

    @Override
    public int getColumnCount() {
        return Cabecera.length;
    }
    
   @Override
    public String getColumnName(int column){
        return Cabecera[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo articulo = producto.get(rowIndex);
        switch(columnIndex){
            case 0 : return articulo.getIdArticulo();
            case 1 : return articulo.getNumSerie();
            case 2 : return articulo.getNombre();
            case 3 : return articulo.getPrecio();    
            default: return null;
        }
    }
}
