package edu.utn.frm.dao.tanque;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.tanque.GrupoTanque;
import edu.utn.frm.entities.usuario.Usuario;

public class GrupoTanqueDao extends GenericDao<GrupoTanque, Long> {

    public GrupoTanqueDao(Usuario currentUser) {
        super(GrupoTanque.class,currentUser);
    }

    public GrupoTanqueDao() {
        super(GrupoTanque.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        GrupoTanqueFilter filter = (GrupoTanqueFilter) genericFilter;
        builder.appendSchema(getEntityName(), "gt");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("gt", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullNombre()) {
            builder.addAndClause();
            builder.appendFieldClause("gt", "nombre", "like", "nombre", "%" + filter.getNombre() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("gt", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
