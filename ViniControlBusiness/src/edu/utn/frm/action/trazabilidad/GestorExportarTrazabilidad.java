/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.action.trazabilidad;

import edu.utn.frm.action.exception.TrazabilidadException;
import edu.utn.frm.action.file.JFileChooser;
import edu.utn.frm.entities.proceso.Proceso;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Proyecto
 */
public class GestorExportarTrazabilidad {

    private Proceso proceso;
    private JInternalFrame frame;
    private JFileChooser jFileChooser;

    public GestorExportarTrazabilidad(Proceso proceso, JInternalFrame frame, JFileChooser jFileChooser) {
        this.proceso = proceso;
        this.frame = frame;
        this.jFileChooser = jFileChooser;
    }

    public void exportar() throws TrazabilidadException {
        if (proceso != null) {
            Element elementProceso = proceso.exportar();

            Document document = new Document(elementProceso);
            byte[] file = null;

            try {
                XMLOutputter out = new XMLOutputter();
                Format format = out.getFormat();
                //por defecto es UTF-8
                format.setEncoding("ISO-8859-1");
                out.setFormat(format);
                StringBuffer s = new StringBuffer(out.outputString(document));
                file = s.toString().getBytes();
                guardarArchivo(file);
            } catch (Exception e) {
                throw new TrazabilidadException("La exportación de trazabilidad ha fallado");
            }
        }
    }

    private void guardarArchivo(byte[] fileArray) throws TrazabilidadException {
        int result = jFileChooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            if (file == null) {
                JOptionPane.showMessageDialog(frame, "No se ha seleccionado ningún archivo");
                return;
            } else {
                try {
                    OutputStream stream = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(stream);
                    bos.write(fileArray);
                    bos.flush();
                    stream.close();
                    bos.close();
                } catch (IOException ex) {

                    throw new TrazabilidadException("Se ha producido un error al generar " +
                            "el informe. " + ex.getMessage());
                } catch (Exception e) {
                    throw new TrazabilidadException("La generación del informe ha fallado");
                }
            }
        }
    }
}
