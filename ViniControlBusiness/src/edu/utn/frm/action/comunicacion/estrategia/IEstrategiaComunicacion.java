/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.comunicacion.estrategia;

import edu.utn.frm.action.comunicacion.PaqueteComunicacion;
import edu.utn.frm.action.comunicacion.eventos.ViniControlListener;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;


/**
 *
 * @author Proyecto
 */
public interface IEstrategiaComunicacion {

    public void setDebugMode();
    public void deactivateDebugMode();
    public void probarConfiguracionConexion() throws ComunicacionException;
    public void probarConfiguracionRecepcionConexion() throws ComunicacionException;
    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion);
    public void recibirPaquete() throws ComunicacionException;
    public void setConfiguracionConexion(ConfiguracionConexion configuracionConexion);
    public void addEventListener(ViniControlListener conexionMessageListener);
    public void removeEventListener(ViniControlListener conexionMessageListener);
    public void detenerConexion();
}
