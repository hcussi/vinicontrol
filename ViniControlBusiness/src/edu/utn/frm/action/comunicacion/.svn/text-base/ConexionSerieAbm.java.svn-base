package edu.utn.frm.action.comunicacion;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.dao.comunicacion.ConexionSerieDao;
import edu.utn.frm.dao.comunicacion.ConexionSerieFilter;
import edu.utn.frm.entities.comunicacion.ConexionSerie;


public class ConexionSerieAbm extends GenericAbm<ConexionSerie, ConexionSerieDao, ConexionSerieFilter> {

    public ConexionSerieAbm() {
        super(ConexionSerie.class,
                new ConexionSerieDao(),
                new ConexionSerieFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportConexionSerie.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Conexiones Serie";
    }
    
}