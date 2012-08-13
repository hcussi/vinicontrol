/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.regla;

import edu.utn.frm.entities.base.EntityBase;
import edu.utn.frm.entities.utils.DateOperations;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jdom.Element;

/**
 *
 * @author Proyecto
 */
@Entity
@Table(name = "REGLA_CONTROL_REMONTAJE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"}),
    @UniqueConstraint(columnNames = {"descripcion"})
})
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "ReglaControlRemontaje.findAll", query = "select e from ReglaControlRemontaje e "),
    @NamedQuery(name = "ReglaControlRemontaje.findById", query = "select e from ReglaControlRemontaje e where id = :id")
})
public class ReglaControlRemontaje extends EntityBase {

    private String codigo;
    private String descripcion;
    private List<RemontajeDia> remontajesDia;

    public ReglaControlRemontaje() {
        remontajesDia = new ArrayList<RemontajeDia>();
    }

    /**
     * @return the codigo
     */
    @NotNull(message = "El Código de la Regla de Control de Remontaje no puede ser nulo")
    @Length(min = 3, max = 10, message = "El Código de la Regla de Control de Remontaje debe estar entre {min} y {max} caracteres")
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
    @NotNull(message = "La Descripción de la Regla de Control de Remontaje no puede ser nulo")
    @Length(min = 5, max = 50, message = "La Descripción de la Regla de Control de Remontaje debe estar entre {min} y {max} caracteres")
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
     * @return the remontajesDia
     */
    @OneToMany(mappedBy = "reglaControlRemontaje", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = RemontajeDia.class)
    public List<RemontajeDia> getRemontajesDia() {
        return remontajesDia;
    }

    /**
     * @param remontajesDia the remontajesDia to set
     */
    public void setRemontajesDia(List<RemontajeDia> remontajesDia) {
        this.remontajesDia = remontajesDia;
    }

    public void addRemontajeDia(RemontajeDia remontajeDia) {
        if (!this.remontajesDia.contains(remontajeDia)) {
            this.remontajesDia.add(remontajeDia);
            remontajeDia.setReglaControlRemontaje(this);
        }
    }

    public void remove(RemontajeDia remontajeDia) {
        this.remontajesDia.remove(remontajeDia);
        //subCuentaMayor.setCuentaMayor(null);
    }

    public ReglaControlRemontaje copy() {
        ReglaControlRemontaje copy = new ReglaControlRemontaje();

        copy.setCodigo(this.getCodigo() + "-copy");
        copy.setDescripcion(this.getDescripcion() + "-copy");
        for (RemontajeDia item : remontajesDia) {
            copy.addRemontajeDia(item.copy());
        }

        return copy;
    }

    @Override
    @Transient
    public String getConstraintErrorMessage() {
        return "No se puede guardar, el código/descripción ya existe";
    }

    public Element exportar() {
        Element elementRegla = new Element("reglaControlRemontaje");

        for (RemontajeDia remontajeDia : remontajesDia) {
            elementRegla.addContent(remontajeDia.exportar());
        }

        return elementRegla;
    }

    public boolean isNecesarioRemontar(Date fechaInicioProceso) {
        boolean remontar = false;

        Date fechaHoy = new Date();
        Calendar cFechaHoy = Calendar.getInstance();
        cFechaHoy.setTime(fechaHoy);

        int dias = new DateOperations(fechaInicioProceso, fechaHoy).getDayDiff();

        int cantidadRemontajes = getRemontajesDia().size();
        int posicion = dias;
        
        while (posicion > (cantidadRemontajes - 1 ) ) {
             posicion = posicion - cantidadRemontajes;
        }

        RemontajeDia remontajeDia = getRemontajesDia().get(posicion);

        for (MomentoRemontaje momentoRemontaje : remontajeDia.getMomentosRemontajes()) {
            Date horaInicio = momentoRemontaje.getHora();
            Date horaFin = DateOperations.add(horaInicio, momentoRemontaje.getDuracion(), DateOperations.MINUTE);

            Calendar cHoraInicio = Calendar.getInstance();
            cHoraInicio.setTime(horaInicio);
            cHoraInicio.set(cFechaHoy.get(Calendar.YEAR), cFechaHoy.get(Calendar.MONTH),cFechaHoy.get(Calendar.DAY_OF_MONTH));
            Date fechaInicio = cHoraInicio.getTime();

            Calendar cHoraFin = Calendar.getInstance();
            cHoraFin.setTime(horaFin);
            cHoraFin.set(cFechaHoy.get(Calendar.YEAR), cFechaHoy.get(Calendar.MONTH),cFechaHoy.get(Calendar.DAY_OF_MONTH));
            Date fechaFin = cHoraFin.getTime();

            if( DateOperations.compareMinorTime(fechaInicio, fechaHoy) &&
                    DateOperations.compareMayorTime(fechaFin, fechaHoy) ){
                    remontar = true;
            }
        }
        return remontar;
    }

    private boolean remontando;

    @Transient
    public boolean isRemontando() {
        return remontando;
    }

    public void setRemontando(boolean remontando) {
        this.remontando = remontando;
    }
}
