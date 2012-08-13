/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.estrategia;

import edu.utn.conexion.PaqueteComunicacion;
import edu.utn.conexion.entities.ConfiguracionConexion;
import edu.utn.conexion.eventos.ConexionMessageListener;

/**
 *
 * @author Hernán
 */
public interface IEstrategiaComunicacion {

    public void setDebugMode();
    public void deactivateDebugMode();
    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion);
    public void setConfiguracionConexion(ConfiguracionConexion configuracionConexion);
    public void addEventListener(ConexionMessageListener conexionMessageListener);
    public void removeEventListener(ConexionMessageListener conexionMessageListener);
}
