/*
 * RolIFrame.java
 *
 * Created on 04/08/2009, 17:09:11
 */
package edu.utn.frm.ui.usuario;

import edu.utn.frm.action.exception.ReporteException;
import edu.utn.frm.action.usuario.RolAbm;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.entities.usuario.Privilegio;
import edu.utn.frm.entities.usuario.Rol;
import edu.utn.frm.ui.IconManager;
import edu.utn.frm.ui.model.JComponentCellEditor;
import edu.utn.frm.ui.model.JComponentCellRenderer;
import edu.utn.frm.ui.model.TableRecorder;
import javax.swing.JOptionPane;


import edu.utn.frm.ui.utils.GenericTableModel;
import edu.utn.frm.ui.utils.Utiles;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Proyecto
 */
public class RolIFrame extends javax.swing.JInternalFrame {

    private GenericTableModel<Rol> model = new GenericTableModel<Rol>(
            new String[]{"Código", "Descripción"}, new String[]{"codigo", "descripcion"});
    private RolAbm gestorAbm = new RolAbm();
    private PrivilegioModel privilegioModel;

    /** Creates new form RolIFrame */
    public RolIFrame() {
        initComponents();
        habilitarPanelDatos(false);
        model.setRows(gestorAbm.buscar());
        this.setFrameIcon(IconManager.getInstance().getFrameIcon());
        //sPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(" Giros Comerciales [" + model.getRowCount() + "] "));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        datosDialog = new javax.swing.JDialog();
        datosPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        codigoText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descripcionText = new javax.swing.JTextField();
        limpiarDatosEntityButton = new javax.swing.JButton();
        sPanel1 = new javax.swing.JScrollPane();
        tablaPrivilegios = tablaPrivilegios = new TableRecorder();
        ;
        cerrarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        sPanel = new javax.swing.JScrollPane();
        buscarTabla = new javax.swing.JTable();
        salirButton = new javax.swing.JButton();
        buscarCodigoText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscarButton = new javax.swing.JButton();
        limpiarBuscadorButton = new javax.swing.JButton();
        nuevoButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        buscarDescripcionText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        copiarButton = new javax.swing.JButton();
        generarInformeButton1 = new javax.swing.JButton();

        datosDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        datosDialog.setTitle("Rol de Usuario");
        datosDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        datosDialog.setLocationByPlatform(true);
        datosDialog.setMinimumSize(new java.awt.Dimension(562, 400));
        datosDialog.setModal(true);
        datosDialog.setName("datosDialog"); // NOI18N
        datosDialog.setResizable(false);
        datosDialog.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosed(java.awt.event.WindowEvent evt) {
                datosDialog_windowClosed(evt);
            }
        });

        datosPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Rol"));
        datosPanel1.setName("datosPanel1"); // NOI18N

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel2.setText("Código:");
        jLabel2.setName("jLabel2"); // NOI18N

        codigoText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        codigoText.setName("txtTPatente"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${entity.codigo}"), codigoText, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel3.setText("Descripción:");
        jLabel3.setName("jLabel3"); // NOI18N

        descripcionText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        descripcionText.setName("txtTModelo"); // NOI18N

        limpiarDatosEntityButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/limpiar.png"))); // NOI18N
        limpiarDatosEntityButton.setText("Limpiar");
        limpiarDatosEntityButton.setName("limpiarDatosEntityButton"); // NOI18N
        limpiarDatosEntityButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarDatosEntityButtonActionPerformed(evt);
            }
        });

        sPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Privilegios"));
        sPanel1.setName("sPanel1"); // NOI18N

        tablaPrivilegios.setAutoCreateRowSorter(true);
        tablaPrivilegios.setModel(model);
        tablaPrivilegios.setName("tablaPrivilegios"); // NOI18N
        tablaPrivilegios.setDefaultRenderer(JComponent.class, new JComponentCellRenderer());
        tablaPrivilegios.setDefaultEditor(JComponent.class, new JComponentCellEditor());
        sPanel1.setViewportView(tablaPrivilegios);

        javax.swing.GroupLayout datosPanel1Layout = new javax.swing.GroupLayout(datosPanel1);
        datosPanel1.setLayout(datosPanel1Layout);
        datosPanel1Layout.setHorizontalGroup(
                datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(codigoText, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(descripcionText, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE).addGap(23, 23, 23).addComponent(limpiarDatosEntityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addContainerGap().addComponent(sPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE).addContainerGap())));
        datosPanel1Layout.setVerticalGroup(
                datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addContainerGap().addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(limpiarDatosEntityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(descripcionText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2).addComponent(codigoText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel3)).addContainerGap(249, Short.MAX_VALUE)).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosPanel1Layout.createSequentialGroup().addContainerGap(46, Short.MAX_VALUE).addComponent(sPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))));

        cerrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/cerrar.png"))); // NOI18N
        cerrarButton.setText("Cerrar");
        cerrarButton.setName("cerrarButton"); // NOI18N
        cerrarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarButtonActionPerformed(evt);
            }
        });

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/guardar.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        guardarButton.setName("guardarButton"); // NOI18N
        guardarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar(evt);
            }
        });

        eliminarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/eliminar.png"))); // NOI18N
        eliminarButton.setText("Eliminar");
        eliminarButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eliminarButton.setName("eliminarButton"); // NOI18N
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar(evt);
            }
        });

        javax.swing.GroupLayout datosDialogLayout = new javax.swing.GroupLayout(datosDialog.getContentPane());
        datosDialog.getContentPane().setLayout(datosDialogLayout);
        datosDialogLayout.setHorizontalGroup(
                datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosDialogLayout.createSequentialGroup().addContainerGap().addGroup(datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(datosPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(datosDialogLayout.createSequentialGroup().addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        datosDialogLayout.setVerticalGroup(
                datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosDialogLayout.createSequentialGroup().addContainerGap().addComponent(datosPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

        setIconifiable(true);
        setTitle("Rol");

        sPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Roles"));
        sPanel.setName("sPanel"); // NOI18N

        buscarTabla.setAutoCreateRowSorter(true);
        buscarTabla.setModel(model);
        buscarTabla.setName("buscarTable"); // NOI18N
        buscarTabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarTablaMouseClicked(evt);
            }
        });
        sPanel.setViewportView(buscarTabla);

        salirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/cerrar.png"))); // NOI18N
        salirButton.setText("Cerrar");
        salirButton.setName("salirButton"); // NOI18N
        salirButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        buscarCodigoText.setName("buscarCodigoText"); // NOI18N

        jLabel1.setText("Descripción");
        jLabel1.setName("jLabel1"); // NOI18N

        buscarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/buscar.png"))); // NOI18N
        buscarButton.setText("Buscar");
        buscarButton.setName("buscarButton"); // NOI18N
        buscarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        limpiarBuscadorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/limpiar.png"))); // NOI18N
        limpiarBuscadorButton.setText("Limpiar");
        limpiarBuscadorButton.setName("limpiarBuscarButton"); // NOI18N
        limpiarBuscadorButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBuscadorButtonActionPerformed(evt);
            }
        });

        nuevoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/agregar.png"))); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nuevoButton.setName("nuevoButton"); // NOI18N
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevo(evt);
            }
        });

        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/editar.png"))); // NOI18N
        editarButton.setText("Editar");
        editarButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        editarButton.setName("editarButton"); // NOI18N
        editarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonnuevo(evt);
            }
        });

        buscarDescripcionText.setName("buscarDescripcionText"); // NOI18N

        jLabel5.setText("Código");
        jLabel5.setName("jLabel5"); // NOI18N

        copiarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/copiar.png"))); // NOI18N
        copiarButton.setText("Copiar");
        copiarButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        copiarButton.setName("copiarButton"); // NOI18N
        copiarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarButton(evt);
            }
        });

        generarInformeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/informe.png"))); // NOI18N
        generarInformeButton1.setText("Generar Informe");
        generarInformeButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        generarInformeButton1.setName("generarInformeButton1"); // NOI18N
        generarInformeButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarInformeButton1(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(sPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(nuevoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(generarInformeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE).addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(buscarCodigoText, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel5)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(buscarDescripcionText, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(128, 128, 128).addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE).addComponent(limpiarBuscadorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel5).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(buscarCodigoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(buscarDescripcionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(layout.createSequentialGroup().addGap(22, 22, 22).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(limpiarBuscadorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(6, 6, 6).addComponent(sPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(nuevoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(generarInformeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevo
        nuevo();
    }//GEN-LAST:event_nuevo

    private void buscarTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarTablaMouseClicked
        if (evt.getClickCount() > 1) {
            editar();
        }
    }//GEN-LAST:event_buscarTablaMouseClicked

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        dispose();
    }//GEN-LAST:event_salirButtonActionPerformed

    private void guardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar
        guardar();
    }//GEN-LAST:event_guardar

    private void eliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar

        eliminar();
    }//GEN-LAST:event_eliminar

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        gestorAbm.getFilter().setCodigo(buscarCodigoText.getText());
        gestorAbm.getFilter().setDescripcion(buscarDescripcionText.getText());
        model.setRows(gestorAbm.buscar());
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void limpiarBuscadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBuscadorButtonActionPerformed
        buscarCodigoText.setText("");
        buscarDescripcionText.setText("");
        gestorAbm.getFilter().clear();
    }//GEN-LAST:event_limpiarBuscadorButtonActionPerformed

    private void limpiarDatosEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarDatosEntityButtonActionPerformed
        codigoText.setText("");
        descripcionText.setText("");
    }//GEN-LAST:event_limpiarDatosEntityButtonActionPerformed

    private void cerrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarButtonActionPerformed
        cerrar();
    }//GEN-LAST:event_cerrarButtonActionPerformed

    private void datosDialog_windowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_datosDialog_windowClosed
        cerrar();
    }//GEN-LAST:event_datosDialog_windowClosed

    private void editarButtonnuevo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonnuevo
        editar();
    }//GEN-LAST:event_editarButtonnuevo

    private void copiarButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarButton
        copiar();
    }//GEN-LAST:event_copiarButton

    private void generarInformeButton1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarInformeButton1
        try {
            gestorAbm.ejecutarReporte();
        } catch (ReporteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al generar el reporte", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generarInformeButton1
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField buscarCodigoText;
    private javax.swing.JTextField buscarDescripcionText;
    private javax.swing.JTable buscarTabla;
    private javax.swing.JButton cerrarButton;
    private javax.swing.JTextField codigoText;
    private javax.swing.JButton copiarButton;
    private javax.swing.JDialog datosDialog;
    private javax.swing.JPanel datosPanel1;
    private javax.swing.JTextField descripcionText;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton generarInformeButton1;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton limpiarBuscadorButton;
    private javax.swing.JButton limpiarDatosEntityButton;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JScrollPane sPanel;
    private javax.swing.JScrollPane sPanel1;
    private javax.swing.JButton salirButton;
    private javax.swing.JTable tablaPrivilegios;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void cerrar() {
        reset();
        showDatosDialog(false);
    }

    private void nuevo() {
        gestorAbm.nuevo();
        bindinDataEntity(servidor);
        habilitarPanelDatos(true);
        eliminarButton.setEnabled(false);
        showDatosDialog(true);
    }

    private void editar() {
        Rol rol = null;

        if (buscarTabla.getSelectedRow() != -1) {
            rol = model.getRow(buscarTabla.getSelectedRow());
            if (rol != null) {
                gestorAbm.setEntity(rol);
                bindinDataEntity(servidor);
                habilitarPanelDatos(true);
                showDatosDialog(true);
            }
        }
        if (rol == null) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un Rol para editar");
        }
    }

    private void copiar() {
        Rol rol = null;

        if (buscarTabla.getSelectedRow() != -1) {
            rol = model.getRow(buscarTabla.getSelectedRow());
            if (rol != null) {
                gestorAbm.setEntity(rol);
                try {
                    gestorAbm.copiar();
                    reset();
                    JOptionPane.showMessageDialog(this, "Se ha copiado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        if (rol == null) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un Rol para copiar");
        }
    }

    private void guardar() {
        bindinDataEntity(cliente);
        try {
            gestorAbm.valid();
            gestorAbm.guardar();
            reset();
            showDatosDialog(false);
        } catch (ValidateException ex) {
            JOptionPane.showMessageDialog(datosDialog, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(datosDialog, ex.getMessage());
        }
    }

    private void showDatosDialog(boolean show) {
        if (show) {
            Utiles.centrarFormulario(datosDialog);
        }
        datosDialog.setVisible(show);
    }

    private void eliminar() {
        Rol rol = null;

        try {
            if (buscarTabla.getSelectedRow() != -1) {
                rol = model.getRow(buscarTabla.getSelectedRow());
                if (rol != null) {

                    int respuesta = JOptionPane.showConfirmDialog(datosDialog, "Está seguro que desea eliminar el Rol seleccionado?",
                            "Confirmar Eliminación", JOptionPane.OK_CANCEL_OPTION);

                    if (respuesta == JOptionPane.OK_OPTION) {
                        gestorAbm.setEntity(rol);
                        gestorAbm.eliminar();
                        reset();
                        showDatosDialog(false);
                    }

                }
            }
            if (rol == null) {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un Rol para eliminar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void reset() {
        codigoText.setText("");
        descripcionText.setText("");
        setEntity(null);
        habilitarPanelDatos(false);
        model.setRows(gestorAbm.buscar());
    }

    public RolAbm getGestorAbm() {
        return gestorAbm;
    }

    public void setGestorAbm(RolAbm gestorAbm) {
        this.gestorAbm = gestorAbm;
    }

    public Rol getEntity() {
        return gestorAbm.getEntity();
    }

    public void setEntity(Rol entity) {
        this.gestorAbm.setEntity(entity);
    }

    private void habilitarPanelDatos(boolean habilitar) {
        codigoText.setEnabled(habilitar);
        descripcionText.setEnabled(habilitar);
        cerrarButton.setEnabled(habilitar);
        guardarButton.setEnabled(habilitar);
        eliminarButton.setEnabled(habilitar);
        limpiarDatosEntityButton.setEnabled(habilitar);
        nuevoButton.setEnabled(true);
    }
    private final int cliente = 0;
    private final int servidor = 1;

    public void bindinDataEntity(int fuente) {
        if (fuente == servidor) {
            codigoText.setText(getEntity().getCodigo());
            descripcionText.setText(getEntity().getDescripcion());
            privilegioModel = new PrivilegioModel(buscarTodosPrivilegios(), true);
            tablaPrivilegios.setModel(privilegioModel);
        } else if (fuente == cliente) {
            getEntity().setCodigo(codigoText.getText());
            getEntity().setDescripcion(descripcionText.getText());
            getEntity().setPrivilegios(privilegioModel.getSeleccionados());
        }
    }

    public List<Privilegio> buscarTodosPrivilegios() {
        List<Privilegio> privilegios = gestorAbm.buscarTodosPrivilegios();

        for (Privilegio privilegio : privilegios) {
            privilegio.setSeleccionado(false);
        }

        for (Privilegio privilegio : getEntity().getPrivilegios()) {
            int index = privilegios.indexOf(privilegio);
            if (index >= 0) {
                Privilegio privilegioItem = (Privilegio) privilegios.get(index);
                privilegioItem.setSeleccionado(true);
            }
        }

        return privilegios;
    }
}
