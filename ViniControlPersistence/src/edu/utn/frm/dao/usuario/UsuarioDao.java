package edu.utn.frm.dao.usuario;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.usuario.EncriptadorPassword;
import edu.utn.frm.entities.usuario.Usuario;

public class UsuarioDao extends GenericDao<Usuario, Long> {

    private static final String ROOT = "superusuario";

    public UsuarioDao() {
        super(Usuario.class,null);
    }

    public UsuarioDao(Usuario currentUser) {
        super(Usuario.class,currentUser);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        UsuarioFilter filter = (UsuarioFilter) genericFilter;
        builder.appendSchema(getEntityName(), "u");

        if (filter.isEmpty()) {
            if (this.currentUser != null && currentUser.getUsuario().equals(ROOT)) {
                builder.appendWhereClause();
                builder.addAndClause();
                builder.appendFieldClause("u", "usuario", "!=", "usuario", ROOT);
            } else if (this.currentUser != null && !currentUser.getUsuario().equals(ROOT)) {
                builder.appendWhereClause();
                builder.addAndClause();
                builder.appendFieldClause("u", "usuario", "=", "usuario", currentUser.getUsuario());
            }
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullApellido()) {
            builder.addAndClause();
            builder.appendFieldClause("u", "apellido", "like", "apellido", "%" + filter.getApellido() + "%");
        }
        if (!filter.isNullNombre()) {
            builder.addAndClause();
            builder.appendFieldClause("u", "nombre", "like", "nombre", "%" + filter.getNombre() + "%");
        }
        if (!filter.isNullUsuario()) {
            builder.addAndClause();
            builder.appendFieldClause("u", "usuario", "like", "usuario", "%" + filter.getUsuario() + "%");
        }
        if (!filter.isNullDni()) {
            builder.addAndClause();
            builder.appendFieldClause("u", "dni", "like", "dni", "%" + filter.getDni() + "%");
        }
        if (this.currentUser != null && currentUser.getUsuario().equals(ROOT)) {
            builder.addAndClause();
            builder.appendFieldClause("u", "usuario", "!=", "nombreUsuario", ROOT);
        } else if (this.currentUser != null && !currentUser.getUsuario().equals(ROOT)) {
            builder.addAndClause();
            builder.appendFieldClause("u", "usuario", "=", "nombreUsuario", currentUser.getUsuario());
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("u", "anulado", "=", "anulado", filter.isAnulada());
        }
    }

    public Usuario validarLogin( String nombreUsuario, String password ){

        HqlBuilder builder = new HqlBuilder(getVinicEM());
        builder.appendSchema(getEntityName(), "u");

        builder.appendWhereClause();
        builder.appendFieldClause("u", "usuario", "=", "usuario", nombreUsuario );
        if( !nombreUsuario.equals(ROOT) ){
            builder.addAndClause();
            builder.appendFieldClause("u", "password", "=", "password", EncriptadorPassword.encriptarPassword(password) );
        }

        Usuario usuario =  (Usuario) builder.executeField();

        return usuario;
    }

}
