package edu.utn.frm.dao.comunicacion;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.comunicacion.ConexionSerie;

public class ConexionSerieDao extends GenericDao<ConexionSerie, Long> {

    public ConexionSerieDao() {
        super(ConexionSerie.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ConexionSerieFilter filter = (ConexionSerieFilter) genericFilter;
        builder.appendSchema(getEntityName(), "cs");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullNobrePuerto()) {
            builder.appendFieldClause("cs", "nombrePuerto", "like", "nombrePuerto", "%" + filter.getNombrePuerto() + "%");
        }
        if (!filter.isNullCodigo()) {
            builder.appendFieldClause("cs", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.appendFieldClause("cs", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.appendFieldClause("ca", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
