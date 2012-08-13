package edu.utn.frm.action.producto;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.entities.producto.Camion;
import edu.utn.frm.dao.producto.CamionDao;
import edu.utn.frm.dao.producto.CamionFilter;

public class CamionAbm extends GenericAbm<Camion, CamionDao, CamionFilter> {

    public CamionAbm() {
        super(Camion.class,
                new CamionDao(LoginControlador.getInstance().getUsuario()),
                new CamionFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportCamion.jasper";
    }
    
    @Override
    protected String getTituloInforme(){
        return "Informe de Camiones";
    }

}