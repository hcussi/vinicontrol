/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.operacion;

import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.proceso.Proceso;

/**
 *
 * @author Proyecto
 */
public class FactoriaComandos {

    private static FactoriaComandos instance;
    public static final int COMANDO_BAJAR_TEMPERATURA = 0;
    public static final int COMANDO_FIN_REMONTAJE = 1;
    public static final int COMANDO_REMONTAJE = 2;

    private FactoriaComandos(){
        
    }

    public static FactoriaComandos getInstance(){
        if( instance == null ){
            instance = new FactoriaComandos();
        }
        return instance;
    }

    public IComandoTanque getComando(int tipo, ConfiguracionConexion configuracionConexion, Proceso proceso){
        switch(tipo){
            case COMANDO_BAJAR_TEMPERATURA:
                return new ComandoBajarTemperatura(configuracionConexion, proceso);
            case COMANDO_FIN_REMONTAJE:
                return new ComandoFinRemontaje(configuracionConexion, proceso);
            case COMANDO_REMONTAJE:
                return new ComandoRemontaje(configuracionConexion, proceso);
        }
        return null;
    }

}