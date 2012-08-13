package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.producto.Variedad;
import edu.utn.frm.entities.usuario.Usuario;

public class VariedadDao extends GenericDao<Variedad, Long> {

    public VariedadDao(Usuario currentUser) {
        super(Variedad.class,currentUser);
    }

    public VariedadDao() {
        super(Variedad.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        VariedadFilter filter = (VariedadFilter) genericFilter;
        builder.appendSchema(getEntityName(), "va");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("va", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("va", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullTinte()) {
            builder.addAndClause();
            builder.appendFieldClause("va", "tinte", "like", "tinte", "%" + filter.getTinte() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
