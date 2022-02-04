/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Boris
 */
public class ModeloDetalleFactura extends DetalleFactura {

    ConectionPg con = new ConectionPg();

    public ModeloDetalleFactura() {
    }

    public ModeloDetalleFactura(Integer id, Integer factura, Integer producto, Integer cantidad, double precio, double total) {
        super(id, factura, producto, cantidad, precio, total);
    }
    
    public List<DetalleFactura> listarDetalleFactura() {
        List<DetalleFactura> listaDetalleFactura = new ArrayList<DetalleFactura>();
        try {
            String sql = "select * from detallefactura";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                DetalleFactura detalleFactura =new DetalleFactura();
                detalleFactura.setId(rs.getInt("id"));
                detalleFactura.setFactura(rs.getInt("fecha"));
                detalleFactura.setProducto(rs.getInt("total"));
                detalleFactura.setCantidad(rs.getInt("cantidad"));
                detalleFactura.setPrecio(rs.getDouble("precio"));
                detalleFactura.setTotal(rs.getDouble("total"));
                
                listaDetalleFactura.add(detalleFactura);
            }
            rs.close();
            return listaDetalleFactura;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }

  public boolean crearDetalleFactura(){
        String sql= "insert into detallefactura values ('"+getId()+"','"+getFactura()+"','"+getProducto()+"','"+getCantidad()+"','"+getPrecio()+"','"+getTotal()+"')";
        return con.accion(sql);
  }
    
    
    public boolean  modificarDetalleFactura(){
        String sql = "update  detallefactura set factura='"+getFactura()+"',producto='"+getProducto()+"',cantidad='"+getCantidad()+"',precio='"+getPrecio()+"',total='"+getTotal()+"' where id = '"+getId()+"'";
        return con.accion(sql);
        
    }
    
    public boolean eliminarDetalleFactura(){
        String sql = "delete from  detallefactura where id = '"+getId()+"'";
        return con.accion(sql);
    }
    

}
