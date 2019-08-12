package org.chobit.jspy.charts;


import java.util.LinkedList;
import java.util.List;

public class Series {

    private String id;

    private String name;

    private String symbol = "none";

    private ChartType type = ChartType.line;

    private boolean selected;

    private int yAxisIndex = 0;

    private String unit = "";

    private ValueType valType;

    private final List<Object> data = new LinkedList<>();


    public Series() {
    }

    public Series(String name) {
        this.name = name;
    }

    public Series(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addData(Object d) {
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(int yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ValueType getValType() {
        return valType;
    }

    public void setValType(ValueType valType) {
        this.valType = valType;
    }

    public List<Object> getData() {
        return data;
    }

}
