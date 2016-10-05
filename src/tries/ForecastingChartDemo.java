package tries;

import java.util.Date;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.Forecaster;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.MovingAverageModel;
import net.sourceforge.openforecast.models.NaiveForecastingModel;
import net.sourceforge.openforecast.models.RegressionModel;
import net.sourceforge.openforecast.models.PolynomialRegressionModel;

/**
 * An example of a time series chart, showing an initial series of
 * observations (the first data series), overlaid with forecasts produced
 * using a variety of different forecasting models. This both demonstrates
 * the use of JFreeChart, as well as provides a graphical comparison of
 * some of the different forecasting models.
 */
public class ForecastingChartDemo extends ApplicationFrame
{
    /** The set of data points for which forecast values are required. */
    private TimeSeries fc;
    
    /**
     * A demonstration application showing a quarterly time series
     * along with the forecast values.
     * @param title the frame title.
     */
    public ForecastingChartDemo(String title)
    {
        super(title);
        
        // Create a title...
        String chartTitle = "Графіки прогнозів";
        XYDataset dataset = createDataset();
        
        JFreeChart chart
            = ChartFactory.createTimeSeriesChart(chartTitle,
                                                 "Дата",
                                                 "Квартальні продажі",
                                                 dataset,
                                                 true,  // Legend
                                                 true,  // Tooltips
                                                 false);// URLs
        
        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer)
            {
                StandardXYItemRenderer r = (StandardXYItemRenderer) renderer;
                r.setPlotLines(true);
                r.setBaseShapesVisible(true);
            }
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     * @return the dataset.
     */
    public XYDataset createDataset()
    {
        TimeSeries observations
            = new TimeSeries("Квартальні продажі", Quarter.class);
        
        observations.add(new Quarter(1,2010), 362.0);
        observations.add(new Quarter(2,2010), 385.0);
        observations.add(new Quarter(3,2010), 432.0);
        observations.add(new Quarter(4,2010), 341.0);
        observations.add(new Quarter(1,2011), 382.0);
        observations.add(new Quarter(2,2011), 409.0);
        observations.add(new Quarter(3,2011), 498.0);
        observations.add(new Quarter(4,2011), 387.0);
        observations.add(new Quarter(1,2012), 473.0);
        observations.add(new Quarter(2,2012), 513.0);
        observations.add(new Quarter(3,2012), 582.0);
        observations.add(new Quarter(4,2012), 474.0);
        observations.add(new Quarter(1,2013), 544.0);
        observations.add(new Quarter(2,2013), 582.0);
        observations.add(new Quarter(3,2013), 681.0);
        observations.add(new Quarter(4,2013), 557.0);
        observations.add(new Quarter(1,2014), 628.0);
        observations.add(new Quarter(2,2014), 707.0);
        observations.add(new Quarter(3,2014), 773.0);
        observations.add(new Quarter(4,2014), 592.0);
        observations.add(new Quarter(1,2015), 627.0);
        observations.add(new Quarter(2,2015), 725.0);
        observations.add(new Quarter(3,2015), 854.0);
        observations.add(new Quarter(4,2015), 661.0);
        
        fc = new TimeSeries("Значення прогнозів", Quarter.class);
        fc.add(new Quarter(1,2010), 0.0);
        fc.add(new Quarter(2,2010), 0.0);
        fc.add(new Quarter(3,2010), 0.0);
        fc.add(new Quarter(4,2010), 0.0);
        fc.add(new Quarter(1,2011), 0.0);
        fc.add(new Quarter(2,2011), 0.0);
        fc.add(new Quarter(3,2011), 0.0);
        fc.add(new Quarter(4,2011), 0.0);
        fc.add(new Quarter(1,2012), 0.0);
        fc.add(new Quarter(2,2012), 0.0);
        fc.add(new Quarter(3,2012), 0.0);
        fc.add(new Quarter(4,2012), 0.0);
        fc.add(new Quarter(1,2013), 0.0);
        fc.add(new Quarter(2,2013), 0.0);
        fc.add(new Quarter(3,2013), 0.0);
        fc.add(new Quarter(4,2013), 0.0);
        fc.add(new Quarter(1,2014), 0.0);
        fc.add(new Quarter(2,2014), 0.0);
        fc.add(new Quarter(3,2014), 0.0);
        fc.add(new Quarter(4,2014), 0.0);
        fc.add(new Quarter(1,2015), 0.0);
        fc.add(new Quarter(2,2015), 0.0);
        fc.add(new Quarter(3,2015), 0.0);
        fc.add(new Quarter(4,2015), 0.0);
        fc.add(new Quarter(1,2016), 0.0);
        fc.add(new Quarter(2,2016), 0.0);
        fc.add(new Quarter(3,2016), 0.0);
        fc.add(new Quarter(4,2016), 0.0);
        fc.add(new Quarter(1,2017), 0.0);
        fc.add(new Quarter(2,2017), 0.0);
        fc.add(new Quarter(3,2017), 0.0);
        fc.add(new Quarter(4,2017), 0.0);
        fc.add(new Quarter(1,2018), 0.0);
        fc.add(new Quarter(2,2018), 0.0);
        fc.add(new Quarter(3,2018), 0.0);
        fc.add(new Quarter(4,2018), 0.0);
        
        DataSet initDataSet = getDataSet( observations, 0, 100 );
        
        
        TimeSeries naiveSeries
            = getForecastTimeSeries(new NaiveForecastingModel(),
                                    initDataSet,
                                    1, 25,
                                    "Наївний прогноз");
        TimeSeries ma4Series
            = getForecastTimeSeries(new MovingAverageModel(4),
                                    initDataSet,
                                    4, 28,
                                    "Змінне середнє з періодом 4");
        TimeSeries ma8Series
            = getForecastTimeSeries(new MovingAverageModel(8),
                                    initDataSet,
                                    8, 32,
                                    "Змінне середнє з періодом 8");
        TimeSeries regressionSeries
            = getForecastTimeSeries(new RegressionModel("t"),
                                    initDataSet,
                                    0, 100,
                                    "Лінійна регресія");
        TimeSeries polyRegressSeries
            = getForecastTimeSeries(new PolynomialRegressionModel("t",4),
                                    initDataSet,
                                    0, 100,
                                    "Поліноміальна регресія регресія 4-го порядку");
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(observations);
        dataset.addSeries(naiveSeries);
        dataset.addSeries(ma4Series);
        dataset.addSeries(ma8Series);
        dataset.addSeries(regressionSeries);
        dataset.addSeries(polyRegressSeries);
        
        return dataset;
    }
    
