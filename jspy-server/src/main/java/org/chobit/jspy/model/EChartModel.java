package org.chobit.jspy.model;

import java.util.LinkedList;
import java.util.List;

public class EChartModel<T, X> {


    private String title;

    private final List<String> legend = new LinkedList<>();

    private final List<Series<T>> series = new LinkedList<>();

    private final List<X> xAxis = new LinkedList<>();


    public EChartModel() {
    }

    public EChartModel(String title) {
        this.title = title;
    }

    public void addSeries(Series<T> s) {
        this.series.add(s);
        this.legend.add(s.getName());
    }

    public void addXAxis(X a) {
        xAxis.add(a);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLegend() {
        return legend;
    }

    public List<Series<T>> getSeries() {
        return series;
    }

    public List<X> getxAxis() {
        return xAxis;
    }
}
