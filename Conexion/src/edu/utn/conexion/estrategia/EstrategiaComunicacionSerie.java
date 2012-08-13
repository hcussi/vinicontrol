/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.conexion.estrategia;

import edu.utn.conexion.PaqueteComunicacion;
import edu.utn.conexion.entities.ConfiguracionConexion;

/**
 *
 * @author Hernán
 */
public class EstrategiaComunicacionSerie extends EstrategiaComunicacion{

    private boolean DEBUG = false;

    public EstrategiaComunicacionSerie(){
        super();
    }

    public void setDebugMode(){
        DEBUG = true;
    }

    public void deactivateDebugMode(){
        DEBUG = false;
    }
    
    public void setConfiguracionConexion(ConfiguracionConexion ConfiguracionConexion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void enviarPaqueteComunicacion(PaqueteComunicacion paqueteComunicacion) {

     
    }

}
