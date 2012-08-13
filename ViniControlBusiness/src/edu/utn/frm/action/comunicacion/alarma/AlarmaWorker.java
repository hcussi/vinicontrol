/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.alarma;

import com.nitido.utils.toaster.Toaster;
import edu.utn.frm.action.GestorConfiguracionSistema;
import edu.utn.frm.entities.alarma.Alarma;
import edu.utn.frm.entities.alarma.AlarmaNivelAzucar;
import edu.utn.frm.entities.alarma.AlarmaNivelCapacidad;
import edu.utn.frm.entities.alarma.AlarmaRemontaje;
import edu.utn.frm.entities.alarma.AlarmaTemperatura;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author Proyecto
 */
public class AlarmaWorker extends SwingWorker{

    private Alarma alarma;
    private static int displayTime = 1200;
    private static Color toasterColor = Color.WHITE;
    private static Color messageColor = Color.RED;
    private static Color borderColor = Color.DARK_GRAY;
    private static int toasterHeight = 100;
    private static int toasterWidht = 420;
    private static int stepTime = 5;
    private static Font font = new Font("Verdana", Font.BOLD, 11);
    private static Toaster toaster;

    public AlarmaWorker(Alarma alarma){
        this.alarma = alarma;
        initToaster();
    }

    private static void initToaster(){
        if( toaster == null ){
            ConfiguracionSistema cs = GestorConfiguracionSistema.getInstance().getConfiguracionSistema();
            toaster = new Toaster();
            toaster.setDisplayTime( (cs!=null && cs.getMostrarAlarma()!=null) ? cs.getMostrarAlarma() : displayTime );
            toaster.setToasterHeight(toasterHeight);
            toaster.setToasterWidth(toasterWidht);
            toaster.setToasterColor(toasterColor);
            toaster.setMessageColor(messageColor);
            toaster.setBorderColor(borderColor);
            toaster.setStepTime(stepTime);
            toaster.setToasterMessageFont(font);
        }
    }

    @Override
    protected Object doInBackground() throws Exception {
        String title = buildTitle();
        String procesoDes = buildDescripcionProceso(alarma.getProceso());
        Icon icon = buildIcon();
        toaster.showToaster( icon, title + "\n\n" + procesoDes + "\n\n" + alarma.toString() );
        return null;
    }

    @Override
    protected void done() {

    }

    private String buildTitle() {
        if( alarma instanceof AlarmaNivelAzucar ){
            return "Alarma de Nivel de Azúcar";
        }else if( alarma instanceof AlarmaNivelCapacidad ){
            return "Alarma de Nivel de Capacidad";
        }else if( alarma instanceof AlarmaTemperatura ){
            AlarmaTemperatura alarmaTemperatura = (AlarmaTemperatura)alarma;
            if( alarmaTemperatura.getMaxima() ){
                return "Alarma de Temperatura Máxima";
            }else{
                return "Alarma de Temperatura Mínima";
            }
        }else if( alarma instanceof AlarmaRemontaje ){
            return "Alarma de Remontaje";
        }else{
            return "Alarma";
        }
    }

    private String buildDescripcionProceso(Proceso proceso){

        return "Proceso: " + proceso.getDescripcion() + " Fecha Inicio: " + proceso.getFechaInicioString() +
                " Fecha Fin: " + proceso.getFechaFinString();
    }

    private Icon buildIcon(){
        if( alarma instanceof AlarmaRemontaje ){
            ImageIcon icon = new ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/remontaje.png"));
            return icon;
        }else{
            ImageIcon icon = new ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/alarma.png"));
            return icon;
        }
    }

}