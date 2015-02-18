/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.controladores;

import com.manuel.tienda.modelos.DetallePedido;
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
 * @param detalles
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public static void agregarPedido(Pedido pedido, ArrayList<DetallePedido> detalles) 
            throws ClassNotFoundException, SQLException {
        //Guarda el id del pedido devuelto por la BD
        int idPedido;
        
        //Iniciamos la conexion
        AyudanteConsulta.conectar();
        
        //Inicializa la transaccion, deshabilitando el auto commit
        AyudanteConsulta.getConexion().setAutoCommit(false);
        
        //Insertar el Pedido
        AyudanteConsulta.consultaConLlave(
                "INSERT INTO pedido(idPedido,fecha,Cliente_idCliente) VALUES(?,?,?);"
        );
        AyudanteConsulta.getConsulta().setInt(1, pedido.getIdPedido());
        AyudanteConsulta.getConsulta().setDate(2, new java.sql.Date(pedido.getFecha().getTime()));
        AyudanteConsulta.getConsulta().setInt(3, pedido.getIdCliente());
        AyudanteConsulta.getConsulta().execute();
        AyudanteConsulta.setDatos(AyudanteConsulta.getConsulta().getGeneratedKeys());
        
        //Si nos devuelve un valor insertamos los detalles
        if(AyudanteConsulta.getDatos().next()){
            //Guardamos el ID devuelto en nuestra variable
            idPedido = AyudanteConsulta.getDatos().getInt(1);
            
            //Establecemos la consulta para insertar detalles de pedido
            AyudanteConsulta.consulta(
                        "INSERT INTO DetallePedido(Pedido_idPedido, Articulo_idArticulo, Cantidad)"
                                + "VALUES(?,?,?);"
                );
            
            //Agregamos todos los detalles en un lote (batch)
            for (DetallePedido detalle : detalles) {
                AyudanteConsulta.getConsulta().setInt(1, idPedido);
                AyudanteConsulta.getConsulta().setInt(2, detalle.getIdArticulo());
                AyudanteConsulta.getConsulta().setInt(3, detalle.getCantidad());
                AyudanteConsulta.getConsulta().addBatch();
            }
            
            //Ejecutamos la consulta y damos commit por ultimo                
            if (AyudanteConsulta.checkBatch(AyudanteConsulta.getConsulta().executeBatch())) {
                AyudanteConsulta.getConexion().commit();
            }else{
                AyudanteConsulta.getConexion().rollback();
            }
            
            //Volvemos a restaurar el autocommit
            AyudanteConsulta.getConexion().setAutoCommit(true);
        
            AyudanteConsulta.desconectar();
        }
        else{
            //Si no devuelve nada borramos los cambios anteriores
            AyudanteConsulta.getConexion().rollback();
            //Volvemos a restaurar el autocommit
            AyudanteConsulta.getConexion().setAutoCommit(true);
        
            AyudanteConsulta.desconectar();
            
            throw new SQLException("Los datos son inconsistentes.");
        } 
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
