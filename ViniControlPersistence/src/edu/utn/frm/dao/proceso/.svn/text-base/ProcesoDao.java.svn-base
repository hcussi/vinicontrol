package edu.utn.frm.dao.proceso;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.usuario.Usuario;

public class ProcesoDao extends GenericDao<Proceso, Long> {

    public ProcesoDao(Usuario currentUser) {
        super(Proceso.class,currentUser);
    }

    public ProcesoDao() {
        super(Proceso.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ProcesoFilter filter = (ProcesoFilter) genericFilter;
        builder.appendSchema(getEntityName(), "p");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullFechaFin()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "fechaFin", "<=", "fechaFin", filter.getFechaFin() );
        }
        if (!filter.isNullFechaInicio()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "fechaInicio", ">=", "fechaInicio", filter.getFechaInicio() );
        }
        if (!filter.isNullFecha()) {
            builder.addAndClause();
            builder.openParanthesis();
                builder.appendFieldClause("p", "fechaInicio", "<=", "fechaEntreInicio", filter.getFecha() );
                builder.addAndClause();
                builder.appendFieldClause("p", "fechaFin", ">=", "fechaEntreFin", filter.getFecha() );
            builder.closeParenthesis();
        }

        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        
        if (!filter.isNullEstadoProceso()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "estadoProceso", "=", "estadoProceso", filter.getEstadoProceso() );
        }
        if (!filter.isNullTanque()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "tanque", "=", "tanque", filter.getTanque() );
        }
        if (!filter.isNullProducto()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "mostoEnTanque", "=", "producto", filter.getProducto() );
        }

        if (!filter.isNullTanqueNotNull()) {
            builder.addAndClause();
            builder.appendFreeClause("p.tanque != null");
        }
        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("p", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
