package edu.utn.frm.action.producto;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.producto.FincaDao;
import edu.utn.frm.dao.producto.FincaFilter;
import edu.utn.frm.entities.producto.Finca;


public class FincaAbm extends GenericAbm<Finca, FincaDao, FincaFilter> {

    public FincaAbm() {
        super(Finca.class,
                new FincaDao(LoginControlador.getInstance().getUsuario()),
                new FincaFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportFinca.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Fincas";
    }
    
}