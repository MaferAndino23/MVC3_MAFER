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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleFactura;
import modelo.ModeloDetalleFactura;
import modelo.Productos;
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

    public static ArrayList<DetalleFactura> listaDetalleFactura = new ArrayList();

    ModeloDetalleFactura dbp = new ModeloDetalleFactura(); //inicializar llamar al objeto de la clase

    MouseListener mouse;

    public ControlerVentas(viewVentas vista, ModeloDetalleFactura modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        CargarDisponibilidad("");
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
            }
        };
        vista.getTxtbuscarPro().addKeyListener(kl);//
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
        //vista.getBtnBuscar().addActionListener(l -> vista2.setVisible(true));
    }

    public void suma() {
        int contar = vista.getTablaFactura().getRowCount();
        double suma = 0;
        for (int i = 0; i < contar; i++) {
            suma = suma + Double.parseDouble(vista.getTablaFactura().getValueAt(i, 4).toString());
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

//             if (!buscaProductoEnModelo (String.valueOf(lp.getId()), tblModel)) {
//                 
//                     
//                 
            tblModel.addRow(new Object[]{lp.getId() + "", lp.getNombre(), lp.getDescripcion(), buscaUltimaCantidad(String.valueOf(lp.getId()), tblModel), lp.getPrecio()});
//                 System.out.println(buscaUltimaCantidad(String.valueOf(lp.getId()),tblModel));
//                
//             }else{
            //tblModel.addRow(new Object[]{lp.getId() + "", lp.getNombre(), lp.getDescripcion(), buscaUltimaCantidad(String.valueOf(lp.getId()),tblModel), lp.getPrecio()});
            System.out.println("estoy en el false");
            // }

        });

        contador = 0;
        int codigo = codigo();
        if (vista.getTablaFactura().getRowCount() >= 1) {
            for (int i = 0; i < vista.getTablaFactura().getRowCount(); i++) {
                if (codigo == Integer.parseInt(vista.getTablaFactura().getValueAt(i, 0).toString())) {

                    System.out.println("codigo:" + codigo);
                    contador++;

                }
            }
        } else {
            contador = 1;

        }

        listapro.stream().forEach(lp -> {

//             if (!buscaProductoEnModelo (String.valueOf(lp.getId()), tblModel)) {
//                 
//                     
//                 
            tblModel.addRow(new Object[]{lp.getId() + "", lp.getNombre(), lp.getDescripcion(), contador, lp.getPrecio()});
//                 System.out.println(buscaUltimaCantidad(String.valueOf(lp.getId()),tblModel));
//                
//             }else{
            //tblModel.addRow(new Object[]{lp.getId() + "", lp.getNombre(), lp.getDescripcion(), buscaUltimaCantidad(String.valueOf(lp.getId()),tblModel), lp.getPrecio()});
            System.out.println("estoy en el false");
            // }

        });

        System.out.println("Cantidad:" + contador);
        //System.out.println(buscaUltimaCantidad(codigo, tblModel));
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

    private boolean buscaProductoEnModelo(String codigo, DefaultTableModel modelo) {
        if (codigo != null && modelo != null) {
            for (int renglon = 0; renglon < modelo.getRowCount(); renglon++) {
                String codigoEnRenglon = (String) modelo.getValueAt(renglon, 0);
                if (codigo.equals(codigoEnRenglon)) {
                    return true;
                }
            }

        }
        return false;
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

//    private int () {
//        if (String.valueOf(lp.getId()) != null && tblModel != null) {
//            for (int i = 0; i < tblModel.getRowCount(); i++) {
//                String codigo = (String) tblModel.getValueAt(i, 0);
//                //if (codigo >= 0 ) {
//                System.out.println(codigo);
//                //}
//            }
//        }
}
