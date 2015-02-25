/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.abstractos;

import com.manuel.tienda.modelos.Articulo;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Cristian
 */
public class ArticuloLM extends AbstractListModel implements ComboBoxModel {
    private ArrayList<Articulo> lista;
    private Articulo modelo;
    
    public ArticuloLM(){
        lista = new ArrayList<>();
        modelo = new Articulo();
    }
    
    public ArticuloLM(ArrayList<Articulo> articulos){
        this.lista = articulos;
        this.modelo = new Articulo();
    }

    public ArrayList<Articulo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articulo> lista) {
        this.lista = lista;
    }        

    @Override
    public int getSize() {
        return this.lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.modelo = (Articulo)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.modelo;
    }
    
}
