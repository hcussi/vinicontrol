package edu.utn.frm.dao.generic.hql;

public class JoinedEntity {

    private String className;
    private String alias;

    public JoinedEntity(String className, String alias) {
        super();
        this.className = className;
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }
    public String getClassName() {
        return className;
    }    
    
    @Override
    public String toString() {
        return className + " as " + alias + " ";
    }
}
