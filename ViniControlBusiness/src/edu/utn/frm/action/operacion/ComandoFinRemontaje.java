/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action.operacion;

import edu.utn.frm.action.GestorConfiguracionSistema;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;

/**
 *
 * @author Proyecto
 */
public class ComandoFinRemontaje extends Comando implements IComandoTanque{

    private static final int ID_COMANDO = 3;

    public ComandoFinRemontaje(ConfiguracionConexion configuracionConexion, Proceso proceso){
        super(configuracionConexion, proceso);
    }

    protected int getComandoID() {
        ConfiguracionSistema cs = GestorConfiguracionSistema.getInstance().getConfiguracionSistema();
        return (cs!=null && cs.getIdComandoFinRemontaje()!=null) ?cs.getIdComandoFinRemontaje(): ID_COMANDO;
    }

}
