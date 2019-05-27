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

import static org.chobit.jspy.utils.Strings.humpToLine;
import static org.chobit.jspy.utils.Strings.isBlank;
import static org.chobit.jspy.utils.Strings.isNotBlank;

/**
 * ECharts工具包
 */
public abstract class ChartKit {

    /**
     * 填充charts数据
     */
    public static final ChartModel fill(String name, List<LowerCaseKeyMap> data, Class chartModel) {
        Map<String, String> seriesNameMap = parseSeriesNameMap(chartModel);
        AxisInfo axisInfo = parseAxisInfo(chartModel);

        ChartModel model = new ChartModel(name);
        Map<String, Series> seriesMap = new HashMap<>(seriesNameMap.size());
        for (Map.Entry<String, String> e : seriesNameMap.entrySet()) {
            Series s = new Series(e.getValue());
            seriesMap.put(e.getKey(), s);
            model.addSeries(s);
        }
        for (LowerCaseKeyMap m : data) {
            model.addXAxis(axisInfo.format(m.get(axisInfo.field)));
            for (Map.Entry<String, Series> e : seriesMap.entrySet()) {
                e.getValue().addData(m.get(e.getKey()));
            }
        }

        return model;
    }


    private static Map<String, String> parseSeriesNameMap(Class model) {
        Map<String, String> seriesMap = new HashMap<>(4);
        for (Field f : model.getDeclaredFields()) {
            if (f.isAnnotationPresent(org.chobit.jspy.charts.annotation.Series.class)) {
                org.chobit.jspy.charts.annotation.Series series =
                        f.getAnnotation(org.chobit.jspy.charts.annotation.Series.class);
                String name = isBlank(series.value()) ? f.getName() : series.value();
                seriesMap.put(humpToLine(f.getName()), name);
            }
        }
        return seriesMap;
    }


    private static AxisInfo parseAxisInfo(Class model) {
        for (Field f : model.getDeclaredFields()) {
            if (f.isAnnotationPresent(Axis.class)) {
                Axis axis = f.getAnnotation(Axis.class);
                String field = humpToLine(f.getName());
                AxisType type = axis.type();
                String format = axis.format();
                return new AxisInfo(field, type, format);
            }
        }
        throw new IllegalArgumentException("Chart Model Class中没有提供坐标轴信息");
    }


    private static class AxisInfo {

        private String field;

        private AxisType type;

        private String format;

        private Format fmt;

        public AxisInfo(String name, AxisType type, String format) {
            this.field = name;
            this.type = type;
            this.format = format;
            if (isNotBlank(format)) {
                switch (type) {
                    case INT:
                    case LONG:
                        fmt = new DecimalFormat(format);
                        break;
                    case MILLS_TIME:
                        fmt = new SimpleDateFormat(format);
                        break;
                }
            }
        }


        public Object format(Object value) {
            if (null != fmt) {
                return fmt.format(value);
            }
            return value;
        }

        public String getField() {
            return field;
        }

        public AxisType getType() {
            return type;
        }

        public String getFormat() {
            return format;
        }
    }

}
