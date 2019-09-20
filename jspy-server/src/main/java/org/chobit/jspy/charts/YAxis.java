package org.chobit.jspy.charts;

public class YAxis {

    private int index;

    private ValueType valType;

    private String unit;


    public YAxis(int index) {
        this.index = index;
    }

    public ValueType getValType() {
        return valType;
    }

    public void setValType(ValueType valType) {
        this.valType = valType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getIndex() {
        return index;
    }
}
