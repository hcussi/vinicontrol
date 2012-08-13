package edu.utn.frm.dao.comunicacion;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.comunicacion.ConexionIP;
import edu.utn.frm.entities.usuario.Usuario;

public class ConexionIPDao extends GenericDao<ConexionIP, Long> {

    public ConexionIPDao(Usuario currentUser) {
        super(ConexionIP.class, currentUser);
    }

    public ConexionIPDao() {
        super(ConexionIP.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ConexionIPFilter filter = (ConexionIPFilter) genericFilter;
        builder.appendSchema(getEntityName(), "cip");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("cip", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("cip", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }

        if (!filter.isNullIp()) {
            builder.addAndClause();
            builder.appendFieldClause("cip", "direccionIPv4", "like", "direccionIPv4", "%" + filter.getIp() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
