package edu.utn.frm.dao.sistema;

import edu.utn.frm.dao.generic.GenericDao;
import edu.utn.frm.dao.generic.GenericFilter;
import edu.utn.frm.dao.generic.hql.HqlBuilder;
import edu.utn.frm.entities.sistema.ConfiguracionSistema;
import edu.utn.frm.entities.usuario.Usuario;
import java.util.List;

public class ConfiguracionSistemaDao extends GenericDao<ConfiguracionSistema, Long> {

    public ConfiguracionSistemaDao(Usuario currentUser) {
        super(ConfiguracionSistema.class,currentUser);
    }

    public ConfiguracionSistemaDao() {
        super(ConfiguracionSistema.class);
    }

    @Override
    protected void parseFilter(HqlBuilder builder, GenericFilter genericFilter) {
        ConfiguracionSistemaFilter filter = (ConfiguracionSistemaFilter) genericFilter;
        builder.appendSchema(getEntityName(), "cs");

        if (filter.isEmpty()) {
            return;
        }

        builder.appendWhereClause();

        if (!filter.isNullEmpresa()) {
            builder.addAndClause();
            builder.appendFieldClause("cs", "empresa", "like", "empresa", "%" + filter.getEmpresa() + "%");
        }
        if (!filter.isNullCuit()) {
            builder.addAndClause();
            builder.appendFieldClause("cs", "cuit", "like", "cuit", "%" + filter.getCuit() + "%");
        }

        if (!filter.isNullAnulada()) {
            builder.addAndClause();
            builder.appendFieldClause("cs", "anulado", "=", "anulado", filter.isAnulada());
        }
    }

    public ConfiguracionSistema getConfiguracionSistema(){

        HqlBuilder builder = new HqlBuilder(getVinicEM());
        builder.appendSchema(getEntityName(), "cs");

        List<ConfiguracionSistema> configuraciones =  builder.executeList();

        return configuraciones.isEmpty() ? null : configuraciones.get(0);
    }
}
