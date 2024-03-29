/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.ui.estado;

import edu.utn.frm.entities.proceso.Proceso;
import edu.utn.frm.entities.regla.ReglaControlTemperatura;
import edu.utn.frm.entities.tanque.EstadoTanque;
import edu.utn.frm.entities.tanque.demanda.PrediccionTendenciaTemperatura;
import edu.utn.frm.entities.utils.DateOperations;
import edu.utn.frm.ui.IconManager;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Proyecto
 */
public class GestorEstadoTanqueView {

    private static final int PANEL_WIDTH = 195;
    private static final int PANEL_HEIGHT = 140;

    public GestorEstadoTanqueView() {
    }

    public void showProporcion(Proceso proceso) {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Temperatura", proceso.getAlarmasTemperatura().size());
        data.setValue("Nivel Azúcar", proceso.getAlarmasNivelAzucar().size());
        data.setValue("Nivel Contenido", proceso.getAlarmasNivelCapacidad().size());
        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart("Proporción de Alarmas", data, true, true, false);
        // create and display a frame...
        crearChartFrame(proceso, "Proporción de Alarmas ", chart);
    }

    private void crearChartFrame(Proceso proceso, String title, JFreeChart chart) {
        ChartFrame frame = new ChartFrame(title + getTitle(proceso), chart);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setIconImage(IconManager.getInstance().getMainIcon());
        frame.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
        frame.setVisible(true);
    }

    private String getTitle(Proceso proceso) {
        String fecha = DateOperations.toDateString(new Date(), "/", ":");
        String timespam = "  Desde: " + proceso.getFechaInicioString() + " " + proceso.getHoraInicioString() + "   Hasta: " + fecha;
        return timespam;
    }

    public void showTemperatura(Proceso proceso, ChartPanel chartPanel) {
        ValueDataset data = null;
        if (proceso.getEstadoTanque() == null) {
            data = new DefaultValueDataset(0);
        } else {
            data = new DefaultValueDataset(proceso.getEstadoTanque().getTemperatura());
        }
        JFreeChart chart = chartPanel.getChart();
        ThermometerPlot plot = (ThermometerPlot) chart.getPlot();
        plot.setDataset(data);
    }

