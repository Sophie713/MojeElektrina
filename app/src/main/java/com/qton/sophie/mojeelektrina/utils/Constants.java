package com.qton.sophie.mojeelektrina.utils;

import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public abstract class Constants {
    public static BarGraphSeries<DataPoint> graphDayMe = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphMonthMe = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphWeekMe = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphYearMe = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphDayTown = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphMonthTown = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphWeekTown = new BarGraphSeries();
    public static BarGraphSeries<DataPoint> graphYearTown = new BarGraphSeries();
}
