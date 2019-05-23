package org.chobit.jspy.model;


import java.util.LinkedList;
import java.util.List;

public class Series<T> {

    private String name;

    private ChartType type;

    private final List<T> data = new LinkedList<>();


    public Series() {
    }

    public Series(String name) {
        this.name = name;
    }

    public void addData(T d) {
        data.add(d);
    }

    public String getName() {
        return name;
    }

    public ChartType getType() {
        return type;
    }

    public void setType(ChartType type) {
        this.type = type;
    }

    public List<T> getData() {
        return data;
    }

}
