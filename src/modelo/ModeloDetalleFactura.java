/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

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
                DetalleFactura detalleFactura = new DetalleFactura();
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

    public Image obtenerImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");//recuerda buscar un solo formato 
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);

    }

    public boolean crearDetalleFactura() {
        String sql = "insert into detallefactura values ('" + getId() + "','" + getFactura() + "','" + getProducto() + "','" + getCantidad() + "','" + getPrecio() + "','" + getTotal() + "')";
        return con.accion(sql);
    }

    public boolean modificarDetalleFactura() {
        String sql = "update  detallefactura set factura='" + getFactura() + "',producto='" + getProducto() + "',cantidad='" + getCantidad() + "',precio='" + getPrecio() + "',total='" + getTotal() + "' where id = '" + getId() + "'";
        return con.accion(sql);

    }

    public boolean eliminarDetalleFactura() {
        String sql = "delete from  detallefactura where id = '" + getId() + "'";
        return con.accion(sql);
    }

    public byte[] imagenDeSql(int numero) {
        try {
            String sql = "SELECT  foto FROM public.producto  where id = '" + numero + "';";
            ResultSet rs = con.consulta(sql);
            byte[] bytea = null;
            while (rs.next()) {
                bytea = rs.getBytes("foto");
            }
            return bytea;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//sentecias sql

    public List<Productos> productitos(int numero) {
        try {
            List<Productos> listaproductos = new ArrayList<Productos>();
            String sql = "SELECT id, nombre,descripcion, precio  FROM public.producto where id = '" + numero + "';";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                listaproductos.add(producto);

            }
            rs.close();
            return listaproductos;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Productos> productitos(String Buscar) {
        try {
            List<Productos> listaproductos = new ArrayList<Productos>();
            String sql = "SELECT id, nombre, cantidad  FROM public.producto where  lower(nombre) like lower('%"+Buscar+"%') ;";
            ResultSet rs = con.consulta(sql);
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));

                listaproductos.add(producto);

            }
            rs.close();
            return listaproductos;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDetalleFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//    public int contar(){
//         try {
//             String sql = "select count(id) as numero from factura;";
//             ResultSet rs=con.consulta(sql);
//             while(rs.next()){
//                 return rs.getInt("numero")+1;
//                 
//             }
//             return 0;
//         } catch (SQLException ex) {
//             Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         return 0; 
//     }
}
