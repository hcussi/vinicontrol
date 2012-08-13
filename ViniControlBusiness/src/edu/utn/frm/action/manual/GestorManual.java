/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.action.manual;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author Proyecto
 */
public class GestorManual {

    private static Logger logger = Logger.getLogger(GestorManual.class);
    private static GestorManual instance;

    private GestorManual() {
        
    }

    public static GestorManual getInstance(){
        if(instance == null){
            instance = new GestorManual();
        }
        return instance;
    }

    public Image getMainIcon() {
        return new ImageIcon(getClass().getResource("/edu/utn/frm/ui/resources/image/icon.png")).getImage();
    }

    public void verManual() {
        URL url = getClass().getResource("/edu/utn/frm/ui/resources/manual/manual.pdf");

        // build a component controller
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder( controller );

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        JFrame applicationFrame = new JFrame();
        applicationFrame.setIconImage(getMainIcon());
        applicationFrame.setTitle("Manual de Usuario - ViniControl");
        applicationFrame.getContentPane().add( viewerComponentPanel );

        // Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(url);
        
        // show the component
        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

    
}