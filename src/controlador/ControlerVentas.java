/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleFactura;
import modelo.ModelPersona;
import modelo.ModeloDetalleFactura;
import modelo.ModeloFactura;
import modelo.Persona;
import modelo.Productos;
import vista.viewMenuPrincipal;
import vista.viewPersonas;
import vista.viewProductos;
import vista.viewVentas;

/**
 *
 * @author Equipo
 */
public class ControlerVentas {

    private viewVentas vista;
    private viewPersonas vista2;
    private ModeloDetalleFactura modelo;
    private JFileChooser jfc;
    private ModeloFactura modeloFactura = new ModeloFactura();
    public static ArrayList<DetalleFactura> listaDetalleFactura = new ArrayList();

    ModeloDetalleFactura dbp = new ModeloDetalleFactura(); //inicializar llamar al objeto de la clase

    MouseListener mouse;

    public ControlerVentas(viewVentas vista, ModeloDetalleFactura modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        CargarDisponibilidad("");
        llenaridfactura();

    }

    public void iniciaControl() throws IOException {
        //Estar a la escucha de todos los eventos de la vista 
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                CargarDisponibilidad(vista.getTxtbuscarPro().getText());
                llenarCuadrosDialogoPersona(vista.getTxtBuscarCliente().getText());

            }
        };
        vista.getTxtbuscarPro().addKeyListener(kl);//
        vista.getTxtBuscarCliente().addKeyListener(kl);//
        CargaImagenenes();
        suma();

        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Contar(e);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        //vista.getBtnRemover().addActionListener(l -> eliminar());
        vista.getBtn1().addActionListener(l -> Botones(1));
        vista.getBtn2().addActionListener(l -> Botones(2));
        vista.getBtn3().addActionListener(l -> Botones(3));
        vista.getBtn4().addActionListener(l -> Botones(4));
        vista.getBtn5().addActionListener(l -> Botones(5));
        vista.getBtn6().addActionListener(l -> Botones(6));
        vista.getBtn7().addActionListener(l -> Botones(7));
        vista.getBtn8().addActionListener(l -> Botones(8));
        vista.getBtn9().addActionListener(l -> Botones(9));
        vista.getBtn11().addMouseListener(ml);
        vista.getBtnGeneraVenta().addActionListener(l -> GenerarVenta());
        vista.getBtnNuevo().addActionListener(l -> crearnuevaventa());
        
    }

    public void suma() {
        int contar = vista.getTablaFactura().getRowCount();
        double suma = 0;
        for (int i = 0; i < contar; i++) {
            suma = suma + Double.parseDouble(vista.getTablaFactura().getValueAt(i, 5).toString());
        }
        vista.getTxtSubtotal().setText(Double.toString(suma));

        double iva = (suma * 0.12);
        vista.getTxtIva().setText(Double.toString(iva));

        vista.getTxtTotalPagar().setText("" + (suma + iva));

    }
    int contador = 1;

    boolean a = false;

    public void Botones(int numero) {

        DefaultTableModel tblModel = (DefaultTableModel) vista.getTablaFactura().getModel();
        List<Productos> listapro = modelo.productitos(numero);
        listapro.stream().forEach(lp -> {
            if (RevisaCodigo(String.valueOf(lp.getId()), tblModel)) {
                tblModel.addRow(new Object[]{lp.getId() + "", lp.getNombre(), lp.getDescripcion(), "1", lp.getPrecio(), lp.getPrecio()});
            } else {
                for (int i = 0; i < tblModel.getRowCount(); i++) {
                    String codigo = tblModel.getValueAt(i, 0).toString();
                    if (String.valueOf(lp.getId()).equals(codigo)) {

                        double precio = Double.parseDouble(tblModel.getValueAt(i, 4).toString());
                        int numerito = Integer.parseInt(tblModel.getValueAt(i, 3).toString());
                        numerito++;
                        double total = numerito * precio;
                        tblModel.setValueAt(numerito, i, 3);
                        tblModel.setValueAt(total, i, 5);
                        tblModel.fireTableRowsUpdated(i, i);

                    }
                }
            }

        });

        suma();
    }

    private int conteo_clicks = 0;

    public void Contar(MouseEvent mouseEvent) {
        conteo_clicks++;

    }

    public Icon imagenDBB(int numero) throws IOException {
        Image imagen = modelo.obtenerImagen(modelo.imagenDeSql(numero));
        Icon icon = new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH));//aki cambias tamano 
        return icon;
    }

    public void CargaImagenenes() throws IOException {

        vista.getBtn1().setIcon(imagenDBB(1));
        vista.getBtn2().setIcon(imagenDBB(2));
        vista.getBtn3().setIcon(imagenDBB(3));
        vista.getBtn4().setIcon(imagenDBB(4));
        vista.getBtn5().setIcon(imagenDBB(5));
        vista.getBtn6().setIcon(imagenDBB(6));
        vista.getBtn7().setIcon(imagenDBB(7));
        vista.getBtn8().setIcon(imagenDBB(8));
        vista.getBtn9().setIcon(imagenDBB(9));

    }

    public void CargarDisponibilidad(String busqueda) {
        DefaultTableModel tblModel = (DefaultTableModel) vista.getTablapro().getModel();
        tblModel.setNumRows(0);//limpiar campos
        List<Productos> listapro = modelo.productitos(busqueda);
        listapro.stream().forEach(lp -> {
            String[] productos = {lp.getId() + "", lp.getNombre(), lp.getCantidad() + ""};
            tblModel.addRow(productos);

        });

    }

    private boolean RevisaCodigo(String codigo, DefaultTableModel modelo) {
        if (codigo != null && modelo != null) {
            for (int renglon = 0; renglon < modelo.getRowCount(); renglon++) {
                String codigoEnRenglon = (String) modelo.getValueAt(renglon, 0);
                if (codigo.equals(codigoEnRenglon)) {
                    return false;
                }
            }

        }
        return true;
    }

    private int codigo() {

        int codigoproducto = Integer.parseInt(vista.getTablaFactura().getValueAt(vista.getTablaFactura().getRowCount() - 1, 0).toString());
        System.out.println(codigoproducto);

        return codigoproducto;
    }

    private int buscaUltimaCantidad(String codigo, DefaultTableModel modelo) {
        int cuentame = 1;
        if (codigo != null && modelo != null) {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String codigoEnRenglon = (String) modelo.getValueAt(i, 0);
                if (codigo.equals(codigoEnRenglon)) {
                    return cuentame++;
                }
            }
        }
        return cuentame++;
    }

    private void llenarCuadrosDialogoPersona(String cadenaBusqueda) {
        List<Persona> listaPersona = modelo.buscarpersona(cadenaBusqueda);

        listaPersona.stream().forEach(p -> {
            String[] persona = {p.getNombres(), p.getApellidos(),
                String.valueOf(p.getTelefono())};

            vista.getLblnombre().setText(persona[0]);
            vista.getLblapellido().setText(persona[1]);
            vista.getLbltelefono().setText(persona[2]);

        });
    }

    private void llenaridfactura() {
        vista.getLblFactura().setText(modelo.contar() + "");
    }

    private void crearFactura() {
        modeloFactura.setId(Integer.parseInt(vista.getLblFactura().getText()));
        modeloFactura.setFecha(((JTextField) vista.getDatefecha().getDateEditor().getUiComponent()).getText());
        modeloFactura.setTotal(Double.parseDouble(vista.getTxtTotalPagar().getText()));
        modeloFactura.setCliente(vista.getTxtBuscarCliente().getText());
        if (modeloFactura.crearFactura()) {
            crear();
            JOptionPane.showMessageDialog(vista, "Factura Creada Con Satisfaccion");
        } else {
            JOptionPane.showMessageDialog(vista, "Valiendo Gasver");
        }

    }

    public void crear() {
        ModeloDetalleFactura modelodeta = new ModeloDetalleFactura();
        DefaultTableModel tblModel = (DefaultTableModel) vista.getTablaFactura().getModel();
        //List<Productos> listapro = modelo.productitos(numero);
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            modelodeta.setFactura(Integer.parseInt(vista.getLblFactura().getText()));
            modelodeta.setId(Integer.parseInt(vista.getLblFactura().getText()));
            modelodeta.setProducto(Integer.parseInt(tblModel.getValueAt(i, 0).toString()));
            modelodeta.setCantidad(Integer.parseInt(tblModel.getValueAt(i, 3).toString()));
            modelodeta.setPrecio(Double.parseDouble(tblModel.getValueAt(i, 4).toString()));
            modelodeta.setTotal(Double.parseDouble(tblModel.getValueAt(i, 5).toString()));
            if (modelodeta.creardetallefactura()) {
                //JOptionPane.showMessageDialog(vista, "Factura Creada Con Satisfaccion");
                //stock();
            } else {
                JOptionPane.showMessageDialog(vista, "Valiendo Gasver");
            }

        }
    }

    private void stock() {
        DefaultTableModel tblModel = (DefaultTableModel) vista.getTablaFactura().getModel(); 
        ModeloDetalleFactura modelodeta = new ModeloDetalleFactura();
        
        for (int i = 0; i < tblModel.getRowCount(); i++) {
            int count=modelodeta.buscameid(Integer.parseInt(tblModel.getValueAt(i, 0).toString()));
            int cantidad=Integer.parseInt(tblModel.getValueAt(i, 3).toString());
            int resta=count-cantidad; 
            //System.out.println("La cantidad es: "+count+"  La cantidad comprada es: " +cantidad+" y me voy a guardar como: "+resta);
            modelodeta.setCantidad(resta);
            modelodeta.setProducto(Integer.parseInt(tblModel.getValueAt(i, 0).toString()));
            if (modelodeta.restame()) {
                
            }else {
                JOptionPane.showMessageDialog(vista, "Valiendo Gasver");
            }

        }
        
     }

    private void GenerarVenta() {
        crearFactura();
        stock();
        CargarDisponibilidad("");
    }

//    public void actualizar() {
//        limpiarCampos();
//        crearFactura();
//        
//    }
    public void crearnuevaventa(){
        llenaridfactura();
        limpiarCampos();
    }

    //LIMPIAR CAMPOS
    private void limpiarCampos() {
        vista.getLblnombre().setText(null);
        vista.getLblapellido().setText(null);
        vista.getLbltelefono().setText(null);
        vista.getDatefecha().setDate(null);
        vista.getTxtBuscarCliente().setText(null);
        vista.getTxtIva().setText(null);
        vista.getTxtSubtotal().setText(null);
        vista.getTxtTotalPagar().setText(null);
        DefaultTableModel tblModel = (DefaultTableModel) vista.getTablaFactura().getModel();
        tblModel.setNumRows(0);//limpiar campos
    }

}
