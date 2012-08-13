package edu.utn.frm.action.file;
/*
 * Created on May 17, 2005 by @author Tom Jacobs
 *
 */
 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
 
public class JFileChooser extends javax.swing.JFileChooser {
    private static final long serialVersionUID = 1L;
    private boolean mWarnOnWriteOver = false;
    private boolean mAncestorNull = false;
    
    public JFileChooser() {
        super();
        init();
    }
    
    private void init() {
    }
 
    public JFileChooser(String arg0) {
        super(arg0);
        init();
    }
 
    public JFileChooser(File arg0) {
        super(arg0);
        init();
    }
 
    public JFileChooser(FileSystemView arg0) {
        super(arg0);
        init();
    }
 
    public JFileChooser(File arg0, FileSystemView arg1) {
        super(arg0, arg1);
        init();
    }
 
    public JFileChooser(String arg0, FileSystemView arg1) {
        super(arg0, arg1);
        init();
    }
    
    public boolean getWarnOnWriteOver() {
        return mWarnOnWriteOver;
    }
    
    public void setWarnOnWriteOver(boolean b) {
        mWarnOnWriteOver = b;
        if (b) {
            addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent pe) {
                    //System.out.println("Here");
                    //System.out.println(pe.getPropertyName() + ": " + pe.getNewValue());
                    if (pe.getPropertyName().equals("ancestor") && pe.getNewValue() == null) {
                        mAncestorNull = true;
                        return;
                    }
                    mAncestorNull = false;
                }
            });
        }
    }

    @Override
    public File getSelectedFile() {
        File f = super.getSelectedFile();
        if (f == null) return f;
        if (getDialogType() == javax.swing.JFileChooser.SAVE_DIALOG && f.getName().indexOf(".") == -1) {
            //check if it's using file extension filter. If so, add the filter extension
            FileFilter ff = getFileFilter();
            if (ff instanceof FileExtensionFilter) {
                String ending = ((FileExtensionFilter)ff).getType();
                f = new File(f.getParent(), f.getName() + "." + ending);
            }
        }
        // now check if it's overwriting another file
        if (mWarnOnWriteOver && mAncestorNull) {
            if (f.exists()) {
                int ans = JOptionPane.showConfirmDialog(null, "" + f.getName() + " existe. Sobrescribir?", "Guardar en archivo existente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.OK_OPTION) 
                    return f;
                return null; 
            }
        }
        return f;
    }
}