package org.chobit.jspy.charts;


import java.util.LinkedList;
import java.util.List;

public class Series<T> {

    private String id;

    private String name;

    private String symbol = "none";

    private ChartType type = ChartType.line;

    private final List<T> data = new LinkedList<>();


    public Series() {
    }

    public Series(String name) {
        this.name = name;
    }

    public Series(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addData(T d) {
        data.add(d);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
