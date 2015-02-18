/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import com.manuel.tienda.modelos.DetallePedido;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class DetalleTM extends AbstractTableModel {

    private ArrayList<DetallePedido> detalles;
    private String[] cabecera = {"Articulo", "Pedido", "Cantidad"};

    public ArrayList<DetallePedido> getDetallePedidos() {
        return detalles;
    }

    public void setDetallePedidos(ArrayList<DetallePedido> detalles) {
        this.detalles = detalles;
    }
    
    public DetalleTM(ArrayList<DetallePedido> detalles){
        this.detalles = detalles;
    }

    @Override
    public int getRowCount() {
        return detalles.size();
    }

    @Override
    public int getColumnCount() {
        return cabecera.length;
    }
    
    @Override
    public String getColumnName(int column){
        return cabecera[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetallePedido detalle = detalles.get(rowIndex);
        switch(columnIndex){
            case 0 : return detalle.getIdArticulo();
            case 1 : return detalle.getIdPedido();
            case 2 : return detalle.getCantidad();
            default: return null;
        }
    }
    
}
