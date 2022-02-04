/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Equipo
 */
public class ConectionPg {
     Connection con;
    String cadConexion="jdbc:postgresql://localhost:5432/ejercicio_pov";
    String pgUser="postgres";
    String pgPass="1";

    public ConectionPg() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectionPg.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection(cadConexion,pgUser,pgPass);
            System.out.println("Conecxion OK");
        } catch (SQLException ex) {
            Logger.getLogger(ConectionPg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public ResultSet consulta(String sql){ //recibe comando sql y ejecuta
         try {
           
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConectionPg.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
        
    
        
        public boolean accion(String sql) {

        boolean correcto;
        try {
            Statement st = con.createStatement();
            correcto = st.execute(sql);
            st.close();
            correcto=true;
        } catch (SQLException ex) {
            Logger.getLogger(ConectionPg.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            correcto = false;
        }


        return correcto;
        }
    public Connection getCon() {
        return con;
    }
        
}
