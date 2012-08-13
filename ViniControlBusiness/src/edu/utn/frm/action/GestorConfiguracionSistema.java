/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.action;

import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.dao.generic.PersistException;
import edu.utn.frm.dao.sistema.ConfiguracionSistemaDao;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class GestorConfiguracionSistema {

    private static GestorConfiguracionSistema instance;
    private ConfiguracionSistema configuracionSistema;
    private ConfiguracionSistemaDao configuracionSistemaDao;
    private static Logger logger = Logger.getLogger(GestorConfiguracionSistema.class);
    private LogManager logManager = LogManager.getInstance();

    private GestorConfiguracionSistema(){
        this.configuracionSistemaDao = new ConfiguracionSistemaDao();
        this.configuracionSistema = this.configuracionSistemaDao.getConfiguracionSistema();
        if( this.configuracionSistema == null ){
            construirConfiguracionSistema();
        }
    }

    public static GestorConfiguracionSistema getInstance(){
        if( instance == null ){
            instance = new GestorConfiguracionSistema();
        }
        return instance;
    }

    public ConfiguracionSistema getConfiguracionSistema() {
        return this.configuracionSistemaDao.getConfiguracionSistema();
    }

    private void construirConfiguracionSistema() {
        this.configuracionSistema = new ConfiguracionSistema("10-10101010-1","direccion Empresa","Empresa S.A.","Empresa S.A.");
        try {
            this.configuracionSistemaDao.save(configuracionSistema);
        } catch (PersistException ex) {
            logger.fatal("No se ha podido crear una Configuración del Sistema por defecto");
            logManager.addLog("No se ha podido crear una Configuración del Sistema por defecto", null);
        }
    }

}