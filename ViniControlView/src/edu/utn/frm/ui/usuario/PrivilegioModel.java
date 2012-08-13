package edu.utn.frm.ui.usuario;

import edu.utn.frm.entities.usuario.Privilegio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

public class PrivilegioModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String[] columnNames = new String[]{"Seleccionado", "Descripción", "Tipo Privilegio"};
    private List<Privilegio> privilegios;
    private List<JCheckBox> checks;
    private boolean editable;

    /**
     *Constructor por defecto
     */
    public PrivilegioModel() {
        super();
        privilegios = new ArrayList<Privilegio>(0);
        checks = new ArrayList<JCheckBox>(0);
    }

    /**
     * @param moduloXML
     * @param modXML
     */
    public PrivilegioModel(List<Privilegio> privilegios, boolean editable) {
        super();
        this.privilegios = privilegios;
        this.editable = editable;
        this.checks = new ArrayList<JCheckBox>();

        //se crea un checkBox por cada modulo
        for (Privilegio privilegio : privilegios) {
            final JCheckBox checkBox = new JCheckBox();
            //si el modulo esta seleccionado se selecciona el checkBox
            checkBox.setSelected(privilegio.isSeleccionado());
            checkBox.setEnabled(editable);
            checkBox.addItemListener(new java.awt.event.ItemListener() {

                public void itemStateChanged(java.awt.event.ItemEvent e) {
                    setValueAt(checkBox, checks.indexOf(checkBox), 0);
                }
            });
            this.checks.add(checkBox);
        }
    }

    public int getColumnCount() {

        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {

        return this.columnNames[columnIndex].toString();
    }

    public int getRowCount() {

        int count = privilegios.size();

        return count;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < privilegios.size()) {
            if (columnIndex == 0) {
                return checks.get(rowIndex);
            } else if (columnIndex == 1) {
                Privilegio privilegio = privilegios.get(rowIndex);
                return privilegio.getDescripcion();
            } else if (columnIndex == 2) {
                Privilegio privilegio = privilegios.get(rowIndex);
                return privilegio.getTipoPrivilegio().getDescripcion();
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        if (editable && columnIndex == 0 && rowIndex < privilegios.size()) {
            return true;
        }

        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (editable && columnIndex == 0 && rowIndex < privilegios.size()) {
            boolean selected = ((JCheckBox) aValue).isSelected();
            privilegios.get(rowIndex).setSeleccionado(selected);
        }
    }

    public List<Privilegio> getSeleccionados() {
        List<Privilegio> seleccionados = new ArrayList<Privilegio>();

        for (Privilegio privilegio : privilegios) {
            if (privilegio.isSeleccionado()) {
                seleccionados.add(privilegio);
            }
        }

        return seleccionados;
    }
}
