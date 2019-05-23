package org.chobit.jspy.tools;

import org.chobit.jspy.model.EChartModel;
import org.chobit.jspy.model.Series;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ECharts工具包
 */
public abstract class EChartKit {


    public static final EChartModel fill(String name, List<LowerCaseKeyMap> data, String axis, String... columns) {
        EChartModel model = new EChartModel<>(name);
        Map<String, Series> map = new HashMap<>(columns.length);
        for (String c : columns) {
            map.put(c, new Series(c));
        }
        for (LowerCaseKeyMap m : data) {
            model.addXAxis(m.get(axis));
            for (String c : columns) {
                map.get(c).addData(m.get(c));
            }
        }
        for (Series s : map.values()) {
            model.addSeries(s);
        }
        return model;
    }

}
