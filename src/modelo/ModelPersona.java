/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
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
import org.postgresql.util.Base64;
import java.awt.Image;
/**
 *
 * @author Equipo
 */
public class ModelPersona extends Persona{
    ConectionPg cpg = new ConectionPg();//INVOCA A LA CONEXION DE LA BASE DE DATOS
    
    public ModelPersona() {
    }

    public ModelPersona(String idPersona, String Nombres, String apellidos, String fechaNacimiento, int telefono, String sexo, Double sueldo, int cupo) {
        super(idPersona, Nombres, apellidos, fechaNacimiento, telefono, sexo, sueldo, cupo);
    }
    public List<Persona> listarPersonas(){
        List<Persona> lista = new ArrayList<Persona>();
        try {
            String sql ="SELECT idpersona, nombres, apellidos, fechanacimiento,substring(cast(age(fechanacimiento) as varchar),1,2) edad , telefono, sexo, sueldo, cupo, foto FROM public.persona;";
            
            ResultSet rs= cpg.consulta(sql);
            byte[] bytea;
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(rs.getString("idpersona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setFechaNacimiento(rs.getString("fechanacimiento"));
                persona.setEdad(rs.getString("edad"));
                persona.setTelefono(rs.getInt("telefono"));
                persona.setSexo(rs.getString("sexo"));
                persona.setSueldo(rs.getDouble("sueldo"));
                persona.setCupo(rs.getInt("cupo"));
                //Proceso de conversion del formato de la base (Base64) a el formato Image
               //bytea> Bytes Array
                bytea=rs.getBytes("foto");
                if (bytea!=null){
                //Decodificando del formato de la base.(Base64)
                
                 //bytea=Base64.decode(bytea,0,bytea.length);
                try {
                    persona.setFoto(obtenerImagen(bytea));
                } catch (IOException ex) {
                    Logger.getLogger(ModelPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                lista.add(persona);
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
    public boolean crearPersona(){
        String sql;
        sql="INSERT INTO persona (idpersona,nombres,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo)";
        sql+="VALUES('" + getIdPersona()+"',' "+getNombres()+"','"+getApellidos()+"',to_date('"+getFechaNacimiento()+"','yyyy-mm-dd'),'"+getTelefono()+"',' "+getSexo()+"',' "+getSueldo()+"',' "+getCupo()+"');";
        
        return cpg.accion(sql);
    }
    public boolean crearPersonaByte(){
        try {
            String sql;
            sql="INSERT INTO persona (idpersona,nombres,apellidos,fechanacimiento,telefono,sexo,sueldo,cupo,foto)";
            sql+="VALUES(?,?,?,to_date('"+getFechaNacimiento()+"','yyyy-mm-dd'),?,?,?,?,?)";
            PreparedStatement ps = cpg.getCon().prepareStatement(sql);
            ps.setString(1, getIdPersona());
            ps.setString(2, getNombres());
            ps.setString(3, getApellidos());    
            ps.setInt(4, getTelefono());
            ps.setString(5, getSexo());
            ps.setDouble(6, getSueldo());
            ps.setInt(7, getCupo());
            ps.setBinaryStream(8,getImagen(),getLargo() );
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersona.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean eliminar(String identificador){ //eliminar
         String sql = "delete from persona where \"idpersona\"= '" + identificador +"'";
         return cpg.accion(sql);
     }
     public boolean modificar(String identificador){ //modificar identificador es la llave primaria UPDATE= MODIFICAR
         String sql="UPDATE public.persona SET nombres='"+getNombres()+"', apellidos='" + getApellidos()+"', fechanacimiento=to_date('"+getFechaNacimiento()+"','yyyy-mm-dd'),telefono='" + getTelefono()+"', sexo='" + getSexo()+"', sueldo='" + getSueldo()+"', cupo='" + getCupo()+"' WHERE idpersona = '" + identificador + "';";        
         return cpg.accion(sql);
     }
//     public List<Persona> listarPersonas(){
//     String sql = "select idpersona,nombres,apellidos, fechanacimiento,"
//                    + "COALESCE(substring(cast(age(fechanacimiento) as character varying),1,2),'N/A'),"
//                    + "telefono,sexo,sueldo,cupo,foto from public.persona where";
//            sql += " idpersona like '%" + cadena + "%' ";
//            sql += " OR upper(nombres) like Upper('%" + cadena + "%') ";
//            sql += " OR upper(apellidos) like Upper('%" + cadena + "%') ";
//      }
}
