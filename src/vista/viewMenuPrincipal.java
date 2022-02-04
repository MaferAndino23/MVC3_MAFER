/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Equipo
 */
public class viewMenuPrincipal extends javax.swing.JFrame {

    /** Creates new form viewMenuPrincipal */
    public viewMenuPrincipal() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnpersona = new javax.swing.JButton();
        btnlistapersona = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnproductos = new javax.swing.JButton();
        lblMensajePrincipal = new javax.swing.JLabel();
        deskprincipal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCrudPerso = new javax.swing.JMenu();
        menuPersonasCrud = new javax.swing.JMenuItem();
        mnuListaPersonas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuItemProductos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnpersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592854-add-user-business-man-employee-general-human-member-office_107767.png"))); // NOI18N
        btnpersona.setToolTipText("Crear Persona(Alt+P)");
        btnpersona.setFocusable(false);
        btnpersona.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnpersona.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnpersona);

        btnlistapersona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592857-burger-general-ham-list-menu-menu-icon-office_107735.png"))); // NOI18N
        btnlistapersona.setToolTipText("Listado de Personas");
        btnlistapersona.setFocusable(false);
        btnlistapersona.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnlistapersona.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnlistapersona);
        jToolBar1.add(jSeparator1);

        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592869-compose-create-edit-edit-file-office-pencil-writing-creative_107746.png"))); // NOI18N
        btnproductos.setToolTipText("Mantenimiento de Productos");
        btnproductos.setFocusable(false);
        btnproductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnproductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnproductos);

        lblMensajePrincipal.setText("Mi Tienda 1.0");

        javax.swing.GroupLayout deskprincipalLayout = new javax.swing.GroupLayout(deskprincipal);
        deskprincipal.setLayout(deskprincipalLayout);
        deskprincipalLayout.setHorizontalGroup(
            deskprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        deskprincipalLayout.setVerticalGroup(
            deskprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton1)
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuCrudPerso.setText("Personas");

        menuPersonasCrud.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        menuPersonasCrud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592854-add-user-business-man-employee-general-human-member-office_107767.png"))); // NOI18N
        menuPersonasCrud.setText("Crear Personas");
        menuCrudPerso.add(menuPersonasCrud);

        mnuListaPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592857-burger-general-ham-list-menu-menu-icon-office_107735.png"))); // NOI18N
        mnuListaPersonas.setText("Lista Personas");
        menuCrudPerso.add(mnuListaPersonas);

        jMenuBar1.add(menuCrudPerso);

        jMenu2.setText("Productos");

        MenuItemProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        MenuItemProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/icons/3592869-compose-create-edit-edit-file-office-pencil-writing-creative_107746.png"))); // NOI18N
        MenuItemProductos.setText("Crear Productos");
        MenuItemProductos.setToolTipText("Crear Productos(Alt+C)");
        jMenu2.add(MenuItemProductos);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ventas");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reportes");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ayuda");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addComponent(lblMensajePrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deskprincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deskprincipal)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensajePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public JMenuItem getMenuItemProductos() {
        return MenuItemProductos;
    }

    public void setMenuItemProductos(JMenuItem MenuItemProductos) {
        this.MenuItemProductos = MenuItemProductos;
    }

    public JButton getBtnlistapersona() {
        return btnlistapersona;
    }

    public void setBtnlistapersona(JButton btnlistapersona) {
        this.btnlistapersona = btnlistapersona;
    }

    public JButton getBtnpersona() {
        return btnpersona;
    }

    public void setBtnpersona(JButton btnpersona) {
        this.btnpersona = btnpersona;
    }

    public JButton getBtnproductos() {
        return btnproductos;
    }

    public void setBtnproductos(JButton btnproductos) {
        this.btnproductos = btnproductos;
    }

    public JDesktopPane getDeskprincipal() {
        return deskprincipal;
    }

    public void setDeskprincipal(JDesktopPane deskprincipal) {
        this.deskprincipal = deskprincipal;
    }

    public JLabel getLblMensajePrincipal() {
        return lblMensajePrincipal;
    }

    public void setLblMensajePrincipal(JLabel lblMensajePrincipal) {
        this.lblMensajePrincipal = lblMensajePrincipal;
    }

    public JMenu getMenuCrudPerso() {
        return menuCrudPerso;
    }

    public void setMenuCrudPerso(JMenu menuCrudPerso) {
        this.menuCrudPerso = menuCrudPerso;
    }

    public JMenuItem getMenuPersonasCrud() {
        return menuPersonasCrud;
    }

    public void setMenuPersonasCrud(JMenuItem menuPersonasCrud) {
        this.menuPersonasCrud = menuPersonasCrud;
    }

    public JMenuItem getMnuListaPersonas() {
        return mnuListaPersonas;
    }

    public void setMnuListaPersonas(JMenuItem mnuListaPersonas) {
        this.mnuListaPersonas = mnuListaPersonas;
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemProductos;
    private javax.swing.JButton btnlistapersona;
    private javax.swing.JButton btnpersona;
    private javax.swing.JButton btnproductos;
    private javax.swing.JDesktopPane deskprincipal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblMensajePrincipal;
    private javax.swing.JMenu menuCrudPerso;
    private javax.swing.JMenuItem menuPersonasCrud;
    private javax.swing.JMenuItem mnuListaPersonas;
    // End of variables declaration//GEN-END:variables

}
