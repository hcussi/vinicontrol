package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.producto.Finca;
import edu.utn.frm.entities.usuario.Usuario;

public class FincaDao extends GenericDao<Finca, Long> {

    public FincaDao(Usuario currentUser) {
        super(Finca.class,currentUser);
    }

    public FincaDao() {
        super(Finca.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        FincaFilter filter = (FincaFilter) genericFilter;
        builder.appendSchema(getEntityName(), "fi");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullDireccion()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "direccion", "like", "direccion", "%" + filter.getDireccion() + "%");
        }
        if (!filter.isNullRazonSocial()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "razonSocial", "like", "razonSocial", "%" + filter.getRazonSocial() + "%");
        }
        if (!filter.isNullCantidadHectareas()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "cantidadHectareas", "like", "cantidadHectareas", "%" + filter.getCantidadHectareas() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("fi", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
