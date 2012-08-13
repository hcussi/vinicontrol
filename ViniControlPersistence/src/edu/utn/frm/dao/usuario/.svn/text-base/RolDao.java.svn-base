package edu.utn.frm.dao.usuario;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.usuario.Rol;
import edu.utn.frm.entities.usuario.Usuario;

public class RolDao extends GenericDao<Rol, Long> {

    public RolDao(Usuario currentUser) {
        super(Rol.class,currentUser);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        RolFilter filter = (RolFilter) genericFilter;
        builder.appendSchema(getEntityName(), "r");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("r", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("r", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("r", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
