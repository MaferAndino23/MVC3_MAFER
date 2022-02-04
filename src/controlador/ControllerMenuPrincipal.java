/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.ModelPersona;
import modelo.ModelProductos;
import vista.viewMenuPrincipal;
import vista.viewPersonas;
import vista.viewProductos;

/**
 *
 * @author Equipo
 */
public class ControllerMenuPrincipal {
    viewMenuPrincipal vistaMenuPrincipal;
    //viewProductos vistaProductos;

    public ControllerMenuPrincipal(viewMenuPrincipal vista) {
        this.vistaMenuPrincipal = vista;
        vista.setVisible(true);
    }
    public void iniciaCotrol(){
        vistaMenuPrincipal.getMenuCrudPerso().addActionListener(l->crudPersonas());
        vistaMenuPrincipal.getMenuPersonasCrud().addActionListener(l->crudPersonas());
        vistaMenuPrincipal.getBtnpersona().addActionListener(l->crudPersonas());
        vistaMenuPrincipal.getBtnproductos().addActionListener(l->crudProductos());
        vistaMenuPrincipal.getMenuItemProductos().addActionListener(l->crudProductos());

    }
    private void crudPersonas(){
        //Instacio las clases de modelo y vista
         ModelPersona modeloCrudPersonas = new ModelPersona();
        viewPersonas vistaCrudPersonas = new viewPersonas();
        // viewProductos vistaCrudProductos= new viewProductos();
        //Agregar el frame personas al desk panel
        vistaMenuPrincipal.getDeskprincipal().add(vistaCrudPersonas);
        //vistaProductos.getDeskprincipal().add(vistaCrudProductos);
       ControlerPersona controladorCrudPersonas = new ControlerPersona(modeloCrudPersonas, vistaCrudPersonas);
       controladorCrudPersonas.iniciaControl(); //empezamos los escuchas a los eventos
    }
    private void crudProductos(){
        //Instacio las clases de modelo y vista
         ModelProductos modeloCrudProductos = new ModelProductos();
        
         viewProductos vistaCrudProductos= new viewProductos();
        //Agregar el frame personas al desk panel
        vistaMenuPrincipal.getDeskprincipal().add(vistaCrudProductos);
        //vistaProductos.getDeskprincipal().add(vistaCrudProductos);
       ControlerProductos controladorCrudProductos = new ControlerProductos( vistaCrudProductos, modeloCrudProductos);
       controladorCrudProductos.iniciaControl(); //empezamos los escuchas a los eventos
    }
}
