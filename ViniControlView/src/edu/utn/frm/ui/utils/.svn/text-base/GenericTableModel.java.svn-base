/*
 * ModeloTablaCtaCte.java
 *
 * Created on 14 de noviembre de 2006, 14:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package edu.utn.frm.ui.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author cristian
 */
public class GenericTableModel<T> extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    protected List<T> filas; // cada propiedad representa una columna
    protected String[] namesColum;
    protected String[] namesPropertys;
    protected Boolean[] editingPropertys;

    public GenericTableModel() {
    }

    public GenericTableModel(String[] namesColum, String[] namesPropertys) {
        Boolean[] editingPropertys = new Boolean[namesColum.length];

        for (int i = 0; i < namesColum.length; i++) {
            editingPropertys[i] = new Boolean(false);
        }

        init(namesColum, namesPropertys, editingPropertys);

    }

    public GenericTableModel(String[] namesColum, String[] namesPropertys,
            Boolean[] editingPropertys) {
        init(namesColum, namesPropertys, editingPropertys);

    }

    private void init(String[] namesColum, String[] namesPropertys,
            Boolean[] editingPropertys) {
        if (namesColum.length == namesPropertys.length || namesColum.length == editingPropertys.length) {
            filas = new ArrayList<T>();
            this.namesColum = namesColum;
            this.namesPropertys = namesPropertys;
            this.editingPropertys = editingPropertys;
        } else {
            try {
                throw new Exception(
                        "Los parametros namesColum, namesPropertys y editingPropertys deben tener la misma longitud, (namesColum.length:" + namesColum.length + ") != (" + namesPropertys.length + ":namesPropertys.length)" + "!= (" + editingPropertys.length + ":editingPropertys.length)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getColumnCount() {
        return namesColum.length;
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        T entity = null;
        try {
            entity = filas.get(rowIndex);
        } catch (IndexOutOfBoundsException ev) {
            return null;
        }

        String property = null;
        try {
            property = namesPropertys[columnIndex];
        } catch (IndexOutOfBoundsException ev) {
            return null;
        }

        return Utiles.invokeMethodGet(entity, property);

    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        if (columnIndex == -1) {
            return;
        }

        T entity = null;
        try {

            entity = filas.get(rowIndex);

        } catch (IndexOutOfBoundsException ev) {
            ev.printStackTrace();
            return;
        }
        if (entity == null) {
            return;
        }

        if (value == null) {
            return;
        }

        String nameMethod = "set" + namesPropertys[columnIndex].replaceFirst(
                namesPropertys[columnIndex].substring(0, 1),
                namesPropertys[columnIndex].substring(0, 1).toUpperCase());

        try {

            Method methodSet = null;
            for (Method method : entity.getClass().getMethods()) {
                if (method.getName().equals(nameMethod)) {
                    methodSet = method;
                    break;
                }
            }

            if (methodSet != null) {
                Object[] params = {value};
                methodSet.invoke(entity, params);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String getColumnName(int column) {
        return (column < namesColum.length) ? namesColum[column] : "";
    }

    public void addRow(T entity) {
        filas.add(entity);
        fireTableChanged(null);
    }

    public void addRows(List<T> entitys) {
        filas.addAll(entitys);
        fireTableChanged(null);
    }

    public T getRow(int index) {
        return filas.get(index);
    }

    public void removeAll() {
        filas = new ArrayList<T>();
        fireTableChanged(null);
    }

    public void removeRow(int index) {
        filas.remove(index);
        fireTableChanged(null);
    }

    public void removeRow(T entity) {
        filas.remove(entity);
        fireTableChanged(null);
    }

    public void removeRows(int[] indexs) {
        for (int i = 0; i < indexs.length; i++) {
            filas.remove(indexs[indexs.length - 1 - i]);
        }
        fireTableChanged(null);
    }

    public List<T> getRows() {
        return filas;
    }

    public void setRows(List<T> entitys) {
        filas = entitys;
        fireTableChanged(null);
    }

    public void insert(int index, T entity) {
        filas.add(index, entity);
        fireTableChanged(null);
    }

    public void fireTableChanged(TableModelEvent e) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TableModelListener.class) {
                ((TableModelListener) listeners[i + 1]).tableChanged(e);
            }
        }
    }

    @Override
    public Class getColumnClass(int c) {
        Object entity = getValueAt(0, c);
        return entity != null ? entity.getClass() : super.getColumnClass(c);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editingPropertys[columnIndex];
    }
}
