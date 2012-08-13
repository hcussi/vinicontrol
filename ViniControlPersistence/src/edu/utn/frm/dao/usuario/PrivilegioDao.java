package edu.utn.frm.dao.usuario;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.usuario.Privilegio;
import edu.utn.frm.entities.usuario.Usuario;

public class PrivilegioDao extends GenericDao<Privilegio, Long> {

    public PrivilegioDao(Usuario currentUser) {
        super(Privilegio.class,currentUser);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        PrivilegioFilter filter = (PrivilegioFilter) genericFilter;
        builder.appendSchema(getEntityName(), "p");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullTipoPrivilegio()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "tipoPrivilegio", "=", "tipoPrivilegio", filter.getTipoPrivilegio() );
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}