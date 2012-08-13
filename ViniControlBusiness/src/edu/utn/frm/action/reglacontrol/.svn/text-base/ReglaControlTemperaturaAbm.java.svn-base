package edu.utn.frm.action.reglacontrol;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.regla.ReglaControlTemperaturaDao;
import edu.utn.frm.dao.regla.ReglaControlTemperaturaFilter;
import edu.utn.frm.entities.regla.ReglaControlTemperatura;


public class ReglaControlTemperaturaAbm extends GenericAbm<ReglaControlTemperatura, ReglaControlTemperaturaDao,
        ReglaControlTemperaturaFilter> {

    public ReglaControlTemperaturaAbm() {
        super(ReglaControlTemperatura.class,
                new ReglaControlTemperaturaDao(LoginControlador.getInstance().getUsuario()),
                new ReglaControlTemperaturaFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportReglaControlTemperatura.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Reglas de Control de Temperatura";
    }
}