/*
 * CamionIFrame.java
 *
 * Created on 04/08/2009, 17:09:11
 */
package edu.utn.frm.ui.producto;

import edu.utn.frm.action.exception.ReporteException;
import edu.utn.frm.action.producto.CamionAbm;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.entities.producto.Camion;
import edu.utn.frm.ui.IconManager;
import javax.swing.JOptionPane;


import edu.utn.frm.ui.utils.GenericTableModel;
import edu.utn.frm.ui.utils.Utiles;

/**
 *
 * @author Proyecto
 */
public class CamionIFrame extends javax.swing.JInternalFrame {

    private GenericTableModel<Camion> model = new GenericTableModel<Camion>(
            new String[]{"Patente", "Marca", "Capacidad", "Modelo", "Descripcion"},
            new String[]{"patente", "marca", "capacidadKilos", "modelo", "descripcion"});
    private CamionAbm gestorAbm = new CamionAbm();

    /** Creates new form GrupoTanqueIFrame */
    public CamionIFrame() {
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
        patenteText = new javax.swing.JTextField();
        capacidadText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        marcaText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        modeloText = new javax.swing.JTextField();
        descripcionText = new javax.swing.JTextField();
        limpiarDatosEntityButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cerrarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        sPanel = new javax.swing.JScrollPane();
        buscarTabla = new javax.swing.JTable();
        salirButton = new javax.swing.JButton();
        buscarPatenteText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscarButton = new javax.swing.JButton();
        limpiarBuscadorButton = new javax.swing.JButton();
        nuevoButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        buscarMarcaText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        copiarButton = new javax.swing.JButton();
        generarInformeButton = new javax.swing.JButton();

        datosDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        datosDialog.setTitle("Camión");
        datosDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        datosDialog.setLocationByPlatform(true);
        datosDialog.setMinimumSize(new java.awt.Dimension(490, 260));
        datosDialog.setModal(true);
        datosDialog.setName("datosDialog"); // NOI18N
        datosDialog.setResizable(false);
        datosDialog.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosed(java.awt.event.WindowEvent evt) {
                datosDialog_windowClosed(evt);
            }
        });

        datosPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Camión"));
        datosPanel1.setName("datosPanel1"); // NOI18N

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel2.setText("Patente:");
        jLabel2.setName("jLabel2"); // NOI18N

        patenteText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        patenteText.setName("txtTPatente"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${entity.codigo}"), patenteText, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        capacidadText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        capacidadText.setName("txtTCapacidad"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${entity.detalle}"), capacidadText, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel6.setText("Capacidad:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Marca:");
        jLabel7.setName("jLabel7"); // NOI18N

        marcaText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        marcaText.setName("txtTMarca"); // NOI18N

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel4.setText("Descripción:");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() & ~java.awt.Font.BOLD));
        jLabel3.setText("Modelo:");
        jLabel3.setName("jLabel3"); // NOI18N

        modeloText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        modeloText.setName("txtTModelo"); // NOI18N

        descripcionText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        descripcionText.setName("txtTDescripcion"); // NOI18N

        limpiarDatosEntityButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/limpiar.png"))); // NOI18N
        limpiarDatosEntityButton.setText("Limpiar");
        limpiarDatosEntityButton.setName("limpiarDatosEntityButton"); // NOI18N
        limpiarDatosEntityButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarDatosEntityButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Kgs");
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout datosPanel1Layout = new javax.swing.GroupLayout(datosPanel1);
        datosPanel1.setLayout(datosPanel1Layout);
        datosPanel1Layout.setHorizontalGroup(
                datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addGap(13, 13, 13).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel3).addComponent(jLabel2).addComponent(jLabel6))).addGroup(datosPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(descripcionText, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(datosPanel1Layout.createSequentialGroup().addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(modeloText).addComponent(patenteText, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE).addComponent(capacidadText)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(marcaText, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel8)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(limpiarDatosEntityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        datosPanel1Layout.setVerticalGroup(
                datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosPanel1Layout.createSequentialGroup().addContainerGap().addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(patenteText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2).addComponent(jLabel7).addComponent(marcaText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(limpiarDatosEntityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(modeloText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(capacidadText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6).addComponent(jLabel8)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(datosPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(descripcionText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
                datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosDialogLayout.createSequentialGroup().addContainerGap().addGroup(datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(datosPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosDialogLayout.createSequentialGroup().addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        datosDialogLayout.setVerticalGroup(
                datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(datosDialogLayout.createSequentialGroup().addContainerGap().addComponent(datosPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(datosDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        setIconifiable(true);
        setTitle("Camión");

        sPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Camiones"));
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

        buscarPatenteText.setName("buscarPatenteText"); // NOI18N

        jLabel1.setText("Marca");
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

        buscarMarcaText.setName("buscarMarcaText"); // NOI18N

        jLabel5.setText("Patente");
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

        generarInformeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/informe.png"))); // NOI18N
        generarInformeButton.setText("Generar Informe");
        generarInformeButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        generarInformeButton.setName("generarInformeButton"); // NOI18N
        generarInformeButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarInformeButton(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(sPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(nuevoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(generarInformeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE).addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(13, 13, 13).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(buscarPatenteText, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel5)).addGap(10, 10, 10).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(buscarMarcaText, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE).addComponent(limpiarBuscadorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jLabel5)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(buscarPatenteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(buscarMarcaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(limpiarBuscadorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(nuevoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(editarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(copiarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(generarInformeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

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
        gestorAbm.getFilter().setPatente(buscarPatenteText.getText());
        gestorAbm.getFilter().setMarca(buscarMarcaText.getText());
        model.setRows(gestorAbm.buscar());
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void limpiarBuscadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBuscadorButtonActionPerformed
        buscarPatenteText.setText("");
        buscarMarcaText.setText("");
        gestorAbm.getFilter().clear();
    }//GEN-LAST:event_limpiarBuscadorButtonActionPerformed

    private void limpiarDatosEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarDatosEntityButtonActionPerformed
        patenteText.setText("");
        capacidadText.setText("");
        modeloText.setText("");
        marcaText.setText("");
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

    private void generarInformeButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarInformeButton
        try {
            gestorAbm.ejecutarReporte();
        } catch (ReporteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al generar el reporte", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generarInformeButton
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField buscarMarcaText;
    private javax.swing.JTextField buscarPatenteText;
    private javax.swing.JTable buscarTabla;
    private javax.swing.JTextField capacidadText;
    private javax.swing.JButton cerrarButton;
    private javax.swing.JButton copiarButton;
    private javax.swing.JDialog datosDialog;
    private javax.swing.JPanel datosPanel1;
    private javax.swing.JTextField descripcionText;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton generarInformeButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton limpiarBuscadorButton;
    private javax.swing.JButton limpiarDatosEntityButton;
    private javax.swing.JTextField marcaText;
    private javax.swing.JTextField modeloText;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTextField patenteText;
    private javax.swing.JScrollPane sPanel;
    private javax.swing.JButton salirButton;
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
        Camion camion = null;

        if (buscarTabla.getSelectedRow() != -1) {
            camion = model.getRow(buscarTabla.getSelectedRow());
            if (camion != null) {
                gestorAbm.setEntity(camion);
                bindinDataEntity(servidor);
                habilitarPanelDatos(true);
                showDatosDialog(true);
            }
        }
        if (camion == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Camión para editar");
        }
    }

    private void copiar() {
        Camion camion = null;

        if (buscarTabla.getSelectedRow() != -1) {
            camion = model.getRow(buscarTabla.getSelectedRow());
            if (camion != null) {
                gestorAbm.setEntity(camion);
                try {
                    gestorAbm.copiar();
                    reset();
                    JOptionPane.showMessageDialog(this, "Se ha copiado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        if (camion == null) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un Camión para copiar");
        }
    }

    private void guardar() {

        try {
            new Double(capacidadText.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(datosDialog, "La capacidad tiene un formato incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            new Integer(modeloText.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(datosDialog, "El modelo tiene un formato incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
        Camion camion = null;

        try {
            if (buscarTabla.getSelectedRow() != -1) {
                camion = model.getRow(buscarTabla.getSelectedRow());
                if (camion != null) {

                    int respuesta = JOptionPane.showConfirmDialog(datosDialog, "Está seguro que desea eliminar el Camión seleccionado?",
                            "Confirmar Eliminación", JOptionPane.OK_CANCEL_OPTION);

                    if (respuesta == JOptionPane.OK_OPTION) {
                        gestorAbm.setEntity(camion);
                        gestorAbm.eliminar();
                        reset();
                        showDatosDialog(false);
                    }

                }
            }
            if (camion == null) {
                JOptionPane.showInternalMessageDialog(this, "Debe seleccionar un Camión para eliminar");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void reset() {
        patenteText.setText("");
        capacidadText.setText("");
        modeloText.setText("");
        marcaText.setText("");
        descripcionText.setText("");
        setEntity(null);
        habilitarPanelDatos(false);
        model.setRows(gestorAbm.buscar());
    }

    public CamionAbm getGestorAbm() {
        return gestorAbm;
    }

    public void setGestorAbm(CamionAbm gestorAbm) {
        this.gestorAbm = gestorAbm;
    }

    public Camion getEntity() {
        return gestorAbm.getEntity();
    }

    public void setEntity(Camion entity) {
        this.gestorAbm.setEntity(entity);
    }

    private void habilitarPanelDatos(boolean habilitar) {
        patenteText.setEnabled(habilitar);
        capacidadText.setEnabled(habilitar);
        marcaText.setEnabled(habilitar);
        modeloText.setEnabled(habilitar);
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
            patenteText.setText(getEntity().getPatente());
            capacidadText.setText(Double.toString(getEntity().getCapacidadKilos()));
            marcaText.setText(getEntity().getMarca());
            modeloText.setText(Integer.toString(getEntity().getModelo()));
            descripcionText.setText(getEntity().getDescripcion());
        } else if (fuente == cliente) {
            getEntity().setPatente(patenteText.getText());
            getEntity().setCapacidadKilos(Double.valueOf(capacidadText.getText()).doubleValue());
            getEntity().setModelo(Integer.valueOf(modeloText.getText()).intValue());
            getEntity().setMarca(marcaText.getText());
            getEntity().setDescripcion(descripcionText.getText());
        }

    }
}
