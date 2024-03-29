/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViniControlView.java
 *
 * Created on 04/08/2009, 13:33:20
 */
package edu.utn.frm.ui;

import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.action.comunicacion.GestorGeneralEstado;
import edu.utn.frm.action.exception.GestorGeneralEstadoException;
import edu.utn.frm.ui.about.AboutDialog;
import edu.utn.frm.action.comunicacion.alarma.GestorGeneralAlarmaView;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.action.manual.GestorManual;
import edu.utn.frm.ui.comunicacion.ConexionIPIFrame;
import edu.utn.frm.ui.comunicacion.ConexionSerieIFrame;
import edu.utn.frm.ui.control.ReglaControlIFrame;
import edu.utn.frm.ui.control.ReglaControlNivelIFrame;
import edu.utn.frm.ui.control.ReglaControlRemontajeIFrame;
import edu.utn.frm.ui.control.ReglaControlTemperaturaIFrame;
import edu.utn.frm.ui.estado.JFramePanelDeControl;
import edu.utn.frm.ui.log.LogMessageIFrame;
import edu.utn.frm.ui.proceso.ProcesoIFrame;
import edu.utn.frm.ui.producto.CamionIFrame;
import edu.utn.frm.ui.producto.FincaIFrame;
import edu.utn.frm.ui.producto.MostoEnTanqueIFrame;
import edu.utn.frm.ui.producto.VariedadIFrame;
import edu.utn.frm.ui.sistema.ConfiguracionSistemaIFrame;
import edu.utn.frm.ui.tanque.GrupoTanqueIFrame;
import edu.utn.frm.ui.tanque.TanqueIFrame;
import edu.utn.frm.ui.usuario.PrivilegioIFrame;
import edu.utn.frm.ui.usuario.RolIFrame;
import edu.utn.frm.ui.usuario.UsuarioIFrame;
import edu.utn.frm.ui.utils.Utiles;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Proyecto
 */
public class ViniControlView extends javax.swing.JFrame {

    private GestorGeneralEstado gge;
    private GestorGeneralAlarmaView ggav;

    /** Creates new form ViniControlView */
    public ViniControlView() {
        gge = GestorGeneralEstado.getInstance();
        ggav = new GestorGeneralAlarmaView();
        initComponents();
        validarAcceso();
        this.setIconImage(IconManager.getInstance().getMainIcon());
        crearTableroControl();
    }

