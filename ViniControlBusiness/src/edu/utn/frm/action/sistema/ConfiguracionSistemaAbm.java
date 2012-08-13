package edu.utn.frm.action.sistema;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.demanda.estado.EstadoPrediccionDao;
import edu.utn.frm.dao.generic.PersistException;
import edu.utn.frm.dao.generic.ValidateException;
import edu.utn.frm.dao.generic.utils.HelperValidator;
import edu.utn.frm.dao.sistema.ConfiguracionSistemaDao;
import edu.utn.frm.dao.sistema.ConfiguracionSistemaFilter;
import edu.utn.frm.dao.tanque.TanqueDao;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import edu.utn.frm.entities.tanque.Tanque;
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccion;
import edu.utn.frm.entities.tanque.demanda.EstadoPrediccionTendencia;
import java.util.List;
import org.apache.log4j.Logger;


public class ConfiguracionSistemaAbm extends GenericAbm<ConfiguracionSistema, ConfiguracionSistemaDao, ConfiguracionSistemaFilter> {

    private static Logger logger = Logger.getLogger(ConfiguracionSistemaAbm.class);
    private TanqueDao tanqueDao = new TanqueDao();
    private EstadoPrediccionDao estadoPrediccionDao = new EstadoPrediccionDao();

    public ConfiguracionSistemaAbm() {
        super(ConfiguracionSistema.class,
                new ConfiguracionSistemaDao(LoginControlador.getInstance().getUsuario()),
                new ConfiguracionSistemaFilter());
    }

    @Override
    public ConfiguracionSistema getEntity(){
        if( entity == null ){
            entity = dao.getConfiguracionSistema();
            if( entity == null ){
                entity = new ConfiguracionSistema();
            }
        }

        return entity;
    }

    public List<Tanque> buscarTodosTanques() {
        return tanqueDao.findAll();
    }

    public EstadoPrediccion buscarEstadoPrediccion() throws PersistException {
        EstadoPrediccion estadoPrediccion = estadoPrediccionDao.findEstado();
        if( estadoPrediccion == null ){
            try {
                estadoPrediccion = new EstadoPrediccion();
                estadoPrediccionDao.save(estadoPrediccion);
            } catch (PersistException ex) {
                logger.error("Error al crear el estado de predicción");
                throw ex;
            }
        }
        return estadoPrediccion;
    }

    public void guardarTanque(Tanque tanque) throws PersistException {
        tanqueDao.update(tanque);
    }

    public void validEstado(EstadoPrediccionTendencia estadoPrediccionTendencia) throws ValidateException {
        HelperValidator<EstadoPrediccionTendencia> validator = new HelperValidator<EstadoPrediccionTendencia>();
        validator.validate(estadoPrediccionTendencia, EstadoPrediccionTendencia.class );
    }
    
}