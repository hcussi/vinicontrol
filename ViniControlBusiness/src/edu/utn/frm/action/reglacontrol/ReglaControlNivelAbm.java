package edu.utn.frm.action.reglacontrol;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.regla.ReglaControlNivelDao;
import edu.utn.frm.dao.regla.ReglaControlNivelFilter;
import edu.utn.frm.entities.regla.ReglaControlNivel;


public class ReglaControlNivelAbm extends GenericAbm<ReglaControlNivel, ReglaControlNivelDao,
        ReglaControlNivelFilter> {

    public ReglaControlNivelAbm() {
        super(ReglaControlNivel.class,
                new ReglaControlNivelDao(LoginControlador.getInstance().getUsuario()),
                new ReglaControlNivelFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportReglaControlNivel.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Reglas de Control de Nivel";
    }
    
}