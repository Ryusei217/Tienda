/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.controladores;

import com.manuel.tienda.modelos.Articulo;
import java.sql.SQLException;
import java.util.ArrayList;
import tienda.AyudanteConsulta;

/**
 *
 * @author Escobar
 */
public class ArticuloController {

    private static ArrayList<Articulo> articulo;
    private static Articulo producto;

    public static ArrayList<Articulo> buscarArticulos() throws ClassNotFoundException, SQLException {
        articulo = new ArrayList<>();
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM articulo");
        AyudanteConsulta.ejecutar();
        while (AyudanteConsulta.getDatos().next()) {
            producto = new Articulo();
            producto.setIdArticulo(AyudanteConsulta.getDatos().getInt("idArticulo"));
            producto.setNumSerie(AyudanteConsulta.getDatos().getString("numSerie"));
            producto.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
            producto.setPrecio(AyudanteConsulta.getDatos().getBigDecimal("precio"));
            articulo.add(producto);
        }

        AyudanteConsulta.desconectar();
        return articulo;

    }
    
    public static void agregarArticulos() throws ClassNotFoundException, SQLException{
        
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("INSERT INTO articulo(IdArticulo,numSerie,nombre,precio) VALUES(?,?,?,?);");
        AyudanteConsulta.getConsulta().setInt(1, producto.getIdArticulo());
        AyudanteConsulta.getConsulta().setString(2, producto.getNumSerie());
        AyudanteConsulta.getConsulta().setString(3, producto.getNombre());
        AyudanteConsulta.getConsulta().setBigDecimal(4, producto.getPrecio());
        
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }

}