    public GestorGeneralEstado getGestorGeneralEstado() {
        return gge;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        sistemaMenu = new javax.swing.JMenu();
        configuracionSistemaMenuItem = new javax.swing.JMenuItem();
        procesoMenuItem = new javax.swing.JMenuItem();
        logMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        tanqueMenu = new javax.swing.JMenu();
        grupoTanqueMenuItem = new javax.swing.JMenuItem();
        tanqueMenuItem = new javax.swing.JMenuItem();
        reglaMenu = new javax.swing.JMenu();
        reglaControlTemperaturaMenuItem = new javax.swing.JMenuItem();
        reglaControlNivelMenuItem = new javax.swing.JMenuItem();
        reglaControlRemontajeMenuItem = new javax.swing.JMenuItem();
        reglaControlMenuItem = new javax.swing.JMenuItem();
        productoMenu = new javax.swing.JMenu();
        camionMenuItem = new javax.swing.JMenuItem();
        fincaMenuItem = new javax.swing.JMenuItem();
        VariedadMenuItem = new javax.swing.JMenuItem();
        productoMenuItem = new javax.swing.JMenuItem();
        conexionMenu = new javax.swing.JMenu();
        conexionIPMenuItem = new javax.swing.JMenuItem();
        conexionSerieMenuItem = new javax.swing.JMenuItem();
        usuarioMenu = new javax.swing.JMenu();
        privilegioMenuItem = new javax.swing.JMenuItem();
        rolMenuItem = new javax.swing.JMenuItem();
        usuarioMenuItem = new javax.swing.JMenuItem();
        ayudaMenu = new javax.swing.JMenu();
        contenidosMenuItem = new javax.swing.JMenuItem();
        acercaDeMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Control del Proceso de Vinificación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                windowClosedHandler(evt);
            }
        });

        desktopPane.setBackground(javax.swing.UIManager.getDefaults().getColor("inactiveCaptionText"));

        sistemaMenu.setText("Sistema");

        configuracionSistemaMenuItem.setText("Configuración");
        configuracionSistemaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionSistemaMenuItemActionPerformed(evt);
            }
        });
        sistemaMenu.add(configuracionSistemaMenuItem);

        procesoMenuItem.setText("Procesos");
        procesoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesoMenuItemActionPerformed(evt);
            }
        });
        sistemaMenu.add(procesoMenuItem);

        logMenuItem.setText("Mensajes de Log");
        logMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logMenuItemActionPerformed(evt);
            }
        });
        sistemaMenu.add(logMenuItem);
        sistemaMenu.add(jSeparator1);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        sistemaMenu.add(jMenuItemSalir);

        menuBar.add(sistemaMenu);

        tanqueMenu.setText("Tanque");

        grupoTanqueMenuItem.setText("Grupo Tanque");
        grupoTanqueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoTanqueMenuItemActionPerformed(evt);
            }
        });
        tanqueMenu.add(grupoTanqueMenuItem);

        tanqueMenuItem.setText("Tanque");
        tanqueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanqueMenuItemActionPerformed(evt);
            }
        });
        tanqueMenu.add(tanqueMenuItem);

        menuBar.add(tanqueMenu);

        reglaMenu.setText("Regla");

        reglaControlTemperaturaMenuItem.setText("Regla Control Temperatura");
        reglaControlTemperaturaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reglaControlTemperaturaMenuItemActionPerformed(evt);
            }
        });
        reglaMenu.add(reglaControlTemperaturaMenuItem);

        reglaControlNivelMenuItem.setText("Regla Control de Nivel");
        reglaControlNivelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reglaControlNivelMenuItemActionPerformed(evt);
            }
        });
        reglaMenu.add(reglaControlNivelMenuItem);

        reglaControlRemontajeMenuItem.setText("Regla Control de Remontaje");
        reglaControlRemontajeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reglaControlRemontajeMenuItemActionPerformed(evt);
            }
        });
        reglaMenu.add(reglaControlRemontajeMenuItem);

        reglaControlMenuItem.setText("Regla Control");
        reglaControlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reglaControlMenuItemActionPerformed(evt);
            }
        });
        reglaMenu.add(reglaControlMenuItem);

        menuBar.add(reglaMenu);

        productoMenu.setText("Producto");

        camionMenuItem.setText("Camión");
        camionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camionMenuItemActionPerformed(evt);
            }
        });
        productoMenu.add(camionMenuItem);

        fincaMenuItem.setText("Finca");
        fincaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fincaMenuItemActionPerformed(evt);
            }
        });
        productoMenu.add(fincaMenuItem);

        VariedadMenuItem.setText("Variedad");
        VariedadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VariedadMenuItemActionPerformed(evt);
            }
        });
        productoMenu.add(VariedadMenuItem);

        productoMenuItem.setText("Producto");
        productoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoMenuItemActionPerformed(evt);
            }
        });
        productoMenu.add(productoMenuItem);

        menuBar.add(productoMenu);

        conexionMenu.setText("Conexión");

        conexionIPMenuItem.setText("Conexión IP");
        conexionIPMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conexionIPMenuItemActionPerformed(evt);
            }
        });
        conexionMenu.add(conexionIPMenuItem);

        conexionSerieMenuItem.setText("Conexión Serie");
        conexionSerieMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conexionSerieMenuItemActionPerformed(evt);
            }
        });
        conexionMenu.add(conexionSerieMenuItem);

        menuBar.add(conexionMenu);

        usuarioMenu.setText("Usuario");

        privilegioMenuItem.setText("Privilegio");
        privilegioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privilegioMenuItemActionPerformed(evt);
            }
        });
        usuarioMenu.add(privilegioMenuItem);

        rolMenuItem.setText("Rol");
        rolMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolMenuItemActionPerformed(evt);
            }
        });
        usuarioMenu.add(rolMenuItem);

        usuarioMenuItem.setText("Usuario");
        usuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioMenuItemActionPerformed(evt);
            }
        });
        usuarioMenu.add(usuarioMenuItem);

        menuBar.add(usuarioMenu);

        ayudaMenu.setText("Ayuda");

        contenidosMenuItem.setText("Contenidos");
        contenidosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contenidosMenuItemActionPerformed(evt);
            }
        });
        ayudaMenu.add(contenidosMenuItem);

        acercaDeMenuItem.setText("Acerca de");
        acercaDeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaDeMenuItemActionPerformed(evt);
            }
        });
        ayudaMenu.add(acercaDeMenuItem);

        menuBar.add(ayudaMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        try {
            GestorGeneralEstado.getInstance().detener();
        } catch (GestorGeneralEstadoException ex) {
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void grupoTanqueMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoTanqueMenuItemActionPerformed
        load(new GrupoTanqueIFrame(), true);
    }//GEN-LAST:event_grupoTanqueMenuItemActionPerformed

    private void camionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camionMenuItemActionPerformed
        load(new CamionIFrame(), true);
    }//GEN-LAST:event_camionMenuItemActionPerformed

    private void fincaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fincaMenuItemActionPerformed
        load(new FincaIFrame(), true);
    }//GEN-LAST:event_fincaMenuItemActionPerformed

    private void VariedadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VariedadMenuItemActionPerformed
        load(new VariedadIFrame(), true);
    }//GEN-LAST:event_VariedadMenuItemActionPerformed

    private void reglaControlTemperaturaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reglaControlTemperaturaMenuItemActionPerformed
        load(new ReglaControlTemperaturaIFrame(), true);
    }//GEN-LAST:event_reglaControlTemperaturaMenuItemActionPerformed

    private void reglaControlNivelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reglaControlNivelMenuItemActionPerformed
        load(new ReglaControlNivelIFrame(), true);
    }//GEN-LAST:event_reglaControlNivelMenuItemActionPerformed

    private void reglaControlRemontajeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reglaControlRemontajeMenuItemActionPerformed
        load(new ReglaControlRemontajeIFrame(), true);
    }//GEN-LAST:event_reglaControlRemontajeMenuItemActionPerformed

    private void reglaControlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reglaControlMenuItemActionPerformed
        load(new ReglaControlIFrame(), true);
    }//GEN-LAST:event_reglaControlMenuItemActionPerformed

    private void conexionIPMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conexionIPMenuItemActionPerformed
        load(new ConexionIPIFrame(), true);
    }//GEN-LAST:event_conexionIPMenuItemActionPerformed

    private void privilegioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_privilegioMenuItemActionPerformed
        load(new PrivilegioIFrame(), true);
    }//GEN-LAST:event_privilegioMenuItemActionPerformed

    private void rolMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolMenuItemActionPerformed
        load(new RolIFrame(), true);
    }//GEN-LAST:event_rolMenuItemActionPerformed

    private void usuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioMenuItemActionPerformed
        load(new UsuarioIFrame(), true);
    }//GEN-LAST:event_usuarioMenuItemActionPerformed

    private void configuracionSistemaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionSistemaMenuItemActionPerformed
        load(new ConfiguracionSistemaIFrame(), true);
    }//GEN-LAST:event_configuracionSistemaMenuItemActionPerformed

    private void tanqueMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanqueMenuItemActionPerformed
        load(new TanqueIFrame(), true);
    }//GEN-LAST:event_tanqueMenuItemActionPerformed

    private void productoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoMenuItemActionPerformed
        load(new MostoEnTanqueIFrame(), true);
    }//GEN-LAST:event_productoMenuItemActionPerformed

    private void procesoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesoMenuItemActionPerformed
        load(new ProcesoIFrame(), true);
    }//GEN-LAST:event_procesoMenuItemActionPerformed

    private void conexionSerieMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conexionSerieMenuItemActionPerformed
        load(new ConexionSerieIFrame(), true);
    }//GEN-LAST:event_conexionSerieMenuItemActionPerformed

    private void acercaDeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaDeMenuItemActionPerformed
        AboutDialog dialog = new AboutDialog(this, true);
        Utiles.centrarFormulario(dialog);
        dialog.setVisible(true);
    }//GEN-LAST:event_acercaDeMenuItemActionPerformed

    private void logMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logMenuItemActionPerformed
        load(new LogMessageIFrame(), true);
    }//GEN-LAST:event_logMenuItemActionPerformed

    private void windowClosedHandler(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosedHandler
        try {
            GestorGeneralEstado.getInstance().detener();
        } catch (GestorGeneralEstadoException ex) {
        }
    }//GEN-LAST:event_windowClosedHandler

    private void contenidosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contenidosMenuItemActionPerformed
        try {
            GestorManual.getInstance().verManual();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Se ha producido un error al intentar abrir el manual","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_contenidosMenuItemActionPerformed

    private void load(JInternalFrame iFrame, boolean centrarFormulario) {
        if (centrarFormulario) {
            Utiles.centrarFormulario(iFrame);
        }
        iFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {

            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                System.gc();
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        desktopPane.add(iFrame);
        iFrame.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem VariedadMenuItem;
    private javax.swing.JMenuItem acercaDeMenuItem;
    private javax.swing.JMenu ayudaMenu;
    private javax.swing.JMenuItem camionMenuItem;
    private javax.swing.JMenuItem conexionIPMenuItem;
    private javax.swing.JMenu conexionMenu;
    private javax.swing.JMenuItem conexionSerieMenuItem;
    private javax.swing.JMenuItem configuracionSistemaMenuItem;
    private javax.swing.JMenuItem contenidosMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem fincaMenuItem;
    private javax.swing.JMenuItem grupoTanqueMenuItem;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem logMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem privilegioMenuItem;
    private javax.swing.JMenuItem procesoMenuItem;
    private javax.swing.JMenu productoMenu;
    private javax.swing.JMenuItem productoMenuItem;
    private javax.swing.JMenuItem reglaControlMenuItem;
    private javax.swing.JMenuItem reglaControlNivelMenuItem;
    private javax.swing.JMenuItem reglaControlRemontajeMenuItem;
    private javax.swing.JMenuItem reglaControlTemperaturaMenuItem;
    private javax.swing.JMenu reglaMenu;
    private javax.swing.JMenuItem rolMenuItem;
    private javax.swing.JMenu sistemaMenu;
    private javax.swing.JMenu tanqueMenu;
    private javax.swing.JMenuItem tanqueMenuItem;
    private javax.swing.JMenu usuarioMenu;
    private javax.swing.JMenuItem usuarioMenuItem;
    // End of variables declaration//GEN-END:variables

    private void validarAcceso() {
        LoginControlador controlador = LoginControlador.getInstance();
        conexionMenu.setVisible(controlador.isAccesoConexion());
        productoMenu.setVisible(controlador.isAccesoProducto());
        reglaMenu.setVisible(controlador.isAccesoReglaControl());
        tanqueMenu.setVisible(controlador.isAccesoTanque());
        usuarioMenu.setVisible(controlador.isAccesoUsuario());
        configuracionSistemaMenuItem.setVisible(controlador.isAccesoConfiguracionSistema());
        procesoMenuItem.setVisible(controlador.isAccesoConfiguracionSistema());
    }

    private void crearTableroControl() {

        LogManager.getInstance().addLog("Creando tablero de control", null);
        JFramePanelDeControl control = new JFramePanelDeControl(GestorGeneralEstado.getInstance().getProcesos());
        load(control, false);
        LogManager.getInstance().addLog("Fin de la creación del tablero de control", null);
    }
}
