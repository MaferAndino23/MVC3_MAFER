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
/*

//modleo
public byte[] imagenDeSql(int numero) {
        try {
            String sql = "select foto from hola where idfoto = '" + numero + "' ;";
            ResultSet rs = con.consulta(sql);
            byte[] bytea = null;
            while (rs.next()) {                
                 bytea = rs.getBytes("foto");
            }
            return bytea;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloFoto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public Image obtenerImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("jpg");//recuerda buscar un solo formato 
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);

    }

//popner en controlador
public Icon imagenDBB(int numero) throws IOException {
        Image imagen = modelo.obtenerImagen(modelo.imagenDeSql(numero));
        Icon icon = new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));//aki cambias tamano 
        return icon;
    }

    public void CargaImagenenes() throws IOException {

        vista.getBtn1().setIcon(imagenDBB(1));
        vista.getBtn2().setIcon(imagenDBB(2));
        vista.getBtn3().setIcon(imagenDBB(3));
        vista.getBtnFor().setIcon(imagenDBB(4));

    }


*/