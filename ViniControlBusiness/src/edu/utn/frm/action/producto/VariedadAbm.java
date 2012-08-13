package edu.utn.frm.action.producto;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.producto.VariedadDao;
import edu.utn.frm.dao.producto.VariedadFilter;
import edu.utn.frm.entities.producto.Variedad;


public class VariedadAbm extends GenericAbm<Variedad, VariedadDao, VariedadFilter> {

    public VariedadAbm() {
        super(Variedad.class,
                new VariedadDao(LoginControlador.getInstance().getUsuario()),
                new VariedadFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportVariedad.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Variedades";
    }
    
}