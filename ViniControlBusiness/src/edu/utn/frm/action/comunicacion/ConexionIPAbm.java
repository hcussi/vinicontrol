package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.action.exception.ComunicacionException;
import edu.utn.frm.action.log.LogManager;
import edu.utn.frm.dao.comunicacion.ConexionIPDao;
import edu.utn.frm.dao.comunicacion.ConexionIPFilter;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import org.apache.log4j.Logger;


public class ConexionIPAbm extends GenericAbm<ConexionIP, ConexionIPDao, ConexionIPFilter> {

    private static Logger logger = Logger.getLogger(ConexionIPAbm.class);

    public ConexionIPAbm() {
        super(ConexionIP.class,
                new ConexionIPDao(LoginControlador.getInstance().getUsuario()),
                new ConexionIPFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportConexionIP.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Conexiones IP";
    }

    public void probarConexionUDP() throws ComunicacionException{
        logger.info("Probando la conexión UDP");
        LogManager.getInstance().addLog("Probando la conexión UDP", null);
        GestorComunicacionEnviar gce = new GestorComunicacionEnviar(null);
        gce.probarConexion(entity);
    }

    public void probarConexionRecepcionUDP() throws ComunicacionException{
        logger.info("Probando la conexión UDP");
        LogManager.getInstance().addLog("Probando la conexión UDP", null);
        GestorComunicacionEnviar gce = new GestorComunicacionEnviar(null);
        gce.probarConexionRecepcion(entity);
    }

}