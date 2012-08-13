/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.action.reporte;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Proyecto
 */
public class GestorReporte {

    private static Logger logger = Logger.getLogger(GestorReporte.class);
    private Connection conn  = null;
    private static GestorReporte instance;

    private GestorReporte() {
        try {
            logger.info("Iniciando la conexión contra la base de datos para generar los reportes");
            Class.forName("com.mysql.jdbc.Driver"); //se carga el driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vinicontrol", "root", "root");
            logger.info("Fin de la configuración de la conexión");
        } catch (Exception ex) {
            logger.error("La configuración de la conexión para la generación de reportes ha fallado");
            logger.error(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Conexión para generar los reportes ha fallado");
        }
    }

    public static GestorReporte getInstance(){
        if(instance == null){
            instance = new GestorReporte();
        }
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }
}