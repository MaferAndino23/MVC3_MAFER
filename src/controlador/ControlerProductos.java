/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;
import modelo.ModelProductos;
import modelo.Productos;
import vista.viewProductos;

/**
 *
 * @author Equipo
 */
public class ControlerProductos {
     private viewProductos vista;
     private ModelProductos modelo;
     private JFileChooser jfc;
     public static ArrayList<Productos> listaproductos = new ArrayList();

    ModelProductos dbp = new ModelProductos(); //inicializar llamar al objeto de la clase

    public ControlerProductos(viewProductos vista, ModelProductos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
    }

    
     public void iniciaControl() {
          //Estar a la escucha de todos los eventos de la vista 
        //boton actualizar
         //cargarProductos();
        vista.getBtnActualizar().addActionListener(l -> cargarProductos());
        vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
        vista.getBtnaceptar().addActionListener(l -> crearEditarProducto());
        vista.getBtncancelar().addActionListener(l -> cancelar());
        vista.getBtnRemover().addActionListener(l -> eliminar());
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
                Logger.getLogger(ControlerProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     private void abrirDialogo(int ce) {
        String title;
        if (ce == 1) {
            title = "Crear nuevo Producto";
            vista.getDialogoProducto().setName("crear");
        } else {
            modificarProducto();
            title = "Editar Producto";
            vista.getDialogoProducto().setName("editar");
        }
        vista.getDialogoProducto().setLocationRelativeTo(null);
        vista.getDialogoProducto().setSize(980, 450);
        vista.getDialogoProducto().setTitle(title);
        vista.getDialogoProducto().setVisible(true);
        vista.getDialogoProducto().setResizable(false);
    }

    private void crearEditarProducto() {
        if (vista.getDialogoProducto().getName() == "crear") {
            Crear();

        }
        if (vista.getDialogoProducto().getName() == "editar") {
            Modificar();
        }//else hacemos el editar

    }
    
    public void Crear(){
            //insertar
             //String sexo = (String)Combosexo().getSelectedItem();//extraer
              //comboestado.setSelectedItem(lista.get(selec).getestado_civil());
           
            //int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.valueOf(vista.getTxtPrecio().getText());           
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());         
            String descripcion = vista.getAreaDescripcion().getText();
           
            ModelProductos producto = new ModelProductos();
            //System.out.println("000"+producto.contar());
            producto.setId(producto.contar());
            //producto.setId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setDescripcion(descripcion);
           try {
                //Foto
                FileInputStream img=new FileInputStream(jfc.getSelectedFile());
                int largo=(int)jfc.getSelectedFile().length();
                producto.setImagen(img);
                producto.setLargo(largo);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlerPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           if( producto.crearProductoByte()){
               vista.getDialogoProducto().setVisible(false);
               JOptionPane.showMessageDialog(vista, "Producto Creado Satisfactoriamente");
           }else{
               JOptionPane.showMessageDialog(vista, "No se pudo crear el producto");
           }
//            if (producto.crearProducto()) {
//                vista.getDialogoProducto().setVisible(false);//cierra el dialogo
//                JOptionPane.showMessageDialog(vista, "Producto creada satisfactoriamente");
//            } else {
//                JOptionPane.showMessageDialog(vista, "No se pudo crear el producto");
//            }
    }
    public void Modificar(){
           int id = Integer.parseInt(vista.getTxtId().getText());
            String nombre = vista.getTxtNombre().getText();
            double precio = Double.valueOf(vista.getTxtPrecio().getText());           
            int cantidad = Integer.parseInt(vista.getTxtCantidad().getText());         
            String descripcion = vista.getAreaDescripcion().getText();
           
            ModelProductos producto = new ModelProductos();
            producto.setId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setDescripcion(descripcion);
            ImageIcon ic = (ImageIcon) vista.getLblFoto().getIcon();
            producto.setFoto(ic.getImage());
           
            if (producto.modificar(vista.getTxtId().getText())) {
                vista.getDialogoProducto().setVisible(false);//cierra el dialogo
                JOptionPane.showMessageDialog(vista, "Producto creada satisfactoriamente");
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo crear el producto");
            }
    }        
    public void eliminar() {
        List<Productos> lista = modelo.listarProductos();
        int selec = vista.getTablaProductos().getSelectedRow();//para q aparezca lo q selecciono y saber la posicion    
        if (modelo.eliminar(lista.get(selec).getId())) {
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
//        tblModel = (DefaultTableModel) vista.getTablaProductos().getModel();
//        tblModel.setNumRows(0);//limpio filas de la tabla
//        List<Productos> listap = modelo.listarProductos();//enlazo al modelo y obtengo los datos 
//
//        listap.stream().forEach(pe -> {
//            String[] filap = {Integer.toString(pe.getId()), pe.getNombre(),  String.valueOf(pe.getPrecio()), Integer.toString(pe.getCantidad()), pe.getDescripcion()};
//            tblModel.addRow(filap);
//        });
//
//    }
    private void cargarProductos(){
        vista.getTablaProductos().setDefaultRenderer(Object.class, new ImagenTabla());//La manera de renderizar la tabla.
        vista.getTablaProductos().setRowHeight(100);
        //Enlazar el modelo de tabla con mi controlador.
        DefaultTableModel tblModel;
        tblModel=(DefaultTableModel)vista.getTablaProductos().getModel();
        tblModel.setNumRows(0);//limpio filas de la tabla.
        List<Productos> listap=modelo.listarProductos();//Enlazo al Modelo y obtengo los datos
        Holder<Integer> i = new Holder<>(0);//contador para el no. fila
        listap.stream().forEach(pe->{
            
            tblModel.addRow(new Object[11]);//Creo una fila vacia/
            vista.getTablaProductos().setValueAt(pe.getId(), i.value, 0);
            vista.getTablaProductos().setValueAt(pe.getNombre(), i.value, 1);
            vista.getTablaProductos().setValueAt(pe.getPrecio(), i.value, 2);
            vista.getTablaProductos().setValueAt(pe.getCantidad(), i.value, 3);
            vista.getTablaProductos().setValueAt(pe.getDescripcion(), i.value, 4);
            
            
            Image foto=pe.getFoto();
            if(foto!=null){
            
                Image nimg= foto.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icono=new ImageIcon(nimg);
                DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();
                renderer.setIcon(icono);
                vista.getTablaProductos().setValueAt(new JLabel(icono), i.value, 5);
                
            }else{
                vista.getTablaProductos().setValueAt(null, i.value, 5);
            }
            
            i.value++;
//           String[] filap={pe.getIdPersona(),pe.getNombres(),pe.getApellidos(),"25"}; 
//           tblModel.addRow(filap);
        });
        
    }
    private void cancelar(){
        vista.getDialogoProducto().setVisible(false);
    }
    private void modificarProducto() {
        Productos persona = new Productos();
        DefaultTableModel tblProductos = (DefaultTableModel) vista.getTablaProductos().getModel();
        int fila = vista.getTablaProductos().getSelectedRow();
        if (fila != -1) {
           
            vista.getTxtId().setText(tblProductos.getValueAt(fila, 0).toString());
            vista.getTxtNombre().setText(tblProductos.getValueAt(fila, 1).toString());
            vista.getTxtPrecio().setText(tblProductos.getValueAt(fila, 2).toString());
            
            vista.getTxtCantidad().setText(tblProductos.getValueAt(fila, 3).toString());
            vista.getAreaDescripcion().setText(tblProductos.getValueAt(fila, 4).toString());
            
             JLabel lbl = (JLabel) tblProductos.getValueAt(fila, 5);
            vista.getLblFoto().setIcon(lbl.getIcon()); 
        } else {
            JOptionPane.showMessageDialog(vista, "DE PRIMERO CLICK ENCIMA EN ALGUNA PERSONA Y LUEGO EN EDITAR", "AVISO", 2);
        }

    }
     
}
