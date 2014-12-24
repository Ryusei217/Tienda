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
    
    public static ArrayList<Cliente> buscarClientes() throws ClassNotFoundException, SQLException {
        clientes = new ArrayList<>();
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("SELECT * FROM cliente");
        AyudanteConsulta.ejecutar();
        while(AyudanteConsulta.getDatos().next()){
            cliente = new Cliente();
            cliente.setIdCliente(AyudanteConsulta.getDatos().getInt("idCliente"));
            cliente.setDpi(AyudanteConsulta.getDatos().getString("dpi"));
            cliente.setNombre(AyudanteConsulta.getDatos().getString("nombre"));
            clientes.add(cliente);
        }
        
        AyudanteConsulta.desconectar();
        return clientes;
    }
    
    public static void agregarCliente(Cliente cliente) throws ClassNotFoundException, SQLException{                    
        AyudanteConsulta.conectar();
        AyudanteConsulta.consulta("INSERT INTO cliente(idCliente,dpi,nombre) VALUES(?,?,?);");
        AyudanteConsulta.getConsulta().setInt(1, cliente.getIdCliente());
        AyudanteConsulta.getConsulta().setString(2, cliente.getDpi());
        AyudanteConsulta.getConsulta().setString(3, cliente.getNombre());
        AyudanteConsulta.getConsulta().executeUpdate();
        AyudanteConsulta.desconectar();
    }
    
}
