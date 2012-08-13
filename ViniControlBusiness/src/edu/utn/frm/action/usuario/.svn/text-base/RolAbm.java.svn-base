package edu.utn.frm.action.usuario;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.usuario.PrivilegioDao;
import edu.utn.frm.dao.usuario.RolFilter;
import edu.utn.frm.dao.usuario.RolDao;
import edu.utn.frm.entities.usuario.Privilegio;
import edu.utn.frm.entities.usuario.Rol;
import java.util.List;


public class RolAbm extends GenericAbm<Rol, RolDao, RolFilter> {

    private PrivilegioDao privilegioDao = new PrivilegioDao(LoginControlador.getInstance().getUsuario());

    public RolAbm() {
        super(Rol.class,
                new RolDao(LoginControlador.getInstance().getUsuario()),
                new RolFilter());
    }

    public List<Privilegio> buscarTodosPrivilegios(){
        return privilegioDao.findAll();
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportRol.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Roles";
    }
}