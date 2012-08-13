/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.tanque;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.regla.ReglaControl;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "GRUPO_TANQUE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"}),
    @UniqueConstraint(columnNames = {"nombre"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "GrupoTanque.findAll", query = "select e from GrupoTanque e "),
    @NamedQuery(name = "GrupoTanque.findById", query = "select e from GrupoTanque e where id = :id")
})
public class GrupoTanque extends EntityBase{

    private String codigo;
    private String nombre;
    private ReglaControl reglaControl;

    public GrupoTanque() {
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código del Grupo Tanque no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código del Grupo Tanque debe estar entre {min} y {max} caracteres")
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
     * @return the nombre
     */
    @NotNull(message = "El Nombre del Grupo Tanque no puede ser nulo")
    @Length(min = 5, max = 50, message = "El Nombre del Grupo Tanque debe estar entre {min} y {max} caracteres")
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name = "reglaControlId")
    public ReglaControl getReglaControl() {
        return reglaControl;
    }

    public void setReglaControl(ReglaControl reglaControl) {
        this.reglaControl = reglaControl;
    }

    public GrupoTanque copy() {
        GrupoTanque copy = new GrupoTanque();

        copy.setCodigo(this.getCodigo() + "-copy");
        copy.setNombre(this.getNombre() + "-copy");
        copy.setReglaControl(this.getReglaControl());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/nombre ya existe";
    }
}
