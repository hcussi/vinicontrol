package edu.utn.frm.dao.tanque;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.tanque.Tanque;
import edu.utn.frm.entities.usuario.Usuario;

public class TanqueDao extends GenericDao<Tanque, Long> {

    public TanqueDao(Usuario currentUser) {
        super(Tanque.class,currentUser);
    }

    public TanqueDao() {
        super(Tanque.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        TanqueFilter filter = (TanqueFilter) genericFilter;
        builder.appendSchema(getEntityName(), "t");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("t", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("t", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("t", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
