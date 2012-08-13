package edu.utn.frm.dao.regla;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.regla.ReglaControlNivel;
import edu.utn.frm.entities.usuario.Usuario;

public class ReglaControlNivelDao extends GenericDao<ReglaControlNivel, Long> {

    public ReglaControlNivelDao(Usuario currentUser) {
        super(ReglaControlNivel.class,currentUser);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ReglaControlNivelFilter filter = (ReglaControlNivelFilter) genericFilter;
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
