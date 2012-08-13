package edu.utn.frm.action.tanque;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.comunicacion.ConexionIPDao;
import edu.utn.frm.dao.comunicacion.ConexionSerieDao;
import edu.utn.frm.dao.regla.ReglaControlDao;
import edu.utn.frm.dao.tanque.GrupoTanqueDao;
import edu.utn.frm.dao.tanque.TanqueDao;
import edu.utn.frm.dao.tanque.TanqueFilter;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.comunicacion.ConexionSerie;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.tanque.GrupoTanque;
import edu.utn.frm.entities.tanque.Tanque;
import java.util.List;


public class TanqueAbm extends GenericAbm<Tanque, TanqueDao, TanqueFilter> {

    private ReglaControlDao reglaControlDao = new ReglaControlDao();
    private ConexionIPDao conexionIPDao = new ConexionIPDao();
    private ConexionSerieDao conexionSerieDao = new ConexionSerieDao();
    private GrupoTanqueDao grupoTanqueDao = new GrupoTanqueDao();

    public TanqueAbm() {
        super(Tanque.class,
                new TanqueDao(LoginControlador.getInstance().getUsuario()),
                new TanqueFilter());
    }

    public List<ReglaControl> buscarTodosReglaControl() {
        return reglaControlDao.findAll();
    }

    public List<ConexionIP> buscarTodosConexionIP() {
        return conexionIPDao.findAll();
    }

    public List<ConexionSerie> buscarTodosConexionSerie() {
        return conexionSerieDao.findAll();
    }

    public List<GrupoTanque> buscarTodosGruposTanque() {
        return grupoTanqueDao.findAll();
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportTanque.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Tanques";
    }
}