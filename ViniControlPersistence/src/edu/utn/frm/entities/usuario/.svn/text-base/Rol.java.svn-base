/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.usuario;

import edu.utn.frm.entities.base.EntityBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CacheModeType;
import org.hibernate.annotations.FlushModeType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="ROL", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="Rol.findAll", query="select e from Rol e ",flushMode=FlushModeType.NEVER,cacheable=true,cacheMode=CacheModeType.REFRESH,cacheRegion="rol",timeout=60),
	@NamedQuery(name="Rol.findById", query="select e from Rol e where id = :id",flushMode=FlushModeType.NEVER,cacheable=true,cacheMode=CacheModeType.REFRESH,cacheRegion="rol",timeout=60)
})
public class Rol extends EntityBase{

    private String codigo;
    private String descripcion;
    private List<Privilegio> privilegios;
    private boolean seleccionado;

    public Rol(){
        privilegios = new ArrayList<Privilegio>();
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código del Rol no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código del Rol  debe estar entre {min} y {max} caracteres")
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    @NotNull(message = "La Descripción del Rol no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción del Rol debe estar entre {min} y {max} caracteres")
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the privilegios
     */
    @ManyToMany(fetch=FetchType.LAZY, targetEntity=Privilegio.class)
    public List<Privilegio> getPrivilegios() {
        return privilegios;
    }

    /**
     * @param privilegios the privilegios to set
     */
    public void setPrivilegios(List<Privilegio> privilegios) {
        this.privilegios = privilegios;
    }

    /**
     * @return the seleccionado
     */
    @Transient
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Rol copy() {
        Rol copy = new Rol();
        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");

        for (Privilegio privilegio : privilegios) {
            copy.getPrivilegios().add( privilegio );
        }

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }
    
}
