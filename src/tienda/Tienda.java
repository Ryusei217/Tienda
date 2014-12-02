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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection conexion;
            PreparedStatement consulta;
            ResultSet datos;
            
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda","root","pos.2012");
            consulta = conexion.prepareStatement("SELECT * FROM cliente");
            datos = consulta.executeQuery();
            while(datos.next()){
                System.out.println("Codigo: " + datos.getInt("idCliente"));
                System.out.println("DPI: " + datos.getString("dpi"));
                System.out.println("Nombre: " + datos.getString("nombre"));
                System.out.println("--------------------------------------");
            }
        } catch (Exception e) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
