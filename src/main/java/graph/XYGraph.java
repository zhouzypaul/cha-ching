package main.java.graph;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;

public class XYGraph extends ApplicationFrame {

    public XYGraph(String applicationTitle, String chartTitle,
                   ArrayList<Float> netWorth, ArrayList<Float> portfolioWorth, ArrayList<Float> stockWorth) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Time",
                "Price",
                createDataset(netWorth, portfolioWorth, stockWorth),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(750, 550));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        renderer.setSeriesStroke(2, new BasicStroke(1.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset(ArrayList<Float> netWorth, ArrayList<Float> portfolioWorth, ArrayList<Float> stockWorth) {

        final XYSeries worth = new XYSeries("Net Worth");
        for (int i = 0; i < netWorth.size(); i++) {
            worth.add(i, netWorth.get(i));
        }

        final XYSeries portfolio = new XYSeries("Portfolio Worth");
        for (int i = 0; i < portfolioWorth.size(); i++) {
            portfolio.add(i, portfolioWorth.get(i));
        }

        final XYSeries stock = new XYSeries("Stock Worth");
        for (int i = 0; i < stockWorth.size(); i++) {
            stock.add(i, stockWorth.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(worth);
        dataset.addSeries(portfolio);
        dataset.addSeries(stock);
        return dataset;
    }

    public static void main(String[] args) {
//        XYGraph chart = new XYGraph("Stock Market", "Price Over Time");
//        chart.pack();
//        chart.setVisible(true);
    }
}
