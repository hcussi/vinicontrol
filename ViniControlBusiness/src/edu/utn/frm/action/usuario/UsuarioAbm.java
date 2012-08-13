package edu.utn.frm.action.usuario;

import edu.utn.frm.action.GenericAbm;
import edu.utn.frm.action.LoginControlador;
import edu.utn.frm.dao.usuario.RolDao;
import edu.utn.frm.dao.usuario.UsuarioDao;
import edu.utn.frm.dao.usuario.UsuarioFilter;
import edu.utn.frm.entities.usuario.Rol;
import edu.utn.frm.entities.usuario.Usuario;
import java.util.List;


public class UsuarioAbm extends GenericAbm<Usuario, UsuarioDao, UsuarioFilter> {

    private RolDao rolDao = new RolDao(LoginControlador.getInstance().getUsuario());

    public UsuarioAbm() {
        super(Usuario.class,
                new UsuarioDao(LoginControlador.getInstance().getUsuario()),
                new UsuarioFilter());
    }

    @Override
    public List<Usuario> buscarTodos(){
        LoginControlador controlador = LoginControlador.getInstance();
        if( !controlador.isSuperUsuario() ){
            UsuarioFilter usuarioFilter = new UsuarioFilter();
            usuarioFilter.setNombre(controlador.getUsuario().getNombre());
            return dao.findByFilter(usuarioFilter);
        }else{
            return dao.findAll();
        }
    }

    public List<Rol> buscarTodosRoles(){
        
        return rolDao.findAll();
    }

    public Usuario validarLogin( String usuario, String password ){
        return this.dao.validarLogin( usuario, password );
    }

    @Override
    protected String getNombreArchivo(){
        return "/edu/utn/frm/ui/resources/report/reportUsuario.jasper";
    }

    @Override
    protected String getTituloInforme(){
        return "Informe de Usuarios";
    }
    
}