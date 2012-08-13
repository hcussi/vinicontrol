package edu.utn.frm.action.reglacontrol;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.regla.ReglaControlDao;
import edu.utn.frm.dao.regla.ReglaControlFilter;
import edu.utn.frm.dao.regla.ReglaControlNivelDao;
import edu.utn.frm.dao.regla.ReglaControlRemontajeDao;
import edu.utn.frm.dao.regla.ReglaControlTemperaturaDao;
import edu.utn.frm.entities.regla.ReglaControl;
import edu.utn.frm.entities.regla.ReglaControlNivel;
import edu.utn.frm.entities.regla.ReglaControlRemontaje;
import edu.utn.frm.entities.regla.ReglaControlTemperatura;
import java.util.List;


public class ReglaControlAbm extends GenericAbm<ReglaControl, ReglaControlDao, ReglaControlFilter> {

    private ReglaControlTemperaturaDao reglaControlTemperaturaDao = new ReglaControlTemperaturaDao(LoginControlador.getInstance().getUsuario());
    private ReglaControlNivelDao reglaControlNivelDao = new ReglaControlNivelDao(LoginControlador.getInstance().getUsuario());
    private ReglaControlRemontajeDao reglaControlRemontajeDao = new ReglaControlRemontajeDao(LoginControlador.getInstance().getUsuario());

    public ReglaControlAbm() {
        super(ReglaControl.class,
                new ReglaControlDao(LoginControlador.getInstance().getUsuario()),
                new ReglaControlFilter());
    }

    public List<ReglaControlTemperatura> buscarTodosReglaTemperatura(){

        return reglaControlTemperaturaDao.findAll();
    }

    public List<ReglaControlNivel> buscarTodosReglaNivel(){

        return reglaControlNivelDao.findAll();
    }
    public List<ReglaControlRemontaje> buscarTodosReglaRemontaje(){

        return reglaControlRemontajeDao.findAll();
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportReglaControl.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Reglas de Control";
    }
}