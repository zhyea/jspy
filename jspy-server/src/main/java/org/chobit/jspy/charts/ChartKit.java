package org.chobit.jspy.charts;

import org.chobit.jspy.charts.annotation.Axis;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.chobit.jspy.core.utils.Strings.humpToLine;
import static org.chobit.jspy.core.utils.Strings.isBlank;
import static org.chobit.jspy.core.utils.Strings.isNotBlank;

/**
 * ECharts工具包
 */
public abstract class ChartKit {


    /**
     * 坐标轴上刻度的数量
     */
    private static final int NUM_OF_MARKS = 12;

    /**
     * 填充charts数据
     */
    public static final ChartModel fill(String name, List<LowerCaseKeyMap> data, Class chartModel) {
        Map<String, Series> seriesMap = parseSeriesNameMap(chartModel);
        AxisInfo axisInfo = parseAxisInfo(chartModel);

        ChartModel model = new ChartModel(name);

        model.setInterval(data.size() / NUM_OF_MARKS);
        for (Map.Entry<String, Series> e : seriesMap.entrySet()) {
            model.addSeries(e.getValue());
        }
        for (LowerCaseKeyMap m : data) {
            // 如xAxis type为 time，横轴数据在Series中设置
            if (axisInfo.getType() != AxisType.time) {
                model.addXAxis(axisInfo.format(axisInfo.getField()));
            }
            for (Map.Entry<String, Series> e : seriesMap.entrySet()) {
                if (axisInfo.getType() == AxisType.time) {
                    // 如xAxis type为 time，时间在Series中设置
                    Object[] value = new Object[]{m.get(axisInfo.getField()), m.get(e.getKey())};
                    e.getValue().addData(value);
                } else {
                    e.getValue().addData(m.get(e.getKey()));
                }
            }
        }

        return model;
    }


    private static Map<String, Series> parseSeriesNameMap(Class model) {
        Map<String, Series> seriesMap = new HashMap<>(4);
        Field[] fields = model.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(org.chobit.jspy.charts.annotation.Series.class)) {
                org.chobit.jspy.charts.annotation.Series series =
                        f.getAnnotation(org.chobit.jspy.charts.annotation.Series.class);
                String name = isBlank(series.value()) ? f.getName() : series.value();
                String id = isBlank(series.id()) ? f.getName() : series.id();
                seriesMap.put(humpToLine(f.getName()), new Series(id, name));
            }
        }
        return seriesMap;
    }


    private static AxisInfo parseAxisInfo(Class model) {
        Field[] fields = model.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Axis.class)) {
                Axis axis = f.getAnnotation(Axis.class);
                String field = humpToLine(f.getName());
                AxisType type = axis.type();
                ValueType valueType = axis.valueType();
                String format = axis.format();
                return new AxisInfo(field, type, valueType, format);
            }
        }
        throw new IllegalArgumentException("Chart Model Class中没有提供坐标轴信息");
    }


}

class AxisInfo {

    private String field;

    private AxisType type;

    private ValueType valueType;

    private String format;

    private Format fmt;

    public AxisInfo(String field, AxisType type, ValueType valueType, String format) {
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
        if (type == AxisType.time && valueType != ValueType.MILLS_TIME) {
            throw new IllegalArgumentException("如Axis type 为time，其值的类型必须为MILLS_TIME");
        }
    }


    public Object format(Object value) {
        if (null != fmt) {
            return fmt.format(value);
        }
        return value;
    }

    public AxisType getType() {
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