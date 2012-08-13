package edu.utn.frm.dao.producto;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.producto.Camion;
import edu.utn.frm.entities.usuario.Usuario;

public class CamionDao extends GenericDao<Camion, Long> {

    public CamionDao(Usuario currentUser) {
        super(Camion.class,currentUser);
    }

    public CamionDao() {
        super(Camion.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        CamionFilter filter = (CamionFilter) genericFilter;
        builder.appendSchema(getEntityName(), "ca");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullCapacidadKilos()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "capacidadKilos", "like", "capacidadKilos", "%" + filter.getCapacidadKilos() + "%");
        }
        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullMarca()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "marca", "like", "marca", "%" + filter.getMarca() + "%");
        }
        if (!filter.isNullPatente()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "patente", "like", "patente", "%" + filter.getPatente() + "%");
        }
        if (!filter.isNullModelo()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "modelo", "like", "modelo", "%" + filter.getModelo() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
