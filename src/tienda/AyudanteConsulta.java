/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cristian
 */
public class AyudanteConsulta {
    private static Connection conexion;
    private static PreparedStatement consulta;
    private static ResultSet datos;
    
    public static void conectar() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda","tienda","Tienda.2014");
    }
    
    public static void desconectar() throws SQLException {
        consulta.close();
        conexion.close();
    }
    
    public static void consulta(String query) throws SQLException {
        consulta = conexion.prepareStatement(query);
    }
    
    public static void ejecutar() throws SQLException {
        datos = consulta.executeQuery();
    }
    
    public static void setDatos(ResultSet datos) {
        AyudanteConsulta.datos = datos;
    }
    
    public static ResultSet getDatos() {
        return datos;
    }

    public static PreparedStatement getConsulta() {
        return consulta;
    }

    public static void setConsulta(PreparedStatement consulta) {
        AyudanteConsulta.consulta = consulta;
    }

    public static Connection getConexion() {
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        AyudanteConsulta.conexion = conexion;
    }
    
}