    public ChartPanel showTemperatura(Proceso proceso) {
        ValueDataset data = null;
        if (proceso.getEstadoTanque() == null) {
            data = new DefaultValueDataset(0);
        } else {
            data = new DefaultValueDataset(proceso.getEstadoTanque().getTemperatura());
        }
        ThermometerPlot plot = new ThermometerPlot(data);
        plot.setRange(0, 100);

        double tmax = 0;
        double tmin = 0;

        if (proceso.getReglaControl() != null) {
            ReglaControlTemperatura rct = proceso.getReglaControl().getReglaControlTemperatura();
            tmax = rct.getTemperaturaMaxima();
            tmin = rct.getTemperaturaMinima();
        }
        plot.setSubrangeInfo(ThermometerPlot.NORMAL, tmin, tmax, 0.0, 100.0);
        plot.setSubrangeInfo(ThermometerPlot.WARNING, 0, tmin, 0.0, 100.0);
        plot.setSubrangeInfo(ThermometerPlot.CRITICAL, tmax, 100.0, 0.0, 100.0);

        JFreeChart chart = new JFreeChart("Temperatura", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        chartPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumDrawWidth(PANEL_WIDTH);
        chartPanel.setMaximumDrawHeight(PANEL_HEIGHT);
        return chartPanel;
    }

    public ChartPanel showVolumen(Proceso proceso) {
        double nivelContenido = 0;
        if (proceso.getEstadoTanque() != null) {
            nivelContenido = proceso.getEstadoTanque().getNivelContenido();
        }
        double nivelMaximoCapacidad = 0;
        if (proceso.getReglaControl() != null) {
            nivelMaximoCapacidad = proceso.getReglaControl().getReglaControlNivel().getNivelMaximoCapacidad();
        }

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.addValue(nivelContenido, "Nivel Actual", "");
        data.addValue(nivelMaximoCapacidad, "Nivel Máximo", "");
        // create a chart...
        JFreeChart chart = ChartFactory.createBarChart("Nivel del Tanque", "Referencia", "Volumen", data, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        chartPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumDrawWidth(PANEL_WIDTH);
        chartPanel.setMaximumDrawHeight(PANEL_HEIGHT);

        return chartPanel;
    }

    public void showVolumen(Proceso proceso, ChartPanel chartPanel) {
        double nivelContenido = 0;
        if (proceso.getEstadoTanque() != null) {
            nivelContenido = proceso.getEstadoTanque().getNivelContenido();
        }
        double nivelMaximoCapacidad = 0;
        if (proceso.getReglaControl() != null) {
            nivelMaximoCapacidad = proceso.getReglaControl().getReglaControlNivel().getNivelMaximoCapacidad();
        }

        DefaultCategoryDataset data = null;
        data = new DefaultCategoryDataset();
        data.addValue(nivelContenido, "Nivel Actual", "");
        data.addValue(nivelMaximoCapacidad, "Nivel Máximo", "");

        JFreeChart chart = chartPanel.getChart();
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDataset(data);
    }

    public ChartPanel showNivelAzucar(Proceso proceso) {
        double nivelAzucar = 0;
        if (proceso.getEstadoTanque() != null) {
            nivelAzucar = proceso.getEstadoTanque().getNivelAzucar();
        }
        double nivelMaximoCapacidad = 0;

        if (proceso.getReglaControl() != null) {
            nivelMaximoCapacidad = proceso.getReglaControl().getReglaControlNivel().getNivelMinimoAzucar();
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nivel", nivelAzucar);

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.addValue(nivelAzucar, "Nivel Actual", "");
        data.addValue(nivelMaximoCapacidad, "Nivel Mínimo", "");
        // create a chart...
        JFreeChart chart = ChartFactory.createBarChart("Nivel de Azúcar", "Referencia", "Volumen", data, PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        chartPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        chartPanel.setMaximumDrawWidth(PANEL_WIDTH);
        chartPanel.setMaximumDrawHeight(PANEL_HEIGHT);

        return chartPanel;
    }

    public void showNivelAzucar(Proceso proceso, ChartPanel chartPanel) {
        double nivelAzucar = 0;
        if (proceso.getEstadoTanque() != null) {
            nivelAzucar = proceso.getEstadoTanque().getNivelAzucar();
        }
        double nivelMaximoCapacidad = 0;
        if (proceso.getReglaControl() != null) {
            nivelMaximoCapacidad = proceso.getReglaControl().getReglaControlNivel().getNivelMinimoAzucar();
        }
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nivel", nivelAzucar);

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        data.addValue(nivelAzucar, "Nivel Actual", "");
        data.addValue(nivelMaximoCapacidad, "Nivel Mínimo", "");

        JFreeChart chart = chartPanel.getChart();
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDataset(data);
    }

    public void mostrarEvolucionTemperatura(Proceso proceso) {
        List<Double> temperaturas = armarArregloTemperatura(proceso.getEstadosTanque());
        List<Double> temperaturasPredecidas = armarArregloTemperaturaPredicha(proceso.getPrediccionesTendencia());

        XYSeries series = new XYSeries("Evolución de la Temperatura");
        XYSeries series2 = new XYSeries("Temperatura Predicha");
        int i = 1;
        for (Double temperatura : temperaturas) {
            series.add(new Double(i++), temperatura);
        }
        i = 1;
        for (Double temperatura : temperaturasPredecidas) {
            series2.add(new Double(i++), temperatura);
        }

        XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series);
        data.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Evolución de la Temperatura", "Momentos", "Temperatura", data,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setBackgroundPaint(Color.white);
        xyplot.setDomainGridlinePaint(Color.gray);
        xyplot.setRangeGridlinePaint(Color.gray);
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(true);

        // create and display a frame...
        crearChartFrame(proceso, "Evolución de la Temperatura ", chart);
    }

    public void mostrarEvolucionNivelAzucar(Proceso proceso) {
        List<Double> azucar = armarArregloNivelAzucar(proceso.getEstadosTanque());

        XYSeries series = new XYSeries("Evolución del Nivel de Azúcar");
        int i = 1;
        for (Double azucarItem : azucar) {
            series.add(new Double(i++), azucarItem);
        }

        XYDataset data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Evolución del Nivel de Azúcar", "Momentos", "Azúcar", data,
                PlotOrientation.VERTICAL, true, true, false);

        // create and display a frame...
        crearChartFrame(proceso, "Evolución del Nivel de Azúcar ", chart);
    }

    public void mostrarEvolucionVolumen(Proceso proceso) {
        List<Double> volumen = armarArregloVolumen(proceso.getEstadosTanque());

        XYSeries series = new XYSeries("Evolución del Nivel de Contenido");
        int i = 1;
        for (Double volumenItem : volumen) {
            series.add(new Double(i++), volumenItem);
        }

        XYDataset data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Evolución del Nivel de Contenido", "Momentos", "Litros", data,
                PlotOrientation.VERTICAL, true, true, false);

        // create and display a frame...
        crearChartFrame(proceso, "Evolución del Nivel de Contenido ", chart);
    }

    private List<Double> armarArregloTemperatura(List<EstadoTanque> estadosTanque) {
        List<Double> temperaturas = new ArrayList<Double>();

        for (EstadoTanque estadoTanque : estadosTanque) {
            temperaturas.add(estadoTanque.getTemperatura());
        }
        return temperaturas;
    }

    private List<Double> armarArregloTemperaturaPredicha(List<PrediccionTendenciaTemperatura> tendenciaTemperaturas) {
        List<Double> temperaturas = new ArrayList<Double>();

        for (PrediccionTendenciaTemperatura ptt : tendenciaTemperaturas) {
            temperaturas.add(ptt.getPromedioAnterior());
        }
        return temperaturas;
    }

    private List<Double> armarArregloNivelAzucar(List<EstadoTanque> estadosTanque) {
        List<Double> azucar = new ArrayList<Double>();

        for (EstadoTanque estadoTanque : estadosTanque) {
            azucar.add(estadoTanque.getNivelAzucar());
        }
        return azucar;
    }

    private List<Double> armarArregloVolumen(List<EstadoTanque> estadosTanque) {
        List<Double> volumenes = new ArrayList<Double>();

        for (EstadoTanque estadoTanque : estadosTanque) {
            volumenes.add(estadoTanque.getNivelContenido());
        }
        return volumenes;
    }
}
