/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.utn.frm.entities.regla;

import edu.utn.frm.entities.base.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name="REGLA_CONTROL", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "codigo" }),
                @UniqueConstraint(columnNames = { "descripcion" })
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="ReglaControl.findAll", query="select e from ReglaControl e "),
	@NamedQuery(name="ReglaControl.findById", query="select e from ReglaControl e where id = :id")
})
public class ReglaControl extends EntityBase{

    private String codigo;
    private String descripcion;
    private ReglaControlRemontaje reglaControlRemontaje;
    private ReglaControlTemperatura reglaControlTemperatura;
    private ReglaControlNivel reglaControlNivel;
    
    public ReglaControl(){

    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Codigo de la Regla de Control no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Codigo de la Regla de Control debe estar entre {min} y {max} caracteres")
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
    @NotNull(message = "La Descripción de la Regla de Control no puede ser nula")
    @Length(min = 5, max = 50, message = "La Descripción de la Regla de Control debe estar entre {min} y {max} caracteres")
       public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name = "reglaControlNivelId")
    public ReglaControlNivel getReglaControlNivel() {
        return reglaControlNivel;
    }

    public void setReglaControlNivel(ReglaControlNivel reglaControlNivel) {
        this.reglaControlNivel = reglaControlNivel;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name = "reglaControlRemontajeId")
    public ReglaControlRemontaje getReglaControlRemontaje() {
        return reglaControlRemontaje;
    }

    public void setReglaControlRemontaje(ReglaControlRemontaje reglaControlRemontaje) {
        this.reglaControlRemontaje = reglaControlRemontaje;
    }

    @ManyToOne(optional=true)
    @JoinColumn(name = "reglaControlTemperaturaId")
    public ReglaControlTemperatura getReglaControlTemperatura() {
        return reglaControlTemperatura;
    }

    public void setReglaControlTemperatura(ReglaControlTemperatura reglaControlTemperatura) {
        this.reglaControlTemperatura = reglaControlTemperatura;
    }

    public ReglaControl copy() {
        ReglaControl copy = new ReglaControl();

        copy.setCodigo(this.getCodigo()+"-copy");
        copy.setDescripcion(this.getDescripcion()+"-copy");
        copy.setReglaControlNivel(this.getReglaControlNivel());
        copy.setReglaControlRemontaje(this.getReglaControlRemontaje());
        copy.setReglaControlTemperatura(this.getReglaControlTemperatura());

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

}