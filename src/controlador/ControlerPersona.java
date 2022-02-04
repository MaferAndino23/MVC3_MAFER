/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.ModelPersona;
import modelo.Persona;
import vista.viewPersonas;

/**
 *
 * @author Equipo
 */
public class ControlerPersona {

    private ModelPersona modelo;
    private viewPersonas vista;
    private JFileChooser jfc;
    public static ArrayList<Persona> listapersonas = new ArrayList();

    ModelPersona dbp = new ModelPersona(); //inicializar llamar al objeto de la clase

    public ControlerPersona(ModelPersona modelo, viewPersonas vista) {//recibe instacians 
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);//se agrega la vista
        //vista.setClosable(true);//para q salga la equis
        //cargarPersonas("");

    }
   
    public void iniciaControl() {
        //Estar a la escucha de todos los eventos de la vista 
        //boton actualizar
         KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cargarPersonas(vista.getTxtBuscador().getText());
            }
        };
        vista.getTxtBuscador().addKeyListener(kl);
        vista.getBtnactualizar().addActionListener(l -> cargarPersonas(""));
        vista.getBtncrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtneditar().addActionListener(l -> abrirDialogo(2));
        vista.getBtnaceptar().addActionListener(l -> crearEditarPersona());
        vista.getBtncancelar().addActionListener(l -> cancelar());
        vista.getBtnremover().addActionListener(l -> eliminar());
        vista.getBtnExaminar().addActionListener(l->examinaFoto());
    }

     private void examinaFoto(){
        jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado=jfc.showOpenDialog(vista);
        if(estado==JFileChooser.APPROVE_OPTION){
            try {
                Image imagen=ImageIO.read(jfc.getSelectedFile()).getScaledInstance(
                        vista.getLblFoto().getWidth(),
                        vista.getLblFoto().getHeight(),
                        Image.SCALE_DEFAULT);
                
                Icon icono=new ImageIcon(imagen);
                vista.getLblFoto().setIcon(icono);
                vista.getLblFoto().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(ControlerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void abrirDialogo(int ce) {
        String title;
        if (ce == 1) {
            title = "Crear nueva Persona";
            vista.getDialogopersona().setName("crear");
        } else {
            modificarPersona();
            title = "Editar Persona";
            vista.getDialogopersona().setName("editar");
        }
        
        vista.getDialogopersona().setLocationRelativeTo(null);
        vista.getDialogopersona().setSize(980, 450);
        vista.getDialogopersona().setTitle(title);
        vista.getDialogopersona().setVisible(true);
        vista.getDialogopersona().setResizable(false);
    }

    private void crearEditarPersona() {
        if (vista.getDialogopersona().getName() == "crear") {
            Crear();

        }
        if (vista.getDialogopersona().getName() == "editar") {
            Modificar();
        }//else hacemos el editar

    }
    
    public void Crear(){
            //insertar
             //String sexo = (String)Combosexo().getSelectedItem();//extraer
              //comboestado.setSelectedItem(lista.get(selec).getestado_civil());
            String cedula = vista.getTxtcedula().getText();
            String nombres = vista.getTxtnombres().getText();
            String apellidos = vista.getTxtapellidos().getText();
            int telefono = Integer.parseInt(vista.getTxttelefono().getText());
            String sexo =  ( String)( vista.getCombosexo().getSelectedItem());
            double sueldo = Double.valueOf(vista.getTxtsueldo().getText());
            int cupo = Integer.parseInt(vista.getTxtcupo().getText());
            String fechanacimiento = ((JTextField) vista.getFechanaci().getDateEditor().getUiComponent()).getText();
            ModelPersona persona = new ModelPersona();
            persona.setIdPersona(cedula);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setFechaNacimiento(fechanacimiento);
            persona.setTelefono(telefono);
            persona.setSexo(sexo);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);
            try {
                //Foto
                FileInputStream img=new FileInputStream(jfc.getSelectedFile());
                int largo=(int)jfc.getSelectedFile().length();
                persona.setImagen(img);
                persona.setLargo(largo);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           if( persona.crearPersonaByte()){
               vista.getDialogopersona().setVisible(false);
               JOptionPane.showMessageDialog(vista, "Persona Creada Satisfactoriamente");
           }else{
               JOptionPane.showMessageDialog(vista, "No se pudo crear la persona");
           }
            
//            if (persona.crearPersona()) {
//                vista.getDialogopersona().setVisible(false);//cierra el dialogo
//                JOptionPane.showMessageDialog(vista, "Persona creada satisfactoriamente");
//            } else {
//                JOptionPane.showMessageDialog(vista, "No se pudo crear la persona");
//            }
    }
    public void Modificar(){
             String cedula = vista.getTxtcedula().getText();
            String nombres = vista.getTxtnombres().getText();
            String apellidos = vista.getTxtapellidos().getText();
            int telefono = Integer.parseInt(vista.getTxttelefono().getText());
            String sexo =  ( String)( vista.getCombosexo().getSelectedItem());
            double sueldo = Double.valueOf(vista.getTxtsueldo().getText());
            int cupo = Integer.parseInt(vista.getTxtcupo().getText());
            String fechanacimiento = ((JTextField) vista.getFechanaci().getDateEditor().getUiComponent()).getText();
            ModelPersona persona = new ModelPersona();
            persona.setIdPersona(cedula);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setFechaNacimiento(fechanacimiento);
            persona.setTelefono(telefono);
            persona.setSexo(sexo);
            persona.setSueldo(sueldo);
            persona.setCupo(cupo);
            ImageIcon ic = (ImageIcon) vista.getLblFoto().getIcon();
            persona.setFoto(ic.getImage());
            if (persona.modificar(vista.getTxtcedula().getText())) {
                vista.getDialogopersona().setVisible(false);//cierra el dialogo
                JOptionPane.showMessageDialog(vista, "Persona Modificada satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo Modificar la persona");
            }
    }        
    public void eliminar() {
        List<Persona> lista = modelo.listarPersonas();
        int selec = vista.getTablapersonas().getSelectedRow();//para q aparezca lo q selecciono y saber la posicion    
        if (modelo.eliminar(lista.get(selec).getIdPersona())) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado satisfactoriamente",
                    "Perfecto", JOptionPane.PLAIN_MESSAGE);
            // cargarPersonas();
        } else {
            JOptionPane.showMessageDialog(vista, "No se han guardado los cambios", "Error!", 0);
        }
    }
   
//    private void cargarPersonas() {
//        //Enlazar el modelo de la tabla con mi controlador
//        DefaultTableModel tblModel;//para la tabla
//        tblModel = (DefaultTableModel) vista.getTablapersonas().getModel();
//        tblModel.setNumRows(0);//limpio filas de la tabla
//        List<Persona> listap = modelo.listarPersonas();//enlazo al modelo y obtengo los datos 
//
//        listap.stream().forEach(pe -> {
//            String[] filap = {pe.getIdPersona(), pe.getNombres(), pe.getApellidos(),pe.getFechaNacimiento(), pe.getEdad(), Integer.toString(pe.getTelefono()), pe.getSexo(), String.valueOf(pe.getSueldo()), Integer.toString(pe.getCupo())};
//            tblModel.addRow(filap);
//        });
//
//    }
    private void cargarPersonas(String cadena){
        vista.getTablapersonas().setDefaultRenderer(Object.class, new ImagenTabla());//La manera de renderizar la tabla.
        vista.getTablapersonas().setRowHeight(100);
         DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();
        //Enlazar el modelo de tabla con mi controlador.
        DefaultTableModel tblModel;
        tblModel=(DefaultTableModel)vista.getTablapersonas().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.
        List<Persona> listap=modelo.listarPersonas();//Enlazo al Modelo y obtengo los datos
        Holder<Integer> i = new Holder<>(0);//contador para el no. fila
        listap.stream().forEach(pe->{
            
            tblModel.addRow(new Object[10]);//Creo una fila vacia/
            vista.getTablapersonas().setValueAt(pe.getIdPersona(), i.value, 0);
            vista.getTablapersonas().setValueAt(pe.getNombres(), i.value, 1);
            vista.getTablapersonas().setValueAt(pe.getApellidos(), i.value, 2);
            vista.getTablapersonas().setValueAt(pe.getFechaNacimiento(), i.value, 3);
            vista.getTablapersonas().setValueAt(pe.getEdad(), i.value, 4);
            vista.getTablapersonas().setValueAt(pe.getTelefono(), i.value, 5);
            vista.getTablapersonas().setValueAt(pe.getSexo(), i.value, 6);
            vista.getTablapersonas().setValueAt(pe.getSueldo(), i.value, 7);
            vista.getTablapersonas().setValueAt(pe.getCupo(), i.value, 8);
            Image foto=pe.getFoto();
           
            if(foto!=null){
            
                Image nimg= foto.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icono=new ImageIcon(nimg);
                renderer.setIcon(icono);
                vista.getTablapersonas().setValueAt(new JLabel(icono), i.value, 9);
                
            }else{
                vista.getTablapersonas().setValueAt(null, i.value, 9);
            }
            
            i.value++;
//           String[] filap={pe.getIdPersona(),pe.getNombres(),pe.getApellidos(),"25"}; 
//           tblModel.addRow(filap);
        });
        
    }
    

    private void cancelar(){
        vista.getDialogopersona().setVisible(false);
    }
    private void modificarPersona() {
        Persona persona = new Persona();
        DefaultTableModel tblPersonas = (DefaultTableModel) vista.getTablapersonas().getModel();
        int fila = vista.getTablapersonas().getSelectedRow();
        if (fila != -1) {
           
            vista.getTxtcedula().setText(tblPersonas.getValueAt(fila, 0).toString());
            vista.getTxtnombres().setText(tblPersonas.getValueAt(fila, 1).toString());
            vista.getTxtapellidos().setText(tblPersonas.getValueAt(fila, 2).toString());
            String fecha = (tblPersonas.getValueAt(fila, 3).toString());
            Date sFecha;
            try {
                sFecha = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                vista.getFechanaci().setDate(sFecha);//setDateFormatString(tblPersonas.getValueAt(fila, 3).toString());
            } catch (ParseException ex) {
                Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            vista.getTxttelefono().setText(tblPersonas.getValueAt(fila, 5).toString());
            vista.getCombosexo().setSelectedItem(tblPersonas.getValueAt(fila, 6));
            vista.getTxtsueldo().setText(tblPersonas.getValueAt(fila, 7).toString());
            vista.getTxtcupo().setText(tblPersonas.getValueAt(fila, 8).toString());
            JLabel lbl = (JLabel) tblPersonas.getValueAt(fila, 9);
            vista.getLblFoto().setIcon(lbl.getIcon());
            
        } else {
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUNA PERSONA Y LUEGO EN EDITAR", "AVISO", 2);
        }

    }
}
