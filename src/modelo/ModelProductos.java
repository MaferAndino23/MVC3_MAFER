/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
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
 * @author Equipo
 */
public class ModelProductos extends Productos{
     ConectionPg cpg = new ConectionPg();//INVOCA A LA CONEXION DE LA BASE DE DATOS

    public ModelProductos() {
    }

    public ModelProductos(int id, String nombre, Double precio, int cantidad, String descripcion) {
        super(id, nombre, precio, cantidad, descripcion);
    }
     public List<Productos> listarProductos(){
        List<Productos> lista = new ArrayList<Productos>();
        try {
            String sql ="Select * from producto;";
            
            ResultSet rs= cpg.consulta(sql);
             byte[] bytea;
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                //Proceso de conversion del formato de la base (Base64) a el formato Image
               //bytea> Bytes Array
                bytea=rs.getBytes("foto");
                if (bytea!=null){
                //Decodificando del formato de la base.(Base64)
                
                 //bytea=Base64.decode(bytea,0,bytea.length);
                try {
                    pro.setFoto(obtenerImagen(bytea));
                } catch (IOException ex) {
                    Logger.getLogger(ModelPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
             lista.add(pro);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     private Image obtenerImagen(byte[] bytes) throws IOException{
        ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
        Iterator it= ImageIO.getImageReadersByFormatName("png");
        ImageReader reader=(ImageReader)it.next();
        Object source=bis;
        ImageInputStream iis=ImageIO.createImageInputStream(source);
        reader.setInput(iis,true);
        ImageReadParam param= reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0,param);
        
        
        
    }
    public boolean crearProducto(){
        String sql;
        sql="INSERT INTO producto (id,nombre,precio,cantidad,descripcion)";
        sql+="VALUES('" +getId()+"',' "+getNombre()+"','"+getPrecio()+"','"+getCantidad()+"',' "+getDescripcion()+"');";
        
        return cpg.accion(sql);
    }
    public boolean crearProductoByte(){
        try {
            String sql;
            sql="INSERT INTO producto (id,nombre,precio,cantidad,descripcion, foto)";
            sql+="VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = cpg.getCon().prepareStatement(sql);
            ps.setInt(1, getId());
            ps.setString(2, getNombre());
            ps.setDouble(3, getPrecio());
            ps.setInt(4, getCantidad());
            ps.setString(5, getDescripcion());
            ps.setBinaryStream(6,getImagen(),getLargo() );
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersona.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean eliminar(int identificador){ //eliminar
         String sql = "delete from producto where \"id\"= '" + identificador +"'";
         return cpg.accion(sql);
     }
     public boolean modificar(String identificador){ //modificar identificador es la llave primaria UPDATE= MODIFICAR
         String sql="UPDATE public.producto SET nombre='"+getNombre()+"', precio='" + getPrecio()+"', cantidad='" + getCantidad()+"', descripcion='" + getDescripcion()+ "'WHERE id = '" + identificador + "';";       
         return cpg.accion(sql);
     } 
     public int contar(){
         try {
             String sql = "select count(id) as numero from producto;";
             ResultSet rs=cpg.consulta(sql);
             while(rs.next()){
                 return rs.getInt("numero")+1;
                 
             }
             return 0;
         } catch (SQLException ex) {
             Logger.getLogger(ModelProductos.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0; 
     }
}
