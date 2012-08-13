package edu.utn.frm.action.tanque;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.regla.ReglaControlDao;
import edu.utn.frm.dao.tanque.GrupoTanqueDao;
import edu.utn.frm.dao.tanque.GrupoTanqueFilter;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.tanque.GrupoTanque;
import java.util.List;


public class GrupoTanqueAbm extends GenericAbm<GrupoTanque, GrupoTanqueDao, GrupoTanqueFilter> {

    private ReglaControlDao reglaControlDao = new ReglaControlDao(LoginControlador.getInstance().getUsuario());

    public GrupoTanqueAbm() {
        super(GrupoTanque.class,
                new GrupoTanqueDao(LoginControlador.getInstance().getUsuario()),
                new GrupoTanqueFilter());
    }

    public List<ReglaControl> buscarTodosReglaControl() {
        return reglaControlDao.findAll();
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportGrupoTanque.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Grupos de Tanque";
    }
    
}