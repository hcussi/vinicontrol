package edu.utn.frm.dao.regla;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.regla.ReglaControlRemontaje;
import edu.utn.frm.entities.usuario.Usuario;

public class ReglaControlRemontajeDao extends GenericDao<ReglaControlRemontaje, Long> {

    public ReglaControlRemontajeDao(Usuario currentUser) {
        super(ReglaControlRemontaje.class,currentUser);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ReglaControlRemontajeFilter filter = (ReglaControlRemontajeFilter) genericFilter;
        builder.appendSchema(getEntityName(), "rc");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("rc", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("rc", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("rc", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
