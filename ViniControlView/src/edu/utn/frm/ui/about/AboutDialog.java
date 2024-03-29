/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AboutDialog.java
 *
 * Created on 18/10/2009, 20:35:43
 */
package edu.utn.frm.ui.about;

import edu.utn.frm.ui.IconManager;

/**
 *
 * @author Proyecto
 */
public class AboutDialog extends javax.swing.JDialog {

    /** Creates new form AboutDialog */
    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblImage.setIcon(IconManager.getInstance().getAboutIcon());
        this.setIconImage(IconManager.getInstance().getMainIcon());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel appTitleLabel = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
        cerrarButton = new javax.swing.JButton();
        javax.swing.JLabel appHomepageLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel1 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de");

        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize() + 4));
        appTitleLabel.setText("ViniControl - Control en Tiempo Real");
        appTitleLabel.setName("appTitleLabel"); // NOI18N

        appDescLabel.setText("Una aplicación que asiste y controla automáticamente el");
        appDescLabel.setName("appDescLabel"); // NOI18N

        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel.setText("Versión del Producto:");
        versionLabel.setName("versionLabel"); // NOI18N

        appVersionLabel.setText("1.0");
        appVersionLabel.setName("appVersionLabel"); // NOI18N

        appVendorLabel.setText("Cátedra de Proyecto 2009");
        appVendorLabel.setName("appVendorLabel"); // NOI18N

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setText("Equipo:");
        vendorLabel.setName("vendorLabel"); // NOI18N

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setText("Integrantes:");
        homepageLabel.setName("homepageLabel"); // NOI18N

        appHomepageLabel.setText("Daniela Nievas, Gabriel Mopardo");
        appHomepageLabel.setName("appHomepageLabel"); // NOI18N

        imageLabel.setName("imageLabel"); // NOI18N

        cerrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/cerrar.png"))); // NOI18N
        cerrarButton.setText("Cerrar");
        cerrarButton.setName("cerrarButton"); // NOI18N
        cerrarButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarButtonActionPerformed(evt);
            }
        });

        appHomepageLabel2.setText("Nicolás Torres, Hernán Cussi");
        appHomepageLabel2.setName("appHomepageLabel2"); // NOI18N

        appDescLabel1.setText("proceso de vinificación.");
        appDescLabel1.setName("appDescLabel1"); // NOI18N

        lblImage.setName("lblImage"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(imageLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(appDescLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(versionLabel).addComponent(vendorLabel).addComponent(homepageLabel).addComponent(lblImage)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(appVersionLabel).addComponent(appVendorLabel).addComponent(appHomepageLabel).addComponent(appHomepageLabel2))).addComponent(appTitleLabel, javax.swing.GroupLayout.Alignment.LEADING).addComponent(appDescLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(appTitleLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(appDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(appDescLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(versionLabel).addComponent(appVersionLabel)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(vendorLabel).addComponent(appVendorLabel)).addGap(6, 6, 6).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(appHomepageLabel).addGap(7, 7, 7).addComponent(appHomepageLabel2)).addGroup(layout.createSequentialGroup().addComponent(homepageLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblImage)))).addComponent(imageLabel)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cerrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(19, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cerrarButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cerrarButton;
    private javax.swing.JLabel lblImage;
    // End of variables declaration//GEN-END:variables
}
