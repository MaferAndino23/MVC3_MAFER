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
public class ModeloFactura extends Factura {

    ConectionPg con = new ConectionPg();

    public ModeloFactura() {
    }

    public ModeloFactura(Integer id, String fecha, double total, String Cliente) {
        super(id, fecha, total, Cliente);
    }

    public List<Factura> listarFactura() {
        List<Factura> listaFactura = new ArrayList<Factura>();
        try {
            String sql = "select * from factura";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Factura factura =new Factura();
                factura.setId(rs.getInt("id"));
                factura.setFecha(rs.getString("fecha"));
                factura.setTotal(rs.getInt("total"));
                factura.setCliente(rs.getString("cliente"));
                
                listaFactura.add(factura);
            }
            rs.close();
            return listaFactura;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloFactura.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public boolean crearFactura(){
        String sql= "insert into factura values ('"+getId()+"',to_date('"+getFecha()+"','yyyy-mm-dd'),'"+getTotal()+"','"+getCliente()+"')";
        return con.accion(sql);

    }
    
    public boolean  modificar(){
        String sql = "update factura set fecha =to_date('"+getFecha()+"','yyyy-mm-dd'), total='"+getTotal()+"',cliente='"+getCliente()+"' where id = '"+getId()+"'";
        return con.accion(sql);
        
    }
    
    public boolean eliminarFactura(){
        String sql = "delete from factura where id = '"+getId()+"'";
        return con.accion(sql);
    }

}
