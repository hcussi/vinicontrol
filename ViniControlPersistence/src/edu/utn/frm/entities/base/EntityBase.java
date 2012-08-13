package edu.utn.frm.entities.base;

import edu.utn.frm.entities.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public abstract class EntityBase implements Serializable,ICopiable<EntityBase> {

    private Long id;
    private Date createDate;
    private Date lastUpdateDate;
    private boolean anulado;
    private Long version;
    private String oid;
    private String userCreate;
    private String userLastUpdate;

    public EntityBase() {
        super();
        this.oid = UUID.randomUUID().toString();
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdate) {
        this.lastUpdateDate = lastUpdate;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    @Transient
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
    private Usuario currentUser;

    /**
     * El usuario que esta creando o actualizando esta entidad
     */
    @Transient
    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Setea valores de auditoria en el momento previo de la creaci�n
     */
    @PrePersist
    public void PrePersist() {
        if (getCreateDate() == null) {
            setCreateDate(new Date());
        }
        if (getLastUpdateDate() == null) {
            setLastUpdateDate(new Date());
        }
        if (getUserCreate() == null && currentUser != null) {
            setUserCreate(currentUser.getUsuario());
        }
        if (getUserLastUpdate() == null && currentUser != null) {
            setUserLastUpdate(currentUser.getUsuario());
        }
    }

    @PreUpdate
    public void PreUpdate() {
        if (currentUser != null) {
            setUserLastUpdate(currentUser.getUsuario());
        }
        setLastUpdateDate(new Date());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityBase) {
            if (id != null) {
                return id.equals(((EntityBase) obj).id);
            } else {
                return oid.equals(((EntityBase) obj).oid);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return oid.hashCode();
        }
    }

    @Transient
    public String getOid() {
        return oid;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(String userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    @Transient
    public boolean isNew() {

        return id == null;
    }

    @Transient
    public abstract String getConstraintErrorMessage();

    /*@Transient
    public String getDatosInforme(){
        return "";
    }*/
    
}
