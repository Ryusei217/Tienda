/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import com.manuel.tienda.modelos.Pedido;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class PedidoTM extends AbstractTableModel {
private ArrayList<Pedido> pedidos;
    private String[] cabecera = {"idPedido", "fecha", "idCliente"};

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public PedidoTM(ArrayList<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
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
        Pedido pedido = pedidos.get(rowIndex);
        switch(columnIndex){
            case 0 : return pedido.getIdPedido();
            case 1 : return pedido.getFecha();
            case 2 : return pedido.getIdCliente();
            default: return null;
        }
    }
    
}
