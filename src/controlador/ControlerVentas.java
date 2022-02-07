/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleFactura;
import modelo.ModeloDetalleFactura;
import vista.viewVentas;

/**
 *
 * @author Equipo
 */
public class ControlerVentas {
     private viewVentas vista;
     private ModeloDetalleFactura modelo;
     private JFileChooser jfc;
     
     public static ArrayList<DetalleFactura> listaDetalleFactura = new ArrayList();

    ModeloDetalleFactura dbp = new ModeloDetalleFactura(); //inicializar llamar al objeto de la clase

    public ControlerVentas(viewVentas vista, ModeloDetalleFactura modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
    }
     public void iniciaControl() {
          //Estar a la escucha de todos los eventos de la vista 
        //boton actualizar
       // vista.getBtnActualizar().addActionListener(l -> cargarProductos());
        //vista.getBtnCrear().addActionListener(l -> abrirDialogo(1));
        //vista.getBtnEditar().addActionListener(l -> abrirDialogo(2));
        //vista.getBtnaceptar().addActionListener(l -> crearEditarProducto());
        //vista.getBtncancelar().addActionListener(l -> cancelar());
        //vista.getBtnRemover().addActionListener(l -> eliminar());
         vista.getBtn1().addActionListener(l->Botones());
     }
     public void ItemCost(){
         double sum=0;
         for (int i = 0; i < vista.getTablaFactura().getRowCount() ; i++) {
             sum = sum + Double.parseDouble( vista.getTablaFactura().getValueAt(i, 2).toString());
             
         }
         vista.getTxtSubtotal().setText(Double.toString(sum));
         double ctotal = Double.parseDouble(vista.getTxtSubtotal().getText());
         
         double iva =(ctotal*0.12)/100;
         String iIvaTotal = String.format("e %.2f", iva);
         vista.getTxtIva().setText(iIvaTotal);
         
         String iSubtotal = String.format("e %.2f", ctotal);
         vista.getTxtSubtotal().setText(iSubtotal);
         
         String total = String.format("e %.2f", ctotal+iva);
         vista.getTxtTotalPagar().setText(total);
         
     }
     public void Botones(){
         double boton =2.40;
         DefaultTableModel model =(DefaultTableModel)vista.getTablaFactura().getModel();
          model.addRow(new Object[]{"001","Cake","Chocolate","1",boton});//Creo una fila vacia/
          ItemCost();
     }

}