    /**
     * A helper function to convert data points (from startIndex to
     * endIndex) of a (JFreeChart) TimeSeries object into an
     * OpenForecast DataSet.
     * @param series the series of data points stored as a JFreeChart
     * TimeSeries object.
     * @param startIndex the index of the first data point required from the
     * series.
     * @param endIndex the index of the last data point required from the
     * series.
     * @return an OpenForecast DataSet representing the data points extracted
     * from the TimeSeries.
     */
    private DataSet getDataSet( TimeSeries series,
                                int startIndex, int endIndex )
    {
        DataSet dataSet = new DataSet();
        if ( endIndex > series.getItemCount() )
            endIndex = series.getItemCount();
        
        for ( int i=startIndex; i<endIndex; i++ )
            {
                TimeSeriesDataItem dataPair = series.getDataItem(i);
                DataPoint dp = new Observation( dataPair.getValue().doubleValue() );
                dp.setIndependentValue( "t", i );
                dataSet.add( dp );
            }
        
        return dataSet;
    }
    
    /**
     * Use the given forecasting model to produce a TimeSeries object
     * representing the periods startIndex through endIndex, and containing
     * the forecast values produced by the model.
     * @param model the forecasting model to use to generate the forecast
     * series.
     * @param initDataSet data set to use to initialize the forecasting model.
     * @param startIndex the index of the first data point to use from the
     * set of potential forecast values.
     * @param endIndex the index of the last data point to use from the set
     * of potential forecast values.
     * @param title a title to give to the TimeSeries created.
     */
    private TimeSeries getForecastTimeSeries( ForecastingModel model,
                                              DataSet initDataSet,
                                              int startIndex,
                                              int endIndex,
                                              String title )
    {
        // Initialize the forecasting model
        model.init( initDataSet );
        
        // Get range of data required for forecast
        DataSet fcDataSet = getDataSet( fc, startIndex, endIndex );
        
        // Obtain forecast values for the forecast data set
        model.forecast( fcDataSet );
        
        // Create a new TimeSeries
        TimeSeries series
            = new TimeSeries(title,fc.getTimePeriodClass());
        
        // Iterator through the forecast results, adding to the series
        Iterator it = fcDataSet.iterator();
        while ( it.hasNext() )
            {
                DataPoint dp = (DataPoint)it.next();
                int index = (int)dp.getIndependentValue("t");
                series.add( fc.getTimePeriod(index), dp.getDependentValue() );
            }
        
        return series;
    }
    
    public static void main(String[] args)
    {
        ForecastingChartDemo demo
            = new ForecastingChartDemo("Прогнози");
        demo.pack();
        demo.setVisible(true);
    }


}