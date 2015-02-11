/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.controladores;

import com.manuel.tienda.modelos.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import tienda.AyudanteConsulta;

/**
 *
 * @author Cristian
 */
public class PedidoController {
    private static ArrayList<Pedido> pedidos;
    private static Pedido pedido;

    /**
     * Devuelve una lista con todos los pedidos en la BD
     * @return lista de pedidos
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static ArrayList<Pedido> listaPedidos() throws ClassNotFoundException, SQLException {
        pedidos = new ArrayList<>();
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM pedido");
        AyudanteConsulta.ejecutar();
        while (AyudanteConsulta.getDatos().next()) {
            pedido = new Pedido();
            pedido.setIdPedido(AyudanteConsulta.getDatos().getInt("idPedido"));
            pedido.setFecha(AyudanteConsulta.getDatos().getDate("fecha"));
            pedido.setIdCliente(AyudanteConsulta.getDatos().getInt("Cliente_idCliente"));
            pedidos.add(pedido);
        }

        AyudanteConsulta.desconectar();
        return pedidos;
    }
    
    /**
     * Busca un Pedido dentro de la BD
     * @param id el código del pedido
     * @return Pedido
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Pedido buscarPedido(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM pedido WHERE idPedido = ?");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.ejecutar();

        pedido = new Pedido();
        if (AyudanteConsulta.getDatos().next()) {
            pedido.setIdPedido(AyudanteConsulta.getDatos().getInt("idPedido"));
            pedido.setFecha(AyudanteConsulta.getDatos().getDate("fecha"));
            pedido.setIdCliente(AyudanteConsulta.getDatos().getInt("Cliente_idCliente"));
        }
        
        AyudanteConsulta.desconectar();
        return pedido;
    }
/**
 * metodo que agrega un pedido en la BD
 * @param pedido pedido que se agrega
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public static void agregarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("INSERT INTO pedido(idPedido,dpi,nombre) VALUES(?,?,?);");
        AyudanteConsulta.getConsulta().setInt(1, pedido.getIdPedido());
        AyudanteConsulta.getConsulta().setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
        AyudanteConsulta.getConsulta().setInt(3, pedido.getIdCliente());
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
    
    /**
     * Edita un pedido que existe en la BD
     * @param pedido pedido que se edita
     * @throws ClassNotFoundException
     * @throws SQLException 
     */

    public static void editarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("UPDATE pedido SET idPedido = ?, "
                + "dpi = ?, nombre = ? WHERE idPedido = ?;");

        AyudanteConsulta.getConsulta();
        AyudanteConsulta.getConsulta().setInt(1, pedido.getIdPedido());
        AyudanteConsulta.getConsulta().setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
        AyudanteConsulta.getConsulta().setInt(3, pedido.getIdCliente());
        AyudanteConsulta.getConsulta().setInt(4, pedido.getIdPedido());

        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
    
    /**
     * Elimina pedido en la BD
     * @param id codigo del pedido encontrado para eliminarlo
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void eliminarPedido(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("DELETE FROM pedido  WHERE IdPedido = ?;");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
}
