package edu.utn.frm.action.usuario;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.usuario.PrivilegioDao;
import edu.utn.frm.dao.usuario.PrivilegioFilter;
import edu.utn.frm.entities.usuario.Privilegio;


public class PrivilegioAbm extends GenericAbm<Privilegio, PrivilegioDao, PrivilegioFilter> {

    public PrivilegioAbm() {
        super(Privilegio.class,
                new PrivilegioDao(LoginControlador.getInstance().getUsuario()),
                new PrivilegioFilter());
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportPrivilegio.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Privilegios";
    }
}