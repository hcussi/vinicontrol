/*
 * LogMessageIFrame.java
 *
 * Created on 04/08/2009, 17:09:11
 */
package edu.utn.frm.ui.log;

import edu.utn.frm.action.comunicacion.GestorGeneralEstado;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.ui.IconManager;
import java.util.List;

/**
 *
 * @author Proyecto
 */
public class LogMessageIFrame extends javax.swing.JInternalFrame{

    /** Creates new form LogMessageIFrame */
    public LogMessageIFrame() {
        initComponents();
        this.setFrameIcon(IconManager.getInstance().getFrameIcon());
        construirLogs();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salirButton = new javax.swing.JButton();
        logContainerTabbedPane = new javax.swing.JTabbedPane();

        setIconifiable(true);
        setTitle("Mensajes de Control");

        salirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/cerrar.png"))); // NOI18N
        salirButton.setText("Cerrar");
        salirButton.setName("salirButton"); // NOI18N
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        logContainerTabbedPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logContainerTabbedPane.setName("logContainerTabbedPane"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salirButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logContainerTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logContainerTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        dispose();
    }//GEN-LAST:event_salirButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane logContainerTabbedPane;
    private javax.swing.JButton salirButton;
    // End of variables declaration//GEN-END:variables

    private void construirLogs() {
        List<Proceso> procesos = GestorGeneralEstado.getInstance().getProcesos();

        logContainerTabbedPane.addTab("General", new JLogPanel("General", null));

        for (Proceso proceso : procesos) {
            JLogPanel logPanel = new JLogPanel(proceso.getDescripcion(), proceso);
            logContainerTabbedPane.addTab(proceso.getDescripcion(), logPanel);
        }
    }

}