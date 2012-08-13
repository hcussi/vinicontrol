/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.estrategia;

import edu.utn.conexion.PaqueteComunicacion;
import edu.utn.conexion.entities.ConexionIP;
import edu.utn.conexion.entities.ConfiguracionConexion;

/**
 *
 * @author Hernán
 */
public class EstrategiaComunicacionTCP extends EstrategiaComunicacion {

    private ConexionIP conexionIP;
    private boolean DEBUG = false;

    public EstrategiaComunicacionTCP(){
        super();
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

}
