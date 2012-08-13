package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.producto.MostoEnTanque;
import edu.utn.frm.entities.usuario.Usuario;

public class MostoEnTanqueDao extends GenericDao<MostoEnTanque, Long> {

    public MostoEnTanqueDao(Usuario currentUser) {
        super(MostoEnTanque.class,currentUser);
    }

    public MostoEnTanqueDao() {
        super(MostoEnTanque.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        MostoEnTanqueFilter filter = (MostoEnTanqueFilter) genericFilter;
        builder.appendSchema(getEntityName(), "met");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("met", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("met", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullFechaIngreso()) {
            builder.addAndClause();
            builder.appendFieldClause("met", "fechaIngreso", "=", "fechaIngreso", filter.getFechaIngreso() );
        }
        if (!filter.isNullFechaEgreso()) {
            builder.addAndClause();
            builder.appendFieldClause("met", "fechaEgreso", "=", "fechaEgreso", filter.getFechaEgreso() );
        }
        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("met", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
