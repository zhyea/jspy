package org.chobit.jspy.charts;

import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.chobit.jspy.core.utils.Strings.*;

/**
 * ECharts工具集合
 */
public abstract class ChartKit {


    /**
     * 坐标轴上刻度的数量
     */
    private static final int NUM_OF_MARKS = 12;

    /**
     * 填充charts数据
     */
    public static ChartModel fill(String name, List<LowerCaseKeyMap> data, Class chartModel) {
        Map<String, Series> seriesMap = parseSeriesMap(chartModel);
        XAxis axisInfo = parseXAxis(chartModel);
        List<YAxis> yAxises = parseYAxises(chartModel);

        ChartModel model = new ChartModel(name);

        model.setInterval(data.size() / NUM_OF_MARKS);
        model.addYAxises(yAxises);

        int yAxisNum = 1;
        for (Map.Entry<String, Series> e : seriesMap.entrySet()) {
            model.addSeries(e.getValue());
            if (e.getValue().getyAxisIndex() + 1 > yAxisNum) {
                yAxisNum++;
            }
        }

        for (LowerCaseKeyMap m : data) {
            // 如xAxis type为 time，横轴数据在Series中设置
            if (axisInfo.getType() != XAxisType.time) {
                model.addXAxis(axisInfo.format(axisInfo.getField()));
            }

            for (Map.Entry<String, Series> e : seriesMap.entrySet()) {
                String id = e.getKey();
                Series series = e.getValue();
                if (axisInfo.getType() == XAxisType.time) {
                    // 如xAxis type为 time，时间在Series中设置
                    Object[] value = new Object[]{m.get(axisInfo.getField()), m.get(id)};
                    series.addData(value);
                } else {
                    series.addData(m.get(id));
                }
            }
        }

        return model;
    }


    /**
     * 解析Series数据
     */
    private static Map<String, Series> parseSeriesMap(Class model) {
        Map<String, Series> seriesMap = new LinkedHashMap<>(4);
        Field[] fields = model.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(org.chobit.jspy.charts.annotation.Series.class)) {
                org.chobit.jspy.charts.annotation.Series series =
                        f.getAnnotation(org.chobit.jspy.charts.annotation.Series.class);

                String name = isBlank(series.value()) ? f.getName() : series.value();
                String id = isBlank(series.id()) ? f.getName() : series.id();
                boolean selected = series.selected();
                int yAxisIdx = series.yAxisIndex();
                ChartType type = series.type();
                String unit = series.unit();
                ValueType valType = series.valType();

                Series s = new Series(id, name);
                s.setSelected(selected);
                s.setyAxisIndex(yAxisIdx);
                s.setType(type);
                s.setUnit(unit);
                s.setValType(valType);

                seriesMap.put(humpToLine(f.getName()), s);
            }
        }
        return seriesMap;
    }


    /**
     * 解析横轴数据
     */
    private static XAxis parseXAxis(Class model) {
        Field[] fields = model.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(org.chobit.jspy.charts.annotation.XAxis.class)) {
                org.chobit.jspy.charts.annotation.XAxis axis = f.getAnnotation(org.chobit.jspy.charts.annotation.XAxis.class);
                String field = humpToLine(f.getName());
                XAxisType type = axis.type();
                ValueType valueType = axis.valueType();
                String format = axis.format();
                return new XAxis(field, type, valueType, format);
            }
        }
        throw new IllegalArgumentException("Chart Model Class中没有提供坐标轴信息");
    }


    /**
     * 解析纵轴数据
     */
    private static List<YAxis> parseYAxises(Class model) {
        List<YAxis> axisList = new LinkedList<>();

        Field[] fields = model.getDeclaredFields();
        int yAxisNum = 0;
        for (Field f : fields) {
            if (f.isAnnotationPresent(org.chobit.jspy.charts.annotation.Series.class)) {
                org.chobit.jspy.charts.annotation.Series series =
                        f.getAnnotation(org.chobit.jspy.charts.annotation.Series.class);

                if (series.yAxisIndex() + 1 > yAxisNum) {
                    YAxis axis = new YAxis(yAxisNum);
                    axis.setUnit(series.unit());
                    axis.setValType(series.valType());

                    axisList.add(axis);
                }
            }
        }

        return axisList;
    }


}


class XAxis {

    private String field;

    private XAxisType type;

    private ValueType valueType;

    private String format;

    private Format fmt;

    public XAxis(String field, XAxisType type, ValueType valueType, String format) {
        this.field = field;
        this.type = type;
        this.valueType = valueType;
        this.format = format;
        if (isNotBlank(format)) {
            switch (valueType) {
                case INT:
                case LONG:
                    fmt = new DecimalFormat(format);
                    break;
                case MILLS_TIME:
                    fmt = new SimpleDateFormat(format);
                    break;
            }
        }
        if (type == XAxisType.time && valueType != ValueType.MILLS_TIME) {
            throw new IllegalArgumentException("如Axis type 为time，其值的类型必须为MILLS_TIME");
        }
    }


    public Object format(Object value) {
        if (null != fmt) {
            return fmt.format(value);
        }
        return value;
    }

    public XAxisType getType() {
        return type;
    }

    public String getField() {
        return field;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public String getFormat() {
        return format;
    }
}