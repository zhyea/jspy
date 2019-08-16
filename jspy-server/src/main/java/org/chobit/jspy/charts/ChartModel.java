package org.chobit.jspy.charts;

import java.util.*;

public class ChartModel {


    private String title;

    private final List<String> legend = new LinkedList<>();

    private final Map<String, Boolean> legendSelected = new LinkedHashMap<>();

    private final List<Series> series = new LinkedList<>();

    private final List<Object> xAxis = new LinkedList<>();

    private final List<YAxis> yAxises = new LinkedList<>();

    private int interval;


    public ChartModel() {
    }

    public ChartModel(String title) {
        this.title = title;
    }

    public void addSeries(Series s) {
        this.series.add(s);
        this.legend.add(s.getName());
        // 如果legend中的元素数量超过6个则换行
        if (this.legend.size() == 6) {
            legend.add("");
        }
        if (!s.isSelected()) {
            legendSelected.put(s.getName(), false);
        }
    }

    public void addXAxis(Object a) {
        xAxis.add(a);
    }


    public void addYAxis(YAxis axis) {
        yAxises.add(axis);
    }


    public void addYAxises(Collection<YAxis> axises) {
        yAxises.addAll(axises);
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

    public Map<String, Boolean> getLegendSelected() {
        return legendSelected;
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

    public List<YAxis> getyAxises() {
        return yAxises;
    }
}
