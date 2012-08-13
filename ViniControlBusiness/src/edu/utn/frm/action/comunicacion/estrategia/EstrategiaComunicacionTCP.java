/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.estrategia;

import edu.utn.frm.action.comunicacion.eventos.EventDispatcher;
import edu.utn.frm.action.comunicacion.PaqueteComunicacion;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.proceso.Proceso;


/**
 *
 * @author Proyecto
 */
public class EstrategiaComunicacionTCP extends EventDispatcher implements IEstrategiaComunicacion{

    private ConexionIP conexionIP;
    private boolean DEBUG = false;
    private Proceso proceso;

    public EstrategiaComunicacionTCP(Proceso proceso){
        super();
        this.proceso = proceso;
    }

    public void setDebugMode(){
        DEBUG = true;
    }

    public void deactivateDebugMode(){
        DEBUG = false;
    }

    public void setConfiguracionConexion(ConfiguracionConexion configuracionConexion) {
        if( configuracionConexion instanceof ConexionIP ){
            conexionIP = (ConexionIP)configuracionConexion;
        }

    }

    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion) {

        

    }

    public void probarConfiguracionConexion() throws ComunicacionException {


        
    }

    public void probarConfiguracionRecepcionConexion() throws ComunicacionException {



    }

    public void recibirPaquete() throws ComunicacionException {



    }

    public void detenerConexion() {


        
    }

}
