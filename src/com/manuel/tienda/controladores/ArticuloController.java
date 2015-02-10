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

    public static ArrayList<Articulo> listaArticulos() throws ClassNotFoundException, SQLException {
        articulo = new ArrayList<>();
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM articulo");
        AyudanteConsulta.ejecutar();
        while (AyudanteConsulta.getDatos().next()) {
            producto = new Articulo();
            producto.setIdArticulo(AyudanteConsulta.getDatos().getInt("idArticulo"));
            producto.setNumSerie(AyudanteConsulta.getDatos().getString("numeroSerie"));
            producto.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
            producto.setPrecio(AyudanteConsulta.getDatos().getBigDecimal("precio"));
            articulo.add(producto);
        }

        AyudanteConsulta.desconectar();
        return articulo;
    }

    public static Articulo buscarArticulos(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM articulo WHERE idArticulo = ?");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.ejecutar();
        
            producto = new Articulo();
            if (AyudanteConsulta.getDatos().next()){
            producto.setIdArticulo(AyudanteConsulta.getDatos().getInt("idArticulo"));
            producto.setNumSerie(AyudanteConsulta.getDatos().getString("numeroSerie"));
            producto.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
            producto.setPrecio(AyudanteConsulta.getDatos().getBigDecimal("precio"));
        }
        AyudanteConsulta.desconectar();
        return producto;
    }

    /**
     * Agrega articulos en la BD
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void agregarArticulos(Articulo articulo) throws ClassNotFoundException, SQLException {

        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("INSERT INTO articulo(idArticulo,numeroSerie,nombre,precio) VALUES(?,?,?,?);");
        AyudanteConsulta.getConsulta().setInt(1, articulo.getIdArticulo());
        AyudanteConsulta.getConsulta().setString(2, articulo.getNumSerie());
        AyudanteConsulta.getConsulta().setString(3, articulo.getNombre());
        AyudanteConsulta.getConsulta().setBigDecimal(4, articulo.getPrecio());

        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }

    /**
     * Edita Articulos en la BD
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void editarArticulos(Articulo producto) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("UPDATE articulo SET idArticulo = ?, "
                + "numeroSerie = ?, nombre = ?, precio = ? WHERE idArticulo = ?;");

        AyudanteConsulta.getConsulta();
        AyudanteConsulta.getConsulta().setInt(1, producto.getIdArticulo());
        AyudanteConsulta.getConsulta().setString(2, producto.getNumSerie());
        AyudanteConsulta.getConsulta().setString(3, producto.getNombre());
        AyudanteConsulta.getConsulta().setBigDecimal(4, producto.getPrecio());
        AyudanteConsulta.getConsulta().setInt(5, producto.getIdArticulo());

        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }

    /**
     * Elimina articulo en la BD
     *
     * @param id codigo del Articulo
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void eliminarArticulo(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("DELETE FROM articulo  WHERE idArticulo = ?;");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
}
