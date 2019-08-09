package org.chobit.jspy.charts;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChartModel {


    private String title;

    private final List<String> legend = new LinkedList<>();

    private final Map<String, Boolean> legendUnSelected = new LinkedHashMap<>();

    private final List<Series> series = new LinkedList<>();

    private final List<Object> xAxis = new LinkedList<>();

    private int interval;


    public ChartModel() {
    }

    public ChartModel(String title) {
        this.title = title;
    }

    public void addSeries(Series s) {
        this.series.add(s);
        this.legend.add(s.getName());
        if (!s.isSelected()) {
            legendUnSelected.put(s.getName(), false);
        }
    }

    public void addXAxis(Object a) {
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

    public Map<String, Boolean> getLegendUnSelected() {
        return legendUnSelected;
    }

    public List<Series> getSeries() {
        return series;
    }

    public List<Object> getxAxis() {
        return xAxis;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
