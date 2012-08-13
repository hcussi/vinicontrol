package edu.utn.frm.dao.comunicacion;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.comunicacion.ConfiguracionConexion;
import edu.utn.frm.entities.usuario.Usuario;

public class ConfiguracionConexionDao extends GenericDao<ConfiguracionConexion, Long> {

    public ConfiguracionConexionDao(Usuario currentUser) {
        super(ConfiguracionConexion.class, currentUser);
    }

    public ConfiguracionConexionDao() {
        super(ConfiguracionConexion.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ConfiguracionConexionFilter filter = (ConfiguracionConexionFilter) genericFilter;
        builder.appendSchema(getEntityName(), "cc");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullDescripcion()) {
            builder.addAndClause();
            builder.appendFieldClause("cc", "descripcion", "like", "descripcion", "%" + filter.getDescripcion() + "%");
        }
        if (!filter.isNullCodigo()) {
            builder.addAndClause();
            builder.appendFieldClause("cc", "codigo", "like", "codigo", "%" + filter.getCodigo() + "%");
        }
        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("ca", "anulado", "=", "anulado", filter.isAnulada());
        }
    }
}
