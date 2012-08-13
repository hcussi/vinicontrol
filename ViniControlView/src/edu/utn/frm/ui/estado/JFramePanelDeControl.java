package edu.utn.frm.ui.estado;

import edu.utn.frm.action.comunicacion.GestorGeneralEstado;
import edu.utn.frm.action.comunicacion.eventos.AgregarProcesoEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevaAlarmaEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoComandoEvent;
import edu.utn.frm.action.comunicacion.eventos.NuevoEstadoTanqueEvent;
import edu.utn.frm.action.comunicacion.eventos.RemoverProcesoEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlEvent;
import edu.utn.frm.action.comunicacion.eventos.ViniControlListener;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.ui.IconManager;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JInternalFrame;

public class JFramePanelDeControl extends JInternalFrame implements ViniControlListener {

    private Hashtable<Proceso, JInternalFrameTanque> mapProcesoFrame;
    private static final int FRAME_WIDTH = 1270;
    private static final int FRAME_HEIGHT = 725;

    public JFramePanelDeControl(List<Proceso> procesos) {

        GestorGeneralEstado.getInstance().addEventListener(this);
        initComponents();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocation(3, 3);

        mapProcesoFrame = new Hashtable<Proceso, JInternalFrameTanque>();
        jDesktopPane1.setAutoscrolls(true);

        //Creo los internal frames
        for (Proceso proceso : procesos) {
            mapProcesoFrame.put(proceso, new JInternalFrameTanque(proceso));
        }

        //Organizo como mostrarlos
        ordenarJIFrames();

        this.setFrameIcon(IconManager.getInstance().getFrameIcon());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();

        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tablero de Control de Procesos");
        setAutoscrolls(true);
        setVisible(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(jDesktopPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables

    public int getCantJIFrames() {
        return mapProcesoFrame.size();
    }

    public void addJIFrame(Proceso proceso) {
        JInternalFrameTanque jIFrameTanque = new JInternalFrameTanque(proceso);
        mapProcesoFrame.put(proceso, jIFrameTanque);
        ordenarJIFrames();
    }

    public void removeJIFrame(Proceso proceso) {
        JInternalFrameTanque jIFrameTanque = mapProcesoFrame.get(proceso);
        if (jIFrameTanque != null) {
            jIFrameTanque.dispose();
            mapProcesoFrame.remove(proceso);
            ordenarJIFrames();
        }
    }

    private void ordenarJIFrames() {
        int cordenadaX = 1;
        int cordenadaY = 1;

        Object[] llaves = mapProcesoFrame.keySet().toArray();

        jDesktopPane1.removeAll();

        for (Object p : llaves) {
            JInternalFrame frame = mapProcesoFrame.get((Proceso) p);
            if (FRAME_WIDTH > cordenadaX + frame.getWidth()) {
                frame.setLocation(cordenadaX, cordenadaY);
                jDesktopPane1.add(frame);
                frame.setVisible(true);
                cordenadaX += frame.getWidth();
            } else {
                cordenadaX = 1;
                cordenadaY += frame.getHeight();
                frame.setLocation(cordenadaX, cordenadaY);
                jDesktopPane1.add(frame);
                frame.setVisible(true);
                cordenadaX += frame.getWidth();
            }
        }
    }

    public void actionPerformed(ViniControlEvent e) {
        if (e instanceof NuevoEstadoTanqueEvent) {
            NuevoEstadoTanqueEvent event = (NuevoEstadoTanqueEvent) e;
            Proceso proceso = event.getProceso();
            if (mapProcesoFrame.containsKey(proceso)) {
                mapProcesoFrame.get(proceso).actualizar(proceso);
            } else {
                addJIFrame(proceso);
            }
        } else if (e instanceof AgregarProcesoEvent) {
            AgregarProcesoEvent event = (AgregarProcesoEvent) e;
            addJIFrame(event.getProceso());
        } else if (e instanceof RemoverProcesoEvent) {
            RemoverProcesoEvent event = (RemoverProcesoEvent) e;
            removeJIFrame(event.getProceso());
        } else if (e instanceof NuevaAlarmaEvent) {
            NuevaAlarmaEvent event = (NuevaAlarmaEvent) e;
            Proceso proceso = event.getAlarma().getProceso();
            if (proceso != null && mapProcesoFrame.containsKey(proceso)) {
                if (mapProcesoFrame.get(proceso) != null) {
                    mapProcesoFrame.get(proceso).mostrarAlarma();
                }
            }
        } else if (e instanceof NuevoComandoEvent) {
            NuevoComandoEvent event = (NuevoComandoEvent) e;
            Proceso proceso = event.getProceso();
            if (proceso != null && mapProcesoFrame.containsKey(proceso)) {
                if (mapProcesoFrame.get(proceso) != null) {
                    try {
                        mapProcesoFrame.get(proceso).setComandoMessage(event.getMessage());
                    } catch (Exception ex) {
                        LogManager.getInstance().addLog("Fallo al actualizar el comando que se esta realizando", proceso);
                    }
                }
            }
        }
    }
}
