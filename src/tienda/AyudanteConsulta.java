/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import com.manuel.tienda.modelos.Articulo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AyudanteConsulta permite trabajar con conexiones a MySQL
 * sin necesidad de instanciar en todas partes las conexiones.
 * @author Cristian
 */
public class AyudanteConsulta {
    private static Connection conexion;
    private static PreparedStatement consulta;
    private static ResultSet datos;
    
    /**
     * Permite abrir la conexion hacia la base de datos.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void conectar() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda","tienda","Tienda.2014");
    }
    
    /**
     * Permite cerrar la conexion hacia la base de datos.
     * @throws SQLException 
     */
    public static void desconectar() throws SQLException {
        consulta.close();
        conexion.close();
    }
    
    /**
     * Permite generar una consulta en la base de datos
     * @param query consulta a ejecutar
     * @throws SQLException 
     */
    public static void consulta(String query) throws SQLException {
        consulta = conexion.prepareStatement(query);
    }
    
    public static void consultaConLlave(String query) throws SQLException{
        consulta = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * Ejecuta la consulta en la base de datos.
     * @throws SQLException 
     */
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

    /**
     * Evalua el arreglo devuelto por un PreparedStatement en busca de operaciones
     * que hayan fallado a la hora de ingresarse a la BD
     * @param val arreglo de enteros con el resultado.
     * @return true si no hay errores de ejecucion.
     */
    public static boolean checkBatch(int[] val){
        boolean centinela = true;
        for (int i : val) {
            if(val[i] == PreparedStatement.EXECUTE_FAILED){
                centinela = false;
            }
        }
        return centinela;
    }
    
}
