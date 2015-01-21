/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.tienda.controladores;

import com.manuel.tienda.modelos.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import tienda.AyudanteConsulta;

/**
 *
 * @author Cristian
 */
public class ClienteController {

    private static ArrayList<Cliente> clientes;
    private static Cliente cliente;

    /**
     * Devuelve una lista con todos los clientes en la BD
     * @return lista de clientes
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static ArrayList<Cliente> listaClientes() throws ClassNotFoundException, SQLException {
        clientes = new ArrayList<>();
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM cliente");
        AyudanteConsulta.ejecutar();
        while (AyudanteConsulta.getDatos().next()) {
            cliente = new Cliente();
            cliente.setIdCliente(AyudanteConsulta.getDatos().getInt("idCliente"));
            cliente.setDpi(AyudanteConsulta.getDatos().getString("dpi"));
            cliente.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
            clientes.add(cliente);
        }

        AyudanteConsulta.desconectar();
        return clientes;
    }
    
    /**
     * Busca un Cliente dentro de la BD
     * @param id el código del cliente
     * @return Cliente
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Cliente buscarCliente(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM cliente WHERE idCliente = ?");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.ejecutar();

        cliente = new Cliente();
        if (AyudanteConsulta.getDatos().next()) {
            cliente.setIdCliente(AyudanteConsulta.getDatos().getInt("idCliente"));
            cliente.setDpi(AyudanteConsulta.getDatos().getString("dpi"));
            cliente.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
        }
        
        AyudanteConsulta.desconectar();
        return cliente;
    }

    public static void agregarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("INSERT INTO cliente(idCliente,dpi,nombre) VALUES(?,?,?);");
        AyudanteConsulta.getConsulta().setInt(1, cliente.getIdCliente());
        AyudanteConsulta.getConsulta().setString(2, cliente.getDpi());
        AyudanteConsulta.getConsulta().setString(3, cliente.getNombre());
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }

    public static void editarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("UPDATE cliente SET idCliente = ?, "
                + "dpi = ?, nombre = ? WHERE idCliente = ?;");

        AyudanteConsulta.getConsulta();
        AyudanteConsulta.getConsulta().setInt(1, cliente.getIdCliente());
        AyudanteConsulta.getConsulta().setString(2, cliente.getDpi());
        AyudanteConsulta.getConsulta().setString(3, cliente.getNombre());
        AyudanteConsulta.getConsulta().setInt(4, cliente.getIdCliente());

        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
    
    public static void eliminarCliente(int id) throws ClassNotFoundException, SQLException {
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("DELETE FROM cliente  WHERE IdCliente = ?;");
        AyudanteConsulta.getConsulta().setInt(1, id);
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
}
